FROM maven:onbuild AS buildenv
  
FROM openjdk:jre-alpine
COPY --from=buildenv /usr/src/app/target/helloworld-rest-*.jar /opt/helloworld-rest.jar
EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/helloworld-rest.jar"]