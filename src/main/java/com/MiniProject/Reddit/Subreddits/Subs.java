package com.MiniProject.Reddit.Subreddits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="subreddits") //shift + f6
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subs {
    @Id
    private String name;
    @JsonProperty("active_user_count")
    private int activeUserCount;
    @JsonProperty("icon_img")
    private String iconImg=null;
    @JsonProperty("key_color")
    private String keyColor;
    @JsonProperty("subscriber_count")
    private int subscriberCount;
    @JsonProperty("allow_images")
    private boolean allowImages;
    //camel case in java
    // refresh token
    // formulate

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActiveUserCount() {
        return activeUserCount;
    }

    public void setActiveUserCount(int activeUserCount) {
        this.activeUserCount = activeUserCount;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getKeyColor() {
        return keyColor;
    }

    public void setKeyColor(String keyColor) {
        this.keyColor = keyColor;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(int subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public boolean isAllowImages() {
        return allowImages;
    }

    public void setAllowImages(boolean allowImages) {
        this.allowImages = allowImages;
    }
}
