package com.rikkabot.rikkabotcore.dao.entities;

import com.manulaiko.tabitha.utils.Point;

/**
 * Movable Entity interface.
 * =========================
 *
 * Interface for the movable entities.
 *
 * As you can't move if you're not in a map, this interface extends *IMapEntity*.
 *
 * This interface declares the following methods:
 *
 *  * `isMoving`
 *  * `move`
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface IMovableEntity extends IMapEntity
{
    /**
     * Returns whether the entity is moving or not.
     *
     * @return Whether the entity is moving or not.
     */
    boolean isMoving();

    /**
     * Sets whether the entity is moving or not.
     *
     * @param isMoving New moving state.
     */
    void isMoving(boolean isMoving);

    /**
     * Performs the movement.
     *
     * @param destination Target position.
     * @param time        Time needed to reach destination.
     */
    void move(Point destination, long time);
}
