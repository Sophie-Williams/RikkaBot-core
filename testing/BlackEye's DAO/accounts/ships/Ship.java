package com.rikkabot.rikkabotcore.dao.accounts.ships;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.utils.Point;

import com.rikkabot.rikkabotcore.dao.entities.IMapEntity;
import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.maps.Map;

/**
 * Ship DAO.
 * =========
 *
 * Represents a row from the `accounts_ships` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Ship extends DAO
{
    /**
     * Ship ID.
     */
    private int _shipsID;

    /**
     * Ship object.
     */
    private com.rikkabot.rikkabotcore.dao.ships.Ship _ship = null;

    /**
     * Map ID.
     */
    private int _mapsID;

    /**
     * Map object.
     */
    private Map _map = null;

    /**
     * Position on map.
     */
    private Point _position;

    /**
     * Health points.
     */
    private int _health;

    /**
     * Shield points.
     */
    private int _shield;

    /**
     * Nanohull points.
     */
    private int _nanohull;

    /**
     * Graphic ID.
     */
    private int _gfx;

    /**
     * Near ship entities.
     */
    private java.util.Map<Integer, IMapEntity> _nearEntities = new HashMap<>();

    /**
     * Whether the ship is moving or not.
     */
    private boolean _isMoving;

    /**
     * Target position.
     */
    private Point _targetPosition;

    /**
     * Constructor.
     *
     * @param id       Ship ID.
     * @param shipsID  Ship ID.
     * @param mapsID   Map ID.
     * @param position Position on map.
     * @param health   Health points.
     * @param shield   Shield points.
     * @param nanohull Nanohull points.
     * @param gfx      Graphic ID..
     */
    public Ship(
            int id, int shipsID, int mapsID, Point position,
            int health, int shield, int nanohull, int gfx
    ) {
        super(id);
        
        this._shipsID  = shipsID;
        this._mapsID   = mapsID;
        this._position = position;
        this._health   = health;
        this._shield   = shield;
        this._nanohull = nanohull;
        this._gfx      = gfx;
    }

    /**
     * Moves the ship.
     *
     * @param target Target position.
     */
    public void move(Point target)
    {
        this.targetPosition(target);
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns ship ship.
     *
     * @return Ship ship.
     */
    public com.rikkabot.rikkabotcore.dao.ships.Ship ship()
    {
        if(this._ship == null) {
            this.ship(GameManager.ships.find(this.shipsID()));
        }
        
        return this._ship;
    }

    /**
     * Returns map map.
     *
     * @return Ship map.
     */
    public Map map()
    {
        if(this._map == null) {
            this.map(GameManager.maps.find(this.mapsID()));
        }

        return this._map;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
