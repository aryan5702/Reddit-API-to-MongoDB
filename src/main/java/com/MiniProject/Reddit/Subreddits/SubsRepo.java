package com.MiniProject.Reddit.Subreddits;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubsRepo extends MongoRepository<Subs,String> {
    public List<Subs> findByActiveUserCount(int count);
    public List<Subs> findByKeyColor(String c);

    public List<Subs> findBySubscriberCount(int i);

    public List<Subs> findByAllowImages(Boolean b);
}
