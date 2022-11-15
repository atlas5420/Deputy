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
	 * @description 회원가입 처리 페이지
	 * @param dto view에서 입력된 값 
	 * @return service가 올바르게 작동시 200 OK를 return
	 */
	@PostMapping("/join/joinProc")
	@ApiOperation(value = "가입 처리", notes = "신규 가입시 join process를 수행하는 동작")
	public HttpStatus joinProc(@Valid @RequestBody EmployeePrivateDto dto) {
			employeeService.join(dto);
			return HttpStatus.OK;
	}

	/**
	 * @description 로그인된 객체의 정보의 업데이트 처리 페이지 (private 정보만 수정가능하다)
	 * @param newDto view에서 수정하기 위해 입력된 값
	 * @param principal security에서 인증된 객체 (로그인된 객체)
	 * @return service가 올바르게 작동시 200 OK를 return
	 */
	@PutMapping("/employee/updateProc")
	@ApiOperation(value = "개인정보 수정 처리", notes = "가입된 유저의 update process를 수행하는 동작")
	public HttpStatus updateProc(@Valid @RequestBody EmployeePrivateDto newDto, Principal principal) {
		EmployeePrivate rawDto = employeePrivateRepository.findByUsername(principal.getName());
		employeeService.update(newDto, rawDto);
		return HttpStatus.OK;
	}

}
