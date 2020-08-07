package lloydAssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lloydAssignment.dto.ResponseDto;
import lloydAssignment.model.Supervisor;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
	@Query("SELECT new lloydAssignment.dto.ResponseDto(s.name,e.name) FROM Supervisor s JOIN  s.employees e")
	List<ResponseDto> getjoinInformation();
	
	
}
