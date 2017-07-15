package com.rikkabot.rikkabotcore;

import com.rikkabot.rikkabotcore.dao.FactoryManager;
import com.rikkabot.rikkabotcore.dao.hero.Hero;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import com.manulaiko.tabitha.Console;

public class MainWindowController {

    public TextField usernameField;
    public PasswordField passwordField;

    public void onLoginBtnPressed(ActionEvent event) {
        Console.debug("Logging in...");

        Hero hero = FactoryManager.heroes.login(usernameField.getText(), passwordField.getText());

        Console.debug("Successfully logged in!");

        Console.debug("Connecting...");

        hero.connect();
    }
}
