package controller.rider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.CallDao;

import model.dto.ServiceDto;


@ServerEndpoint("/callsocket/{userType}/{no}")
public class CallSocket {
	
	public static Map<Session , Integer > callList = new HashMap<>();
	public static Map<Session , Integer> riderlist = new HashMap<>();
	
	@OnOpen
	public void OnOpen( Session session, @PathParam("userType") String userType
			, @PathParam("no") int no ) {
		
		if ("rider".equals(userType)) {
            // 라이더인 경우 riderList에 세션 추가
			riderlist.put( session, no );
            System.out.println("라이더 세션 : " + session);
            System.out.println(riderlist);
        } else {
            // 사용자인 경우 callList에 세션 추가
            callList.put( session , no );
            System.out.println("사용자 세션 : " + session);
            System.out.println(callList);
        }
	}
	
	

	@OnClose
	public void OnClose( Session session ) {
		riderlist.remove(session);
		callList.remove(session);
	}
	
	@OnMessage
	public void OnMessage(Session session, String msg) throws IOException {
		System.out.println(msg);

		// String(json형식) Map로 바꾼다.
		ObjectMapper mapper = new ObjectMapper();
		
		
		try {
			JsonNode jsonNode = mapper.readTree(msg);
			String type = jsonNode.get("type").asText();

			if ("call".equals(type)) { // 사용자가 -> 콜 요청
				
				ServiceDto servicedto = mapper.convertValue(jsonNode, ServiceDto.class);
				
				// 콜 정보를 SQL로 DB에 담아서... 콜 번호를 반환 받는다..
				int result = CallDao.getInstance().MemberCall(servicedto.getMno(), servicedto.getSfromla(),
						servicedto.getSfromlo(), servicedto.getStola(), servicedto.getStolo(), servicedto.getSpayYN(), servicedto.getSpayment());
				
				if ( result > 0 ) { 	// 콜 정보를 SQL 담기를 성공했을대.. 
					System.out.println("사용자 정보 성공");
					// 라이더들에게 메시지 보내기.
					riderlist.keySet().forEach(s -> {
						try {
							
							servicedto.setSno(result); // 리턴 된 sno를 담아서..
							// 다시 json 변환..
							String sendMsg = mapper.writeValueAsString( servicedto );
								// 메시지를 받을 조건 : 출근 했으면서 상태가 가능이면  rstart = Y  , rcall = Y
								boolean result2 = CallDao.getInstance().getRiderstate( riderlist.get(s) );
								if( result2 ) { // 콜 받을수 있는 상태 
									s.getBasicRemote().sendText( sendMsg );
								}else { // 못받는 상태 
									
								}
								
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					});
					
				} else {
					System.out.println("실패");
				}
			} else if ("accept".equals(type)) { // 라이더가 -> 콜 수락
				System.out.println(type);
				ServiceDto servicedto = mapper.convertValue(jsonNode, ServiceDto.class);
				boolean result = CallDao.getInstance().RiderAccept(
						servicedto.getRno(),
						servicedto.getSriderla(),
						servicedto.getSriderlo(),
						servicedto.getSno()
						);
				System.out.println(result);
				 if (result) {
			            System.out.println("라이더 정보 성공");
			            ServiceDto riderinfo = CallDao.getInstance().ShowRiderInfo( servicedto.getSno() );
			            
			            
			            
			            callList.keySet().forEach( s ->{
			            	// 콜 수락시 회원들에게 콜 수락된 이벤트를 보내줘야한다.
			            	// 문제점 : 콜 소켓 리스트에 각 소켓들은  어떤 회원의 소켓인지 알고 있나요?? 모르죠.... 음,.... 음....
			            	// 방안 : 1. 리스트에 MAP 이용해서 List<Session , 접속한회원번호 >
			            	// 2. 그냥 보내서 각 클라이언트소켓들이 알아서 식별한다..
			            	// 뭐가 좋을까요? 음음음 잠시 휴식 하죠..  1번으로 하죠. 잠시만요.. 제 스피커 인줄 알았습ㄴ디ㅏ. ㅋㅋ
			            	// 하죠 
			            	// callList에서 메시지를 받을 세션(콜 받을 회원) 찾아서 메시지 보내기 

			    			try {
			    				// 콜 리스트(사용자 리스트 ) 에 있는 회원 번호와 콜정보의 있는 회원번호와 같으면
			    				// 콜 요청한 사람에게만 메시지 보내기.
			    				if( callList.get(s) == riderinfo.getMno() ) {
			    					System.out.println(" 콜리스트내 회원번호 " + callList.get(s) );
			    					System.out.println(" 콜리스트내 회원번호 " + riderinfo.getMno() );
			    					
			    					riderinfo.setType("accept");
			    					
			    					ObjectMapper objectMapper = new ObjectMapper();
							        String ridermsg = objectMapper.writeValueAsString(riderinfo);
							        
				    				s.getBasicRemote().sendText(ridermsg);
			    				}
			    				
			    			} 
			    			catch (IOException e) { e.printStackTrace(); }
			    		});
			        } else {
			            System.out.println("라이더 실패");
			        }
			} else if("out".equals(type)) {
				ServiceDto servicedto = mapper.convertValue(jsonNode, ServiceDto.class);
				callList.keySet().forEach( s ->{
					
					try {
						// [라이더가 보낸 메시지] 라이더와 같은 방에 있는 회원이면
						System.out.println("하차 컨트롤:" + servicedto.getMno() );
						if( callList.get(s) == servicedto.getMno()  ) {
							System.out.println("하차 컨트롤:" + servicedto.getMno() );
							s.getBasicRemote().sendText(msg);
						}
					} catch (Exception e) {
						e.printStackTrace(); 
					}
				});
				
				riderlist.keySet().forEach( s ->{
					try {
						// [라이더가 보낸 메시지] 라이더와 같은 방에 있는 회원이면
						System.out.println("하차 컨트롤:" + servicedto.getRno() );
						if( riderlist.get(s) == servicedto.getRno()  ) {
							// // 1. 서비스 상태 변경 // 2. 라이더 상태 변경
							boolean result = CallDao.getInstance().getOut(servicedto.getSno(),servicedto.getRno());
							s.getBasicRemote().sendText(msg);
						}
					} catch (Exception e) { e.printStackTrace(); }
				});
			} else if("on".equals(type)) {
				ServiceDto servicedto = mapper.convertValue(jsonNode, ServiceDto.class);
				callList.keySet().forEach( s ->{
					try {
						if( callList.get(s) == servicedto.getMno()  ) {
							s.getBasicRemote().sendText(msg);
						}
					} catch (Exception e) { e.printStackTrace();  }
				});
			}
		} catch (JsonProcessingException e) { e.printStackTrace(); }
	}

}
	


