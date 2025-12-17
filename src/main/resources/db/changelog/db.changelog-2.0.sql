--liquibase formatted sql

--changeset admin:1
ALTER TABLE reminders
ADD COLUMN status VARCHAR(32) NOT NULL DEFAULT 'PENDING';

--changeset admin:2
ALTER TABLE users
ADD COLUMN birth_date DATE;

ALTER TABLE users
ADD COLUMN firstname VARCHAR(64);

ALTER TABLE users
ADD COLUMN lastname VARCHAR(64);
