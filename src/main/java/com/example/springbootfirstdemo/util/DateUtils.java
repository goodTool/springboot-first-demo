package com.example.springbootfirstdemo.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * @NAME: wjk
 * @USER: Administrator
 * @DATE: 2021-3-3
 * @TIME: 16:04
 * @DAY_NAME_SHORT: 星期三
 **/
@Slf4j
public class DateUtils {


    /**
     * 只有一天
     * @param offset 偏移量 0当天，-1昨天
     * @return
     */
    public static List<String> getDay(LocalDate dbSysDate,int offset){
        LocalDate time = dbSysDate.plusDays(offset);
        String day = time.toString();
        day = day.replaceAll("-","");
        List<String> list = new ArrayList<>();
        list.add(day);
        return list;

    }

    /**
     *从01-昨天
     * @param
     * @return
     */
    public static List<String> getYesterday(LocalDate dbSysDate){
        LocalDate now = dbSysDate.plusDays(-1);
        int day = now.getDayOfMonth();
        String year = String.valueOf(now.getYear());
        String month = String.valueOf(now.getMonthValue());
        if( now.getMonthValue() < 10){

            month = "0" + month;
        }

        List<String> list = new ArrayList<>();
        for(int a = 1;a <= day; a++){
            String date = null;
            if(a < 10){
                date = year + ""+ month + "" + "0"+a;
            }else{
                date = year + ""+ month + "" +a;
            }
            list.add(date);

        }
        return list;

    }

    /**
     * 获取季度的每月
     * @param offset  偏移量 0当季，-1上季，1下季
     * @return
     */
    public static List<String> getQuarter(LocalDate dbSysDate,int offset) {

        LocalDate resDate = dbSysDate.plusMonths(offset * 3);
        LocalDate today = dbSysDate;
        if (today == null) {
            today = resDate;
        }
        Month month = resDate.getMonth();
        int monthValue = month.firstMonthOfQuarter().getValue();
        int year = resDate.getYear();
      /*Month month = resDate.getMonth();
         Month firstMonthOfQuarter = month.firstMonthOfQuarter();
         Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() +2);
         if (isFirst) {
          resDate = LocalDate.of(resDate.getYear(), firstMonthOfQuarter,1);
         } else {
          resDate = LocalDate.of(resDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(resDate.isLeapYear()));
         }*/
        List<String> list = new ArrayList<>();
        for(int a = 0;a < 3; a++){
            String date = null;
            if(monthValue < 10){
                date = year + ""+ "0"+ monthValue;
            }else{
                date = year + ""+ monthValue;
            }
            monthValue++;
            list.add(date);

        }
        return list;

    }

    /**
     * 获取月份的每天
     * @param offset 偏移量  0当月，-1上月
     * @param allMonth  true显示到当天，false显示整月
     * @return
     */
    public static List<String> getMonth(LocalDate dbSysDate,int offset,Boolean allMonth){
        LocalDate now = dbSysDate.plusMonths(offset);
        int day = now.getDayOfMonth();
        int dayTotal = now.lengthOfMonth(); //当月天数
        String year = String.valueOf(now.getYear());
        String month = String.valueOf(now.getMonthValue());
        if( now.getMonthValue() < 10){

            month = "0" + month;
        }
        if(allMonth){
            day = dayTotal;
        }
        List<String> list = new ArrayList<>();
        for(int a = 1;a <= day; a++){
            String date = null;
            if(a < 10){
                date = year + ""+ month + "" + "0"+a;
            }else{
                date = year + ""+ month + "" +a;
            }
            list.add(date);

        }
        return list;
    }

    /**
     * 获取年的每月
     * @return offset偏移量 0当年 ，-1上年
     * @param allYear  true显示到当月，false显示整年的月份
     */
    public static List<String> getYearMonth(LocalDate dbSysDate,int offset,Boolean allYear){

        LocalDate now = dbSysDate.plusYears(offset);
        String year = String.valueOf(now.getYear());
        int month = now.getMonthValue();
        if(allYear){
            month = 12;
        }

        List<String> list = new ArrayList<>();
        for(int a = 1;a <= month; a++){
            String date = null;
            if(a < 10){
                date = year + "0"+ a;
            }else{
                date = year + ""+ a;
            }
            list.add(date);

        }
        return list;
    }

    /**
     * 累计到上月
     *
     */
    public static List<String> getLastMonthAdd(LocalDate dbSysDate){

        LocalDate now = dbSysDate.plusMonths(-1);
        String year = String.valueOf(now.getYear());
        int month = now.getMonthValue();

        List<String> result = new ArrayList<>();
        for(int a = 1;a <= month; a++){
            String data = null;
            if(a < 10){
                data = year + "0"+ a;
            }else{
                data = year + ""+ a;
            }
            result.add(data);

        }
        return result;
    }


    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        log.info(now.toString());
        log.info("当日：{}",getDay(now,0));
        log.info("昨日：{}",getDay(now,-1));
        log.info("昨日2：{}",getYesterday(now));

        log.info("当季：{}", getQuarter(now,0));

        log.info("上季：{}", getQuarter(now,-1));


        log.info("当月：{}", getMonth(now,0,false));
        log.info("上月：{}", getMonth(now,-1,true));
        log.info("累计到上月：{}", getLastMonthAdd(now));
        log.info("当年：{}", getYearMonth(now,0,false));
        log.info("上年：{}", getYearMonth(now,-1,true));


    }



}
