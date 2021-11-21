#!/bin/bash

source config.sh

docker-compose --project-directory ./infra --file infra/docker-compose.yml up -d
sleep 30s
docker-compose --project-directory ./deploy --file deploy/docker-compose.yml up -d
docker-compose --project-directory ./reverse-proxy --file reverse-proxy/docker-compose.yml up -d
