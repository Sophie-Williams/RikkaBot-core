package com.rikkabot.rikkabotcore.dao;

import java.util.*;
/*
import org.apache.http.client.fluent.Request;

import org.json.JSONArray;
import org.json.JSONObject;*/

import com.manulaiko.tabitha.Console;

import com.manulaiko.blackeye.launcher.Settings;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Abstract factory.
 * =================
 *
 * This is the abstract factory class, it contains the common
 * logic to all factories so the child classes just need to implement
 * their custom features.
 *
 * The child classes must implement the `endpoint` method which returns
 * the API server endpoint and the `builder` method which returns the builder instance.
 *
 * All child classes must specify the generic type that must be an instance of the DAO class.
 *
 *
 * The method `all` returns all the results from the API server.
 * The method `find` returns a specific result from the API server.
 * The method `where` returns all results from the API server that meets specified criteria (doesn't
 * work with cache).
 *
 * The results are first checked from cache, if it doesn't exist in cache, it will be loaded
 * from the API server and added to the cache.
 * To clear the cache use the `clearCache` method, to retrieve the current cache use the `cache` method.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public abstract class Factory<T>
{
    /**
     * Current cache.
     */
    @Getter @Setter
    protected Map<Integer, T> _cache = new HashMap<>();

    /**
     * Returns all results from the API server.
     *
     * This method will return all the results provided by the API server.
     *
     * @return Results from API server.
     */
    public Map<Integer, T> all()
    {
        if(this._cache.size() != 0) {
            return this._cache;
        }

        Map<Integer, T> ret     = new HashMap<>();
        JSONObject      results = this._get("all");

        if(results.getBoolean("isError")) {
            return ret;
        }

        JSONArray res = results.getJSONArray("result");

        for(int i = 0; i < res.length(); i++) {
            DAO obj = (DAO)this.builder().build(res.getJSONObject(i));

            if(obj == null) {
                continue;
            }

            ret.put(obj.id(), (T)obj);
        }

        this._cache.putAll(ret);

        return this._cache;
    }

    /**
     * Finds and returns a result from the API server.
     *
     * @param id Value of the `id` column.
     *
     * @return Result with id `id`.
     */
    public T find(int id)
    {
        if(this._cache.containsKey(id)) {
            return this._cache.get(id);
        }

        JSONObject result = this._get(id +"/");

        if(result.getBoolean("isError")) {
            return null;
        }

        JSONArray res = result.getJSONArray("result");

        DAO obj = (DAO)this.builder().build(res.getJSONObject(0));

        this._cache.put(obj.id(), (T)obj);

        return (T)obj;
    }

    /**
     * Find and returns various results from the API server.
     *
     * @param ids JSON array with values of the `id` column.
     *
     * @return Results of `ids`.
     */
    public Map<Integer, T> find(JSONArray ids)
    {
        Map<Integer, T> results = new HashMap<>();

        for(int i = 0; i < ids.length(); i++) {
            DAO res = (DAO)this.find(ids.getInt(i));

            results.put(res.id(), (T)res);
        }

        return results;
    }

    /**
     * Finds and returns a result from the API server.
     *
     * This method does not work with cache.
     *
     * @param params Value of the `id` column.
     *
     * @return Result with id `id`.
     */
    public Map<Integer, T> where(Map<String, String> params)
    {
        Map<Integer, T> ret     = new HashMap<>();
        JSONObject      results = this._get("all", params);

        if(results.getBoolean("isError")) {
            return ret;
        }

        JSONArray res = results.getJSONArray("result");

        for(int i = 0; i < res.length(); i++) {
            DAO obj = (DAO)this.builder().build(res.getJSONObject(i));

            ret.put(obj.id(), (T)obj);
        }

        return ret;
    }

    /**
     * Builds and returns a collection of specified amount and id
     *
     * @param id     ID of the row.
     * @param amount Amount of rows with `id` to create.
     *
     * @return List of `amount` sized objects with id `id`.
     */
    public List<T> list(int id, int amount)
    {
        List<T> list = new ArrayList<>();

        for(int i = 0; i < amount; i++) {
            list.add(this.find(id));
        }

        return list;
    }

    /**
     * Clears the current cache.
     */
    public void clearCache()
    {
        this._cache.clear();
    }

    /**
     * Executes a GET request.
     *
     * @param query  Request query.
     * @param params Request params.
     *
     * @return JSONObject from API server.
     */
    protected JSONObject _get(String query, Map<String, String> params)
    {
        String uri = Settings.configuration.getString("core.api_server") + this.endpoint() +"/"+ query;

        String response = "";
        try {
            response = Request.Get(uri)
                              .execute()
                              .returnContent()
                              .toString();
        } catch(Exception e) {
            Console.println("Error while execute GET request to "+ uri +"!");
            Console.debug(e.getMessage());
        }

        if(response.isEmpty()) {
            response = "{\"isError\":true,\"errors\":[{\"code\":-1,\"message\":\"Empty response received!\"}]}";
        }

        return new JSONObject(response);
    }

    /**
     * Executes a GET request.
     *
     * @param query  Request query.
     *
     * @return JSONObject from API server.
     */
    protected JSONObject _get(String query)
    {
        return this._get(query, new HashMap<>());
    }

    /**
     * Returns API endpoint.
     *
     * @return API server endpoint.
     */
    public abstract String endpoint();

    /**
     * Returns the Builder object.
     *
     * @return Builder object.
     */
    public abstract Builder builder();
}
