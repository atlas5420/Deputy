package com.deputy.dto;

import java.sql.Date;
import java.sql.Time;

import com.deputy.model.EmployeePublic;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkHistoryDto {
	
	private Long id;
	
	@ApiModelProperty(value = "시작일", dataType = "Date", required = false, example = "YYYY-MM-dd")
	private Date startDate;
	
	@ApiModelProperty(value = "종료일", dataType = "Date", required = false, example = "YYYY-MM-dd")
	private Date finishDate;
	
	@ApiModelProperty(value = "시작시간", dataType = "Time", required = false, example = "HH:MM")
	private Time startAt;
	
	@ApiModelProperty(value = "종료시간", dataType = "Time", required = false, example = "HH:MM")
	private Time finishAt;
	
	private EmployeePublic employeePublic;
}
