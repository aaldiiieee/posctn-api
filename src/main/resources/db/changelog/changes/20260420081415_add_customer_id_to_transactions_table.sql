-- liquibase formatted sql

-- changeset aaldiiieee:20260420081415
-- description: add_customer_id_to_transactions_table

ALTER TABLE transactions
ADD COLUMN IF NOT EXISTS customer_id BIGINT;

-- Relational

ALTER TABLE transactions
ADD CONSTRAINT fk_transaction_customer
    FOREIGN KEY (customer_id) REFERENCES customers(id);