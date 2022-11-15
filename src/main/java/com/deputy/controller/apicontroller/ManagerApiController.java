package com.deputy.controller.apicontroller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deputy.dto.EmployeePublicDto;
import com.deputy.model.EmployeePublic;
import com.deputy.repository.EmployeePublicRepository;
import com.deputy.service.impl.ManagerService;
import com.deputy.service.impl.WorkHistoryService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/deputy")
@RequiredArgsConstructor
public class ManagerApiController {

	private final ManagerService managerService;
	private final WorkHistoryService workHistoryService;
	private final EmployeePublicRepository employeePublicRepository;
	
	/**
	 * @description �����ڱ� �̻��� ������ ���õ� ������ ���� ���� ó�� ������ (public ������ ���������ϴ�)
	 * @param dto view���� �Էµ� �� 
	 * @return service�� �ùٸ��� �۵��� 200 OK�� return
	 */
	@PutMapping("/manager/updateProc")
	@ApiOperation(value = "Manager�̻� ������ ���� ���� ����(public)", notes = "���Ե� ������ public ������ update process�� �����ϴ� ����")
	public HttpStatus update(@RequestBody EmployeePublicDto dto) {
		System.out.println(dto.getDepartment() + dto.getNumber() + dto.getPosition() + dto.isResign() + dto.getId());
		System.out.println("employee public id = " + dto.getId());
		EmployeePublic employeePublic = employeePublicRepository.findById(dto.getId()).get();
		managerService.update(dto, employeePublic);
		return HttpStatus.OK;
	}

	/**
	 * @description �����ڱ� �̻��� ������ ���õ� ������ ���� ó�� ������
	 * @param id ���õ� ���̵�
	 * @return service�� �ùٸ��� �۵��� 200 OK�� return
	 */
	@DeleteMapping("/manager/deleteProc/{id}")
	@ApiOperation(value = "Manager�̻� ������ ���� ���� ����", notes = "���õ� ������ delete �����ϴ� ����")
	public HttpStatus deleteProc(@PathVariable Long id) {
		managerService.delete(id);
		return HttpStatus.OK;
	}
	
	/**
	 * @description �����ڱ� �̻��� ������ ���õ� ������ �������� ���� ������ ���� ó�� ������
	 * @param id ���õ� ���̵�
	 * @return service�� �úθ��� �۵��� ������ uri �������� �̵�
	 */
	@PostMapping("/manager/work-update/{id}")
	@ApiOperation(value = "Manager�̻� ������ ���� ���� ����", notes = "���õ� ���� workHistory�� update process �����ϴ� ����")
	public ResponseEntity<?> workHistoryUpdate(@PathVariable Long id, @RequestParam String idList, @RequestParam String startDate, @RequestParam @Nullable String finishDate, @RequestParam String startAts, @RequestParam @Nullable String finishAts) {
		workHistoryService.workUpdate(idList, startDate, finishDate, startAts, finishAts);
		
		// redirect
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/deputy/manager/work-update/"+id));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
}
