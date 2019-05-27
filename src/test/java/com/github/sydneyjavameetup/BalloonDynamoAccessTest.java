package com.github.sydneyjavameetup;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BalloonDynamoAccessTest {

    @Test
    void crudBalloon() {
        BalloonDynamoAccess balloonDynamoAccess = new BalloonDynamoAccess();

        balloonDynamoAccess.saveBalloon("123", "http://clipart-library.com/data_images/19301.png", false);

        List<Balloon> balloons = balloonDynamoAccess.listBalloons();
        System.out.println(balloons);

        assertEquals(1, balloons.size());
        assertEquals("123", balloons.get(0).getId());
        assertEquals("http://clipart-library.com/data_images/19301.png", balloons.get(0).getUrl());
        assertEquals(false, balloons.get(0).getPopped());

        balloonDynamoAccess.saveBalloon("123", "http://clipart-library.com/data_images/19302.png", true);

        Balloon balloon = balloonDynamoAccess.getBalloon("123");
        System.out.println(balloon);

        assertEquals("123", balloon.getId());
        assertEquals("http://clipart-library.com/data_images/19302.png", balloon.getUrl());
        assertEquals(true, balloon.getPopped());

        balloonDynamoAccess.deleteBalloon("123");

        List<Balloon> balloonsEnd = balloonDynamoAccess.listBalloons();
        System.out.println(balloonsEnd);

        assertEquals(0, balloonsEnd.size());
    }
}