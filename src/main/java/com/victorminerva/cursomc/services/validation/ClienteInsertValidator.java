package com.victorminerva.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.victorminerva.cursomc.domain.enums.TipoCliente;
import com.victorminerva.cursomc.dto.ClienteNewDTO;
import com.victorminerva.cursomc.resources.exception.FieldMessage;
import com.victorminerva.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

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
		
		for (FieldMessage field : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage()).addPropertyNode(field.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}