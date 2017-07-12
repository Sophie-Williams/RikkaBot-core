package com.rikkabot.rikkabotcore.dao.accounts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.utils.Point;

import com.rikkabot.rikkabotcore.dao.accounts.hangars.Hangar;
import com.rikkabot.rikkabotcore.dao.accounts.items.Item;
import com.rikkabot.rikkabotcore.dao.accounts.settings.Settings;
import com.rikkabot.rikkabotcore.dao.entities.*;
import com.rikkabot.rikkabotcore.dao.levels.Level;
import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.accounts.galaxygates.GalaxyGate;
import com.rikkabot.rikkabotcore.dao.clans.Clan;
import com.rikkabot.rikkabotcore.dao.factions.Faction;
import com.manulaiko.blackeye.net.game.GameConnection;
import com.manulaiko.blackeye.net.game.commands.out.*;

/**
 * Account DAO.
 * ============
 *
 * Represents a row from the `accounts` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Account extends DAO implements IShipEntity, IMovableEntity, IAttackerEntity, IAttackableEntity
{
    /**
     * Session ID.
     */
    private String _sessionID;

    /**
     * Level ID.
     */
    private int _levelsID;

    /**
     * Level object.
     */
    private Level _level = null;

    /**
     * Faction ID.
     */
    private int _factionsID;

    /**
     * Faction object.
     */
    private Faction _faction = null;

    /**
     * Active hangar ID.
     */
    private int _hangarsID;

    /**
     * Active hangar object.
     */
    private Hangar _hangar = null;

    /**
     * Available hangars.
     */
    private Map<Integer, Hangar> _hangars = null;

    /**
     * Available items.
     */
    private Map<Integer, Item> _items = null;

    /**
     * Clan ID.
     */
    private int _clansID;

    /**
     * Clan object.
     */
    private Clan _clan = null;

    /**
     * Available galaxy gates.
     */
    private Map<Integer, GalaxyGate> _galaxyGates = null;

    /**
     * Rank ID.
     */
    private int _ranksID;

    /**
     * Account name.
     */
    private String _name;

    /**
     * Ban end date.
     */
    private String _banDate;

    /**
     * Premium end date.
     */
    private String _premiumDate;

    /**
     * Uridium.
     */
    private int _uridium;

    /**
     * Credits.
     */
    private long _credits;

    /**
     * Jackpot.
     */
    private double _jackpot;

    /**
     * Experience.
     */
    private long _experience;

    /**
     * Honor.
     */
    private int _honor;

    /**
     * Settings.
     */
    private Settings _settings = null;

    /**
     * Used cargo space.
     */
    private int _cargo = 0;

    /**
     * Batteries amount.
     */
    private int _batteries = 0;

    /**
     * Rockets amount.
     */
    private int _rockets = 0;

    /**
     * Whether account is premium or not.
     */
    private boolean _isPremium;

    /**
     * Whether premium has been checked or not.
     */
    private boolean _hasCheckedPremium;

    /*
     * Available quests.
     */
    //private Map<Integer, Quest> _quests;

    /**
     * Active game connection.
     */
    private GameConnection _connection;

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
     * @param id          Account ID.
     * @param sessionID   Session ID.
     * @param levelsID    Level ID.
     * @param factionsID  Faction ID.
     * @param hangarsID   Active hangar ID.
     * @param clansID     Clan ID.
     * @param ranksID     Rank ID.
     * @param name        Account name.
     * @param banDate     Ban end date.
     * @param premiumDate Premium end date.
     * @param uridium     Uridium.
     * @param credits     Credits.
     * @param jackpot     Jackpot.
     * @param experience  Experience.
     * @param honor       Honor.
     */
    public Account(
            int id, String sessionID, int levelsID, int factionsID, int hangarsID, int clansID,
            int ranksID, String name, String banDate, String premiumDate, int uridium, long credits,
            double jackpot, long experience, int honor
    ) {
        super(id);

        this._sessionID   = sessionID;
        this._levelsID    = levelsID;
        this._factionsID  = factionsID;
        this._hangarsID   = hangarsID;
        this._clansID     = clansID;
        this._ranksID     = ranksID;
        this._name        = name;
        this._banDate     = banDate;
        this._premiumDate = premiumDate;
        this._uridium     = uridium;
        this._credits     = credits;
        this._jackpot     = jackpot;
        this._experience  = experience;
        this._honor       = honor;
    }

    /**
     * Builds and returns the DronesInit command.
     *
     * @return DroneInit command.
     */
    @Override
    public DronesInitCommand getDronesInitCommand()
    {
        List<Item> drones = new ArrayList<>();

        this.items().forEach((k, v)->{
            if(
                    v.itemsID() == com.rikkabot.rikkabotcore.dao.items.Item.IRIS ||
                    v.itemsID() == com.rikkabot.rikkabotcore.dao.items.Item.FLAX
            ) {
                drones.add(v);
            }
        });

        return new DronesInitCommand(super.id(), drones);
    }

    /**
     * Builds and returns the ShipInit command.
     *
     * @return ShipInit command.
     */
    public ShipInitCommand getShipInitCommand()
    {
        String tag = "";
        if(this.clan() != null) {
            tag = this.clan().tag();
        }

        return new ShipInitCommand(
                super.id(),
                this.hangar().ship().gfx(),
                this.hangar().ship().shield(),
                this.hangar().shield(),
                this.hangar().ship().health(),
                this.hangar().ship().ship().health(),
                this.cargo(),
                this.hangar().ship().ship().cargo(),
                (int)this.hangar().ship().position().x(),
                (int)this.hangar().ship().position().y(),
                this.hangar().ship().mapsID(),
                this.factionsID(),
                this.clansID(),
                this.batteries(),
                this.rockets(),
                this.hangar().configuration().expansions(),
                this.honor(),
                this.levelsID(),
                this.uridium(),
                this.ranksID(),
                0,
                1,
                this.hangar().speed(),
                this.experience(),
                this.credits(),
                this.jackpot(),
                this.name(),
                tag,
                this.isPremium()
        );
    }

    /**
     * Builds and returns the BatteriesInit command.
     *
     * @return BatteriesInit command
     */
    public BatteriesInit getBatteriesInitCommand()
    {
        int lcb10  = 0;
        int mcb25  = 0;
        int mcb50  = 0;
        int ucb100 = 0;
        int sab50  = 0;

        for(Item i : this.items().values()) {
            switch(i.itemsID())
            {
                case com.rikkabot.rikkabotcore.dao.items.Item.LCB_10:
                    lcb10 += i.amount();
                    break;

                case com.rikkabot.rikkabotcore.dao.items.Item.MCB_25:
                    mcb25 += i.amount();
                    break;

                case com.rikkabot.rikkabotcore.dao.items.Item.MCB_50:
                    mcb50 += i.amount();
                    break;

                case com.rikkabot.rikkabotcore.dao.items.Item.UCB_100:
                    ucb100 += i.amount();
                    break;

                case com.rikkabot.rikkabotcore.dao.items.Item.SAB_50:
                    sab50 += i.amount();
                    break;
            }
        }

        return new BatteriesInit(lcb10, mcb25, mcb50, ucb100, sab50);
    }

    /**
     * Builds and returns the RocketsInit command.
     *
     * @return RocketsInit command
     */
    public RocketsInit getRocketsInitCommand()
    {
        int r310     = 0;
        int plt2020  = 0;
        int plt2021  = 0;
        int acm100   = 0;

        for(Item i : this.items().values()) {
            switch(i.itemsID())
            {
                case com.rikkabot.rikkabotcore.dao.items.Item.R_310:
                    r310 += i.amount();
                    break;

                case com.rikkabot.rikkabotcore.dao.items.Item.PLT_2020:
                    plt2020 += i.amount();
                    break;

                case com.rikkabot.rikkabotcore.dao.items.Item.PLT_2021:
                    plt2021 += i.amount();
                    break;

                case com.rikkabot.rikkabotcore.dao.items.Item.ACM_100:
                    acm100 += i.amount();
                    break;
            }
        }

        return new RocketsInit(r310, plt2020, plt2021, acm100, 0, 0);
    }

    /**
     * Builds and returns the ShipCreate command.
     *
     * @return ShipCreate command.
     */
    @Override
    public ShipCreateCommand getShipCreateCommand()
    {
        String tag = "";
        if(this.clan() != null) {
            tag = this.clan().tag();
        }

        return new ShipCreateCommand(
                super.id(),
                this.hangar().ship().gfx(),
                (int)this.position().x(),
                (int)this.position().y(),
                this.factionsID(),
                this.clansID(),
                this.hangar().configuration().expansions(),
                this.ranksID(),
                0,
                0,
                false,
                false,
                false,
                this.name(),
                tag
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
        return new ShipRemoveCommand(super.id());
    }

    /**
     * Builds and returns the ShipDestroy command.
     *
     * @return ShipDestroy command.
     */
    @Override
    public ShipDestroyCommand getShipDestroyCommand()
    {
        return new ShipDestroyCommand(super.id());
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
                this.hangar().ship().health(),
                this.hangar().ship().ship().health(),
                this.hangar().configuration().shield(),
                this.hangar().shield()
        );
    }

    /**
     * Performs the movement.
     *
     * @param destination Target position.
     * @param time        Time needed to reach destination.
     */
    @Override
    public void move(Point destination, long time)
    {
        //ayy
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
     * Returns account level.
     *
     * @return Account level.
     */
    public Level level()
    {
        if(this._level == null) {
            this.level(GameManager.levels.find(this._levelsID));
        }

        return this._level;
    }

    /**
     * Returns account faction.
     *
     * @return Account faction.
     */
    public Faction faction()
    {
        if(this._faction == null) {
            this.faction(GameManager.factions.find(this.factionsID()));
        }

        return this._faction;
    }

    /**
     * Returns account hangar.
     *
     * @return Account hangar.
     */
    public Hangar hangar()
    {
        if(this._hangar == null) {
            this.hangar(GameManager.accounts.hangars().find(this.hangarsID()));
        }

        return this._hangar;
    }

    /**
     * Returns account hangars.
     *
     * @return Account hangars.
     */
    public Map<Integer, Hangar> hangars()
    {
        if(this._hangars == null) {
            this.hangars(GameManager.accounts.hangars().byAccountsID(super.id()));
        }

        return this._hangars;
    }

    /**
     * Returns account items.
     *
     * @return Account items.
     */
    public Map<Integer, Item> items()
    {
        if(this._items == null) {
            this.items(GameManager.accounts.items().byAccountsID(super.id()));
        }

        return this._items;
    }

    /**
     * Returns account clan.
     *
     * @return Account clan.
     */
    public Clan clan()
    {
        if(
            this.clansID() > 0 &&
            this._clan == null
        ) {
            this.clan(GameManager.clans.find(this.clansID()));
        }

        return this._clan;
    }

    /**
     * Returns account galaxyGates.
     *
     * @return Account galaxyGates.
     */
    public Map<Integer, GalaxyGate> galaxyGates()
    {
        if(this._galaxyGates == null) {
            this.galaxyGates(GameManager.accounts.galaxygates().byAccountsID(super.id()));
        }

        return this._galaxyGates;
    }

    /**
     * Returns account settings.
     *
     * @return Account settings.
     */
    public Settings settings()
    {
        if(this._settings == null) {
            this.settings(GameManager.accounts.settings().findByAccountID(super.id()));
        }

        return this._settings;
    }

    /**
     * Returns account cargo.
     *
     * @return Account cargo.
     */
    public int cargo()
    {
        if(this._cargo == 0) {
            int cargo = 0;

            for(Item i : this.items().values()) {
                if(
                        i.item().category().equals("resource") &&
                        (i.item().type().equals("ore") && i.itemsID() != com.rikkabot.rikkabotcore.dao.items.Item.XENOMIT)
                ) {
                    cargo += i.amount();
                }
            }

            this.cargo(cargo);
        }

        return this._cargo;
    }

    /**
     * Returns account batteries.
     *
     * @return Account batteries.
     */
    public int batteries()
    {
        if(this._batteries == 0) {
            int batteries = 0;

            for(Item i : this.items().values()) {
                if(
                        i.item().category().equals("ammunition") &&
                        i.item().type().equals("laser")
                ) {
                    batteries += i.amount();
                }
            }

            this.batteries(batteries);
        }

        return this._batteries;
    }

    /**
     * Returns account rockets.
     *
     * @return Account rockets.
     */
    public int rockets()
    {
        if(this._rockets == 0) {
            int rockets = 0;

            for(Item i : this.items().values()) {
                String t = i.item().type(); // shortcut
                if(
                        i.item().category().equals("ammunition") &&
                        (t.equals("rocket") || t.equals("mine") || t.equals("rocketlauncher"))
                ) {
                    rockets += i.amount();
                }
            }

            this.rockets(rockets);
        }

        return this._rockets;
    }

    /**
     * Returns account isPremium.
     *
     * @return Account isPremium.
     */
    private boolean isPremium()
    {
        if(!this._hasCheckedPremium) {
            try {
                this.isPremium(
                        new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(this.premiumDate()).before(new Date())
                );
            } catch(Exception e) {
                this.isPremium(false);
            }
        }

        return this._isPremium;
    }

    /**
     * Sets account isPremium.
     *
     * @param isPremium New isPremium
     */
    public void isPremium(boolean isPremium)
    {
        this._hasCheckedPremium = true;
        this._isPremium         = isPremium;
    }

    /*
     * Returns account quests.
     *
     * @return Account quests.
     */
    /* public Map<Integer, Quest> quests()
    {
        return this._quests;
    } */

    /*
     * Sets account quests.
     *
     * @param quests New quests.
     */
    /* public void quests(Map<Integer, Quest> quests)
    {
        this._quests = quests;
    } */

    /**
     * Sets account connection.
     *
     * @param connection New connection.
     */
    public void connection(GameConnection connection)
    {
        if(this.connection() != null) {
            // A connection for this account already exists
            //TODO close connection.
        }

        this._connection = connection;
        connection.account(this);
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
     * Returns map.
     *
     * @return Entity's map.
     */
    @Override
    public com.rikkabot.rikkabotcore.dao.maps.Map map()
    {
        return this.hangar().ship().map();
    }

    /**
     * Sets map.
     *
     * @param map New entity map.
     */
    @Override
    public void map(com.rikkabot.rikkabotcore.dao.maps.Map map)
    {
        this.hangar().ship().map(map);
    }

    /**
     * Returns ship position.
     *
     * @return Ship position on map.
     */
    @Override
    public Point position()
    {
        return this.hangar().ship().position();
    }

    /**
     * Sets ship position.
     *
     * @param position New position on map.
     */
    @Override
    public void position(Point position)
    {
        this.hangar().ship().position(position);
    }

    /**
     * Returns near entities.
     *
     * @return Near entities.
     */
    @Override
    public Map<Integer, IMapEntity> nearEntities()
    {
        return this.hangar().ship().nearEntities();
    }

    /**
     * Sets near entities.
     *
     * @param entities New near entities.
     */
    @Override
    public void nearEntities(Map<Integer, IMapEntity> entities)
    {
        this.hangar().ship().nearEntities(entities);
    }

    /**
     * Returns whether the entity is moving or not.
     *
     * @return Whether the entity is moving or not.
     */
    @Override
    public boolean isMoving()
    {
        return this.hangar().ship().isMoving();
    }

    /**
     * Sets whether the entity is moving or not.
     *
     * @param isMoving New moving state.
     */
    @Override
    public void isMoving(boolean isMoving)
    {
        this.hangar().ship().isMoving(isMoving);
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
