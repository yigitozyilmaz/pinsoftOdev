FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar odev.jar
ENTRYPOINT ["java","-jar","/odev.jar"]
EXPOSE 8080