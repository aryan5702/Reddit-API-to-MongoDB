package com.MiniProject.Reddit.Subreddits;

//import lombok.*;

import java.util.List;


public class MetaData {
    private List<Subs> subreddits;

    public List<Subs> getSubreddits() {
        return subreddits;
    }

    public void setSubreddits(List<Subs> subreddits) {
        this.subreddits = subreddits;
    }
}
