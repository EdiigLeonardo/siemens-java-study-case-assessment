CREATE TABLE comments (
    id UUID PRIMARY KEY,
    ticket_id UUID NOT NULL REFERENCES tickets(id),
    author VARCHAR(255) NOT NULL,
    body VARCHAR(4000) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_comments_ticket_id ON comments(ticket_id);

-- Reparar: ha uma foreign key para tickets(id) mas nenhum indice em
-- ticket_id. Toda a query "comentarios de um ticket" faz table scan
-- a medida que a tabela cresce.
