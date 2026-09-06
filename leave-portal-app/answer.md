# Respostas — Leave Portal

## Backend
1. `@Data` em entidade com relação: trocar por `@Getter/@Setter` + `@ToString(exclude="requests")` + `@EqualsAndHashCode(onlyExplicitlyIncluded=true)` marcando só `id`.
2. `@Builder` sem construtor vazio: adicionar `@NoArgsConstructor(access = AccessLevel.PROTECTED)` + `@AllArgsConstructor`.
3. Falta `@Version`: adicionar `@Version private Long version;` na entidade + coluna `version BIGINT` na migration.
4. JPQL ignora `status`: `WHERE l.employee.id = :employeeId AND l.status = :status`.
5. Paginação sem limite: `spring.data.web.pageable.max-page-size` no `application.yml`.
6. N+1: usar `JOIN FETCH` ou uma query de contagem agregada (`GROUP BY employee_id`) em vez de `getRequests().size()` em loop.
7. Exceção checked sem rollback: mudar `InsufficientLeaveBalanceException` para `extends RuntimeException`, ou usar `@Transactional(rollbackFor = InsufficientLeaveBalanceException.class)`.
8. Sem handler: adicionar `@ExceptionHandler(InsufficientLeaveBalanceException.class)` → 400/409.
9. `decide()` sem `@Transactional`: adicionar a anotação ao método.
10. Débito cedo demais: mover o débito do saldo de `create()` para `decide()` (só ao aprovar).
11. Falta `@Valid`: `create(@Valid @RequestBody CreateLeaveRequestDto dto)`.
12. `hasRole` vs authority: usar `.authorities("ROLE_MANAGER")` no `UserDetailsService`, ou trocar para `hasAuthority('MANAGER')` no controller.
13. `EmployeeDto` sem role: adicionar campo `role`.
14. Coluna `version`: `ALTER TABLE leave_requests ADD COLUMN version BIGINT NOT NULL DEFAULT 0;`

## Frontend
15. Signal mutado direto: `this.pendingCount.set(count)`.
16. Interceptor engole erros: `catchError(err => throwError(() => err))` (ou tratar e só engolir casos específicos).
17. Guard não ligado: `{ path: 'leave/new', component: LeaveFormComponent, canDeactivate: [unsavedChangesGuard] }`.
18. Sem validador cruzado: `Validators` custom no `FormGroup` (`group => group.value.endDate >= group.value.startDate ? null : { dateRange: true }`).
19. Sem disable no submit: `submitting = true` antes do `subscribe`, `[disabled]="submitting || form.invalid"`.
20. `async` + `subscribe` duplicado: guardar o resultado uma vez (`shareReplay(1)`) ou só usar `async` no template, sem subscribe manual extra.
21. `switchMap` cancela pedidos: trocar para `mergeMap` (processa cada aprovação até ao fim, sem cancelar a anterior).
22. `HasUnsavedChanges` sem uso: ligar o guard na rota (ver item 17).
