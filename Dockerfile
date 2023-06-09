FROM openjdk:17-oracle
WORKDIR /app
COPY build/libs/jarvis.bot-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
