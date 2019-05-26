package com.github.sydneyjavameetup;

import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class BalloonDynamoAccess {

    public static final String TABLE_NAME = "Balloons-Table-SydneyJavaContainers";

    public static void main(String[] args) {
        DynamoDbAsyncClient client = DynamoDbAsyncClient.create();


        HashMap<String,AttributeValue> item_values = new HashMap<>();
        item_values.put("BalloonId", AttributeValue.builder().s("123").build());
        item_values.put("URL", AttributeValue.builder().s("http://clipart-library.com/data_images/19301.png").build());

        client.putItem(PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item_values)
                .build()).join();

        CompletableFuture<ScanResponse> listBalloons = client.scan(
                ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build());

        List<Balloon> balloons = listBalloons.join()
                .items()
                .stream()
                .map(balloonRecord ->
                        Balloon.of(
                                balloonRecord.get("BalloonId").s(),
                                balloonRecord.get("URL").s())
                )
                .collect(Collectors.toList()
        );
        System.out.println(balloons);

    }
}
