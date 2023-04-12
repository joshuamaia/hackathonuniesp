FROM openjdk:17-alpine

RUN apk add --no-cache bash
RUN apk update && apk add maven
RUN apk add --no-cache fontconfig freetype
RUN apk add --no-cache msttcorefonts-installer fontconfig
RUN update-ms-fonts

WORKDIR /app

COPY . /app

RUN mvn clean install

ENTRYPOINT ["java","-jar","/app/target/planonegocio-0.0.1-SNAPSHOT.jar"]

