package step01_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import step01_board.dto.BoardDTO1;

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
	
	public void insertBoard(BoardDTO1 boardDTO1) {
		
		getConnection();
		
		String sql = "INSERT INTO BOARD1 (WRITER,EMAIL,SUBJECT,PASSWORD,CONTENT,READ_CNT,ENROLL_DT) VALUES (?,?,?,?,?,0,NOW())";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO1.getWriter());
			pstmt.setString(2, boardDTO1.getEmail());
			pstmt.setString(3, boardDTO1.getSubject());
			pstmt.setString(4, boardDTO1.getPassword());
			pstmt.setString(5, boardDTO1.getContent());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getClose();
		}
		
	}
	
	public ArrayList<BoardDTO1> getBoardList() {
		
		ArrayList boardList = new ArrayList<BoardDTO1>();
		
		getConnection();
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM BOARD1");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardDTO1 temp = new BoardDTO1();
				temp.setBoardId(rs.getLong("BOARD_ID"));
				temp.setWriter(rs.getString("WRITER"));
				temp.setSubject(rs.getString("SUBJECT"));
				temp.setReadCnt(rs.getLong("READ_CNT"));
				temp.setEnrollDt(rs.getDate("ENROLL_DT"));
				boardList.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getClose();
		}

		return boardList;
	}

	public BoardDTO1 getBoardDetail(long boardId) {
		BoardDTO1 boardDTO = new BoardDTO1();
		
		try {
			getConnection();

			pstmt = conn.prepareStatement("UPDATE BOARD1 SET READ_CNT = READ_CNT + 1 WHERE BOARD_ID = ?");
			pstmt.setLong(1, boardId);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("SELECT * FROM BOARD1 WHERE BOARD_ID = ?");
			pstmt.setLong(1, boardId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				boardDTO.setBoardId(boardId);
				boardDTO.setWriter(rs.getString("WRITER"));
				boardDTO.setEmail(rs.getString("EMAIL"));
				boardDTO.setSubject(rs.getString("SUBJECT"));
				boardDTO.setContent(rs.getString("CONTENT"));
				boardDTO.setReadCnt(rs.getLong("READ_CNT"));
				boardDTO.setEnrollDt(rs.getDate("ENROLL_DT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getClose();
		}
		return boardDTO;
	}
	
	public boolean checkAuthorizedUser(BoardDTO1 boardDTO) {
		
		boolean isAuthorizedUser = false;
		
		try {
			
			getConnection();

			pstmt = conn.prepareStatement("SELECT * FROM BOARD1 WHERE BOARD_ID = ? AND PASSWORD = ?");
			pstmt.setLong(1, boardDTO.getBoardId());
			pstmt.setString(2, boardDTO.getPassword());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isAuthorizedUser = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getClose();
		}

		return isAuthorizedUser;
	}
}
