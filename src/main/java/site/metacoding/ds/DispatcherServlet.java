package site.metacoding.ds;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")  // 3 websevlet 설정
public class DispatcherServlet extends HttpServlet{ // 1 소켓 구현 완료
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 호출됨");  // 2 doGet ctrl+spacebar
		doProcess(req, resp);
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("doPost 호출됨"); //4 doPost 
			doProcess(req, resp);
		}
	
	@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doProcess(req, resp);
		}
	
	@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doProcess(req, resp);
		}
	
		private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("doProcess 요청됨");
			
			String httpMethod = req.getMethod();
			System.out.println(httpMethod);
			
			String identifier = req.getRequestURI();   // 파싱할 수 있다.
			System.out.println(identifier);
			
			// FrontController : 공통 로직 처리 (DS에서 분개되기 전에 실행됨)
			// 문제점 : 모든 Controller를 담고 있지 않다. 이 프로그램을 굳이 사지 않겠지? 내가 만들어 쓰면 되는데 ?
			// 해결 : Runtime 시 Annotation 을 분석해서 때린다! => DS
			UserController c = new UserController();
			if(identifier.equals("/join")) {
				c.join();
			}else if (identifier.equals("/login")) {
				c.login();
			}else {
				System.out.println("잘못된 요청입니다.");
			}			
			
			
		}
		
}
