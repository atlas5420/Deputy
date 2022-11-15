package com.deputy.model;


import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

import com.deputy.dto.WorkHistoryDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "work_history")
public class WorkHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date startDate;
	
	@Nullable
	private Date finishDate;
	
	private Time startAt;
	
	@Nullable
	private Time finishAt;
	
	@ManyToOne
	@JoinColumn(name = "employee_public")
	private EmployeePublic employeePublic;

	@Builder(builderClassName = "historyBuilder", builderMethodName = "historyBuilder")
	public WorkHistory(WorkHistoryDto dto) {
		this.id = dto.getId();
		this.startDate = dto.getStartDate();
		this.startAt  =dto.getStartAt();
		this.finishDate = dto.getFinishDate();
		this.finishAt = dto.getFinishAt();
		this.employeePublic = dto.getEmployeePublic();
	}
}
