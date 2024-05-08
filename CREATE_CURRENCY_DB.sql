CREATE TABLE UserC (
    login VARCHAR2(20) NOT NULL,
    password VARCHAR2(20) NOT NULL,
    user_id NUMBER(3) NOT NULL
);

ALTER TABLE UserC
ADD CONSTRAINT userc_pk
PRIMARY KEY (user_id);



CREATE TABLE Currency (
    currency_name VARCHAR2(20) NOT NULL, 
    currency_id VARCHAR2(4) NOT NULL
);

ALTER TABLE Currency
ADD CONSTRAINT currency_pk
PRIMARY KEY (currency_id);



CREATE TABLE converts_into (
    rate NUMBER(8) NOT NULL,
    currency_A VARCHAR2(4),
    currency_B VARCHAR2(4),
    exchange_id FLOAT(8) NOT NULL
);

ALTER TABLE converts_into 
ADD CONSTRAINT converts_into_fk1
FOREIGN KEY (currency_A)
REFERENCES Currency(currency_id);

ALTER TABLE converts_into 
ADD CONSTRAINT converts_into_fk2
FOREIGN KEY (currency_B)
REFERENCES Currency(currency_id);

ALTER TABLE converts_into
ADD CONSTRAINT converts_into_pk
PRIMARY KEY (exchange_id);



CREATE TABLE ExchangeHistory (
    userID NUMBER(3),
    exchangeID FLOAT(8)
);

ALTER TABLE ExchangeHistory 
ADD CONSTRAINT ExchangeHistory_fk1
FOREIGN KEY (userID)
REFERENCES UserC(user_id);

ALTER TABLE ExchangeHistory 
ADD CONSTRAINT ExchangeHistory_fk2
FOREIGN KEY (exchangeID)
REFERENCES converts_into(exchange_id);

ALTER TABLE ExchangeHistory
ADD CONSTRAINT ExchangeHistory_pk
PRIMARY KEY (userID);