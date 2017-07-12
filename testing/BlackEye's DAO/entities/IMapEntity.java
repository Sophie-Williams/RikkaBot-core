package com.rikkabot.rikkabotcore.dao.entities;

import com.manulaiko.tabitha.utils.Point;

import com.rikkabot.rikkabotcore.dao.maps.Map;

/**
 * Map Entity interface.
 * =====================
 *
 * Interface for all map entities.
 *
 * This interface defines the following methods:
 *
 *  * `entityID`.
 *  * `map`.
 *  * `position`.
 *  * `nearEntities`.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface IMapEntity
{
    /**
     * Returns entity ID.
     *
     * @return Entity ID.
     */
    int entityID();

    /**
     * Sets entity ID.
     *
     * @param id Entity ID.
     */
    void entityID(int id);

    /**
     * Returns map.
     *
     * @return Entity's map.
     */
    Map map();

    /**
     * Sets map.
     *
     * @param map New entity map.
     */
    void map(Map map);

    /**
     * Returns entity position.
     *
     * @return Entity position on map.
     */
    Point position();

    /**
     * Sets entity position.
     *
     * @param position New position on map.
     */
    void position(Point position);

    /**
     * Returns near entities.
     *
     * @return Near entities.
     */
    java.util.Map<Integer, IMapEntity> nearEntities();

    /**
     * Sets near entities.
     *
     * @param entities New near entities.
     */
    void nearEntities(java.util.Map<Integer, IMapEntity> entities);
}
