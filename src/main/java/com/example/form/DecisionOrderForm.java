package com.example.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 入力された宛先情報を表すフォーム.
 * 
 * @author izawamotoki
 *
 */
public class DecisionOrderForm {

	/** 注文ID */
	private Integer orderId;

	/** 注文日 */
	private Date orderDate;

	/** 宛先氏名 */
	@NotBlank(message = "名前を入力してください")
	private String decisionName;

	/** 宛先Eメール */
	@Email(message = "メールアドレスの形式ではありません")
	@NotBlank(message = "メールアドレスを入力してください")
	private String decisionEmail;

	/** 宛先郵便番号 */
	@Pattern(regexp = "[0-9]{3}-[0-9]{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
	private String decisionZipcode;

	/** 宛先住所 */
	@NotBlank(message = "住所を入力してください")
	private String decisionAddress;

	/** 宛先TEL */
	@Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}$", message = "電話番号はXXXX-XXXX-XXXXの形式で入力してください")
	private String decisionTel;

	/** 配達時間 */
	@NotBlank(message = "配達日時を入力してください")
	private Timestamp deliveryTime;

	/** 支払方法 */
	private Integer paymentMethod;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDecisionName() {
		return decisionName;
	}

	public void setDecisionName(String decisionName) {
		this.decisionName = decisionName;
	}

	public String getDecisionEmail() {
		return decisionEmail;
	}

	public void setDecisionEmail(String decisionEmail) {
		this.decisionEmail = decisionEmail;
	}

	public String getDecisionZipcode() {
		return decisionZipcode;
	}

	public void setDecisionZipcode(String decisionZipcode) {
		this.decisionZipcode = decisionZipcode;
	}

	public String getDecisionAddress() {
		return decisionAddress;
	}

	public void setDecisionAddress(String decisionAddress) {
		this.decisionAddress = decisionAddress;
	}

	public String getDecisionTel() {
		return decisionTel;
	}

	public void setDecisionTel(String decisionTel) {
		this.decisionTel = decisionTel;
	}

	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
