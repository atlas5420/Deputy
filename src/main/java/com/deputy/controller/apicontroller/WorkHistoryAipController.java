package com.deputy.controller.apicontroller;

import java.net.URI;
import java.security.Principal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deputy.model.EmployeePublic;
import com.deputy.repository.EmployeePrivateRepository;
import com.deputy.service.impl.WorkHistoryService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deputy")
@RequiredArgsConstructor
public class WorkHistoryAipController {
	
	private final EmployeePrivateRepository employeePrivateRepository;
	private final WorkHistoryService workHistoryService;
	
	/**
	 * @description �α��ε� ��ü�� �������� ������ �Է��ϴ� ������
	 * @param principal security���� ������ ��ü (�α��ε� ��ü)
	 * @return service�� �úθ��� �۵��� ������ uri �������� �̵�
	 */
	@PostMapping("/employee/work-controller/process")
	@ApiOperation(value = "������ workHistory ó��", notes = "�α����� �ش� ������ workHistory�� ó���ϴ� ����"
			+ "startDate startAt�� ���� localDateTime�� now()�Ӽ����� �ԷµǾ�����")
	public ResponseEntity<?> start(Principal principal) {
		EmployeePublic employeePublic = employeePrivateRepository.findByUsername(principal.getName())
				.getEmployeePublic();
		System.out.println("principal id = " + employeePublic.getId());
		workHistoryService.workStart(principal);
		
		// redirect
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/deputy/employee/work-controller"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	/**
	 * @description �α��ε� ��ü�� �������� ������ �Է��ϴ� ������
	 * @param historyId ���õ� ��ü�� id�� (workHistory �� ��)
	 * @return service�� �úθ��� �۵��� ������ uri �������� �̵�
	 */
	@GetMapping("/employee/work-controller/{historyId}")
	@ApiOperation(value = "������ workHistory ó��", notes = "�α����� �ش� ������ workHistory�� ó���ϴ� ����"
			+ "finishDate finishAt�� ���� localDateTime�� now()�Ӽ����� �Է� ���������� ������ ��ϵ� ���� �����´�")
	public ResponseEntity<?> finish(@PathVariable Long historyId) {
		//service�� historyId�� �Ѱ� �������� ó�� historyId�� view���� ������� �� "����ϱ�"�� Ŭ���ϸ� �ش� ���� �Ѿ��. 
		workHistoryService.workFinish(historyId);
		
		// redirect
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/deputy/employee/work-controller"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

}
