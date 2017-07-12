package com.rikkabot.rikkabotcore.dao.entities;

import com.manulaiko.blackeye.net.game.commands.out.*;

/**
 * Ship entity interface.
 * ======================
 *
 * Interface for map ship entities.
 *
 * As a ship must be located on a map, it extends the *IMapEntity* interface.
 *
 * This interface defines the following methods:
 *
 *  * `name`.
 *  * `getShipCreateCommand`.
 *  * `getShipRemoveCommand`.
 *  * `getShipDestroyCommand`.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface IShipEntity extends IMapEntity
{
    /**
     * Builds and returns the ShipCreate command.
     *
     * @return ShipCreate command.
     */
    ShipCreateCommand getShipCreateCommand();

    /**
     * Builds and returns the ShipRemove command.
     *
     * @return ShipRemove command.
     */
    ShipRemoveCommand getShipRemoveCommand();

    /**
     * Builds and returns the ShipDestroy command.
     *
     * @return ShipDestroy command.
     */
    ShipDestroyCommand getShipDestroyCommand();

    /**
     * Builds and returns the ShipMovement command.
     *
     * @return ShipMovement command.
     */
    ShipMovementCommand getShipMovementCommand();

    /**
     * Builds and returns the DronesInit command.
     *
     * @return DronesInit command.
     */
    DronesInitCommand getDronesInitCommand();
}
