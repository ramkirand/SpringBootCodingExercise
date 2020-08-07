package lloydAssignment.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lloydAssignment.model.Employee;
import lloydAssignment.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	String line = "";

	public void saveEmployeeData() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src//main//resources//employee.csv"));
			while ((line = br.readLine()) != null) {
				String data[] = line.split(",");
				Employee e = new Employee();
				e.setName(data[1]);
				e.setTitle(data[2]);
				e.setBusinessUnit(data[3]);
				e.setPlace(data[4]);
				String val = data[5];
				e.setCompetencies(val);
				e.setSalary(Double.parseDouble(data[6]));
				employeeRepository.save(e);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}
	}
}
