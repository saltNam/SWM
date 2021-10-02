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
	//�α��� �� ��ü
	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	
	// 1��1
	Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();
		
	//������ ������ ���� ������
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		System.out.println("���� �Գ�");
		System.out.println("sessions :  >> " + sessions );
		System.out.println("�׳� ���� Ȯ�� >>>> " + session);
		String senderNickName = getNickName(session);
		System.out.println("senderNickName Ȯ�� : " + senderNickName);
		userSessionsMap.put(senderNickName , session);
	}
	
	//���Ͽ� �޼����� ��������
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		String senderEmail = getEmail(session);
		//��� �������� ������ - ��ε� ĳ����
//		for (WebSocketSession sess : sessions) {
//			sess.sendMessage(new TextMessage(senderNickname + ": " +  message.getPayload()));
//		}
		
		//protocol : cmd , ����ۼ���, �Խñ� �ۼ��� , seq (reply , user2 , user1 , 12)
		String msg = message.getPayload();

		if(StringUtils.isNotEmpty(msg)) {
			String[] strs = msg.split(",");
			System.out.println("msg : Ȯ�� " + msg);
			int strs2 = strs.length;
			System.out.println("strs ���� >> :" + strs2);
			if(strs != null && strs.length == 5) {
				String cmd = strs[0];
				String caller = strs[1]; 
				String receiver = strs[2];
				String receiverEmail = strs[3];
				String seq = strs[4];
				
				//�ۼ��ڰ� �α��� �ؼ� �ִٸ�
				WebSocketSession boardWriterSession = userSessionsMap.get(receiverEmail);
				
				if("reply".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(caller + "���� " + 
										"<a type='external' href='/mentor/menteeboard/menteeboardView?seq="+seq+"&pg=1'>" + seq + "</a> �� �Խñۿ� ����� ������ϴ�.");
					boardWriterSession.sendMessage(tmpMsg);
				
				}else if("follow".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(caller + "���� " + receiver +
							 "���� �ȷο츦 �����߽��ϴ�.");
					boardWriterSession.sendMessage(tmpMsg);
					
				}else if("scrap".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(caller + "���� " +
										//������ �ϳ��� ������ ��� receiver ������ member_seq�� �־ ���.
										"<a type='external' href='/mentor/essayboard/essayboardView?pg=1&seq="+seq+"&mentors="+ receiver +"'>" + seq + "</a>�� �����̸� ��ũ�� �߽��ϴ�.");
					boardWriterSession.sendMessage(tmpMsg);
				}
			}
			// ���� ��û ������
			if(strs != null && strs.length == 4) {
				String cmd = strs[0];
				String meeting_member = strs[1];
				String meeting_leader = strs[2];
				String meeting_no = strs[3];
				// String participation_seq = strs[4];
				
				// ���� �ۼ��� ���䰡 �α��� ��������
				WebSocketSession meeting_writerSession = userSessionsMap.get(meeting_member);
				for(int i=0; i < sessions.size(); i++) {
					System.out.println("sessions Ȯ�� >>" + sessions.get(i));
					System.out.println("userSessionsMap Ȯ�� >>" + userSessionsMap.get(sessions.get(i)));
				}
			
				System.out.println("���� ������ ���� Ȯ�� >> : " + meeting_writerSession);
				System.out.println("cmd Ȯ�� >> : " +  cmd);
				System.out.println("meeting_leader Ȯ�� : "+ meeting_leader);
			
				if("apply".equals(cmd) && meeting_writerSession != null) {
					TextMessage tmpMsg = new TextMessage(
							meeting_member + "���� ������ ��û�߽��ϴ�. " +"<a type='external' href='/meeting/meetingDetail?meeting_no="+ meeting_no  +"'>��û�� ����</a>");
					meeting_writerSession.sendMessage(tmpMsg);
					System.out.println("tmpMsg>>>>" + tmpMsg);
				}
			}
		}
	}
	
	//���� �����ɶ�
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//System.out.println("afterConnectionClosed " + session + ", " + status);
		userSessionsMap.remove(session.getId());
		sessions.remove(session);
	}
	
	//������ email ��������
	private String getNickName(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		System.out.println("httpSession Ȯ�� : " + httpSession);
		Member loginUser = (Member)httpSession.get("member");
		System.out.println("loginUser Ȯ�� >> : " + loginUser);
		if(loginUser == null) {
			return session.getId();
		} else {
			return loginUser.getNickName();
		}
	}
}