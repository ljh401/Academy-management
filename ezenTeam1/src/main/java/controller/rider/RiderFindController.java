package controller.rider;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.RiderDao;
import model.dto.RiderDto;


// 링크 : http://localhost/ezenTeam1/RiderFindController
@WebServlet("/RiderFindController")
public class RiderFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RiderFindController() {}

	// 로그인 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// 1. js매개변수 요청 
    	String rid = request.getParameter("rid"); System.out.println("rid: "+rid);
    	String rpwd = request.getParameter("rpwd"); System.out.println("rpwd: "+rpwd);
    	
    	
    	// 3. Dao 에게 전달후 결과 받기 
    	boolean result = RiderDao.getInstance().Riderlogin(rid, rpwd);
    	
    	// * 만약에 로그인 성공하면 세션에 로그인한 정보를 담기 
    	if(result == true) {
    		
    		// 1. 세션에 저장할 데이터를 만든다.
    		RiderDto loginDto = RiderDao.getInstance().info(rid); System.out.println("세션에 저장할데이터: "+loginDto);
    		
    		if(loginDto.getRstatus().equals("n") ) { // 마냐게 관리자의 승인상태가 n이면 로그인 실패 
    			response.setContentType("application/json;charset=UTF-8");
    			response.getWriter().print(false);
    			
    		}else { // n이아닌 다른값이면 로그인 성공 
    			
    			// 
    			ObjectMapper objectMapper = new ObjectMapper() ;
    			
    			response.setContentType("application/json;charset=UTF-8");
    			response.getWriter().print(objectMapper.writeValueAsString(loginDto) );
    			
    			// 2. 세션에 저장한다. 
        		request.getSession().setAttribute("loginDto", loginDto);
        		
        		// 세션 상태 확인 
        		 	// 강제 타입변환 
        		RiderDto dto = (RiderDto)request.getSession().getAttribute("loginDto");  
        		System.out.println("세션 상태: "+dto);
    		}
    			
    	}// if  e
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String json = null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		if( type.equals("ridcheck") ) {
		// 1. 요청 
		String type1 = request.getParameter("type1");
			System.out.println("type1: "+type1);
		String data = request.getParameter("data");
			System.out.println("data: "+data);
		
		// 3. DAO 요청 결과 
		boolean result = RiderDao.getInstance().findId(type, data);
		
		// 4. 결과 응답한다.
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
		
		
		}else if( type.equals("findid") ) {
			
			String rname = request.getParameter("rname"); 
				 System.out.println("rname: "+rname);
				
			String rphone = request.getParameter("rphone");
				System.out.println("rphone: "+rphone);
			
		 // dao 
		 RiderDto riderDto = RiderDao.getInstance().onFind(type, rname, rphone);
		 
		 json = objectMapper.writeValueAsString(riderDto);
				
		// 4. 결과 응답한다.
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(json);
		System.out.println("json: "+json);
				
		}else if( type.equals("findPwd") ) {
			
			String rname = request.getParameter("rname");
				System.out.println("rname: "+rname);
			
			String rid = request.getParameter("rid");
				System.out.println("rid: "+rid);
			
			// dao 전달 
			RiderDto riderDto = RiderDao.getInstance().onFind(type, rname, rid); 
			
			json = objectMapper.writeValueAsString(riderDto); 
			
			// 4. 결과 응답한다.
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
		}
		
	}
}














