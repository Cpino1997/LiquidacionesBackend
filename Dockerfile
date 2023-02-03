FROM openjdk:19

ADD target/*.jar app.jar
ENV JAVA_OPTS=""

EXPOSE 8080:8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]