FROM maven:onbuild AS buildenv
  
FROM openjdk:8-jdk
COPY --from=buildenv /usr/src/app/target/helloworld-rest-*.jar /opt/helloworld-rest.jar
EXPOSE 8888

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/helloworld-rest.jar"]