package com.rikkabot.rikkabotcore.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Created by piotr on 12.07.17.
 */

@AllArgsConstructor @Accessors @Getter
public class Hero
{
    private String username;
    private String password;
    private String server;

    private int userId;
}