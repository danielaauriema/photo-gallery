#!/bin/bash

echo $1 @ $2

docker run -it --rm -w /app \
  -v ${TMP}/repository:/root/.m2/repository \
  -v $1/$2/pom.xml:/app/pom.xml \
  -v $1/$2/src/:/app/src/ \
  -v ${TMP}/$2/:/build/ \
  maven:3.8.3-openjdk-11-slim  \
  mvn clean package -Pprod -DskipTests

docker build ${TMP}/$2 -f build/java.Dockerfile -t photo-gallery-$2


