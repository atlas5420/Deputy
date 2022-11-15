package com.deputy.controller;


import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deputy.model.EmployeePrivate;
import com.deputy.model.EmployeePublic;
import com.deputy.repository.EmployeePrivateRepository;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @description controller 작성 class
 */
@Controller
@RequestMapping("/deputy")
@RequiredArgsConstructor
public class MainPageController {
	
	/**
	 * @description 메인페이지
	 * @param principal 로그인 정보를 가져오는 객체
	 * @param model 로그인 정보를 view로 넘기기 위한 객체
	 * @return HTML확장자로 처리된 URL (main.html)
	 */
	private final EmployeePrivateRepository employeePrivateRepository;
	@GetMapping("/main")
	@ApiOperation(value = "어플리케이션의 메인페이지", notes = "메인페이지를 불러온다")
	public String main(Principal principal, Model model) {
		
		//principal이 null 값아니면 (로그인 되어있으면) 로그인 정보를 view로 넘겨준다. 
		if(principal != null) {
			EmployeePrivate employeePrivate = employeePrivateRepository.findByUsername(principal.getName());
			model.addAttribute("employee", employeePrivate);
		}
		return "main";
	}
	
	/**
	 * @description 가입된 정보로 로그인 정보를 입력하기 위한 페이지 - template 하위폴더 employee에서 관리
	 * @return HTML확장자로 처리된 URL (employee/login.html)
	 */
	@GetMapping("/login")
	@ApiOperation(value = "로그인", notes = "로그인 페이지를 불러온다")
	public String login() {
		return "employee/login";
	}
	
	/**
	 * @description 로그아웃 페이지 - template 하위폴더 employee에서 관리
	 * @return HTML확장자로 처리된 URL (employee/login.html)
	 */
	@GetMapping("/successLogout")
	@ApiOperation(value = "로그아웃", notes = "로그아웃을 수행할 페이지")
	public String logout() {
		System.out.println("로그아웃 완료");
		return "employee/login";
	}
	
	/**
	 * @description 로그인된 직원의 정보를 가져오는 페이지 - template 하위폴더 employee에서 관리
	 * @param principal 로그인 정보를 가져오는 객체
	 * @param model 로그인 정보를 view로 넘기기 위한 객체
	 * @return HTML확장자로 처리된 URL (employee/detail.html)
	 */
	
	/**
	 * @description 직원등록을 위한 페이지 - template 하위폴더 employee에서 관리
	 * @return HTML확장자로 처리된 URL (employee/join.html)
	 */
	@GetMapping("/join")
	@ApiOperation(value = "회원가입", notes = "회원가입 페이지를 불러온다")
	public String join() {
		return "employee/join";
	}
	
	@GetMapping("/employee/detail")
	@ApiOperation(value = "유저상세 정보", notes = "로그인한 해당 유저의 상세정보 페이지")
	public String detail(Principal principal, Model model) {
		
		//로그인 정보를 repository에서 찾은 후 view로 넘겨줍니다.
		//model Attribute name = employee는 private 정보를 (직원 개인정보 entity)
		//model Attribute name = employee_pub는 public 정보를 (직원 회사정보 entity) 지정합니다.
		EmployeePrivate employeePrivate = employeePrivateRepository.findByUsername(principal.getName());
		model.addAttribute("employee", employeePrivate);
		EmployeePublic employeePublic = employeePrivate.getEmployeePublic();
		model.addAttribute("employee_pub", employeePublic);
		return "employee/detail";
	}
	
	/**
	 * @description 로그아웃 페이지 - template 하위폴더 employee에서 관리
	 * @param principal 로그인 정보를 가져오는 객체
	 * @param model 로그인 정보를 view로 넘기기 위한 객체
	 * @return HTML확장자로 처리된 URL (employee/update.html)
	 */
	@GetMapping("/employee/update")
	@ApiOperation(value = "유저 private정보 수정", notes = "로그인한 해당 유저의 private정보 수정 페이지")
	public String update(Principal principal, Model model) {
		
		//로그인 정보를 받아온후 view로 넘겨줍니다.
		//Role type이 "EMPLOYEE"인 경우 update시 접근하는 페이지
		//employeePrivate entity 정보를 update할 수 있지만
		//권한에 의해 employeePublic 정보는 update할 수 없습니다.
		EmployeePrivate employeePrivate = employeePrivateRepository.findByUsername(principal.getName());
		model.addAttribute("employee", employeePrivate);
		return "employee/update";
	}

}
