/*==================================
   #15. EmployeeListController.java
   - 사용자 정의 컨트롤러 클래스
   - 리스트 페이지 요청에 대한 액션 처리
   - DAO 객체 에 대한 의존성 주입(DI)을 위한 준비
   → 인터페이스 형태의 자료형을 속성으로 구성
   → setter 메소드 구성
===================================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmployeeListController implements Controller
{
	// DAO 인터페이스 자료형 멤버 구성
	private IEmployeeDAO dao;
	
	// 의존성 주입을 위한 setter 구성
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 세션 처리과정 추가 ----------------------------------------------------------------------
		
		// 직원들 리스트를 확인하는 페이지 → 관리자만 접근 가능
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null) //-- 로그인이 되어있지 않은 상황
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
		}
		else if(session.getAttribute("admin")==null)	//--관리자가 아닌 경우
		{
			mav.setViewName("redirect:logout.action");
			return mav;
		}
		
		// 세션 처리과정 추가 ----------------------------------------------------------------------
		
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		try
		{
			employeeList = dao.list();
			
			mav.addObject("employeeList", employeeList);
			mav.setViewName("/WEB-INF/views/EmployeeList.jsp");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
