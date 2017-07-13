package com.rikkabot.rikkabotcore.dao.hero;

import com.rikkabot.rikkabotcore.dao.DAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Created by piotr on 12.07.17.
 */

@AllArgsConstructor @Accessors @Getter
public class Hero extends DAO
{
    private String username;
    private String password;
    private String server;

    private int userId;
}
