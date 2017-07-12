package com.rikkabot.rikkabotcore.dao.maps.stations;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.utils.Point;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.entities.IMapEntity;
import com.rikkabot.rikkabotcore.dao.factions.Faction;
import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.maps.Map;
import com.manulaiko.blackeye.net.game.commands.out.CreateStationCommand;

/**
 * Station DAO.
 * ============
 *
 * Represents a row from the `maps_stations` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper=true)
public class Station extends DAO implements IMapEntity
{
    /**
     * Position on map.
     */
    private Point _position;

    /**
     * Station map.
     */
    private Map _map = null;

    /**
     * Map's ID.
     */
    private int _mapsID;

    /**
     * Station faction.
     */
    private Faction _faction = null;

    /**
     * Faction's ID.
     */
    private int _factionsID;

    /**
     * Near entities.
     */
    private java.util.Map<Integer, IMapEntity> _nearEntities = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param id         Station ID.
     * @param position   Position on map
     * @param mapsID     Map's ID.
     * @param factionsID Faction's ID.
     */
    public Station(int id, Point position, int mapsID, int factionsID)
    {
        super(id);
        
        this._position   = position;
        this._mapsID     = mapsID;
        this._factionsID = factionsID;
    }

    /**
     * Builds and returns the CreateStation command.
     *
     * @return CreateStation command.
     */
    public CreateStationCommand getCreateStationCommand()
    {
        return new CreateStationCommand(
                super.id(),
                1,
                this.faction().tag() + super.id(),
                this.factionsID(),
                true,
                (int)this.position().x(),
                (int)this.position().y()
        );
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns entity ID.
     *
     * @return Entity ID.
     */
    public int entityID()
    {
        return super.id();
    }

    /**
     * Sets entity ID.
     *
     * @param id Entity ID.
     */
    @Override
    public void entityID(int id)
    {
        super.id(id);
    }

    /**
     * Returns station map.
     *
     * @return Station map.
     */
    public Map map()
    {
        if(this._map == null) {
            this.map(GameManager.maps.find(this.mapsID()));
        }

        return this._map;
    }

    /**
     * Sets map.
     *
     * @param map New entity map.
     */
    @Override
    public void map(Map map)
    {
        this._map = map;
    }

    /**
     * Sets entity position.
     *
     * @param position New position on map.
     */
    @Override
    public void position(Point position)
    {
        this._position = position;
    }

    /**
     * Sets near entities.
     *
     * @param entities New near entities.
     */
    @Override
    public void nearEntities(java.util.Map<Integer, IMapEntity> entities)
    {
        this._nearEntities = entities;
    }

    /**
     * Returns station faction.
     *
     * @return Station faction.
     */
    public Faction faction()
    {
        if(this._faction == null) {
            this.faction(GameManager.factions.find(this.factionsID()));
        }

        return this._faction;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
