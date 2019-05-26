FROM amazoncorretto:8
RUN echo java -version
RUN yum install -y unzip
COPY ./target/balloon-service.zip /
RUN mkdir balloon-service
WORKDIR /balloon-service
RUN unzip /balloon-service.zip
ENTRYPOINT ["java"]
CMD ["-jar", "balloon-service.jar"]
