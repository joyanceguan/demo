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
public @interface ColumnRowValue {
    /**
     * 行,从1开始
     * @return
     */
    int row();

    /**
     * 列,从1开始
     * @return
     */
    int col();
}
