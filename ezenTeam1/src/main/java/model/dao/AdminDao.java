package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.dto.RiderDto;
import model.dto.ServiceDto;

public class AdminDao extends Dao{
	// 0. 싱글톤
	private static AdminDao adminDao = new AdminDao();
	private AdminDao() {}
	public static AdminDao getInstance() {return adminDao;}



	// 1. 기본적인 내용 호출
	public ArrayList<RiderDto> ApprovalPrint(){
		ArrayList<RiderDto> list = new ArrayList<>();
		try {
			// 2.  최신순으로 라이더가 회원가입 요청했을때 간단한 라이더 정보 가져오는 쿼리문
			String sql ="select rno , rid , rdate from rider where rstatus='N' order by rdate asc";
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();

			// 3. 정보가 여러개니깐 while문으로 여러개 레코드 조회
			while(rs.next()) {
				RiderDto dto = new RiderDto(
						rs.getInt("rno"), rs.getString("rid"),
						rs.getString("rdate")	);

				// 4. 리스트에 dto 를 담는다,
				list.add(dto);
			}

		}catch (Exception e) {System.out.println(e);}
		// 5. 리스트에 dto 를 담아서 리턴.
		return list;
	}


	// 2. 상세보기
	public RiderDto ApprovalView(int rno) {
		try {
			String sql = "select * from rider where rno = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rno);
			rs= ps.executeQuery();
			if(rs.next()) {
				RiderDto riderDto = new RiderDto(
						rs.getInt("rno"),
						rs.getString("rname"), rs.getString("rid"),rs.getString("rphone"),
						rs.getString("rphoto"),rs.getString("rlicense"),
						rs.getString("rregistration"),rs.getString("rdate"),
						rs.getString("raccount"),rs.getString("rbank"),
						rs.getString("rstatus"),rs.getString("rcomment"), rs.getString("rbikenum")
						);
				return riderDto;
			}
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	// 3. 승인 거부 함수
	public boolean ApprovalReject(int rno, String rcomment) {

		try {
			 String sql = "UPDATE rider SET rstatus = 'D', rcomment = ? WHERE rno = ?";
			  ps = conn.prepareStatement(sql);
		        ps.setString(1, rcomment);
		        ps.setInt(2, rno);
		        int count = ps.executeUpdate();

		        // 업데이트가 성공하면 count는 1이상의 값을 가집니다.
		        if (count > 0) {
		            return true; // 승인 거부 성공
		        }
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	// 승인 수락 함수
	public boolean approval( int rno) {

		try {
	        String sql = "UPDATE rider SET rstatus = 'Y' WHERE rno = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, rno); // Set the value for the first (and only) parameter

	        int count = ps.executeUpdate();

	        if (count > 0) {
	            return onapprove(rno);
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return false;
	}

	public boolean onapprove(int rno) {
		try {
			String sql = "insert into riderstate (rno) values (?)";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, rno);

	        int count = ps.executeUpdate();

	        if (count > 0) {
	            return true;
	        }
		}catch (Exception e) {System.out.println(e);}
		return false;
	}

	// 승인요청 가져오는 함수
	public int Request() {
		int count = 0;
	    try {
	        String sql = "SELECT COUNT(*) FROM rider WHERE rstatus = 'N'";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
		}catch (Exception e) {System.out.println(e);}
		return count;
	}

	public ArrayList<ServiceDto> ServicePrint(){
		ArrayList<ServiceDto> list = new ArrayList<>();
		try {
			String sql="select * from service order by sdate desc ";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();

			while(rs.next()) {
				ServiceDto dto = new ServiceDto(
						rs.getInt("sno"),rs.getInt("rno"),
						rs.getString("sdate"),rs.getString("sreview"),
						rs.getInt("spoint"));
				list.add(dto);
				System.out.println(list);
			}
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	public ArrayList<ServiceDto> getServiceUsageStatus(){
		ArrayList<ServiceDto> list = new ArrayList<>();
		try {

			String sql ="select * from service";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();

			while(rs.next()) {
				ServiceDto dto = new ServiceDto(
						rs.getInt("sno"),rs.getInt("mno"),
						rs.getInt("rno"),rs.getString("sdate"),
						rs.getString("sfromla"),rs.getString("sfromlo"),
						rs.getString("stola"),rs.getString("stolo"));
				list.add(dto);
			}

		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	public ArrayList<ServiceDto>depositCount(){
		ArrayList<ServiceDto> list = new ArrayList<>();
		try {
			String sql = "select sno,rno,sdate,spayment from service where sdepositYN = 'N'";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				ServiceDto dto = new ServiceDto(
						rs.getInt("sno"),rs.getInt("rno"),
						rs.getString("sdate"), rs.getInt("spayment"));
				list.add(dto);
			}
		}catch (Exception e) {System.out.println("에러이유: "+e);}
		return list;
	}
	public boolean deposit(int rno , int sno , int ddeposit) {
		try {
			String sql = "insert into deposit (rno, sno,ddeposit) VALUES (?, ?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, rno); ps.setInt(2, sno); ps.setInt(3, ddeposit);
			int count = ps.executeUpdate();
			if(count==1) {
				sql="update service set sdepositYN='Y' where sno=? ";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, sno);
				count =ps.executeUpdate();
				if(count==1) {
					return true;
				}
			}


		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	public ArrayList<ServiceDto> servicestatus(){
		try {
				String sql = "";
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
}
