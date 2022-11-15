package com.deputy.service;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.deputy.dto.WorkHistoryDto;
import com.deputy.model.WorkHistory;
import com.deputy.repository.WorkHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkHistoryUpdateProcess {

	private final WorkHistoryRepository workHistoryRepository;

	public void startDay(String[] startDateList, String[] idList) {
		
		Map<Long, String> hm = idAndIndex(startDateList, idList);
		
		// map key ���� workHistory�� id �� value�� ������ startDate ��
		Iterator<Long> keys = hm.keySet().iterator();
		//���������� Ž��
		while (keys.hasNext()) {
			Long key = keys.next();
			WorkHistoryDto workHistoryDto = new WorkHistoryDto();
			//inAndIndex method���� Map�� value�� ����� ":00"���� subString���� �ٽ� ������ set
			String setDate = hm.get(key).substring(0, 10);
			workHistoryDto.setStartDate(Date.valueOf(setDate));
			
			// ������ ����(������� ��������)
			WorkHistory workHistory = workHistoryRepository.findById(key).get();
			workHistoryDto.setId(workHistory.getId());
			workHistoryDto.setStartAt(workHistory.getStartAt());
			workHistoryDto.setFinishDate(workHistory.getFinishDate());
			workHistoryDto.setFinishAt(workHistory.getFinishAt());
			workHistoryDto.setEmployeePublic(workHistory.getEmployeePublic());
			
			new WorkHistory();
			//����� ���� ����
			WorkHistory history = WorkHistory.historyBuilder().dto(workHistoryDto).build();
			workHistoryRepository.save(history);
		}
	}
	
	public void finishDay(String[] finishDateList, String[] idList) {
		
		Map<Long, String> hm = idAndIndex(finishDateList, idList);
		
		// map key ���� workHistory�� id �� value�� ������ finishDate ��
		Iterator<Long> keys = hm.keySet().iterator();
		//���������� Ž��
		while (keys.hasNext()) {
			Long key = keys.next();
			WorkHistoryDto workHistoryDto = new WorkHistoryDto();
			//inAndIndex method���� Map�� value�� ����� ":00"���� subString���� �ٽ� ������ set
			String setDate = hm.get(key).substring(0, 10);
			workHistoryDto.setFinishDate(Date.valueOf(setDate));
			
			// ������ ����(������� ��������)
			WorkHistory workHistory = workHistoryRepository.findById(key).get();
			workHistoryDto.setId(workHistory.getId());
			workHistoryDto.setStartAt(workHistory.getStartAt());
			workHistoryDto.setStartDate(workHistory.getStartDate());
			workHistoryDto.setFinishAt(workHistory.getFinishAt());
			workHistoryDto.setEmployeePublic(workHistory.getEmployeePublic());
			
			//����� ���� ����
			WorkHistory history = WorkHistory.historyBuilder().dto(workHistoryDto).build();
			workHistoryRepository.save(history);
		}
	}
	
	public void startTime(String[] startAtList, String[] idList) {

		Map<Long, String> hm = idAndIndex(startAtList, idList);

		// map key ���� workHistory�� id �� value�� ������ startAt ��
		Iterator<Long> keys = hm.keySet().iterator();
		//���������� Ž��
		while (keys.hasNext()) {
			Long key = keys.next();
			WorkHistoryDto workHistoryDto = new WorkHistoryDto();
			workHistoryDto.setStartAt(Time.valueOf(hm.get(key)));
			
			// ������ ����(������� ��������)
			WorkHistory workHistory = workHistoryRepository.findById(key).get();
			workHistoryDto.setId(workHistory.getId());
			workHistoryDto.setStartDate(workHistory.getStartDate());
			workHistoryDto.setFinishDate(workHistory.getFinishDate());
			workHistoryDto.setFinishAt(workHistory.getFinishAt());
			workHistoryDto.setEmployeePublic(workHistory.getEmployeePublic());
			
			//����� ���� ����
			WorkHistory history = WorkHistory.historyBuilder().dto(workHistoryDto).build();
			workHistoryRepository.save(history);
		}
	}

	public void finishTime(String[] finishAtList, String[] idList) {
		Map<Long, String> hm = idAndIndex(finishAtList, idList);

		// map key ���� workHistory�� id �� value�� ������ finishAt ��
		Iterator<Long> keys = hm.keySet().iterator();
		//���������� Ž��
		while (keys.hasNext()) {
			Long key = keys.next();
			WorkHistoryDto workHistoryDto = new WorkHistoryDto();
			workHistoryDto.setFinishAt(Time.valueOf(hm.get(key)));
			
			// ������ ����(������� ��������)
			WorkHistory workHistory = workHistoryRepository.findById(key).get();
			workHistoryDto.setId(workHistory.getId());
			workHistoryDto.setStartDate(workHistory.getStartDate());
			workHistoryDto.setFinishDate(workHistory.getFinishDate());
			workHistoryDto.setStartAt(workHistory.getStartAt());
			workHistoryDto.setEmployeePublic(workHistory.getEmployeePublic());

			//����� ���� ����
			WorkHistory history = WorkHistory.historyBuilder().dto(workHistoryDto).build();
			workHistoryRepository.save(history);
		}
	}

	//list�� ���� ó���ϱ����� method
	public Map<Long, String> idAndIndex(String[] indexList, String[] idList) {
		Map<Long, String> hm = new HashMap<>();
		//key���� id value�� ������ data�� ����
		for (int i = 0; i < indexList.length; i++) {
			if (indexList[i] != null && !indexList[i].equals("")) {
				//view���� �ʴ����� data�� �������� �ʱ⶧���� value�� ":00"���� �������ش�
				//startDate, finishDate������ ���ʿ� subString���� �ٽ� �������ش�
				//��������� Date���� ������ ��캸�� Time�� ������ ��찡 ������ ���⶧���� ���� method�� �ۼ����� �ʰ� ó����
				hm.put(Long.parseLong(idList[i]), indexList[i] + ":00");
			}
		}
		return hm;
	}
}
