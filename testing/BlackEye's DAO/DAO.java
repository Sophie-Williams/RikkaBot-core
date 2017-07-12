package com.rikkabot.rikkabotcore.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * DAO Object.
 * ===========
 *
 * Represents an object from the DataBase.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @AllArgsConstructor
public class DAO
{
    /**
     * Entity ID.
     */
    private int _id = 0;
}
