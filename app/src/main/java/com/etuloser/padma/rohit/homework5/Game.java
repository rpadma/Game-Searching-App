package com.etuloser.padma.rohit.homework5;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Rohit on 2/17/2017.
 */

public class Game implements Serializable {

    String Overview,publisher,gametitle,youtubelink,imageurl,id,platform,publisheddata;
    ArrayList<String> SimilarId;
    ArrayList<String> genre;

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getGametitle() {
        return gametitle;
    }

    public void setGametitle(String gametitle) {
        this.gametitle = gametitle;
    }

    public String getPublisheddata() {
        return publisheddata;
    }

    public void setPublisheddata(String publisheddata) {
        this.publisheddata = publisheddata;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public ArrayList<String> getSimilarId() {
        return SimilarId;
    }

    public void setSimilarId(ArrayList<String> similarId) {
        SimilarId = similarId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getYoutubelink() {
        return youtubelink;
    }

    public void setYoutubelink(String youtubelink) {
        this.youtubelink = youtubelink;
    }


}
