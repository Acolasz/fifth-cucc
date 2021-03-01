FROM maven:3.6.3-openjdk-8 AS builder

ADD . /app
WORKDIR /app

RUN mvn -U clean package -PskipJavaDoc

FROM openjdk:8-jdk

COPY --from=builder "/app/fifth-cucc-service/target/*-service-*.jar" service.jar
COPY ./entrypoint.sh /
RUN chmod 700 /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /service.jar" ]
