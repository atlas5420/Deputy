package com.deputy.service.impl;

import com.deputy.dto.EmployeePrivateDto;
import com.deputy.model.EmployeePrivate;

/**
 * ��� ���� ���� ������ �������̽��� �ۼ� 
 */
public interface EmployeeService {

	
	public void join(EmployeePrivateDto dto);
	
	public void update(EmployeePrivateDto dto, EmployeePrivate rawDto);
	
}
