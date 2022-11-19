package com.example.activitynewyorktimesapi;

public class Result {
    String display_title;
    String mpaa_rating;
    int critics_pick;
    String byline;
    String headline;
    String summary_short;
    String publication_date;
    String date_updated;
    Link link;
    Multimedia multimedia;

    public Result(String display_title, String mpaa_rating, int critics_pick, String byline, String headline, String summary_short, String publication_date, String date_updated, Link link, Multimedia multimedia) {
        this.display_title = display_title;
        this.mpaa_rating = mpaa_rating;
        this.critics_pick = critics_pick;
        this.byline = byline;
        this.headline = headline;
        this.summary_short = summary_short;
        this.publication_date = publication_date;
        this.date_updated = date_updated;
        this.link = link;
        this.multimedia = multimedia;
    }
}
