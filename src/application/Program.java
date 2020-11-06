package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
//		Department obj = new Department(1, "Books");
//		
//		Seller seller = new Seller(12, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
//		
		
		
		Seller seller =  DaoFactory.createSellerDao().findById(2);
		
		System.out.println(seller);

	}

}
