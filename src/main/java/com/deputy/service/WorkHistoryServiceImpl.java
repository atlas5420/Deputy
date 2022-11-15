package com.deputy.service;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.deputy.dto.WorkHistoryDto;
import com.deputy.model.EmployeePublic;
import com.deputy.model.WorkHistory;
import com.deputy.repository.EmployeePrivateRepository;
import com.deputy.repository.EmployeePublicRepository;
import com.deputy.repository.WorkHistoryRepository;
import com.deputy.service.impl.WorkHistoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkHistoryServiceImpl implements WorkHistoryService {

	private final WorkHistoryRepository workHistoryRepository;
	private final EmployeePublicRepository employeePublicRepository;
	private final EmployeePrivateRepository employeePrivateRepository;

	private final WorkHistoryUpdateProcess workHistoryUpdateProcess;
	
	@Override
	public void list(Long id, Model model) {
		//��ü ���� ���� �ҷ�����
		List<WorkHistory> histories = workHistoryRepository
				.findByEmployeePublic(employeePublicRepository.findById(id).get());
		model.addAttribute("history", histories);
		model.addAttribute("employee", employeePublicRepository.findById(id).get());
	}

	@Override
	public void workStart(Principal principal) {
		//�ð��� Dto�� ������ build
		EmployeePublic employeePublic = employeePrivateRepository.findByUsername(principal.getName())
				.getEmployeePublic();

		//�������� ��ư Ŭ�����Բ� ���۽ð��� build ����ð��� null�� ����
		//����� ���õ� ������ workFinish()���� ó�� 
		WorkHistoryDto historyDto = new WorkHistoryDto();
		historyDto.setStartDate(Date.valueOf(LocalDate.now()));
		historyDto.setStartAt(Time.valueOf(LocalTime.now()));
		historyDto.setFinishDate(null);
		historyDto.setFinishAt(null);
		historyDto.setEmployeePublic(employeePublic);
		
		//build�ϰ� ����
		WorkHistory setStart = WorkHistory.historyBuilder().dto(historyDto).build();
		workHistoryRepository.save(setStart);

	}

	@Override
	public void workFinish(Long historyId) {
		// �ش� workhistory ���� ��������
		WorkHistory workHistory = workHistoryRepository.findById(historyId).get();

		// �������� ���� ���� ����
		WorkHistoryDto historyDto = new WorkHistoryDto();
		historyDto.setId(historyId);
		historyDto.setStartDate(workHistory.getStartDate());
		historyDto.setStartAt(workHistory.getStartAt());
		historyDto.setEmployeePublic(workHistory.getEmployeePublic());
		// ���� ����� �����Ǿ�� �� ����
		historyDto.setFinishDate(Date.valueOf(LocalDate.now()));
		historyDto.setFinishAt(Time.valueOf(LocalTime.now()));
		
		//build�ϰ� ����
		WorkHistory setfinish = WorkHistory.historyBuilder().dto(historyDto).build();
		workHistoryRepository.save(setfinish);
	}

	@Override
	public void workUpdate(String id, String startDates, String finishDates, String startAts, String finishAts) {
		//�Էµ� �������� ������ list�� split ����
		//���������� map�� ���� key��(id)�� value(data)�� �����ϱ� ���� �����۾�
		String[] idList = id.split(",");
		String[] startDateList = startDates.split(",");
		String[] finishDateList = null;
		if(finishDates != null) {
			finishDateList = finishDates.split(",");
			System.out.println("finish�� ������ Ȯ�� : " + finishDateList.toString());
		}
		String[] startAtList = startAts.split(",");
		String[] finishAtList = null;
		if(finishAts != null) {
			finishAtList = finishAts.split(",");
		}
		
		//split ���� �迭�� length �� 0 �� ���� �ƹ��� ���� �Ѿ���� �ʾ������̴�
		//���� �迭�� ���̰� 0�� �ƴѰ�� �Է°��� �ֱ⶧���� �۾��� ���� 0�ΰ�� �������� �ʴ´�
		if(startDateList.length != 0) {
			workHistoryUpdateProcess.startDay(startDateList, idList);
		}
		
		if(finishDateList.length != 0 || finishDateList == null) {
			workHistoryUpdateProcess.finishDay(finishDateList, idList);
		}
		
		if (startAtList.length != 0) {
			workHistoryUpdateProcess.startTime(startAtList, idList);
		}
		
		if (finishAtList.length != 0 || finishAtList == null) {
			workHistoryUpdateProcess.finishTime(finishAtList, idList);
		}
	}

	
}
