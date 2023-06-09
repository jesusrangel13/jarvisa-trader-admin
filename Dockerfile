FROM openjdk:17-oracle
WORKDIR /app
ARG JAR_FILE=build/libs/jarvis.bot-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
