package com.rikkabot.rikkabotcore.dao;

import java.util.HashMap;

/**
 * Base factory class.
 * ===================
 *
 * Serves as base for the rest of factories.
 *
 * It contains the cached instances and provides methods for retrieving them.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Factory<T extends DAO> {
    /**
     * Cached instances.
     */
    private HashMap<Integer, T> cache = new HashMap<>();

    /**
     * Returns a cached instance by its id.
     *
     * @param id Instance ID.
     *
     * @return Cached instance or null.
     */
    public T byID(int id) {
        return this.cache.getOrDefault(id, null);
    }

    /**
     * Adds an instance to the cache.
     *
     * @param id Instance ID.
     * @param i  Instance.
     *
     * @return Previous instance with ID.
     */
    public T add(int id, T i) {
        return this.cache.put(id, i);
    }

    /**
     * Returns all the cached instances.
     *
     * @return All cached instances.
     */
    public HashMap<Integer, T> all() {
        return this.cache;
    }

    /**
     * Clears the cache.
     */
    public void clear() {
        this.cache = new HashMap<>();
    }
}
