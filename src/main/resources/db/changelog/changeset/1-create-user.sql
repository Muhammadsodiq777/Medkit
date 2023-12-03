CREATE TABLE users (
    id                  SERIAL PRIMARY KEY,
    created_date        TIMESTAMP,
    updated_date        TIMESTAMP,
    active              BOOLEAN NOT NULL DEFAULT FALSE,
    phone_number        VARCHAR(50),
    email               VARCHAR(100),
    password            VARCHAR(255),
    type                VARCHAR(20)
);

CREATE TABLE patients (
    id                          SERIAL PRIMARY KEY,
    created_date                TIMESTAMP,
    updated_date                TIMESTAMP,
    active                      BOOLEAN NOT NULL DEFAULT FALSE,
    firstname                   VARCHAR(255),
    lastname                    VARCHAR(255),
    parent_name                 VARCHAR(255),
    passport_serial             VARCHAR(255),
    passport_number             VARCHAR(255),
    user_id                     BIGINT NOT NULL,
    CONSTRAINT fk_patients_user FOREIGN KEY (user_id) REFERENCES users(id)
);
