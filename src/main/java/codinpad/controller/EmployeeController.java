package codinpad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import codinpad.model.Employee;
import codinpad.repository.EmployeeRepository;

@Controller
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/directory")
	public String getDirectory(Model model) {
		model.addAttribute("employees", employeeRepository.findAll());
		return "directory";
	}

	// get all employees
	@GetMapping("/employees")
	@ResponseBody
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// create employee rest api
	@PostMapping("/employees")
	@ResponseBody
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee by id rest api
	@GetMapping("/employees/{id}")
	@ResponseBody
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			return ResponseEntity.ok(optionalEmployee.get());
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// update employee rest api
	@PutMapping("/employees/{id}")
	@ResponseBody
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (!optionalEmployee.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Employee employee = optionalEmployee.get();
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());

		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (!optionalEmployee.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Employee employee = optionalEmployee.get();
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
