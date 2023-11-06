package model.dto;

import java.util.HashMap;
import java.util.Map;

public class BoardDto {
	/*
	# 게시판(공지사항/이벤트)
	drop table if exists board;
	create table board (
	   bno int auto_increment,
	   btarget varchar(2) not null,
	   btype varchar(3) not null,
	   btitle varchar(100) not null,
	   bcontent text not null,
	   bdate datetime default now(),
	   bview int ,
	   bstartdate datetime,
	   benddate datetime,
	    primary key(bno)
	);


	drop table if exists boardfiles;
	create table boardfiles(
	   bfno int auto_increment,
	    bno int,
	    bfile longtext,
	    primary key(bfno),
	    foreign key(bno) references board(bno) on delete cascade
	);*/

	private int bno;			//게시물번호
	private String btarget;		//게시물의 이용자 (사용자인지 라이더인지)
	private String btype;		//게시물의 종류 ( 이벤트인지 공지사항인지)
	private String btitle;		//게시물의 제목
	private String bcontent;	//게시물의 내용
	private String bdate;		//게시물작성일자
	private int bview;			//게시물의 조회수
	private String bstartdate;	//게시물이 이벤트인경우 이벤트 시작일
	private String benddate;	//게시물이 이벤트인경우 이벤트 종료일
	private Map<Integer,String>  fileList = new HashMap<>();	// 첨부파일 이름.경로
	private int bfno;			//첨부파일 번호
	private String bfile;		//첨부파일명

	public BoardDto() {
		// TODO Auto-generated constructor stub
	}
	// 풀생성자
	public BoardDto(int bno, String btarget, String btype, String btitle, String bcontent, String bdate, int bview,
			String bstartdate, String benddate, Map<Integer, String> fileList) {
		super();
		this.bno = bno;
		this.btarget = btarget;
		this.btype = btype;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bview = bview;
		this.bstartdate = bstartdate;
		this.benddate = benddate;
		this.fileList = fileList;

	}

	//게시물 저장용
	public BoardDto(String btype,String btarget,  String btitle, String bcontent, String bstartdate, String benddate,
			Map<Integer, String> fileList) {
		super();
		this.btype = btype;
		this.btarget = btarget;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bstartdate = bstartdate;
		this.benddate = benddate;
		this.fileList = fileList;
	}

	//리스트 출력용
	public BoardDto(int bno, String btarget, String btype, String btitle, String bdate, int bview, String bstartdate,
			String benddate) {
		super();
		this.bno = bno;
		this.btarget = btarget;
		this.btype = btype;
		this.btitle = btitle;
		this.bdate = bdate;
		this.bview = bview;
		this.bstartdate = bstartdate;
		this.benddate = benddate;
	}

	//상세보기 출력용
	public BoardDto(int bno, String btarget, String btype, String btitle, String bcontent, String bdate, int bview,
			String bstartdate, String benddate, int bfno, String bfile) {
		super();
		this.bno = bno;
		this.btarget = btarget;
		this.btype = btype;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bview = bview;
		this.bstartdate = bstartdate;
		this.benddate = benddate;
		this.bfno = bfno;
		this.bfile = bfile;
	}
	//Getter & Setter
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtarget() {
		return btarget;
	}
	public void setBtarget(String btarget) {
		this.btarget = btarget;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public int getBview() {
		return bview;
	}
	public void setBview(int bview) {
		this.bview = bview;
	}
	public String getBstartdate() {
		return bstartdate;
	}
	public void setBstartdate(String bstartdate) {
		this.bstartdate = bstartdate;
	}
	public String getBenddate() {
		return benddate;
	}
	public void setBenddate(String benddate) {
		this.benddate = benddate;
	}
	public Map<Integer, String> getFileList() {
		return fileList;
	}
	public void setFileList(Map<Integer, String> fileList) {
		this.fileList = fileList;
	}
	public int getBfno() {
		return bfno;
	}
	public void setBfno(int bfno) {
		this.bfno = bfno;
	}
	public String getBfile() {
		return bfile;
	}
	public void setBfile(String bfile) {
		this.bfile = bfile;
	}


	@Override
	public String toString() {
		return "BoardDto [bno=" + bno + ", btarget=" + btarget + ", btype=" + btype + ", btitle=" + btitle
				+ ", bcontent=" + bcontent + ", bdate=" + bdate + ", bview=" + bview + ", bstartdate=" + bstartdate
				+ ", benddate=" + benddate + ", fileList=" + fileList + ", bfno=" + bfno + ", bfile=" + bfile + "]";
	}



}
