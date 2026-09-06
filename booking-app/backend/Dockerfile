FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn -q clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
COPY --from=build /app/target/booking-0.1.0.jar /app.jar
# BUG: password da BD "embutida" na imagem (fica visivel em "docker history").
ENV DB_PASSWORD=supersecret123
EXPOSE 8080
# BUG: CMD em forma "shell" -> java corre como filho de /bin/sh (PID 1 e o
# sh), SIGTERM do "docker stop" nao chega ao java -> kill so ao fim do timeout.
CMD java -jar /app.jar
