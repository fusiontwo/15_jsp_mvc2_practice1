package step01_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO1 {
	
	private BoardDAO1() {}
	private static BoardDAO1 instance = new BoardDAO1();
	public static BoardDAO1 getInstance() {
		return instance;
	}
	
	private Connection conn 		= null;
	private PreparedStatement pstmt = null;
	private ResultSet rs 			= null;
	
	private void getConnection() {
		
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/board");
			conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void getClose() {
		try {rs.close();} catch (SQLException e) {e.printStackTrace();}
		try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
		try {conn.close();} catch (SQLException e) {e.printStackTrace();}
	}
}
