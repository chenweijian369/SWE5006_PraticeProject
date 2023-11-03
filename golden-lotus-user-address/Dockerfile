FROM openjdk:8-jdk-alpine
MAINTAINER ZJH

WORKDIR /app
COPY ./target/golden-lotus-server-1.0-SNAPSHOT.jar golden-lotus-server-1.0-SNAPSHOT.jar

EXPOSE 8085

CMD ["java","-jar","/app/golden-lotus-server-1.0-SNAPSHOT.jar"]
