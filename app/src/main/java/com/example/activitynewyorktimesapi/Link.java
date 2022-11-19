package com.example.activitynewyorktimesapi;

public class Link {
    String type;
    String url;
    String suggested_link_text;

    public Link(String type, String url, String suggested_link_text) {
        this.type = type;
        this.url = url;
        this.suggested_link_text = suggested_link_text;
    }
}
