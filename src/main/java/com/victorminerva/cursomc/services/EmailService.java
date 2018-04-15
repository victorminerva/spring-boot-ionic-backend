package com.victorminerva.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.victorminerva.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
