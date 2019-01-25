package com.douzon.mysite.vo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//no
//name
//password
//message
//reg_date

public class GuestbookDao {
	
	public boolean delete(GuestbookVo vo) {
		
		boolean result = false;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "DELETE FROM guestbook WHERE no = ? and password = ? ;";
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			
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
	
	
	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "insert into guestbook values(null, ?, ?, ?, now())";
			String sql2 = "select now()";
			pstmt = conn.prepareStatement(sql);

			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
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

	
	public List<GuestbookVo> getList(){
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = getConnection();
			  
			  String sql = "select no, name, password, message, reg_date from guestbook order by no desc";
			  
			  pstmt = conn.prepareStatement(sql);
		
			  rs = pstmt.executeQuery();
			  
			  while(rs.next()) {
				  long no = rs.getLong(1);
				  String name = rs.getString(2);
				  String password = rs.getString(3);
				  String message = rs.getString(4);
				  Date reg_date = rs.getDate(5);
				  
				  
				  GuestbookVo vo = new GuestbookVo();
				  vo.setNo(no);
				  vo.setName(name);
				  vo.setPassword(password);
				  vo.setMessage(message);
				  vo.setReg_date(reg_date);
				  
				  list.add(vo); 
			  }
		
		} catch (SQLException e) {
			System.out.println("erorr:" + e);
		} finally {
			try {
			if(conn!=null)
				conn.close();
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}		
		}	
		return list;
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
