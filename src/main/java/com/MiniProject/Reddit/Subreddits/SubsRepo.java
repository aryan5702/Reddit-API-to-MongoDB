package com.MiniProject.Reddit.Subreddits;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubsRepo extends MongoRepository<Subs,String> {
    public List<Subs> findByActiveusercount(int count);
    public List<Subs> findByKeycolor(String c);

    public List<Subs> findBySubscribercount(int i);

    public List<Subs> findByAllowimages(Boolean b);
}
