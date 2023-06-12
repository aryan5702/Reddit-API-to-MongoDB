package com.MiniProject.Reddit.Controllers;

import com.MiniProject.Reddit.Subreddits.MetaData;
import com.MiniProject.Reddit.Subreddits.Subs;
import com.MiniProject.Reddit.Subreddits.SubsServices;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
@RestController
@RequestMapping("/subreddits")
public class SubsController {
    @Autowired
    private SubsServices services;
    @Autowired
    private Environment env;

    private String getAuthToken(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //Different login details as I had to re-create the app
        headers.setBasicAuth("RKNxGgYWSxbkdrClgMRL6g", "dEuhKlpimxfBGgyf8l-lIvW7mc1y8g");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.put("User-Agent", Collections.singletonList("https://www.reddit.com/prefs/apps"));
        String password=env.getProperty("password");
        String user=env.getProperty("user");
        String body = "grant_type=password&username="+user+"&password="+password;
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        String authUrl = "https://www.reddit.com/api/v1/access_token";
        ResponseEntity<String> response = restTemplate.postForEntity(authUrl, request, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        try {
            map.putAll(mapper.readValue(response.getBody(), new TypeReference<Map<String,Object>>(){}));
        }
        catch (IOException e) {
            System.out.println("exception");
            e.printStackTrace();
        }
        return String.valueOf(map.get("access_token"));
    }
    @PostMapping
    public String Store(){

        String uri="https://oauth.reddit.com/api/search_subreddits?query=reddit";
        RestTemplate template=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String token=getAuthToken();
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


    @GetMapping("/sortBySubs")
    public List<Subs> SortSubs(){
        List<Subs> subs=services.ViewAll();
        Collections.sort(subs, new Comparator<Subs>(){
            public int compare(Subs a, Subs b)
            {
                return (a.getSubscribercount())-(b.getSubscribercount());
            }
        });
        return subs;
    }

    @GetMapping("/sortByUsers")
    public List<Subs> SortUsers(){
        List<Subs> subs=services.ViewAll();
        Collections.sort(subs, new Comparator<Subs>(){
            public int compare(Subs a, Subs b)
            {
                return (a.getActiveusercount())-(b.getActiveusercount());
            }
        });
        return subs;
    }

    @GetMapping("/sortByName")
    public List<Subs> Sorted(){
        List<Subs> subs=services.ViewAll();
        Collections.sort(subs, new Comparator<Subs>(){
            public int compare(Subs a, Subs b)
            {
                return a.getName().compareTo(b.getName());
            }
        });
        return subs;
    }
    @GetMapping("/key/{key}")
    public List<Subs> getKey(@PathVariable String key){
        return services.Key(key);
    }

    @GetMapping("/post/{sub}/{title}/{body}")
    public String Post(@PathVariable String sub,@PathVariable String title,@PathVariable String body){
        String uri="https://oauth.reddit.com/api/submit?sr="+sub+"&kind=self&title="+title+"&text="+body;
        RestTemplate template=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String token=env.getProperty("BEARER_TOKEN");
        headers.add("Authorization","bearer "+token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        template.exchange(uri, HttpMethod.POST, requestEntity,String.class);
        return "Posted "+title+" on "+sub;
    }

}
