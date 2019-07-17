FROM openjdk:8
EXPOSE 8088
VOLUME /tmp
COPY . /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/build/libs/demo.jar"]
