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
	
	@ApiModelProperty(value = "������", dataType = "Date", required = false, example = "YYYY-MM-dd")
	private Date startDate;
	
	@ApiModelProperty(value = "������", dataType = "Date", required = false, example = "YYYY-MM-dd")
	private Date finishDate;
	
	@ApiModelProperty(value = "���۽ð�", dataType = "Time", required = false, example = "HH:MM")
	private Time startAt;
	
	@ApiModelProperty(value = "����ð�", dataType = "Time", required = false, example = "HH:MM")
	private Time finishAt;
	
	private EmployeePublic employeePublic;
}
