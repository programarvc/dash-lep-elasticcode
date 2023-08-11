FROM openjdk:17-alpine
MAINTAINER agilize
VOLUME /tmp
EXPOSE 8091
RUN mkdir -p /app/
RUN mkdir -p /app/logs/

ADD build/libs/*.jar /app/dash.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=container", "-jar", "/app/dash.jar"]