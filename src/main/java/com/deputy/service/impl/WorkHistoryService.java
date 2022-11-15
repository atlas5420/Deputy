package com.deputy.service.impl;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.deputy.model.EmployeePublic;

/**
 * �ӿ� ������� �����ϴ� �������̽��� �ۼ�
 */
public interface WorkHistoryService {
	
	public void list(Long id, Model model);
	
	public void workStart(Principal principal);
	
	public void workFinish(Long historyId);
	
	public void workUpdate(String id, String startDate, String finishDate, String startAts, String finishAs);
}
