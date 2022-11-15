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
 * @description controller �ۼ� class
 */
@Controller
@RequestMapping("/deputy")
@RequiredArgsConstructor
public class MainPageController {
	
	/**
	 * @description ����������
	 * @param principal �α��� ������ �������� ��ü
	 * @param model �α��� ������ view�� �ѱ�� ���� ��ü
	 * @return HTMLȮ���ڷ� ó���� URL (main.html)
	 */
	private final EmployeePrivateRepository employeePrivateRepository;
	@GetMapping("/main")
	@ApiOperation(value = "���ø����̼��� ����������", notes = "������������ �ҷ��´�")
	public String main(Principal principal, Model model) {
		
		//principal�� null ���ƴϸ� (�α��� �Ǿ�������) �α��� ������ view�� �Ѱ��ش�. 
		if(principal != null) {
			EmployeePrivate employeePrivate = employeePrivateRepository.findByUsername(principal.getName());
			model.addAttribute("employee", employeePrivate);
		}
		return "main";
	}
	
	/**
	 * @description ���Ե� ������ �α��� ������ �Է��ϱ� ���� ������ - template �������� employee���� ����
	 * @return HTMLȮ���ڷ� ó���� URL (employee/login.html)
	 */
	@GetMapping("/login")
	@ApiOperation(value = "�α���", notes = "�α��� �������� �ҷ��´�")
	public String login() {
		return "employee/login";
	}
	
	/**
	 * @description �α׾ƿ� ������ - template �������� employee���� ����
	 * @return HTMLȮ���ڷ� ó���� URL (employee/login.html)
	 */
	@GetMapping("/successLogout")
	@ApiOperation(value = "�α׾ƿ�", notes = "�α׾ƿ��� ������ ������")
	public String logout() {
		System.out.println("�α׾ƿ� �Ϸ�");
		return "employee/login";
	}
	
	/**
	 * @description �α��ε� ������ ������ �������� ������ - template �������� employee���� ����
	 * @param principal �α��� ������ �������� ��ü
	 * @param model �α��� ������ view�� �ѱ�� ���� ��ü
	 * @return HTMLȮ���ڷ� ó���� URL (employee/detail.html)
	 */
	
	/**
	 * @description ��������� ���� ������ - template �������� employee���� ����
	 * @return HTMLȮ���ڷ� ó���� URL (employee/join.html)
	 */
	@GetMapping("/join")
	@ApiOperation(value = "ȸ������", notes = "ȸ������ �������� �ҷ��´�")
	public String join() {
		return "employee/join";
	}
	
	@GetMapping("/employee/detail")
	@ApiOperation(value = "������ ����", notes = "�α����� �ش� ������ ������ ������")
	public String detail(Principal principal, Model model) {
		
		//�α��� ������ repository���� ã�� �� view�� �Ѱ��ݴϴ�.
		//model Attribute name = employee�� private ������ (���� �������� entity)
		//model Attribute name = employee_pub�� public ������ (���� ȸ������ entity) �����մϴ�.
		EmployeePrivate employeePrivate = employeePrivateRepository.findByUsername(principal.getName());
		model.addAttribute("employee", employeePrivate);
		EmployeePublic employeePublic = employeePrivate.getEmployeePublic();
		model.addAttribute("employee_pub", employeePublic);
		return "employee/detail";
	}
	
	/**
	 * @description �α׾ƿ� ������ - template �������� employee���� ����
	 * @param principal �α��� ������ �������� ��ü
	 * @param model �α��� ������ view�� �ѱ�� ���� ��ü
	 * @return HTMLȮ���ڷ� ó���� URL (employee/update.html)
	 */
	@GetMapping("/employee/update")
	@ApiOperation(value = "���� private���� ����", notes = "�α����� �ش� ������ private���� ���� ������")
	public String update(Principal principal, Model model) {
		
		//�α��� ������ �޾ƿ��� view�� �Ѱ��ݴϴ�.
		//Role type�� "EMPLOYEE"�� ��� update�� �����ϴ� ������
		//employeePrivate entity ������ update�� �� ������
		//���ѿ� ���� employeePublic ������ update�� �� �����ϴ�.
		EmployeePrivate employeePrivate = employeePrivateRepository.findByUsername(principal.getName());
		model.addAttribute("employee", employeePrivate);
		return "employee/update";
	}

}
