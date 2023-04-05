FROM openjdk:17
ADD target/i-market.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080