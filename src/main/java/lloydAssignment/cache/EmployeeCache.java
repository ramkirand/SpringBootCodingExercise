package lloydAssignment.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lloydAssignment.dto.ResponseDto;
import lloydAssignment.model.Employee;
import lloydAssignment.model.Supervisor;
import lloydAssignment.repository.EmployeeRepository;
import lloydAssignment.repository.SupervisorRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EmployeeCache {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SupervisorRepository supervisorRepository;
	@Autowired
	private EntityManager em;
	private List<Employee> employees;

	@Cacheable(value = "employeeCache", key = "#name")
	public Employee getUser(String name) {
		log.info("Retrieving from Database for name: " + name);
		return employeeRepository.findByName(name);
	}

	@Cacheable(value = "employeeCache", key = "#name")
	public List<Employee> fetchByPlace(String place) {
		employees = employeeRepository.findByPlace(place);
		return employees;
	}

	@Cacheable(value = "employeeCache", key = "#name")
	public void updateDetails(String place, int percentage) {
		for (Employee e : employees) {
			double temp = e.getSalary() * percentage * 0.01;
			e.setSalary(e.getSalary() + temp);
		}
		employeeRepository.saveAll(employees);
	}

	public int getTotalSalary(String bu, Integer supervisorId, String place) {
		Optional<Supervisor> sup = supervisorRepository.findById(supervisorId);
		int totalSalary = 0;
		TypedQuery<Employee> query = filterSalary(bu, supervisorId, place);
		List<Employee> empList = query.getResultList();
		if (!empList.isEmpty() && sup.isPresent()) {
			for (Employee emp : empList)
				totalSalary += emp.getSalary();
		}

		return totalSalary;
	}

	private TypedQuery<Employee> filterSalary(String bu, Integer supervisorId, String place) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> employee = cq.from(Employee.class);
		Predicate businessUnitPredicate = cb.equal(employee.get("businessUnit"), bu);
		Predicate placePredicate = cb.equal(employee.get("place"), place);
		cq.where(businessUnitPredicate, placePredicate);
		TypedQuery<Employee> query = em.createQuery(cq);
		return query;
	}

	public String fetchSalariesBytitle(String title) throws JsonProcessingException {
		StringBuilder resp = new StringBuilder();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> employee = cq.from(Employee.class);
		Predicate salaryPredicate = cb.equal(employee.get("title"), title);
		cq.where(salaryPredicate);
		TypedQuery<Employee> query = em.createQuery(cq);
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.enable(SerializationFeature.INDENT_OUTPUT);
		for (Employee e : query.getResultList()) {
			resp.append(e.getSalary()).append(" ");
		}
		return objMapper.writeValueAsString(resp.toString());
	}

	public List<String> getEmployeesForGivenSupervisor(String name) {
		List<String> emps = new ArrayList<String>();
		Map<String, List<String>> supervisorEmpMap = new HashMap<>();

		List<ResponseDto> infos = getJoinInformation();

		for (ResponseDto info : infos) {
			emps.add(info.getEmpName());
		}
		supervisorEmpMap.put(infos.get(0).getName(), emps);
		return supervisorEmpMap.get(name);
	}

	public List<ResponseDto> getJoinInformation() {
		return supervisorRepository.getjoinInformation();
	}
}
