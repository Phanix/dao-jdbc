package application;

import model.dao.DaoFactory;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=== TEST 1 insert Department ===");
		DaoFactory.createDepartmentDao().insert(new Department(5, "Cars"));
	}

}
