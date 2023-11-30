package step01_board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step01_board.dao.BoardDAO1;
import step01_board.dto.BoardDTO1;

@WebServlet("/bAuthentication1")
public class AuthenticationBoard1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long boardId = Long.parseLong(request.getParameter("boardId"));
		
		request.setAttribute("boardDTO", BoardDAO1.getInstance().getBoardDetail(boardId));
		request.setAttribute("menu", request.getParameter("menu"));
		
		RequestDispatcher dis = request.getRequestDispatcher("step01_boardEx/bAuthentication1.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		long boardId = Long.parseLong(request.getParameter("boardId"));
		
		BoardDTO1 boardDTO = new BoardDTO1();
		boardDTO.setBoardId(boardId);
		boardDTO.setPassword(request.getParameter("password"));
		
		String menu = request.getParameter("menu");
		
		
		
	}

}
