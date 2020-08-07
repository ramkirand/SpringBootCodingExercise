package lloydAssignment.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Supervisor {
	@Id
	private Integer id;
	private String name;
	@OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "supervisorId", referencedColumnName = "id")
	private List<Employee> employees;

	public Supervisor(String name, List<Employee> employees) {
		this.name = name;
		this.employees = employees;
	}

}
