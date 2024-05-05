ARG MAVEN_PROFILE=stage

# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /app
COPY . .
RUN mvn -f pom.xml clean package -P$MAVEN_PROFILE -DskipTests

# Package stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /usr/local/lib/patterson.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/patterson.jar"]