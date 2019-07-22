package com.joyance.demo.base.excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class MixLoanUserHistoryExcel implements Serializable{

	/**
     * 贷款人Id
     */
	@ColumnNum(1)
    private String loanerId;
	
	/**
	 * 申请时间
	 */
	@ColumnNum(2)
	private Date applyTime;
	
	/**
	 * 用户状态
	 */
	@ColumnNum(3)
	private int status;
	
	/**
     * 金融Id
     */
	@ColumnNum(4)
    private String financeId;
	
	/**
     * 用户总额度
     */
	@ColumnNum(5)
    private Double totalAmount;
	
	/**
     * 授信完成时间
     */
	@ColumnNum(6)
    private Date creditFinishTime;
	
	/**
     * 首次分期支付成功时间
     */
	@ColumnNum(7)
    private Date firstPaySuccessTime;
	
	/**
     * 首次提现成功时间
     */
	@ColumnNum(8)
    private Date firstCashSuccessTime;
	
	/**
     * 贷款人姓名(冗余字段)
     */
	@ColumnNum(9)
    private String loanerName;
	
	/**
     * 身份证
     */
	@ColumnNum(10)
	private String idCardNo;
	
	/**
     * 银行预留手机号(冗余字段)
     */
	@ColumnNum(11)
    private String bankCardTelephone;

	public String getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getLoanerName() {
		return loanerName;
	}

	public void setLoanerName(String loanerName) {
		this.loanerName = loanerName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getBankCardTelephone() {
		return bankCardTelephone;
	}

	public void setBankCardTelephone(String bankCardTelephone) {
		this.bankCardTelephone = bankCardTelephone;
	}
	
    static BigDecimal hundred = new BigDecimal("100");
	
	public long totalAmount(){
		return new BigDecimal(String.valueOf(totalAmount)).multiply(hundred).longValue();
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getCreditFinishTime() {
		return creditFinishTime;
	}

	public void setCreditFinishTime(Date creditFinishTime) {
		this.creditFinishTime = creditFinishTime;
	}

	public Date getFirstPaySuccessTime() {
		return firstPaySuccessTime;
	}

	public void setFirstPaySuccessTime(Date firstPaySuccessTime) {
		this.firstPaySuccessTime = firstPaySuccessTime;
	}

	public Date getFirstCashSuccessTime() {
		return firstCashSuccessTime;
	}

	public void setFirstCashSuccessTime(Date firstCashSuccessTime) {
		this.firstCashSuccessTime = firstCashSuccessTime;
	}


}
