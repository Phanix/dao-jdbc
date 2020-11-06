package application;

import java.util.List;

import model.dao.DaoFactory;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("=== TEST 1 insert Department ===");
//		DaoFactory.createDepartmentDao().insert(new Department(5, "Cars"));
		
		System.out.println("=== TEST 2 findAll Department ===");
		List<Department> deps =  DaoFactory.createDepartmentDao().findAll();
		
		for(Department dep : deps){
			System.out.println(dep);
		}
		
		System.out.println("=== TEST 2 deleteById Department ===");
		DaoFactory.createDepartmentDao().deleteById(5);
		
		List<Department> deps2 =  DaoFactory.createDepartmentDao().findAll();
		
		for(Department dep : deps2){
			System.out.println(dep);
		}
	}

}
