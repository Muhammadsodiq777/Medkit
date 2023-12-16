insert into users(created_date, updated_date, active, phone_number, email, password)
        values (now(), now(), true, '+998998734836', 'rdoston22@gmail.com', '$2a$10$buGktDeq28/sTYvS3UyRSeNvgj7NBgWNSTuwfiETnOHDyWV3URRwC');

insert into user_roles(user_id, role_id)
        values (1, 1);