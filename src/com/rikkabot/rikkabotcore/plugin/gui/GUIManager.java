package com.rikkabot.rikkabotcore.plugin.gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.Policy;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import lombok.Getter;
import lombok.Setter;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.Main;
import com.rikkabot.rikkabotcore.plugin.PluginPolicy;

/**
 * GUI Manager.
 * ============
 *
 * This class loads, bootstraps and starts a GUI.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class GUIManager {
    /**
     * Loaded GUI.
     */
    @Getter @Setter
    private GUI gui;

    /**
     * Starts the GUI.
     *
     * @param gui GUI to start.
     *
     * @return Whether the GUI could be started or not.
     */
    public boolean start(File gui) {
        Policy.setPolicy(new PluginPolicy());
        System.setSecurityManager(new SecurityManager());


        String mainClass = this.getMainClass(gui);
        if(mainClass.isEmpty()) {
            return false;
        }

        GUI instance = this.instance(gui, mainClass);
        if(instance == null) {
            return false;
        }

        instance.bootstrap(Main.api);
        instance.show();

        this.gui(instance);

        return true;
    }

    /**
     * Finds and returns the main class of the GUI jar.
     *
     * The main class is the one defined by the `Main-Class` attribute
     * in the MANIFEST.mf file of the jar.
     *
     * @param gui GUI to get the main class.
     *
     * @return GUI's main class.
     */
    private String getMainClass(File gui) {
        try {
            JarFile jarFile = new JarFile(gui);

            Manifest manifest = jarFile.getManifest();

            return manifest.getMainAttributes()
                           .getValue("Main-Class");
        } catch (IOException e) {
            Console.print(e);
            Console.println("Couldn't read GUI jar!");

            return "";
        }
    }

    /**
     * Instances and returns the GUI.
     *
     * @param gui       GUI jar file.
     * @param mainClass Class to instance.
     *
     * @return GUI instance.
     */
    private GUI instance(File gui, String mainClass) {
        URLClassLoader classLoader;
        try {
            classLoader = URLClassLoader.newInstance(new URL[] {
                    gui.toURI().toURL()
            });
        } catch (MalformedURLException e) {
            Console.print(e);
            Console.println("Malformed GUI path!");

            return null;
        }

        GUI instance;
        try {
            instance = (GUI)classLoader.loadClass(mainClass).newInstance();
        } catch (ClassNotFoundException e) {
            Console.print(e);
            Console.println("Couldn't find GUI's main class!");

            return null;
        } catch (Exception e) {
            Console.print(e);
            Console.println("Couldn't instance GUI!");

            return null;
        }

        return instance;
    }
}
