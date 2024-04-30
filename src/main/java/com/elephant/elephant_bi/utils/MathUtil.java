package com.elephant.elephant_bi.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathUtil {
    /**
     * 获取字符串集合中的最大值
     * @param list
     * @return
     */
    public static int getMax(List<String> list){
        String regN = "\\d+";
        Pattern pattern = Pattern.compile(regN);
        Integer max = 0;
        for (String s:list) {
            Matcher matcher = pattern.matcher(s);
            while(matcher.find()){
                int i = Integer.parseInt(matcher.group());
                if(i > max){
                    max = i;
                }
            }
        }
        return max;
    }
}
