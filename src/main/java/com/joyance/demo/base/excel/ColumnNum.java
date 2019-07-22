package com.joyance.demo.base.excel;

import java.lang.annotation.*;

/**
 * excel 自定义注解
 *
 * @author weilai
 * @since 1.5.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ColumnNum {
    int value();

    /**
     * 列表名
     * @return
     */
    String title() default "其他";
}
