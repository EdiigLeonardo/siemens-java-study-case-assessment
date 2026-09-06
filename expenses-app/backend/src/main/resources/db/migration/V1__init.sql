CREATE TABLE expense (
  id BIGSERIAL PRIMARY KEY,
  employee_email VARCHAR(255),
  description VARCHAR(500),
  amount DOUBLE PRECISION,   -- BUG: devia ser NUMERIC(12,2) para dinheiro.
  status VARCHAR(20)
  -- BUG: sem CHECK (amount > 0) - a app valida, a BD nao. Um insert direto/migration futura pode gravar negativo.
);
