package com.rikkabot.rikkabotcore.plugin;

import java.security.*;
import java.util.regex.Matcher;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.utils.Regex;

/**
 * Plugin policy.
 * ==============
 *
 * Used to sandbox plugins.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class PluginPolicy extends Policy {

    /**
     * Manages permissions for code sources.
     *
     * If the source ends with `(gui|plugin)/(.*).jar$` an empty set of permission
     * will be returned
     *
     * @param codeSource The code source to get the permissions for.
     *
     * @return The permissions for the given code source.
     */
    public PermissionCollection getPermissions(CodeSource codeSource) {
        Console.debug("Setting permissions for "+ codeSource.getLocation());

        Permissions p = new Permissions();

        Matcher m = Regex.match("(gui|plugin)/(.*).jar$", codeSource.getLocation().toString());
        if(!m.matches()) {
            p.add(new AllPermission());
            Console.debug("All permissions granted!");
        }

        return p;
    }

    /**
     * Does nothing.
     */
    public void refresh() {
    }
}
