package com.cloudcc.frame.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/*
* 写工具类的
* 程序里面的类
* */
public class test {
    public static void main(String[] args) {
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//转换格式
        String VisitDate = sdf.format(new Date());

        System.out.println(VisitDate);
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String theday = sf.format(date);
//        String substring = theday.substring(5);
//        System.out.println(substring);

        String khmc = "28岁";
        int i = Integer.parseInt(khmc);
        System.out.println(i);
    }
}
