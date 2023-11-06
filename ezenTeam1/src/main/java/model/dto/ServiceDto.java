package model.dto;

public class ServiceDto {// 서비스 Dt
	

	// 필드
	private int sno;			// 서비스번호
	private int mno; 			// 회원번호
	private int rno;     		// 라이더번호
	private String sdate; 		// 서비스이용일
	private String sfromla; 	// 서비스 시작위치 위도
	private String sfromlo;		// 서비스 시작위치 경도
	private String stola;		// 서비스 도착위치 위도
	private String stolo;		// 서비스 도착위치 경도
	private int spayment; 		// 서비스결제금액
	private String spayYN;		// 결제여부
	private String sreview;		// 리뷰
	private int spoint;			// 별점
	private String type;			// 타입
	private String 요청내용;		// 요청내용
	private Destination 목적지;		// 목적지
	private Departure 출발지;		// 출발지
	private String sriderla;		// 라이더 출발위치 위도
	private String sriderlo;		// 라이더 출발위치 경도
	private String sDepositYN;		//라이더 입금여부

	// 조인할라고 필드만 만듬
	private String rname;
	private String rphoto;
	private String rbikenum;

	private String ddate;		// deposit 테이블 조인시 입금일


	public static class Destination {
	    private String 장소명;
	    private String 주소;

		public String get장소명() {
			return 장소명;
		}
		public void set장소명(String 장소명) {
			this.장소명 = 장소명;
		}
		public String get주소() {
			
			return 주소;
		}
		public void set주소(String 주소) {
			this.주소 = 주소;
		}
	}

	 public static class Departure {
        private String address_name;
        private String region_1depth_name;
        private String region_2depth_name;
        private String region_3depth_name;
        private String mountain_yn;
        private String main_address_no;
        private String sub_address_no;
        private String zip_code;
		public String getAddress_name() {
			return address_name;
		}
		public void setAddress_name(String address_name) {
			this.address_name = address_name;
		}
		public String getRegion_1depth_name() {
			return region_1depth_name;
		}
		public void setRegion_1depth_name(String region_1depth_name) {
			this.region_1depth_name = region_1depth_name;
		}
		public String getRegion_2depth_name() {
			return region_2depth_name;
		}
		public void setRegion_2depth_name(String region_2depth_name) {
			this.region_2depth_name = region_2depth_name;
		}
		public String getRegion_3depth_name() {
			return region_3depth_name;
		}
		public void setRegion_3depth_name(String region_3depth_name) {
			this.region_3depth_name = region_3depth_name;
		}
		public String getMountain_yn() {
			return mountain_yn;
		}
		public void setMountain_yn(String mountain_yn) {
			this.mountain_yn = mountain_yn;
		}
		public String getMain_address_no() {
			return main_address_no;
		}
		public void setMain_address_no(String main_address_no) {
			this.main_address_no = main_address_no;
		}
		public String getSub_address_no() {
			return sub_address_no;
		}
		public void setSub_address_no(String sub_address_no) {
			this.sub_address_no = sub_address_no;
		}
		public String getZip_code() {
			return zip_code;
		}
		public void setZip_code(String zip_code) {
			this.zip_code = zip_code;
		}



    }

	// 생성자
	public ServiceDto() {}
	// 풀
	public ServiceDto(int sno, int mno, int rno, String sdate, String sfromla, String sfromlo, String stola,
			String stolo, int spayment, String spayYN, String sreview, int spoint, String type, String 요청내용,
			Destination 목적지, Departure 출발지, String sriderla, String sriderlo) {
		super();
		this.sno = sno;
		this.mno = mno;
		this.rno = rno;
		this.sdate = sdate;
		this.sfromla = sfromla;
		this.sfromlo = sfromlo;
		this.stola = stola;
		this.stolo = stolo;
		this.spayment = spayment;
		this.spayYN = spayYN;
		this.sreview = sreview;
		this.spoint = spoint;
		this.type = type;
		this.요청내용 = 요청내용;
		this.목적지 = 목적지;
		this.출발지 = 출발지;
		this.sriderla = sriderla;
		this.sriderlo = sriderlo;
	}
	// 라이더가 콜을 누르기 전에 저장되는 생성자
	public ServiceDto(int mno, String sfromla, String sfromlo, String stola, String stolo) {
		super();
		this.mno = mno;
		this.sfromla = sfromla;
		this.sfromlo = sfromlo;
		this.stola = stola;
		this.stolo = stolo;
	}



	// 서비스 정보 조회 생성자
	public ServiceDto(int sno, int rno, String sdate, String sreview, int spoint) {
		super();
		this.sno = sno;
		this.rno = rno;
		this.sdate = sdate;
		this.sreview = sreview;
		this.spoint = spoint;
	}
	// 서비스이용현황 조희 생성자
	public ServiceDto(int sno, int mno, int rno, String sdate, String sfromla, String sfromlo, String stola,
			String stolo) {
		super();
		this.sno = sno;
		this.mno = mno;
		this.rno = rno;
		this.sdate = sdate;
		this.sfromla = sfromla;
		this.sfromlo = sfromlo;
		this.stola = stola;
		this.stolo = stolo;
	}
	// 입급내역 조회 리스트
	public ServiceDto(int sno, int rno, String sdate, int spayment ) {
		super();
		this.sno = sno;
		this.rno = rno;
		this.sdate = sdate;
		this.spayment = spayment;
	}



	//입금내역, 주행기록 리스트

	public ServiceDto(int sno, int mno, int rno, String sdate, String sfromla, String sfromlo, String stola,
			String stolo, int spayment, String spayYN, String sDepositYN, String ddate) {
		super();
		this.sno = sno;
		this.mno = mno;
		this.rno = rno;
		this.sdate = sdate;
		this.sfromla = sfromla;
		this.sfromlo = sfromlo;
		this.stolo = stolo;
		this.stola = stola;
		this.spayment = spayment;
		this.spayYN = spayYN;
		this.sDepositYN = sDepositYN;
		this.ddate = ddate;
	}
	// 라이더가 콜을 누른 후에 수정
	public ServiceDto(int sno, int rno, String sriderla, String sriderlo) {
		super();
		this.sno = sno;
		this.rno = rno;
		this.sriderla = sriderla;
		this.sriderlo = sriderlo;
	}
	// 라이더가 콜을 누른 후에 조회
	public ServiceDto(int sno, int rno, String sriderla, String sriderlo, String rname, String rphoto,
			String rbikenum) {
		super();
		this.sno = sno;
		this.rno = rno;
		this.sriderla = sriderla;
		this.sriderlo = sriderlo;
		this.rname = rname;
		this.rphoto = rphoto;
		this.rbikenum = rbikenum;
	}



	// 메소드
	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getSfromla() {
		return sfromla;
	}
	public void setSfromla(String sfromla) {
		this.sfromla = sfromla;
	}
	public String getSfromlo() {
		return sfromlo;
	}
	public void setSfromlo(String sfromlo) {
		this.sfromlo = sfromlo;
	}
	public String getStola() {
		return stola;
	}
	public void setStola(String stola) {
		this.stola = stola;
	}
	public String getStolo() {
		return stolo;
	}
	public void setStolo(String stolo) {
		this.stolo = stolo;
	}
	public int getSpayment() {
		return spayment;
	}
	public void setSpayment(int spayment) {
		this.spayment = spayment;
	}
	
	public String getSpayYN() {
		return spayYN;
	}
	public void setSpayYN(String spayYN) {
		this.spayYN = spayYN;
	}
	public String getSreview() {
		return sreview;
	}
	public void setSreview(String sreview) {
		this.sreview = sreview;
	}
	public int getSpoint() {
		return spoint;
	}
	public void setSpoint(int spoint) {
		this.spoint = spoint;
	}
	public String get요청내용() {
        return 요청내용;
    }

    public void set요청내용(String 요청내용) {
        this.요청내용 = 요청내용;
    }


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public Destination get목적지() {
		return 목적지;
	}
	public void set목적지(Destination 목적지) {
		this.목적지 = 목적지;
	}
	public Departure get출발지() {
		return 출발지;
	}
	public void set출발지(Departure 출발지) {
		this.출발지 = 출발지;
	}


	public String getSriderla() {
		return sriderla;
	}
	public void setSriderla(String sriderla) {
		this.sriderla = sriderla;
	}
	public String getSriderlo() {
		return sriderlo;
	}
	public void setSriderlo(String sriderlo) {
		this.sriderlo = sriderlo;
	}


	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRphoto() {
		return rphoto;
	}
	public void setRphoto(String rphoto) {
		this.rphoto = rphoto;
	}
	public String getRbikenum() {
		return rbikenum;
	}
	public void setRbikenum(String rbikenum) {
		this.rbikenum = rbikenum;
	}


	public String getsDepositYN() {
		return sDepositYN;
	}
	public void setsDepositYN(String sDepositYN) {
		this.sDepositYN = sDepositYN;
	}


	public String getDdate() {
		return ddate;
	}
	public void setDdate(String ddate) {
		this.ddate = ddate;
	}
	@Override
	public String toString() {
		return "ServiceDto [sno=" + sno + ", mno=" + mno + ", rno=" + rno + ", sdate=" + sdate + ", sfromla=" + sfromla
				+ ", sfromlo=" + sfromlo + ", stola=" + stola + ", stolo=" + stolo + ", spayment=" + spayment
				+ ", spayYN=" + spayYN + ", sreview=" + sreview + ", spoint=" + spoint + ", type=" + type + ", 요청내용="
				+ 요청내용 + ", 목적지=" + 목적지 + ", 출발지=" + 출발지 + ", sriderla=" + sriderla + ", sriderlo=" + sriderlo
				+ ", sDepositYN=" + sDepositYN + ", rname=" + rname + ", rphoto=" + rphoto + ", rbikenum=" + rbikenum
				+ "]";
	}


}// class
