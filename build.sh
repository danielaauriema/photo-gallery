#!/bin/bash

export CURRENT_DIR=$(pwd)

source config.sh

source build/java-build.sh $CURRENT_DIR image-service
source build/java-build.sh $CURRENT_DIR post-service

docker build ./reverse-proxy -t photo-gallery-proxy