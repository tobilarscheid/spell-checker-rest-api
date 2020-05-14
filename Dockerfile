FROM openjdk:14-jdk-alpine as builder
COPY . /workdir
WORKDIR /workdir
RUN ./gradlew build

FROM openjdk:14-jdk-alpine
COPY --from=builder /workdir/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]