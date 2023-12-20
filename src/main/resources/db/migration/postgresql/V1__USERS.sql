CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf_cnpj VARCHAR(14) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    balance NUMERIC(19,2) NOT NULL,
    type VARCHAR(20) NOT NULL
);