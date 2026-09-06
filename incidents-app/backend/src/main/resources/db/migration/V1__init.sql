CREATE TABLE incident (
  id BIGSERIAL PRIMARY KEY, title VARCHAR(255), status INT, internal_notes VARCHAR(1000), created_at TIMESTAMP
);
CREATE TABLE comment (
  id BIGSERIAL PRIMARY KEY, incident_id BIGINT REFERENCES incident(id), body VARCHAR(1000)
);
