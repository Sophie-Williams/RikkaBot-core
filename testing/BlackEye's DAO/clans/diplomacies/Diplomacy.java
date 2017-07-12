package com.rikkabot.rikkabotcore.dao.clans.diplomacies;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.clans.Clan;
import com.manulaiko.blackeye.launcher.GameManager;

/**
 * Diplomacy DAO.
 * ==============
 *
 * Represents a row from the `clans_diplomacy` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Diplomacy extends DAO
{
    ///////////////////////////////
    // Start constant definition //
    ///////////////////////////////
    
    public static final int TYPE_WAR      = 1;
    public static final int TYPE_NAP      = 2;
    public static final int TYPE_ALLIANCE = 3;
    
    /////////////////////////////
    // End constant definition //
    /////////////////////////////
    
    /**
     * Clan ID that sent the diplomacy.
     */
    private int _fromClansID;

    /**
     * Clan object that sent the diplomacy.
     */
    private Clan _fromClan = null;

    /**
     * Clan ID that received the diplomacy.
     */
    private int _toClansID;

    /**
     * Clan object that received the diplomacy.
     */
    private Clan _toClan = null;

    /**
     * Diplomacy type.
     */
    private int _type;

    /**
     * Constructor.
     *
     * @param id          Diplomacy ID.
     * @param fromClansID Clan ID that sent the diplomacy.
     * @param toClansID   Clan ID that received the diplomacy.
     * @param type        Diplomacy type.
     */
    public Diplomacy(int id, int fromClansID, int toClansID, int type)
    {
        super(id);
        
        this._fromClansID = fromClansID;
        this._toClansID   = toClansID;
        this._type        = type;
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns diplomacy fromClan.
     *
     * @return Diplomacy fromClan.
     */
    public Clan fromClan()
    {
        if(this._fromClan == null) {
            this.fromClan(GameManager.clans.find(this.fromClansID()));
        }

        return this._fromClan;
    }

    /**
     * Returns diplomacy toClan.
     *
     * @return Diplomacy toClan.
     */
    public Clan toClan()
    {
        if(this._toClan == null) {
            this.toClan(GameManager.clans.find(this.toClansID()));
        }

        return this._toClan;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
