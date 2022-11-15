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
 * @description controller �ۼ� class, ����� ������ view�� ǥ���ϱ� ���� class
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/deputy")
public class WorkHisotryController {

	private final EmployeePrivateRepository employeePrivateRepository;
	private final WorkHistoryRepository workHistoryRepository;
	
	/**
	 * @description 
	 * @param principal �α��� ������ �������� ��ü
	 * @param model workHistory�� ����� ������ view�� �ѱ�� ���� ��ü
	 * @return HTMLȮ���ڷ� ó���� URL (history/workController.html)
	 */
	@GetMapping("/employee/work-controller")
	@ApiOperation(value = "������ workHistory ��� ��ȸ", notes = "�α����� �ش� ������ workHistory ����� �ҷ����� ������")
	public String workController(Principal principal, Model model) {
		//�α��� ������ ����Ͽ� �α��ε� ������ ���������� List�������� �����ɴϴ�.
		//�α��������� employeePrivate entity�� �������ֱ� ������ ��������� workHistory�� �ҷ����� ���� �α��� ������ Ȱ���� �ش� employeePublic�� �ҷ����� mapping�� history������ �����ɴϴ�.
		//������� �� ��ٸ��� ���Ӱ� ������ ������Ʈ�ϱ⶧���� List �������� �����ɴϴ�.
		List<WorkHistory> history = workHistoryRepository.findByEmployeePublic(employeePrivateRepository.findByUsername(principal.getName()).getEmployeePublic());
		model.addAttribute("history", history);
		return "history/workController";
	}

}
