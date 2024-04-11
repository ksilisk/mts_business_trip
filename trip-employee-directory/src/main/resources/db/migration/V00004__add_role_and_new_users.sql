INSERT INTO roles(role)
VALUES ('LEAD');

INSERT INTO employees(username, name, surname, patronymic, phone, email, passport_seria,
                      passport_number, position, role_id, master_id)
VALUES ('lead', 'Nikolay', 'Smirnov', 'Andreevich', '12345678910', 'lead@test.ru', '1284', '129456', 'Head of Big Data',
        3, 3);

INSERT INTO employees(username, name, surname, patronymic, phone, email, passport_seria,
                      passport_number, position, master_id, role_id)
VALUES ('acco', 'James', 'Bond', 'Avraamovich', '22345678910', 'acco@test.ru', '1263', '183455', 'Accountant', 3, 2);

UPDATE employees
SET master_id = 3
WHERE username = 'test';
UPDATE employees
SET master_id = 3
WHERE username = 'test2';