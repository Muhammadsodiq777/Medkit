CREATE TABLE employees(
    id SERIAL PRIMARY KEY,
    active          BOOLEAN NOT NULL DEFAULT FALSE,
    firstname       VARCHAR(255),
    lastname        VARCHAR(255),
    passport_number VARCHAR(255),
    passport_serial VARCHAR(255),
    pinfl           VARCHAR(255),
    created_date    TIMESTAMP,
    updated_date    TIMESTAMP,
    employee_id     BIGINT,
    CONSTRAINT fk_employee_users FOREIGN KEY (employee_id) REFERENCES users(id)

);

CREATE TABLE  hospitals(
    id             SERIAL PRIMARY KEY ,
    active         BOOLEAN NOT NULL DEFAULT FALSE,
    account_number VARCHAR(255),
    email          VARCHAR(255),
    name           VARCHAR(255),
    owner_name     VARCHAR(255),
    password       VARCHAR(255),
    phone_number   VARCHAR(255),
    created_date   TIMESTAMP,
    updated_date   TIMESTAMP,
    owner_id       BIGINT,
    CONSTRAINT fk_hospital_owners FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE images (
    id                  SERIAL PRIMARY KEY,
    created_date        TIMESTAMP,
    updated_date        TIMESTAMP,
    active              BOOLEAN NOT NULL DEFAULT FALSE,
    content_type        VARCHAR(20),
    url                 VARCHAR(255),
    length              BIGINT,
    filename            VARCHAR(100),
    type                VARCHAR(30),
    patient_id          BIGINT,
    hospital_id         BIGINT,
    employee_id         BIGINT,
    CONSTRAINT fk_images_patients FOREIGN KEY (patient_id) REFERENCES patients(id),
    CONSTRAINT fk_images_hospitals FOREIGN KEY (hospital_id) REFERENCES hospitals(id),
    CONSTRAINT fk_images_employees FOREIGN KEY (employee_id) REFERENCES employees(id)
);
