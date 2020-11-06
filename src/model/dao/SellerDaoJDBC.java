package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Department;
import model.entities.Seller;
import db.DB;
import db.DbException;

public class SellerDaoJDBC implements SellerDao{

	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.departmentId = department.Id "
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);
				
				return seller;					
			}
	
		}catch(Exception e){
			throw new DbException(e.getMessage());
			
		}finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
			
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		
		Seller seller =  new Seller();	
		
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(department);
		
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		
		Department department =  new Department();
		
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
	
		return department;		
	}

	@Override
	public List<Seller> findAll() {
		return null;
		
	}
//	st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
//			+ "FROM seller INNER JOIN department "
//			+ "ON seller.departmentId = department.Id "
//			+ "WHERE seller.Id = ?");

	@Override
	public List<Seller> findByDepartment(Department dep) {
		ResultSet rs = null;
		PreparedStatement st = null;
		try{
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE Seller.departmentId = ? "
					+ "ORDER BY Name"
					+ "");
			st.setInt(1, dep.getId());
			rs = st.executeQuery();
			List<Seller> sellers = new ArrayList<Seller>();
			Department department = null;
			while(rs.next()){
				if(department == null){
					department = instantiateDepartment(rs);
				}
				Seller seller = instantiateSeller(rs, department);
				sellers.add(seller);
			}
			return sellers;
		}catch(Exception e){
			throw new DbException(e.getMessage());
		}
		
	}

}
