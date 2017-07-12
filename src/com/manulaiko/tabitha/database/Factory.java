package com.manulaiko.tabitha.database;

import java.sql.ResultSet;
import java.util.Map;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Str;

/**
 * DAO Factory.
 * ============
 *
 * Factory object used to retrieve objects from the database.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public abstract class Factory
{
    ///////////////////////////////////
    // Static Methods and Properties //
    ///////////////////////////////////
    /**
     * Database object.
     */
    protected static Connection _database;

    /**
     * Sets database object.
     *
     * @param database Database connection object.
     */
    public static void database(Connection database)
    {
        Factory._database = database;
    }

    /**
     * Returns database object.
     *
     * @return Database object.
     */
    public static Connection database()
    {
        return Factory._database;
    }

    ///////////////////////////////////////
    // Non Static Methods and Properties //
    ///////////////////////////////////////
    /**
     * Builder object.
     */
    protected Builder _builder;

    /**
     * Table name.
     */
    protected String _table;

    /**
     * Primary key name.
     */
    protected String _primaryKey = "id";

    /**
     * Finds an object from the database based on the given id.
     *
     * @param id Primary Key value.
     *
     * @return DAO instance.
     */
    public DAO find(int id)
    {
        return this.find(this.primaryKey(), id);
    }

    /**
     * Finds an object from the database based on the given id.
     *
     * @param column Column name.
     * @param value  Column value.
     *
     * @return DAO instance.
     */
    public DAO find(String column, Object value)
    {
        DAO object = null;

        try {
            ResultSet query = Factory.database().query("SELECT * FROM `"+ this.table() +"` WHERE `"+ column +"`=?", value);

            if(!query.isBeforeFirst()) {
                Console.println("DAO for `"+ this.table() +"` with `"+ column +"` "+ value.toString() +" not found!");

                return null;
            }

            object = this.build(query);
        } catch(Exception e) {
            // Ignore
        }

        return object;
    }

    /**
     * Finds an object from the database based on the given id.
     *
     * @param columns Column names and values.
     *
     * @return DAO instance.
     */
    public DAO find(Map<String, Object> columns)
    {
        DAO object = null;

        try {
            String query = "SELECT * FROM `"+ this.table() +"` WHERE `";
            query += Str.implode(columns.keySet(), "`=? AND `");

            ResultSet result = Factory.database().query(query, columns.values());

            if(!result.isBeforeFirst()) {
                Console.println("DAO for `"+ this.table() +"` not found. Query: "+ query);

                return null;
            }

            object = this.build(result);
        } catch(Exception e) {
            // Ignore
        }

        return object;
    }

    /**
     * Builds a DAO object from its ResultSet query.
     *
     * @param result Query result.
     *
     * @return DAO object.
     */
    public DAO build(ResultSet result)
    {
        return builder().build(result);
    }

    /**
     * Returns table name.
     *
     * @return Table name.
     */
    public String table()
    {
        return _table;
    }

    /**
     * Sets table name.
     *
     * @param table New table name.
     */
    public void table(String table)
    {
        _table = table;
    }

    /**
     * Returns primary key name.
     *
     * @return Primary key name.
     */
    public String primaryKey()
    {
        return _primaryKey;
    }

    /**
     * Sets primary key name.
     *
     * @param primaryKey New primary key name.
     */
    public void primaryKey(String primaryKey)
    {
        _primaryKey = primaryKey;
    }

    /**
     * Returns the builder object.
     *
     * @return Builder object.
     */
    public Builder builder()
    {
        return _builder;
    }

    /**
     * Sets the builder object.
     *
     * @param builder New builder object.
     */
    public void builder(Builder builder)
    {
        _builder = builder;
    }
}
