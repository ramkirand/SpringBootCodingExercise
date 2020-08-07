package lloydAssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lloydAssignment.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByName(String name);

	List<Employee> findByPlace(String place);

	List<Double> findByTitle(String title);

}
