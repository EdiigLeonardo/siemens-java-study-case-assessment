CREATE TABLE equipment (id BIGSERIAL PRIMARY KEY, name VARCHAR(255));
CREATE TABLE booking (
  id BIGSERIAL PRIMARY KEY,
  equipment_id BIGINT REFERENCES equipment(id),
  requester_email VARCHAR(255),
  start_time TIMESTAMP,   -- BUG: sem "WITH TIME ZONE".
  end_time TIMESTAMP,
  status VARCHAR(20)
  -- BUG: sem UNIQUE/EXCLUDE constraint -> nada impede overlap na BD (so a app "tenta" validar).
);
