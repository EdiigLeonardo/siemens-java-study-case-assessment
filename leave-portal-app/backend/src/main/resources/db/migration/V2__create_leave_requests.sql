CREATE TABLE leave_requests (
  id BIGSERIAL PRIMARY KEY,
  employee_id BIGINT NOT NULL REFERENCES employees(id),
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  type VARCHAR(20) NOT NULL,
  status VARCHAR(20) NOT NULL,
  reason VARCHAR(1000),
  created_at TIMESTAMP NOT NULL
);
CREATE INDEX idx_leave_requests_employee_id ON leave_requests(employee_id);
-- BUG: falta a coluna "version" (BIGINT) para @Version / optimistic locking.
