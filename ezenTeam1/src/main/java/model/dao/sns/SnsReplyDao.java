package model.dao.sns;


import java.util.ArrayList;

import model.dao.Dao;
import model.dto.sns.SnsReplyDto;

public class SnsReplyDao extends Dao{

	// 싱글톤
	private static SnsReplyDao snsReplyDao = new SnsReplyDao();
	private SnsReplyDao() {}
	public static SnsReplyDao getInstance() {return snsReplyDao;}


	// 쓰기
	public boolean replyWrite(SnsReplyDto replyDto) {
		try {
													// sno - 게시글 pk 에 참조하여 댓글 달기
			String sql="insert into reply(rcontent,rpwd,sno) values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, replyDto.getRcontent());
			ps.setString(2, replyDto.getRpwd());
			ps.setInt(3, replyDto.getSno());
			int row=ps.executeUpdate();
			if(row==1)return true;

		}catch (Exception e) {System.out.println(e);}
		return false;
	}

	// 호출


	public ArrayList<SnsReplyDto> getReply(int sno){

		ArrayList<SnsReplyDto> list = new ArrayList<>();
		//abs(timestampdiff(second, rdate ,now())) as  rdate "
		// rdate와  현재시간을 비교해서 그 차이를 분(minute)로 반환
		try {
			String sql = "select rno"
					+ ", 		rcontent"
					+ ", 		abs(timestampdiff(MINUTE, rdate ,now())) as  rdate "
					+ ", 		sno "
					+ "	  from reply "
					+ "	  where sno = ?  "
					+ "   order by rdate asc";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sno);
			rs = ps.executeQuery();
			System.out.println("getReply:: SQL "+ ps);
			while(rs.next()) {
				SnsReplyDto dto = new SnsReplyDto(rs.getInt(1),rs.getString(2),rs.getString(3));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;

	}

	// 삭제
	public boolean replyDelete(int rno, String rpwd) {

		try {

			String sql = "delete from reply where rno = ? and rpwd = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, rno);
			ps.setString(2, rpwd);
		
			System.out.println("replyDelete  :: SQL :: "+ ps);
			int rs = ps.executeUpdate();
			if(rs ==1 ) { return true;}


		} catch (Exception e) {System.out.println(e);}

		return false;
	}

}// class e
