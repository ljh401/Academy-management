package controller.rider;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.RiderDao;
import model.dto.RiderDto;

// 링크 : http://localhost/ezenTeam1/RiderInfoController
@WebServlet("/RiderInfoController")
public class RiderInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RiderInfoController() {}
    
    // 저장
    // 1. 첨부파일 있을때 회원가입
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 도착");
    	
		// 첨부파일 있을때 회원가입
		String uploadpath = request.getSession().getServletContext().getRealPath("/gorider/rider/img");
		System.out.println("rider 폴더 img 폴더 실제(서버) 경로 : "+uploadpath);
		
		// 첨부파일 전송 했을때 
			// 1. 첨부파일 서버pc에 업로드 
		MultipartRequest mulit = new MultipartRequest(
				request, 		// 1. 요청방식 
				uploadpath ,	// 2. 첨부파일을 저장할 폴더 경로
				1024*1024*10 ,  // 3. 첨부파일 용량 허용 범위 [ 바이트단위 ]
				"UTF-8" ,
				new DefaultFileRenamePolicy() // 5. [ 파일명중복제거 ]
			);
		
	// 2. from 안에 있는 각 데이터 호출 
		//  rname , rid , rpwd , rphoto , rlicense ,  rregistration , raccount , rbank 
		// 1. ajax 통신받은 data객체의 '속성명' 요헝한다. 
			// 이름
		String rname = mulit.getParameter("rname");     			System.out.println("rname: "+rname);
			// 아이디
		String rid = mulit.getParameter("rid"); 	    			System.out.println("rid: "+rid);
			// 비밀번호
		String rpwd = mulit.getParameter("rpwd"); 					System.out.println("rpwd: "+rpwd);
			// 라이더 전화번호 
		String rphone = mulit.getParameter("rphone"); 				System.out.println("rphone: "+rphone);
			// 프로필사진 
		String rphoto = mulit.getFilesystemName("rphoto");   			System.out.println("rphoto: "+rphoto);	
			// 면허증 
		String rlicense = mulit.getFilesystemName("rlicense");			System.out.println("rlicense: "+rlicense);
			// 차량등록증 
		String rregistration = mulit.getFilesystemName("rregistration"); System.out.println("rregistration: "+rregistration);
			// 계좌번호
		String raccount = mulit.getParameter("raccount"); 			System.out.println("raccount: "+raccount);
			// 은행명 
		String rbank = mulit.getParameter("rbank");					System.out.println("rbank: "+rbank);
			// 라이더 차량번호
		String rbikenum = mulit.getParameter("rbikenum"); 			System.out.println("rbikenum: "+rbikenum);
		
		// 2. 객체화
			// 입력받을 dto 설계 부르기 
		RiderDto riderDto = new RiderDto(rname, rid, rpwd, rphone, rphoto, 
				rlicense, rregistration, raccount, rbank, rbikenum);
		
		// 3. Dao 객체 전달후 결과 응답받기 
		boolean result = RiderDao.getInstance().RiderSignup(riderDto);
		
		// 4. 결과를 ajax에게 응답한다.
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
		
    }
    
    // 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// 1. 요청
		String type = request.getParameter("type");
			// * 만약에 타입이 info이면 
		if(type.equals("info") ) {
			// 섹션에 저장된 로그인 객체를 꺼낸다.
			// 1. 세션호출한다. [ 세션타입은 object ]
			Object session = request.getSession().getAttribute("loginDto");
			System.out.println((Object)session);
			// 2. 타입변환한다. [ 부모 -> 자식 ( 캐스팅 / 강제타입변환 ) ]
			
				// 원래 로그인세션에는 계좌정보가 없다..[ 안전상 로그인세션에는 개인정보가 없고... 회원번호나 아이디.. ]
			    RiderDto riderDto = (RiderDto) session;
			    
			    // 로그인세션에서 loginDto 꺼내서 loginDto에 있는 아이디로 라이더정보 구하기.
			    RiderDto riderDto2 = RiderDao.getInstance().info( riderDto.getRid() );
			    
			    System.out.println("RiderInfoController::riderDto::" + riderDto2);
			    ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(riderDto2);
			
		// Dto는 JS가 이해살수 없는 언어이기 때문에 JS가 이해할수 있게 JS언어로 변환해줘야한다. [ jackson 라이브러리사용 ]
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
			// 응답한다.
			
			
		}else if(type.equals("logout") ) {
			// * 세션에 저장된 로그인 객체를 없애기/초기화/지우기/삭제 
			// 방법 : (세션 특정 속성) 초기화하는 방법 jvm GC(쓰레기수집기 = 해당 객체를 아무도 참조하고 있지 않으면) 
			request.getSession().setAttribute("logout", null);
			
		}
		
	}

	// 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 첨부파일 서버pc에 업로드( COS.jar 라이브러리 )
		MultipartRequest multi = new MultipartRequest(
				request, 	// 1. 요청방식
				request.getServletContext().getRealPath("gorider/rider/img") , // 2. 첨부파일을 저장할 경로
				1024 * 1024 * 10 ,		// 3. 첨부파일 용량 허용 번위 [ 바이트 단위 ] 10mb
				"UTF-8" ,			// 4. 한글 인코딩타입 
				new DefaultFileRenamePolicy()
				);
		
		
		// Dao [ 로그인된 라이더번호 , 수정할 값 ]
		Object object = request.getSession().getAttribute("loginDto");
		RiderDto riderDto = (RiderDto)object;
		int rno = riderDto.getRno();
		
		// 
		String change = multi.getParameter("type");
	
		String type = null;
		String value = null;
		
		System.out.println( "change : " + change);
		
		if(change.equals("프로필사진") ) { // 프로필사진수정할경우			// 수정할 항목이 1개 

			System.out.println("프로필사진if: ");
				
			
			type = "rphoto";
			value = multi.getFilesystemName("rphoto");			// 수정할 항목이 1개 
			
			
			System.out.println("type: "+type);
			
			boolean result = RiderDao.getInstance().rupdate(rno , type , value); // 모든 타입에 맞춰 사용할수 없음..
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(result);
			
			
		}else if(change.equals("라이더차량번호") ) { // 라이더 차량번호 수정할경우
			System.out.println("라이더차량번호if: ");
			 type = "rbikenum";
			value = request.getParameter("rbikenum");
			
			
			System.out.println("type: "+type);
			
			boolean result = RiderDao.getInstance().rupdate(rno , type , value); // 모든 타입에 맞춰 사용할수 없음..
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(result);
			
		}else if(change.equals("라이더계좌번호")) {					// 수정할 항목이 2개 
			
			System.out.println("라이더계좌번호if: " );
			
			String rbankinfo = multi.getParameter("rbankinfo"); // 입력받은 은행 
			String raccountinfo = multi.getParameter("raccountinfo"); // 입력받은 계좌번호. 
			
			String type1 = "rbank";  // 변경할 대상 [ 은행 필드 ] 
			String type2 = "raccount";// 변경할 대상 [ 계좌번호 필드]
				 //value = request.getParameter("rbank");
				//value = request.getParameter("raccount");
	
			System.out.println("type: "+type);
			
			boolean result = RiderDao.getInstance().rupdate2(rno, type1, type2, rbankinfo, raccountinfo); // 모든 타입에 맞춰 사용할수 없음..
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(result);
		
			
		}

		
	 }
		

	// 삭제 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}


/*
 * 
 * 		// ????????????? 왜 타입이 두개고... 벨류가 듀개 일까요??? 
			// 은행명 과 계좌번호 두개를 수정하려고 넣었습니다 // 네???? 하나의 변수에 2개가 저장이 될수 있다고 생각 하실까요??
			// 아 애초에 타입이 하나네요.. 그렇죠... 그럼 ..  
			// 업데이트 할때 필요한 정보들이 뭐뭐 있을까요??? 무엇을 어떻게 변경할껀지... 여기에 적어보세요..
		
		
	무엇을: 라이더 은행명[1개]] 과 계좌번호[1개]] 를
	어떻게: 각각[ 각각 이라고 했으니까 은행명[1개],계좌번호[1개 ] 수정한다.
	그럼 데이터는 각각 필요한거겠네요... 총 4개의 변수가 필요한거겠죠???
	왜 4개죠?? 4개 맞을까요? 2개 아닌가요? 왜 두개죠???
	은행명[1개] 계좌번호[1개] 총 2개 아닌가요? 제가 먼저 발씀 드린부분은 무엇을 어떻게...
	은행명을 계좌번호로 인가요???
	은행명을 입력받은 은행명으로 인가요???
	아.....
	그걸 생각해보지 못했습니다 .
	무엇을[주체] 어떻게[수정할내용]
	그러면 은행명을 입력받은 계좌번호가 맞는거같아요 ??
	2개 수정한다고 했으니까.. 무엇을[은행명을] 어떻게[입력받은내용으로]] / 무엇을[계좌번호을]] 어떻게[입력받은내용으로]
	이해 되었을까요??
	네 이해 했습니다
	
	그럼 이코드의 문제 가 위에 다른 타입들은 수정할때 1개씩만 수정하고 있어요...
	근데 라이더계좌번호 은 수정을 2개 해야 하므로 다른 타입과 DAO 함수를 같이 사용할수가 없어요.. 
	 rupdate함수를 같이 사용할수 없다는거죠.. 
	 
	 이해했습니다...
	 
	 타입마다 따로 dao 처리를 해야 될것 같아요.. 
	 
	 설명이 필요할까요?? 먼저 해보시겠어요??
	 네 해볼께요 강사님 한번만 봐주실수 있을까요? 무엇을 봐줄까요??
	 일단 지금 코드작성하는거 아 본인이 코드 치는게 디스코드로 봐달라는거죠??
	 그렇게하셔도되고 아니면 지금 그냥 봐주셔도됩니다.
	 디스코드로 켜두세요..
	 넵 그 학원카톡방에 올린 서버에서 켜주세요...
	 제가 보내드린 디스코드말씀이신거죠??아까 학원 톡방에 디코 서버 링크 별도로 올렸어요..
	 넵
* */

