package com.rikkabot.rikkabotcore.dao;

import org.json.JSONObject;

/**
 * Builder interface.
 * ==================
 *
 * Is the responsible for building the DAO objects.
 * All the logic regarding construction of DAO objects should be here.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface Builder<T>
{
    /**
     * Builds and returns the DAO object.
     *
     * @param json API server response.
     *
     * @return DAO object for `json`.
     */
    T build(JSONObject json);
}
