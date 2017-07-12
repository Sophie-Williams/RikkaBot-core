package com.rikkabot.rikkabotcore.dao.clans;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.accounts.Account;
import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.clans.diplomacies.Diplomacy;

/**
 * Clan DAO.
 * =========
 *
 * Represents a row from the `clans` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Clan extends DAO
{
    /**
     * Leader ID.
     */
    private int _accountsID;

    /**
     * Leader's account.
     */
    private Account _account = null;

    /**
     * Clan tag.
     */
    private String _tag;

    /**
     * Clan name.
     */
    private String _name;

    /**
     * Accounts from the clan.
     */
    private Map<Integer, Account> _accounts = null;

    /**
     * Diplomacies list
     */
    private Map<Integer, Diplomacy> _diplomacies = null;

    /**
     * Constructor.
     *
     * @param id         Clan ID.
     * @param accountsID Leader ID.
     * @param tag        Clan tag.
     * @param name       Clan name.
     */
    public Clan(int id, int accountsID, String tag, String name)
    {
        super(id);

        this._accountsID = accountsID;
        this._tag        = tag;
        this._name       = name;
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns clan account.
     *
     * @return Clan account.
     */
    public Account account()
    {
        if(this._account == null) {
            this.account(GameManager.accounts.find(this.accountsID()));
        }

        return this._account;
    }

    /**
     * Returns clan accounts.
     *
     * @return Clan accounts.
     */
    public Map<Integer, Account> accounts()
    {
        if(this._accounts == null) {
            this.accounts(GameManager.accounts.byClanID(super.id()));
        }

        return this._accounts;
    }

    /**
     * Returns clan diplomacies.
     *
     * @return Clan diplomacies.
     */
    public Map<Integer, Diplomacy> diplomacies()
    {
        if(this._diplomacies == null) {
            this.diplomacies(GameManager.clans.diplomacies().byClanID(super.id()));
        }

        return this._diplomacies;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
