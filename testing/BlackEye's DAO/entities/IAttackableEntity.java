package com.rikkabot.rikkabotcore.dao.entities;

import com.manulaiko.blackeye.net.game.commands.out.SelectShipCommand;

/**
 * Attackable Entity interface.
 * ============================
 *
 * Interface for the map entities that can be attacked.
 *
 * This interface defines the following methods:
 *
 *  * `destroy`.
 *  * `isUnderAttack`.
 *  * `canBeAttacked`.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface IAttackableEntity extends IMapEntity
{
    /**
     * Sets whether this instance is under attack or not.
     *
     * @param status New attack status.
     */
    void isUnderAttack(boolean status);

    /**
     * Returns whether this instance is under attack or not.
     *
     * @return Attack status.
     */
    boolean isUnderAttack();

    /**
     * Sets whether this instance can be attacked or not.
     *
     * @param status New attackable status.
     */
    void canBeAttacked(boolean status);

    /**
     * Returns whether this instance can be attacked or not.
     *
     * @return Attackable status.
     */
    boolean canBeAttacked();

    /**
     * Sets the attacker that is attacking this instance.
     *
     * @param attacker New attacker.
     */
    void attacker(IAttackerEntity attacker);

    /**
     * Returns the attacker that is attacking this instance.
     *
     * @return Attacker instance.
     */
    IAttackerEntity attacker();

    /**
     * Builds and returns the SelectShip command.
     *
     * @return SelectShip command.
     */
    SelectShipCommand getSelectShipCommand();

    /**
     * Destroys this instance.
     */
    void destroy();
}
