-- Функция для добавления письма (данных в ребенка, адрес, подарок, поступки, ложь и положительность; статусы)

CREATE OR REPLACE FUNCTION add_letter(
    child_name VARCHAR(32),
    child_surname VARCHAR(32),
    country1 VARCHAR(32),
    region1 VARCHAR(32),
    city1 VARCHAR(32),
    street1 VARCHAR(32),
    house1 VARCHAR(32),
    room1 INTEGER,
    gift_name VARCHAR(32),
    actions VARCHAR[],
    descriptions VARCHAR[],
    truth1 BOOLEAN,
    approval1 BOOLEAN,
    positivities BOOLEAN[]
) RETURNS VOID AS $$
DECLARE
    child_id INTEGER;
    address_id INTEGER;
    gift_id INTEGER;
    letter_id INTEGER;
    i INTEGER;
BEGIN
    -- Проверяем существует ли ребенок в таблице, если нет - добавляем
    INSERT INTO child (name, surname) VALUES (child_name, child_surname) ON CONFLICT DO NOTHING;
    SELECT id INTO child_id FROM child WHERE name = child_name AND surname = child_surname;

    -- Проверяем существует ли адрес в таблице, если нет - добавляем
    INSERT INTO address (Country, Region, City, Street, House, Room) VALUES
                                    (country1, region1, city1, street1, house1, room1) ON CONFLICT DO NOTHING;
    SELECT id INTO address_id FROM address WHERE country = country1 AND region = region1 AND city = city1 AND street = street1 AND house = house1 AND room = room1;

    -- Проверяем существует ли подарок в таблице, если нет - добавляем
    INSERT INTO gift (name) VALUES (gift_name) ON CONFLICT DO NOTHING;
    SELECT id INTO gift_id FROM gift WHERE name = gift_name;

    -- Добавляем запись о письме
    INSERT INTO letter (idChild, idGift, idAddress, truth) VALUES (child_id, gift_id, address_id, truth1) RETURNING id INTO letter_id;

    -- Добавляем поступки из массива actions и их описания из массива descriptions
    FOR i IN 1..ARRAY_LENGTH(actions, 1) LOOP
        INSERT INTO action (idletter, name, description, positivity) VALUES (letter_id, actions[i], descriptions[i], positivities[i]);
    END LOOP;

    -- Добавляем запись о статусе подарка
    INSERT INTO gift_status (idgift, time, approval, status_location) VALUES (gift_id, CURRENT_TIMESTAMP, approval1, 'in letter');

END;
$$ LANGUAGE plpgsql;


-- Функция занесения подарка в производство (включая проверку одобрения подарка (если не одобрен, то заменяется углем))

CREATE OR REPLACE FUNCTION add_gift_to_production(gift_id INTEGER) RETURNS VOID AS $$
DECLARE
    gift_approval BOOLEAN;
BEGIN
    SELECT approval INTO gift_approval FROM gift_status WHERE idgift = gift_id AND status_location = 'in letter' LIMIT 1;

    -- Если подарок не одобрен, меняем его на "Уголь" и устанавливаем одобрение на положительное
    IF gift_approval = FALSE THEN
        UPDATE gift SET name = 'Уголь' WHERE id = gift_id;
        INSERT INTO gift_status (idgift, time, approval, status_location) VALUES (gift_id, CURRENT_TIMESTAMP, TRUE, 'in letter');
    END IF;

    -- Добавляем подарок в производство
    IF NOT EXISTS (SELECT 1 FROM production WHERE idgift = gift_id) THEN
        INSERT INTO production (idgift, workstatus) VALUES (gift_id, 'not started');
    END IF;

    RETURN;
END;
$$ LANGUAGE plpgsql;

-- Назначение эльфа (-ов) для производства в конкретный подарок (изменение rest на false, добавление данных о
-- связи эльфа и производства отдельного подарка)

CREATE OR REPLACE FUNCTION assign_elfs_to_production(elf_ids INTEGER[], production_id INTEGER) RETURNS VOID AS $$
DECLARE
    elf_id INTEGER;
BEGIN
    FOREACH elf_id IN ARRAY elf_ids LOOP
        UPDATE elf_status SET rest = FALSE WHERE id = elf_id;
        INSERT INTO elf_production (idelf, idproduction) VALUES (elf_id, production_id);
    END LOOP;

    RETURN;
END;
$$ LANGUAGE plpgsql;



-- Эльфы завершают производство над конкретным подарком (изменение rest на true, статус подарка изменяется на 'in storage',
-- производство этого подарка на 'completed', хранение назначается)

CREATE OR REPLACE FUNCTION complete_production(gift_id INTEGER) RETURNS VOID AS $$
DECLARE
    production_id INTEGER;
    elf_id INTEGER;
BEGIN
    SELECT id INTO production_id FROM production
    WHERE idgift = gift_id LIMIT 1;

    SELECT id INTO elf_id FROM elf_production
    WHERE idproduction = production_id;

    UPDATE elf_status SET rest = TRUE
    WHERE idelf = elf_id;

    UPDATE gift_status SET status_location = 'in storage' WHERE idgift = gift_id;

    UPDATE production SET workstatus = 'completed' WHERE id = production_id;

    INSERT INTO storage (IdGift) VALUES (gift_id);

    RETURN;
END;
$$ LANGUAGE plpgsql;

-- Перемещение подарков из всех ячеек хранения storage в доставку, очищение ячеек storage, изменение статуса на 'in delivery',
-- место выбирается случайно

CREATE OR REPLACE FUNCTION move_gifts_to_delivery() RETURNS VOID AS $$
DECLARE
    delivery_place VARCHAR(16);
BEGIN

        delivery_place := CASE floor(random() * 5)
                        WHEN 0 THEN 'under the pillow'
                        WHEN 1 THEN 'in socks'
                        WHEN 2 THEN 'on the windowsill'
                        WHEN 3 THEN 'near the fireplace'
                        ELSE 'under the tree'
                      END;

    INSERT INTO delivery (idgift, idchild, idaddress, place)
    SELECT s.idgift, l.idchild, l.idaddress, delivery_place
    FROM storage s
    JOIN letter l ON s.idgift = l.idgift;

    UPDATE gift_status SET status_location = 'in delivery' WHERE idgift IN (SELECT idgift FROM delivery);

    DELETE FROM storage;

    RETURN;
END
$$ LANGUAGE plpgsql;