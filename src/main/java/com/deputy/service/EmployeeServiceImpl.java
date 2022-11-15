package com.deputy.service;

import java.time.LocalDateTime;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deputy.dto.EmployeePrivateDto;
import com.deputy.dto.EmployeePublicDto;
import com.deputy.model.EmployeePrivate;
import com.deputy.model.EmployeePublic;
import com.deputy.model.employeeEnum.Department;
import com.deputy.model.employeeEnum.Position;
import com.deputy.repository.EmployeePrivateRepository;
import com.deputy.repository.EmployeePublicRepository;
import com.deputy.repository.RoleRepository;
import com.deputy.service.impl.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeePrivateRepository employeePrivateRepository;
	private final EmployeePublicRepository employeePublicRepository;
	private final RoleRepository roleRepository;
	
	@Override
	public void join(EmployeePrivateDto dto) {
		//��й�ȣ ��ȣȭ
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPwd = dto.getPassword();
		String encPwd = encoder.encode(rawPwd);
		dto.setPassword(encPwd);
		
		//public default �����Է�
		EmployeePublicDto pubDto = new EmployeePublicDto();
		pubDto.setDepartment(Department.������);
		pubDto.setPosition(Position.���);
		pubDto.setRole(roleRepository.findByRoleName("EMPLOYEE").get(0));
		String number = LocalDateTime.now().getYear() + "" + Math.round((Math.random()*100));
		pubDto.setNumber(number);
		
		//public ���� ����
		EmployeePublic pubUpdate = EmployeePublic.joinBuilder().dto(pubDto).build();
		employeePublicRepository.save(pubUpdate);
		
		//private ���� ���� join �Ϸ�
		dto.setEmployeePublic(pubUpdate);
		EmployeePrivate priUpdate = EmployeePrivate.joinBuilder().dto(dto).build();
		employeePrivateRepository.save(priUpdate);
	}

	@Override
	public void update(EmployeePrivateDto newDto, EmployeePrivate rawDto) {
		//���� �Էµ� ��й�ȣ ��ȣȭ
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPwd = newDto.getPassword();
		String encPwd = encoder.encode(rawPwd);
		newDto.setPassword(encPwd);
		newDto.setUsername(rawDto.getUsername());
		newDto.setName(rawDto.getName());
		newDto.setEmployeePublic(rawDto.getEmployeePublic());
		
		//���ο� �������Բ� ���� update �Ϸ�
		EmployeePrivate employeePrivate = EmployeePrivate.joinBuilder().dto(newDto).build();
		employeePrivateRepository.save(employeePrivate);
	}

}
