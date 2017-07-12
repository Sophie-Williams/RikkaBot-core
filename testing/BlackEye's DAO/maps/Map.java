package com.rikkabot.rikkabotcore.dao.maps;

import java.util.List;
import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.utils.Point;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.collectables.Collectable;
import com.rikkabot.rikkabotcore.dao.entities.IShipEntity;
import com.rikkabot.rikkabotcore.dao.factions.Faction;
import com.rikkabot.rikkabotcore.dao.maps.portals.Portal;
import com.rikkabot.rikkabotcore.dao.maps.stations.Station;
import com.rikkabot.rikkabotcore.dao.npcs.NPC;
import com.manulaiko.blackeye.launcher.GameManager;
import com.manulaiko.blackeye.net.game.GameConnection;
import com.manulaiko.blackeye.net.game.commands.out.MapInitCommand;
import com.manulaiko.blackeye.net.game.commands.out.ShipCreateCommand;

/**
 * Map DAO.
 * ========
 *
 * Represents a row from the `maps` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Map extends DAO
{
    /////////////////////
    // Start constants //
    /////////////////////
    public static final int MAX_RANGE = 1000;
    ///////////////////
    // End constants //
    ///////////////////

    /**
     * Map name.
     */
    private String _name = "";

    /**
     * Map stations.
     */
    private List<Station> _stations;

    /**
     * Map NPCs.
     */
    private java.util.Map<Integer, NPC> _npcs;

    /**
     * Map collectables.
     */
    private List<Collectable> _collectables;

    /**
     * Map portals.
     */
    private List<Portal> _portals;

    /**
     * Whether the map is a PVP map or not.
     */
    private boolean _isPVP = false;

    /**
     * Whether the map is a starter map or not.
     */
    private boolean _isStarter = false;

    /**
     * Map faction.
     */
    private Faction _faction = null;

    /**
     * Faction's ID.
     */
    private int _factionsID = 1;

    /**
     * Map scale factor.
     */
    private int _scaleFactor = 1;

    /**
     * Connections in the map.
     */
    private java.util.Map<Integer, GameConnection> _connections = new HashMap<>();

    /**
     * Constructor.
     *
     * @param id          Map ID.
     * @param name        Map name.
     * @param isPVP       Whether the map is a PVP map or not.
     * @param isStarter   Whether the map is a starter map or not.
     * @param factionsID  Faction's ID.
     * @param scaleFactor Map scale factor.
     */
    public Map(int id, String name, boolean isPVP, boolean isStarter, int factionsID, int scaleFactor)
    {
        super(id);

        this._name        = name;
        this._isPVP       = isPVP;
        this._isStarter   = isStarter;
        this._factionsID  = factionsID;
        this._scaleFactor = scaleFactor;
    }

    /**
     * Adds a new connection to the map.
     *
     * @param gameConnection Game connection.
     */
    public void addConnection(GameConnection gameConnection)
    {
        if(this.connections().containsKey(gameConnection.account().id())) {
            this.removeConnection(gameConnection);
        }

        gameConnection.send(this.getMapInitCommand());

        this.stations().forEach((s)->{
            gameConnection.send(s.getCreateStationCommand());
        });
        this.portals().forEach((p)->{
            gameConnection.send(p.getCreatePortalCommand());
        });
        this.collectables().forEach((c)->{
            gameConnection.send(c.getCreateCollectableCommand());
        });

        this.npcs().forEach((k, n)->{
            this._sendShip(n, gameConnection);
        });
        this.connections().forEach((k, v)->{
            this._sendShip(v.account(), gameConnection);
            this._sendShip(gameConnection.account(), v);
        });

        this.connections().put(gameConnection.account().id(), gameConnection);
    }

    /**
     * Removes a connection from the map.
     *
     * @param gameConnection Connection to remove.
     */
    private void removeConnection(GameConnection gameConnection)
    {
        if(!this.connections().containsKey(gameConnection.account().id())) {
            return;
        }

        gameConnection.account().nearEntities().forEach((k, v)->{
            if(!(v instanceof IShipEntity)) {
                return;
            }

            gameConnection.send(((IShipEntity)v).getShipRemoveCommand());
        });

        this.connections().remove(gameConnection.account().id());
    }

    /**
     * Sends the ship to the connection.
     *
     * @param ship       Ship to send.
     * @param connection Connection to send the ship.
     */
    private void _sendShip(IShipEntity ship, GameConnection connection)
    {
        ShipCreateCommand c = ship.getShipCreateCommand();
        if(c.entityID() == connection.account().id()) {
            return;
        }

        if(
                this.isStarter() &&
                c.factionID() != this.factionsID() &&
                c.factionID() != connection.account().factionsID()
        ) {
            c.warningIcon(true);
        }

        Point p = connection.account().hangar().ship().position();
        Point maxRange = Point.plus(p, Map.MAX_RANGE);

        if(!ship.position().isInRange(p, maxRange)) {
            return;
        }

        if(!ship.nearEntities().containsKey(connection.account().id())) {
            ship.nearEntities().put(connection.account().id(), connection.account());
        }
        if(!connection.account().nearEntities().containsKey(c.entityID())) {
            connection.account().nearEntities().put(c.entityID(), ship);
        }

        connection.send(c);
        connection.send(ship.getDronesInitCommand());
    }

    /**
     * Builds and returns the MapInit command.
     *
     * @return MapInit command.
     */
    public MapInitCommand getMapInitCommand()
    {
        return new MapInitCommand(super.id());
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns Map faction.
     *
     * @return Map faction.
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
