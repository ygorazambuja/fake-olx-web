FROM maven:3.6.3-openjdk-11-slim AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package


FROM openjdk:11-slim
EXPOSE 8081
COPY --from=MAVEN_BUILD build/target/olx-0.0.1.jar /app/

ENTRYPOINT ["java", "-jar", "app/olx-0.0.1.jar"]
