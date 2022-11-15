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
		
		// map key 값은 workHistory의 id 값 value는 수정될 startDate 값
		Iterator<Long> keys = hm.keySet().iterator();
		//순차적으로 탐색
		while (keys.hasNext()) {
			Long key = keys.next();
			WorkHistoryDto workHistoryDto = new WorkHistoryDto();
			//inAndIndex method에서 Map의 value에 저장된 ":00"값을 subString으로 다시 제거후 set
			String setDate = hm.get(key).substring(0, 10);
			workHistoryDto.setStartDate(Date.valueOf(setDate));
			
			// 기존값 세팅(변경되지 않은값들)
			WorkHistory workHistory = workHistoryRepository.findById(key).get();
			workHistoryDto.setId(workHistory.getId());
			workHistoryDto.setStartAt(workHistory.getStartAt());
			workHistoryDto.setFinishDate(workHistory.getFinishDate());
			workHistoryDto.setFinishAt(workHistory.getFinishAt());
			workHistoryDto.setEmployeePublic(workHistory.getEmployeePublic());
			
			new WorkHistory();
			//변경된 값을 저장
			WorkHistory history = WorkHistory.historyBuilder().dto(workHistoryDto).build();
			workHistoryRepository.save(history);
		}
	}
	
	public void finishDay(String[] finishDateList, String[] idList) {
		
		Map<Long, String> hm = idAndIndex(finishDateList, idList);
		
		// map key 값은 workHistory의 id 값 value는 수정될 finishDate 값
		Iterator<Long> keys = hm.keySet().iterator();
		//순차적으로 탐색
		while (keys.hasNext()) {
			Long key = keys.next();
			WorkHistoryDto workHistoryDto = new WorkHistoryDto();
			//inAndIndex method에서 Map의 value에 저장된 ":00"값을 subString으로 다시 제거후 set
			String setDate = hm.get(key).substring(0, 10);
			workHistoryDto.setFinishDate(Date.valueOf(setDate));
			
			// 기존값 세팅(변경되지 않은값들)
			WorkHistory workHistory = workHistoryRepository.findById(key).get();
			workHistoryDto.setId(workHistory.getId());
			workHistoryDto.setStartAt(workHistory.getStartAt());
			workHistoryDto.setStartDate(workHistory.getStartDate());
			workHistoryDto.setFinishAt(workHistory.getFinishAt());
			workHistoryDto.setEmployeePublic(workHistory.getEmployeePublic());
			
			//변경된 값을 저장
			WorkHistory history = WorkHistory.historyBuilder().dto(workHistoryDto).build();
			workHistoryRepository.save(history);
		}
	}
	
	public void startTime(String[] startAtList, String[] idList) {

		Map<Long, String> hm = idAndIndex(startAtList, idList);

		// map key 값은 workHistory의 id 값 value는 수정될 startAt 값
		Iterator<Long> keys = hm.keySet().iterator();
		//순차적으로 탐색
		while (keys.hasNext()) {
			Long key = keys.next();
			WorkHistoryDto workHistoryDto = new WorkHistoryDto();
			workHistoryDto.setStartAt(Time.valueOf(hm.get(key)));
			
			// 기존값 세팅(변경되지 않은값들)
			WorkHistory workHistory = workHistoryRepository.findById(key).get();
			workHistoryDto.setId(workHistory.getId());
			workHistoryDto.setStartDate(workHistory.getStartDate());
			workHistoryDto.setFinishDate(workHistory.getFinishDate());
			workHistoryDto.setFinishAt(workHistory.getFinishAt());
			workHistoryDto.setEmployeePublic(workHistory.getEmployeePublic());
			
			//변경된 값을 저장
			WorkHistory history = WorkHistory.historyBuilder().dto(workHistoryDto).build();
			workHistoryRepository.save(history);
		}
	}

	public void finishTime(String[] finishAtList, String[] idList) {
		Map<Long, String> hm = idAndIndex(finishAtList, idList);

		// map key 값은 workHistory의 id 값 value는 수정될 finishAt 값
		Iterator<Long> keys = hm.keySet().iterator();
		//순차적으로 탐색
		while (keys.hasNext()) {
			Long key = keys.next();
			WorkHistoryDto workHistoryDto = new WorkHistoryDto();
			workHistoryDto.setFinishAt(Time.valueOf(hm.get(key)));
			
			// 기존값 세팅(변경되지 않은값들)
			WorkHistory workHistory = workHistoryRepository.findById(key).get();
			workHistoryDto.setId(workHistory.getId());
			workHistoryDto.setStartDate(workHistory.getStartDate());
			workHistoryDto.setFinishDate(workHistory.getFinishDate());
			workHistoryDto.setStartAt(workHistory.getStartAt());
			workHistoryDto.setEmployeePublic(workHistory.getEmployeePublic());

			//변경된 값을 저장
			WorkHistory history = WorkHistory.historyBuilder().dto(workHistoryDto).build();
			workHistoryRepository.save(history);
		}
	}

	//list의 값을 처리하기위한 method
	public Map<Long, String> idAndIndex(String[] indexList, String[] idList) {
		Map<Long, String> hm = new HashMap<>();
		//key값에 id value에 수정된 data를 설정
		for (int i = 0; i < indexList.length; i++) {
			if (indexList[i] != null && !indexList[i].equals("")) {
				//view에서 초단위를 data로 설정하지 않기때문에 value에 ":00"값을 포함해준다
				//startDate, finishDate에서는 불필요 subString으로 다시 제거해준다
				//상대적으로 Date값을 수정할 경우보다 Time을 수정할 경우가 월등히 높기때문에 따로 method를 작성하지 않고 처리함
				hm.put(Long.parseLong(idList[i]), indexList[i] + ":00");
			}
		}
		return hm;
	}
}
