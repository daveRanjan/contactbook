package com.centralway.contactbook.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberUtil {
    public static boolean isPhoneNumberCorrect(String pPhoneNumber) {

        Pattern pattern = Pattern.compile("((\\+[1-9]{3,4}|0[1-9]{4}|00[1-9]{3})\\-?)?\\d{8,20}");
        Matcher matcher = pattern.matcher(pPhoneNumber);

        if (matcher.matches()) return true;

        return false;
    }
}
