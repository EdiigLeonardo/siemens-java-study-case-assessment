CREATE TABLE article (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255),
  body VARCHAR(5000),
  category VARCHAR(100),
  author_name VARCHAR(255),
  author_email VARCHAR(255),
  created_at TIMESTAMP
);
CREATE TABLE article_tags (article_id BIGINT REFERENCES article(id), tags VARCHAR(100));
-- BUG: pesquisa por titulo faz ILIKE '%termo%' sem indice trigram (pg_trgm) -> sequential scan.
