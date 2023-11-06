package controller.rider;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.RiderDao;
import model.dto.RiderDto;
import model.dto.ServiceDto;



/**
 * Servlet implementation class RiderServiceInfo
 */
@WebServlet("/RiderServiceController")
public class RiderServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RiderServiceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 라이더의 주행기록 조회
	 * 라이더의 입금내역 조회
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 타입정의
		 * 주행기록 DriveRecord : DR
		 * 입금정보중 이미 임금된 Already Deposit : AD
		 * 입금 예정중인 입금정보 Not yet Deposit : ND
		 */
		String type = request.getParameter("type");
		RiderDto riderDto =  (RiderDto)request.getSession().getAttribute("loginDto");
		//System.out.println("riderDto  :: "+ riderDto );
		int rno = riderDto.getRno();

		System.out.println("type  :: "+ type);
		System.out.println("rno  :: "+ rno);
		ArrayList<ServiceDto> result = null;
		String json= null;

		ObjectMapper objectMapper = new ObjectMapper();

		if(type.equals("DR")) { //주행기록
			System.out.println("IF :: DR ");
			result = RiderDao.getInstance().getDriveRecord(rno);

		} else if(type.equals("AD")) {

		} else if(type.equals("ND")) {

		}
		json = objectMapper.writeValueAsString( result );
		System.out.println("json  :: "+ json);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(  json );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
