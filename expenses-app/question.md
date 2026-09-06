# Guia — Expenses App (ronda 5)

Padrão: AOP (`@Aspect`) para auditoria + fundamentos de dinheiro (`BigDecimal`). Domínio: despesas.

## Backend (8)
- [ ] `@Auditable` sem `@Retention(RUNTIME)` → aspecto nunca dispara
- [ ] `ExpenseService` é `final` → sem proxy CGLIB → nenhum aspecto/`@Transactional` aplica
- [ ] `AuditAspect`/`SecurityAspect` sem `@Order` → ordem de execução não garantida
- [ ] `Expense.amount` é `double` → erros de arredondamento
- [ ] `TaxCalculator.splitEvenly` sem `RoundingMode` → `ArithmeticException` em divisões não exatas
- [ ] `TaxCalculator.isZero` usa `.equals()` em vez de `.compareTo()` no `BigDecimal`
- [ ] `ReportScheduler`: tarefa de 30s bloqueia o `heartbeat` (pool do scheduler = 1 thread)
- [ ] Coluna `amount` sem `CHECK (amount > 0)` na BD

## Frontend (5)
- [ ] `create()`: `retry()` num POST não idempotente → despesas duplicadas
- [ ] `downloadReport()`: sem `responseType: 'blob'` → PDF corrompido
- [ ] `expenseListResolver`: sem `catchError` → navegação presa se a API falhar
- [ ] `trustDescription()`: `bypassSecurityTrustHtml` em texto do utilizador → XSS
- [ ] (revê o resto do componente à procura de mais 1 problema)

## K8s (3)
- [ ] Deployment sem `resources.requests` → o HPA nunca calcula % CPU, nunca escala
- [ ] `ClusterRoleBinding` a `cluster-admin` para a app → RBAC demasiado permissivo
- [ ] `Ingress` sem `tls` → portal só em HTTP

## Features para dominares
**Spring:** `@Order` nos aspectos + um 3º aspecto de auditoria em BD; `TaskScheduler` dedicado (pool maior) para os `@Scheduled`.
**PostgreSQL:** migrar `amount` para `NUMERIC(12,2)` + `CHECK`.
**Docker:** `HEALTHCHECK` no Dockerfile do backend.
**Angular:** interceptor de erros com `retry()` só em GETs idempotentes; pipe de moeda customizada testada com valores de borda.
**K8s:** `Role`/`RoleBinding` com permissões mínimas em vez de `ClusterRole: cluster-admin`; `cert-manager` + TLS no Ingress.

Soluções: `answer.md`.
