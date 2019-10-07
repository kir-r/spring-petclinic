FROM openjdk:8-jre-slim
EXPOSE 8082
COPY target/spring-petclinic-2.1.0.BUILD-SNAPSHOT.jar /app/spring-petclinic.jar
RUN bash -c 'touch /app/spring-petclinic.jar'
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-petclinic.jar"]
