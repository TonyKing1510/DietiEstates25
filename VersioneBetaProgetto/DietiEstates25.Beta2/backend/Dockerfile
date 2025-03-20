
FROM openjdk:21-jdk
COPY .env /usr/app/
ENV SECRET_KEY=very_secret_key_not_to_share
COPY target/ProvaProgettoV2-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "ProvaProgettoV2-1.0-SNAPSHOT.jar"]
