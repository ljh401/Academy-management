package model.dto.sns;

import java.util.ArrayList;

public class SnsDto {
	
	// 필드
	private int sno;
	private String sfile;
	private String scontent;
	private String spwd;
	private String sdate;
	
	// - 조회 대사앚와 게시물 작성자대상자 일치여부 ( 본인글인지 아닌지에대한 유효성검사 )
	private boolean ishost;
	
	ArrayList<SnsReplyDto> ReplyList;
	
	
	
	
	

	// 기본 생성자
	public SnsDto() {}

	// 풀 
	public SnsDto(int sno, String sfile, String scontent, String spwd, String sdate) {
		super();
		this.sno = sno;
		this.sfile = sfile;
		this.scontent = scontent;
		this.spwd = spwd;
		this.sdate = sdate;
	}

	// 등록할때 필요한 생성자
	public SnsDto(String sfile, String scontent, String spwd) {
		super();
		this.sfile = sfile;
		this.scontent = scontent;
		this.spwd = spwd;
	}
	

	// -- 수정할때 필요한 생성자 
	public SnsDto(int sno, String sfile, String scontent) {
		super();
		this.sno = sno;
		this.sfile = sfile;
		this.scontent = scontent;
	}
	
	// -- 개별글 출력 생성자 
	public SnsDto(int sno, String sfile, String scontent, String sdate) {
		super();
		this.sno = sno;
		this.sfile = sfile;
		this.scontent = scontent;
		this.sdate = sdate;
	}


	// 메소드
	
	public int getSno() {
		return sno;
	}	

	public boolean isIshost() {
		return ishost;
	}

	public void setIshost(boolean ishost) {
		this.ishost = ishost;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSfile() {
		return sfile;
	}

	public void setSfile(String sfile) {
		this.sfile = sfile;
	}

	public String getScontent() {
		return scontent;
	}

	public void setScontent(String scontent) {
		this.scontent = scontent;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
	public ArrayList<SnsReplyDto> getReplyList() {
		return ReplyList;
	}

	public void setReplyList(ArrayList<SnsReplyDto> replyList) {
		ReplyList = replyList;
	}

	@Override
	public String toString() {
		return "SnsDto [sno=" + sno + ", sfile=" + sfile + ", scontent=" + scontent + ", spwd=" + spwd + ", sdate="
				+ sdate + "]";
	}
	
	
	
	
	
}// class e
