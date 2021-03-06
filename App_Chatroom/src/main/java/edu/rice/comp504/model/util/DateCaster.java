package edu.rice.comp504.model.util;

import edu.rice.comp504.model.message.AMessage;
import edu.rice.comp504.model.message.Notification;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCaster {

    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String DateToStr(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(date);
    }


}
