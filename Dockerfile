From openjdk:11
EXPOSE 8080
COPY /target/mindoktor-assignment-0.0.1-SNAPSHOT.jar  mindoktor-assignment.jar
ENTRYPOINT ["java","-jar","mindoktor-assignment.jar"]

