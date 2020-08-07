package lloydAssignment.resource;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiOperation;
import lloydAssignment.cache.EmployeeCache;
import lloydAssignment.model.Employee;
import lloydAssignment.model.Supervisor;
import lloydAssignment.repository.SupervisorRepository;
import lloydAssignment.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeResource {

	@Autowired
	private EmployeeCache employeeCache;
	@Autowired
	private SupervisorRepository supervisorRepository;
	@Autowired
	private EmployeeService employeeService;

	@ApiOperation(value = "This endpoint will return the list of employees for a given place from the cache")
	@GetMapping(value = "/place/{place}")
	public List<Employee> fetchByPlace(@PathVariable final String place) {
		return employeeCache.fetchByPlace(place);
	}

	@ApiOperation(value = "Endpoint will update each record by increasing their salary by percentage(percentage path param) for employees that have place matching with place path param")
	@PutMapping(value = "/place/{place}/salary/{percentage}")
	public void update(@PathVariable final String place, @PathVariable final int percentage) {
		employeeCache.updateDetails(place, percentage);
	}

	@ApiOperation(value = "This endpoint will return the total salary of" + "a. given BU\r\n"
			+ "b. given supervisor\r\n" + "c. given place" + "")
	@GetMapping(value = "/bu/{bu}/supervisorId/{supervisorId}/place/{place}")
	public int getTotalSalary(@PathVariable final String bu, @PathVariable final Integer supervisorId,
			@PathVariable final String place) {
		
		
		return employeeCache.getTotalSalary(bu, supervisorId, place);
	}

	@ApiOperation(value = "The endpoint will return the nested list of all supervisees of a given supervisor")
	@GetMapping(value = "/{name}")
	public List<String> getEmployeesForGivenSupervisor(@PathVariable final String name) {
		return employeeCache.getEmployeesForGivenSupervisor(name);
	}

	@GetMapping("/findAllsupervisors")
	public List<Supervisor> findAllSupervisor() {
		return supervisorRepository.findAll();
	}

	@ApiOperation(value = "This endpoint to return the range of salaries for a given title")
	@GetMapping("/title/{title}")
	public String findSalaryByTitle(final String title) throws JsonProcessingException {
		return employeeCache.fetchSalariesBytitle(title);
	}

	@PostMapping("/load")
	public void loadEmployeeData() throws IOException {
		employeeService.saveEmployeeData();
	}

}
