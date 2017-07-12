package com.rikkabot.rikkabotcore.dao.levels;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.DAO;

/**
 * Level DAO.
 * ==========
 *
 * Represents a row from the `levels` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Level extends DAO
{
    /**
     * Account experience.
     */
    private long _account = 0L;

    /**
     * Drone experience.
     */
    private int _drone = 0;

    /**
     * Pet experience.
     */
    private int _pet = 0;

    /**
     * Damage bonus.
     */
    private double _damage = 0D;

    /**
     * Shield bonus.
     */
    private double _shield = 0D;

    /**
     * Constructor.
     *
     * @param id      Level ID.
     * @param account Account experience.
     * @param drone   Drone experience.
     * @param pet     Pet experience.
     * @param damage  Damage bonus.
     * @param shield  Shield bonus.
     */
    public Level(int id, long account, int drone, int pet, double damage, double shield)
    {
        super(id);

        this._account = account;
        this._drone   = drone;
        this._pet     = pet;
        this._damage  = damage;
        this._shield  = shield;
    }
}
