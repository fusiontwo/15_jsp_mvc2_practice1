package step01_board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step01_board.dao.BoardDAO1;
import step01_board.dto.BoardDTO1;

@WebServlet("/bWrite1")
public class WriteBoard1 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("step01_boardEx/bWrite1.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardDTO1 boardDTO1 = new BoardDTO1();
		
		boardDTO1.setWriter(request.getParameter("writer"));
		boardDTO1.setSubject(request.getParameter("subject"));
		boardDTO1.setEmail(request.getParameter("email"));
		boardDTO1.setPassword(request.getParameter("password"));
		boardDTO1.setContent(request.getParameter("content"));
		
		BoardDAO1.getInstance().insertBoard(boardDTO1);
		
		String jsScript = "<script>";
			   jsScript += "alert('게시글이 등록되었습니다.');";
			   jsScript += "location.href='bList1';";
			   jsScript += "</script>";
			   
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsScript);
	}

}
