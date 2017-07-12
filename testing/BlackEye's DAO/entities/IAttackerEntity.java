package com.rikkabot.rikkabotcore.dao.entities;

/**
 * Attacker Entity interface.
 * ==========================
 *
 * Interface for all map entities that can perform attacks.
 *
 * This interface declares the following methods:
 *
 *  * `selectedAttackableEntity`.
 *  * `attack`.
 *  * `isAttacking`.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface IAttackerEntity extends IMapEntity
{
    /**
     * Sets selected IAttackableEntity.
     *
     * @param entity New IAttackableEntity.
     */
    void selectedAttackableEntity(IAttackableEntity entity);

    /**
     * Returns selected IAttackableEntity.
     *
     * @return Selected IAttackableEntity.
     */
    IAttackableEntity selectedAttackableEntity();

    /**
     * Sets whether this instance is attacking or not.
     *
     * @param status New attack status.
     */
    void isAttacking(boolean status);

    /**
     * Returns whether this instance is attacking or not.
     *
     * @return Attack status.
     */
    boolean isAttacking();

    /**
     * Attacks the selected IAttackableEntity.
     */
    void attack();

    /**
     * Stops the attack.
     */
    void stopAttack();
}
