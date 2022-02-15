FROM openjdk:17.0-jdk-slim
EXPOSE 8080
COPY target/spring-petclinic-2.1.0.BUILD-SNAPSHOT.jar /app/spring-petclinic.jar
RUN bash -c 'touch /app/spring-petclinic.jar'
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-petclinic.jar"]
