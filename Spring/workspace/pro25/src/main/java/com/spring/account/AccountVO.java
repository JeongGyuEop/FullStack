package com.spring.account;


//DB에서 조회한 레코드 정보 저장할 용도의 vo
public class AccountVO {
	
	private String accountNo;//계좌번호
	private String custName;//계좌명
	private int balance;//계좌잔고
	
	public AccountVO() {}

	public AccountVO(String accountNo, String custName, int balance) {
		super();
		this.accountNo = accountNo;
		this.custName = custName;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
