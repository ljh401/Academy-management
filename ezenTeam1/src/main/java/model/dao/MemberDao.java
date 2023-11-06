package model.dao;

import java.util.ArrayList;

import model.dto.MemberDto;
import model.dto.ServiceDto;

public class MemberDao extends Dao{

	// 0. 싱글톤
	private static MemberDao memberDao = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() {return memberDao;}

	// 1. 쓰기 , 회원가입
	public boolean signup(MemberDto mDto) {
		try {
			String sql = "insert into member(mname, mid, mpwd, memail, mpayinfo,mphoto) values (?,?,?,?,?,?);";
			ps= conn.prepareStatement(sql);
			ps.setString(1, mDto.getMname());
			ps.setString(2, mDto.getMid());
			ps.setString(3, mDto.getMpwd());
			ps.setString(4, mDto.getMemail());
			ps.setString(5, mDto.getMpayinfo());
			ps.setString(6, mDto.getMphoto());
			System.out.println("SQL M signup() ::" +ps);
			int rs = ps.executeUpdate();
			if(rs == 1) {
				return true;
			}

		} catch (Exception e) {
			System.out.println("Exception ::::"+ e);
		}
		return false;
	}



	// 2. 출력 , 로그인
	public boolean login(String mid, String mpwd) {

		try {
			String sql = "select *  from member where mid =? and mpwd=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, mpwd);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Exception :: "+ e);
		}
		return false;

	}


	// 3. 아이디/이메일 중복검사 [ 인수 : 검사할아이디 / 반환 : true(중복없이) , false(중복없이) ]
	public boolean isExist(String search, String key) {
		String sql = "select *from member  where "+ search +" = ?";
		try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			System.out.println("MEMBER :: isExist() _SQL"+ps);

			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Exception  :: " + e);
		}

		return false;
	}

	// 4. 수정 , 회원수정
	public boolean updateInfo(String mid) {

		try {

		} catch (Exception e) {
			System.out.println("Exception :: "+e);
		}
		return false;
	}

	// 5. 삭제 , 회원탈퇴
	public boolean deleteInfo(String mid) {

		try {

		} catch (Exception e) {
			System.out.println("Exception :: "+e);
		}
		return false;
	}

	// 6. 세션에 저장할 회원정보 가져오기
	public MemberDto getLoginInfo(String mid) {

		try {
			String sql = " select * from member where mid = ? ";
			ps= conn.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if(rs.next()) {
				MemberDto loginDto = new MemberDto(
						  rs.getInt("mno")
						, rs.getString("mname")
						, rs.getString("mid")
						, rs.getString("memail")
						, rs.getString("mpayinfo")
						, rs.getString("mdate")
						, rs.getString("mphoto"));

				return loginDto;
			}


		} catch (Exception e) {
			System.out.println("Exception :: "+e);
		}
		return null;
	}
	// 7. 아이디 or 비밀번호 찾기
	public MemberDto onFind (String type, String req1, String req2) {

		try {
			String sql = " select * from member where memail = ? ";

			if (type.equals("findId")) { //아이디찾기
				sql += "and mname = ?";
			} else if(type.equals("findPwd")) { //비밀번호찾기
				sql += "and mid = ? " ;
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, req1);
			ps.setString(2, req2);
			
			System.out.println(" MemberDao :: onFind :: "+ ps);
			rs = ps.executeQuery();
			if(rs.next()) {
				//public MemberDto(String mname, String mid, String mpwd, String memail, String mpayinfo)
				MemberDto memberDto = new MemberDto( 
						  rs.getString("mname")
						, rs.getString("mid")
						, rs.getString("mpwd")
						, rs.getString("memail")
						, rs.getString("mpayinfo"));
				
				return memberDto;
			}
		} catch (Exception e) {
			System.out.println("Exception :: " + e);
		}
		return null;
	}

	// 8  내 정보가져오기
	public boolean getMyInfo(int mno) {
		return false;
	}

	// 9. 정보 변경하기
	public boolean changeMyInfo(int mno, String type, String data) {
		
		try {
			String sql = "update member set " + type + " = ? where mno = ? " ;
			ps=conn.prepareStatement(sql);
			ps.setString(1, data);
			ps.setInt(2, mno);
			System.out.println("changeMyInfo SQL :: " +ps);
			int rs = ps.executeUpdate();
			if(rs == 1) { return true;	}
			
		}catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	
	// 10. 사용자 이용내역
   public ArrayList<ServiceDto> getDriveRecord(int mno) {
      System.out.println("getDriveRecord");
      System.out.println("mno :: "+ mno);
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
               + "      , SPAYMENT  "
               + "      , SPAYYN "
               + "      , SDEPOSITYN "
               + " FROM SERVICE "
               + " WHERE RNO = ? AND STOLA IS NOT NULL"
               + " ORDER BY SDATE DESC ";
         ps= conn.prepareStatement(sql);
         ps.setInt(1, mno);
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
	
}// class e
