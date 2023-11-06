package service;



import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class EmailService {

	// 1. 필드
		// 보내는 사람의 이메일주소 [ 관리자 이메일 ]
	private String fromEmail = "보내는 사람의 이메일주소";
		// 보내는 사람의 이메일주소 비밀번호 
	private String fromEmailPwd = "보내는사람의 이메일주소 비밀번호";
	
	// 2. 생성자 
	public EmailService() {}
	
	// 3. 메소드 
		// 1. 이메일 보내기 함수 
	public boolean authsend( String toEmail , String contentHTML ) {
		
		// 1. 호스팅 설정
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.naver.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp,ssl.protocols", "TLSv1.2");
		
		// 2. 안중처리 [ 네이버기준 ]
		Authenticator authenticator = new Authenticator() {
			
			// 패스워드 검증 함수 
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(fromEmail, fromEmailPwd);
			}
			
		};
		
		// 인증후 결과정보를 담고 있는 객체 
		Session session = Session.getDefaultInstance(properties, authenticator);
		
		try {
			// 1. MimeMessage 객체 생성 
			MimeMessage message = new MimeMessage( session );
			// 2. .setFrom( new InternetAddress("보내는사람의 이메일주소") );
			message.setFrom( new InternetAddress(fromEmail) );
			
			// 3. .addRecipient( Message.RecipientType.TO , "받는사람의 이메일주소" );
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			
			// 4. .setSubject("메일제목");
			message.setSubject("JSP회원가입 인증코드 발송");
			
			// 5. .setText("메일내용");
			message.setText("안녕하세요. 이메일 인증코드 : "+contentHTML);
			
			// * 메일 전송
			Transport.send(message);
			
		} catch (Exception e) {System.out.println(e);}
		
		return false;// 메일전송실패 
	}
	
}// class e
