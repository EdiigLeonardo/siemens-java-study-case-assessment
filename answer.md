# Respostas — Self-Service Portal

## Java
1. `Ticket.isMoreUrgentThan` usa `priority.ordinal()`. Fix: campo `int weight` explícito no enum, não depender da ordem de declaração.
2. `Ticket.equals()` sem `hashCode()`. Fix: sobrepor `hashCode()` com `Objects.hash(id)`.

## Spring Boot
3. `TicketController`: `@Autowired` em campos. Fix: injeção por construtor (um só, `final`).
4. `UpdateTicketStatusService.validateAndFetch` é `@Transactional` mas chamado via `this.` na mesma classe — proxy do Spring não intercepta. Fix: mover a lógica transacional para outro bean, ou tirar o método privado da mesma classe.
5. `GlobalExceptionHandler.handleGeneric` apanha `Exception` genérico e devolve sempre 500. Fix: tratar `MethodArgumentNotValidException` → 400, deixar o genérico só para erros mesmo inesperados.

## Hexagonal
6. `TicketController` importa `TicketJpaRepository` (adapter out) diretamente. Fix: remover, usar só os use cases (ports in).
7. Endpoint `GET /{id}/raw` devolve `TicketJpaEntity`. Fix: remover ou mapear para DTO.

## REST / DTO
8. `updateStatus` usa `@PostMapping`. Fix: `@PatchMapping` ou `@PutMapping`.
9. `CreateTicketRequest` sem validação. Fix: `@NotBlank` em `title`/`requesterEmail`, `@Email`, adicionar `@Valid` no controller.

## Performance / SQL
10. `ListTicketsService.list()` chama `commentRepository.countByTicketId()` por ticket, dentro de um loop. Fix: query agregada única (`GROUP BY ticket_id`) ou não carregar contagem na listagem.
11. `TicketPersistenceAdapter.findAll` ignora `statusFilter`. Fix: usar `findAllByStatus` / `Specification`.
12. `V2__create_comments_table.sql`: FK `ticket_id` sem índice. Fix: `CREATE INDEX idx_comments_ticket_id ON comments(ticket_id);`

## Segurança
13. `SecurityConfig`: CORS com `allowedOrigins("*")` + `allowCredentials(true)` (combinação inválida/perigosa). Fix: origens explícitas (ex: `http://localhost:4200`).
14. Nenhum endpoint tem `@PreAuthorize` por role. Fix: `@PreAuthorize("hasRole('AGENT')")` em `assign`/`updateStatus`.

## Angular
15. `TicketListComponent`: subscribe sem `ngOnDestroy`. Fix: `takeUntilDestroyed()` ou `Subscription` + `unsubscribe()`.
16. Mesmo subscribe sem callback de erro. Fix: `.subscribe({ next, error })`.
17. `*ngFor` sem `trackBy`. Fix: `trackBy: t => t.id`.
18. `TicketDetailComponent` usa `route.snapshot.paramMap`. Fix: `route.paramMap.subscribe(...)` ou `input()` com `withComponentInputBinding`.
19. `TicketFormComponent`: form sem `Validators`. Fix: `Validators.required`, `Validators.email`.
20. `authInterceptor` não trata 401. Fix: `catchError` + refresh token ou redirect para login.

## Docker / K8s / CI-CD
21. `backend/Dockerfile` single-stage (imagem com Maven + fonte). Fix: multi-stage (build stage com Maven, runtime stage só com JRE + jar).
22. `docker-compose.yml`: password em texto plano + `depends_on` sem healthcheck. Fix: `.env`/secrets + `condition: service_healthy`.
23. `k8s/backend-deployment.yaml`: sem probes. Fix: `readinessProbe`/`livenessProbe` no `/actuator/health`.
24. `k8s/backend-config.yaml`: password num ConfigMap. Fix: mover para `Secret` (`kind: Secret`, base64).
25. `ci-cd.yml`: sem cache Maven, sem testes antes do push. Fix: `actions/cache` para `~/.m2`, job de testes com `needs:` antes do build/push da imagem.
