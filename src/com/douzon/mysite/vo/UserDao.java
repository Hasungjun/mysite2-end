package com.douzon.mysite.vo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	
	
	public UserVo get(String email, String password) {
		UserVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = 
				" select no, name" + 
				"   from user" + 
				"  where email=?" +
				"    and password=?";

			if(password!=null&&email!=null) {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
			}
		}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	
	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "insert into user values(null, ?, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("erorr:" + e);
		} finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	public boolean update(UserVo vo) {
		boolean result = false;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "UPDATE user SET password = ? ,name = ?, gender = ?  WHERE no= ?";
			
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getGender());
			pstmt.setLong(4, vo.getNo());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("erorr:" + e);
		} finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	
	
	
	
	
	
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		
		return conn;
	}
}
