#FROM bellsoft/liberica-openjdk-alpine:21 AS builder
#
#WORKDIR /workspace/app
#RUN mkdir [jar-directory]
#
#COPY ./mvnw .
#COPY ./.mvn .mvn
#COPY ./pom.xml .
#
#COPY ./[jar-directory]/pom.xml [jar-directory]
#COPY ./[jar-directory]/src [jar-directory]/src
#
#RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
#
#FROM bellsoft/liberica-openjdk-alpine:21
#VOLUME /tmp
#
#WORKDIR /workspace/build/[jar-directory]
#ARG JAR_FILE=/workspace/profile/[jar-directory]/target/[jar-directory]-0.0.1-SNAPSHOT.jar
#COPY --from=builder ${JAR_FILE} ./[jar-directory].jar
#
#EXPOSE [PORT]

FROM openjdk:21
COPY profile/target/profile-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]