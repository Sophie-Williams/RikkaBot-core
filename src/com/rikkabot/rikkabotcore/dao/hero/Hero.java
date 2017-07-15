package com.rikkabot.rikkabotcore.dao.hero;

import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.net.GameClient;
import com.rikkabot.rikkabotcore.net.HttpClient;
import com.rikkabot.rikkabotcore.utils.Regex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Created by piotr on 12.07.17.
 */

@AllArgsConstructor @Accessors @Getter
public class Hero extends DAO
{
    private String username;
    private String password;
    private String server;

    private int userId;
    private int mapId;

    private HttpClient httpClient;

    public void connect() {
        String ip = Regex.match("<map id=\\\"" + this.mapId + "\\\"><gameserverip>(.*?)<\\/", httpClient.get("https://darkorbit.com/spacemap/xml/maps.php")).group(1);
        GameClient gameClient = new GameClient(ip, 8080);
        gameClient.connect();
    }
}
