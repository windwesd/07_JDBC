package edu.kh.todolist.controller;

import java.io.IOException;

import edu.kh.todolist.service.TodoListService;
import edu.kh.todolist.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/complete")
public class CompleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 전달 받은 파라미터 (todoNo) 얻어오기
		int todoNo = Integer.parseInt( req.getParameter("todoNo") );
		
		try {
			
			// 할일 여부를 변경하는 서비스 호출 후 결과 반환 받기
			TodoListService service = new TodoListServiceImpl();
			int result = service.todoComplete(todoNo);
			
			
			// session scope 객체 얻어오기
			HttpSession session = req.getSession();
			
			// 변경 성공 시
			// -> 원래 보고있던 상세 페이지로 redirect
			if(result > 0) {
				
				session.setAttribute("message", 
						"완료 여부가 변경 되었습니다");
				resp.sendRedirect("/todo/detail?todoNo=" + todoNo);
				return;
			}
			
			// 변경 실패 시
			session.setAttribute("message", 
					"todo가 존재하지 않습니다");
			
			// 메인 페이지로 redirect
			resp.sendRedirect("/");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	
	}
}