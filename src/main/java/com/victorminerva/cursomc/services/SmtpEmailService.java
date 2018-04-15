package com.victorminerva.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOGGER.info("Enviando email ...");
		mailSender.send(msg);
		LOGGER.info("Email enviado.");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOGGER.info("Enviando email HTML...");
		javaMailSender.send(msg);
		LOGGER.info("Email enviado.");		
	}
}
