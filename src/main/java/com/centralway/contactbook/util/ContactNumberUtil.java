package com.centralway.contactbook.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class ContactNumberUtil {
    private static final String DEFAULT_REGION = "CH";
    private static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public static boolean isValid(String contactNumber) {
        try {
            PhoneNumber phoneNumber = phoneNumberUtil.parseAndKeepRawInput(contactNumber, DEFAULT_REGION);
            return phoneNumberUtil.isPossibleNumber(phoneNumber) && phoneNumberUtil.isValidNumber(phoneNumber);
        } catch (NumberParseException e) {
            return false;
        }
    }

    public static String format(String contactNumber) {
        try {
            PhoneNumber phoneNumber = phoneNumberUtil.parseAndKeepRawInput(contactNumber, DEFAULT_REGION);
            return phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
        } catch (NumberParseException e) {
            return contactNumber;
        }

    }

}
