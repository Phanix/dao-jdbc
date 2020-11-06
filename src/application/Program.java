package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		System.out.println("=== TEST 1: seller findById ===");
		
		Seller seller =  DaoFactory.createSellerDao().findById(2);
		
		System.out.println(seller);
		
		System.out.println("=== TEST 2: seller findAllByDepartment ===");
		
		List<Seller> sellers = DaoFactory.createSellerDao().findByDepartment(new Department(2, "Books"));
		
		for(Seller seller1 : sellers){
			System.out.println(seller1);
		}
		
		System.out.println("=== TEST 3: seller findAll ===");
		
		List<Seller> sellers2 = DaoFactory.createSellerDao().findAll();
		
		for(Seller seller1 : sellers2){
			System.out.println(seller1);
		}
		
		System.out.println("=== TEST 4: insert seller ===");
		
		Seller seller4 = new Seller();
		seller4.setName("Brian");
		seller4.setEmail("brian@gmail.com");
		seller4.setBaseSalary(5000.0);
		seller4.setDepartment(new Department(1, "Books"));
		seller4.setBirthDate(new Date());
		
		DaoFactory.createSellerDao().insert(seller4);
		
		List<Seller> sellers3 = DaoFactory.createSellerDao().findAll();
		
		for(Seller seller1 : sellers3){
			System.out.println(seller1);
		}
	}

}
