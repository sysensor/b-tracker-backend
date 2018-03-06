FROM java:8
MAINTAINER sysensor.it@gmail.com
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} b-tracker-backend.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/b-tracker-backend.jar"]