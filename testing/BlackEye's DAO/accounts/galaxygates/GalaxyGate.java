package com.rikkabot.rikkabotcore.dao.accounts.galaxygates;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.galaxygates.waves.Wave;

/**
 * GalaxyGate DAO.
 * ===============
 *
 * Represents a row from the `accounts_galaxygates` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class GalaxyGate extends DAO
{
    /**
     * Gate ID.
     */
    private int _galaxyGatesID;

    /**
     * Gate object.
     */
    private com.rikkabot.rikkabotcore.dao.galaxygates.GalaxyGate _galaxyGate = null;

    /**
     * Collected parts.
     */
    private int _parts;

    /**
     * Available lifes.
     */
    private int _lifes;

    /**
     * Current wave ID.
     */
    private int _waveID;

    /**
     * Current wave object.
     */
    private Wave _wave = null;

    /**
     * Times this gate was completed.
     */
    private int _times;

    /**
     * Whether the gate is in base or not.
     */
    private boolean _isCompleted;
    
    /**
     * Constructor.
     *
     * @param id            GalaxyGate ID.
     * @param galaxyGatesID Gate ID.
     * @param parts         Collected parts.
     * @param lifes         Available lifes.
     * @param waveID        Current wave ID.
     * @param times         Times this gate was completed.
     * @param isCompleted   Whether the gate is in base or not.
     */
    public GalaxyGate(int id, int galaxyGatesID, int parts, int lifes, int waveID, int times, boolean isCompleted)
    {
        super(id);
        
        this._galaxyGatesID = galaxyGatesID;
        this._parts         = parts;
        this._lifes         = lifes;
        this._waveID        = waveID;
        this._times         = times;
        this._isCompleted   = isCompleted;
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns galaxygate galaxyGate.
     *
     * @return GalaxyGate galaxyGate.
     */
    public com.rikkabot.rikkabotcore.dao.galaxygates.GalaxyGate galaxyGate()
    {
        if(this._galaxyGate == null) {
            this.galaxyGate(GameManager.galaxygates.find(this.galaxyGatesID()));
        }

        return this._galaxyGate;
    }

    /**
     * Returns galaxygate wave.
     *
     * @return GalaxyGate wave.
     */
    public Wave wave()
    {
        if(this._wave == null) {
            this.wave(this.galaxyGate().waves().get(this.waveID()));
        }
        return this._wave;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
