package com.rikkabot.rikkabotcore.api.endpoint;

import java.util.regex.Matcher;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.api.IEndpoint;
import com.rikkabot.rikkabotcore.net.HttpClient;
import com.rikkabot.rikkabotcore.utils.Regex;

/**
 * HTTP Login endpoint.
 * ====================
 *
 * Login with username and password to the DarkOrbit site.
 *
 * Response:
 *
 * ```json
 * {
 *     "isError": isError,
 *     "messages": ["result messages"],
 *     "result": {
 *         "username":   username,
 *         "userID":     userID,
 *         "sessionID":  sessionID,
 *         "mapID":      mapID,
 *         "serverID":   serverID
 *     }
 * }
 * ```
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class HTTPLogin implements IEndpoint {
    /**
     * Executes the endpoint.
     *
     * @param params Endpoint params.
     *
     * @return JSON response.
     */
    @Override
    public JSONObject execute(JSONArray params) {
        JSONObject response = new JSONObject("{\"isError\":true,\"messages\":[\"Wrong parameters!\"],\"result\":{}}");

        if (params.length() != 2) {
            return response;
        }

        String username, password;
        try {
            username = params.getString(0);
            password = params.getString(1);
        } catch (Exception e) {
            Console.println("Couldn't get username/password from parameters!");
            Console.print(e);

            return response;
        }

        return this._login(username, password);
    }

    /**
     * Performs the login action.
     *
     * @param username Login username.
     * @param password Login password.
     *
     * @return Response JSON.
     */
    private JSONObject _login(String username, String password) {
        HttpClient httpClient  = new HttpClient();

        String loginPage   = httpClient.get("https://lp.darkorbit.com/frame"); //Turkish login fix
        String loginAction = Regex.match("bgcdw_login_form\" action=\"(.*?)\">", loginPage)
                .group(1)
                .replace("&amp;", "&");

        httpClient.post(loginAction, "username=" + username + "&password=" + password);

        String host = httpClient.locationUrl().getHost();

        String mapRev = httpClient.get("https://"+ host +"/indexInternal.es?action=internalMapRevolution");

        return this._parseResponse(mapRev, username, password);
    }

    /**
     * Parses the MapRevolution page and returns the response.
     *
     * @param source   MapRevolution page.
     * @param username Login username.
     * @param password Login password.
     *
     * @return JSONObject response.
     */
    private JSONObject _parseResponse(String source, String username, String password) {
        String jsonRegex = "([a-zA-Z0-9\"\\s:,\\/\\?_=\\[\\]%#\\.\\-]*)";
        Matcher regex = Regex.match("flashembed\\(\"container\", \\{"+ jsonRegex +"\\}, \\{"+ jsonRegex +"\\}\\);\n", source);

        //JSONObject json = new JSONObject("{\"flash\":{"+ regex.group(1) +"}, \"vars\":{"+ regex.group(2) +"}}");
        JSONObject json = new JSONObject("{"+ regex.group(2) +"}");

        JSONObject result = new JSONObject();
        result.put("username",           username)
              .put("password",           password) // Probably not a good idea
              .put("sessionID",          json.getString("sessionID"))
              .put("userID",             json.getString("userID"))
              .put("lang",               json.getString("lang"))
              .put("chatHost",           json.getString("chatHost"))
              .put("host",               json.getString("host"))
              .put("mapID",              json.getString("mapID"))
              .put("eventStreamContext", json.getString("eventStreamContext"));

        JSONObject response = new JSONObject();
        response.put("isError", false)
                .put("messages", new String[] {"Successfully logged in!"})
                .put("result", result);

        Console.debug(response.toString(2));

        return response;
    }
}
