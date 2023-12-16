ALTER TABLE users
    ADD COLUMN sms_code     VARCHAR(10),
    ADD COLUMN session_key  VARCHAR(50);