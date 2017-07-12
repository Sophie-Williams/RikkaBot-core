package com.rikkabot.rikkabotcore.dao.accounts.configurations;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.accounts.items.Item;
import com.rikkabot.rikkabotcore.dao.items.Item.Value;
import com.manulaiko.blackeye.net.game.commands.out.ChangeConfigurationCommand;

/**
 * Configuration DAO.
 * ==================
 * 
 * Represents a row from the `accounts_configurations` table.
 * 
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Configuration extends DAO
{
    /**
     * Laser slots.
     */
    private Map<Integer, Item> _lasers;
    
    /**
     * Hellstorms slots.
     */
    private Map<Integer, Item> _hellstorms;
    
    /**
     * Generators slots.
     */
    private Map<Integer, Item> _generators;
    
    /**
     * Extras slots.
     */
    private Map<Integer, Item> _extras;
    
    /**
     * Drones slots.
     */
    private Map<Integer, Item> _drones;

    /**
     * Configuration speed.
     */
    private int _speed = 0;

    /**
     * Configuration damage.
     */
    private int _damage = 0;

    /**
     * Configuration shield.
     */
    private int _shield = 0;

    /**
     * Configuration expansions.
     */
    private int _expansions = 0;

    /**
     * Configuration ID.
     */
    private int _configurationID = 1;

    /**
     * Constructor.
     *
     * @param id         Entity ID.
     * @param lasers     Laser slots.
     * @param hellstorms Hellstorms slots.
     * @param generators Generators slots.
     * @param extras     Extras slots.
     * @param drones     Drones slots.
     */
    public Configuration(
            int id, int configurationID, Map<Integer, Item> lasers, Map<Integer, Item> hellstorms,
            Map<Integer, Item> generators, Map<Integer, Item> extras, Map<Integer, Item> drones
    ) {
        super(id);

        this._configurationID = configurationID;
        this._lasers          = lasers;
        this._hellstorms      = hellstorms;
        this._generators      = generators;
        this._extras          = extras;
        this._drones          = drones;

        this.calculate();
    }

    /**
     * Calculates configuration's values.
     */
    public void calculate()
    {
        int damage       = 0;
        int speed        = 0;
        int shield       = 0;
        int eliteLasers  = 0;

        for(Item i : this.lasers().values()) {
            if(i.item().isElite()) {
                eliteLasers++;
            }

            for(Value v : i.item().values()) {
                if(v.type() == Value.Type.DAMAGE) {
                    damage = (int)v.apply(damage);
                }
            }
        }

        for(Item i : this.generators().values()) {
            for(Value v : i.item().values()) {
                if(v.type() == Value.Type.SPEED) {
                    speed = (int)v.apply(speed);
                }

                if(v.type() == Value.Type.SHIELD) {
                    shield = (int)v.apply(shield);
                }
            }
        }

        for(Item i : this.drones().values()) {
            for(Value v: i.item().values()) {
                if(v.type() == Value.Type.DAMAGE) {
                    damage = (int)v.apply(damage);
                }

                if(v.type() == Value.Type.SHIELD) {
                    shield = (int)v.apply(shield);
                }
            }
        }

        int expansions = 1;
        if(eliteLasers == this.lasers().size()) {
            expansions = 3;
        } else if(eliteLasers <= (this.lasers().size() / 2)) {
            expansions = 2;
        }

        this.speed(speed);
        this.shield(shield);
        this.damage(damage);
        this.expansions(expansions);
    }

    /**
     * Builds and returns the ChangeConfiguration command.
     *
     * @return ChangeConfiguration command.
     */
    public ChangeConfigurationCommand getChangeConfigurationCommand()
    {
        return new ChangeConfigurationCommand(this.configurationID());
    }
}
