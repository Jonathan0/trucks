FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=build/libs/trucks-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app

EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
