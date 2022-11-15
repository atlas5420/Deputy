package com.deputy.dto;

import java.util.List;

import javax.persistence.Column;

import com.deputy.model.Role;
import com.deputy.model.WorkHistory;
import com.deputy.model.employeeEnum.Department;
import com.deputy.model.employeeEnum.Position;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePublicDto {
	
	private Long id;
	
	@ApiModelProperty(value = "사원번호", dataType = "String", required = false, example = "202217")
	private String number;
	
	@ApiModelProperty(value = "아이디", dataType = "department", required = false, example = "개발")
	private Department department;
	
	@ApiModelProperty(value = "아이디", dataType = "Position", required = false, example = "사원")
	private Position position;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	@ApiModelProperty(value = "퇴사여부", dataType = "boolean", required = false, example = "NO")
	private boolean resign;
	
	private List<WorkHistory> histories;
	
	private Role role;
	
}
