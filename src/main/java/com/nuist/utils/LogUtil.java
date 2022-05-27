package com.nuist.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志生成
 */
public class LogUtil {

    //定义记录日志的方法
    public static void Log(String msg){
        try {
            //日期当前时间
            Date nowTime = new Date();
            String curTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date() );

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
            String strTime = sdf.format(nowTime);
            //指向一个日志文件
            PrintStream out = new PrintStream(new FileOutputStream("D:\\Logs\\" + curTime +"-log.txt",true));
            //改变一个输出方向
            System.setOut(out);
            System.out.println(strTime+": "+msg);

        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
