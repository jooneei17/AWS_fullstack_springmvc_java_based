package co.jmymble.handler;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import co.jmymble.domain.MemberVO;
import co.jmymble.domain.NoteVO;
import co.jmymble.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
//ServerSocket 역할
@EnableWebSocket
public class NoteHandler extends TextWebSocketHandler{

	// lifecycle
	// 접속, 실제할일, 종료
	
	//접속자 관리 객체
	private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	@Autowired
	private NoteService noteService;
	
	//접속 되었을 때 할 일
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	      log.warn("입장한 사람 : " + getIdBySession(session));
	      sessions.forEach(log::warn);
	      sessions.add(session);
	      log.warn("현재 접속자 수 : " + sessions.size());
	      log.warn("현재 접속자 정보");
	      log.warn(sessions.stream().map(s -> getIdBySession(s)).collect(Collectors.toList())); 
	}

	//접속 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.warn(session.getId() + "로그아웃");
		sessions.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.warn(noteService);
		
		String receiver = message.getPayload(); //js, ws.send() 수신자 
		String sender = getIdBySession(session);
		List<NoteVO> list0 = noteService.getSendList(sender); //발송자
		List<NoteVO> list1 = noteService.getReceiveList(receiver);
		List<NoteVO> list2 = noteService.getReceiveUncheckedList(receiver);
		
		Map<String, Object> map = new HashMap<>();
		map.put("sendList", list0);
		map.put("receiveList", list1);
		map.put("receiveUncheckedList", list2);
		map.put("sender", sender);
		
		Gson gson = new Gson();
		for(WebSocketSession s : sessions) {
			if(receiver.equals(getIdBySession(s)) || session == s){
				s.sendMessage(new TextMessage(gson.toJson(map)));
			}
		}
	}
	private String getIdBySession(WebSocketSession session){
		Object obj = session.getAttributes().get("member");
		String id = null;
//		if(obj != null && obj instanceof MemberVO){
//			id = ((MemberVO)obj).getId();
//		}
		return id;
	}
	
}
