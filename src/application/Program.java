package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
//		Department obj = new Department(1, "Books");
//		
//		Seller seller = new Seller(12, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
//		
		
		System.out.println("=== TEST 1: seller findById ===");
		
		Seller seller =  DaoFactory.createSellerDao().findById(2);
		
		System.out.println(seller);
		
		System.out.println("=== TEST 2: seller findAllByDepartment ===");
		
		List<Seller> sellers = DaoFactory.createSellerDao().findByDepartment(new Department(2, "Books"));
		
		for(Seller seller1 : sellers){
			System.out.println(seller1);
		}
	}

}
