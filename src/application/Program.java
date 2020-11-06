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
	}

}
