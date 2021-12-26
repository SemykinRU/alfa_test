FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=build/libs/alfa_test-0.1.0.jar
COPY ${JAR_FILE} currency_app.jar
ENTRYPOINT ["java","-jar","/currency_app.jar"]