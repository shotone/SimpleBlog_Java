FROM openjdk:11

CMD ["./gradlew", "build"]

COPY ./build/libs/blog-0.0.1-SNAPSHOT.jar ./usr/src/app/blog-0.0.1-SNAPSHOT.jar
COPY ./conf/dev/* ./usr/src/app/conf/


CMD ["java", "-Xdebug",  "-Xrunjdwp:transport=dt_socket,server=y,address=*:8789,suspend=n", "-Dspring.config.location=./usr/src/app/conf/", "-Dspring.config.name=datasource,application", "-jar", "./usr/src/app/blog-0.0.1-SNAPSHOT>



