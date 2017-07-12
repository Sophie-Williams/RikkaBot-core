package com.rikkabot.rikkabotcore.dao.npcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.utils.Point;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.entities.IAttackableEntity;
import com.rikkabot.rikkabotcore.dao.entities.IAttackerEntity;
import com.rikkabot.rikkabotcore.dao.entities.IMapEntity;
import com.rikkabot.rikkabotcore.dao.entities.IShipEntity;
import com.rikkabot.rikkabotcore.dao.rewards.Reward;
import com.manulaiko.blackeye.net.game.commands.out.*;

/**
 * NPC DAO.
 * ========
 *
 * Represents a row from the `npcs` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class NPC extends DAO implements IShipEntity, IAttackableEntity, IAttackerEntity
{
    /**
     * NPC name.
     */
    private String _name = "";

    /**
     * Max health points.
     */
    private int _maxHealth = 0;

    /**
     * Current health points.
     */
    private int _health = 0;

    /**
     * Max shield points.
     */
    private int _maxShield = 0;

    /**
     * Current shield points.
     */
    private int _shield = 0;

    /**
     * Shield absorption rate.
     */
    private double _shieldAbsorption = 0D;

    /**
     * Damage points.
     */
    private int _damage = 0;

    /**
     * Speed points.
     */
    private int _speed = 0;

    /**
     * Rewards to award when destroyed.
     */
    private List<Reward> _rewards = new ArrayList<>();

    /**
     * Graphic ID.
     */
    private int _gfx = 0;

    /**
     * AI type.
     */
    private int _ai = 0;

    /**
     * Position on map.
     */
    private Point _position = new Point(0, 0);

    /**
     * Map entity ID.
     */
    private int _entityID;

    /**
     * Near ship entities.
     */
    private Map<Integer, IMapEntity> _nearEntities = new HashMap<>();

    /**
     * Map instance.
     */
    private com.rikkabot.rikkabotcore.dao.maps.Map _map;

    /**
     * Selected IAttackableEntity.
     */
    private IAttackableEntity _selectedAttackableEntity;

    /**
     * Whether the account is attacking or not.
     */
    private boolean _isAttacking;

    /**
     * Whether the account can be attacked or not.
     */
    private boolean _canBeAttacked;

    /**
     * Whether the account is under attack or not.
     */
    private boolean _isUnderAttack;

    /**
     * Attacker that is attacking this instance.
     */
    private IAttackerEntity _attacker;

    /**
     * Constructor.
     *
     * @param id               NPC ID.
     * @param name             NPC name.
     * @param health           Max health point.
     * @param shield           Max shield points.
     * @param shieldAbsorption Shield absorption rate.
     * @param damage           Damage points.
     * @param speed            Speed points.
     * @param rewards          Rewards to award when destroyed.
     * @param gfx              Graphic ID.
     * @param ai               AI type.
     */
    public NPC(
            int id, String name, int health, int shield, double shieldAbsorption,
            int damage, int speed, List<Reward> rewards, int gfx, int ai
    ) {
        super(id);

        this._name             = name;
        this._maxHealth        = health;
        this._health           = health;
        this._maxShield        = shield;
        this._shield           = shield;
        this._shieldAbsorption = shieldAbsorption;
        this._damage           = damage;
        this._speed            = speed;
        this._rewards          = rewards;
        this._gfx              = gfx;
        this._ai               = ai;
    }

    /**
     * Clones the NPC.
     *
     * @return Cloned NPC.
     */
    @Override
    public NPC clone()
    {
        NPC npc = new NPC(
                super.id(),
                this.name(),
                this.health(),
                this.shield(),
                this.shieldAbsorption(),
                this.damage(),
                this.speed(),
                this.rewards(),
                this.gfx(),
                this.ai()
        );

        npc.position(this.position().clone());
        npc.entityID(this.entityID());

        return npc;
    }

    /**
     * Builds and returns the ShipCreate command.
     *
     * @return ShipCreate command.
     */
    public ShipCreateCommand getShipCreateCommand()
    {
        return new ShipCreateCommand(
                this.entityID(),
                this.gfx(),
                (int)this.position().x(),
                (int)this.position().y(),
                0,
                0,
                0,
                0,
                0,
                0,
                false,
                false,
                true,
                this.name(),
                ""
        );
    }

    /**
     * Builds and returns the ShipRemove command.
     *
     * @return ShipRemove command.
     */
    @Override
    public ShipRemoveCommand getShipRemoveCommand()
    {
        return new ShipRemoveCommand(this.entityID());
    }

    /**
     * Builds and returns the ShipDestroy command.
     *
     * @return ShipDestroy command.
     */
    @Override
    public ShipDestroyCommand getShipDestroyCommand()
    {
        return new ShipDestroyCommand(this.entityID());
    }

    /**
     * Builds and returns the ShipMovement command.
     *
     * @return ShipMovement command.
     */
    @Override
    public ShipMovementCommand getShipMovementCommand()
    {
        return new ShipMovementCommand(
                super.id(),
                (int)this.position().x(),
                (int)this.position().y(),
                0
        );
    }

    /**
     * Builds and returns the DronesInit command.
     *
     * @return DronesInit command.
     */
    @Override
    public DronesInitCommand getDronesInitCommand()
    {
        return new DronesInitCommand(this.entityID(), new ArrayList<>());
    }

    /**
     * Builds and returns the SelectShip command.
     *
     * @return SelectShip command.
     */
    @Override
    public SelectShipCommand getSelectShipCommand()
    {
        return new SelectShipCommand(
                this.entityID(),
                this.name(),
                this.health(),
                this.maxHealth(),
                this.shield(),
                this.maxShield()
        );
    }

    /**
     * Attacks the selected IAttackableEntity.
     */
    @Override
    public void attack()
    {

    }

    /**
     * Stops the attack.
     */
    @Override
    public void stopAttack()
    {

    }

    /**
     * Destroys this instance.
     */
    @Override
    public void destroy()
    {

    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Sets near entities.
     *
     * @param entities New near entities.
     */
    public void nearEntities(Map<Integer, IMapEntity> entities)
    {
        this._nearEntities = entities;
    }

    /**
     * Sets position.
     *
     * @param position New position.
     */
    public void position(Point position)
    {
        this._position = position;
    }

    /**
     * Sets map.
     *
     * @param map New map.
     */
    public void map(com.rikkabot.rikkabotcore.dao.maps.Map map)
    {
        this._map = map;
    }

    /**
     * Sets entity ID.
     *
     * @param entityID New entity ID.
     */
    public void entityID(int entityID)
    {
        this._entityID = entityID;
    }

    /**
     * Sets selected IAttackableEntity.
     *
     * @param entity New IAttackableEntity.
     */
    @Override
    public void selectedAttackableEntity(IAttackableEntity entity)
    {
        this._selectedAttackableEntity = entity;
    }

    /**
     * Sets whether this instance is attacking or not.
     *
     * @param status New attack status.
     */
    @Override
    public void isAttacking(boolean status)
    {
        this._isAttacking = status;
    }

    /**
     * Sets whether this instance is under attack or not.
     *
     * @param status New attack status.
     */
    @Override
    public void isUnderAttack(boolean status)
    {
        this._isUnderAttack = status;
    }

    /**
     * Sets whether this instance can be attacked or not.
     *
     * @param status New attackable status.
     */
    @Override
    public void canBeAttacked(boolean status)
    {
        this._canBeAttacked = status;
    }

    /**
     * Sets the attacker that is attacking this instance.
     *
     * @param attacker New attacker.
     */
    @Override
    public void attacker(IAttackerEntity attacker)
    {
        this._attacker = attacker;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
