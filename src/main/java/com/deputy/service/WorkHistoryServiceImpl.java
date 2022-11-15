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
		//전체 유저 정보 불러오기
		List<WorkHistory> histories = workHistoryRepository
				.findByEmployeePublic(employeePublicRepository.findById(id).get());
		model.addAttribute("history", histories);
		model.addAttribute("employee", employeePublicRepository.findById(id).get());
	}

	@Override
	public void workStart(Principal principal) {
		//시간을 Dto에 설정후 build
		EmployeePublic employeePublic = employeePrivateRepository.findByUsername(principal.getName())
				.getEmployeePublic();

		//업무시작 버튼 클릭과함께 시작시간만 build 종료시간은 null로 저장
		//종료와 관련된 내용은 workFinish()에서 처리 
		WorkHistoryDto historyDto = new WorkHistoryDto();
		historyDto.setStartDate(Date.valueOf(LocalDate.now()));
		historyDto.setStartAt(Time.valueOf(LocalTime.now()));
		historyDto.setFinishDate(null);
		historyDto.setFinishAt(null);
		historyDto.setEmployeePublic(employeePublic);
		
		//build하고 저장
		WorkHistory setStart = WorkHistory.historyBuilder().dto(historyDto).build();
		workHistoryRepository.save(setStart);

	}

	@Override
	public void workFinish(Long historyId) {
		// 해당 workhistory 정보 가져오기
		WorkHistory workHistory = workHistoryRepository.findById(historyId).get();

		// 수정되지 않을 기존 정보
		WorkHistoryDto historyDto = new WorkHistoryDto();
		historyDto.setId(historyId);
		historyDto.setStartDate(workHistory.getStartDate());
		historyDto.setStartAt(workHistory.getStartAt());
		historyDto.setEmployeePublic(workHistory.getEmployeePublic());
		// 업무 종료로 수정되어야 할 정보
		historyDto.setFinishDate(Date.valueOf(LocalDate.now()));
		historyDto.setFinishAt(Time.valueOf(LocalTime.now()));
		
		//build하고 저장
		WorkHistory setfinish = WorkHistory.historyBuilder().dto(historyDto).build();
		workHistoryRepository.save(setfinish);
	}

	@Override
	public void workUpdate(String id, String startDates, String finishDates, String startAts, String finishAts) {
		//입력된 정보들을 각각의 list에 split 저장
		//순차적으로 map을 통해 key값(id)와 value(data)를 설정하기 위한 기초작업
		String[] idList = id.split(",");
		String[] startDateList = startDates.split(",");
		String[] finishDateList = null;
		if(finishDates != null) {
			finishDateList = finishDates.split(",");
			System.out.println("finish값 있을때 확인 : " + finishDateList.toString());
		}
		String[] startAtList = startAts.split(",");
		String[] finishAtList = null;
		if(finishAts != null) {
			finishAtList = finishAts.split(",");
		}
		
		//split 이후 배열의 length 가 0 인 경우는 아무런 값이 넘어오지 않았을때이다
		//따라서 배열의 길이가 0이 아닌경우 입력값이 있기때문에 작업을 수행 0인경우 수행하지 않는다
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
