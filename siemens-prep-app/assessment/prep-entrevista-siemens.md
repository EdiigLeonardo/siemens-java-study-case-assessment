# Preparação Exaustiva — Entrevista Siemens (7 set, 16h00, Teams)

Projeto: self-service portals / aplicações web — Angular (front-end), .NET ou Java (backend), REST APIs a partir de Swagger, Kubernetes/CI-CD, PostgreSQL/EF, Windows Server.

**Como usar:** Não tens as respostas aqui de propósito — o objetivo é testares-te a ti mesmo primeiro. Depois de tentares responder (mentalmente ou por escrito), traz-me as tuas respostas na conversa e eu corrijo, aprofundo e aponto lacunas. Sugestão: 1 secção por dia, começando pelo Beginner para consolidar bases antes do Avançado.

---

## NÍVEL BEGINNER (50)

### Angular / Frontend
1. O que é um componente Angular e quais são as suas partes principais (template, classe, decorator)?
2. Para que serve o decorator `@Input()` e `@Output()`?
3. Qual a diferença entre `*ngIf` e `*ngFor`?
4. O que é data binding e quantos tipos existem em Angular (interpolation, property, event, two-way)?
5. O que é um módulo Angular (`NgModule`) e para que serve?
6. Como se cria um novo componente usando o Angular CLI?

### TypeScript
7. Qual a diferença entre `interface` e `type` em TypeScript?
8. O que significa tipagem estática e porque é útil?
9. O que é o operador de "optional chaining" (`?.`) e quando o usarias?

### RxJS
10. O que é um Observable e em que difere de uma Promise?
11. Para que serve o método `subscribe()`?
12. O que é o `async` pipe no template Angular e que problema resolve?

### Backend .NET / C#
13. O que é o Entity Framework e para que serve?
14. Qual a diferença entre uma Web API Controller e uma classe normal em .NET?
15. O que é injeção de dependências (Dependency Injection) e porque é usada em .NET?
16. O que é um DTO e porque se usa em vez de expor entidades diretamente?
17. O que faz o atributo `[ApiController]` numa Web API .NET?

### Backend Java / Spring
18. O que é o Spring Boot e que problema resolve em relação ao Spring tradicional?
19. Para que serve a anotação `@RestController`?
20. Qual a diferença entre `@Component`, `@Service` e `@Repository`?
21. O que é o Spring Data JPA e como simplifica o acesso a dados?
22. O que é um bean no contexto do Spring?

### REST API & Swagger
23. O que significa REST e quais são os principais métodos HTTP (GET, POST, PUT, DELETE)?
24. Qual a diferença entre PUT e PATCH?
25. O que é o Swagger/OpenAPI e para que serve?
26. O que é um código de status HTTP 404? E um 500?
27. O que é um endpoint numa API?

### Git
28. Qual a diferença entre `git pull` e `git fetch`?
29. Para que serve o comando `git branch`?
30. O que é um merge conflict e quando ocorre?
31. O que faz o comando `git clone`?

### Kubernetes & Docker
32. O que é um container e em que difere de uma máquina virtual?
33. O que é uma imagem Docker?
34. O que é um Pod em Kubernetes?
35. Para que serve um ficheiro Dockerfile?
36. O que é um Deployment em Kubernetes?

### CI/CD (GitHub Actions)
37. O que significa CI/CD?
38. O que é um workflow no GitHub Actions?
39. Para que serve um ficheiro YAML num pipeline de CI/CD?
40. O que é um "runner" no GitHub Actions?

### PostgreSQL & EF Core
41. O que é uma chave primária (primary key) e uma chave estrangeira (foreign key)?
42. O que faz o comando `SELECT * FROM tabela`?
43. O que é uma migration em Entity Framework?
44. Qual a diferença entre `INNER JOIN` e `LEFT JOIN`?

### Windows Server
45. O que é o Windows Server e para que é normalmente usado numa empresa?
46. O que é o Active Directory, a um nível básico?
47. O que é o IIS (Internet Information Services)?

### Inglês / Soft Skills
48. How would you briefly introduce yourself in English for this interview (30 seconds)?
49. How would you say, naturally, "I have experience maintaining legacy codebases" as part of your career story?
50. What's a simple way to answer "Why do you want to work on this project?" in English?

---

## NÍVEL INTERMÉDIO (50)

### Angular / Frontend
1. Qual a diferença entre Reactive Forms e Template-driven Forms? Quando escolherias cada uma?
2. Como implementarias um Guard para proteger uma rota em Angular?
3. O que é change detection no Angular e como funciona por defeito?
4. O que são standalone components (Angular 17+) e que problema resolvem?
5. Como partilharias dados entre dois componentes irmãos (sem relação direta pai-filho)?
6. O que é lazy loading de módulos/rotas e porque é importante para performance?

### TypeScript
7. O que são generics em TypeScript e dá um exemplo de uso prático.
8. Como usarias union types e type narrowing num cenário real?
9. Explica a diferença entre `readonly` e `const` em TypeScript.

### RxJS
10. Explica a diferença entre `switchMap`, `mergeMap` e `concatMap`.
11. Como cancelarias uma subscription para evitar memory leaks?
12. O que é `combineLatest` e quando o usarias num formulário reativo?

### Backend .NET / C#
13. Explica os três "lifetimes" de injeção de dependências em .NET (Transient, Scoped, Singleton).
14. Como implementarias tratamento global de exceções numa Web API .NET?
15. O que é async/await em C# e porque é importante em operações de I/O?
16. Como farias versionamento de uma API REST em .NET?
17. O que é o padrão Repository e porque o usarias com Entity Framework?

### Backend Java / Spring
18. Explica a diferença entre `@Autowired` por construtor vs por campo. Qual preferes e porquê?
19. Como implementarias tratamento global de exceções numa API Spring Boot (`@ControllerAdvice`)?
20. O que é o Spring Security e como protegerias um endpoint?
21. Explica a diferença entre `@Transactional` em métodos de serviço vs repositório.
22. O que é o padrão DTO + Mapper e porque evitarias expor entidades JPA diretamente?

### REST API & Swagger
23. Como desenhas uma API RESTful a partir de uma especificação Swagger (contract-first vs code-first)?
24. Como documentarias parâmetros de query e respostas de erro no Swagger/OpenAPI?
25. O que é HATEOAS e é relevante numa API REST pragmática?
26. Como farias paginação numa API REST (query params, headers)?
27. Como lidarias com versionamento de uma API já em produção sem quebrar clientes existentes?

### Git
28. Qual a diferença entre `rebase` e `merge`? Quando usarias cada um?
29. Como resolverias um merge conflict complexo em vários ficheiros?
30. O que é `git cherry-pick` e quando o usarias?
31. Explica uma estratégia de branching (ex: Git Flow ou trunk-based) que já tenhas usado.

### Kubernetes & Docker
32. O que é um Service em Kubernetes e que tipos existem (ClusterIP, NodePort, LoadBalancer)?
33. Como farias deploy de uma aplicação Angular num container Docker (multi-stage build)?
34. O que é um ConfigMap e um Secret em Kubernetes?
35. Como escalarias horizontalmente uma aplicação em Kubernetes?
36. O que é um readiness probe e um liveness probe?

### CI/CD (GitHub Actions)
37. Como estruturarias um pipeline GitHub Actions com etapas de build, test e deploy?
38. O que são secrets no GitHub Actions e como os usarias em segurança?
39. Como farias deploy automático de uma imagem Docker para um registry a partir de um pipeline?
40. O que é um matrix build no GitHub Actions e para que serve?

### PostgreSQL & EF Core
41. Como criarias e aplicarias uma migration em EF Core (`Add-Migration`, `Update-Database`)?
42. O que é um índice numa base de dados e quando o criarias?
43. Explica a diferença entre uma transação e uma operação isolada numa base de dados.
44. Como lidarias com uma migration que falha em produção?

### Windows Server
45. Como configurarias um site no IIS para servir uma aplicação Angular?
46. O que é uma Group Policy no contexto do Active Directory?
47. Como diagnosticarias um serviço que não arranca no Windows Server?

### Inglês / Soft Skills
48. Describe a bug you fixed recently, in English, using past tense correctly.
49. How would you explain a technical trade-off to a non-technical client in English?
50. How would you respond in English if a client asked "What would you improve about the current system?"

---

## NÍVEL AVANÇADO (50)

### Angular / Frontend
1. Como otimizarias performance de uma aplicação Angular grande com centenas de componentes (`OnPush`, `trackBy`, detach)?
2. Como implementarias um interceptor HTTP para refresh automático de token JWT?
3. Como estruturarias uma arquitetura de state management (NgRx ou signals) numa aplicação enterprise?
4. Como testarias (unit tests) um componente com dependências de serviços assíncronos?
5. Como implementarias micro-frontends com Angular, se necessário?
6. Como lidarias com memory leaks numa aplicação Angular de longa duração (SPA)?

### TypeScript
7. Como usarias mapped types e conditional types para criar tipos avançados reutilizáveis?
8. Como garantirias type-safety end-to-end entre um backend .NET/Java e um frontend Angular (ex: geração automática de tipos a partir do Swagger)?
9. Descreve um cenário onde usarias decorators customizados em TypeScript.

### RxJS
10. Como implementarias debounce + cancelamento de pedidos anteriores numa pesquisa em tempo real (typeahead)?
11. Como combinarias múltiplos streams com diferentes cadências (polling + eventos de utilizador) de forma eficiente?
12. Como testarias Observables complexos com marble testing?

### Backend .NET / C#
13. Como desenharias uma arquitetura em camadas (Clean Architecture) para uma API .NET enterprise?
14. Como implementarias caching distribuído (ex: Redis) numa Web API .NET de alta carga?
15. Como lidarias com concorrência e locking otimista numa operação de atualização em EF Core?
16. Como implementarias autenticação/autorização baseada em JWT + roles numa API .NET?
17. Como diagnosticarias um problema de performance numa API .NET em produção (profiling, logging estruturado)?

### Backend Java / Spring
18. Como desenharias uma arquitetura hexagonal (ports and adapters) numa aplicação Spring Boot?
19. Como implementarias resiliência (circuit breaker, retry) numa integração entre microserviços com Spring?
20. Como lidarias com problemas de N+1 queries numa aplicação Spring Data JPA?
21. Como implementarias testes de integração com Testcontainers numa aplicação Spring Boot?
22. Como configurarias observabilidade (métricas, tracing) numa aplicação Spring Boot em produção?

### REST API & Swagger
23. Como desenharias uma API que serve múltiplos frontends com necessidades diferentes (padrão BFF)?
24. Como garantirias backward compatibility ao evoluir um contrato Swagger já usado por vários consumidores?
25. Como implementarias rate limiting e throttling numa API pública?
26. Como lidarias com idempotência em endpoints POST críticos (ex: pagamentos)?
27. Como validarias automaticamente que a implementação de uma API respeita a especificação Swagger (contract testing)?

### Git
28. Como resolverias um histórico Git corrompido ou um commit acidental de segredos (rewrite history, BFG)?
29. Como configurarias um workflow de code review eficiente com pull requests e branch protection rules?
30. Descreve um cenário onde usarias `git bisect` para encontrar um bug introduzido há várias releases.
31. Como geririas um monorepo com múltiplas aplicações (frontend + backend) no mesmo repositório Git?

### Kubernetes & Docker
32. Como desenharias uma estratégia de deployment sem downtime (rolling update, blue-green, canary) em Kubernetes?
33. Como otimizarias uma imagem Docker para reduzir tamanho e superfície de ataque (multi-stage, distroless)?
34. Como configurarias autoscaling (HPA) baseado em métricas customizadas em Kubernetes?
35. Como debugarias um Pod em CrashLoopBackOff em produção?
36. Como implementarias gestão segura de segredos em Kubernetes (Sealed Secrets, Vault)?

### CI/CD (GitHub Actions)
37. Como implementarias um pipeline com aprovações manuais antes de deploy para produção?
38. Como estruturarias reusable workflows/composite actions para partilhar lógica entre vários repositórios?
39. Como implementarias rollback automático se um deploy falhar num health check pós-deployment?
40. Como otimizarias tempos de build num pipeline CI/CD com muitos testes (cache, paralelização)?

### PostgreSQL & EF Core
41. Como analisarias e otimizarias uma query lenta usando `EXPLAIN ANALYZE` no PostgreSQL?
42. Como desenharias uma estratégia de migrations para uma base de dados em produção com zero downtime?
43. Como lidarias com um schema com milhões de registos que precisa de uma alteração estrutural (ex: mudar tipo de coluna)?
44. Como configurarias replicação ou particionamento numa base de dados PostgreSQL para alta disponibilidade?

### Windows Server
45. Como configurarias alta disponibilidade para uma aplicação hospedada em IIS/Windows Server?
46. Como automatizarias tarefas administrativas no Windows Server usando PowerShell?
47. Como diagnosticarias e resolverias um problema de permissões de Active Directory que impede um serviço de aceder a um recurso de rede?

### Inglês / Soft Skills
48. How would you handle, in English, a disagreement with a client about a technical approach during a meeting?
49. How would you explain, in English, a complex architectural decision (e.g., choosing Kubernetes over a simpler deployment) to a stakeholder?
50. How would you negotiate scope or deadline concerns with a client in English, while keeping a positive, empathetic tone?
