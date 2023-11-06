package model.dto.sns;

public class SnsReplyDto {

	// 필드
	private int rno;            // 식별번호( 게시물번호 ) , 자동번호 부여
	private String rcontent;    // ( 사진파일 ) , 공백불가능 ,
	private String rpwd;  		// ( 비밀번호 ) , 공백불가능
	private String rdate;     	// ( 등록일 )  , 공백불가능
	private int sno; 			// ( sns내용 ) , 공백불가능 중복불가
	private int slike; 			// 좋아요 카운트				
	private int shate;			// 싫어요 카운트				


	// 기본생성자
	public SnsReplyDto() {}
	// 풀 생성자
	public SnsReplyDto(int rno, String rcontent, String rpwd, String rdate, int sno, int slike, int shate) {
		super();
		this.rno = rno;
		this.rcontent = rcontent;
		this.rpwd = rpwd;
		this.rdate = rdate;
		this.sno = sno;
		this.slike = slike;
		this.shate = shate;
	}
	
	
	// 입력할때 필요한생성자
	public SnsReplyDto(String rcontent, String rpwd, int sno) {
		super();
		this.rcontent = rcontent;
		this.rpwd = rpwd;
		this.sno = sno;
	}
	
	// 출력할때 필요한생성자
	public SnsReplyDto(int rno ,String rcontent, String rdate) {
		super();
		this.rno = rno;
		this.rcontent = rcontent;
		this.rdate = rdate;
	}

	// 출력할때 필요한생성자
	public SnsReplyDto(int rno, String rcontent, String rdate, int sno) {
		super();
		this.rno = rno;
		this.rcontent = rcontent;
		this.rdate = rdate;
		this.sno = sno;
	}
	
	// 메소드
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public String getRpwd() {
		return rpwd;
	}
	public void setRpwd(String rpwd) {
		this.rpwd = rpwd;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getSlike() {
		return slike;
	}
	public void setSlike(int slike) {
		this.slike = slike;
	}
	public int getShate() {
		return shate;
	}
	public void setShate(int shate) {
		this.shate = shate;
	}
	@Override
	public String toString() {
		return "SnsReplyDto [rno=" + rno + ", rcontent=" + rcontent + ", rpwd=" + rpwd + ", rdate=" + rdate + ", sno="
				+ sno + ", slike=" + slike + ", shate=" + shate + "]";
	}


	
	





}// class e
