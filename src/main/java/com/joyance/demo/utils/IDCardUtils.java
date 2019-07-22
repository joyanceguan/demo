package com.joyance.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 身份证号判断和取出年龄函数
 *
 * @author weizijing
 * @since 1.0.0
 **/
public class IDCardUtils {


    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

        boolean matches = IDNumber.matches(regularExpression);

        //判断第18位校验值
        if (matches) {
            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    return idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase());
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return matches;
    }


    /**
     * 不是身份证的话 年龄返回为-1
     *
     * @param idNO 身份证号
     * @return 身份证的年龄 只根据年数判断
     */
    public static int idNoToAge(String idNO) {
        if (isIDNumber(idNO)) {
            int leh = idNO.length();
            String dates;
            if (leh == 18) {
                dates = idNO.substring(6, 10);
            } else {
                dates = "19" + idNO.substring(6, 8);
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(new Date());
            int u = Integer.parseInt(year) - Integer.parseInt(dates);
            return u;
        } else {
            return -1;
        }
    }

    /**
     * 根据身份证号判断性别
     *
     * @param idNO 身份证号
     * @return 性别
     */
    public static String judgeSexFromIdNO(String idNO) {
        if (isIDNumber(idNO)) {
            String sex;
            if (idNO.length() == 15) {
                sex = idNO.substring(14, 15);
            } else {
                sex = idNO.substring(16, 17);
            }
            int iSex = Integer.parseInt(sex);
            iSex %= 2;
            if (iSex == 0) {
                return "女士";
            } else {
                return "先生";
            }
        } else {
            return "";
        }
    }
}
