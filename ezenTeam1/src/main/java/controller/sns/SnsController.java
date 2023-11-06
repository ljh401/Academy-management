package controller.sns;


import java.io.IOException;

import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.fasterxml.jackson.databind.ObjectMapper;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.sns.SnsDao;
import model.dao.sns.SnsReplyDao;
import model.dto.sns.SnsDto;
import model.dto.sns.SnsReplyDto;
import service.FileService;




/**
 * Servlet implementation class SnsController
 */



@WebServlet("/SnsController")
public class SnsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SnsController() {}

	// 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청
		String type = request.getParameter("type");
		String json = null;
		System.out.println("type >>  :: "+ type);
		ObjectMapper objectMapper = new ObjectMapper();
		
		if(type.equals("1")) { //게시물 전체 출력
			// 요청
			String keyword = request.getParameter("keyword");
		
			// dao
			int count = SnsDao.getInstance().getSearchCount(keyword);
			
			// 응답 
			
			
			//json으로 변환

			ArrayList<SnsDto> result = SnsDao.getInstance().snsList(keyword);

			// ArrayList<SnsDto> result = SnsDao.getInstance().snsList();
			for(int i = 0; i<result.size(); i++) {
				int sno = result.get(i).getSno();
				
				System.out.println("sno  :: "+ sno);
				
				
				ArrayList<SnsReplyDto> replyList = SnsReplyDao.getInstance().getReply(sno);
				
				result.get(i).setReplyList(replyList);
			}
			

			System.out.println(result);
			
			json = objectMapper.writeValueAsString( result );
			
			
		} else if (type.equals("2")){ //게시물 1개 출력할때
			// 1. 매개변수 요청 
			
			int sno = Integer.parseInt( request.getParameter("sno") );
			// DAO 처리
			 SnsDto result = SnsDao.getInstance().getSns(sno);
			 json = objectMapper.writeValueAsString( result );
			 
		} else if(type.equals("3")) {
			String spwd = request.getParameter("spwd");
			int sno = Integer.parseInt(request.getParameter("sno"));
			
			boolean result =  SnsDao.getInstance().checkPwd(sno, spwd);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(  result );
			return;
			

		}else if(type.equals("4")) {

			// 요청 
			String keyword = request.getParameter("keyword");
			// dao
			int result = SnsDao.getInstance().getSearchCount(keyword);
			// 응답
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print( result );
			return;

		}
		
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(  json );

	}

	// 저장
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 첨부파일 업로드
		MultipartRequest multi = new MultipartRequest(
				request,
				request.getServletContext().getRealPath("/sns/upload"),
				1024*1024*1024, // 1GB
				"UTF-8",
				new DefaultFileRenamePolicy() // 만약에 업로드파일명이 서버내 존재하면 (중복) 자동으로 파일명뒤에 숫자 붙이기
				);
		System.out.println(request.getServletContext().getRealPath("/sns/upload"));
		
		// 1. (입력받은 매개변수) 요청
		String sfile = multi.getFilesystemName("sfile");
		String scontent = multi.getParameter("scontent");
		String spwd = multi.getParameter("spwd");
		
		SnsDto snsDto = new SnsDto(sfile, scontent, spwd);
		System.out.println(snsDto);
		
		boolean result = SnsDao.getInstance().swrite(snsDto);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
		
}

	// 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러 수정 도착");
		
		// 1. 수정할 첨부파일 업로드 
				MultipartRequest multi = new MultipartRequest(
						request, 
						request.getServletContext().getRealPath("/sns/upload") ,
						1024 * 1024 * 1024 ,
						"UTF-8" ,
						new DefaultFileRenamePolicy() 
						); System.out.println("첨부파일도착" +multi);
		// 2. 수정할 데이터 요청 
		int sno = Integer.parseInt( multi.getParameter("sno") ); System.out.println("sno도착"+sno);
		String sfile = multi.getFilesystemName("sfile");
		String scontent = multi.getParameter("scontent");
		
		// 3. 수정할 데이터 식별키 sno - 게시물식별키
		SnsDto updateDto = new SnsDto(sno, sfile, scontent);
		
		System.out.println("수정할dto"+updateDto);
		
		// * 만약에 새로운 첨부파일이 없으면 기존 첨부파일 그대로 사용 
					// 마냐게boardDto 가 null 이면
		if( updateDto.getSfile() == null ) {
			
			// 기존 첨부파일 .getSns(sno)는 수정할게시물의 번호를 보여주는 코드이다 
			updateDto.setSfile( SnsDao.getInstance().getSns(sno).getSfile() );
		}
		
		// 3. DAO
		boolean result = SnsDao.getInstance().snsUpdate(updateDto);
		
		// 4. 응답 
		response.setContentType("application/json;charset=UTF-8");
    	response.getWriter().print(result);
		
	}

	// 삭제 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 삭제할 데이터 요청
		int sno = Integer.parseInt(request.getParameter("sno"));
		String spwd = request.getParameter("spwd");
		
			// 레코드 삭제 전 파일이른 꺼내기 [ 삭제후 파일이름 호출이 불가능하기때문에 ]
			String filename = SnsDao.getInstance().getSns(sno).getSfile();
		
		// 2. DAO
		boolean result = SnsDao.getInstance().sdelete(sno,spwd);
			
			// 게시물 삭제시 삭제된 게시물의 업로드파일도 같이삭제 
			if(result) { // 만약에 게시물 삭제 성공이면 
				filename = request.getServletContext().getRealPath("/sns/upload")+"/"+filename;
				FileService.fileDelete(filename);
			}
		
		// 3. 응답
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
	}

}
