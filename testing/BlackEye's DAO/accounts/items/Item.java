package com.rikkabot.rikkabotcore.dao.accounts.items;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.levels.Level;

/**
 * Item DAO.
 * =========
 *
 * Represents a row from the `accounts_items` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Item extends DAO
{
    /**
     * The item id.
     */
    private int _itemsID;

    /**
     * The item object.
     */
    private com.rikkabot.rikkabotcore.dao.items.Item _item = null;

    /**
     * Item level id.
     */
    private int _levelsID;

    /**
     * Item level object.
     */
    private Level _level = null;

    /**
     * Amount of items.
     */
    private int _amount;

    /**
     * Constructor.
     *
     * @param id       Item ID.
     * @param itemsID  The item ID.
     * @param levelsID Item level ID.
     * @param amount   Amount of items.
     */
    public Item(int id, int itemsID, int levelsID, int amount)
    {
        super(id);

        this._itemsID  = itemsID;
        this._levelsID = levelsID;
        this._amount   = amount;
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns item item.
     *
     * @return Item item.
     */
    public com.rikkabot.rikkabotcore.dao.items.Item item()
    {
        if(this._item == null) {
            this.item(GameManager.items.find(this.itemsID()));
        }

        return this._item;
    }

    /**
     * Returns item level.
     *
     * @return Item level.
     */
    public Level level()
    {
        if(this._level == null) {
            this.level(GameManager.levels.find(this.levelsID()));
        }

        return this._level;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
