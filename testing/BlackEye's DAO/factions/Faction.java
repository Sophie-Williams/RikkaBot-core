package com.rikkabot.rikkabotcore.dao.factions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.utils.Point;

import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.maps.Map;

/**
 * Faction DAO.
 * ============
 *
 * Represents a row from the `factions` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Faction extends DAO
{
    /**
     * Faction name.
     */
    private String _name;

    /**
     * Faction tag.
     */
    private String _tag;

    /**
     * Faction description.
     */
    private String _description;

    /**
     * Whether the faction is public or not.
     */
    private boolean _isPublic;

    /**
     * Lower map.
     */
    private Map _lowMap = null;

    /**
     * Lower map ID.
     */
    private int _lowMapsID;

    /**
     * Lower map position.
     */
    private Point _lowMapPosition;


    /**
     * Higher map.
     */
    private Map _highMap = null;

    /**
     * Higher map ID.
     */
    private int _highMapsID;

    /**
     * Higher map position.
     */
    private Point _highMapPosition;

    /**
     * Constructor.
     *
     * @param id Faction ID.
     */
    public Faction(
            int id, String name, String tag, String description, boolean isPublic,
            int lowMapsID, Point lowMapPosition, int highMapsID, Point highMapPosition
    ) {
        super(id);
        
        this._name            = name;
        this._tag             = tag;
        this._description     = description;
        this._isPublic        = isPublic;
        this._lowMapsID       = lowMapsID;
        this._lowMapPosition  = lowMapPosition;
        this._highMapsID      = highMapsID;
        this._highMapPosition = highMapPosition;
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns faction lowMap.
     *
     * @return Faction lowMap.
     */
    public Map lowMap()
    {
        if(this._lowMap == null) {
            this.lowMap(GameManager.maps.find(this.lowMapsID()));
        }

        return this._lowMap;
    }

    /**
     * Returns faction highMap.
     *
     * @return Faction highMap.
     */
    public Map highMap()
    {
        if(this._highMap == null) {
            this.highMap(GameManager.maps.find(this.highMapsID()));
        }

        return this._highMap;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
