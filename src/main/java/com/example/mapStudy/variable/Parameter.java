package com.example.mapStudy.variable;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.variable
 * @Author: Noah
 * @CreateTime: 2022-11-14  10:56
 * @Description: 可变参数
 * @Version: 1.0
 */
public class Parameter {
    //    @Test
//    public static void variableParameter() {
//        int[] array = {1, 2, 3, 4, 5};
//        List<int[]> ints = Arrays.asList(array);
//        System.out.println("数组长度：" + ints.size());
//        //java.lang.UnsupportedOperationException 继承abstractList但是没有重写add和remove方法
//        int[] arr = {6};
//        ints.add(arr);
//    }
//    @Test
//    public static void DateTe(){
//        String ss = "20221117";
//        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
//        Date aa = null;
//        try {
//            aa = yyyyMMdd.parse(ss);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(aa.toString());
//
//    }
    //获取当前时间
    @Test
    public void now() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取当前年月日（当前日期）
        LocalDate now1 = LocalDate.now();
        // 获取当前时分秒（具体时间）
        LocalTime now2 = LocalTime.now();
        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);
    }

    //获取当前时间的年月日时分秒
    @Test
    public void now1() {
        LocalDateTime now = LocalDateTime.now(); // 获取当前时间
        int year = now.getYear(); // 获取年份
        int month = now.getMonthValue(); // 获取月份
        int day = now.getDayOfMonth(); // 获取月中的天数
        int hour = now.getHour(); // 获取当前的小时
        int minute = now.getMinute(); // 获取当前分钟
        int second = now.getSecond(); // 获取当前秒数
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
    }

    // 在当前时间基础上加上对应的时间
    @Test
    public void now2() {
        LocalDateTime now = LocalDateTime.now(); // 当前时间
        LocalDateTime now1 = now.plusYears(5); // 在当前时间加上5年
        LocalDateTime now2 = now.plusMonths(5);// 在当前时间商加上5月
        LocalDateTime now3 = now.plusDays(7); // 在当前时间加上7天
        LocalDateTime now4 = now.plusHours(2); // 在当前时间加上2个小时
        LocalDateTime now5 = now.plusMinutes(30); // 在当前时间加上30分钟
        LocalDateTime now6 = now.plusSeconds(30); // 在当前时间加上30秒
        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);
        System.out.println(now3);
        System.out.println(now4);
        System.out.println(now5);
        System.out.println(now6);
    }

    // 在当前时间基础上减去对应的时间
    @Test
    public void now3() {
        LocalDateTime now = LocalDateTime.now(); // 当前时间
        LocalDateTime now1 = now.minusYears(5); // 在当前时间减上5年
        LocalDateTime now2 = now.minusMonths(5);// 在当前时间商减上5月
        LocalDateTime now3 = now.minusDays(7); // 在当前时间减上7天
        LocalDateTime now4 = now.minusHours(2); // 在当前时间减上2个小时
        LocalDateTime now5 = now.minusMinutes(30); // 在当前时间减上30分钟
        LocalDateTime now6 = now.minusSeconds(30); // 在当前时间减上30秒
        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);
        System.out.println(now3);
        System.out.println(now4);
        System.out.println(now5);
        System.out.println(now6);
    }

    //改变当前时间（年月日时分秒）
    @Test
    public void now4() {
        LocalDateTime now = LocalDateTime.now(); // 当前时间
        LocalDateTime now1 = now.withYear(2060); // 改变当前年份（变成2060年）
        LocalDateTime now2 = now.withMonth(12); // 改变当前月份（变成12月份）
        LocalDateTime now3 = now.withDayOfMonth(29); //改变当前天数（变成29日）
        LocalDateTime now4 = now.withHour(23); // 改变当前小时数（变成23时）
        LocalDateTime now5 = now.withMinute(30); // 改变当前分钟（变成30分钟）
        LocalDateTime now6 = now.withSecond(23); // 改变当前小时数（变成23时）
        LocalDateTime now7 = now.withDayOfYear(60); // 从一月一号开始加上60天
        System.out.println(now);//2022-11-18T12:30:25.507
        System.out.println(now1);//2060-11-18T12:30:25.507
        System.out.println(now2);//2022-12-18T12:30:25.507
        System.out.println(now3);//2022-11-29T12:30:25.507
        System.out.println(now4);//2022-11-18T23:30:25.507
        System.out.println(now5);//2022-11-18T12:30:25.507
        System.out.println(now6);//2022-11-18T12:30:23.507
        System.out.println(now7);//2022-03-01T12:30:25.507
    }

    //两个时间作比较
    @Test
    public void compareTo() {
        LocalDateTime now = LocalDateTime.now(); // 当前时间
        LocalDateTime now1 = now.plusYears(5); // 在当前时间加上5年
        //  给LocalDateTime 赋值
        LocalDateTime of = LocalDateTime.of(2022, 2, 5, 1, 1, 1);
        LocalDateTime of1 = LocalDateTime.of(2022, 11, 5, 1, 1, 1);
        //两个时间作比较，第一个时间减去第二个时间(如果年份相同，比较月份，月份相同比较天数，以此类推)
        int compareTo = now1.compareTo(now);
        int compareTo1 = now.compareTo(now1);
        int compareTo2 = now.compareTo(of);
        int compareTo3 = now.compareTo(of1);
        System.out.println(now);   // 2022-11-18T12:35:05.100
        System.out.println(now1); // 2027-11-18T12:35:05.100
        System.out.println(of); // 2022-02-05T01:01:01
        System.out.println(of1); // 2022-08-05T01:01:01
        System.out.println(compareTo); // 5年
        System.out.println(compareTo1); // -5年
        System.out.println(compareTo2); // 9月
        System.out.println(compareTo3); // 13天
    }

    //给LocalDateTime 赋值
    @Test
    public void ofParse() {
        //  给LocalDateTime 赋值
        LocalDateTime of = LocalDateTime.of(2022, 2, 5, 1, 1, 1);
        System.out.println(of); // 输出 2022-02-05 01:01:01
        //  给LocalDateTime 赋值 注意（text文本格式要于yyyy-MM-dd HH:mm:ss一致，不然会报错）
        LocalDateTime parse = LocalDateTime.parse("2022年02*05 01:01:01", DateTimeFormatter.ofPattern("yyyy年MM*dd HH:mm:ss"));
        System.out.println(parse); // 输出 2022-02-05 1:1:1
    }

    //时间转换
    @Test
    public void ofPattern() {
        // 字符串转时间
        String dateTime = "2021-01-05 12:00:00";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dateTime, df);
        // 时间转字符串
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter of = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime1 = now.format(of);
        String format = of.format(now);
        System.out.println(ldt); // 输出 2021-01-05T12:00
        System.out.println(dateTime1); // 输出 2022-08-13 10:15:03
        System.out.println(format);
    }

    //计算时间差
    @Test
    public void duration() {
        LocalDateTime now = LocalDateTime.now(); // 当前时间
        //  给LocalDateTime 赋值
        LocalDateTime of = LocalDateTime.of(2022, 7, 13, 1, 1, 1);
        Duration duration = Duration.between(of, now); // 后面减去前面
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数
        System.out.println(now);
        System.out.println(of);
        System.out.println(days); // 输出 31
        System.out.println(hours); // 输出 752
        System.out.println(minutes); // 输出 45179
        System.out.println(millis); // 输出 2710769393
        System.out.println(nanos); // 输出 2710863444800000
    }

    @Test
    public void getNow() {
        Date date = new Date(); // 返回当前时间戳格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss"); // 改变格式
        System.out.println(dateFormat.format(date)); // 获取当前时间
        String time = "2021-01-05 :12:00:00";
        Date parse = null;
        try {
            parse = dateFormat.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(parse);
    }


    @Test
    public void getNowCalendar() {
        Calendar cal = Calendar.getInstance(); // 返回当前时间戳格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss"); // 改变格式
        System.out.println(dateFormat.format(cal.getTime())); // 获取当前时间
        int y = cal.get(Calendar.YEAR); // 获取当前年份
        int m = cal.get(Calendar.MONTH); // 获取当前月份
        int d = cal.get(Calendar.DATE); // 获取当前日期
        int h = cal.get(Calendar.HOUR_OF_DAY); // 获取当前小时
        int mi = cal.get(Calendar.MINUTE); // 获取当前分钟
        int s = cal.get(Calendar.SECOND); // 获取当前秒数
        System.out.println("现在时刻是" + y + "年" + m + "月" + d + "日" + h + "时" + mi + "分" + s + "秒");
    }

    @Test
    public void getNows() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z"); // 改变格式
        Date date = new Date(System.currentTimeMillis()); // 返回当前时间戳格式
        System.out.println(formatter.format(date)); // 获取当前时间
    }

}
