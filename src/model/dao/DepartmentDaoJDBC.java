package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import db.DbException;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	Connection conn;
	
	public DepartmentDaoJDBC(Connection conn){
		this.conn = conn;
	}
	

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try{
			st = conn.prepareStatement("INSERT INTO department (Id, Name) VALUES (?, ?)");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			st.executeUpdate();
		}catch(Exception e){
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
