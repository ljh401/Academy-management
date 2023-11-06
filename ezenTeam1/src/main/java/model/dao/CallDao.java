package model.dao;

import java.sql.Statement;

import model.dto.ServiceDto;

public class CallDao extends Dao{

	
	private static CallDao callDao = new CallDao();
	private CallDao() {}
	public static CallDao getInstance() {return callDao;}
	

	// 1. 사용자 콜정보 INSERT
	public int MemberCall(int mno, String sfromla,String sfromlo,String stola, String stolo , String spayYN , int spayment  ) {

		try {
			String sql = "insert into service (mno,sfromla,sfromlo,stola,stolo,spayYN,spayment) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS ); // insert 에서 오토키로 생성된 sno 가져오는 방법.
				// Statement.RETURN_GENERATED_KEYS : 방금 생성된 pk(오토키)를 리턴 준비..
			ps.setInt(1, mno);
			ps.setString(2, sfromla);
			ps.setString(3, sfromlo);
			ps.setString(4, stola);
			ps.setString(5, stolo);
			ps.setString(6, spayYN);
			ps.setInt(7, spayment);
			
			int count = ps.executeUpdate();
			// pk 호출 하기 
			rs = ps.getGeneratedKeys();
			rs.next();
			int sno  = rs.getInt(1);
			
			if(count == 1) return sno; // 호출된 pk를 반환
	
		}catch (Exception e) {System.out.println(e);}
		return 0;
	}
	
	// 2. 콜했을때 두가지 // 라이더가 콜 수락했을때..
		// 1. 라이더의 위치 DB에 수정
	public boolean RiderAccept(int rno,String sriderla,
			String sriderlo, int sno) {
		try {
			String sql = "update service set sriderla = ?,sriderlo =? ,rno = ? where sno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, sriderla);
			ps.setString(2, sriderlo);
			ps.setInt(3, rno);
			ps.setInt(4, sno);
			int count = ps.executeUpdate();
			if(count == 1) {
				
				// 라이더 콜상태 변경.
				sql = "update riderstate set rcall = 'N' where rno = "+ rno;
				ps = conn.prepareStatement(sql);
				count = ps.executeUpdate();
				if( count == 1 ) return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	
	// 2. 콜 수락했을때 사용자가 라이더 정보 확인
	public ServiceDto ShowRiderInfo(int sno) {
		try {
			// 사용자 화면에 출력할 내용들 : 1. 출발할때 위도,경도 / 도착지 위도,경도 / 라이더 위도,경도 / 라이더 번호,이름,사진,번호판
			String sql = "SELECT s.sno, s.sriderla, s.sriderlo, s.rno, r.rname, r.rphoto, r.rbikenum , s.mno , s.sfromla , s.sfromlo, s.stola , s.stolo" +
                    "	FROM service s " +
                    "	INNER JOIN rider r ON s.rno = r.rno	where s.sno = ?";
			ps = conn.prepareStatement(sql);
			System.out.println("ps  : " + ps );
			ps.setInt(1, sno);
			rs= ps.executeQuery();
			if(rs.next()) {
				ServiceDto servicedto= new ServiceDto(
						rs.getInt("sno"),
						rs.getInt("rno"),
						rs.getString("sriderla"),
						rs.getString("sriderlo"),
						 rs.getString("rname"),
						 rs.getString("rphoto"),
						 rs.getString("rbikenum")
						);
				servicedto.setMno( rs.getInt("mno") );
				servicedto.setSfromla(rs.getString("sfromla"));
				servicedto.setSfromlo(rs.getString("sfromlo"));
				servicedto.setStola(rs.getString("stola"));
				servicedto.setStolo(rs.getString("stolo"));
				
				return servicedto;
			}
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 3. 라이더 콜 받을수 있는지 상태 확인 
	public boolean getRiderstate( int rno ) {
		try {
			String sql = "SELECT * FROM gorider.riderstate where rno = "+rno+" and rstart = 'Y' and rcall = 'Y';";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) return true; 
		}catch (Exception e) {System.out.println( e );} 
			return false;
	}
	
	// 4. 하차할때 서비스, 라이더 상태 변경 , 결제여부 = Y
	public boolean getOut(int sno, int rno) {
		try {
			
			String sql = "update riderstate set rcall = 'Y' where rno = " + rno;
			ps = conn.prepareStatement(sql);	System.out.println( ps );
			
			int count = ps.executeUpdate();
			
			if( count == 1 ) {
				// 서비스 상태 변경
				sql = "update service set spayYN = 'Y' where sno = " + sno;
				ps = conn.prepareStatement(sql);
				count = ps.executeUpdate();
				if( count == 1 ) return true;
			}
		} catch (Exception e) { System.out.println( e ); }
		
		return false;
	}
	
}






























