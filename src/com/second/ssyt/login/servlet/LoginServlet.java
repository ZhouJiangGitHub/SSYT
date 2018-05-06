package com.second.ssyt.login.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.second.ssyt.login.dao.LoginDao;
import com.second.ssyt.login.entity.UserEntity;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	LoginDao loginDao = new LoginDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		// 字符编码
		request.setCharacterEncoding("UTF8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String command = request.getParameter("command");
		if ("login".equals(command)) {
			login(request, response);
		}else if("user-detail".equals(command)){
			userDetail(request, response);
		}else if("loginout".equals(command)){
			loginout(request, response);
		}else if("updatepasswordpage".equals(command)){
			updatepasswordpage(request, response);
		}else if("userupdate".equals(command)){
			userupdate(request, response);
		}else if("updatePW".equals(command)){
			updatePW(request, response);
		}else if("validatePhone".equals(command)){
			validatePhone(request, response);
		}else if("validateEmail".equals(command)){
			validateEmail(request, response);
		}else if("loginFailure".equals(command)){
			loginFailure(request, response);
		}else{
			//非法调用
		}


	}

	/**
	 * 用户修改邮箱时 做唯一验证
	 * 
	 * @param request
	 * @param response
	 */
	private void validateEmail(HttpServletRequest request,
			HttpServletResponse response) {
			
			String email = request.getParameter("email");
			String userId = request.getParameter("userId");
			int rows = loginDao.validateEmail(email,userId);
			System.out.println("rows="+rows);
			try {
				
				if(rows > 0){
						response.getWriter().print(-1);
					
				}else{
					response.getWriter().print(1);
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}

	/**
	 * 用户修改电话时 做唯一验证
	 * 
	 * @param request
	 * @param response
	 */
	private void validatePhone(HttpServletRequest request,
			HttpServletResponse response) {
			
			String phone = request.getParameter("phone");
			String userId = request.getParameter("userId");
			
			int rows = loginDao.validatePhone(phone,userId);
			
			try {
				
				if(rows > 0){
						response.getWriter().print(-1);
					
				}else{
					response.getWriter().print(1);
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}

	private void loginFailure(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("登录失效");
		request.getRequestDispatcher("/WEB-INF/view/common/login.jsp?loginFailure=true").forward(request, response);
		
	}

	/**
	 * 该方法用于用户密码修改
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updatePW(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String PW = request.getParameter("PW");
		String opeartion = request.getParameter("opeartion");
		
		if("validateOldPW".equals(opeartion)){
			
			//验证原密码
			UserEntity user = (UserEntity)request.getSession().getAttribute("user");
			if(!user.getPassword().equals(PW)){
				response.getWriter().print(0);
			}else{
				response.getWriter().print(1);
			}
			
		}
		if("validateNewPW".equals(opeartion)){
			
			//验证新密码
			if(!StringUtils.isNoneBlank(PW)){
				
			}else if(PW.length() < 5){
				response.getWriter().print(-1);
			}else if(PW.length() > 12){
				response.getWriter().print(-2);
			}else{
				response.getWriter().print(2);
			}
		}
		if("validateRepPW".equals(opeartion)){
			
			
		}
		if("update".equals(opeartion)){
			
			String newPW = request.getParameter("newpassword");
			System.out.println(newPW);
			System.out.println(id);
			//调用dao层逻辑（密码更新）
			int rows = loginDao.updatePW(id,newPW);
			if(rows == 1){
				//密码修改成功
				
				request.getSession().invalidate();
				//跳转到登录页面
				request.getRequestDispatcher("/WEB-INF/view/common/login.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * 用户修改信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void userupdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		int updateState = loginDao.userUpdate(id,phone,email);
		
		if(updateState == 1){
			//更新成功
			UserEntity user = (UserEntity)request.getSession().getAttribute("user");
			
			//更新session里的值
			user.setPhone(phone);
			user.setEmail(email);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/view/common/main.jsp?updateState="+updateState).forward(request, response);
		}else{
			//更新失败
		}
		
		
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updatepasswordpage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/common/updatepassword.jsp").forward(request,
				response);
	}

	/**
	 * 注销登录
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void loginout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//session 失效
		request.getSession().invalidate();
		request.getRequestDispatcher("/WEB-INF/view/common/login.jsp").forward(request,
				response);
	}

	private void userDetail (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/common/user-detail.jsp").forward(request,
				response);
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取登录参数
		String code = request.getParameter("code");
		String password = request.getParameter("password");
		String verification = request.getParameter("verification");
		String rememberLogin = request.getParameter("rememberLogin");
		
		String sessionverification = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

		UserEntity user = loginDao.login(code, password);
		if (user.getId() < 1) {
			// 密码或用户名错误
			request.setAttribute("errorState", "密码或用户名错误");
			request.setAttribute("code", code);
			request.getRequestDispatcher("/WEB-INF/view/common/login.jsp").forward(request,
					response);
		} else if (user.getState() == 2) {
			// 用户失效（限制登录）（1：有效。2：失效）
			request.setAttribute("errorState", "该用户被限制登录");
			request.setAttribute("code", code);
			request.getRequestDispatcher("/WEB-INF/view/common/login.jsp").forward(request,
					response);
		}else if (user.getClass$state() == 2) {
			// 所属班级失效
			request.setAttribute("errorState", "所属班级无效");
			request.setAttribute("code", code);
			request.getRequestDispatcher("/WEB-INF/view/common/login.jsp").forward(request,
					response);
		}else if (user.getRole$state() == 2) {
			// 所属角色失效 
			request.setAttribute("errorState", "所属角色无效");
			request.setAttribute("code", code);
			request.getRequestDispatcher("/WEB-INF/view/common/login.jsp").forward(request,
					response);
		}else if (StringUtils.isBlank(sessionverification)) {
			// 验证码错误
			request.setAttribute("errorState", "会话已关闭！");
			request.setAttribute("code", code);
			request.getRequestDispatcher("/WEB-INF/view/common/login.jsp").forward(request,
					response);
			
		}else if (!(sessionverification.equals(verification))) {
				// 验证码错误
				request.setAttribute("errorState", "验证码错误");
				request.setAttribute("code", code);
				request.getRequestDispatcher("/WEB-INF/view/common/login.jsp").forward(request,
						response);
				
		}else {
			// 用户正常登录
			
			//存Session
			request.getSession().setAttribute("user", user);
			

			//Cookie的存入
			
			if ("true".equals(rememberLogin)) {
				// 设置Cookie
				Cookie codeCookie = new Cookie("code", URLEncoder.encode(code, "UTF-8"));
				// 如果没有设置存活时间（单位为：秒），Cookie在浏览器关闭就会失效，也并不会存入硬盘
				codeCookie.setMaxAge(60 * 30);
	    		response.addCookie(codeCookie);
				Cookie passwordCookie = new Cookie("password", password);
				// 如果没有设置存活时间，Cookie在浏览器关闭就会失效，也并不会存入硬盘
				passwordCookie.setMaxAge(60 * 30);
	    		response.addCookie(passwordCookie);
				Cookie rememberLoginCookie = new Cookie("rememberLogin", rememberLogin);
				// 如果没有设置存活时间，Cookie在浏览器关闭就会失效，也并不会存入硬盘
				rememberLoginCookie.setMaxAge(60 * 30);
	    		response.addCookie(rememberLoginCookie);
			}
			//把用户名放到session
			request.getSession().setAttribute("username", code);
			request.getRequestDispatcher("/WEB-INF/view/common/main.jsp").forward(request, response);
		}
		
	}
}


