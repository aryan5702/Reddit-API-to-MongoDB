package com.MiniProject.Reddit.Subreddits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="subreddits")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subs {
    @Id
    private String name;
    @JsonProperty("active_user_count")
    private int activeusercount;
    @JsonProperty("icon_img")
    private String iconimg=null;
    @JsonProperty("key_color")
    private String keycolor;
    @JsonProperty("subscriber_count")
    private int subscribercount;
    @JsonProperty("allow_images")
    private boolean allowimages;
    //camel case in java
    // refresh token
    // formulate

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActiveusercount() {
        return activeusercount;
    }

    public void setActiveusercount(int activeusercount) {
        this.activeusercount = activeusercount;
    }

    public String getIconimg() {
        return iconimg;
    }

    public void setIconimg(String iconimg) {
        this.iconimg = iconimg;
    }

    public String getKeycolor() {
        return keycolor;
    }

    public void setKeycolor(String keycolor) {
        this.keycolor = keycolor;
    }

    public int getSubscribercount() {
        return subscribercount;
    }

    public void setSubscribercount(int subscribercount) {
        this.subscribercount = subscribercount;
    }

    public boolean isAllowimages() {
        return allowimages;
    }

    public void setAllowimages(boolean allowimages) {
        this.allowimages = allowimages;
    }
}
