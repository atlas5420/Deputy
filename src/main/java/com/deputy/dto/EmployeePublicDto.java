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
	
	@ApiModelProperty(value = "�����ȣ", dataType = "String", required = false, example = "202217")
	private String number;
	
	@ApiModelProperty(value = "���̵�", dataType = "department", required = false, example = "����")
	private Department department;
	
	@ApiModelProperty(value = "���̵�", dataType = "Position", required = false, example = "���")
	private Position position;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	@ApiModelProperty(value = "��翩��", dataType = "boolean", required = false, example = "NO")
	private boolean resign;
	
	private List<WorkHistory> histories;
	
	private Role role;
	
}
