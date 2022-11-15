package com.deputy.service.impl;

import com.deputy.dto.EmployeePrivateDto;
import com.deputy.model.EmployeePrivate;

/**
 * 사원 가입 수정 삭제를 인터페이스로 작성 
 */
public interface EmployeeService {

	
	public void join(EmployeePrivateDto dto);
	
	public void update(EmployeePrivateDto dto, EmployeePrivate rawDto);
	
}
