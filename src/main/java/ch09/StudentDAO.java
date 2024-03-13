package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	Connection conn = null;
	PreparedStatement pstmt;

	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";

	public void open(){
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"jwbook","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Student s) {
		open();
		String sql = "INSERT INTO student(username, univ, birth, email) values(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getUsername());
			pstmt.setString(2, s.getUniv());
			pstmt.setDate(3, s.getBirth());
			pstmt.setString(4, s.getEmail());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void delete(int id, String username) {
		open();
		String sql = "DELETE FROM student WHERE id = ? AND username = ?";
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, username);
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public List<Student> select(int id, String username, String univ) {
		open();
		List<Student> students = new ArrayList<>();
			
		try {
			if(id != 0) {
				pstmt = conn.prepareStatement("select * from student where id = ? AND (username LIKE ? AND univ LIKE ?) ORDER BY username ASC, birth DESC");
			}else {
				pstmt = conn.prepareStatement("select * from student where id = ? OR (username LIKE ? AND univ LIKE ?) ORDER BY username ASC, birth DESC");
			}
			pstmt.setInt(1, id);
			pstmt.setString(2, "%" + username + "%");
			pstmt.setString(3, "%"+ univ + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getDate("birth"));
				s.setEmail(rs.getString("email"));
				
				students.add(s);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return students;
	}
	
	public void update(Student s) {
		open();
		String sql = "UPDATE student SET username = ?, univ = ?, birth = ?, email = ? WHERE id = ?";
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getUsername());
			pstmt.setString(2, s.getUniv());
			pstmt.setDate(3, s.getBirth());
			pstmt.setString(4, s.getEmail());
			pstmt.setInt(5, s.getId());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public List<Student> getAll() {
		open();
		List<Student> students = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from student");
			ResultSet rs = pstmt.executeQuery();
	
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getDate("birth"));
				s.setEmail(rs.getString("email"));
				
				students.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return students;
	}
}
