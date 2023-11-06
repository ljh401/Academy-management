package model.dao.sns;

import java.util.ArrayList;

import model.dao.Dao;
import model.dto.sns.SnsDto;

public class SnsDao extends Dao{

	// 싱글톤
	private static SnsDao snsDao = new SnsDao();
	private SnsDao() {}
	public static SnsDao getInstance() {return snsDao;}

	// 1. 쓰기
	public boolean swrite(SnsDto snsDto) {

		try {
			String sql="insert into sns(sfile,scontent,spwd) values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1,snsDto.getSfile());
			ps.setString(2,snsDto.getScontent());
			ps.setString(3,snsDto.getSpwd());
			int row= ps.executeUpdate();
			if(row==1)return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}

	// 2. 출력
	public ArrayList<SnsDto> snsList( String keyword ){

		ArrayList<SnsDto> list = new ArrayList<>();

		try {
			//abs(timestampdiff(second, sdate ,now())) as  sdate "
			// sdate와  현재시간을 비교해서 그 차이를 분(minute)로 반환
			String sql ="select sno"
					+ ",		sfile"
					+ ",		scontent"
					+ ",		abs(timestampdiff(MINUTE, sdate ,now())) as  sdate"
					+ ",		spwd  "
					+ "	   from sns ";
			if(!keyword.isEmpty() ) {
				sql += " where scontent like '%"+keyword+"%' ";
			}
					sql += "order by sdate asc";


			ps= conn.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("snsList:: SQL "+ ps);
			while(rs.next()){
				SnsDto snsDto = new SnsDto(
						  rs.getInt("sno")
						, rs.getString("sfile")
						, rs.getString("scontent")
						, rs.getString("spwd")
						, rs.getString("sdate"));
				list.add(snsDto);
			}

		} catch (Exception e) {System.out.println(e);}
		return list;
	}

	// 전체 게시물 수 출력
	public int getSearchCount( String keyword ) {

		try {
			String sql = " select count(sno) from sns where scontent like '%"+keyword+"%' ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) return rs.getInt(1);


		} catch (Exception e) {System.out.println(e);}

		return 0;
	}

	// 개별 글 출력 메소드 , 식별키 - sno
	public SnsDto getSns(int sno) {

		try {
			String sql = "select * from sns where sno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sno);
			rs = ps.executeQuery();
			if( rs.next() ) {
				SnsDto snsDto = new SnsDto(
					rs.getInt(1) ,
					rs.getString(2) ,
					rs.getString(3) ,
					rs.getString(4)
						);
				return snsDto;
			}

		} catch (Exception e) {System.out.println(e);}

		return null;
	}

	// 3. 수정
	public boolean snsUpdate( SnsDto dto ) {

		try {
			// sql작성
			String sql = "update sns set sfile = ? , scontent = ?  where sno = ?";
			// sql 실행
			ps = conn.prepareStatement(sql);
			// 입력받을 dto생성자
			ps.setString( 1 , dto.getSfile() );
			ps.setString( 2 , dto.getScontent() );
			ps.setInt( 3 , dto.getSno() );
			int count = ps.executeUpdate();
			// 1개의 레코드 이면 true
			if(count == 1)return true;

		} catch (Exception e) {System.out.println(e);}

		return false;
	}

	// 4. 삭제
	public boolean sdelete(int sno, String spwd) {
		try {
			String sql = "delete from sns where sno = ? and spwd =?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sno);
			ps.setString(2, spwd);
			int count = ps.executeUpdate();
			if(count == 1) return true;


		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	// 5, 비밀번호 검사
	public boolean checkPwd(int sno, String spwd) {
		try {
			String sql = "select sno from sns where sno = ? and spwd = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, sno);
			ps.setString(2, spwd);
			System.out.println("ps :: "+ ps );
			rs = ps.executeQuery();
			if(rs.next()) {return true;}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}


}// class e
