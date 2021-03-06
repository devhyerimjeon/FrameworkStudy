/*==================================
   DepartmentUpdateFormController.java
   - 사용자 정의 컨트롤러 클래스
   - 사용자가 부서를 수정할 수 있는 화면으로 이동
   - 저장되어 있는 부서 정보를 가지고 뷰 화면으로 이동
===================================*/

package com.test.mvc;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class DepartmentUpdateFormController implements Controller
{
	private IDepartmentDAO dao;
	
	public void setDao(IDepartmentDAO dao)
	{
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		// 세션 처리과정 추가 (관리자만 접근) -----------------------------------------------------
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null)
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
		}
		else if(session.getAttribute("admin")==null)
		{
			mav.setViewName("redirect:logout.action");
			return mav;
		}
		// 세션 처리과정 추가 ----------------------------------------------------------------------
		
		try
		{
			Department department = new Department();
			
			// 데이터 수신 → DepartmentList.jsp로부터 departmentName 수신
			String departmentId = request.getParameter("departmentId");
			
			department = dao.searchId(departmentId);
			
			mav.addObject("department", department);
			mav.setViewName("WEB-INF/views/DepartmentUpdateForm.jsp");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
