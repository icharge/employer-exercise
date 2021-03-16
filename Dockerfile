FROM openjdk:8-jre-alpine

ADD target/employer-1.0.1.war /app.jar

EXPOSE 8080

CMD ["java", "-Xms128m", "-Xmx256m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
