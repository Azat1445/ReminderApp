--liquibase formatted sql

--changeset admin:1
ALTER TABLE reminders
ADD COLUMN status VARCHAR(32) NOT NULL DEFAULT 'PENDING';


