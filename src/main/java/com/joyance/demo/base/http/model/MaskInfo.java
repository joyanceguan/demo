package com.joyance.demo.base.http.model;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MaskInfo implements Serializable {
    @JsonProperty("mask_id")
    private String maskId;                  // 处罚对象值

    @JsonProperty("mask_type")
    private int maskType;                   // 处罚对象维度: 卖家id/卖家银行卡/卖家身份证等

    @JsonProperty("mask_type_desc")
    private String maskTypeDesc;            //  处罚对象维度: 卖家id/卖家银行卡/卖家身份证 字符解释

    @JsonProperty("punish_type")
    private int punishType;                 // 处罚业务维度: 套现/刷单/盗刷等

    @JsonProperty("punish_type_desc")
    private String punishTypeDesc;          //  处罚业务维度: 套现/刷单/盗刷等 字符

    @JsonProperty("punish_method")
    private int punishMethod;               // 处罚策略int值

    @JsonProperty("punish_method_desc")
    private String punishMethodDesc;        // 处罚策略描述: 屏蔽/限额/删除等

    @JsonProperty("punish_arg")
    private int punishArg;                  // 处罚策略参数:

    @JsonProperty("add_time")
    private String addTime;                 // 处罚添加时间 2017-05-14 17:11:18

    @JsonProperty("exceed_time")
    private String exceedTime;              // 处罚过期时间 2017-05-14 17:11:18

    @JsonProperty("update_time")
    private String updateTime;              // 处罚更新时间 2017-05-04 17:11:18

    @JsonProperty("mark")
    private String mark;                    // 处罚备注

    @JsonProperty("operator")
    private String operator;                // 操作人或者系统

    @JsonProperty("operator_id")
    private int operatorId;

    @JsonProperty("is_force")
    private int isForceUpdate;              // 如果存在第三方处罚，是否强制调用

    @JsonProperty("extend_parameter")       // 额外扩展参数
    private String extendParameter;

    @JsonProperty("expire")
    private boolean expire;



    public MaskInfo() {
        maskId = "";
        maskType = -1;
        maskTypeDesc = "";
        punishType = -1;
        punishTypeDesc = "";
        punishMethod = -1;
        punishMethodDesc = "";
        punishArg = -1;
        addTime = null;
        exceedTime = null;   // mybatis查询为null, 未覆盖. 这里初始化为null
        updateTime = null;
        mark = "";
        operator = "";
        operatorId = -1;
        isForceUpdate = 0;
        extendParameter = "";
    }

    public MaskInfo(String maskId, int maskType) {
        this.maskId = maskId;
        this.maskType = maskType;
    }


    public String getAddTime() {
        return addTime;
    }

    // attention
    public void setAddTime(String addTime) {
        this.addTime = addTime.replaceAll("\\.\\d+", "");
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getMaskId() {
        return maskId;
    }

    public void setMaskId(String maskId) {
        this.maskId = maskId;
    }

    public int getMaskType() {
        return maskType;
    }

    public void setMaskType(int maskType) {
        this.maskType = maskType;
    }

    public int getPunishMethod() {
        return punishMethod;
    }

    public void setPunishMethod(int punishMethod) {
        this.punishMethod = punishMethod;
    }

    public int getPunishArg() {
        return punishArg;
    }

    public void setPunishArg(int punishArg) {
        this.punishArg = punishArg;
    }

    public String getExceedTime() {
        return exceedTime;
    }

    public void setExceedTime(String exceedTime) {
        if (null != exceedTime && !"".equals(exceedTime)) {
            this.exceedTime = exceedTime.replaceAll("\\.\\d+", "");
        }
    }

    public int getPunishType() {
        return punishType;
    }

    public void setPunishType(int punishType) {
        this.punishType = punishType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime.replaceAll("\\.\\d+", "");
    }

    public String getPunishMethodDesc() {
        return punishMethodDesc;
    }

    public void setPunishMethodDesc(String punishMethodDesc) {
        this.punishMethodDesc = punishMethodDesc;
    }


    public int getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(int isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }

    public String getExtendParameter() {
        return extendParameter;
    }

    public void setExtendParameter(String extendParameter) {
        this.extendParameter = extendParameter;
    }

    public String getPunishTypeDesc() {
        return punishTypeDesc;
    }

    public void setPunishTypeDesc(String punishTypeDesc) {
        this.punishTypeDesc = punishTypeDesc;
    }

    public String getMaskTypeDesc() {
        return maskTypeDesc;
    }

    public void setMaskTypeDesc(String maskTypeDesc) {
        this.maskTypeDesc = maskTypeDesc;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }
    
    public static void main(String[] args) {
		System.out.println((1 << 29) - 1);
	}
}
