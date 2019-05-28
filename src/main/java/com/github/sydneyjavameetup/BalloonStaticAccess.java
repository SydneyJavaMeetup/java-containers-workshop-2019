package com.github.sydneyjavameetup;

import java.util.Arrays;
import java.util.List;

public class BalloonStaticAccess implements BalloonDBAccess {
    @Override
    public void deleteBalloon(String id) {

    }

    @Override
    public Balloon getBalloon(String id) {
        return Balloon.of(id, "http://hello", true);
    }

    @Override
    public List<Balloon> listBalloons() {
        return Arrays.asList(
                Balloon.of("1234", "http://hello", true),
                Balloon.of("12345", "http://goodbye", false)
        );
    }

    @Override
    public void saveBalloon(String id, String url, Boolean popped) {

    }

    @Override
    public void saveBalloon(Balloon balloon) {

    }
}
