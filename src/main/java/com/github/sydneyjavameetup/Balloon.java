package com.github.sydneyjavameetup;

public class Balloon {
    private String id;
    private String url;
    private Boolean popped;

    public static Balloon of(String id, String url, Boolean popped) {
        Balloon balloon = new Balloon();
        balloon.id = id;
        balloon.url = url;
        balloon.popped = popped;
        return balloon;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getPopped() {
        return popped;
    }

    @Override
    public String toString() {
        return "Balloon{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", popped=" + popped +
                '}';
    }
}
