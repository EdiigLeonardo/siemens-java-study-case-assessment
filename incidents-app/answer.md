# Respostas — Incidents App

## Backend
1. Ciclo DI: quebrar a dependência — `NotificationDispatcher` não deve depender de `CreateIncidentHandler`; usar `ApplicationEventPublisher`/evento em vez de injeção direta.
2. Bean ambíguo: `NotificationDispatcher` deve receber `List<NotificationStrategy>` e escolher por tipo/nome, não um `NotificationStrategy` singular.
3. `@Value`: usar `${notification.sender_name}` (igual ao yml) ou corrigir o yml para `sender-name`.
4. Cascade: `@OneToMany(mappedBy="incident", cascade=CascadeType.ALL, orphanRemoval=true)`.
5. Enum: trocar para `@Enumerated(EnumType.STRING)` (migração de dados existentes à parte).
6. DTO: separar `IncidentRequest` (sem `internalNotes`) de `IncidentResponse` (sem `internalNotes` também, a menos que seja para admin).
7. Exception handler: devolver mensagem genérica ao cliente, logar o `ex` no servidor (`log.error(...)`), nunca `ex.toString()` na resposta.
8. Actuator: `management.endpoints.web.exposure.include: health,info` + Spring Security a proteger o resto.
9. `spring.jpa.open-in-view: false` explícito.

## Frontend
10. `ViewChild`: mover a leitura para `ngAfterViewInit`.
11. `setInterval`: guardar o id e `clearInterval(this.timer)` em `ngOnDestroy` (ou trocar por `interval()` do RxJS + `takeUntilDestroyed()`).
12. `{{ total() }}`: usar uma propriedade calculada guardada em variável, um `computed()`/signal, ou uma pipe pura.
13. Objeto literal no template: mover para uma propriedade da classe (referência estável) e passar essa referência ao filho.
14. Wildcard: adicionar `{ path: '**', component: NotFoundComponent }` no fim das rotas.

## K8s
15. Selector/label: alinhar `app: incidents-backend` nos dois ficheiros.
16. Imagem: usar tag semântica/SHA (`incidents-backend:1.4.2`), nunca `latest` em produção.
17. `resources: { requests: {cpu, memory}, limits: {cpu, memory} }` no container.
18. `securityContext: { runAsNonRoot: true, runAsUser: 1000 }`.
19. `PodDisruptionBudget` com `minAvailable: 2` para os 3 replicas.
20. `terminationGracePeriodSeconds: 30` + `preStop` a esperar o load balancer remover o pod antes de matar o processo.
