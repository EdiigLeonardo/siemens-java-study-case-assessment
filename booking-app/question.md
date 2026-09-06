# Guia — Booking App (ronda 4)

Padrão: Event-Driven (Spring Events) + Specification-ready. Domínio: reserva de equipamento. Foco: Spring+Postgres+Docker+Angular+K8s.

## Backend / Postgres (8)
- [ ] `@EventListener` síncrono publicado antes do commit → timing/visibilidade errada
- [ ] Query nativa com concatenação de string → SQL injection
- [ ] `findAllWithBookings` com `JOIN FETCH` sem `DISTINCT` → linhas duplicadas
- [ ] `create()`: verifica disponibilidade e grava sem lock/constraint → dupla reserva sob concorrência (race condition)
- [ ] `cancel()`: `@Transactional(readOnly=true)` mas o método muta dados
- [ ] `BookingRefGenerator` é `@Scope("prototype")` mas injetado num singleton → só é criado uma vez
- [ ] Coluna `start_time`/`end_time` sem timezone
- [ ] Tabela `booking` sem constraint que impeça overlap

## Docker (2)
- [ ] Password embutida na imagem via `ENV`
- [ ] `CMD` em forma shell → sinais não chegam ao processo Java

## Frontend (4)
- [ ] `cancel()` usa `fetch()` nativo em vez de `HttpClient`
- [ ] `combineLatest` nunca emite (Subject sem valor inicial)
- [ ] `removeFirst()` muda o array diretamente (sem imutabilidade/Output)
- [ ] Mistura `ngModel` com `formControlName` no mesmo input

## K8s (4)
- [ ] Postgres como `Deployment` em vez de `StatefulSet`
- [ ] Postgres sem `PersistentVolumeClaim` → perde dados ao reiniciar
- [ ] `Service` do Postgres em `LoadBalancer` → BD exposta à internet
- [ ] `ConfigMap` sem mecanismo de reload → pods não veem alterações

## Features para dominares
**Spring:** Specification/Criteria para pesquisa dinâmica de reservas · `@TransactionalEventListener(AFTER_COMMIT)` + `@Async` para o email de confirmação.
**PostgreSQL:** `EXCLUDE` constraint (`btree_gist`) para impedir overlaps na própria BD.
**Docker:** multi-stage + user não-root + `HEALTHCHECK`.
**Angular:** state imutável (signal/store) em vez de array partilhado; `shareReplay` para cache de disponibilidade.
**K8s:** `StatefulSet` + `PVC` para o Postgres; hash de config no template do Deployment para forçar rolling restart.

Soluções: `answer.md`.
