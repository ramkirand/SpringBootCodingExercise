package lloydAssignment.dto;

import org.springframework.beans.factory.annotation.Autowired;

import lloydAssignment.repository.EmployeeRepository;

public class EmployeeDto {
	@Autowired
	EmployeeRepository employeeRepository;
}
