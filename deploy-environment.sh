#!/usr/bin/env bash

CONTAINER_IMAGE=${1:-"nginx:latest"}

echo "Deploying service ${CONTAINER_IMAGE}..."

aws cloudformation deploy --region ap-southeast-2 \
                          --template-file "deploy-environment.yml" \
                          --stack-name SydneyJavaContainers \
                          --capabilities CAPABILITY_NAMED_IAM \
                          --parameter-overrides \
                          ContainerImage="${CONTAINER_IMAGE}"
