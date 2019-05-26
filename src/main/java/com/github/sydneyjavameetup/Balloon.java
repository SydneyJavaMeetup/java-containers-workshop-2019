package com.github.sydneyjavameetup;

public class Balloon {
    private String id;
    private String url;

    public static Balloon of(String id, String url) {
        Balloon balloon = new Balloon();
        balloon.id = id;
        balloon.url = url;
        return balloon;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Balloon{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
