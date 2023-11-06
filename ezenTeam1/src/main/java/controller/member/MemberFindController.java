package controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.MemberDao;
import model.dao.RiderDao;
import model.dto.MemberDto;
import model.dto.RiderDto;
import model.dto.ServiceDto;

// 링크 : http://localhost/ezenTeam1/MemberFindController
@WebServlet("/MemberFindController")
public class MemberFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public MemberFindController() {}

    // 로그인
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 		String mid = request.getParameter("mid");
 		String mpwd = request.getParameter("mpwd");
 		//로그인
 		boolean result = MemberDao.getInstance().login(mid, mpwd);

 		//로그인 성공시 회원정보 세션에 저장하기
 		if(result) {
 			MemberDto loginDto = MemberDao.getInstance().getLoginInfo(mid);
 			request.getSession().setAttribute("loginDto", loginDto);

 			//세션확인
 			MemberDto mDto = (MemberDto)request.getSession().getAttribute("loginDto");
 			System.out.println("Session LoginDto :: "+ loginDto);
 		}
 		response.setContentType(("application/json;charset=UTF-8"));
    	response.getWriter().print(result);

 	}

 	// 아이디/비밀번호/서비스이용내역/내정보
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String json = null;
		ObjectMapper objectMapper = new ObjectMapper();

		if(type.equals("findId")) {
			String mname = request.getParameter("mname");
			String memail = request.getParameter("memail");

			MemberDto mDto = MemberDao.getInstance().onFind(type,memail,mname);
			json = objectMapper.writeValueAsString(mDto);

		} else if(type.equals("findPwd")) {
			String mid = request.getParameter("mid");
			String memail = request.getParameter("memail");

			MemberDto mDto = MemberDao.getInstance().onFind(type,memail,mid);
			json = objectMapper.writeValueAsString(mDto);
		} else if(type.equals("findHistory")) { //사용자의 주행기록
			MemberDto memberDto =  (MemberDto)request.getSession().getAttribute("loginDto");
			//System.out.println("riderDto  :: "+ riderDto );
			int mno = memberDto.getMno();
			ArrayList<ServiceDto>result = RiderDao.getInstance().getDriveRecord(mno);
			json = objectMapper.writeValueAsString( result );
		}

		response.setContentType(("application/json;charset=UTF-8"));
    	response.getWriter().print(json);


	}


}
