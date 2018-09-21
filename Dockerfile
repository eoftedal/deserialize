FROM maven

WORKDIR /app

COPY src /app
COPY pom.xml /app

RUN mvn compile

CMD mvn jetty:run
