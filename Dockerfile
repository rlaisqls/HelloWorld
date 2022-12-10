FROM openjdk:17.0.1-jdk-slim

EXPOSE 8080
ENV TZ=Asia/Seoul

COPY ./helloworld-infra/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]