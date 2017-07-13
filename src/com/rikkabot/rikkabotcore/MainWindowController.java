package com.rikkabot.rikkabotcore;

import com.rikkabot.rikkabotcore.dao.FactoryManager;
import com.rikkabot.rikkabotcore.dao.hero.Hero;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class MainWindowController {

    public TextField usernameField;
    public PasswordField passwordField;

    public void onLoginBtnPressed(ActionEvent event) {
        System.out.println("logging in");

        Hero hero = FactoryManager.heroes.login(usernameField.getText(), passwordField.getText());
    }
}
