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
	 * @description 로그인된 객체의 업무시작 정보를 입력하는 페이지
	 * @param principal security에서 인증된 객체 (로그인된 객체)
	 * @return service가 올부르게 작동시 지정된 uri 페이지로 이동
	 */
	@PostMapping("/employee/work-controller/process")
	@ApiOperation(value = "유저의 workHistory 처리", notes = "로그인한 해당 유저의 workHistory를 처리하는 동작"
			+ "startDate startAt의 값을 localDateTime의 now()속성으로 입력되어진다")
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
	 * @description 로그인된 객체의 업무종료 정보를 입력하는 페이지
	 * @param historyId 선택된 객체의 id값 (workHistory 모델 값)
	 * @return service가 올부르게 작동시 지정된 uri 페이지로 이동
	 */
	@GetMapping("/employee/work-controller/{historyId}")
	@ApiOperation(value = "유저의 workHistory 처리", notes = "로그인한 해당 유저의 workHistory를 처리하는 동작"
			+ "finishDate finishAt의 값을 localDateTime의 now()속성으로 입력 나머지값은 기존에 기록된 값을 가져온다")
	public ResponseEntity<?> finish(@PathVariable Long historyId) {
		//service로 historyId를 넘겨 업무종료 처리 historyId는 view에서 업무목록 중 "퇴근하기"를 클릭하면 해당 값이 넘어간다. 
		workHistoryService.workFinish(historyId);
		
		// redirect
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/deputy/employee/work-controller"));
		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

}
