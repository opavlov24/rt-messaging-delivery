FROM openjdk:8
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN ./gradlew build

FROM openjdk:8-jre-alpine
WORKDIR /root/
COPY --from=0 /usr/src/app/build/libs/rt-messaging-delivery.jar .
EXPOSE 8080
CMD ["java", "-jar", "rt-messaging-delivery.jar"]