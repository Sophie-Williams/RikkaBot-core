package com.rikkabot.rikkabotcore.dao.items;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.DAO;

/**
 * Item DAO.
 * =========
 *
 * Represents a row from the `items` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Item extends DAO
{
    /////////////////////
    // Start constants //
    /////////////////////
    public static final int LCB_10  = 59;
    public static final int MCB_25  = 60;
    public static final int MCB_50  = 61;
    public static final int SAB_50  = 63;
    public static final int UCB_100 = 64;

    public static final int ACM_100  = 65;
    public static final int R_310    = 87;
    public static final int PLT_2020 = 0; //TODO add PLT-2020 rockets to the database.
    public static final int PLT_2021 = 84;

    public static final int FLAX = 199;
    public static final int IRIS = 200;

    public static final int XENOMIT = 241;
    ///////////////////
    // End constants //
    ///////////////////

    /**
     * Item name.
     */
    private String _name = "";

    /**
     * Item category.
     */
    private String _category = "";

    /**
     * Item description.
     */
    private String _description = "";

    /**
     * Item price.
     */
    private int _price = 0;

    /**
     * Item type.
     */
    private String _type = "";

    /**
     * Whether the item is elite or not.
     */
    private boolean _isElite = false;

    /**
     * Whether the item is buyable or not.
     */
    private boolean _isBuyable = false;

    /**
     * Whether the item is an event item or not.
     */
    private boolean _isEvent = false;

    /**
     * Item values.
     */
    private List<Value> _values;

    /**
     * Constructor.
     *
     * @param id          Item ID.
     * @param name        Item name.
     * @param category    Item category.
     * @param description Item description.
     * @param price       Item price.
     * @param type        Item type.
     * @param isElite     Whether the item is elite or not.
     * @param isBuyable   Whether the item is buyable or not.
     * @param isEvent     Whether the item is event or not.
     * @param values      Item values.
     */
    public Item(
            int id, String name, String category, String description,
            int price, String type, boolean isElite, boolean isBuyable, boolean isEvent,
            List<Value> values
    ) {
        super(id);

        this._name        = name;
        this._category    = category;
        this._description = description;
        this._price       = price;
        this._type        = type;
        this._isElite     = isElite;
        this._isBuyable   = isBuyable;
        this._isEvent     = isEvent;
        this._values      = values;
    }

    /**
     * Item value.
     * ===========
     *
     * Class that represents the item value.
     *
     * @author Manulaiko <manulaiko@gmail.com>
     */
    @Accessors @Data @AllArgsConstructor
    public static class Value
    {
        /////////////////
        // Start enums //
        /////////////////
        public enum Type
        {
            DAMAGE,
            HEALTH,
            SPEED,
            SHIELD,
            CARGO,
            AMMUNITION,
            EXPERIENCE,
            HONOR,
            CREDITS,
            URIDIUM,
            JACKPOT
        }

        public enum Operation
        {
            MINUS,
            PLUS,
            MULTIPLY,
            DIVIDE,
            MINUS_PERCENTAGE,
            PLUS_PERCENTAGE,
            MULTIPLY_PERCENTAGE,
            DIVIDE_PERCENTAGE
        }
        ///////////////
        // End enums //
        ///////////////

        /**
         * Value unit.
         */
        private double _value;

        /**
         * Value type;
         */
        private Type _type;

        /**
         * Value operation.
         */
        private Operation _operation;

        /**
         * Applies the value to a given param.
         *
         * @param param Param to apply the value.
         *
         * @return Param with value applied.
         */
        public double apply(double param)
        {
            double v = this.value(); // shortcut.

            switch(this.operation())
            {
                case DIVIDE:
                    return param / v;

                case MINUS:
                    return param - v;

                case PLUS:
                    return param + v;

                case MULTIPLY:
                    return param * v;

                case MINUS_PERCENTAGE:
                    return param - ((param * v) / 100);

                case PLUS_PERCENTAGE:
                    return param + ((param * v) / 100);

                case MULTIPLY_PERCENTAGE:
                    return param * ((param * v) / 100);

                case DIVIDE_PERCENTAGE:
                    return param / ((param * v) / 100);
            }

            return param;
        }
    }
}
