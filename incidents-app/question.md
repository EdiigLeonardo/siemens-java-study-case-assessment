# Guia — Incidents App (ronda 3, game changers)

Padrões: CQRS-lite (command handlers + query direta) + Strategy (notificações). Foco: bugs de alto impacto, comuns em produção.

## Backend (9)
- [ ] DI circular: `NotificationDispatcher` ↔ `CreateIncidentHandler`
- [ ] Bean ambíguo: injeção de `NotificationStrategy` único em vez de `List<NotificationStrategy>`
- [ ] `@Value` aponta para chave errada no yml
- [ ] `@OneToMany` sem cascade → comentários nunca gravados
- [ ] `@Enumerated(ORDINAL)` no status → bomba-relógio se reordenares o enum
- [ ] `IncidentDto` serve pedido e resposta → `internalNotes` vaza para o cliente
- [ ] `GlobalExceptionHandler` devolve `ex.toString()` ao cliente → vaza stacktrace/detalhes internos
- [ ] Actuator `include: "*"` sem segurança → `/actuator/env`, `/heapdump` públicos
- [ ] `open-in-view` no default (true) → esconde N+1, prende conexões

## Frontend (6)
- [ ] `@ViewChild` lido em `ngOnInit` (cedo demais)
- [ ] `setInterval` nativo sem `clearInterval` no `ngOnDestroy`
- [ ] Função chamada no template (`{{ total() }}`) → corre todo CD
- [ ] Objeto literal criado no template → quebra otimização de `OnPush`
- [ ] Sem rota wildcard `**`
- [ ] (revê o resto do componente à procura de mais 1 problema de arquitetura)

## K8s (6)
- [ ] Label do Pod não bate com o selector do Service → 0 endpoints
- [ ] `image: ...:latest`
- [ ] Sem `resources.requests/limits`
- [ ] Sem `securityContext` (container corre como root)
- [ ] Sem `PodDisruptionBudget`
- [ ] Sem `terminationGracePeriodSeconds`/`preStop` (rollout derruba pedidos em curso)

## Features para dominares (não são bugs — implementa do zero)
**Spring:** Specification/Criteria para filtro dinâmico · `@Cacheable` + eviction na query · `@Version` + `@Retryable` (optimistic lock).
**Angular:** `Resolver` para pré-carregar incidente antes da rota ativar · virtual scroll (CDK) na lista · loading global via interceptor + signal.
**K8s:** HPA por CPU no deployment · `NetworkPolicy` restringindo acesso à BD só ao backend.

Soluções: `answer.md`.
