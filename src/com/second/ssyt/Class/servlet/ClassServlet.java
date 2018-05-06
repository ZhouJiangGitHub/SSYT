package com.second.ssyt.Class.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.second.ssyt.Class.dao.ClassDao;
import com.second.ssyt.Class.entity.ClassEntity;
import com.second.ssyt.common.PageModel;
@WebServlet("/ClassServlet")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassDao  classDao = new ClassDao();
	
	PageModel<ClassEntity> pageModel = new PageModel<>();
	
	
       
    
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(1);
		request.setCharacterEncoding("UTF-8");
		
		 String command= request.getParameter("command");
		 if ("add".equals(command)) {
				add(request, response);
				
		 }else if("List".equals(command)){
			   List(request, response);
			  
	   
		} else if("classSelect".equals(command)) {
			
			classSelect(request, response);
			
		}else if("classAddPage".equals(command)) {
			
			classAddPage(request, response);
			
		}else if("add".equals(command)){
			
			add(request, response);
			
		} else if("delete".equals(command)){
			delete(request,response);
			
		}else if("updateState".equals(command)){
			updateState(request,response);
			
		}
		else if("update".equals(command)){
			update(request,response);
		}
		 
		else if("last".equals(command)){
			last(request,response);
		}
		else if("validateClass".equals(command)){
			validateClass(request,response);
		}
		else{
			
			throw new IllegalStateException("非法调用");
		}
	
	}
	 
		/**
		 *添加班级
		 * 
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
	private void classAddPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			
			request.getRequestDispatcher("/WEB-INF/view/Class/Class.jsp").forward(request,
					response);
		
	}
	/**
	 * 班级查找
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void classSelect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/view/Class/select.jsp").forward(request,
					response);
		
	}
	/**
	 * 班级新增
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
		//获取前台传来的参数
	 	ClassEntity  user= new  ClassEntity();
	 	String  name= request.getParameter("name");
	 	int course_id=Integer.parseInt(request.getParameter("course_id"));
	 	int operate_user_id = Integer.parseInt(request.getParameter("operate_user_id"));
	 	String  date= request.getParameter("operate_time");
      	int state=Integer.parseInt(request.getParameter("state"));
      	String  memo= request.getParameter("memo");
	   
	   
	   user.setName(name);
	   user.setCourse_id(course_id);
	   user.setOperate_user_id(operate_user_id);
	   user.setOperate_time(date);
	   user.setState(state);
	   user.setMemo(memo);
	   
	   int rows = classDao.add(user);
	   if(rows == 1){
		   
		   request.getRequestDispatcher("ClassServlet?command=List").forward(request, response);
	   }
	   
}


		 
		private void List(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			int pageNo=1;
			int pageSize=10;
			String pageNostr=request.getParameter("pageNo");
			if(pageNostr!=null){
				
				pageNo=Integer.parseInt(pageNostr);
			}
			//查询条件参数
			String  name= request.getParameter("name");
			/*//接收前台传来的参数
			if(request.getParameter("pageNo") != null && request.getParameter("pageNo") != ""){
				int pageNo = Integer.parseInt(request.getParameter("pageNo"));
				pageModel.setPageNo(pageNo);
			}
			if(request.getParameter("pageSize") != null && request.getParameter("pageSize") != ""){
				
				int pageSize= Integer.parseInt(request.getParameter("pageSize"));
				pageModel.setPageSize(pageSize);
			}
			*/
			ClassEntity cla = new ClassEntity();
			cla.setName(name);

			// 3、转向
			request.setAttribute("cla", cla);
			//调用义务逻
			PageModel<ClassEntity> pageModel = classDao.List(cla,pageNo, pageSize);
			request.setAttribute("pageModel", pageModel);
	         request.getRequestDispatcher("WEB-INF/view/Class/select.jsp").forward(request, response);
		}	
	
		  
		/**
		 * 班级删除
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void delete(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 获取删除用户信息的编号
			String id = request.getParameter("id");
		    int uid = Integer.parseInt(id);

			int row = classDao.deleteUserById(uid);

			if (row > 0) {
				response.sendRedirect("ClassServlet?command=List");
			}
		}
		    
		 
		private void update(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			// 获取删除用户信息的编号
			
			String id = request.getParameter("id");
			int uid = Integer.parseInt(id);
         ClassEntity cla= classDao.getclassById(uid);
         request.setAttribute("cla", cla);
         
    request.getRequestDispatcher("WEB-INF/view/Class/update.jsp").forward(request, response) ;
		}
		
		  
		private void last(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 设置编码格式
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");

			PrintWriter out = response.getWriter();

	//获取前台传来的参数
			ClassEntity  user= new  ClassEntity();
			
		 	String  name= request.getParameter("name");
		 	int course_id=Integer.parseInt(request.getParameter("course_id"));
		 	int operate_user_id = Integer.parseInt(request.getParameter("operate_user_id"));
		 	String  date= request.getParameter("operate_time");
	      	int state=Integer.parseInt(request.getParameter("state"));
	      	String  memo= request.getParameter("memo");
	      	int id =Integer.parseInt(request.getParameter("id"));
		   
		// 把获取的信息封装到UserEntity对象中
	    
	   user.setName(name);
	   user.setCourse_id(course_id);
	   user.setOperate_user_id(operate_user_id);
	   user.setOperate_time(date);
	   user.setState(state);
	   user.setMemo(memo);
	   user.setId(Integer.valueOf(id));
	 
	// 调用userDao中添加用户信息的方法
	   
	   int row =  classDao.updateUser(user);
		// 判断操作结果
		if (row > 0) {
			response.sendRedirect("ClassServlet?command=List");
		} else {
			out.println("<script>alert('修改失败');location.href='UserServlet';</script>");
		}

		out.flush();
		out.close();
	}

	    
		/**
		 * 状态更新
		 * 
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServletException
		 */
		private void updateState(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			int state = Integer.parseInt(request.getParameter("state"));

			int row = classDao.update(id, state);


		    PrintWriter  printWriter  =response.getWriter();
			try {
			 	printWriter.print(row);
			} finally {
				printWriter.close();
			}
		}
		 
		/**
		 * 班级的唯一验证
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void validateClass(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			//获取参数
			String className = request.getParameter("className");
			String classId = request.getParameter("classId");
			
			int rows = classDao.validateClass(className,classId);
			
			if(rows > 0){
				//该班级名已存在
				response.getWriter().print(true);
			}else{
				//该班级可用
				response.getWriter().print(false);
			}
	}

}

