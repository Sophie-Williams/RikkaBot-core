package com.rikkabot.rikkabotcore.dao.ships;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.items.Item;
import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.rewards.Reward;

/**
 * Ship DAO.
 * =========
 *
 * Represents a row from the `ships` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Ship extends DAO
{
    /**
     * Item's ID.
     */
    private int _itemsID;

    /**
     * Ship item.
     */
    private Item _item = null;

    /**
     * Health points.
     */
    private int _health;

    /**
     * Speed points.
     */
    private int _speed;

    /**
     * Cargo space.
     */
    private int _cargo;

    /**
     * Batteries space.
     */
    private int _batteries;

    /**
     * Rockets space.
     */
    private int _rockets;

    /**
     * Laser slots.
     */
    private int _lasers;

    /**
     * Hellstorm slots.
     */
    private int _hellstorms;

    /**
     * Generator slots.
     */
    private int _generators;

    /**
     * Extra slots.
     */
    private int _extras;

    /**
     * Rewards to award when destroying this ship.
     */
    private List<Reward> _rewards;

    /**
     * Graphic ID.
     */
    private int _gfx;
    
    /**
     * Constructor.
     *
     * @param id         Ship ID.
     * @param itemsID    Item's ID.
     * @param health     Health points.
     * @param speed      Speed points.
     * @param cargo      Cargo space.
     * @param batteries  Batteries space.
     * @param rockets    Rockets space.
     * @param lasers     Lasers slots.
     * @param hellstorms Hellstorms slots.
     * @param generators Generator slots.
     * @param extras     Extra slots.
     * @param rewards    Rewards to award when destroying this ship.
     * @param gfx        Graphic ID.
     */
    public Ship(
            int id, int itemsID, int health, int speed, int cargo, int batteries,int rockets,
            int lasers, int hellstorms, int generators, int extras, List<Reward> rewards, int gfx
    ) {
        super(id);

        this._itemsID    = itemsID;
        this._health     = health;
        this._speed      = speed;
        this._cargo      = cargo;
        this._batteries  = batteries;
        this._rockets    = rockets;
        this._lasers     = lasers;
        this._hellstorms = hellstorms;
        this._generators = generators;
        this._extras     = extras;
        this._rewards    = rewards;
        this._gfx        = gfx;
    }

    /**
     * Returns ship item.
     *
     * @return Ship item.
     */
    public Item item()
    {
        if(this._item == null) {
            this.item(GameManager.items.find(this.itemsID()));
        }

        return this._item;
    }
}
