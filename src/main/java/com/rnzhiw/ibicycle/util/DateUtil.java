package com.rnzhiw.ibicycle.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转换工具类
 *
 * @author hjb
 * @date 2018/11/30
 */
public class DateUtil {

    public static String date2string(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date string2date(String stringDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(stringDate);
    }

}
