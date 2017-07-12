package com.rikkabot.rikkabotcore.dao.collectables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.utils.Point;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.entities.IMapEntity;
import com.rikkabot.rikkabotcore.dao.maps.Map;
import com.rikkabot.rikkabotcore.dao.rewards.Reward;
import com.manulaiko.blackeye.net.game.commands.out.CreateCollectableCommand;

/**
 * Collectable DAO.
 * =========
 *
 * Represents a row from the `collectables` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Collectable extends DAO implements IMapEntity
{
    /**
     * Collectable name.
     */
    private String _name = "";

    /**
     * Collectable graphic ID.
     */
    private int _gfx = 0;

    /**
     * Collectable type.
     */
    private int _type = 0;

    /**
     * Rewards to award when collected.
     */
    private List<Reward> _rewards = new ArrayList<>();

    /**
     * Map entity ID.
     */
    private int _entityID;

    /**
     * Position on map.
     */
    private Point _position;

    /**
     * Map instance.
     */
    private Map _map;

    /**
     * Near entities.
     */
    private java.util.Map<Integer, IMapEntity> _nearEntities = new HashMap<>();

    /**
     * Constructor.
     *
     * @param id      Collectable ID.
     * @param gfx     Graphic ID.
     * @param type    Collectable type.
     * @param name    Name.
     * @param rewards Rewards to award when collected.
     */
    public Collectable(int id, int gfx, int type, String name, List<Reward> rewards)
    {
        super(id);

        this._gfx     = gfx;
        this._type    = type;
        this._name    = name;
        this._rewards = rewards;
    }

    /**
     * Clones the collectable.
     *
     * @return Cloned collectable.
     */
    public Collectable clone()
    {
        Collectable c = new Collectable(
                super.id(),
                this.gfx(),
                this.type(),
                this.name(),
                this.rewards() // this should be read-only so I'm not going to waste effort in cloning this
                // hf when you try to add rewards to a collectable and you find out it's added everywhere :)
        );

        c.entityID(this.entityID());
        c.position(this.position().clone());

        return c;
    }

    /**
     * Builds and returns the CreateCollectable command.
     *
     * @return CreateCollectable command.
     */
    public CreateCollectableCommand getCreateCollectableCommand()
    {
        return new CreateCollectableCommand(
                this.entityID(),
                this.type(),
                this.gfx(),
                (int)this.position().x(),
                (int)this.position().y()
        );
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
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
     * Sets position.
     *
     * @param position New position.
     */
    @Override
    public void position(Point position)
    {
        this._position = position;
    }

    /**
     * Sets entity ID.
     *
     * @param entityID New entityID.
     */
    @Override
    public void entityID(int entityID)
    {
        this._entityID = entityID;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
