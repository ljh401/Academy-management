package model.dto;

public class MemberDto { // 회원 Dto

	// 필드
	private int mno;      		// 회원번호
	private String mname;  		// 회원이름
	private String mid;  		// 회원아이디
	private String mpwd;		// 회원비밀번호
	private String memail; 		// 회원이메일
	private String mpayinfo; 	// 결제카드번호
	private String mdate;		// 회원등록일
	private String mphoto;		// 회원프로필사진

	// 셍성자
	public MemberDto() {}








	//수정할때
	public MemberDto(String mname, String mid, String mpwd, String memail, String mpayinfo, String mphoto) {
		super();
		this.mname = mname;
		this.mid = mid;
		this.mpwd = mpwd;
		this.memail = memail;
		this.mpayinfo = mpayinfo;
		this.mphoto = mphoto;
	}



	// 등록할때 필요한 생성자
	public MemberDto(String mname, String mid, String mpwd, String memail, String mpayinfo) {
		super();
		this.mname = mname;
		this.mid = mid;
		this.mpwd = mpwd;
		this.memail = memail;
		this.mpayinfo = mpayinfo;
	}


	//pwd를 제외한 생성자
	public MemberDto(int mno, String mname, String mid, String memail, String mpayinfo, String mdate, String mphoto) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.mid = mid;
		this.memail = memail;
		this.mpayinfo = mpayinfo;
		this.mdate = mdate;
		this.mphoto = mphoto;
	}



	// 메소드
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpwd() {
		return mpwd;
	}
	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMpayinfo() {
		return mpayinfo;
	}
	public void setMpayinfo(String mpayinfo) {
		this.mpayinfo = mpayinfo;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getMphoto() {
		return mphoto;
	}
	public void setMphoto(String mphoto) {
		this.mphoto = mphoto;
	}


}// class e
