package com.hbicc.cloud.common.utils;
import java.io.File;


public class ToolUtil {
    /**
    * 递归创建目录
    * @param  file
    */
    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {
        file.mkdir();
        } else {
        mkDir(file.getParentFile());
        file.mkdir();
        }
    }
    /**
    * 10进制转36进制
    *
    * @param  num
    * @return
    */
    public static String tenTo36(int num) {
        String[] X36_ARRAY = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
        StringBuffer sBuffer = new StringBuffer();
        if (num == 0) {
        sBuffer.append("0");
        }
        while (num > 0) {
        sBuffer.append(X36_ARRAY[num % 36]);
        num = num / 36;
        }
        return sBuffer.reverse().toString();
    }
    /**
    * 字符串左侧自动补零
    *
    * @param  str
    * @param  strLength
    * @return
    */
    public static String strLeftPad(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
        while (strLen < strLength) {
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(str); // 左补0
            // sb.append(str).append("0"); //右补0
            str = sb.toString();
            strLen = str.length();
        }
        }
        return str;
    }
}
