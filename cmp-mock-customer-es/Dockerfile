FROM openjdk:11
ADD target/cmp-0.0.1-SNAPSHOT.jar cmp-mock.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cmp-mock.jar"]