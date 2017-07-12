package com.rikkabot.rikkabotcore.dao.galaxygates;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.manulaiko.blackeye.launcher.GameManager;
import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.galaxygates.waves.Wave;
import com.rikkabot.rikkabotcore.dao.rewards.Reward;

/**
 * GalaxyGate DAO.
 * ===============
 *
 * Represents a row from the `galaxygates` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class GalaxyGate extends DAO
{
    /**
     * Starting wave ID.
     */
    private int _galaxygatesWavesID;

    /**
     * Starting wave object.
     */
    private Wave _galaxygateWave = null;

    /**
     * Available waves.
     */
    private Map<Integer, Wave> _waves = null;

    /**
     * Parts needed to build the galaxy gate.
     */
    private int _parts;

    /**
     * Rewards to award when the GG is completed.
     */
    private List<Reward> _rewards;
    
    /**
     * Constructor.
     *
     * @param id                 Entity ID.
     * @param galaxygatesWavesID Starting wave ID.
     * @param parts              Parts needed to build the galaxy gate.
     * @param rewards            Rewards to award when the GG is completed.
     */
    public GalaxyGate(int id, int galaxygatesWavesID, int parts, List<Reward> rewards)
    {
        super(id);
        
        this._galaxygatesWavesID = galaxygatesWavesID;
        this._parts              = parts;
        this._rewards            = rewards;
    }

    ///////////////////////////////
    // Start getters and setters //
    ///////////////////////////////
    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    /**
     * Returns galaxygate galaxygateWave.
     *
     * @return Galaxygate galaxygateWave.
     */
    public Wave galaxygateWave()
    {
        if(this._galaxygateWave == null) {
            this.galaxygateWave(GameManager.galaxygates.waves().find(this.galaxygatesWavesID()));
        }

        return this._galaxygateWave;
    }

    /**
     * Returns galaxygate waves.
     *
     * @return Galaxygate waves.
     */
    public Map<Integer, Wave> waves()
    {
        if(this._galaxygateWave == null) {
            this.waves(GameManager.galaxygates.waves().byGalaxyGateID(super.id()));
        }

        return this._waves;
    }
    //</editor-fold>
    /////////////////////////////
    // End getters and setters //
    /////////////////////////////
}
