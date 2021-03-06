package com.rikkabot.rikkabotcore.plugin.gui;

import com.rikkabot.rikkabotcore.plugin.API;

/**
 * GUI Interface.
 * ==============
 *
 * Interface for all GUIs.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface GUI {
    /**
     * Bootstraps the GUI without rendering it.
     *
     * @param api Bot API.
     */
    void bootstrap(API api);

    /**
     * Show the GUI.
     */
    void show();

    /**
     * Hides the GUI.
     */
    void hide();
}
