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

@ServerEndpoint("/gpssocket/{sno}")
public class GpsSocket {
	
	public static Map< Session , Integer > gpsList = new HashMap<>();
	
	@OnOpen
	public void OnOpen( Session session , @PathParam("sno") int sno ) {
		gpsList.put(session ,sno );
	}
	
	@OnClose
	public void OnClose( Session session ) {
		gpsList.remove(session);
	}
	
	@OnMessage
	public void OnMessage( Session session , String msg ) {
		System.out.println( msg );
		gpsList.keySet().forEach( s ->{
			try {
				// gps회원명단의 서비스번호와 현재위치메시지를 보낸회원[라이더] 과 같은 서비스번호[sno] 이면.
				if( gpsList.get(s)  == gpsList.get( session ) )
				s.getBasicRemote().sendText(msg);
			} 
			catch (IOException e) { e.printStackTrace(); }
		});
	}
	
}
