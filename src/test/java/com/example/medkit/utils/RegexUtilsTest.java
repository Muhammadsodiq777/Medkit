package com.example.medkit.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilsTest {

    @Test
    void isEmailAddress() {
        String emailAddress = "username@gmail.com";
        assertTrue(RegexUtils.isEmailAddress(emailAddress));
    }

    @Test
    void isPhoneNumber() {
    }

    @Test
    void isUsername() {
    }
}