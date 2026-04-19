-- liquibase formatted sql

-- changeset aaldiiieee:20260412095632
-- description: create_transactions_table

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount NUMERIC(12,2) NOT NULL,
    payment_type VARCHAR(20) NOT NULL CHECK (payment_type IN ('CASH', 'DEBT')),
    created_by INT NOT NULL,

    CONSTRAINT fk_transactions_user
      FOREIGN KEY (created_by) REFERENCES users(id)
);

CREATE INDEX idx_transactions_date ON transactions(transaction_date);

