alter table hospitals
    drop constraint fk_hospital_owners;

alter table hospitals
    drop column owner_id;

alter table hospitals
    drop column password;