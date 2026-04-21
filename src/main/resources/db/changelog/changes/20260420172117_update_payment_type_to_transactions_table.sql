-- liquibase formatted sql

-- changeset aaldiiieee:20260420172117
-- description: update_payment_type_to_transactions_table

ALTER TABLE transactions
DROP CONSTRAINT transactions_payment_type_check;

ALTER TABLE transactions
ADD CONSTRAINT transactions_payment_type_check
CHECK (payment_type IN ('CASH', 'TRANSFER', 'KASBON'));

