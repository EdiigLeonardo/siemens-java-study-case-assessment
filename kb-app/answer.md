# Respostas — Knowledge Base App

## Backend
1. Trocar `record Author` por classe normal com `@NoArgsConstructor`/setters (ou `@Embeddable` com Lombok `@Getter/@Setter/@NoArgsConstructor/@AllArgsConstructor`).
2. `hasCategory`: `return category == null ? null : (root,q,cb) -> cb.equal(root.get("category"), category);` — `Specification.and()` já ignora specs `null`.
3. `keyword`: usar índice trigram + `ILIKE`, ou pesquisa full-text (`to_tsvector`/`to_tsquery`) em vez de `LIKE '%...%'`.
4. `withTagsFetched`: usar `@EntityGraph(attributePaths = "tags")` no repositório para esse caso específico, sem misturar fetch join com Specification+Pageable.
5. `findByCategoryLoose`: assinatura `findByCategory(String category)` normal — devolver `Optional<Article>` só quando fizer sentido (ex.: `findById`), nunca receber `Optional` como parâmetro.
6. Controller: whitelist de campos de sort permitidos, validar `pageable.getSort()` antes de aplicar (ou usar um DTO de request próprio em vez de `Pageable` cru).
7. Calcular `maximum-pool-size` = `max_connections do Postgres / número de réplicas` (com margem), ou introduzir PgBouncer.
8. `CREATE EXTENSION IF NOT EXISTS pg_trgm; CREATE INDEX idx_article_title_trgm ON article USING gin (title gin_trgm_ops);`

## Frontend
9. `this.keyword.valueChanges.pipe(debounceTime(300), distinctUntilChanged()).subscribe(...)`.
10. `runSearch()`: `this.page = 0;` sempre que o `keyword` mudar (só incrementar `page` na paginação).
11. Sincronizar com `Router.navigate([], { queryParams: { keyword, page } })` e ler o estado inicial de `ActivatedRoute.queryParams`.

## K8s
12. Adicionar `affinity.podAntiAffinity` (ou `topologySpreadConstraints`) para espalhar réplicas por nodes diferentes.
13. Colocar PgBouncer entre app e Postgres, ou reduzir `maximum-pool-size` para caber no `max_connections` disponível.
14. Criar `ConfigMap`/`Secret` com os valores de ambiente e injetar via `envFrom`, removendo valores fixos do `application.yml` embutido na imagem.
