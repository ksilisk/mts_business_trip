INSERT INTO Hotels (name, city)
VALUES ('Cosmos', 'SPB');


INSERT INTO Rooms (hotel_id, room_number, price, available)
VALUES ((SELECT id FROM Hotels WHERE name = 'Cosmos'), 101, 1500, true),
       ((SELECT id FROM Hotels WHERE name = 'Cosmos'), 102, 2000, true),
       ((SELECT id FROM Hotels WHERE name = 'Cosmos'), 103, 2500, true);


INSERT INTO Flights (flight_number, departure_city, arrival_city, departure_time, arrival_time, price, airline)
VALUES (12345, 'MSK', 'SPB', '2024-04-12 08:00:00', '2024-04-12 10:00:00', 10000, 'Aeroflot'),
       (54321, 'MSK', 'SPB', '2024-04-12 12:00:00', '2024-04-12 14:00:00', 15000, 'Aeroflot');

insert into customers(customer_name, customer_surname, customer_patronymic, passport_data)
VALUES ('Alexandr', 'Popov', 'Alexandrovich', '2015123987'),
       ('Irina', 'Sidorova', 'Vasilievna', '1233123455');


