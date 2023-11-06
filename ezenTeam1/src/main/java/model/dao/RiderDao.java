
package model.dao;

import java.util.ArrayList;

import model.dto.RiderDto;
import model.dto.ServiceDto;


public class RiderDao extends Dao{ // 라이더 

	// 0. 싱글톤 
	private static RiderDao riderDao = new RiderDao();
	private RiderDao() {}
	public static RiderDao getInstance() {return riderDao;}
	
	// 1. 쓰기 / 라이더 회원가입 
	public boolean RiderSignup( RiderDto dto ) {
		System.out.println("RiderDao 도착");
		
		try {
			String sql = "insert into \r\n"
					+ "	rider( rname , rid , rpwd , rphone , rphoto , rlicense ,  rregistration , raccount , rbank , rbikenum ) \r\n"
					+ "	values( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
			System.out.println("sql 입력받았어요"+sql);
			// sql 실행
			ps = conn.prepareStatement(sql);
			// 입력받을 데이터
			ps.setString(1 , dto.getRname() );			ps.setString(2 , dto.getRid() );
			ps.setString(3 , dto.getRpwd() );			ps.setString(4 , dto.getRphone() );
			ps.setString(5 , dto.getRphoto() );			ps.setString(6 , dto.getRlicense() );
			ps.setString(7 , dto.getRregistration() );	ps.setString(8 , dto.getRaccount() );	
			ps.setString(9 , dto.getRbank() );  ps.setString(10, dto.getRbikenum() ); 
			int row = ps.executeUpdate();
			if(row == 1) return true;
			
		} catch (Exception e) {System.out.println(e);}
		
		return false;
	}
	
	
	// 2. 출력 , 로그인 
	public boolean Riderlogin( String rid , String rpwd ) {
		
		try {
			String sql = "select * from rider where rid = ? and rpwd = ? ";
			
			// 실행한다. 
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, rid); ps.setString(2, rpwd);
			rs = ps.executeQuery();
			if(rs.next() )return true;
		System.out.println("Riderlogin: "+ps);
			
		} catch (Exception e) {System.out.println(e);}
		
		return false;
	}
	
	// 3. 내정보(라이더) 출력 
	public RiderDto info( String rid ) {
		System.out.println("rid: "+rid);
		try {
			
			String sql = "select rno , rname , rid , rphone , rphoto , rlicense , rregistration , "
					+ "raccount , rbank , rstatus , rbikenum , rstart , rcall from rider natural join riderstate where rid = ? ";
			ps = conn.prepareStatement(sql); System.out.println("ps: "+ps);
			
			/*
			 	public RiderDto(int rno, String rname, String rid, String rphone, String rphoto, String rregistration,
				String raccount, String rbank, String rstatus, String rbikenum, String rstart, String rcall)
			 */
			
			// 입력받을것 
			ps.setString(1, rid);
			// 
			rs = ps.executeQuery();
			// 하나의 정보만 출력할꺼기 때문에 if 사용 
			if(rs.next() ) {
				
				/*
				  RiderDto riderDto = new RiderDto( rs.getInt(1) , rs.getString(2) ,
				  rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(6) ,
				  rs.getString(7) , rs.getString(8) , rs.getString(9) , rs.getString(10) ,
				  rs.getString(11) , rs.getString(12) , rs.getString(13) 
				  );
				  System.out.println("info: "+riderDto); return riderDto;
				 */
				
				RiderDto riderDto = new RiderDto(
						
						rs.getInt(1) , rs.getString(2) ,
						rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(6) ,
						rs.getString(7) , rs.getString(8) , rs.getString(9) , rs.getString(10) ,
						rs.getString(11) , rs.getString(12) , rs.getString(13) 
						
				);
				return riderDto;
						
			}
			
			
		} catch (Exception e) {System.out.println(e);}
		
		return null;
	}
	
	// 4. 라이더 정보 수정 [ 이 수정은 1개만 수정이 가능... 그러므로... 은행명 /계좌번호 수정은 불가능하다..]
	public boolean rupdate( int rno , String type , String value) {
		
		try {
			// sql문 작성 
			// type 을 넣어서 라이더가 수정하고싶은 것을 수정한다.
			String sql = "update rider set "+type+" = ? where rno = ? ";
			System.out.println("type: "+sql);
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , value);
			ps.setInt(2, rno);
			
			System.out.println("rupdate SQL:: "+ps);
			// 실행시킨다 
		    int count = ps.executeUpdate();
			if( count == 1 ) return true;
			
		} catch (Exception e) {System.out.println(e);}
		
		return false;
	}
	// 4-2 은행명//계좌번호를 수정할수 있도록 필드2개 수정 SQL 작성하기 .
	public boolean rupdate2( int rno , String type1 , String type2 , String value1 , String value2 ) {
		
		try {
			
			// Sql 작성 //희락씨 잘 하셨는데. sql 에문제가 있어요.. 예시를 생각해보세요.
			// 은행명을 입력받은 값으로 , 계좌번호를 입력받은 값으로 누가 현재 라이더가.
			// update 테이블명 set 수정할필드명 = 수정할값 , 수정할필드명 = 수정할값 where 조건 
			
			String sql = "update rider set "+type1+" = ? , "+type2+" = ? where rno = ? ";
			
			System.out.println("Sql: "+sql);
			
			// 
			ps = conn.prepareStatement(sql);
			ps.setString(1 , value1);
			ps.setString(2 , value2);
			ps.setInt(3, rno);
			
			System.out.println("rupdate2 SQL:: "+ps);
			int count = ps.executeUpdate();
			if( count == 1 ) return true;
			
		} catch (Exception e) {System.out.println(e);}
		
		return false;
	}

	
	// 5. 삭제 
	public boolean rdelete( int rno , String rpwd ) {
		
		try {
			String sql = "delete from rider where rno = ? and rpwd = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rno); ps.setString(2, rpwd);
			int count = ps.executeUpdate();
			if(count == 1) return true; // 삭제성공 => 회원탈퇴 
			
		} catch (Exception e) {System.out.println(e);}
		
		return false; // 회원번호 또는 입력받은 패스워드 일치하지 않거나 
	}
	
	// 6. 아이디 중복검사 [ 인수 : 검사할아이디 , 반환 : true(중복없이) , false(중복없이) ]
	public boolean findId( String type , String data ){
		
		try {
			// "+type+" 함수를하나더 만들필요없이 type 을 넣어줘서 아이디 선택하에 중복검사를한다
			String sql = "select * from rider where "+type+" = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, data); System.out.println("findId: "+ps);
			rs = ps.executeQuery();
			// 아이디 하나만확인하기 때문에 if 사용 
			if( rs.next() )return true;
			
		} catch (Exception e) {System.out.println("findId: "+e); }
		
		return false;
	}
	
	// 7. 아이디 , 비밀번호 찾기 
	public RiderDto onFind( String type , String idfind , String pwdfind ) {
		
		try {
			
			// sql 정의
			// 아이디 비번찾기에서 공통으로 이름을 입력한다.
				// 가입된 라이더의 이름 알려줘 
			String sql = "select * from rider where rname = ? ";
			
			if( type.equals("findid") ) { //아이디 찾기 
				sql += "and rphone = ? "; 
			}else if( type.equals("findPwd") ) { // 비밀번호 찾기 
				sql += "and rid = ? ";	
			}
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , idfind );
			ps.setString( 2 , pwdfind );
			
			System.out.println("onFind: "+ps);
			// 실행 
			rs = ps.executeQuery();
			if( rs.next() ) {
				
				RiderDto dto = new RiderDto(
						
					rs.getString("rname") , rs.getString("rid") ,
					rs.getString("rpwd") , rs.getString("rphone") ,
					rs.getString("rphoto") , rs.getString("rlicense") ,
					rs.getString("rregistration") , rs.getString("raccount") ,
					rs.getString("rbank") , rs.getString("rbikenum") 
						
				);
				return dto;
			}
			
			
		} catch (Exception e) {System.out.println(e);}
		
		return null;
	}
	
		/*
	    * 라이더 주행기록 조회
	    * */
	   public ArrayList<ServiceDto> getDriveRecord(int rno) {
	      System.out.println("getDriveRecord");
	      System.out.println("rno :: "+ rno);
	      ArrayList<ServiceDto> list = new ArrayList<>();
	      try {
	         String sql = "SELECT "
	               + "        SNO "
	               + "      , MNO"
	               + "    , RNO "
	               + "      , SDATE "
	               + "      , SFROMLA "
	               + "      , SRIDERLO "
	               + "      , STOLA, STOLO "
	               + "      , CEIL(SPAYMENT *70/100) as SPAYMENT  "
	               + "      , SPAYYN "
	               + "      , SDEPOSITYN "
	               + " FROM SERVICE "
	               + " WHERE RNO = ? AND STOLA IS NOT NULL"
	               + " ORDER BY SDATE DESC ";
	         ps= conn.prepareStatement(sql);
	         ps.setInt(1, rno);
	         System.out.println( "getDriveRecord  :: " + ps);
	         rs = ps.executeQuery();
	         System.out.println("rs.next()   >>>" + rs.next());
	         while (rs.next()) {
	            System.out.println("serviceDto  :: "+  rs.getInt("mno"));
	            ServiceDto serviceDto = new ServiceDto(
	                    rs.getInt(1)
	                  , rs.getInt(2)
	                  , rs.getInt(3)
	                  , rs.getString(4)
	                  , rs.getString(5)
	                  , rs.getString(6)
	                  , rs.getString(7)
	                  , rs.getString(8)
	                  , rs.getInt(9)
	                  , rs.getString(10)
	                  , rs.getString(11)
	                  , null);

	            System.out.println("serviceDto  :: "+ serviceDto);
	            list.add(serviceDto);
	         }
	         System.out.println(list);
	      }catch(Exception e) {
	         e.getStackTrace();
	      }
	      return list;
	   }

	   /*
	    * 라이더 입금내역 조회
	    * */
	   public ArrayList<ServiceDto> getIncome( int rno , String value ){
		   
		   ArrayList<ServiceDto> list = new ArrayList<>();
		   
		   try {
			
			   // sql작성 
			   String sql = "SELECT "
		               + "        s.SNO "
		               + "      , s.MNO "
		               + "      , s.RNO "
		               + "      , s.SDATE "
		               + "      , s.SFROMLA "
		               + "      , s.sfromlo "
		               + "      , s.STOLA "
		               + "      , s.STOLO "
		               + "      , CEIL(s.SPAYMENT *70/100) as SPAYMENT  "
		               + "      , s.SPAYYN "
		               + "      , s.SDEPOSITYN "
		               + "      , d.ddate "
		               + " FROM SERVICE s left join deposit d "
		               + " on s.sno = d.sno "
		               + " WHERE s.RNO = ? "
		               + " and s.sdepositYN = ? "
		               + " ORDER BY SDATE DESC ";
			   ps = conn.prepareStatement(sql);
			   ps.setInt(1, rno);
			   ps.setString(2, value);
			   System.out.println("getIncome: "+ps);
			   rs = ps.executeQuery();
			   
			   while( rs.next() ) {
				   
				   ServiceDto serviceDto = new ServiceDto(
		                    rs.getInt(1)
		                  , rs.getInt(2)
		                  , rs.getInt(3)
		                  , rs.getString(4)
		                  , rs.getString(5)
		                  , rs.getString(6)
		                  , rs.getString(7)
		                  , rs.getString(8)
		                  , rs.getInt(9)
		                  , rs.getString(10)
		                  , rs.getString(11)
		                  , rs.getString(12) );

		            System.out.println("serviceDto  :: "+ serviceDto);
		            list.add(serviceDto);
				   
			   }
			   
		} catch (Exception e) {System.out.println(e);}
		   
		   return null;
	   }
	   
	   
}// class e




