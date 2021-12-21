FROM openjdk:16-alpine3.13
LABEL maintainer="monikasa891@gmail.com"
EXPOSE 8080
ADD target/spring-food-delivery.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]