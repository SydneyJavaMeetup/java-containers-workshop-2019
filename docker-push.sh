#!/bin/bash

aws cloudformation describe-stacks --stack-name SydneyJavaContainers > cfn-output.json
ECR_REPO=$(jq < cfn-output.json -r '.Stacks[0].Outputs[] | select(.OutputKey=="'EcrRepo'") | .OutputValue')

$(aws ecr get-login --no-include-email)

docker build -t sydney-java-service .
docker tag sydney-java-service:latest $ECR_REPO:latest
docker push $ECR_REPO:latest
