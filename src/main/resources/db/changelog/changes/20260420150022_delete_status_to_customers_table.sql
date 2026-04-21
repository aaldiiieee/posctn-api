-- liquibase formatted sql

-- changeset aaldiiieee:20260420150022
-- description: delete_status_to_customers_table

ALTER TABLE customers
DROP COLUMN status;