package model.dto;

public class RiderDto {// 기사 Dto

	// 필드 
	private int rno;      			// 라이더번호              	
	private String rname; 			// 라이더이름              		
	private String rid;				// 라이더아이디		
	private String rpwd; 			// 라이더비밀번호 
	private String rphone;			// 라이더전화번호 
	private String rphoto;			// 라이더프로필사진 //이미지
	private String rlicense;		// 면허증  //이미지
	private String rregistration;	// 차량등록증  //이미지
	private String rdate;			// 등록일
	private String raccount; 		// 계좌번호
	private String rbank;			// 은행
	private String rstatus;			// 승인상태
	private String rcomment;		// 승인거부시 사유
	private String rbikenum;		// 라이더 차량번호 
	private String rstart;    		// 라이더출근상태  
	private String rcall;   		// 라이더콜가능상태
	private String type;			// 타입
	private String accept;			// 라이더가 사용자 요청 수락했을때.

	
	// 생성자
	public RiderDto() {}
	// 풀 
	public RiderDto(int rno, String rname, String rid, String rpwd, String rphone, String rphoto, String rlicense,
			String rregistration, String rdate, String raccount, String rbank, String rstatus, String rcomment,
			String rbikenum, String rstart, String rcall, String type, String accept) {
		super();
		this.rno = rno;
		this.rname = rname;
		this.rid = rid;
		this.rpwd = rpwd;
		this.rphone = rphone;
		this.rphoto = rphoto;
		this.rlicense = rlicense;
		this.rregistration = rregistration;
		this.rdate = rdate;
		this.raccount = raccount;
		this.rbank = rbank;
		this.rstatus = rstatus;
		this.rcomment = rcomment;
		this.rbikenum = rbikenum;
		this.rstart = rstart;
		this.rcall = rcall;
		this.type = type;
		this.accept = accept;
		
	}
	// 라이더 로그인 객체 만들때 필요한 생성자 
	public RiderDto(int rno, String rname, String rid, String rphone, String rphoto, String rlicense,
			String rregistration, String raccount, String rbank, String rstatus, String rbikenum, String rstart,
			String rcall) {
		super();
		this.rno = rno;
		this.rname = rname;
		this.rid = rid;
		this.rphone = rphone;
		this.rphoto = rphoto;
		this.rlicense = rlicense;
		this.rregistration = rregistration;
		this.raccount = raccount;
		this.rbank = rbank;
		this.rstatus = rstatus;
		this.rbikenum = rbikenum;
		this.rstart = rstart;
		this.rcall = rcall;
	}
	

	// 등록/회원가입 할때 필요한 생성자 
	
	public RiderDto(String rname, String rid, String rpwd, String rphone, String rphoto, String rlicense,
			String rregistration, String raccount, String rbank, String rbikenum) {
		super();
		this.rname = rname;
		this.rid = rid;
		this.rpwd = rpwd;
		this.rphone = rphone;
		this.rphoto = rphoto;
		this.rlicense = rlicense;
		this.rregistration = rregistration;
		this.raccount = raccount;
		this.rbank = rbank;
		this.rbikenum = rbikenum;
	}
	
	
	
	// 라이더 간단한 정보 출력할때 생성자
	public RiderDto(int rno, String rid, String rdate) {
		super();
		this.rno = rno;
		this.rid = rid;
		this.rdate = rdate;
	}
	
	
	
	
	// 라이더가 사용자에 대한 콜을 수락했을때 생성자

	public RiderDto(int rno, String rname, String rphone, String rphoto, String accept) {
		super();
		this.rno = rno;
		this.rname = rname;
		this.rphone = rphone;
		this.rphoto = rphoto;
		this.accept = accept;
		
	}

	public RiderDto(int rno, String rstatus) {
		super();
		this.rno = rno;
		this.rstatus = rstatus;
	}
	
	// 라이더가 사용자한테 콜 수락했을때 가져와서 조회할때.
	public RiderDto(int rno, String rname, String rphoto, String rbikenum) {
		super();
		this.rno = rno;
		this.rname = rname;
		this.rphoto = rphoto;
		this.rbikenum = rbikenum;
	}
	// 메소드 
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getRpwd() {
		return rpwd;
	}
	public void setRpwd(String rpwd) {
		this.rpwd = rpwd;
	}
	public String getRphone() {
		return rphone;
	}
	public void setRphone(String rphone) {
		this.rphone = rphone;
	}
	public String getRphoto() {
		return rphoto;
	}
	public void setRphoto(String rphoto) {
		this.rphoto = rphoto;
	}
	public String getRlicense() {
		return rlicense;
	}
	public void setRlicense(String rlicense) {
		this.rlicense = rlicense;
	}
	public String getRregistration() {
		return rregistration;
	}
	public void setRregistration(String rregistration) {
		this.rregistration = rregistration;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getRaccount() {
		return raccount;
	}
	public void setRaccount(String raccount) {
		this.raccount = raccount;
	}
	public String getRbank() {
		return rbank;
	}
	public void setRbank(String rbank) {
		this.rbank = rbank;
	}
	public String getRstatus() {
		return rstatus;
	}
	public void setRstatus(String rstatus) {
		this.rstatus = rstatus;
	}
	public String getRcomment() {
		return rcomment;
	}
	public void setRcomment(String rcomment) {
		this.rcomment = rcomment;
	}
	public String getRbikenum() {
		return rbikenum;
	}
	public void setRbikenum(String rbikenum) {
		this.rbikenum = rbikenum;
	}
	public String getRstart() {
		return rstart;
	}
	public void setRstart(String rstart) {
		this.rstart = rstart;
	}
	public String getRcall() {
		return rcall;
	}
	public void setRcall(String rcall) {
		this.rcall = rcall;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	
	
	@Override
	public String toString() {
		return "RiderDto [rno=" + rno + ", rname=" + rname + ", rid=" + rid + ", rpwd=" + rpwd + ", rphone=" + rphone
				+ ", rphoto=" + rphoto + ", rlicense=" + rlicense + ", rregistration=" + rregistration + ", rdate="
				+ rdate + ", raccount=" + raccount + ", rbank=" + rbank + ", rstatus=" + rstatus + ", rcomment="
				+ rcomment + ", rbikenum=" + rbikenum + ", rstart=" + rstart + ", rcall=" + rcall + ", type=" + type
				+ ", accept=" + accept + " ]";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// class e
