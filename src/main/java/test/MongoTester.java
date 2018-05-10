package test;

import java.util.List;

import data.SimpleDao;
import models.Employee;

public class MongoTester {

	public static void main(String[] args) {

		SimpleDao<Employee> dao = new SimpleDao<>(Employee.class);
		// Employee emp1 = new Employee("123", "Daniel Sh", 21);
		// Employee emp2 = new Employee("234", "Eduard M", 19);
		// Employee emp3 = new Employee("567", "Employee 3", 46);
		//
		// dao.insert(emp1);
		// dao.insert(Arrays.asList(emp2, emp3));

		Employee emp4 = dao.getFirst();
		List<Employee> emps = dao.getAll();
		emps.add(emp4);

		System.out.println(emps);
	}

}
