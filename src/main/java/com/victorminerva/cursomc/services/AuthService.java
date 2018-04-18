package com.victorminerva.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.victorminerva.cursomc.domain.Cliente;
import com.victorminerva.cursomc.repositories.ClienteRepository;
import com.victorminerva.cursomc.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepo.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado.");
		}

		String newPass = newPassword();
		cliente.setSenha(passwordEncoder.encode(newPass));

		clienteRepo.save(cliente);

		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { /* Gera um digito */
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { /* Gera letra maiuscula */
			return (char) (rand.nextInt(26) + 65);
		} else { /* Gera letra minuscula */
			return (char) (rand.nextInt(26) * 97);
		}
	}
}
