package com.rikkabot.rikkabotcore.dao.hero;

import com.manulaiko.tabitha.Console;
import com.rikkabot.rikkabotcore.bot.GameConnection;
import com.rikkabot.rikkabotcore.bot.commands.outgoing.VersionRequest;
import com.rikkabot.rikkabotcore.dao.DAO;
import com.rikkabot.rikkabotcore.net.GameClient;
import com.rikkabot.rikkabotcore.net.HttpClient;
import com.rikkabot.rikkabotcore.utils.Regex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by piotr on 12.07.17.
 */

@AllArgsConstructor @Accessors @Data
public class Hero extends DAO
{
    private String username;
    private String password;
    private String server;

    private int userId;
    private int mapId;

    private HttpClient httpClient;

    private GameConnection gameConnection;

    public void connect() {
        Console.debug("Connecting to mapId: " + this.mapId);

        String maps = httpClient.get("https://darkorbit.com/spacemap/xml/maps.php");
        String ip = Regex.match("<map id=\\\"10\\\"><gameserverIP>(.*?)<\\/", maps).group(1);
        Console.debug("Matched IP Address: " + ip + ", connecting...");
        GameClient gameClient = new GameClient(ip, 8080);
        gameClient.connect(this);
    }

    public void onConnected() {
        this.gameConnection.send(new VersionRequest(0, 105, 7));
    }
}
