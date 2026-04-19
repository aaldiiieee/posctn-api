-- liquibase formatted sql

-- changeset aaldiiieee:20260412100814
-- description: create_transactions_items_table

CREATE TABLE transaction_items (
    id SERIAL PRIMARY KEY,
    transaction_id INT NOT NULL,
    menu_item_id INT NOT NULL,
    qty INT NOT NULL CHECK (qty > 0),
    price NUMERIC(12,2) NOT NULL,
    subtotal NUMERIC(12,2) NOT NULL,

    CONSTRAINT fk_trx_items_trx
       FOREIGN KEY (transaction_id) REFERENCES transactions(id) ON DELETE CASCADE,

    CONSTRAINT fk_trx_items_menu
       FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);

CREATE INDEX idx_trx_items_trx ON transaction_items(transaction_id);

