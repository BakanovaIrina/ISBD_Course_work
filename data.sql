INSERT INTO elf (Name) VALUES
('Измир'),
('Галлен'),
('Ноа'),
('Менсон');

INSERT INTO gift (Name) VALUES
('Игрушечная машина'),
('Плюшевый медведь'),
('Лего'),
('Айфон'),
('Ароматизированная свеча');

INSERT INTO child (Name, Surname) VALUES
('Ирина', 'Баканова'),
('Ольга', 'Сахарова'),
('Тимофей', 'Соколов'),
('Валентин', 'Котиков'),
('Родион', 'Раскольников');

INSERT INTO address (country, Region, City, Street, House, Room) VALUES
('Россия', 'г. Санкт-Петербург', 'г. Санкт-Петербург', 'Королева', '2Б', 198),
('Россия', 'Московская обл.', 'Королев', 'Победы', '78', 2),
('Белорусь', 'Брестская обл.', 'Брест', 'Достаевского', '7к2', 666),
('Россия', 'Ульяновская обл.', 'г. Димитровград', 'Мулловское шоссе', '9', null),
('Россия', 'Республика Татарстан', 'Казань', 'Ярышлар', '9', 789);

INSERT INTO letter (IdChild, IdGift, IdAddress, Truth) VALUES
(1, 5, 1, TRUE),
(2, 4, 2, TRUE),
(3, 1, 3, FALSE),
(4, 3, 4, TRUE),
(5, 2, 5, TRUE);

INSERT INTO action (Name, Description, IdLetter, Positivity) VALUES
('Кормила кота', 'и иногда ела его еду', 1, TRUE),
('Училась отлично', null, 2, TRUE),
('Мыл посуду', 'Помогал маме мыть посуду', 3, TRUE),
('Гулял с собакой', 'Каждый вечер', 3, TRUE),
('Заработал миллион', 'в семь лет', 4, TRUE),
('Убил бабушку', 'топором', 5, FALSE);

UPDATE gift SET name = 'Уголь' WHERE id = 2;

INSERT INTO gift_status (IdGift, Time, Approval, Status_location) VALUES
(1, TIMESTAMP '2024-10-19 10:00:00', FALSE, 'in letter'),
(2, TIMESTAMP '2024-10-19 10:15:00', FALSE, 'in letter'),
(3, TIMESTAMP '2024-10-19 10:17:00', TRUE, 'in letter'),
(4, TIMESTAMP '2024-10-19 10:19:00', TRUE, 'in letter'),
(5, TIMESTAMP '2024-10-19 10:21:00', TRUE, 'in letter'),
(2, TIMESTAMP '2024-10-19 10:15:00', TRUE, 'in letter'),
(2, TIMESTAMP '2024-10-19 10:25:00', TRUE, 'in production'),
(2, TIMESTAMP '2024-11-21 13:35:00', TRUE, 'in storage'),
(3, TIMESTAMP '2024-11-22 12:00:55', TRUE, 'in production'),
(4, TIMESTAMP '2024-11-23 16:00:32', TRUE, 'in production'),
(5, TIMESTAMP '2024-11-23 16:10:32', TRUE, 'in production'),
(5, TIMESTAMP '2024-11-30 18:00:21', TRUE, 'in storage');

INSERT INTO elf_status (IdElf, Time, Rest) VALUES
(1, '2024-12-25 13:35:00', FALSE),
(2, '2024-12-25 13:41:22', FALSE),
(3, '2024-12-25 13:41:22', FALSE),
(4, '2024-12-25 13:20:05', TRUE);

INSERT INTO production (IdGift, WorkStatus) VALUES
(1, 'not started'),
(2, 'completed'),
(3, 'assembly'),
(4, 'materials selection'),
(5, 'completed');

INSERT INTO elf_production (IdElf, IdProduction) VALUES
(1, 4),
(2, 2),
(3, 2),
(4, 5),
(2, 3);

INSERT INTO storage (idgift) VALUES
(5),
(2);

INSERT INTO delivery (idgift, idchild, idaddress, place) VALUES
(5, 5, 5, 'under the tree');
