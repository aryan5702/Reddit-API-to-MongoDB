package com.MiniProject.Reddit.Subreddits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsServices {
    @Autowired
    private SubsRepo repo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Subs Save(Subs name){
        return repo.insert(name);
    }

    public List<Subs> ViewAll(){
        return repo.findAll();
    }

    public Subs ViewByName(String name){
        return repo.findById(name).get();
    }

    public List<Subs> ViewByUserCount(int count){
        return repo.findByActiveUserCount(count);
    }

    public List<Subs> ViewBySubsCount(int count){
        return repo.findBySubscriberCount(count);
    }

    public List<Subs> ViewByColour(String c){
        return repo.findByKeyColor(c);
    }

    public List<Subs> ViewByAllowImg(Boolean b){
        return repo.findByAllowImages(b);
    }

    public Subs Update(Subs sub){
        Subs curr=repo.findById(sub.getName()).get();
        curr.setKeyColor(sub.getKeyColor());
        curr.setIconImg(sub.getIconImg());
        curr.setAllowImages(sub.isAllowImages());
        curr.setSubscriberCount(sub.getSubscriberCount());
        curr.setActiveUserCount(sub.getActiveUserCount());
        return repo.save(curr);
    }

    public String Delete(String name){
        repo.deleteById(name);
        return("Subreddit with name "+name+" deleted successfully\n");
    }

    public List<Subs> Key(String key){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(key));
        return mongoTemplate.find(query, Subs.class);
    }
}
