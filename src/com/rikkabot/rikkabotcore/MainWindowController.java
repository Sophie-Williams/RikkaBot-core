package com.rikkabot.rikkabotcore;

import com.rikkabot.rikkabotcore.dao.hero.Hero;
import com.rikkabot.rikkabotcore.dao.hero.HeroFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class MainWindowController {

    public TextField usernameField;
    public PasswordField passwordField;

    public void onLoginBtnPressed(ActionEvent event) {
        System.out.println("logging in");

        Hero hero = HeroFactory.login(usernameField.getText(), passwordField.getText());
    }
}
