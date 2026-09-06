# Respostas — Expenses App

## Backend
1. Adicionar `@Retention(RetentionPolicy.RUNTIME)` a `@Auditable`.
2. Remover `final` da classe `ExpenseService`.
3. Adicionar `@Order(1)` no `SecurityAspect` e `@Order(2)` no `AuditAspect` (segurança primeiro).
4. Trocar `double amount` por `BigDecimal amount`.
5. `amount.divide(BigDecimal.valueOf(parts), 2, RoundingMode.HALF_UP)`.
6. `amount.compareTo(BigDecimal.ZERO) == 0`.
7. Configurar um `TaskScheduler` com pool maior (`@Bean ThreadPoolTaskScheduler` com `setPoolSize(5)`), ou mover a tarefa pesada para `@Async`.
8. `ALTER TABLE expense ALTER COLUMN amount TYPE NUMERIC(12,2); ALTER TABLE expense ADD CONSTRAINT chk_amount CHECK (amount > 0);`

## Frontend
9. Remover `retry()` do `create()` (ou só usar em GET); se precisares de resiliência num POST, usa idempotency key.
10. `this.http.get('/api/expenses/report', { responseType: 'blob' })`.
11. `inject(ExpenseService).list().pipe(catchError(() => of([])))` no resolver (ou redirecionar para uma página de erro).
12. Não usar `bypassSecurityTrustHtml` em texto de utilizador — mostrar como texto simples (`{{ e.description }}`) ou sanitizar de verdade no backend.

## K8s
13. Adicionar `resources.requests.cpu`/`memory` (e `limits`) ao container.
14. Trocar `ClusterRoleBinding`+`cluster-admin` por um `Role`/`RoleBinding` com só as permissões necessárias (ou remover, se a app não precisa de falar com a API do Kubernetes).
15. Adicionar `spec.tls` ao Ingress com um `Secret` de certificado (ou `cert-manager` a emitir automaticamente).
