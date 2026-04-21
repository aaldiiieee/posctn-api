-- liquibase formatted sql

-- changeset aaldiiieee:20260420082955
-- description: add_payment_status_to_transactions_table

ALTER TABLE transactions
ADD COLUMN IF NOT EXISTS payment_status VARCHAR(20);

