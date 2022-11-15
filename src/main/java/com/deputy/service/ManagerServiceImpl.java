package com.deputy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deputy.dto.EmployeePublicDto;
import com.deputy.model.EmployeePrivate;
import com.deputy.model.EmployeePublic;
import com.deputy.model.employeeEnum.Position;
import com.deputy.repository.EmployeePrivateRepository;
import com.deputy.repository.EmployeePublicRepository;
import com.deputy.repository.RoleRepository;
import com.deputy.service.impl.ManagerService;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

	private final EmployeePrivateRepository employeePrivateRepository;
	private final EmployeePublicRepository employeePublicRepository;
	private final RoleRepository roleRepository;
	
	@Override
	public void update(EmployeePublicDto newDto, EmployeePublic rawDto) {
		//유지되어야 할 정보 저장 (Manager에서 public과 관련된 정보만 update한다)
		newDto.setHistories(rawDto.getHistories());
		newDto.setNumber(rawDto.getNumber());
		//선택된 role 정보에 따라 저장내용 조건 설정
		if(newDto.getPosition().equals(Position.사원)) {
			System.out.println("role type check = " + roleRepository.findAll().get(0));
			newDto.setRole(roleRepository.findAll().get(0));
		} else if (newDto.getPosition().equals(Position.매니저)) {
			newDto.setRole(roleRepository.findAll().get(2));
		} else if (newDto.getPosition().equals(Position.관리자)) {
			newDto.setRole(roleRepository.findAll().get(1));
		}
		
		//관리자 role을 통한 public update 완료
		EmployeePublic update = EmployeePublic.joinBuilder().dto(newDto).build();
		employeePublicRepository.save(update);
	}

	@Override
	public void delete(Long id) {
		employeePrivateRepository.deleteById(id);
	}

	@Override
	public List<EmployeePrivate> search(String keyword) {
		//유저 검색리스트
		List<EmployeePrivate> search = employeePrivateRepository.findByNameContaining(keyword);
		return search;
		
	}
		
}
