package controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;

import model.dao.BoardDao;
import model.dao.RiderDao;
import model.dto.BoardDto;
import model.dto.RiderDto;
import model.dto.ServiceDto;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청
		String type = request.getParameter("type");


		String json= null;
		ObjectMapper objectMapper = new ObjectMapper();


		if(type.equals("getList")) { //리스트출력

			ArrayList<BoardDto> result = BoardDao.getInstance().boardList();
			json = objectMapper.writeValueAsString( result );

		} else if(type.equals("getDetail")) { //상세페이지 출력

			int bno = Integer.parseInt(request.getParameter("bno"));
			BoardDto result = BoardDao.getInstance().boardView(bno);
			json = objectMapper.writeValueAsString( result );

		} else if(type.equals("N")||type.equals("E")){
			String btarget = request.getParameter("btarget");
			int limit = Integer.parseInt(request.getParameter("limit"));
			ArrayList<BoardDto> result = BoardDao.getInstance().onList(type, btarget,limit);
			json = objectMapper.writeValueAsString( result );
		}else if(type.equals("AD")) { // 입금내역을 클릭했을때
			
			RiderDto riderDto = (RiderDto)request.getSession().getAttribute("loginDto");
			// 라이더 번호 
			int rno = riderDto.getRno(); 
			
			ArrayList<ServiceDto> list = RiderDao.getInstance().getIncome(rno, "Y" );
			
			// 라이더 
			
		}else if(type.equals("ND")) { // 입금 예정을 클릭했을때 
			
			RiderDto riderDto = (RiderDto)request.getSession().getAttribute("loginDto");
			
			// 라이더 번호
			int rno = riderDto.getRno();
			
			ArrayList<ServiceDto> list = RiderDao.getInstance().getIncome(rno, "Y");
			
		}


		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(  json );

	}

	/**
	 * 2. 공지사항/이벤트 등록
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.저장경로 지정
		String uploadPath = request.getServletContext().getRealPath("/gorider/admin/board/bfiles");
		System.out.println("uploadPath :: "+uploadPath);
		// 2. 디스크파일아이템팩토리  객체 생성
		DiskFileItemFactory itemFactory = new DiskFileItemFactory();

		//2-1 생성된 객체에 옵션set하기
		itemFactory.setRepository(new File(uploadPath)); 	//저장위치
		itemFactory.setSizeThreshold(1024*1024-1024);		//용량
		itemFactory.setDefaultCharset("UTF-8");				//한글인코딩

		//3 ServletFileUpload객체생성, itemFactory매개값 전달
		ServletFileUpload fileUpload = new ServletFileUpload(itemFactory);

		try {
			System.out.println("try시작");
			//업로드된 파일명을 저장할 map컬렉션
			Map<Integer,String> imgList = new HashMap<>();

			//폼데이터에 저장된 모든 태그의 값들을 List컬렉션으로 저장
			List<FileItem> fileList = fileUpload.parseRequest(request);

			//업로드
			int i = 0;
			int j = 0;
			for(FileItem item : fileList) {
				//item.write(new File(uploadPath));
				//일반필드이면
				System.out.println("fileList아이템"+j+ " : "+ fileList.get(j).getString() );
				if(item.isFormField()) {


				} else {//파일필드면
					//파일이름 가져오기 : itetm.getName();
					//System.out.println( "업로드할 파일명 : " + item.getName() ); // .getName()

					//파일명 UUID로 식별id만들기
					UUID uuid = UUID.randomUUID();
					//System.out.println("uuid  : "+uuid);
					// 파일명에 "-"하이픈이 있으면 "_"언더바로 바꿔주고 uuid로 생성된 식별자 더해서 파일명 생성하기
					String filename = uuid+"-"+item.getName().replaceAll("-","_");

					//파일업로드 경로를 파일경로+파일명으로 파일타입설정
					File fileUploadPath = new File(uploadPath + "/" + filename);
					item.write(fileUploadPath);
					i++;

					imgList.put(i, filename);
					//System.out.println("filename :: "+ filename);
				}
				j++;
			}
			//BoardDto에 담기

			BoardDto boardDto = new BoardDto(
					fileList.get(0).getString(),
					fileList.get(1).getString(),
					fileList.get(2).getString(),
					fileList.get(3).getString(),
					fileList.get(4).getString(),
					fileList.get(5).getString(),
					imgList	);
			System.out.println("BoardDto :: "+ boardDto);
			boolean result = BoardDao.getInstance().save(boardDto);

			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(result);
		} catch (Exception e) {
			e.getStackTrace();
		}




	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
