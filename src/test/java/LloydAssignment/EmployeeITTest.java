package LloydAssignment;

import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lloydAssignment.cache.EmployeeCache;


@SpringBootTest
//@WebMvcTest
public class EmployeeITTest {
//	@Autowired
//	MockMvc mockMvc;
//	@Autowired
//	EmployeeCache employeeCache;
//	
//	@Test
//	public void printTest() throws Exception {
//		when(employeeCache.getEmployeesForGivenSupervisor("Tom")).
//		thenReturn(Collections.EMPTY_LIST);
//		MvcResult mvcResult  = mockMvc.perform(
//				MockMvcRequestBuilders.get("/employee/place/Bangalore")
//				.accept(MediaType.APPLICATION_JSON)
//				).andReturn();
//		//System.out.println(mvcResult.getResponse());
//	}
	
}
