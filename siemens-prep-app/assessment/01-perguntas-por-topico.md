# Perguntas por Tópico — Prep Entrevista Siemens

Stack escolhida: Java + Spring Boot (backend) + Angular (frontend).
Tópicos de senioridade (Java, Spring Boot, Angular): 50 perguntas cada.
Restantes tópicos: menos perguntas, mas sem saltar armadilhas comuns.
Cada tópico tem 3 blocos: **Fundamentos**, **Rasteiras comuns**, **Hipotéticas**.

Sem respostas aqui de propósito — ver `02-respostas-por-topico.md` só depois de tentares.

---

## JAVA (50)

### Fundamentos
1. Diferença entre `==` e `.equals()` em objetos?
2. O que é autoboxing/unboxing?
3. Diferença entre `String`, `StringBuilder` e `StringBuffer`?
4. O que é uma interface funcional?
5. Diferença entre classe abstrata e interface?
6. O que são streams em Java? Exemplo?
7. Diferença entre `List`, `Set` e `Map`?
8. O que é o Garbage Collector, a alto nível?
9. Diferença entre checked e unchecked exceptions?
10. O que é `Optional` e para que serve?
11. Diferença entre `final`, `finally` e `finalize`?
12. O que são generics em Java?
13. Diferença entre `ArrayList` e `LinkedList`?
14. O que é `hashCode()` e porque anda com `equals()`?
15. O que são lambda expressions?
16. Diferença entre `HashMap` e `TreeMap`?
17. O que é method reference (`::`)?
18. Diferença entre `throw` e `throws`?
19. O que é imutabilidade? Como criar uma classe imutável?
20. Diferença entre `static` e instância?

### Rasteiras comuns
21. `String s1="a"; String s2="a"; s1==s2` → true/false? Porquê?
22. Modificar uma `List` dentro de um `forEach` — que erro?
23. `Integer a=127,b=127; a==b` → e com 200?
24. `Collections.unmodifiableList` vs `List.of()`?
25. Porque é perigoso sobrepor `equals()` sem `hashCode()`?
26. `try-with-resources` garante o quê a mais que `try/finally`?
27. Overload vs override — compile-time ou runtime?
28. O que acontece a uma exceção lançada dentro de `finally`?
29. Um Stream pode ser reutilizado após operação terminal?
30. `synchronized` vs `ReentrantLock`?
31. Porque `int/0` dá exceção mas `double/0` dá `Infinity`?
32. `Double.NaN == Double.NaN` → resultado?
33. Métodos static podem ser overridden?
34. Java passa por valor ou referência (objetos)?
35. O que é "effectively final" e porque as lambdas exigem isso?
36. Que problema traz `catch (Exception e)` genérico?
37. `Comparable` vs `Comparator`?
38. O que acontece a uma thread que faz `wait()` sem `synchronized`?
39. Porque acontece `ConcurrentModificationException`?
40. Deep copy vs shallow copy?

### Hipotéticas
41. Como desenhar um contador thread-safe partilhado?
42. Como refatorar um método com 5 `if` aninhados?
43. Como implementar um cache em memória com TTL?
44. 1M de objetos: filtrar e agrupar por categoria — abordagem com Streams?
45. Como investigar um `OutOfMemoryError` intermitente em produção?
46. Como implementar retry com backoff exponencial numa chamada externa?
47. Como implementar o padrão Builder?
48. Como testar unitariamente código que usa `LocalDateTime.now()`?
49. `record` vs classe normal para um DTO — quando cada um?
50. Como implementar um observer pattern simples em Java puro?

---

## SPRING BOOT (50)

### Fundamentos
1. O que é auto-configuration?
2. `@Component` vs `@Service` vs `@Repository` vs `@Controller`?
3. O que faz `@SpringBootApplication`?
4. `@RestController` vs `@Controller`?
5. Para que serve `application.properties`/`.yml`?
6. O que é injeção de dependências no Spring?
7. `@Autowired` no construtor vs no campo — qual preferes?
8. O que é um `@Bean`?
9. `@RequestParam` vs `@PathVariable` vs `@RequestBody`?
10. O que é Spring Data JPA?
11. `@Entity` vs `@Table`?
12. O que faz `@Transactional`?
13. `@GetMapping` vs `@RequestMapping(method=GET)`?
14. Para que serve `@ControllerAdvice`?
15. `@Valid` vs `@Validated`?
16. O que são profiles no Spring Boot?
17. Como se integra Swagger/OpenAPI num Spring Boot?
18. `ResponseEntity<T>` vs devolver o objeto direto?
19. O que faz `@ExceptionHandler`?
20. O que é o Actuator?

### Rasteiras comuns
21. Porque injeção por campo é má prática?
22. Dois beans do mesmo tipo sem `@Qualifier` — o que acontece?
23. Bean singleton vs prototype?
24. Porque `@Transactional` numa chamada interna à mesma classe não funciona?
25. O que é o problema N+1 e como identificá-lo?
26. `@Autowired` num campo `final` — dá erro?
27. `save()` vs `saveAndFlush()`?
28. Porque devolver a entidade JPA direto na API é anti-padrão?
29. Exceção dentro de um método `@Async` — o que acontece?
30. `@RequestBody` sem `required=false` e sem corpo — que erro?
31. Porque um endpoint pode dar 403 mesmo com `@PreAuthorize` "certo"?
32. O que é lazy loading no JPA e que exceção comum causa?
33. `CrudRepository` vs `JpaRepository` vs `PagingAndSortingRepository`?
34. Porque é má prática lógica de negócio no Controller?
35. Dois endpoints com o mesmo `@RequestMapping` — o que acontece?
36. `@Valid` no DTO vs constraints na BD — papel de cada um?
37. Porque `@Transactional(readOnly=true)` pode melhorar performance?
38. O que é CORS e como configurar num Spring Boot API?
39. 401 vs 403 — sabes distinguir na prática?
40. Porque `@SpringBootTest` é mais lento que um teste unitário simples?

### Hipotéticas
41. Como desenhar endpoints REST + Swagger para um recurso "Pedido" com CRUD?
42. Como implementar paginação e ordenação numa listagem?
43. Como padronizar o formato de erro de validação em toda a API?
44. Como restringir um endpoint só a role "ADMIN"?
45. REST síncrono vs mensageria entre dois microserviços — como decidir?
46. Como migrar gradualmente um monólito para microserviços?
47. Como tornar um endpoint idempotente?
48. Como configurar logging estruturado (JSON) para debug em produção?
49. Como desenhar testes de contrato backend↔frontend com base no Swagger?
50. Bug só reproduzível em produção — como investigar?

---

## ANGULAR (50)

### Fundamentos
1. Partes de um componente (template, classe, decorator)?
2. `@Input()` vs `@Output()`?
3. `*ngIf` vs `*ngFor`?
4. O que é data binding e quantos tipos existem?
5. O que é um serviço `@Injectable`?
6. Reactive Forms vs Template-driven Forms?
7. Observable vs Promise?
8. O que faz o `async` pipe?
9. `interface` vs `type` em TypeScript?
10. O que são generics em TypeScript?
11. O que é o Angular Router?
12. O que é um Guard de rota?
13. O que é lazy loading de módulos?
14. `subscribe()` vs `pipe()`?
15. O que são standalone components (Angular 17+)?
16. O que são signals?
17. `ViewChild` vs `ContentChild`?
18. O que é o `HttpClient`?
19. O que é um interceptor HTTP?
20. `constructor` vs `ngOnInit`?

### Rasteiras comuns
21. Porque não cancelar um Observable causa memory leak?
22. `switchMap` vs `mergeMap` vs `concatMap` — qual num typeahead?
23. Porque `*ngFor` sem `trackBy` pode re-renderizar tudo?
24. `ChangeDetectionStrategy.Default` vs `OnPush`?
25. Porque um `array.push()` pode não disparar change detection com `OnPush`?
26. Um serviço injetado em módulos diferentes — instância única ou várias?
27. `providedIn: 'root'` vs provider declarado no módulo?
28. Porque HTTP direto no componente é má prática?
29. O que é "prop drilling" e como evitar?
30. `[(ngModel)]` vs Reactive Forms — onde cada um falha a escalar?
31. Porque um `pipe` pode disparar mais vezes que esperado?
32. O que é zone.js e o que muda com signals?
33. `EventEmitter` vs `Subject`/`BehaviorSubject`?
34. Porque `ngOnChanges` não deteta mutações internas?
35. `unsubscribe()` num Observable já completo — o que acontece?
36. Lazy loading de módulo vs de componente standalone?
37. Porque `any` em TypeScript é desaconselhado?
38. `readonly` vs `const` em TypeScript?
39. O que é um "stale closure" numa subscription em `setTimeout`?
40. Porque testes com `HttpClient` precisam de `HttpClientTestingModule`?

### Hipotéticas
41. Como implementar refresh automático de token via interceptor?
42. Como otimizar uma tabela com milhares de linhas?
43. Como partilhar estado entre componentes não relacionados?
44. Como estruturar um feature module lazy loaded?
45. Como implementar um formulário dinâmico gerado por configuração?
46. Como debugar uma fuga de memória numa SPA de longa duração?
47. Como gerar automaticamente um serviço Angular a partir de um Swagger?
48. Como testar um componente com dependência de serviço assíncrono?
49. Como migrar gradualmente NgModule para standalone sem quebrar a app?
50. Como implementar cache simples para respostas de API no frontend?

---

## GIT (15)

### Fundamentos
1. `git pull` vs `git fetch`?
2. Para que serve `git branch`?
3. O que faz `git clone`?
4. `git add` vs `git commit`?
5. O que é um merge conflict?
6. O que é `origin`?
7. O que faz `git status`?

### Rasteiras comuns
8. `rebase` vs `merge` — o que muda no histórico?
9. O que é `git cherry-pick`?
10. `git revert` vs `git reset` — quando usar cada um?
11. `reset --soft` vs `--mixed` vs `--hard`?
12. Porque force push é perigoso num branch partilhado?
13. `.gitignore` adicionado depois de um ficheiro já commitado — efeito?

### Hipotéticas
14. Fizeste commit de uma password e já deste push — como resolves?
15. Como estruturarias um workflow de branching para uma equipa pequena?

---

## KUBERNETES & DOCKER (15)

### Fundamentos
1. Container vs máquina virtual?
2. Imagem Docker vs Dockerfile?
3. O que é um Pod?
4. O que é um Deployment?
5. O que é um Service?
6. `docker run` vs `docker-compose up`?
7. O que é um registry de imagens?

### Rasteiras comuns
8. Porque "funciona na minha máquina" e falha no cluster?
9. `ClusterIP` vs `NodePort` vs `LoadBalancer`?
10. Readiness probe vs liveness probe?
11. Porque multi-stage build reduz o tamanho da imagem?
12. Dados dentro de um container sem volume — o que acontece ao reiniciar?
13. `ConfigMap` vs `Secret`?

### Hipotéticas
14. Como fazer deploy de uma app Angular num container eficiente?
15. Pod em `CrashLoopBackOff` — como diagnosticas?

---

## CI/CD — GITHUB ACTIONS (15)

### Fundamentos
1. O que significa CI/CD?
2. O que é um workflow?
3. Job vs step?
4. Para que serve `on: push` / `on: pull_request`?
5. O que é um runner?
6. Como definir uma variável de ambiente num workflow?
7. O que é um artifact de workflow?

### Rasteiras comuns
8. `secrets` vs `variables`?
9. Porque um pipeline passa localmente mas falha no CI?
10. O que é caching de dependências num workflow?
11. Como evitar expor um segredo nos logs?
12. `needs:` vs steps sequenciais no mesmo job?
13. O que é um matrix build?

### Hipotéticas
14. Como estruturar build → testes → deploy para staging?
15. Como implementar rollback automático se o deploy falhar?

---

## POSTGRESQL & MIGRATIONS (15)

### Fundamentos
1. Chave primária vs chave estrangeira?
2. `INNER JOIN` vs `LEFT JOIN`?
3. O que é uma migration?
4. O que faz `WHERE` + `GROUP BY`?
5. O que é um índice?
6. `DELETE` vs `TRUNCATE`?
7. O que é uma transação?

### Rasteiras comuns
8. Porque um `LEFT JOIN` pode duplicar linhas?
9. O que é o problema N+1?
10. Porque indexar tudo não é boa ideia?
11. Migration falha a meio em produção — o que acontece?
12. `READ COMMITTED` vs `SERIALIZABLE`, a alto nível?
13. Porque `SELECT *` é desaconselhado?

### Hipotéticas
14. Query lenta em produção — como analisar (EXPLAIN ANALYZE)?
15. Como mudar o tipo de uma coluna sem downtime?

---

## WINDOWS SERVER (10)

### Fundamentos
1. O que é o Windows Server?
2. O que é o Active Directory, a alto nível?
3. O que é o IIS?
4. O que é uma Group Policy?
5. Servidor físico vs VM Windows Server?

### Rasteiras comuns
6. Serviço não arranca por permissões, não por erro de código — comum?
7. Site IIS devolve 503 — o que verificar primeiro?
8. Permissões NTFS vs permissões de partilha?
9. O que é o Event Viewer?

### Hipotéticas
10. Como configurar um site Angular estático no IIS com fallback de rotas?
