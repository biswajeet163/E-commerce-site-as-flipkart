package com.hackercode.action;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hackercode.constants.Constants;
import com.opensymphony.xwork2.ActionSupport;
public class Emailer extends ActionSupport{
	
	private static final long serialVersionUID=-3511373266750531748L;
	private String from;
	private String to;
	private String password;
	private String subject;
	private String body;
	static Properties properties=new Properties();
	static {
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "java.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
	
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String execute() throws Exception{
		String ret = Constants.SUCCESS;
		try {
			Session session=Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication
				getPasswordAuthentication() {
					return new PasswordAuthentication(from,password);
				}
			}
			);
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			
		}catch(Exception e){
			ret=Constants.ERROR;
			e.printStackTrace();
		}
		return ret;
	}
	
	
}