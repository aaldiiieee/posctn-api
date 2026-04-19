-- liquibase formatted sql

-- changeset aaldiiieee:20260412100927
-- description: create_debts_table

CREATE TABLE debts (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    transaction_id INT NOT NULL,
    amount NUMERIC(12,2) NOT NULL,
    remaining_amount NUMERIC(12,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'UNPAID' CHECK (status IN ('UNPAID', 'PAID')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_debts_customer
       FOREIGN KEY (customer_id) REFERENCES customers(id),

    CONSTRAINT fk_debts_transaction
       FOREIGN KEY (transaction_id) REFERENCES transactions(id)
);

CREATE INDEX idx_debts_customer ON debts(customer_id);
