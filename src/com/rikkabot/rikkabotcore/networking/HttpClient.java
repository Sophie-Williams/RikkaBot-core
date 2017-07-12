package com.rikkabot.rikkabotcore.networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.StringJoiner;

/**
 * Created by Freshek on 12.07.17.
 */
public class HttpClient {
    public HttpClient() {
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    }

    /**
     * Sends GET request to the target url
     * @param targetUrl url to send the request
     * @return request response
     */
    public String get(String targetUrl) {
        try {
            URL url = new URL(targetUrl);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:54.0) Gecko/20100101 Firefox/54.0");

            return readHttpResponse(connection);

        } catch (Exception e) {
            System.out.println(e.getMessage() + '\n' + e.getStackTrace());
            return null;
        }
    }

    /**
     * Sends POST request to the target url
     * @param targetUrl url to send the request
     * @param postData data to post
     * @return request response
     */
    public String post(String targetUrl, String postData) {
        try {
            String data = prepareHttpPostData(postData);
            URL url = new URL(targetUrl);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:54.0) Gecko/20100101 Firefox/54.0");

            connection.setRequestProperty("Content-Length", String.valueOf(data.length()));

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);
            outputStream.close();

            return readHttpResponse(connection);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reads HTTP response from HttpURLConnection
     * @param connection connection to read data from
     * @return HTTP response
     */
    private String readHttpResponse(HttpURLConnection connection) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            StringBuilder ret = new StringBuilder();
            while ((line = reader.readLine()) != null)
                ret.append(line + '\n');
            reader.close();

            return ret.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Prepares the data for a POST request
     * @param postData data to be prepared
     * @return prepared data
     */
    private String prepareHttpPostData(String postData) {
        try {
            URI uri = new URI(postData);
            return uri.toASCIIString();
        } catch (Exception e) {
            return null;
        }
    }
}
