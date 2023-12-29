package com.dialoguespace.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageService {

	@Autowired
	MessageService messageService;

	public boolean sendMyMessage() {
		DefaultMessageDTO myMsg = new DefaultMessageDTO();
		myMsg.setBtnTitle("자세히보기");
		myMsg.setMobileUrl("https://www.naver.com");
		myMsg.setObjType("text");
		myMsg.setWebUrl("https://www.daum.net");
		myMsg.setText("JAVA 메세지 테스트");

		String accessToken = AuthService.authToken;

		return messageService.sendMessage(accessToken, myMsg);
	}
	
	public boolean sendMyMessage(String btnTitle, String webUrl, String mobileUrl, String objType, String text) {
		DefaultMessageDTO myMsg = new DefaultMessageDTO();
		myMsg.setBtnTitle(btnTitle);
		myMsg.setWebUrl(webUrl);
		myMsg.setMobileUrl(mobileUrl);
		myMsg.setObjType(objType);
		myMsg.setText(text);

		String accessToken = AuthService.authToken;

		return messageService.sendMessage(accessToken, myMsg);
	}
}