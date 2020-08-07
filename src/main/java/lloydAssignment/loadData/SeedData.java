package lloydAssignment.loadData;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lloydAssignment.model.Employee;
import lloydAssignment.model.Supervisor;
import lloydAssignment.repository.EmployeeRepository;
import lloydAssignment.repository.SupervisorRepository;

@Component
public class SeedData {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SupervisorRepository supervisorRepository;
	private List<Employee> employees;

	@PostConstruct
	public void load() {
		List<Supervisor> supervisors = new ArrayList<>();
		employeeRepository.saveAll(getList());
		supervisors.add(new Supervisor(1024,"Pradeep", employees));
		supervisorRepository.saveAll(supervisors);

	}

	public List<Employee> getList() {
		employees = new ArrayList<>();
		employees.add(new Employee("Ajay", "Tech", "STSD", "Bangalore", "Java,python", 123.0));
		employees.add(new Employee("Tom", "NonTech", "NETWORK", "Kolkata", "Pearl", 23.0));
		employees.add(new Employee("Meenu", "Sales", "SEO", "Bangalore", "Java,VB", 203.0));
		employees.add(new Employee("Sundar", "Development", "SEARCH", "USA", "C++, python", 903.0));
		employees.add(new Employee("Shyam", "Marketing", "STSD", "Bangalore", "Ruby, python", 123.0));
		employees.add(new Employee("Pinki", "Tech", "LOS", "Bangalore", "Ruby, python", 13.0));
		return employees;
	}

}
