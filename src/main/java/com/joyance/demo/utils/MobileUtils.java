package com.joyance.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号正则校验 类
 *
 * @author weilai
 * @since 1.3.0
 */
public class MobileUtils {

    /**
     * 验证手机号格式
     * @param mobile 有效返回true,否则返回false
     * @return 有效返回true,否则返回false
     */
    public static boolean isLegalMobile(String mobile) {
        Pattern p = Pattern.compile("^((\\+86)|(86))?(1)\\d{10}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

}
