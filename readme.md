
# Workshop 
In this workshop we'll be building a simple Docker based Java service, and deploying it to Amazon Web Services (AWS) Elastic Container Service (ECS). 

We'll start by discussing a few of the ways docker and containerisation can be used on the AWS platform.

Then we'll dive into the workshop steps:

## Step 1 - Deploy Environment
Next, for the adventurous, we'll deploy a container environment on our own AWS accounts using CloudFormation, an infrastructure automation framework. 

If you're doing that you'll need to install the AWS CLI from here:
https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html

Once you've got the CLI installed and configured (aws configure), you'll need to deploy the environment using the template here:

[deploy-environment.yml](deploy-environment.yml)

```
aws cloudformation deploy --region ap-southeast-2 \
                          --template-file "deploy-environment.yml" \
                          --stack-name SydneyJavaContainers \
                          --capabilities CAPABILITY_NAMED_IAM
```

## Step 2 - Build a Web Service with Helidon
After that we'll build a small Java web service using the Helidon framework, and package it into a Java 8 (AWS Corretto) container.
https://helidon.io/#/

```
mvn archetype:generate -DinteractiveMode=false \
    -DarchetypeGroupId=io.helidon.archetypes \
    -DarchetypeArtifactId=helidon-quickstart-mp \
    -DarchetypeVersion=1.1.1 \
    -DgroupId=com.github.sydneyjavameetup \
    -DartifactId=balloon-service \
    -Dpackage=com.github.sydneyjavameetup.balloons
```

* trim the Dockerfile and .dockerignore
* build and package with maven
* build docker image
* run the docker image locally
* update the port to 80
* rebuild

## Publishing to Our AWS Environment
Finally to bring it all together we'll update the container environment by publishing our Java service to a container registry and creating an updated service task to point at our service's container.

Push instructions:
https://docs.aws.amazon.com/AmazonECR/latest/userguide/docker-push-ecr-image.html

Log docker into ECR:
```
aws ecr get-login --region ap-southeast-2 --no-include-email
```

Tag:
```
docker tag balloon-service:latest xyz.ecr.region.amazonaws.com:latest
```

Push:
```
docker push xyz.ecr.region.amazonaws.com:latest
```

Update the CloudFormation template:
```
aws cloudformation deploy --region ap-southeast-2 \
                          --template-file "deploy-environment.yml" \
                          --stack-name SydneyJavaContainers \
                          --capabilities CAPABILITY_NAMED_IAM \
                          --parameter-overrides \
                          ContainerImage="xyz.ecr.region.amazonaws.com:latest"
```

## Reference
Here's the preso!
https://1drv.ms/p/s!Al_H-71CJTZJi-gYFy6RslD5-Y3cZQ 

There will be a lot to get through in a short time, but we'll have a crack, hopefully have some fun and learn something along the way as well. 


# Handy Docker Commands
https://docs.docker.com/engine/reference/commandline/cli/

### Build an Image based on Dockerfile
```
docker build -t image-name .
```

### Run an image (with port 8080 exposed to the host)
```
docker run -p 8080:8080/tcp image-name
```

### View running docker images
```
docker ps
```

### List Images
```
docker images
```

### Remove an Image
```
docker image rm image-name
```
or 
```
docker image rm --force image-name
```

### Shell into a running image
```
docker exec -it 52c5629d1266 /bin/bash
```
