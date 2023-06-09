package com.MiniProject.Reddit.Subreddits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubsServices {
    @Autowired
    private SubsRepo repo;

    public Subs Save(Subs name){
        return repo.save(name);
    }

    public List<Subs> ViewAll(){
        return repo.findAll();
    }

    public Subs ViewByName(String name){
        return repo.findById(name).get();
    }

    public List<Subs> ViewByUserCount(int count){
        return repo.findByActiveusercount(count);
    }

    public List<Subs> ViewBySubsCount(int count){
        return repo.findBySubscribercount(count);
    }

    public List<Subs> ViewByColour(String c){
        return repo.findByKeycolor(c);
    }

//    public List<Subs> ViewByImg(String i){
//        return repo.findByIconimg(i);
//    }

    public List<Subs> ViewByAllowImg(Boolean b){
        return repo.findByAllowimages(b);
    }

    public Subs Update(Subs sub){
        Subs curr=repo.findById(sub.getName()).get();
        curr.setKeycolor(sub.getKeycolor());
        curr.setIconimg(sub.getIconimg());
        curr.setAllowimages(sub.isAllowimages());
        curr.setSubscribercount(sub.getSubscribercount());
        curr.setActiveusercount(sub.getActiveusercount());
        return repo.save(curr);
    }

    public String Delete(String name){
        repo.deleteById(name);
        return("Subreddit with name "+name+" deleted successfully\n");
    }
}
