package com.example.medkit.utils;

import java.util.regex.Pattern;

public class RegexUtils {

    public final static String EMAIL_REGEX = "^(.+)@(\\S+)$";
    public static boolean isEmailAddress(String email) {
        return Pattern.compile(EMAIL_REGEX)
                .matcher(email)
                .matches();
    }

    public static boolean isPhoneNumber(String phoneNumber) {
        return phoneNumber.startsWith("+998");
    }

    public static boolean isUsername(String username) {
        return isPhoneNumber(username) || isEmailAddress(username);
    }
}
