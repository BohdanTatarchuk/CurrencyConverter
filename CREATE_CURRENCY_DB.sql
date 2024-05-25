CREATE TABLE UserC (
    login VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    user_id INTEGER, --GENERATED ALWAYS AS IDENTITY,
    PRIMARY KEY (user_id)
);

CREATE TABLE Currency (
    currency_name VARCHAR(20) NOT NULL,
    currency_id VARCHAR(4) NOT NULL,
    PRIMARY KEY (currency_id)
);

CREATE TABLE converts_into (
    rate FLOAT NOT NULL,
    currency_A VARCHAR(4),
    currency_B VARCHAR(4),
    exchange_id INTEGER, -- GENERATED ALWAYS AS IDENTITY,
    FOREIGN KEY (currency_A) REFERENCES Currency(currency_id),
    FOREIGN KEY (currency_B) REFERENCES Currency(currency_id),
    PRIMARY KEY (exchange_id)
);

CREATE TABLE ExchangeHistory (
    user_id INTEGER,
    exchange_id INTEGER,
    request_id INTEGER, --GENERATED ALWAYS AS IDENTITY,
    request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES UserC(user_id),
    FOREIGN KEY (exchange_id) REFERENCES converts_into(exchange_id),
    PRIMARY KEY (request_id)
);