#!/bin/bash

mvn package

aws cloudformation describe-stacks --stack-name SydneyJavaContainers > cfn-output.json
ECR_REPO=$(jq < cfn-output.json -r '.Stacks[0].Outputs[] | select(.OutputKey=="'EcrRepo'") | .OutputValue')

$(aws ecr get-login --no-include-email)

ECR_TAG="latest-$(date +"%m-%d-%Y--%H-%M--%S")"

docker build -t sydney-java-service:${ECR_TAG} .
docker tag sydney-java-service:${ECR_TAG} ${ECR_REPO}:${ECR_TAG}
docker push ${ECR_REPO}:${ECR_TAG}

./deploy-environment.sh "${ECR_REPO}:${ECR_TAG}"
