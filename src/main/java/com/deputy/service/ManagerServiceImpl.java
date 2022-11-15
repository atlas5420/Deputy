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
		//�����Ǿ�� �� ���� ���� (Manager���� public�� ���õ� ������ update�Ѵ�)
		newDto.setHistories(rawDto.getHistories());
		newDto.setNumber(rawDto.getNumber());
		//���õ� role ������ ���� ���峻�� ���� ����
		if(newDto.getPosition().equals(Position.���)) {
			System.out.println("role type check = " + roleRepository.findAll().get(0));
			newDto.setRole(roleRepository.findAll().get(0));
		} else if (newDto.getPosition().equals(Position.�Ŵ���)) {
			newDto.setRole(roleRepository.findAll().get(2));
		} else if (newDto.getPosition().equals(Position.������)) {
			newDto.setRole(roleRepository.findAll().get(1));
		}
		
		//������ role�� ���� public update �Ϸ�
		EmployeePublic update = EmployeePublic.joinBuilder().dto(newDto).build();
		employeePublicRepository.save(update);
	}

	@Override
	public void delete(Long id) {
		employeePrivateRepository.deleteById(id);
	}

	@Override
	public List<EmployeePrivate> search(String keyword) {
		//���� �˻�����Ʈ
		List<EmployeePrivate> search = employeePrivateRepository.findByNameContaining(keyword);
		return search;
		
	}
		
}
