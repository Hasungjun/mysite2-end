package com.douzon.mysite.vo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoardDao {

	
	
	public boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "UPDATE board SET title= ? , contents = ? WHERE no= ? and user_no = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getNo());
			pstmt.setInt(4, vo.getUser_no());

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
	
	
	public boolean delete(int no) {
		boolean result = false;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "DELETE FROM board WHERE no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

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
	
	
	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn =null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "insert into board values(null, ?, ?, now(), 0 , 0, 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getUser_no());
			
			int count = pstmt.executeUpdate();
			
			String sql2 = "select no from board where g_no = 0";
			String sql3 ="UPDATE board SET g_no= ? WHERE no= ?";
			
			BoardVo voo = new BoardVo();
			
			pstmt2 = conn.prepareStatement(sql2);
			rs = pstmt2.executeQuery();
			
			while(rs.next()) {
				  int no = rs.getInt(1);
				  voo.setNo(no);
				  break;
			  }
			
			pstmt3 = conn.prepareStatement(sql3);
			
			pstmt3.setInt(1, voo.getNo());
			pstmt3.setInt(2, voo.getNo());
			pstmt3.executeUpdate();
						
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("erorr:" + e);
		} finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(pstmt2!=null)
					pstmt2.close();
				if(conn!=null)
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}
	
	
	
	public boolean reply(BoardVo vo, int no) {
		boolean result = false;
		Connection conn =null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt3_5 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			conn = getConnection();
			
			String sql = "insert into board values(null, ?, ?, now(), 0 , 0, 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getUser_no());
			
			int count = pstmt.executeUpdate();
			

			String sql2 = "select g_no, o_no, depth from board where no = ?";
			
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setInt(1, no);
			
			rs = pstmt2.executeQuery();
			rs.next();
				  int g_no = rs.getInt(1);
				  int o_no = rs.getInt(2);
				  int depth = rs.getInt(3);
				  
			String sql3 = "select no from board where g_no = 0";
			
			pstmt3 = conn.prepareStatement(sql3);
			
			rs2 = pstmt3.executeQuery();
			
			rs2.next();
			int child_no = rs2.getInt(1);
			
			
			
			String sql3_5 = "UPDATE board SET o_no=o_no+1 WHERE o_no > ? and g_no = ?";
			
			pstmt3_5 = conn.prepareStatement(sql3_5);

			pstmt3_5.setInt(1, o_no);
			pstmt3_5.setInt(2, g_no);
		    pstmt3_5.executeUpdate();
			
			
			String sql4 ="UPDATE board SET g_no= ? ,o_no = ?, depth = ? WHERE no= ?";
			
			pstmt4 = conn.prepareStatement(sql4);
			
			pstmt4.setInt(1, g_no);
			pstmt4.setInt(2, ++o_no);
			pstmt4.setInt(3, ++depth);
			pstmt4.setInt(4, child_no);
			
			pstmt4.executeUpdate();
				
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("erorr:" + e);
		} finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(pstmt2!=null)
					pstmt2.close();
				if(pstmt3!=null)
					pstmt3.close();
				if(pstmt4!=null)
					pstmt4.close();
				if(conn!=null)
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;
	}

	
	public List<BoardVo> select(BoardVo vo){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			  conn = getConnection();
			  
			  String sql = "select title , contents, user_no, no , depth from board where no = ?";
			  String sql2 = "UPDATE board SET hit=hit+1  WHERE no = ?";
			  
			  pstmt2 = conn.prepareStatement(sql2);
			  
			  pstmt2.setInt(1, vo.getNo());
			  
			  pstmt2.executeUpdate();
			  
			  pstmt = conn.prepareStatement(sql);
			  
			  pstmt.setInt(1, vo.getNo());
			 
			  rs = pstmt.executeQuery();
			  
			  while(rs.next()) {
				  String title = rs.getString(1);
				  String contents = rs.getString(2); 
				  int user_no = rs.getInt(3); 
				  int no = rs.getInt(4); 
				  int depth = rs.getInt(5); 
				  
				  BoardVo voo = new BoardVo();
				  voo.setTitle(title);
				  voo.setContents(contents);
				  voo.setUser_no(user_no);
				  voo.setNo(no);
				  voo.setDepth(depth);
				  
				  list.add(voo); 
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
	
	public List<BoardVo> getList(){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = getConnection();
			  
			  String sql = "select a.no, a.title, b.name, a.write_date, a.hit, a.user_no, a.depth from board a, user b where a.user_no = b.no order by g_no DESC, o_no ASC";
			  
			  pstmt = conn.prepareStatement(sql);
			 
			 
			  rs = pstmt.executeQuery();
			  
			  while(rs.next()) {
				  int no = rs.getInt(1);
				  String title = rs.getString(2);
				  String user_name = rs.getString(3);
				  Date write_date = rs.getDate(4);
				  int hit = rs.getInt(5);
				  int user_no = rs.getInt(6);
				  int depth = rs.getInt(7);
				  
				  BoardVo vo = new BoardVo();
				  vo.setNo(no);
				  vo.setTitle(title);
				  vo.setUser_name(user_name);
				  vo.setWrite_date(write_date);
				  vo.setHit(hit);
				  vo.setUser_no(user_no);
				  vo.setDepth(depth);;
				  
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
	
	public List<BoardVo> getList(String search){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = getConnection();
			  
			  String sql = "select a.no, a.title, b.name, a.write_date, a.hit, a.user_no, a.depth from board a, user b where a.user_no = b.no and a.title like ? or a.contents like ? group by a.no order by g_no DESC, o_no ASC";
			  
			  pstmt = conn.prepareStatement(sql);
			 
			  pstmt.setString(1, '%'+search+'%');
			  pstmt.setString(2, '%'+search+'%');
			  rs = pstmt.executeQuery();
			  
			  while(rs.next()) {
				  int no = rs.getInt(1);
				  String title = rs.getString(2);
				  String user_name = rs.getString(3);
				  Date write_date = rs.getDate(4);
				  int hit = rs.getInt(5);
				  int user_no = rs.getInt(6);
				  int depth = rs.getInt(7);
				  
				  BoardVo vo = new BoardVo();
				  vo.setNo(no);
				  vo.setTitle(title);
				  vo.setUser_name(user_name);
				  vo.setWrite_date(write_date);
				  vo.setHit(hit);
				  vo.setUser_no(user_no);
				  vo.setDepth(depth);;
				  
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
	public List<BoardVo> getListPage(int pg, int size){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = getConnection();
			  
			  String sql = "select a.no, a.title, b.name, a.write_date, a.hit, a.user_no, a.depth from board a, user b where a.user_no = b.no order by g_no DESC, o_no ASC limit ? offset ?";
			  
			  pstmt = conn.prepareStatement(sql);
			 
			  pstmt.setInt(1, size);
			  pstmt.setInt(2, (pg-1)*size);
			  
			  rs = pstmt.executeQuery();
			  
			  while(rs.next()) {
				  int no = rs.getInt(1);
				  String title = rs.getString(2);
				  String user_name = rs.getString(3);
				  Date write_date = rs.getDate(4);
				  int hit = rs.getInt(5);
				  int user_no = rs.getInt(6);
				  int depth = rs.getInt(7);
				  
				  BoardVo vo = new BoardVo();
				  vo.setNo(no);
				  vo.setTitle(title);
				  vo.setUser_name(user_name);
				  vo.setWrite_date(write_date);
				  vo.setHit(hit);
				  vo.setUser_no(user_no);
				  vo.setDepth(depth);;
				  
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
	
	public List<BoardVo> getListPage(int pg, int size, String search){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = getConnection();
			  
			  String sql = "select a.no, a.title, b.name, a.write_date, a.hit, a.user_no, a.depth from board a, user b where a.user_no = b.no and a.title like ? or a.contents like ? group by a.no order by g_no DESC, o_no ASC limit ? offset ?;";
			  
			  pstmt = conn.prepareStatement(sql);
			 
			  pstmt.setString(1, '%'+search+'%');
			  pstmt.setString(2, '%'+search+'%');
			  pstmt.setInt(3, size);
			  pstmt.setInt(4, (pg-1)*size);
			  
			  rs = pstmt.executeQuery();
			  
			  while(rs.next()) {
				  int no = rs.getInt(1);
				  String title = rs.getString(2);
				  String user_name = rs.getString(3);
				  Date write_date = rs.getDate(4);
				  int hit = rs.getInt(5);
				  int user_no = rs.getInt(6);
				  int depth = rs.getInt(7);
				  
				  BoardVo vo = new BoardVo();
				  vo.setNo(no);
				  vo.setTitle(title);
				  vo.setUser_name(user_name);
				  vo.setWrite_date(write_date);
				  vo.setHit(hit);
				  vo.setUser_no(user_no);
				  vo.setDepth(depth);;
				  
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
