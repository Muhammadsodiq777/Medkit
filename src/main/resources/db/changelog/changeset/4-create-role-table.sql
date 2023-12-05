CREATE TABLE roles(
    id              SERIAL PRIMARY KEY,
    role_name       VARCHAR(50)
);

INSERT INTO roles(id, role_name) VALUES
                                     (1, 'ROLE_SUPER_ADMIN'),
                                     (2, 'ROLE_MANAGER'),
                                     (3, 'ROLE_USER'),
                                     (4, 'ROLE_ORGANIZATION_ADMIN'),
                                     (5, 'ROLE_ORGANIZATION_MANAGER'),
                                     (6, 'ROLE_ORGANIZATION_DOCTOR'),
                                     (7, 'ROLE_ORGANIZATION_RECEPTION'),
                                     (8, 'ROLE_ORGANIZATION_CASHIER'),
                                     (9, 'ROLE_ORGANIZATION_NURSE');