package com.deputy.controller.apicontroller;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deputy.dto.EmployeePrivateDto;
import com.deputy.model.EmployeePrivate;
import com.deputy.repository.EmployeePrivateRepository;
import com.deputy.service.impl.EmployeeService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deputy")
public class EmployeeApiController {
	
	private final EmployeeService employeeService;
	private final EmployeePrivateRepository employeePrivateRepository;
	
	/**
	 * @description ȸ������ ó�� ������
	 * @param dto view���� �Էµ� �� 
	 * @return service�� �ùٸ��� �۵��� 200 OK�� return
	 */
	@PostMapping("/join/joinProc")
	@ApiOperation(value = "���� ó��", notes = "�ű� ���Խ� join process�� �����ϴ� ����")
	public HttpStatus joinProc(@Valid @RequestBody EmployeePrivateDto dto) {
			employeeService.join(dto);
			return HttpStatus.OK;
	}

	/**
	 * @description �α��ε� ��ü�� ������ ������Ʈ ó�� ������ (private ������ ���������ϴ�)
	 * @param newDto view���� �����ϱ� ���� �Էµ� ��
	 * @param principal security���� ������ ��ü (�α��ε� ��ü)
	 * @return service�� �ùٸ��� �۵��� 200 OK�� return
	 */
	@PutMapping("/employee/updateProc")
	@ApiOperation(value = "�������� ���� ó��", notes = "���Ե� ������ update process�� �����ϴ� ����")
	public HttpStatus updateProc(@Valid @RequestBody EmployeePrivateDto newDto, Principal principal) {
		EmployeePrivate rawDto = employeePrivateRepository.findByUsername(principal.getName());
		employeeService.update(newDto, rawDto);
		return HttpStatus.OK;
	}

}
