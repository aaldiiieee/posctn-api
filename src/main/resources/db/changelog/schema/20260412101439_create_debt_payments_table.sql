-- liquibase formatted sql

-- changeset aaldiiieee:20260412101439
-- description: create_debt_payments_table

CREATE TABLE debt_payments (
    id SERIAL PRIMARY KEY,
    debt_id INT NOT NULL,
    amount NUMERIC(12,2) NOT NULL CHECK (amount > 0),
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_debt
       FOREIGN KEY (debt_id) REFERENCES debts(id) ON DELETE CASCADE
);

