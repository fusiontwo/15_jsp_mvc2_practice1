package step01_board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step01_board.dao.BoardDAO1;

@WebServlet("/bList1")
public class ListBoard1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("boardList", BoardDAO1.getInstance().getBoardList());
		
		RequestDispatcher dis = request.getRequestDispatcher("step01_boardEx/bList1.jsp");
		dis.forward(request, response);
	}

}
