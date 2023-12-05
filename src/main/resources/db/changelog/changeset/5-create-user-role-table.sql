CREATE TABLE user_roles(
    user_id     BIGINT,
    role_id     BIGINT,
    CONSTRAINT fk_user_roles FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_roles_users FOREIGN KEY (role_id) REFERENCES roles(id)
);