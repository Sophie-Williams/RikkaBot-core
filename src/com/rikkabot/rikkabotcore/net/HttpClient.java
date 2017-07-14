package com.rikkabot.rikkabotcore.net;

import com.manulaiko.tabitha.Console;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.net.HttpURLConnection;

/**
 * Created by piotr on 12.07.17.
 */
public class HttpClient {
    private URL locationUrl;

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
            System.out.println(targetUrl);
            URL url = new URL(targetUrl);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(false);

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:54.0) Gecko/20100101 Firefox/54.0");

            return readHttpResponse(connection);

        } catch (Exception e) {
            Console.print(e);
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
            connection.setInstanceFollowRedirects(false);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:54.0) Gecko/20100101 Firefox/54.0");

            connection.setRequestProperty("Content-Length", String.valueOf(data.length()));

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);
            outputStream.close();

            return readHttpResponse(connection);
        } catch (Exception e) {
            Console.print(e);
            return null;
        }
    }

    /**
     * Reads HTTP response from HttpURLConnection
     * If the HTTP response code is 301 or 302, it follows
     * `Location` header until the target is reached
     * @param connection connection to read data from
     * @return HTTP response
     */
    private String readHttpResponse(HttpURLConnection connection) {
        try {
            InputStream inputStream = connection.getInputStream();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
                String location = connection.getHeaderField("Location");
                if (location.startsWith("/")) {
                    URI uri = new URI(connection.getURL().toString());
                    return get(uri.getScheme() + "://" + uri.getHost() + location);
                }
                return get(connection.getHeaderField("Location"));
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuilder ret = new StringBuilder();
            while ((line = reader.readLine()) != null)
                ret.append(line + '\n');
            reader.close();

            locationUrl = connection.getURL();
            return ret.toString();
        } catch (Exception e) {
            Console.print(e);
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
            Console.print(e);
            return null;
        }
    }

    public URL locationUrl() {
        return this.locationUrl;
    }
}
