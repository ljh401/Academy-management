package controller.sns;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.sns.SnsReplyDao;
import model.dto.sns.SnsReplyDto;

import com.fasterxml.jackson.databind.ObjectMapper;




// 링크 : /ezenTeam1/SnsReplyController

@WebServlet("/SnsReplyController")
public class SnsReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SnsReplyController() {}

	// 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");

		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";


		if( type.equals("1") ) { // 전체 조회 로직 ;


			//int rno = Integer.parseInt( request.getParameter("rno") );
			//String rcontent = request.getParameter("rcontent");
			//String rdate = request.getParameter("rdate");

			//SnsReplyDto dto = new SnsReplyDto(sno,rcontent, rdate);

			//ArrayList<SnsReplyDto> result = SnsReplyDao.getInstance().getReply(sno);

			//json = objectMapper.writeValueAsString( result );
		}

		// 공통 로직 // 1. 전체조회 , 개별조회 하던 응답 로직 공통
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print( json );


	}

	// 쓰기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//요청
			String rcontent = request.getParameter("rcontent");
			String rpwd = request.getParameter("rpwd");
			int sno = Integer.parseInt(request.getParameter("sno"));

			//객체화
			SnsReplyDto replyDto = new SnsReplyDto(rcontent, rpwd, sno);

			//DAO
			boolean result = SnsReplyDao.getInstance().replyWrite(replyDto);

			//응답
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(result);

	}
	// 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	// 삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int rno =  Integer.parseInt(request.getParameter("rno")); System.out.println(rno);
		String rpwd = request.getParameter("rpwd");

		boolean result = SnsReplyDao.getInstance().replyDelete(rno, rpwd);

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);

	}

}
