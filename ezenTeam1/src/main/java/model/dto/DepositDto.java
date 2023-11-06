package model.dto;

public class DepositDto {// 결제 Dto

	// 필드 
	private int dno; 		// 입금번호
	private int rno;     	// 라이더번호
	private String ddate;	// 입금일
	private int ddeposit;	// 입금액
	
	// 생성자 
	public DepositDto() {}
	// 풀 

	public DepositDto(int dno, int rno, String ddate, int ddeposit) {
		super();
		this.dno = dno;
		this.rno = rno;
		this.ddate = ddate;
		this.ddeposit = ddeposit;
	}
	// 메소드 
	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getDdate() {
		return ddate;
	}

	public void setDdate(String ddate) {
		this.ddate = ddate;
	}

	public int getDdeposit() {
		return ddeposit;
	}

	public void setDdeposit(int ddeposit) {
		this.ddeposit = ddeposit;
	}

	@Override
	public String toString() {
		return "DepositDto [dno=" + dno + ", rno=" + rno + ", ddate=" + ddate + ", ddeposit=" + ddeposit + "]";
	}
	
	
}// c e
