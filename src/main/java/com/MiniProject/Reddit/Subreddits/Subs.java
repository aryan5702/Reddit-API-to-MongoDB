package com.MiniProject.Reddit.Subreddits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="subreddits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
