package com.tactfactory.webservicetester.mywstester.managers.base;

import org.json.JSONObject;

/**
 * Created by tactfactory on 12/12/17.
 */

public interface BaseManager {
    JSONObject get(String url);
    void update(String url);
    void delete(String url);;
    JSONObject post(String url);
}
