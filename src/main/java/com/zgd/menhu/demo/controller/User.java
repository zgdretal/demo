package com.zgd.menhu.demo.controller;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class User {
    private String ddd;
    private String levelName;
    private String mingchen;
    private String name;
    private String name11;
    private String family;
    private String family11;
    private String birthLocaltion;

    private String birthDay;

    private String deadDay;

    private String fengmuLocaltion;
    private String fengmuLocaltionFangxiang;
    private String fengmuLocaltionFangxiang11;

    private String currentLocaltion;

    private String zhiye;

    private String officeName;

    private String xuewei;

    private String jiuduyu;

    private String mark;


    /****
     * 隔断====
     */
    private Integer level;

    private Integer realLevel;

    private String sexChinaName;

    private String father;

    private Integer familyLevel;

    private String husbandName;

    public static Integer getFamilyLevel(String family) {
        if (StringUtils.isEmpty(family)) {
            return 0;
        }

        if (family.contains("之子")){
            return 1;
        }

        if (family.contains("长子")){
            return 1;
        }

        if (family.contains("之长子")){
            return 1;
        }
        if (family.contains("之次子")){
            return 2;
        }
        if (family.contains("之三子")){
            return 3;
        }
        if (family.contains("之四子")){
            return 4;
        }
        if (family.contains("之五子")){
            return 5;
        }
        if (family.contains("之六子")){
            return 6;
        }
        if (family.contains("之七子")){
            return 7;
        }


        if (family.contains("之长女")){
            return 1;
        }

        if (family.contains("长女")){
            return 1;
        }

        if (family.contains("之次女")){
            return 2;
        }
        if (family.contains("之三女")){
            return 3;
        }
        if (family.contains("之四女")){
            return 4;
        }
        if (family.contains("之五女")){
            return 5;
        }
        if (family.contains("之六女")){
            return 6;
        }
        if (family.contains("之七女")){
            return 7;
        }
        return 100;
    }


    public static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }




}
