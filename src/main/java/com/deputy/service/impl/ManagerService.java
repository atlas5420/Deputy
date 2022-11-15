package com.deputy.service.impl;

import java.util.List;

import com.deputy.dto.EmployeePublicDto;
import com.deputy.model.EmployeePrivate;
import com.deputy.model.EmployeePublic;

/**
 * 매니저이상 권한자가 직원의 회사 내부 정보를 수정(EmployeePublic)할 수 있습니다.
 * 또한 직원 목록을 불러올 수 있습니다. 
 */
public interface ManagerService {

	public void update(EmployeePublicDto employeePublicDto, EmployeePublic employeePublic);
	
	public void delete(Long id);

	public List<EmployeePrivate> search(String keyword);
}
