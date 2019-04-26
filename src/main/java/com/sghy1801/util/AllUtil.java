package com.sghy1801.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AllUtil {
    /**
     * 获取两个时间的时间差，精确到毫秒
     * @param start
     * @param end
     * @return
     */
    public static String TimeDifference(long start, long end) {

        long between = end - start;
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);
        String timeDifference = day + "天" + hour + "小时" + min + "分" + s + "秒" + ms
                + "毫秒";
        return timeDifference;
    }





    /**
     * 日期减几日
     */
    public static String dateMinusMonth(String str,int day) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = sdf.parse(str);//将字符串生成Date
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);//使用给定的 Date 设置此 Calendar 的时间。
        rightNow.add(Calendar.DATE, day);// 日期减1天
        Date dt1 = rightNow.getTime();//返回一个表示此 Calendar 时间值的 Date 对象。
        String reStr = sdf.format(dt1);//将给定的 Date 格式化为日期/时间字符串，并将结果添加到给定的 StringBuffer。
        return reStr;
    }
}
