package com.manulaiko.tabitha.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Base64;
import java.util.Map;
import java.util.HashMap;

import com.manulaiko.tabitha.Console;

/**
 * Class for executing HTTP Request.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class WebClient
{
    /**
     * Auth user.
     */
    private String _username = "";

    /**
     * Auth password.
     */
    private String _password = "";

    /**
     * Auth type.
     */
    private String _type = "";

    /**
     * Proxy object.
     */
    private Proxy _proxy = null;

    /**
     * Executes an HTTP Request.
     *
     * @param URL     URL to request.
     * @param params  Params to send.
     * @param method  Request method.
     * @param headers Headers.
     *
     * @return Response string.
     */
    public String execute(String URL, String params, String method, Map<String, String> headers)
    {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection)this.open(URL);
            connection.setRequestMethod(method);

            for(Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(params);
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String response = "";
            String line = null;

            while((line = in.readLine()) != null) {
                response += line +"\n";
            }

            in.close();

            return response;
        } catch (Exception e) {
            Console.println("Couldn't execute "+ method +" request!");
            Console.println(e.getMessage());

            e.printStackTrace();

            return "";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Executes a POST request.
     *
     * @param url    URL to request.
     * @param params Params to send.
     *
     * @return Response string.
     */
    public String post(String url, Map<String, String> params)
    {
        String p = WebClient.parseParams(params);

        return this.execute(url, p, "POST", new HashMap<>());
    }

    /**
     * Executes a GET request.
     *
     * @param url    URL to request.
     * @param params Params to send.
     *
     * @return Response string.
     */
    public String get(String url, Map<String, String> params)
    {
        String p = WebClient.parseParams(params);

        return this.execute(url, p, "GET", new HashMap<>());
    }

    /**
     * Opens an URL connection.
     *
     * @param URL URL to open.
     */
    public URLConnection open(String URL)
    {
        try {
            URL url = new URL(URL);
            URLConnection connection = url.openConnection();

            if(this.proxy() != null) {
                connection = url.openConnection(this.proxy());
            }

            if(!this.type().isEmpty()) {
                String authStr = this.username() +":"+ this.password();
                String encoded = Base64.getEncoder().encodeToString(authStr.getBytes());

                connection.setRequestProperty("Authorization", this.type() +" "+ encoded);
            }

            return connection;
        } catch(Exception e) {
            Console.println("Couldn't open URL "+ URL);
            Console.println(e.getMessage());

            return null;
        }
    }

    /**
     * Converts a map to a HTTP params set.
     *
     * @param params Params to convert.
     *
     * @return Converted params.
     */
    public static String parseParams(Map<String, String> params)
    {
        String p = "";
        for(Map.Entry<String, String> param : params.entrySet()) {
            p += param.getKey() +"="+ param.getValue() +"&";
        }

        if(p.endsWith("&")) {
            p = p.substring(0, p.length() - 1);
        }

        return p;
    }

    /**
     * Returns proxy object.
     *
     * @return Proxy object.
     */
    public Proxy proxy()
    {
        return this._proxy;
    }

    /**
     * Sets proxy object.
     *
     * @param proxy Proxy object.
     */
    public void proxy(Proxy proxy)
    {
        this._proxy = proxy;
    }

    /**
     * Sets proxy object.
     *
     * @param host Proxy host.
     * @param port Proxy port.
     */
    public void proxy(String host, int port)
    {
        this.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)));
    }

    /**
     * Returns authentication username.
     *
     * @return Authentication username.
     */
    public String username()
    {
        return this._username;
    }

    /**
     * Sets authentication username.
     *
     * @param username New username.
     */
    public void username(String username)
    {
        this._username = username;
    }

    /**
     * Returns authentication password.
     *
     * @return Authentication password.
     */
    public String password()
    {
        return this._password;
    }

    /**
     * Sets authentication password.
     *
     * @param password New password.
     */
    public void password(String password)
    {
        this._password = password;
    }

    /**
     * Returns authentication type.
     *
     * @return Authentication type.
     */
    public String type()
    {
        return this._type;
    }

    /**
     * Sets authentication type.
     *
     * @param type New type.
     */
    public void type(String type)
    {
        this._type = type;
    }
}
