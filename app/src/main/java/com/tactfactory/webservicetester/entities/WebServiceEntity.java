package com.tactfactory.webservicetester.entities;

import com.tactfactory.webservicetester.httpclientapache.activities.HttpClientActivity;
import com.tactfactory.webservicetester.springrestservice.activities.SpringRestActivity;
import com.tactfactory.webservicetester.volley1.activities.Volley1Activity;
import com.tactfactory.webservicetester.volley2.activities.Volley2Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tactfactory on 11/12/17.
 */

public class WebServiceEntity {
    private String name;
    private String url;
    private Class<?> activity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WebServiceEntity(){

    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }

    public WebServiceEntity(String name, String url, Class<?> activity){
        this.name = name;
        this.url = url;
        this.activity = activity;
    }

    public static List<WebServiceEntity> getItems(){

        //Generate items
        List<WebServiceEntity> wsEL = new ArrayList<WebServiceEntity>();
        wsEL.add(new WebServiceEntity("httpClient Apache","", HttpClientActivity.class));
        wsEL.add(new WebServiceEntity("volley 1","", Volley1Activity.class));
        wsEL.add(new WebServiceEntity("volley 2","", Volley2Activity.class));
//        wsEL.add(new WebServiceEntity("android async http",""));
        wsEL.add(new WebServiceEntity("spring android rest","", SpringRestActivity.class));
        return wsEL;
    }
}
