FROM openjdk:17
EXPOSE 8090
ADD target/blog-app-apis-0.0.1-SNAPSHOT.jar blogapp.jar
ENTRYPOINT ["java","-jar","/blogapp.jar"]
