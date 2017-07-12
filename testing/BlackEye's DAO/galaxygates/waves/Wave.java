package com.rikkabot.rikkabotcore.dao.galaxygates.waves;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.dao.npcs.NPC;

/**
 * Wave DAO.
 * =========
 *
 * Represents a row from the `galaxygates_waves` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @EqualsAndHashCode(callSuper = true)
public class Wave extends DAO
{
    /**
     * Starting round of NPCs.
     */
    private List<NPC> _starting;

    /**
     * Continuing round of NPCs.
     */
    private List<NPC> _continuing;

    /**
     * Last round of NPCs.
     */
    private List<NPC> _last;

    /**
     * Time between rounds.
     */
    private int _time;

    /**
     * Amount of NPCs that must be left before passing to next spawn.
     */
    private int _npcsLeft;

    /**
     * Constructor.
     *
     * @param id         Entity ID.
     * @param starting   Starting round of NPCs.
     * @param continuing Continuing round of NPCs.
     * @param last       Last round of NPCs.
     * @param time       Time between rounds.
     * @param npcsLeft   Amount of NPCs that must be left before passing to next spawn.
     */
    public Wave(int id, List<NPC> starting, List<NPC> continuing, List<NPC> last, int time, int npcsLeft)
    {
        super(id);

        this._starting   = starting;
        this._continuing = continuing;
        this._last       = last;
        this._time       = time;
        this._npcsLeft   = npcsLeft;
    }
}
