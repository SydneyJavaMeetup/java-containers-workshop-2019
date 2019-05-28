package com.github.sydneyjavameetup;

import java.util.List;

public interface BalloonDBAccess {
    void deleteBalloon(String id);

    Balloon getBalloon(String id);

    List<Balloon> listBalloons();

    void saveBalloon(String id, String url, Boolean popped);

    void saveBalloon(Balloon balloon);
}
