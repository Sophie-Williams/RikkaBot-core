package com.rikkabot.rikkabotcore.dao.hero;

import com.rikkabot.rikkabotcore.networking.HttpClient;
import com.rikkabot.rikkabotcore.utils.Regex;

import java.util.regex.Matcher;

public class HeroFactory {
    /**
     * Logins to the game and returns an instance of `Hero`
     * @param username username of the account
     * @param password password of the account
     * @return an instance of hero
     */
    public static Hero login(String username, String password) {
        HttpClient httpClient = new HttpClient();
        String loginPage = httpClient.get("https://lp.darkorbit.com/frame"); //Turkish login fix
        String loginAction = Regex.match("bgcdw_login_form\" action=\"(.*?)\"", loginPage).group(1).replace("amp;", "");
        httpClient.post(loginAction, "username=" + username + "&password=" + password);
        String server = httpClient.locationUrl().getHost().replace(".darkorbit.com", ""); //can be done with Substring as well
        String mapRev = httpClient.get("https://" + server + ".darkorbit.com/indexInternal.es?action=internalMapRevolution");
        Matcher match = Regex.match(".*\"userID\": \"([0-9]+)\",\"sessionID\": \"(.+?)\".*\"pid\": \"([0-9]+)\".*\"mapID\": \"([0-9]+)\"", mapRev);
        return new Hero(username, password, server, Integer.parseInt(match.group(1)));
    }
}
