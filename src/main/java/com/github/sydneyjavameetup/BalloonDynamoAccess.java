package com.github.sydneyjavameetup;

import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class BalloonDynamoAccess {
    private static final String TABLE_NAME = "Balloons-Table-SydneyJavaContainers";
    private static final DynamoDbAsyncClient client = DynamoDbAsyncClient.create();

    public void deleteBalloon(String id) {
        client.deleteItem(DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Collections.singletonMap("BalloonId", AttributeValue.builder().s(id).build()))
                .build()).join();
    }

    public Balloon getBalloon(String id) {
        Map<String, AttributeValue> balloonProperties = client.getItem(GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Collections.singletonMap("BalloonId", AttributeValue.builder().s(id).build()))
                .build()).join().item();
        return Balloon.of(balloonProperties.get("BalloonId").s(), balloonProperties.get("URL").s(), balloonProperties.get("Popped").bool());
    }

    public List<Balloon> listBalloons() {
        CompletableFuture<ScanResponse> listBalloons = client.scan(
                ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build());

        return listBalloons.join()
                .items()
                .stream()
                .map(balloonRecord ->
                        Balloon.of(
                                balloonRecord.get("BalloonId").s(),
                                balloonRecord.get("URL").s(),
                                balloonRecord.get("Popped").bool())
                )
                .collect(Collectors.toList()
        );
    }

    public void saveBalloon(String id, String url, Boolean popped) {
        HashMap<String, AttributeValue> item_values = new HashMap<>();
        item_values.put("BalloonId", AttributeValue.builder().s(id).build());
        item_values.put("URL", AttributeValue.builder().s(url).build());
        item_values.put("Popped", AttributeValue.builder().bool(popped).build());

        client.putItem(PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item_values)
                .build()).join();
    }
}
