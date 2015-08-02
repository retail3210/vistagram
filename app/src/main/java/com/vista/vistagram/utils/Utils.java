package com.vista.vistagram.utils;

import android.text.format.DateUtils;

import java.util.Date;

/**
 * Utility class.
 */
public class Utils {

    /**
     * Format the given date relatively to now.
     * @param date Date
     * @return formatted date
     */
    public static String formatRelative(Date date) {
        return DateUtils.getRelativeTimeSpanString(
                date.getTime(), new Date().getTime(), DateUtils.DAY_IN_MILLIS).toString();
    }
}
