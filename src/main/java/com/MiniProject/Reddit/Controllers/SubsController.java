package com.MiniProject.Reddit.Controllers;

import com.MiniProject.Reddit.Subreddits.MetaData;
import com.MiniProject.Reddit.Subreddits.Subs;
import com.MiniProject.Reddit.Subreddits.SubsServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
@RequestMapping("/subreddits")
public class SubsController {
    @Autowired
    private SubsServices services;
    @Autowired
    private Environment env;

    @PostMapping
    public String Store(){

        String uri="https://oauth.reddit.com/api/search_subreddits?query=reddit";
        RestTemplate template=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String token=env.getProperty("BEARER_TOKEN");
        headers.add("Authorization","bearer "+token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ObjectMapper objectmapper=new ObjectMapper();
        ResponseEntity<String> objects=template.exchange(uri, HttpMethod.POST, requestEntity,String.class);
        MetaData wrapper=null;
        try{
            wrapper=objectmapper.readValue(objects.getBody(), MetaData.class);
            for(Subs s:wrapper.getSubreddits()) services.Save(s);
            return "Data Stored Successfully\n";
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add")
    public Subs Add(@RequestBody Subs sub){
        return services.Save(sub);
    }

    @GetMapping("/viewSubs")
    public List<Subs> ViewSubs(){
        return services.ViewAll();
    }

    @GetMapping("/name/{name}")
    public Subs Name(@PathVariable String name){
        return services.ViewByName(name);
    }

    @GetMapping("/userCount/{users}")
    public List<Subs> UserCount(@PathVariable int users){
        return services.ViewByUserCount(users);
    }

    @GetMapping("/subCount/{count}")
    public List<Subs> SubCount(@PathVariable int count){
        return services.ViewBySubsCount(count);
    }

    @GetMapping("/colour/{c}")
    public List<Subs> Colour(@PathVariable String c){
        return services.ViewByColour(c);
    }

//    @GetMapping("/image/{img}")
//    public List<Subs> Image(@PathVariable String img){
//        return services.ViewByImg(img);
//    }

    @GetMapping("/allowImage/{a}")
    public List<Subs> Allow(@PathVariable Boolean a){
        return services.ViewByAllowImg(a);
    }

    @PutMapping("/update")
    public Subs Update(@RequestBody Subs sub){
        return services.Update(sub);
    }

    @DeleteMapping("/delete/{name}")
    public String Delete(@PathVariable String name){
        return services.Delete(name);
    }
}
