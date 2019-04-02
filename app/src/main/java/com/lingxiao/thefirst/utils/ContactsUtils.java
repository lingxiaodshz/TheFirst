package com.lingxiao.thefirst.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;

/**
 * @author luckw
 * @date 2019/3/29
 */
public class ContactsUtils {
    public static final String[] PHONES_PROJECTION = new String[] {
            Phone.DISPLAY_NAME, Phone.NUMBER };
    public static final int PHONES_DISPLAY_NAME_INDEX = 0;

    public static final int PHONES_NUMBER_INDEX = 1;

    // 判断手机号是不是+86开头
    public static boolean checkPhoneNumeberCountryCode(String phone) {
        if (!TextUtils.isEmpty(phone) && phone.startsWith("+86")) {
            return true;
        }
        return false;
    }

    public static String trimPhoneNumber(String phone) {
        phone = phone.trim();
        if (phone.contains(" ")) {
            phone = phone.replaceAll(" ", "");
        }
        if (phone.contains("-")) {
            phone = phone.replaceAll("-", "");
        }
        if (phone.startsWith("+86")) {
            phone = phone.substring(3);
        }
        return phone;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
