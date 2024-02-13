--Триггер для проверки перед тем, как добавить подарок в производство (статус подарка одобрен).

CREATE OR REPLACE FUNCTION check_gift_approval()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM gift_status WHERE idgift = NEW.idgift AND approval = TRUE
    ) THEN
        RAISE EXCEPTION 'Cannot add gift to production. Gift is not approved.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_gift_approval_trigger
BEFORE INSERT ON production
FOR EACH ROW
EXECUTE FUNCTION check_gift_approval();


-- Проверка соответствия статуса подарка со реальным статусом (
--    если последняя по дате запись статуса подарка 'in letter', то производство 'not started',
--    если последняя по дате запись статуса подарка 'in production', то производство может быть 'materials selection',
--                                                  'assembly', 'packaging',
--    если последняя по дате запись статуса подарка 'in storage', то производство 'completed'
--                                                    и в storage есть запись о хранении подарка с данным id,
--    если последняя по дате запись статуса подарка 'in delivery', то производство 'completed'
--                                                    и в delivery есть запись о подарке с данным id)


CREATE OR REPLACE FUNCTION check_gift_status()
RETURNS TRIGGER AS $$
DECLARE
    latest_gift_status TEXT;
BEGIN
    SELECT status_location INTO latest_gift_status
    FROM gift_status
    WHERE idgift = NEW.idgift
    ORDER BY time DESC
    LIMIT 1;

    CASE latest_gift_status
        WHEN 'in letter' THEN
            IF NEW.workstatus != 'not started' THEN
                RAISE EXCEPTION 'Production status must be "not started" when gift status is "in letter".';
            END IF;
        WHEN 'in production' THEN
            IF NEW.workstatus NOT IN ('materials selection', 'assembly', 'packaging') THEN
                RAISE EXCEPTION 'Production status must be "materials selection", "assembly", or "packaging" when gift status is "in production".';
            END IF;
        WHEN 'in storage' THEN
            IF NOT EXISTS (
                SELECT 1 FROM storage WHERE idgift = NEW.idgift
            ) THEN
                RAISE EXCEPTION 'Gift is marked as "in storage" but there is no corresponding record in storage table.';
            END IF;
            IF NEW.workstatus != 'completed' THEN
                RAISE EXCEPTION 'Production status must be "completed" when gift status is "in storage".';
            END IF;
        WHEN 'in delivery' THEN
            IF NOT EXISTS (
                SELECT 1 FROM delivery WHERE idgift = NEW.idgift
            ) THEN
                RAISE EXCEPTION 'Gift is marked as "in delivery" but there is no corresponding record in delivery table.';
            END IF;
            IF NEW.workstatus != 'completed' THEN
                RAISE EXCEPTION 'Production status must be "completed" when gift status is "in delivery".';
            END IF;
    END CASE;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_gift_status_trigger
BEFORE INSERT ON production
FOR EACH ROW
EXECUTE FUNCTION check_gift_status();


-- Проверка того, что эльф не отлынивает от работы

CREATE OR REPLACE FUNCTION check_elf_work()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.rest = TRUE THEN
        SELECT workstatus
        FROM production
        JOIN elf_production ON production.id = elf_production.idProduction
        WHERE elf_production.idElf = NEW.idElf;

        IF workstatus IS NULL OR workstatus != 'completed' THEN
            RAISE EXCEPTION 'Elf cannot rest until all work is completed.';
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_elf_work_trigger
BEFORE UPDATE ON elf_status
FOR EACH ROW
EXECUTE FUNCTION check_elf_work();


-- Проверка хранения ГОТОВЫХ подарков в хранилище и в доставке (в таблицах storage и delivery должны быть только те строки, у которых
--            статус подарка готов)

CREATE OR REPLACE FUNCTION check_gift_status()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_TABLE_NAME = 'storage' THEN
        IF NOT EXISTS (
            SELECT 1 FROM gift_status WHERE idgift = NEW.idgift AND approval = TRUE AND status_location = 'in storage'
        ) THEN
            RAISE EXCEPTION 'Cannot store gift in storage. Gift is not ready.';
        END IF;
    END IF;

    IF TG_TABLE_NAME = 'delivery' THEN
        IF NOT EXISTS (
            SELECT 1 FROM gift_status WHERE idgift = NEW.idgift AND approval = TRUE AND status_location = 'in delivery'
        ) THEN
            RAISE EXCEPTION 'Cannot deliver gift. Gift is not ready.';
        END IF;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_gift_status_trigger_storage
BEFORE INSERT ON storage
FOR EACH ROW
EXECUTE FUNCTION check_gift_status();

CREATE TRIGGER check_gift_status_trigger_delivery
BEFORE INSERT ON delivery
FOR EACH ROW
EXECUTE FUNCTION check_gift_status();


-- Проверка, что подарок действительно должен быть у этого ребенка (уголь, если ложь или плохо вел) при добавлении в доставку

CREATE OR REPLACE FUNCTION check_gift_delivery()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM letter WHERE idchild = NEW.idchild AND idgift = NEW.idgift AND truth = TRUE
    )
    THEN
        RAISE EXCEPTION 'Cannot deliver. Gift is not approved.';
    END IF;

    IF NOT EXISTS (
        SELECT 1 FROM letter l
        JOIN action a ON l.id = a.idletter
        JOIN gift_status gs ON l.IdGift = gs.IdGift
        WHERE l.idgift = NEW.idgift AND (l.truth = FALSE OR a.positivity = FALSE) AND gs.approval = TRUE
    )
     THEN
        RAISE EXCEPTION 'Cannot add gift to delivery. Gift is not intended for this child or child did not tell the truth.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_gift_delivery_trigger
BEFORE INSERT ON delivery
FOR EACH ROW
EXECUTE FUNCTION check_gift_delivery();