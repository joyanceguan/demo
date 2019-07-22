package com.joyance.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.zip.CRC32;

/**
 * <p>内容摘要：违规记录ID生成器Util </p>
 * <p>其他说明：</p>
 * <p>创建日期：17/11/21</p>
 *
 * @author yangyamin
 * @version 1.0
 */
public class IllegalIdGenerateUtil {


    public static final Logger LOGGER = LoggerFactory.getLogger(IllegalIdGenerateUtil.class);

    /**
     * 生成协议版本号
     */
    public static final int CURRENT_VERSION = 0;

    /**
     * version占用7位,所以需要右移64-7=57位
     */
    public static final int VERSION_LEFT_SHIT_BITS = 57;

    /**
     * 分表总数
     */
    public static final int TOTAL_SHADE_TABLE_NUMBER = 1024;

    /**
     * 合并生成,取买家id的后10位,版本号为前7为,中间47个bit是sequence
     *
     * @param version    版本号
     * @param sequenceId sequence号
     * @param ownerId    违规记录归属方id
     * @return
     */
    public static long generateId(long version, long sequenceId, String ownerId) {

        long buyerIdLong = crc32(ownerId);
        //买家id 后13位
        buyerIdLong = buyerIdLong % TOTAL_SHADE_TABLE_NUMBER;

        sequenceId = sequenceId * TOTAL_SHADE_TABLE_NUMBER;

        version = version << VERSION_LEFT_SHIT_BITS;

        return (version | sequenceId | buyerIdLong);
    }

    public static long crc32(String buyerId) {
        CRC32 crc32 = new CRC32();
        crc32.update(buyerId.getBytes());
        return crc32.getValue();
    }

    /**
     * 生成违规记录id,默认当前版本号 {@link #CURRENT_VERSION '当前版本'};,取买家id ,版本号为前7为,中间47个bit是sequence
     *
     * @param sequenceId
     * @param buyerId
     * @return
     */
    public static long generateId(long sequenceId, String buyerId) {
        long illegalId = generateId(CURRENT_VERSION, sequenceId, buyerId);
        return illegalId;
    }

}
