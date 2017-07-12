package com.rikkabot.rikkabotcore.bot;

import com.rikkabot.rikkabotcore.networking.HttpClient;
import com.rikkabot.rikkabotcore.utils.Regex;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by piotr on 12.07.17.
 */
public class Hero {
    private String username;
    private String password;

    private HttpClient httpClient;

    public Hero(String username, String password) {
        this.username = username;
        this.password = password;
        httpClient = new HttpClient();
    }

    /**
     * logins into the account
     */
    public void login() {
        String loginPage = httpClient.get("https://lp.darkorbit.com/frame"); //Turkish login fix
        String loginAction = Regex.match("bgcdw_login_form\" action=\"(.*?)\"", loginPage).group(1).replace("amp;", "");
        String homePage = httpClient.post(loginAction, "username=" + username + "&password=" + password);
        System.out.println(homePage);
    }

    //region getters
    /**
     * gets username
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * gets password
     * @return password
     */
    public String getPassword() {
        return this.password;
    }
    //endregion
}
