package com.example.mapStudy;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy
 * @Author: Noah
 * @CreateTime: 2022-12-02  20:37
 * @Description: TODO
 * @Version: 1.0
 */
public class Uber {
    public static void main(String[] args) {
        // 获取工作日集合（排除周末）
        List<Date> workDays = getWorkDays(2022, 10);
        // 获取工作日字符串集合
        List<String> dateString = getDateString(workDays);
        // 获取法定节假日集合
        List<String> holidays = getHolidays();
        // 从工作日中除去法定节假日
        dateString.removeAll(holidays);

        System.out.println(dateString.size());
        // 遍历纯工作日集合
        for (String date : dateString) {
            System.out.println(date);
        }
    }

    // 返回工作日集合，只排除周末
    public static List<Date> getWorkDays(int year, int month) {
        // 用于储存每月工作日
        List<Date> dates = new ArrayList<Date>();

        Calendar cal = Calendar.getInstance();
        //设置月份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 设置为当月第一天
        cal.set(Calendar.DATE, 1);


        while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
            // 判断当前天为本周的第几天
            int day = cal.get(Calendar.DAY_OF_WEEK);
            // 如果不为周六或者周天,将日期进行储存
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                dates.add((Date) cal.getTime().clone());
            }
            // 将当前日期增加一天
            cal.add(Calendar.DATE, 1);
        }
        // 返回当前月工作日集合
        return dates;


    }

    /**
     * @param dateList
     * @return 返回日期字符串集合
     */
    public static List<String> getDateString(List<Date> dateList) {
        // 储存日期字符串
        List<String> dateString = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        for (Date date : dateList) {
            String date2 = simpleDateFormat.format(date);
            dateString.add(date2);

        }
        return dateString;
    }

    /**
     * @return 返回法定节假日集合
     */
    public static List<String> getHolidays() {
        List<String> holidays = new ArrayList<>();
        holidays.add("2022-01-01");
        holidays.add("2022-01-14");
        holidays.add("2022-02-11");
        holidays.add("2022-03-21");
        holidays.add("2022-04-29");
        holidays.add("2022-04-30");
        holidays.add("2022-05-01");
        holidays.add("2022-05-02");
        holidays.add("2022-05-03");
        holidays.add("2022-05-04");
        holidays.add("2022-05-05");
        holidays.add("2022-05-06");
        holidays.add("2022-07-15");
        holidays.add("2022-08-11");
        holidays.add("2022-08-12");
        holidays.add("2022-09-16");
        holidays.add("2022-09-23");
        holidays.add("2022-10-01");
        holidays.add("2022-10-02");
        holidays.add("2022-10-03");
        holidays.add("2022-10-04");
        holidays.add("2022-10-05");
        return holidays;
    }

    @Test
    public void test() {
        List<Date> workDays = getWorkDays(2022, 10);
        workDays = workDays.stream().filter(x -> !getHolidays().contains(new SimpleDateFormat("yyyy-MM-dd").format(x))).collect(Collectors.toList());
//        workDays.stream().map(x -> new Date()).collect(Collectors.toList())
//        Strings.removeAll(getHolidays());
        int count = 1000 + workDays.size();
        int avg = count / workDays.size();
        AtomicInteger currentCount = new AtomicInteger();
        workDays.forEach(x -> {
            int m = (int) ((avg + (Math.random() * 10)) - 5);
            currentCount.addAndGet(m);
            LocalDateTime ldt = x.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            System.out.println("" +
                    ldt.format(DateTimeFormatter.ofPattern("yyyyMMdd 星期")) + getDayOfWeek(ldt.getDayOfWeek().toString()) +
                    "   21:" + getno((int) (Math.random() * 60)) + "   " + m);
        });
        System.out.println(currentCount);
    }

    private String getno(int i) {
        return (i < 10) ? ("0" + i) : i + "";
    }

    private String getDayOfWeek(String s) {
        Map<String, String> map = new HashMap<>(16);
        map.put("TUESDAY", "二");
        map.put("WEDNESDAY", "三");
        map.put("THURSDAY", "四");
        map.put("FRIDAY", "五");
        map.put("MONDAY", "一");
        return map.get(s);
    }
}
