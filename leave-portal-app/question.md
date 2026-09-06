# Guia — Leave Portal (prep Siemens, ronda 2)

Stack: Java 21 + Spring Boot + Lombok + Angular 17 standalone. Estrutura MVP (controller/service/repository/model), sem hexagonal desta vez. Sem Docker/K8s.

App: pedidos de férias (criar, listar, aprovar/rejeitar).

## Backend — 15 problemas
- [ ] Lombok: `@Data` em entidade com relação bidirecional → risco de `StackOverflowError` (toString/equals)
- [ ] Lombok: `@Builder` sem `@NoArgsConstructor` → Hibernate sem construtor vazio
- [ ] JPA: falta `@Version` (optimistic locking) em `LeaveRequest`
- [ ] JPQL: `findByEmployeeAndStatus` recebe `status` mas ignora-o na query
- [ ] Paginação: sem limite máximo de `size` configurado
- [ ] N+1: `listWithRequestCount()` carrega lazy collection em loop
- [ ] Exceção: `InsufficientLeaveBalanceException` é checked → não faz rollback num `@Transactional`
- [ ] Exception handler: sem `@ExceptionHandler` para essa exceção
- [ ] Transação: `decide()` sem `@Transactional` faz dois saves não-atómicos
- [ ] Lógica de negócio: `create()` debita saldo ao criar (antes de aprovar)
- [ ] Validação: `@Valid` em falta no `create()` do controller
- [ ] Segurança: `hasRole('MANAGER')` vs authority `"MANAGER"` sem prefixo `ROLE_`
- [ ] DTO: `EmployeeDto` não expõe `role`
- [ ] SQL: falta coluna `version`
- [ ] (bónus) `SecurityConfig` usa password encoder de teste — nunca em produção

## Frontend — 9 problemas
- [ ] Signal: `pendingCount()` mutado por atribuição direta em vez de `.set()`/`.update()`
- [ ] Interceptor: engole todos os erros HTTP com `catchError(() => of(null))`
- [ ] Guard: `unsavedChangesGuard` existe mas não está ligado a nenhuma rota
- [ ] Reactive Forms: sem validador cruzado `endDate >= startDate`
- [ ] Reactive Forms: botão de submeter não desativa durante o pedido → duplo submit
- [ ] RxJS: `| async` no template + `.subscribe()` manual no mesmo observable → pedidos HTTP duplicados
- [ ] RxJS: `switchMap` em vez de `mergeMap` em aprovações → cancela pedidos em curso
- [ ] `LeaveFormComponent` implementa `HasUnsavedChanges` mas ninguém chama (rota sem `canDeactivate`)
- [ ] `LeaveResponseDto`/rota: sem tratamento de estado de loading nos componentes

Soluções: `answer.md`.
