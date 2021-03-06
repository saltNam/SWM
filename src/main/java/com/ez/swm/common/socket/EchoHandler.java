package com.ez.swm.common.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ez.swm.login.vo.Member;
 

public class EchoHandler extends TextWebSocketHandler {
	//로그인 한 전체
	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	
	// 1대1
	Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();
		
	//서버에 접속이 성공 했을때
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		System.out.println("세션 왔냐");
		System.out.println("sessions :  >> " + sessions );
		System.out.println("그냥 세션 확인 >>>> " + session);
		String senderNickName = getNickName(session);
		System.out.println("senderNickName 확인 : " + senderNickName);
		userSessionsMap.put(senderNickName , session);
	}
	
	//소켓에 메세지를 보냈을때
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		String senderEmail = getEmail(session);
		//모든 유저에게 보낸다 - 브로드 캐스팅
//		for (WebSocketSession sess : sessions) {
//			sess.sendMessage(new TextMessage(senderNickname + ": " +  message.getPayload()));
//		}
		
		//protocol : cmd , 댓글작성자, 게시글 작성자 , seq (reply , user2 , user1 , 12)
		String msg = message.getPayload();

		if(StringUtils.isNotEmpty(msg)) {
			String[] strs = msg.split(",");
			System.out.println("msg : 확인 " + msg);
			int strs2 = strs.length;
			System.out.println("strs 길이 >> :" + strs2);
			if(strs != null && strs.length == 5) {
				String cmd = strs[0];
				String caller = strs[1]; 
				String receiver = strs[2];
				String receiverEmail = strs[3];
				String seq = strs[4];
				
				//작성자가 로그인 해서 있다면
				WebSocketSession boardWriterSession = userSessionsMap.get(receiverEmail);
				
				if("reply".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(caller + "님이 " + 
										"<a type='external' href='/mentor/menteeboard/menteeboardView?seq="+seq+"&pg=1'>" + seq + "</a> 번 게시글에 댓글을 남겼습니다.");
					boardWriterSession.sendMessage(tmpMsg);
				
				}else if("follow".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(caller + "님이 " + receiver +
							 "님을 팔로우를 시작했습니다.");
					boardWriterSession.sendMessage(tmpMsg);
					
				}else if("scrap".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(caller + "님이 " +
										//변수를 하나더 보낼수 없어서 receiver 변수에 member_seq를 넣어서 썼다.
										"<a type='external' href='/mentor/essayboard/essayboardView?pg=1&seq="+seq+"&mentors="+ receiver +"'>" + seq + "</a>번 에세이를 스크랩 했습니다.");
					boardWriterSession.sendMessage(tmpMsg);
				}
			}
			// 모임 신청 했을때
			if(strs != null && strs.length == 4) {
				String cmd = strs[0];
				String meeting_member = strs[1];
				String meeting_leader = strs[2];
				String meeting_no = strs[3];
				// String participation_seq = strs[4];
				
				// 모임 작성한 멘토가 로그인 해있으면
				WebSocketSession meeting_writerSession = userSessionsMap.get(meeting_member);
				for(int i=0; i < sessions.size(); i++) {
					System.out.println("sessions 확인 >>" + sessions.get(i));
					System.out.println("userSessionsMap 확인 >>" + userSessionsMap.get(sessions.get(i)));
				}
			
				System.out.println("미팅 라이터 세션 확인 >> : " + meeting_writerSession);
				System.out.println("cmd 확인 >> : " +  cmd);
				System.out.println("meeting_leader 확인 : "+ meeting_leader);
			
				if("apply".equals(cmd) && meeting_writerSession != null) {
					TextMessage tmpMsg = new TextMessage(
							meeting_member + "님이 모임을 신청했습니다. " +"<a type='external' href='/meeting/meetingDetail?meeting_no="+ meeting_no  +"'>신청서 보기</a>");
					meeting_writerSession.sendMessage(tmpMsg);
					System.out.println("tmpMsg>>>>" + tmpMsg);
				}
			}
		}
	}
	
	//연결 해제될때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//System.out.println("afterConnectionClosed " + session + ", " + status);
		userSessionsMap.remove(session.getId());
		sessions.remove(session);
	}
	
	//웹소켓 email 가져오기
	private String getNickName(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		System.out.println("httpSession 확인 : " + httpSession);
		Member loginUser = (Member)httpSession.get("member");
		System.out.println("loginUser 확인 >> : " + loginUser);
		if(loginUser == null) {
			return session.getId();
		} else {
			return loginUser.getNickName();
		}
	}
}