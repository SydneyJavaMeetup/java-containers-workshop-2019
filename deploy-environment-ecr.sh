#!/usr/bin/env bash

aws cloudformation describe-stacks --stack-name SydneyJavaContainers > cfn-output.json
ECR_REPO=$(jq < cfn-output.json -r '.Stacks[0].Outputs[] | select(.OutputKey=="'EcrRepo'") | .OutputValue')

aws cloudformation deploy --region ap-southeast-2 \
                          --template-file "deploy-environment.yml" \
                          --stack-name SydneyJavaContainers \
                          --capabilities CAPABILITY_NAMED_IAM \
                          --parameter-overrides \
                          ContainerImage="${ECR_REPO}:latest"
