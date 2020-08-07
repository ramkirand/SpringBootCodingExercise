package lloydAssignment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Employee {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String title;
	private String businessUnit;
	private String place;
	private String competencies;
	private Double salary;

	public Employee(String name, String title, String businessUnit, String place, String competencies, Double salary) {
		this.name = name;
		this.title = title;
		this.businessUnit = businessUnit;
		this.place = place;
		this.competencies = competencies;
		this.salary = salary;
	}
}
