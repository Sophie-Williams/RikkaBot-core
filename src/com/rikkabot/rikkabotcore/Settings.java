package com.rikkabot.rikkabotcore;

import java.io.File;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Settings class.
 * ===============
 *
 * Application settings so they can easily be changed at runtime.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Settings {
    /**
     * Whether we're running in debug mode or not.
     */
    @Getter @Setter
    private static boolean debug = false;

    /**
     * Whether we should start the GUI or not.
     */
    @Getter @Setter
    private static boolean showGUI = true;

    /**
     * GUI to render.
     */
    @Getter @Setter
    private static File gui = new File("gui/default.jar");
}
