package com.victorminerva.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.victorminerva.cursomc.domain.Cliente;
import com.victorminerva.cursomc.domain.enums.TipoCliente;
import com.victorminerva.cursomc.dto.ClienteNewDTO;
import com.victorminerva.cursomc.repositories.ClienteRepository;
import com.victorminerva.cursomc.resources.exception.FieldMessage;
import com.victorminerva.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepo;
	
	@Override
	public void initialize(ClienteInsert clienteInsert) {
	}

	@Override
	public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>(); 
		
		if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo())
				&& !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido."));
		} else if (clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
				&& !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido."));
		}
		
		Cliente clienteDB = clienteRepo.findByEmail(clienteNewDTO.getEmail());
		if (clienteDB != null) {
			list.add(new FieldMessage("email", "Email já existente."));
		}
		
		for (FieldMessage field : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage()).addPropertyNode(field.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}