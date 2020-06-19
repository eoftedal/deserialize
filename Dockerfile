FROM maven:3-jdk-8

WORKDIR /app

COPY src /app/src/
COPY pom.xml /app

RUN mvn compile

CMD mvn jetty:run
