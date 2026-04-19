-- liquibase formatted sql

-- changeset aaldiiieee:20260412093208
-- description: create_users_table

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    fullname VARCHAR(150) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('ADMIN', 'CASHIER', 'OWNER')),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);