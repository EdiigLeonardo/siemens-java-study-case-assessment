# Guia — Knowledge Base App (ronda 6)

Padrão: Specification (Criteria API) + pesquisa paginada. Domínio: artigos de conhecimento.

## Backend (8)
- [ ] `Author` é um `record` usado como `@Embeddable` → Hibernate não consegue reconstruir/atualizar
- [ ] `hasCategory(null)` gera `WHERE category = NULL` → pesquisa "sem filtro" devolve zero resultados
- [ ] `keyword()`: `LIKE '%termo%'` com wildcard inicial → sem uso de índice
- [ ] `withTagsFetched()` + `Specification` + `Pageable` → paginação em memória/exceção do Hibernate
- [ ] `findByCategoryLoose(Optional<String>)` → `Optional` usado como parâmetro (anti-padrão)
- [ ] `search()` no controller: sem whitelist de campos de `sort` → `PropertyReferenceException` com campo inexistente
- [ ] `hikari.maximum-pool-size: 50` sem olhar ao `max_connections` do Postgres nem ao nº de réplicas
- [ ] Tabela sem índice trigram (`pg_trgm`) para pesquisa por texto

## Frontend (4)
- [ ] `keyword.valueChanges` sem `debounceTime`/`distinctUntilChanged` → 1 pedido por tecla
- [ ] `runSearch()`: `page` não volta a 0 quando o filtro muda
- [ ] Estado da pesquisa nunca vai para a URL (`queryParams`) → perde-se ao dar refresh/partilhar link
- [ ] (revê o componente à procura de mais 1 problema)

## K8s (3)
- [ ] Deployment sem `podAntiAffinity`/`topologySpreadConstraints` → réplicas podem cair todas no mesmo node
- [ ] 4 réplicas × pool de 50 ligações, sem PgBouncer → esgota `max_connections` do Postgres sob carga
- [ ] Sem `ConfigMap` → configuração compilada dentro da imagem (viola 12-factor)

## Features para dominares
**Spring:** `Specification.where(null)` idiomático + builder que só adiciona predicados de filtros não-nulos; combinar `Specification` com `EntityGraph` em vez de `fetch()` para evitar o bug de paginação.
**PostgreSQL:** `CREATE EXTENSION pg_trgm` + índice GIN para `ILIKE`; calcular `maximum-pool-size` = `max_connections / réplicas`.
**Docker:** imagem com config 100% via env vars/profiles (nada de valores fixos no `application.yml` do jar).
**Angular:** serviço de pesquisa reutilizável com `debounceTime + distinctUntilChanged + switchMap`; sincronizar filtros com `Router.navigate({queryParams})`.
**K8s:** `topologySpreadConstraints`; PgBouncer como Deployment/Service entre a app e o Postgres.

Soluções: `answer.md`.
