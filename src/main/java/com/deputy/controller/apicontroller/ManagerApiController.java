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
	 * @description 관리자급 이상의 권한이 선택된 유저의 값을 수정 처리 페이지 (public 정보만 수정가능하다)
	 * @param dto view에서 입력된 값 
	 * @return service가 올바르게 작동시 200 OK를 return
	 */
	@PutMapping("/manager/updateProc")
	@ApiOperation(value = "Manager이상 권한이 유저 정보 수정(public)", notes = "가입된 유저의 public 정보의 update process를 수행하는 동작")
	public HttpStatus update(@RequestBody EmployeePublicDto dto) {
		System.out.println(dto.getDepartment() + dto.getNumber() + dto.getPosition() + dto.isResign() + dto.getId());
		System.out.println("employee public id = " + dto.getId());
		EmployeePublic employeePublic = employeePublicRepository.findById(dto.getId()).get();
		managerService.update(dto, employeePublic);
		return HttpStatus.OK;
	}

	/**
	 * @description 관리자급 이상의 권한이 선택된 유저를 삭제 처리 페이지
	 * @param id 선택된 아이디값
	 * @return service가 올바르게 작동시 200 OK를 return
	 */
	@DeleteMapping("/manager/deleteProc/{id}")
	@ApiOperation(value = "Manager이상 권한이 유저 정보 삭제", notes = "선택된 유저를 delete 수행하는 동작")
	public HttpStatus deleteProc(@PathVariable Long id) {
		managerService.delete(id);
		return HttpStatus.OK;
	}
	
	/**
	 * @description 관리자급 이상의 권한이 선택된 유저의 업무시작 종료 정보를 수정 처리 페이지
	 * @param id 선택된 아이디값
	 * @return service가 올부르게 작동시 지정된 uri 페이지로 이동
	 */
	@PostMapping("/manager/work-update/{id}")
	@ApiOperation(value = "Manager이상 권한이 유저 업무 수정", notes = "선택된 유저 workHistory의 update process 수행하는 동작")
	public ResponseEntity<?> workHistoryUpdate(@PathVariable Long id, @RequestParam String idList, @RequestParam String startDate, @RequestParam @Nullable String finishDate, @RequestParam String startAts, @RequestParam @Nullable String finishAts) {
		workHistoryService.workUpdate(idList, startDate, finishDate, startAts, finishAts);
		
		// redirect
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/deputy/manager/work-update/"+id));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
}
