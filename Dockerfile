FROM --platform=linux/amd64 maven:3.8.5-openjdk-17 as builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

FROM --platform=linux/amd64 openjdk:17

COPY --from=builder /app/target/resource-server-*.jar /resource-server.jar

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/resource-server.jar"]
EXPOSE 9000