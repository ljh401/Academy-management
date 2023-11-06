package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import model.dto.BoardDto;

public class BoardDao extends Dao{

	private static BoardDao boardDao = new BoardDao();
	public static BoardDao getInstance() {return boardDao;}
	private BoardDao() {}

	// 저장
	public boolean save(BoardDto dto)  {
		/*
		 this.btarget = btarget;
		 this.btype = btype;
		 this.btitle = btitle;
		 this.bcontent = bcontent;
		 this.bstartdate = bstartdate;
		 this.benddate = benddate;
		 this.fileList = fileList;
		 */
		System.out.println("DAO :: dto:: "+ dto);
		System.out.println("1233");
		try {
			System.out.println("dddddd");
			String sql = "insert into board (btarget,btype,btitle,bcontent,bstartdate,benddate) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getBtarget());
			ps.setString(2, dto.getBtype());
			ps.setString(3, dto.getBtitle());
			ps.setString(4, dto.getBcontent());
			ps.setString(5, dto.getBstartdate().equals("null") ? null : dto.getBstartdate() );
			ps.setString(6, dto.getBenddate().equals("null")? null: dto.getBenddate());
			System.out.println("save ps000"+ ps);


			int count = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			System.out.println("save ps"+ ps);
			rs.next();
			if(count == 1) {
				for(String file:dto.getFileList().values()) {
					sql = "insert into boardfiles(bno, bfile) values( ?,?) ";
					ps= conn.prepareStatement(sql);
					ps.setInt(1, rs.getInt(1));
					ps.setString(2,file);
					ps.executeUpdate();
					System.out.println("filesave ps"+ ps);

				}return true;
			}
		}catch(Exception e){
			e.getStackTrace();
		}
		return false;
	}

	// 출력
	public ArrayList<BoardDto> boardList(){
		ArrayList<BoardDto> list = new ArrayList<>();
		try {
			String sql = "select "
					+ "  bno "
					+ ", btarget "
					+ ", btype "
					+ ", btitle "
					+ ", bview "
					+ ", DATE_FORMAT(bdate, '%y-%m-%d') as bdate "
					+ ", DATE_FORMAT(bstartdate, '%y-%m-%d') as bstartdate "
					+ ", DATE_FORMAT(benddate, '%y-%m-%d') as benddate  "
					+ "from board "
					+ "order by bno desc ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			System.out.println(" boardList SQL :: " + ps);

			while(rs.next()) {
				BoardDto boardDto = new BoardDto(
					rs.getInt("bno")
					, rs.getString("btarget")
					, rs.getString("btype")
					, rs.getString("btitle")
					, rs.getString("bdate")
					, rs.getInt("bview")
					, rs.getString("bstartdate")
					, rs.getString("benddate"));
				list.add(boardDto);
			}

		}catch(Exception e) {

			System.out.println("Exception :: "+ e);
		} return list;

	}

	// 사용자/라이더 출력
		public ArrayList<BoardDto> onList(String btype, String btarget, int limit){
			ArrayList<BoardDto> list = new ArrayList<>();
			try {
				String sql = "select "
						+ "  	bno "
						+ "	  , btarget "
						+ "	  , btype "
						+ "   , btitle "
						+ "   , bview "
						+ "   , DATE_FORMAT(bdate, '%Y-%m-%d') as bdate "
						+ "  , DATE_FORMAT(bstartdate, '%Y-%m-%d') as bstartdate "
						+ "  , DATE_FORMAT(benddate, '%Y-%m-%d') as benddate  "
						+ " from board "
						+ " where btype = ? "
						+ " and btarget = ? "
						+ " order by bno desc ";
				if(limit > 0) {
					sql +=" limit " + limit ;
				}
				ps = conn.prepareStatement(sql);
				ps.setString(1, btype);
				ps.setString(2, btarget);
				rs = ps.executeQuery();

				System.out.println(" onList SQL :: " + ps);

				while(rs.next()) {
					BoardDto boardDto = new BoardDto(
						rs.getInt("bno")
						, rs.getString("btarget")
						, rs.getString("btype")
						, rs.getString("btitle")
						, rs.getString("bdate")
						, rs.getInt("bview")
						, rs.getString("bstartdate")
						, rs.getString("benddate"));
					list.add(boardDto);
				}

			}catch(Exception e) {

				System.out.println("Exception :: "+ e);
			} return list;

		}

	// 상세보기
	public BoardDto boardView(int bno) {


		try {

			String sql ="select "
					+ "		b.bno as bno "
					+ "	  , b.btarget as btarget "
					+ "	  , b.btype as btype "
					+ "   , b.btitle as btitle "
					+ "   , b.bcontent as bcontent "
					+ "   , DATE_FORMAT(bdate, '%Y-%m-%d') as bdate "
					+ "   , b.bview as bview "
					+ "   , DATE_FORMAT(b.bstartdate, '%Y-%m-%d') as bstartdate "
					+ "   , DATE_FORMAT(b.benddate, '%Y-%m-%d') as benddate "
					+ " from board b left outer join boardfiles bf on b.bno = bf.bno where b.bno = ? ";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			System.out.println("boardView ::ps ::: "+ ps);
			if(rs.next()) {
				/*

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
				 */
				BoardDto boardDto = new BoardDto(
						 rs.getInt("bno")
						,rs.getString("btarget")
						,rs.getString("btype")
						,rs.getString("btitle")
						,rs.getString("bcontent")
						,rs.getString("bdate")
						,rs.getInt("bview")
						,rs.getString("bstartdate")
						,rs.getString("benddate")
						,getFile(rs.getInt("bno")));

				System.out.println("boardView ::boardDto ::: "+ boardDto);
				return boardDto;
			}
		} catch (Exception e) {
			System.out.println("Exception :: "+ e);
		}
		return null;
	}
	// 게시물 파일(이미지)출력
	public Map<Integer, String> getFile(int bno){

		try {
			Map<Integer, String> fileList = new HashMap<>(); //제품의 다수 이미지정보
			String sql = " select * from boardfiles where bno = " +bno;
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("getFile(int bno) :: SQL"+ ps);
			ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					fileList.put(rs.getInt("bfno"), rs.getString("bfile"));
				}
			return fileList;

		} catch (Exception ex) {
			System.out.println("fileList::Exception :: "+ ex );
		}
		return null;
	}




	// 수정
	public boolean update(BoardDao dto) {
		try {

		} catch (Exception e) {
			System.out.println("Exception :: "+ e);
		}
		return false;
	}

	// 삭제
	public boolean delete(int bno) {
		try {
			String sql ="delete from board where bno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			int cnt = ps.executeUpdate();
			if(cnt == 1) { 	return true; }


		} catch (Exception e) {
			System.out.println("Exception :: "+ e);
		}
		return false;
	}
	// 조회수

}
