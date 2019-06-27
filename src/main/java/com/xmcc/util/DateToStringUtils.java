package com.xmcc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringUtils {

    public static String datetoString(Date date){

        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);

        return dateStr.replace("-","");
    }
}
