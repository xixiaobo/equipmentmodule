package com.hcycom.jhipster.web.rest.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {
	/*

    获取当前时间之前或之后几小时 hour

   */

    public static String getTimeByHour(int hour) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    /*

    获取当前时间之前或之后几分钟 minut	e

    */

    public static String getTimeByMinute(int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, minute);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }
}
