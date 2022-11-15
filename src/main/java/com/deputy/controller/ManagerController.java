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
 * @description controller �ۼ� class, ROLE type�� "MANAGER" �̻��� ��� ���ٰ����մϴ�.
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
	 * @description employee ����� �������� ������ - template �������� manager���� ����
	 * @param keyword �˻� ������ �޾ƿ��� �Ű�����
	 * @param model ������ view�� �ѱ�� ���� ��ü
	 * @return HTMLȮ���ڷ� ó���� URL (manager/employeeList.html)
	 */
	@GetMapping("/manager/employeelist")
	@ApiOperation(value = "Manager�̻� ������ ������� ��ȸ", notes = "Manager�̻� ������ ���� �����ڰ� ������� ��ü�� ��ȸ�ϴ� ������")
	public String employeeList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		//�˻� ������ ���°�� ��� ������ �ҷ��ɴϴ�.
		if(keyword == "" || keyword == null) {
		List<EmployeePrivate> memberlist = employeePrivateRepository.findAll();
		model.addAttribute("memberlist", memberlist);
		}
		//���� �˻� ������ �ִ°�� �ش� ������ ȭ�鿡 ǥ���մϴ�.
		//managerService�� search �޼���� keyword���� �Ѱ� ó��
		else if (keyword != null && keyword != "") {
			System.out.println("�˻� ���� data Ȯ��" + keyword);
			List<EmployeePrivate> search = managerService.search(keyword);
			System.out.println("search data Ȯ�� = " + search);
			model.addAttribute("search", search);
		}
		return "manager/employeeList";
	}

	
	/**
	 * @description ������ employee ������ update�ϱ� ���� ������ - template �������� manager���� ����
	 * @param id ������ employee���� �������� ���� �Ű����� (employee�� PK�� id)
	 * @param model ������ view�� �ѱ�� ���� ��ü
	 * @return HTMLȮ���ڷ� ó���� URL (manager/update.html)
	 */
	@GetMapping("/manager/update/{id}")
	@ApiOperation(value = "Manager�̻� ������ �������� ����(public)", notes = "Manager�̻� ������ ���� �����ڰ� ���õ� ������ public������ �����ϴ� ������")
	public String employeedetail(@PathVariable Long id, Model model) {
		//���õ� employee�� PK�� id�� �Ѱ� �ش� ������ �����ɴϴ�.
		EmployeePrivate employeePrivate = employeePrivateRepository.findById(id).get();
		model.addAttribute("delete", employeePrivate);
		EmployeePublic employeePublic = employeePrivateRepository.findById(id).get().getEmployeePublic();
		model.addAttribute("employee", employeePublic);
		model.addAttribute("department", Department.values());
		model.addAttribute("position", Position.values());
		return "manager/update";
	}
	
	/**
	 * @description employee �ٹ��ð� workHistory �������� �������� ����� - template �������� manager���� ����
	 * @param id ������ �ش�� employee�� workHisotry���� �������� ���� �Ű����� (�ش� employee�� PK�� id)
	 * @param model ������ view�� �ѱ�� ���� ��ü
	 * @return HTMLȮ���ڷ� ó���� URL (manager/workUpdate.html)
	 */
	@GetMapping("/manager/work-update/{Id}")
	@ApiOperation(value = "Manager�̻� ������ ���� workhistory ��ȸ", notes = "Manager�̻� ������ ���� �����ڰ� ���õ� ������ workHistory ��ü ������ ��ȸ, �����ϴ� ������")
	public String employeeWorkHistory(@PathVariable Long Id, Model model) {
		//���õ� employee Ž��
		EmployeePrivate employeePrivate = employeePrivateRepository.findById(Id).get();
		model.addAttribute("employee", employeePrivate);
		//�ش� employee�� workHistory���
		List<WorkHistory> history = workHistoryRepository.findByEmployeePublic(employeePrivate.getEmployeePublic());
		System.out.println("�������? "+history);
			model.addAttribute("history", history);
		return "manager/workUpdate";
	}
}
