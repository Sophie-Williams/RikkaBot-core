package com.rikkabot.rikkabotcore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by piotr on 12.07.17.
 */
public class Regex {
    public static Matcher match(String regex, String input) {
        Pattern p = Pattern.compile(regex);
        final Matcher matcher = p.matcher(input);
        matcher.find();
        return matcher;
    }
}
