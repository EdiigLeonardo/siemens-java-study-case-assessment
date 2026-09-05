# Respostas — Prep Entrevista Siemens

Numeração espelha `01-perguntas-por-topico.md`. Respostas diretas — usa-as para conferir, não para decorar.

---

## JAVA

### Fundamentos
1. `==` compara referências (identidade); `.equals()` compara conteúdo/valor. Para objetos/Strings, usa sempre `.equals()`.
2. Autoboxing converte primitivo→wrapper (`int`→`Integer`) automaticamente; unboxing faz o inverso. Custa performance em massa (loops).
3. `String` é imutável; `StringBuilder` mutável e não thread-safe (rápido); `StringBuffer` mutável e thread-safe (sincronizado, mais lento).
4. Interface com um único método abstrato (ex: `Runnable`, `Comparator`). Permite lambdas.
5. Classe abstrata: estado + métodos concretos, herança única. Interface: sem estado (métodos default à parte), implementação múltipla.
6. API para processar coleções de forma declarativa. Ex: `list.stream().filter(x -> x > 5).collect(toList())`.
7. `List` ordenada com duplicados; `Set` sem duplicados; `Map` pares chave-valor.
8. Processo automático que liberta memória de objetos sem referências, evitando gestão manual.
9. Checked (`IOException`) exigem declaração/tratamento em compile-time; unchecked (`RuntimeException`) não.
10. Wrapper para valor que pode não existir, evitando `null` e `NullPointerException`.
11. `final` impede reatribuição/override; `finally` executa sempre após try/catch; `finalize()` (obsoleto) corre antes do GC recolher o objeto.
12. Tipos parametrizáveis (`List<T>`) com type-safety em compile-time.
13. `ArrayList`: array dinâmico, acesso rápido por índice. `LinkedList`: lista ligada, inserção/remoção rápida nas pontas.
14. Código numérico usado por estruturas hash. Objetos iguais por `equals()` devem ter o mesmo `hashCode()`.
15. Funções anónimas concisas, usadas com interfaces funcionais/Streams. Ex: `x -> x * 2`.
16. `HashMap`: sem ordem, O(1) médio. `TreeMap`: ordenado por chave, O(log n).
17. Sintaxe curta para referenciar um método como lambda. Ex: `String::toUpperCase`.
18. `throw` lança a exceção; `throws` declara na assinatura que o método pode lançá-la.
19. Objeto cujo estado não muda após criação: campos `final`, sem setters, devolve cópias de coleções internas.
20. `static` pertence à classe (partilhado); instância pertence a cada objeto.

### Rasteiras comuns
21. Costuma dar true por interning de literais, mas não é garantido (ex: `new String()`). Nunca usar `==` para Strings.
22. `ConcurrentModificationException` — usa `Iterator.remove()` ou uma cópia.
23. Com 127: true (cache -128 a 127). Com 200: false (fora do cache).
24. `unmodifiableList` só "esconde" mutabilidade (a original pode mudar); `List.of()` é verdadeiramente imutável.
25. Quebra o contrato de `HashMap`/`HashSet` — podes não encontrar um objeto que acabaste de inserir.
26. Fecha automaticamente o recurso `Closeable` mesmo com exceção, sem `finally` manual.
27. Overload resolve em compile-time (assinatura); override em runtime (polimorfismo).
28. A exceção do `finally` "engole" a original do `try`, salvo tratamento explícito.
29. Não — Stream é single-use; após operação terminal lança `IllegalStateException` se reutilizado.
30. `synchronized`: simples, bloqueio automático. `ReentrantLock`: timeout, interrupção, fairness configurável.
31. `int/0` lança `ArithmeticException`; `double/0` segue IEEE 754 (`Infinity`, ou `NaN` para `0.0/0.0`).
32. Sempre false — usa `Double.isNaN()` ou `.equals()`.
33. Não — static é resolvido por tipo declarado (hiding), sem polimorfismo real.
34. Sempre por valor — mas para objetos o "valor" é a referência, por isso parece passagem por referência ao mutar internamente.
35. Lambdas só capturam variáveis locais que não mudam depois de inicializadas (evita inconsistências de fecho).
36. Esconde o tipo real do erro, dificulta tratamento específico, mascara bugs — apanha exceções específicas.
37. `Comparable`: ordenação natural (`compareTo`, dentro da classe). `Comparator`: ordenação externa/custom (`compare`).
38. `IllegalMonitorStateException` — `wait()` só dentro de bloco sincronizado no mesmo monitor.
39. Modificas estruturalmente a coleção enquanto a percorres com um iterator que não suporta essa alteração concorrente.
40. Shallow copy: copia só referências (objetos internos partilhados). Deep copy: cópias independentes de tudo, incluindo aninhados.

### Hipotéticas
41. `AtomicInteger`, ou sincronizar o incremento com `synchronized`/`ReentrantLock`, evitando race conditions.
42. Extrair condições em métodos com nomes claros, early return/guard clauses, ou polimorfismo/strategy pattern.
43. `Map<K, CacheEntry<V>>` com valor + timestamp de expiração; validar no `get()`, ou usar Caffeine/Guava.
44. `stream().collect(groupingBy(Item::getCategory))`; considerar `.parallelStream()` se o volume justificar.
45. Heap dumps, procurar leaks (coleções sem limpeza, listeners não removidos), rever config JVM, profiling (VisualVM).
46. Retry com espera crescente (1s, 2s, 4s...) e limite de tentativas, idealmente via Resilience4j.
47. Métodos encadeados que devolvem `this`, terminando em `.build()` que valida e cria o objeto imutável.
48. Injetar um `Clock` em vez de chamar `LocalDateTime.now()` direto, permitindo mockar tempo nos testes.
49. `record`: DTO imutável, só dados (equals/hashCode/toString grátis). Classe normal: lógica extra ou mutabilidade.
50. Interface `Listener` com `onEvent()`; o "subject" mantém lista de listeners e notifica-os.

---

## SPRING BOOT

### Fundamentos
1. Configura beans automaticamente com base no classpath (ex: `spring-boot-starter-web` → servidor embutido).
2. `@Component`: genérico. `@Service`: lógica de negócio. `@Repository`: acesso a dados (traduz exceções de BD). `@Controller`: camada web.
3. Combina `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan` — ponto de entrada da app.
4. `@RestController` = `@Controller` + `@ResponseBody` (JSON direto); `@Controller` sozinho devolve normalmente uma view.
5. Ficheiro de configuração da app (portas, ligação a BD, variáveis por ambiente).
6. Dependências fornecidas de fora pelo container Spring, em vez da classe as criar — reduz acoplamento.
7. Construtor é preferido: permite `final`, facilita testes sem container, evita dependências circulares escondidas.
8. Método `@Bean` numa classe `@Configuration` cujo retorno é registado como bean gerido.
9. `@RequestParam`: query string. `@PathVariable`: parte da URL. `@RequestBody`: corpo JSON.
10. Abstração sobre JPA que gera repositórios automaticamente a partir de interfaces (ex: `findByEmail`).
11. `@Entity` marca a classe como mapeada; `@Table` define o nome da tabela (opcional).
12. Garante que um conjunto de operações na BD é atómico — tudo ou nada.
13. Equivalentes — `@GetMapping` é atalho de `@RequestMapping(method=GET)`.
14. Centraliza tratamento de exceções para todos os controllers, evitando try/catch repetido.
15. `@Valid`: Bean Validation padrão (JSR-380). `@Validated`: do Spring, suporta grupos de validação.
16. Ativa configurações/beans diferentes por ambiente (`application-{profile}.yml`).
17. Biblioteca (ex: springdoc-openapi) gera documentação OpenAPI automaticamente a partir das anotações dos controllers.
18. `ResponseEntity<T>`: controlo total de status/headers/corpo. Objeto direto: assume sempre 200 OK.
19. Trata um tipo específico de exceção lançada num controller.
20. Endpoints de monitorização (health, metrics, info) prontos a usar — úteis para observabilidade.

### Rasteiras comuns
21. Dificulta testes (sem injetar mocks facilmente), esconde dependências, impede campos `final`.
22. `NoUniqueBeanDefinitionException` — precisa de `@Qualifier` ou `@Primary`.
23. Singleton (default): uma instância partilhada. Prototype: nova instância a cada injeção/pedido.
24. `@Transactional` é implementado via proxy — chamadas internas (`this.metodo()`) não passam pelo proxy, transação ignorada.
25. Carregar N entidades gera 1 query extra por entidade para relações lazy — identifica-se nos logs SQL ou stats do Hibernate.
26. Sim, erro de compilação — campos `final` têm de ser inicializados no construtor.
27. `save()`: pode adiar sincronização com a BD. `saveAndFlush()`: força escrita imediata.
28. Acopla a API à estrutura interna, expõe campos sensíveis, dificulta evolução — usa DTOs.
29. Por default é "engolida" silenciosamente — precisa de `AsyncUncaughtExceptionHandler`.
30. 400 Bad Request se faltar/for inválido, sem `required=false`.
31. Autenticação (quem és) e autorização (o que podes) são passos diferentes — podes estar autenticado sem a role certa.
32. Carregamento diferido de relações; fora de sessão/transação aberta → `LazyInitializationException`.
33. `CrudRepository`: CRUD básico. `JpaRepository`: adiciona batch/flush. `PagingAndSortingRepository`: paginação/ordenação.
34. Mistura responsabilidades, dificulta testes/reutilização — lógica de negócio pertence à camada de serviço.
35. `AmbiguousMappingException` no arranque.
36. `@Valid`: valida forma dos dados antes de tocar na BD. Constraints de BD: última linha de defesa (integridade).
37. Evita overhead de gestão de transação de escrita, pode otimizar acesso a dados.
38. Restrição do browser a pedidos entre origens diferentes; configura-se com `@CrossOrigin` ou `CorsConfigurationSource` global.
39. 401: não autenticado. 403: autenticado mas sem permissão.
40. Sobem o contexto Spring completo (todos os beans), ao contrário de um teste unitário isolado com mocks.

### Hipotéticas
41. `GET/POST/PUT/DELETE /pedidos`, DTOs entrada/saída, anotações Swagger (`@Operation`, `@ApiResponse`), `@Valid`.
42. `Pageable` do Spring Data (`Page<T> findAll(Pageable)`), via query params (`page`, `size`, `sort`).
43. `@ControllerAdvice` global que captura `MethodArgumentNotValidException` e devolve erro padronizado (código, mensagem, campo).
44. `@PreAuthorize("hasRole('ADMIN')")` combinado com Spring Security e filtro de autenticação (JWT).
45. REST síncrono para pedido-resposta imediato; mensageria (Kafka/RabbitMQ) para desacoplamento/assíncrono.
46. Strangler pattern: extrair um módulo de cada vez, começando pelos menos acoplados, coexistindo via API/eventos.
47. Chave de idempotência enviada pelo cliente, guardada e verificada antes de reprocessar.
48. Logback/SLF4J com encoder JSON (timestamp, nível, trace ID), integrado com stack de logs (ELK/Loki).
49. Gerar cliente TypeScript a partir do OpenAPI (openapi-generator) + testes de contrato (Pact).
50. Reproduzir em staging com dados/config semelhantes, rever logs estruturados, comparar diferenças de configuração/versão.

---

## ANGULAR

### Fundamentos
1. Template (HTML), classe (lógica/estado), decorator `@Component` (selector, template, styles).
2. `@Input()`: recebe dados do pai. `@Output()`: emite eventos ao pai (`EventEmitter`).
3. `*ngIf`: mostra/esconde condicionalmente. `*ngFor`: itera coleção.
4. Sincroniza dados template↔classe: interpolation `{{}}`, property `[prop]`, event `(event)`, two-way `[(ngModel)]`.
5. Classe `@Injectable` com lógica reutilizável (ex: HTTP), partilhada via injeção de dependências.
6. Reactive Forms: definidos na classe, testáveis, escaláveis. Template-driven: no HTML com `ngModel`, simples.
7. Observable: lazy, múltiplos valores, cancelável. Promise: eager, resolve uma vez.
8. Subscreve automaticamente um Observable no template e cancela ao destruir o componente.
9. `interface`: forma de tipos, suporta declaration merging. `type`: mais flexível (unions, intersections, primitivos).
10. Tipos parametrizáveis reutilizáveis (`Repository<T>`), mantendo type-safety.
11. Mapeia URLs a componentes; define-se num array de rotas.
12. Classe que decide se uma rota pode ser ativada/desativada (ex: autenticação).
13. Carrega módulo/rota só quando necessário — reduz bundle inicial.
14. `subscribe()`: executa e reage aos valores. `pipe()`: compõe operadores antes de subscrever.
15. Componentes que declaram as próprias dependências, sem `NgModule`.
16. Primitiva reativa (Angular 17+) que notifica dependências ao mudar, sem depender de zone.js.
17. `ViewChild`: elemento/componente filho no próprio template. `ContentChild`: conteúdo projetado (`<ng-content>`).
18. Serviço para pedidos HTTP: `http.get<T>(url)`, `http.post(url, body)`.
19. Intercepta pedidos/respostas HTTP para lógica transversal (token, erros globais).
20. `constructor`: injeção de dependências. `ngOnInit`: corre depois dos `@Input()` disponíveis — lógica de inicialização.

### Rasteiras comuns
21. Subscription fica ativa após o componente ser destruído, mantendo referências/recursos indefinidamente.
22. `switchMap` cancela o pedido anterior ao chegar novo valor — ideal para typeahead.
23. Angular compara por referência do array; sem `trackBy` recria todo o DOM mesmo com 1 item alterado.
24. `Default`: verifica tudo em cada ciclo. `OnPush`: só re-renderiza se referência do `@Input()` mudar ou evento disparar.
25. Com `OnPush`, só deteta mudança de referência — `push()` não muda a referência, view não atualiza.
26. Depende do `providedIn` — `'root'` é sempre singleton; provider em módulos diferentes pode criar instâncias separadas.
27. `providedIn: 'root'`: singleton a nível de app, tree-shakeable. Provider no módulo: instância por módulo (se lazy loaded).
28. Mistura responsabilidades, dificulta testes/reutilização — HTTP deve estar isolado num serviço.
29. Passar dados por vários componentes intermédios que não os usam — evita-se com serviços partilhados ou state management.
30. `[(ngModel)]` escala mal com validação complexa; Reactive Forms lida melhor com formulários dinâmicos/testáveis.
31. O Observable de origem pode emitir mais de uma vez (eventos, retry) — cada pipe processa cada emissão.
32. Lib que detetava automaticamente quando correr change detection; signals reduzem essa dependência.
33. `EventEmitter`: output de filho para pai. `Subject`/`BehaviorSubject`: mais gerais, usados em serviços.
34. `ngOnChanges` compara referências — mutar o objeto internamente (sem trocar referência) não é detetado.
35. Nada acontece — chamar `unsubscribe()` num Observable já completo é seguro e sem efeito.
36. Módulo: carrega tudo o que está nesse módulo. Standalone: carrega só o componente específico (mais granular).
37. Desativa a verificação de tipos, perde os benefícios do TS, esconde erros até ao runtime.
38. `readonly` (TS): propriedade de objeto/classe, reatribuível no construtor. `const` (JS): impede reatribuição da variável.
39. Função dentro de `setTimeout`/callback que captura uma versão antiga já desatualizada de uma variável.
40. Simula `HttpClient` sem pedidos reais à rede, permitindo controlar/verificar respostas nos testes.

### Hipotéticas
41. `HttpInterceptor` que deteta 401, chama refresh, atualiza token e repete o pedido original.
42. `trackBy`, `OnPush`, virtual scrolling (CDK), paginação/lazy loading dos dados.
43. Serviço partilhado com `BehaviorSubject`/signal em `providedIn: 'root'`, ou NgRx para casos complexos.
44. Módulo com rotas/componentes/serviços próprios, importado via `loadChildren` no router principal.
45. Campos gerados a partir de config (tipo, label, validações), usando `FormArray`/`FormGroup` em runtime.
46. Chrome DevTools (heap snapshots), procurar subscriptions/listeners não removidos, rever `OnPush`/`trackBy`.
47. `openapi-generator-cli` para gerar serviços/modelos TypeScript a partir do Swagger do backend.
48. `TestBed`, mockar o serviço com `Observable`/`of()` controlado (spies), verificar comportamento pós-emissão.
49. Migrar componente a componente — Angular suporta `NgModule` e standalone em simultâneo — testando cada passo.
50. Serviço com `Map` (endpoint → resposta + timestamp), verificando expiração antes de nova chamada HTTP.

---

## GIT

### Fundamentos
1. `pull` = fetch + merge automático. `fetch`: só traz alterações remotas sem aplicar.
2. Cria/lista/apaga branches — trabalho paralelo sem afetar o principal.
3. Copia um repositório remoto completo (com histórico) para local.
4. `add`: prepara alterações (staging). `commit`: regista no histórico local.
5. Git não consegue combinar automaticamente alterações concorrentes na mesma linha — resolução manual.
6. Nome padrão do repositório remoto principal.
7. Mostra estado atual: ficheiros modificados, staged, branch atual.

### Rasteiras comuns
8. `rebase`: reescreve histórico, linear. `merge`: cria commit de junção, preserva histórico real.
9. Aplica um commit específico de outro branch ao atual, sem merge completo.
10. `revert`: novo commit que desfaz (seguro em histórico partilhado). `reset`: reescreve histórico (perigoso após push).
11. `--soft`: mantém staged. `--mixed` (default): mantém no working dir, tira do staging. `--hard`: descarta tudo.
12. Pode sobrescrever/apagar commits de colegas que já fizeram pull — perda de trabalho.
13. Sem efeito nos já rastreados — precisa `git rm --cached` primeiro.

### Hipotéticas
14. `git revert` do commit + rotação imediata da credencial (histórico Git é permanente, revert não apaga).
15. Trunk-based com branches curtas e feature flags — menos overhead que Git Flow para equipas pequenas.

---

## KUBERNETES & DOCKER

### Fundamentos
1. VM virtualiza hardware (SO próprio); container partilha o kernel do host, mais leve/rápido.
2. Imagem: pacote imutável com código+dependências. Dockerfile: receita para construir a imagem.
3. Menor unidade de deployment — um ou mais containers partilhando rede/storage.
4. Garante N réplicas de um Pod sempre a correr, gerindo updates.
5. Expõe um conjunto de Pods sob endereço estável, mesmo que os Pods mudem.
6. `docker run`: um container. `docker-compose up`: orquestra múltiplos containers via ficheiro.
7. Repositório onde imagens são armazenadas/distribuídas (push/pull).

### Rasteiras comuns
8. Diferenças de ambiente (SO, versões, permissões) — Docker resolve isso, mas config externa ao cluster ainda pode falhar.
9. `ClusterIP`: só interno. `NodePort`: expõe porta em cada nó. `LoadBalancer`: load balancer externo (cloud).
10. Readiness: decide se recebe tráfego. Liveness: decide se reinicia. Confundir = tirar tráfego de Pod saudável ou nunca reiniciar um preso.
11. Cada stage só passa o necessário ao seguinte (ferramentas de build ficam para trás) — imagem final mais pequena.
12. Perdem-se — filesystem do container é efémero; precisa de volumes para persistir dados.
13. `ConfigMap`: config não sensível. `Secret`: dados sensíveis, armazenados diferenciadamente.

### Hipotéticas
14. Multi-stage: stage `node` (`npm run build`) + stage `nginx` leve só com os estáticos gerados.
15. `kubectl describe pod` + `kubectl logs`, ver erros de arranque, config/variáveis em falta, dependências externas.

---

## CI/CD — GITHUB ACTIONS

### Fundamentos
1. Continuous Integration/Continuous Deployment — automatizar build, testes, entrega.
2. Sequência automatizada de passos disparada por um evento (push, PR).
3. Job: conjunto de steps num runner. Step: comando/ação individual dentro do job.
4. Define quando o workflow corre — push ou abertura/atualização de PR.
5. Máquina (GitHub-hosted ou self-hosted) que executa os jobs.
6. `env:` a nível de workflow/job/step, ou via `secrets`/`vars` do repositório.
7. Ficheiro(s) gerado por um job, guardado temporariamente para partilhar entre jobs ou download.

### Rasteiras comuns
8. `secrets`: encriptados, ocultos nos logs. `variables`: não sensíveis, texto simples.
9. Versões de dependências diferentes, variáveis em falta, ou dependência de estado local que não existe no runner.
10. Guarda dependências (ex: `node_modules`) entre execuções — reduz tempo de build.
11. Nunca `echo` de secrets, mascarar outputs sensíveis, usar sempre `secrets.NOME`.
12. `needs:`: dependência explícita entre jobs. Steps: sempre sequenciais dentro do mesmo job.
13. Corre o mesmo job com combinações diferentes de variáveis, em paralelo.

### Hipotéticas
14. Build → testes (`needs: build`) → deploy staging (`needs: test`), cada um com condição de sucesso.
15. Step pós-deploy com health check; se falhar, corre job de rollback automático para a versão anterior.

---

## POSTGRESQL & MIGRATIONS

### Fundamentos
1. Primária: identifica unicamente a linha. Estrangeira: referencia chave primária de outra tabela (integridade referencial).
2. `INNER JOIN`: só com correspondência em ambas. `LEFT JOIN`: todas da esquerda, `NULL` onde não há correspondência.
3. Script versionado que altera o schema de forma controlada e reproduzível (ex: `Add-Migration` no EF Core).
4. Filtra linhas (`WHERE`) e agrupa por critério (`GROUP BY`), tipicamente com agregação (`COUNT`, `SUM`).
5. Estrutura que acelera pesquisas numa coluna, custando espaço e escrita mais lenta.
6. `DELETE`: remove linhas (com `WHERE`, transacional, mais lento). `TRUNCATE`: limpa tudo rapidamente, sem log linha-a-linha.
7. Conjunto de operações atómicas — tudo ou nada (rollback).

### Rasteiras comuns
8. Se a tabela da direita tiver múltiplas correspondências, cada uma gera linha extra — duplica dados.
9. Query dispara query adicional por cada resultado da query principal para carregar relações — degrada performance.
10. Índices ocupam espaço e abrandam escritas — só compensa em colunas usadas frequentemente em `WHERE`/`JOIN`.
11. BD pode ficar num estado intermédio inconsistente — migrations devem ser idempotentes/reversíveis ou transacionais.
12. `READ COMMITTED`: lê dados já commitados (leituras não repetíveis possíveis). `SERIALIZABLE`: isolamento total, mais contenção.
13. Traz colunas desnecessárias (custo I/O) e quebra se o schema mudar.

### Hipotéticas
14. `EXPLAIN ANALYZE` na query — ver plano de execução real, identificar sequential scans, considerar índice.
15. Adicionar nova coluna, popular em background, mudar a app para usá-la, só depois remover a antiga — evita `ALTER` bloqueante.

---

## WINDOWS SERVER

### Fundamentos
1. SO da Microsoft para correr serviços de rede, aplicações e infraestrutura empresarial.
2. Serviço de diretório que centraliza gestão de utilizadores, grupos e permissões numa rede.
3. Servidor web da Microsoft para hospedar apps/sites .NET (e outros) no Windows Server.
4. Regras aplicadas centralmente a utilizadores/computadores num domínio AD (ex: políticas de password).
5. Físico: corre direto no hardware. VM: corre sobre hipervisor, partilhando recursos.

### Rasteiras comuns
6. Serviços correm sob uma conta específica sem privilégios necessários — verifica sempre a conta de serviço.
7. Application pool parado/com erro, permissões da pasta do site, ou falha de config no `web.config`.
8. NTFS: aplica-se local independentemente do acesso. Partilha: só via rede — a mais restritiva prevalece.
9. Ferramenta que regista logs de sistema/aplicações/segurança — primeiro sítio a olhar num falhanço silencioso.

### Hipotéticas
10. Publicar estáticos num site IIS + regra de URL Rewrite redirecionando pedidos não encontrados para `index.html`.
