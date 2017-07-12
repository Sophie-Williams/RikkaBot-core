package com.rikkabot.rikkabotcore.dao.accounts.hangars;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.accounts.configurations.Configuration;
import com.rikkabot.rikkabotcore.dao.accounts.ships.Ship;

/**
 * Hangar DAO.
 * ===========
 * 
 * Represents a row from the `accounts_hangars` table.
 * 
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Hangar extends DAO
{
    /**
     * Ship id.
     */
    private int _shipsID;

    /**
     * Ship object.
     */
    private Ship _ship = null;

    /**
     * Configuration ID.
     */
    private int _configurationsID;

    /**
     * Configuration object.
     */
    private Configuration _configuration = null;

    /**
     * Available configurations.
     */
    private Map<Integer, Configuration> _configurations = null;
    
    /**
     * Constructor.
     *
     * @param id               Entity ID.
     * @param shipsID          Ship ID.
     * @param configurationsID Configuration ID.
     */
    public Hangar(int id, int shipsID, int configurationsID)
    {
        super(id);
        
        this._shipsID          = shipsID;
        this._configurationsID = configurationsID;
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns hangar's speed (configuration speed + ship speed + boosters).
     *
     * @return Hangar's speed.
     */
    public int speed()
    {
        int speed = this.ship().ship().speed();
        speed += this.configuration().speed();

        //TODO boosters.

        return speed;
    }

    /**
     * Returns hangar's damage (configuration damage + boosters).
     *
     * @return Hangar's damage.
     */
    public int damage()
    {
        int damage = this.configuration().damage();

        //TODO boosters.

        return damage;
    }

    /**
     * Returns hangar's shield (configuration shield + boosters).
     *
     * @return Hangar's shield.
     */
    public int shield()
    {
        int shield = this.configuration().shield();

        //TODO boosters.

        return shield;
    }

    /**
     * Returns hangar ship.
     *
     * @return Hangar ship.
     */
    public Ship ship()
    {
        if(this._ship == null) {
            this.ship(GameManager.accounts.ships().find(this.shipsID()));
        }
        
        return this._ship;
    }

    /**
     * Returns hangar configuration.
     *
     * @return Hangar configuration.
     */
    public Configuration configuration()
    {
        if(this._configuration == null) {
            this.configuration(GameManager.accounts.configurations().find(this.configurationsID()));
        }

        return this._configuration;
    }

    /**
     * Returns hangar configurations.
     *
     * @return Hangar configurations.
     */
    public Map<Integer, Configuration> configurations()
    {
        if(this._configurations == null) {
            this.configurations(GameManager.accounts.configurations().byHangarsID(super.id()));
        }

        return this._configurations;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
