package com.taotao.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理字符串的工具
 * @author: xianzhixianzhixian on 2018/8/20
 */
public class StrUtil {

    /**
     * 从字符串中获取Long类型数组
     * @param str
     * @param splitCharacter
     * @return
     */
    public static List<Long> StringToLongArray(String str, String splitCharacter){
        if(testTrimEmpty(str)){
            return null;
        }

        String[] strArr = str.split(splitCharacter);
        List<Long> list = new ArrayList<>();
        for(String strIn : strArr){
            list.add(Long.valueOf(strIn));
        }
        return list;
    }

    /**
     * 判断字符串是否为null或长度为0
     * @param str
     * @return
     */
    public static Boolean testEmpty(String str){
        if(str == null || str.length() == 0){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断字符串trim后是否为null或长度为0
     * @param str
     * @return
     */
    public  static Boolean testTrimEmpty(String str){
        if(str == null || str.trim().length() == 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
