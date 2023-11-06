package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.AdminDao;
import model.dao.RiderDao;
import model.dto.RiderDto;
import model.dto.ServiceDto;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AdminController() {
       
    }

	// 1. 회원가입 간단한 정보 최신순으로 조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		ObjectMapper objectMapper = new ObjectMapper();
	    String json = "";
	    if(type.equals("1")) {
		    ArrayList<RiderDto> result = AdminDao.getInstance().ApprovalPrint();
	
		    // JSON 형식으로 응답 데이터 설정
		    response.setContentType("application/json;charset=UTF-8");
	
		    // Java 객체를 JSON 문자열로 변환
		    json = objectMapper.writeValueAsString(result);
	
		    // JSON 문자열을 응답에 출력 
		    response.getWriter().print(json);
		    
	    }else if(type.equals("2")) {
	    	
	    	int rno = Integer.parseInt(request.getParameter("rno"));
	    	
	    	RiderDto result = AdminDao.getInstance().ApprovalView(rno);
	    	
	    	 // JSON 형식으로 응답 데이터 설정
	        response.setContentType("application/json;charset=UTF-8");
	        
	        // Jackson ObjectMapper를 사용하여 객체를 JSON 문자열로 변환
	        ObjectMapper objectMapper2 = new ObjectMapper();
	        String json2 = objectMapper.writeValueAsString(result);
	        
	        // JSON 문자열을 응답에 출력
	        response.getWriter().print(json2);
	        System.out.println(json2);
	    }else if (type.equals("3")) {
	        int result = AdminDao.getInstance().Request();
	        response.setContentType("application/json;charset=UTF-8");
	        response.getWriter().print(result);
	        System.out.println("result = " + result);
	    }else if(type.equals("4")) {
	    	ArrayList<ServiceDto> result=AdminDao.getInstance().depositCount();
	    	 response.setContentType("application/json;charset=UTF-8");
		  	 json = objectMapper.writeValueAsString(result);
		  	 response.getWriter().print(json);
		  	 System.out.println("json: "+json);
	    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		int rno = Integer.parseInt(request.getParameter("rno"));
		String rcomment = request.getParameter("rcomment");
		if(type.equals("1")) {
			 boolean result = AdminDao.getInstance().ApprovalReject(rno, rcomment);
			 response.setContentType("application/json;charset=UTF-8");
			 response.getWriter().print("머있어"+result);
			
			
		}else if(type.equals("2")) {

			boolean result = AdminDao.getInstance().approval(rno);
			 response.setContentType("application/json;charset=UTF-8");
			 response.getWriter().print(result);
			 System.out.println("result에 머들어있니? "+result);
		}
	}

	// 3.  라이더 승인 요청 수락/거부
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
