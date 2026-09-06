# Respostas — Booking App

## Backend / Postgres
1. Trocar `@EventListener` por `@TransactionalEventListener(phase = AFTER_COMMIT)` + `@Async` no listener.
2. Usar bind parameter: `WHERE requester_email = :email` (nunca concatenar).
3. Adicionar `DISTINCT`: `SELECT DISTINCT e FROM Equipment e JOIN FETCH e.bookings`.
4. Usar lock pessimista (`@Lock(PESSIMISTIC_WRITE)`) na leitura, ou (melhor) constraint `EXCLUDE` na BD (ver feature Postgres) para a BD recusar o overlap.
5. Tirar `readOnly = true` do `cancel()`.
6. `BookingRefGenerator`: injetar via `ObjectProvider<BookingRefGenerator>` e chamar `.getObject()` a cada uso, ou usar `@Lookup`.
7. Coluna: `TIMESTAMP WITH TIME ZONE` (`timestamptz`).
8. Constraint: `EXCLUDE USING gist (equipment_id WITH =, tsrange(start_time,end_time) WITH &&)` (extensão `btree_gist`).

## Docker
9. Nunca `ENV` com segredos — passar em runtime (`--env-file`, secret do orquestrador).
10. `CMD ["java", "-jar", "/app.jar"]` (forma exec).

## Frontend
11. Trocar `fetch()` por `this.http.delete(...)`.
12. `search$ = new Subject<string>()` → `startWith('')`, ou usar `BehaviorSubject('')`.
13. `removeFirst()`: `this.bookings = this.bookings.filter((_, i) => i !== 0)` (novo array, nunca mutar o existente).
14. Escolher uma API: só `formControlName` (remover `[(ngModel)]` e `FormsModule`).

## K8s
15. `kind: StatefulSet` com `serviceName` e `volumeClaimTemplates`.
16. Adicionar `PersistentVolumeClaim` + `volumeMounts` para `/var/lib/postgresql/data`.
17. `type: ClusterIP` (o backend acede via nome do Service dentro do cluster).
18. Adicionar annotation com hash do `ConfigMap` no `template.metadata.annotations` do Deployment (ou usar Kustomize `configMapGenerator`, que já faz isto automaticamente).
