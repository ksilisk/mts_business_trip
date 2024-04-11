CREATE TABLE IF NOT EXISTS hotels
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255),
    city VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS rooms
(
    id          SERIAL PRIMARY KEY,
    hotel_id    INTEGER REFERENCES Hotels (id),
    room_number int,
    price       int,
    available   boolean
);

CREATE TABLE IF NOT EXISTS flights
(
    id             SERIAL PRIMARY KEY,
    flight_number  int,
    departure_city VARCHAR(100),
    arrival_city   VARCHAR(100),
    departure_time timestamp,
    arrival_time   TIMESTAMP,
    price          int,
    airline        varchar

);

create table IF NOT EXISTS customers
(
    id                  SERIAL PRIMARY KEY,
    customer_name       VARCHAR(100),
    customer_surname    varchar,
    customer_patronymic varchar,
    passport_data       varchar
);

CREATE TABLE IF NOT EXISTS bookings
(
    id             SERIAL PRIMARY KEY,
    room_id        INTEGER UNIQUE REFERENCES Rooms (id),
    flight_id      INTEGER REFERENCES Flights (id),
    customer_id    INTEGER references Customers (id),
    booking_date   TIMESTAMP,
    check_in_time  timestamp,
    check_out_time timestamp,
    booking_number int
);
