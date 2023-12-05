CREATE TABLE hospital_employees (
    employee_id         BIGINT,
    hospital_id         BIGINT,
    CONSTRAINT fk_employee_hospital FOREIGN KEY (employee_id) REFERENCES employees(id),
    CONSTRAINT fk_hospital_employees FOREIGN KEY (hospital_id) REFERENCES hospitals(id)
);