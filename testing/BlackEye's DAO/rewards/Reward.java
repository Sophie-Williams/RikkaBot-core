package com.rikkabot.rikkabotcore.dao.rewards;

import lombok.Data;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.items.Item;
import com.manulaiko.blackeye.launcher.GameManager;

/**
 * Reward DAO.
 * ===========
 *
 * Represents a rewards from the DataBase.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class Reward
{
    /**
     * Reward item.
     */
    private Item _item = null;

    /**
     * Item ID.
     */
    private int _itemsID = 1;

    /**
     * Probability to award item.
     */
    private double _probability = 100D;

    /**
     * Amount of `item` to award.
     */
    private int _amount = 1;

    /**
     * Constructor.
     *
     * @param itemsID     Item to award.
     * @param probability Probability to award item.
     * @param amount      Amount of `item` to award.
     */
    public Reward(int itemsID, double probability, int amount)
    {
        this._itemsID     = itemsID;
        this._probability = probability;
        this._amount      = amount;
    }

    /**
     * Returns the item.
     *
     * @return The item object.
     */
    public Item item()
    {
        if(this._item == null) {
            this.item(GameManager.items.find(this.itemsID()));
        }

        return this._item;
    }
}
