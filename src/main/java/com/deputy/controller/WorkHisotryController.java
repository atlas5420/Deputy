package com.deputy.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deputy.model.WorkHistory;
import com.deputy.repository.EmployeePrivateRepository;
import com.deputy.repository.WorkHistoryRepository;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @description controller 작성 class, 출퇴근 정보를 view에 표시하기 위한 class
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/deputy")
public class WorkHisotryController {

	private final EmployeePrivateRepository employeePrivateRepository;
	private final WorkHistoryRepository workHistoryRepository;
	
	/**
	 * @description 
	 * @param principal 로그인 정보를 가져오는 객체
	 * @param model workHistory에 저장된 정보를 view로 넘기기 위한 객체
	 * @return HTML확장자로 처리된 URL (history/workController.html)
	 */
	@GetMapping("/employee/work-controller")
	@ApiOperation(value = "유저의 workHistory 목록 조회", notes = "로그인한 해당 유저의 workHistory 목록을 불러오는 페이지")
	public String workController(Principal principal, Model model) {
		//로그인 정보를 사용하여 로그인된 유저의 업무정보를 List형식으로 가져옵니다.
		//로그인정보는 employeePrivate entity를 가지고있기 때문에 출퇴근정보 workHistory를 불러오기 위해 로그인 정보를 활용해 해당 employeePublic을 불러오고 mapping된 history정보를 가져옵니다.
		//출퇴근은 매 출근마다 새롭게 정보를 업데이트하기때문에 List 형식으로 가져옵니다.
		List<WorkHistory> history = workHistoryRepository.findByEmployeePublic(employeePrivateRepository.findByUsername(principal.getName()).getEmployeePublic());
		model.addAttribute("history", history);
		return "history/workController";
	}

}
