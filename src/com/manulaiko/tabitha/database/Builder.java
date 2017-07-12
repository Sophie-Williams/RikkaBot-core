package com.manulaiko.tabitha.database;

import java.sql.ResultSet;

/**
 * @author Manulaiko <manulaiko@gmail.com>
 */
public abstract class Builder
{
    /**
     * Builds and returns a DAO object.
     *
     * @param result Query result.
     */
    public abstract DAO build(ResultSet result);
}
