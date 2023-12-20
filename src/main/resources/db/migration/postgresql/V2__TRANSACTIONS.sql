CREATE TABLE transactions (
  id BIGSERIAL NOT NULL PRIMARY KEY ,
    payee_id BIGINT NOT NULL,
    payer_id BIGINT NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_payee_id FOREIGN KEY (payee_id) REFERENCES users (id),
    CONSTRAINT fk_payer_id FOREIGN KEY (payer_id) REFERENCES users (id)
);