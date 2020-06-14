package com.project.tim05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.core.env.Environment;

@Transactional(readOnly = false)
@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;
	
	@Async
	public void sendDeclinanceMail(String email, String text) throws MailException, InterruptedException {
	
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Registration declined");
		mail.setText(text);
		javaMailSender.send(mail);

	}
	
	@Async
	public void sendAcceptanceeMail(String email, int id) throws MailException, InterruptedException {
	
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Registration accepted");
		mail.setText("http://localhost:4200/activateAccount?id=" + id + "&email=" + email);
		javaMailSender.send(mail);

	}
	
	@Async
	public void sendAppointmentApprovalMail(String email, String textStart, String text) throws MailException, InterruptedException {
	
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Appointment approved");
		mail.setText(textStart  +" If you want to cancel the appointment, go to next link: http://localhost:4200/cancelAppointment" + text);
		javaMailSender.send(mail);

	}
	
	@Async
	public void sendAppointmentNotificationAdmin(String email, String text) throws MailException, InterruptedException {
	
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Appointment request");
		mail.setText(text);
		javaMailSender.send(mail);

	}

	@Async
	public void sendPredefinedAppointmentNotificationPatient(String email, String text) throws MailException, InterruptedException {
	
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Appointment approved");
		mail.setText(text);
		javaMailSender.send(mail);

	}}
