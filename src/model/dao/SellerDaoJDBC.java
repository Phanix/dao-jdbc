package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
		PreparedStatement st = null;
		
		try{
			st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES (?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			
			java.sql.Date date = new java.sql.Date(obj.getBirthDate().getTime());
			st.setDate(3, date);
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsEffected = st.executeUpdate();
			
			if(rowsEffected > 0){
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()){
					int id = rs.getInt(1);
					obj.setId(id);
				}
			}else{
				throw new DbException("Unexpected error! No rows effected.");
			}
			
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller obj) {
		
		PreparedStatement st = null;
		try{
			st = conn.prepareStatement("UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ?"
					);
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			
			java.sql.Date date = new java.sql.Date(obj.getBirthDate().getTime());
			st.setDate(3, date);
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();
		
					
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		
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
					+ "WHERE seller.Id = ?"
					
					);
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
		ResultSet rs = null;
		Statement st = null;
		try{
			st = conn.createStatement();
			
			rs = st.executeQuery("SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name"
					+ "");
			List<Seller> sellers = new ArrayList<Seller>();
			
			while(rs.next()){
				
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);
				sellers.add(seller);
			}
			return sellers;
		}catch(Exception e){
			throw new DbException(e.getMessage());
		}finally{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
		
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
