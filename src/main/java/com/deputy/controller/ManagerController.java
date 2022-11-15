package com.deputy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deputy.model.EmployeePrivate;
import com.deputy.model.EmployeePublic;
import com.deputy.model.WorkHistory;
import com.deputy.model.employeeEnum.Department;
import com.deputy.model.employeeEnum.Position;
import com.deputy.repository.EmployeePrivateRepository;
import com.deputy.repository.WorkHistoryRepository;
import com.deputy.service.impl.ManagerService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @description controller 작성 class, ROLE type이 "MANAGER" 이상일 경우 접근가능합니다.
 * 
 *
 */
@Controller
@RequestMapping("/deputy")
@RequiredArgsConstructor
public class ManagerController {

	private final EmployeePrivateRepository employeePrivateRepository;
	private final ManagerService managerService;
	private final WorkHistoryRepository workHistoryRepository;
	
	/**
	 * @description employee 목록을 가져오는 페이지 - template 하위폴더 manager에서 관리
	 * @param keyword 검색 정보를 받아오는 매개변수
	 * @param model 정보를 view로 넘기기 위한 객체
	 * @return HTML확장자로 처리된 URL (manager/employeeList.html)
	 */
	@GetMapping("/manager/employeelist")
	@ApiOperation(value = "Manager이상 권한이 유저목록 조회", notes = "Manager이상 권한을 가진 관리자가 유저목록 전체를 조회하는 페이지")
	public String employeeList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		//검색 정보가 없는경우 모든 정보를 불러옵니다.
		if(keyword == "" || keyword == null) {
		List<EmployeePrivate> memberlist = employeePrivateRepository.findAll();
		model.addAttribute("memberlist", memberlist);
		}
		//만약 검색 정보가 있는경우 해당 정보만 화면에 표시합니다.
		//managerService의 search 메서드로 keyword값을 넘겨 처리
		else if (keyword != null && keyword != "") {
			System.out.println("검색 정보 data 확인" + keyword);
			List<EmployeePrivate> search = managerService.search(keyword);
			System.out.println("search data 확인 = " + search);
			model.addAttribute("search", search);
		}
		return "manager/employeeList";
	}

	
	/**
	 * @description 지정된 employee 정보를 update하기 위한 페이지 - template 하위폴더 manager에서 관리
	 * @param id 지정된 employee값을 가져오기 위한 매개변수 (employee의 PK값 id)
	 * @param model 정보를 view로 넘기기 위한 객체
	 * @return HTML확장자로 처리된 URL (manager/update.html)
	 */
	@GetMapping("/manager/update/{id}")
	@ApiOperation(value = "Manager이상 권한이 유저정보 수정(public)", notes = "Manager이상 권한을 가진 관리자가 선택된 유저의 public정보를 수정하는 페이지")
	public String employeedetail(@PathVariable Long id, Model model) {
		//선택된 employee의 PK값 id를 넘겨 해당 정보만 가져옵니다.
		EmployeePrivate employeePrivate = employeePrivateRepository.findById(id).get();
		model.addAttribute("delete", employeePrivate);
		EmployeePublic employeePublic = employeePrivateRepository.findById(id).get().getEmployeePublic();
		model.addAttribute("employee", employeePublic);
		model.addAttribute("department", Department.values());
		model.addAttribute("position", Position.values());
		return "manager/update";
	}
	
	/**
	 * @description employee 근무시간 workHistory 상세정보를 가져오는 페애지 - template 하위폴더 manager에서 관리
	 * @param id 지정된 해당된 employee의 workHisotry값을 가져오기 위한 매개변수 (해당 employee의 PK값 id)
	 * @param model 정보를 view로 넘기기 위한 객체
	 * @return HTML확장자로 처리된 URL (manager/workUpdate.html)
	 */
	@GetMapping("/manager/work-update/{Id}")
	@ApiOperation(value = "Manager이상 권한이 유저 workhistory 조회", notes = "Manager이상 권한을 가진 관리자가 선택된 유저의 workHistory 전체 정보를 조회, 수정하는 페이지")
	public String employeeWorkHistory(@PathVariable Long Id, Model model) {
		//선택된 employee 탐색
		EmployeePrivate employeePrivate = employeePrivateRepository.findById(Id).get();
		model.addAttribute("employee", employeePrivate);
		//해당 employee의 workHistory목록
		List<WorkHistory> history = workHistoryRepository.findByEmployeePublic(employeePrivate.getEmployeePublic());
		System.out.println("결과값은? "+history);
			model.addAttribute("history", history);
		return "manager/workUpdate";
	}
}
