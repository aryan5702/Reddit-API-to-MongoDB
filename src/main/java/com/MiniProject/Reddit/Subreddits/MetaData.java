package com.MiniProject.Reddit.Subreddits;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetaData {
    private List<Subs> subreddits;
}
