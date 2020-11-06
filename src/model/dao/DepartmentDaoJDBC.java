package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
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
		}finally{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try{
			st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			st.setString(1, obj.getName());
			st.setInt(2,  obj.getId());
			st.executeUpdate();
		}catch(Exception e){
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try{
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}catch(Exception e){
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		ResultSet rs = null;
		PreparedStatement st = null;
		try{
			st = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				int departmentId = rs.getInt("Id");
				String name = rs.getString("Name");
				Department dep = new Department(departmentId, name);
				return dep;
			}
			
		}catch(Exception e){
			throw new DbException(e.getMessage());
		}finally{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}

	@Override
	public List<Department> findAll() {
		ResultSet rs = null;
		Statement st = null;
		try{
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM department");
			List<Department> departments = new ArrayList<>();
			while(rs.next()){
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				Department dep = new Department(id, name);
				departments.add(dep);
			}
			return departments;
		}catch(Exception e){
			throw new DbException(e.getMessage());
		}finally{
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}

}
