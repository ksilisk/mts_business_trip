CREATE TABLE IF NOT EXISTS applications
(
    id SERIAL PRIMARY KEY,
    trip_goal TEXT,
    username TEXT UNIQUE,
    income_city VARCHAR,
    income_country VARCHAR,
    trip_argument VARCHAR,
    status VARCHAR,
    start_date DATE,
    end_date DATE,
    prepayment_type VARCHAR,
    card_number VARCHAR,
    master_id INT
);

CREATE TABLE IF NOT EXISTS advance_reports
(
    id SERIAL PRIMARY KEY,
    application_id INTEGER UNIQUE REFERENCES applications(id),
    description TEXT
);


