package com.rikkabot.rikkabotcore.dao;

import com.rikkabot.rikkabotcore.dao.hero.HeroFactory;

/**
 * Factory manager.
 * ================
 *
 * Groups the instances of all factories and provides
 * methods for massively manage them.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class FactoryManager {
    /////////////////////////////
    // Start factory instances //
    /////////////////////////////
    //<editor-fold desc="Factory Instances" defaultsate="collapsed">
    public static HeroFactory heroes = new HeroFactory();
    //</editor-fold>
    ///////////////////////////
    // End factory instances //
    ///////////////////////////
}
