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
		//비밀번호 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPwd = dto.getPassword();
		String encPwd = encoder.encode(rawPwd);
		dto.setPassword(encPwd);
		
		//public default 정보입력
		EmployeePublicDto pubDto = new EmployeePublicDto();
		pubDto.setDepartment(Department.미지정);
		pubDto.setPosition(Position.사원);
		pubDto.setRole(roleRepository.findByRoleName("EMPLOYEE").get(0));
		String number = LocalDateTime.now().getYear() + "" + Math.round((Math.random()*100));
		pubDto.setNumber(number);
		
		//public 정보 저장
		EmployeePublic pubUpdate = EmployeePublic.joinBuilder().dto(pubDto).build();
		employeePublicRepository.save(pubUpdate);
		
		//private 정보 저장 join 완료
		dto.setEmployeePublic(pubUpdate);
		EmployeePrivate priUpdate = EmployeePrivate.joinBuilder().dto(dto).build();
		employeePrivateRepository.save(priUpdate);
	}

	@Override
	public void update(EmployeePrivateDto newDto, EmployeePrivate rawDto) {
		//새로 입력된 비밀번호 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPwd = newDto.getPassword();
		String encPwd = encoder.encode(rawPwd);
		newDto.setPassword(encPwd);
		newDto.setUsername(rawDto.getUsername());
		newDto.setName(rawDto.getName());
		newDto.setEmployeePublic(rawDto.getEmployeePublic());
		
		//새로운 정보와함께 저장 update 완료
		EmployeePrivate employeePrivate = EmployeePrivate.joinBuilder().dto(newDto).build();
		employeePrivateRepository.save(employeePrivate);
	}

}
