package com.deputy.service.impl;

import java.util.List;

import com.deputy.dto.EmployeePublicDto;
import com.deputy.model.EmployeePrivate;
import com.deputy.model.EmployeePublic;

/**
 * �Ŵ����̻� �����ڰ� ������ ȸ�� ���� ������ ����(EmployeePublic)�� �� �ֽ��ϴ�.
 * ���� ���� ����� �ҷ��� �� �ֽ��ϴ�. 
 */
public interface ManagerService {

	public void update(EmployeePublicDto employeePublicDto, EmployeePublic employeePublic);
	
	public void delete(Long id);

	public List<EmployeePrivate> search(String keyword);
}
