# Build simples de um unico estagio - copia o codigo fonte, o .m2 e o Maven
# para dentro da imagem final. A imagem de producao acaba com ferramentas de
# build, cache do Maven e codigo fonte que nunca deviam la estar.
# (Compara com um Dockerfile multi-stage no README/ANSWERS.)
FROM maven:3.9-eclipse-temurin-21

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

EXPOSE 8080
CMD ["java", "-jar", "target/selfservice-portal-0.1.0.jar"]
