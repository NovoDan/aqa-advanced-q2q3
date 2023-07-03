FROM ubuntu:latest
WORKDIR /app
COPY . /app
RUN apt-get -y update && \
    apt-get install -y \
    openjdk-17-jdk openjdk-17-jre \
    maven
CMD ["mvn", "clean", "install", "-Dtest=api.*Test", "test"]