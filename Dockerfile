FROM openjdk:17-oracle
COPY --from=jarvis.bot-0.0.1-SNAPSHOT.jar jarvis.bot.app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","jarvis.bot.app.jar"]
