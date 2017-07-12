package com.rikkabot.rikkabotcore.dao.maps.portals;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.utils.Point;

import com.rikkabot.rikkabotcore.dao.entities.IMapEntity;
import com.rikkabot.rikkabotcore.dao.levels.Level;
import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.maps.Map;
import com.manulaiko.blackeye.net.game.commands.out.CreatePortalCommand;

/**
 * Portal DAO.
 * ===========
 *
 * Represents a row from the `maps_portals` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Portal extends DAO implements IMapEntity
{
    /**
     * Level needed to use this portal.
     */
    private Level _level = null;

    /**
     * Level ID.
     */
    private int _levelsID;

    /**
     * Map where the portal is located.
     */
    private Map _map = null;

    /**
     * Map ID of the portal.
     */
    private int _mapsID;

    /**
     * Position on map.
     */
    private Point _position;

    /**
     * Destination map.
     */
    private Map _targetMap = null;

    /**
     * Destination map ID.
     */
    private int _targetMapsID;

    /**
     * Destination position.
     */
    private Point _targetPosition;

    /**
     * Whether the portal is visible on map.
     */
    private boolean _isVisible = true;

    /**
     * Whether the portal is working or not.
     */
    private boolean _isWorking = true;

    /**
     * Graphic ID.
     */
    private int _gfx = 1;

    /**
     * Near entities.
     */
    private java.util.Map<Integer, IMapEntity> _nearEntities = new HashMap<>();

    /**
     * Constructor.
     *
     * @param id             Portal ID.
     * @param levelsID       Level needed to use this portal.
     * @param mapsID         Map where the portal is located.
     * @param position       Position on map.
     * @param targetMapsID   Destination map.
     * @param targetPosition Destination position.
     * @param isVisible      Whether the portal is visible on map.
     * @param isWorking      Whether the portal is working or not.
     * @param gfx            Graphic ID.
     */
    public Portal(
            int id, int levelsID, int mapsID, Point position, int targetMapsID,
            Point targetPosition, boolean isVisible, boolean isWorking, int gfx
    ) {
        super(id);

        this._levelsID       = levelsID;
        this._mapsID         = mapsID;
        this._position       = position;
        this._targetMapsID   = targetMapsID;
        this._targetPosition = targetPosition;
        this._isVisible      = isVisible;
        this._isWorking      = isWorking;
        this._gfx            = gfx;
    }

    /**
     * Builds and returns the CreatePortal command.
     *
     * @return CreatePortal command.
     */
    public CreatePortalCommand getCreatePortalCommand()
    {
        return new CreatePortalCommand(
                super.id(),
                this.gfx(),
                this.isWorking(),
                (int)this.position().x(),
                (int)this.position().y()
        );
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns portal level.
     *
     * @return Portal level.
     */
    public Level level()
    {
        if(this._level == null) {
            this.level(GameManager.levels.find(this.levelsID()));
        }

        return this._level;
    }

    /**
     * Returns entity ID.
     *
     * @return Entity ID.
     */
    @Override
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
     * Returns portal map.
     *
     * @return Portal map.
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
     * Returns portal targetMap.
     *
     * @return Portal targetMap.
     */
    public Map targetMap()
    {
        if(this._targetMap == null) {
            this.targetMap(GameManager.maps.find(this.targetMapsID()));
        }

        return this._targetMap;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
