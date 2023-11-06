package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.MemberDao;
import model.dto.MemberDto;
import model.dto.RiderDto;


// 링크 : http://localhost/ezenTeam1/MemberInfoController
@WebServlet("/MemberInfoController")
public class MemberInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public MemberInfoController() {}

    // 저장
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String saveDirectory = request.getSession().getServletContext().getRealPath("/gorider/member/file");
    	MultipartRequest multi = new MultipartRequest(
    			request,
    			saveDirectory,
    			1024*1024*10,
    			"UTF-8",
    			new DefaultFileRenamePolicy()
    	);
    	// alter table tbl_query add qe_answer08 int comment '코멘트 제목')
    	//DB저장
    	String mname = multi.getParameter("mname");
    	String mid = multi.getParameter("mid");
    	String mpwd = multi.getParameter("mpwd");
    	String memail=multi.getParameter("memail");
    	String mpayinfo=multi.getParameter("mpayinfo1")
    			+ multi.getParameter("mpayinfo2")
    			+ multi.getParameter("mpayinfo3")
    			+ multi.getParameter("mpayinfo4");
    	String mphoto ="default.png";

    	System.out.println("mname ::"+mname);
    	System.out.println("mid ::"+mid);
    	System.out.println("mpwd ::"+mpwd);
    	System.out.println("memail ::"+memail);
    	System.out.println("mpayinfo ::"+mpayinfo);
    	System.out.println("mphoto ::"+mphoto);

    	MemberDto memberDto = new MemberDto(mname,mid,mpwd,memail, mpayinfo, mphoto);
    	boolean result = MemberDao.getInstance().signup(memberDto);

    	response.setContentType(("application/json;charset=UTF-8"));
    	response.getWriter().print(result);





	}

    // 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		boolean result = false;

		if( type.equals("isExist")) {
			String key = request.getParameter("key");
			String search = request.getParameter("search");
			result	= MemberDao.getInstance().isExist(search,key);
			//result = isExist ? "true": "false";

		} else if(type.equals("login")) {
			
			
			/*
			if (session instanceof RiderDto) {
			    RiderDto riderDto = (RiderDto) session;
			    ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(riderDto);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().print(json);
			    // RiderDto로 형변환된 객체를 사용할 수 있습니다.
			} else if (session instanceof MemberDto) {
			*/
			Object session = request.getSession().getAttribute("loginDto");
			MemberDto memberDto = (MemberDto) session;
			System.out.println("MemberInfoController::memberDto::" + memberDto);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(memberDto);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
			    // MemberDto로 형변환된 객체를 사용할 수 있습니다.
			//} 
			return;
		} else if(type.equals("logout")) {
			System.out.println("type : "+ type);
			//세션에 저장된 logiDto에 null대입
			request.getSession().setAttribute("loginDto", null);
		}

    	response.setContentType(("application/json;charset=UTF-8"));
    	response.getWriter().print(result);
	}

	// 수정:
	// 1. 프로필사진 수정
	// 2. 결제수단변경
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uploadPath = request.getSession().getServletContext().getRealPath("/gorider/member/file");
		// 사진. 결제정보 두가지를 수정해야 함으로.
		String changeType = null; // 수정할 필드의 정보를 담을 변수를 선언
		String changeData = null; // 수정할 데이터값을  담을 변수를 선언
		
		MemberDto loginDto = (MemberDto) request.getSession().getAttribute("loginDto");
		int mno = loginDto.getMno(); //세션에서 회원의 번호를 가져옴.
		MultipartRequest multi = new MultipartRequest( //cos라이브러리
			request,			// 1. 요청방식
			uploadPath,			// 2. 첨부파일을 저장할 경로
			1024*1024*10,		// 3. 첨부파일 용량 허용 범위 [ 바이트 단위 ]10mb
			"UTF-8",			// 4.한글인코딩타입
			new DefaultFileRenamePolicy());
		
		if(multi.getFilesystemName("mphoto") != null) { //프로필 사진을 수정할 경우
			changeType = "mphoto";
			changeData = multi.getFilesystemName("mphoto");
			
		} else if(multi.getParameter("mpayinfo1") != null) {
			changeType = "mpayinfo";
			changeData = multi.getParameter("mpayinfo1")
	    			+ multi.getParameter("mpayinfo2")
	    			+ multi.getParameter("mpayinfo3")
	    			+ multi.getParameter("mpayinfo4");
		}
		System.out.println("changeType  :: "+ changeType);
		System.out.println("changeData  :: "+ changeData);
	
		boolean result = MemberDao.getInstance().changeMyInfo(mno, changeType, changeData);
		response.setContentType(("application/json;charset=UTF-8"));
    	response.getWriter().print(result);
	}

	// 삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
