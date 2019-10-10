package serviceLayer;

import java.util.ArrayList;

import models.Department;
import models.Employee;
import unitofwork.unitofworkEmployee;

public class EmployeeService {

	public static void addEmployee(String username, String password, String firstName, String lastName, String department_name, int phoneNumber, String birthday, String email) {
		Department department = Department.getDepartmentByName(department_name);
		Employee employee = new Employee(username, password, firstName, lastName, department, phoneNumber, birthday, email);
	}

	public static ArrayList<Employee> searchEmployee(String str) {
		// Search Employee by Id or Username;
		ArrayList<Employee> employees = new ArrayList<>();
		Employee employee = Employee.getEmployeeById(str);
		if(employee!=null) {
			employees.add(employee);
		}
		Employee employee2 = Employee.getEmployeeByUsername(str);
		if(employee2!=null) {
			employees.add(employee2);
		}
		return employees;
	}
	
	public static ArrayList<Employee> getAllEmployee() {
		return Employee.getAllEmployeeList();
	}

	public static Employee getEmployeeById(String id) {
		return Employee.getEmployeeById(id);
	}

	public static void editEmployee(Employee employee, String firstName, String lastName, String department_name,
			int phoneNumber, String birthday, String email) {
		// TODO Auto-generated method stub
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		Department department = Department.getDepartmentByName(department_name);
		if(department!=null) {
			employee.setDepartment(department);
		}
		employee.setPhoneNumber(phoneNumber);
		employee.setBirthday(birthday);
		employee.setEmail(email);
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().commit();
	}

	public static void deleteEmployee(String id) {
		// TODO Auto-generated method stub
		Employee employee = Employee.getEmployeeById(id);
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerDeleted(employee);
		unitofworkEmployee.getCurrent().commit();
	}
	
}
