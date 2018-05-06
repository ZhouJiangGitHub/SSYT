package com.second.ssyt.Stand.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.second.ssyt.PublicEntity.SysUserEntity;
import com.second.ssyt.Stand.Dao.StandDao;
import com.second.ssyt.Stand.Entity.StandEntity;
import com.second.ssyt.common.PageModel;

public class StandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private  StandDao  standDao = new StandDao();
	
	PageModel<StandEntity> pageModel = new PageModel<>();
   
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		 String command= request.getParameter("command");
		 if ("add".equals(command)) {
				add(request, response);
				
		 }else if("List".equals(command)){
			   List(request, response);
			  
	   
		} else if("standSelect".equals(command)) {
			
			standSelect(request, response);
			
		}else if("standAddPage".equals(command)) {
			
			standAddPage(request, response);
			
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
		else if("details".equals(command)){
			details(request,response);
		}
		else if("truedelete".equals(command)){
			truedelete(request,response);
		}
		else{
			
			throw new IllegalStateException("非法调用");
		}
	
	}
	 
		/**
		 *新增站内信息
		 * 
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
	private void standAddPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
			
			request.getRequestDispatcher("/WEB-INF/view/Stand/stand.jsp").forward(request,
					response);
		
	}
	/**
	 * 站内查找
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void standSelect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/view/Stand/select.jsp").forward(request,
					response);
		
	}
	private void add(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
		//获取前台传来的参数
	 	StandEntity  stand= new  StandEntity();
	 	String  title= request.getParameter("title");
	 	String  content= request.getParameter("content");
	 	String  send_time=request.getParameter("send_time");
	 	int  sender_id=Integer.parseInt(request.getParameter("sender_id"));
	 	String  receiver_user_ids=request.getParameter("receiver_user_ids");
	 	int  receiver_class_ids=Integer.parseInt(request.getParameter("receiver_class_ids"));
	 	String  expire_time= request.getParameter("expire_time");
     	int state=Integer.parseInt(request.getParameter("state"));
     	
	   
	   
	    stand.setTitle(title);
	    stand.setContent(content);
	    stand.setSend_time(send_time);
	    stand.setSender_id(sender_id);
	    stand.setReceiver_user_ids(receiver_user_ids);
	    stand.setReceiver_class_ids(receiver_class_ids);
	    stand.setExpire_time(expire_time);
	    stand.setState(state);
	   
	   int rows = standDao.add(stand);
	   if(rows == 1){
		   
		   request.getRequestDispatcher("StandServlet?command=List").forward(request, response);
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
			String  title= request.getParameter("title");
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
			StandEntity sta = new StandEntity();
			sta.setTitle(title);

			// 3、转向
			request.setAttribute("sta", sta);
			//调用义务逻
			PageModel<StandEntity> pageModel = standDao.List(sta,pageNo, pageSize);
			request.setAttribute("pageModel", pageModel);
	         request.getRequestDispatcher("WEB-INF/view/Stand/select.jsp").forward(request, response);
		}	
	
		  
		private void delete(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 获取删除用户信息的编号
			String id = request.getParameter("id");
		    int uid = Integer.parseInt(id);

			int row = standDao.deleteStandById(uid);

			if (row > 0) {
				response.sendRedirect("StandServlet?command=List");
			}
		}
		    
		 
		private void update(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			// 获取查询用户信息的编号
			
			String id = request.getParameter("id");
			int uid = Integer.parseInt(id);
        StandEntity sta= standDao.getstandById(uid);
        request.setAttribute("sta", sta);
        
   request.getRequestDispatcher("WEB-INF/view/Stand/update.jsp").forward(request, response) ;
		}
		
		  
		private void last(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 设置编码格式
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");

			PrintWriter out = response.getWriter();

	//获取前台传来的参数
			
			String  title= request.getParameter("title");
		 	String  content= request.getParameter("content");
		 	String   receiver_user_ids=request.getParameter("receiver_user_ids");
		 	String  expire_time= request.getParameter("expire_time");
	     	int state=Integer.parseInt(request.getParameter("state"));
	     	int id= Integer.parseInt(request.getParameter("id"));
	     	
	     	StandEntity  stand= new  StandEntity();
		    stand.setTitle(title);
		    stand.setContent(content);
		    stand.setReceiver_user_ids(receiver_user_ids);
		    stand.setExpire_time(expire_time);
		    stand.setState(state);
		    stand.setId(id);
	// 调用userDao中添加用户信息的方法
	   
	   int row =  standDao.updateUser(stand);
		// 判断操作结果
		if (row > 0) {
			response.sendRedirect("StandServlet?command=List");
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

			int row = standDao.update(id, state);


		    PrintWriter  printWriter  =response.getWriter();
			try {
			 	printWriter.print(row);
			} finally {
				printWriter.close();
			}
		}
		 
		/**
		 * 标题的唯一验证
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void validateClass(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			//获取参数
			String standtitle = request.getParameter("standtitle");
			String titleId = request.getParameter("titleId");
			
			int rows = standDao.validateClass(standtitle,titleId);
			
			if(rows > 0){
				//该标题已经存在
				response.getWriter().print(true);
			}else{
				//该标题可用
				response.getWriter().print(false);
			}
	     }
         
		/**
		 * 站内信息查询
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void details(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			//h获取前台传来的 I
			//调用义务逻
			String id = request.getParameter("id");
			int uid = Integer.parseInt(id);
           StandEntity sta= standDao.getById(uid);
           SysUserEntity use=standDao.getUser(sta.getSender_id());
         String ids=sta.getReceiver_user_ids();
         String[] userId=ids.split(",");
			List<SysUserEntity> useList=standDao.getUsers(userId);
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<useList.size();i++){
				if(i==useList.size()-1){
					 sb.append(useList.get(i).getName());
				}
				else{
					sb.append(useList.get(i).getName()+",");	
				}
				
			}
			sta.setReceiver_user_name(sb.toString());
         request.setAttribute("sta", sta);
			request.setAttribute("use", use);
	         request.getRequestDispatcher("WEB-INF/view/Stand/details.jsp").forward(request, response);
		}	
		 

		private void truedelete(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {

			String id = request.getParameter("id");
			int uid = Integer.parseInt(id);
           StandEntity sta= standDao.getById(uid);
           
             request.setAttribute("sta", sta);
	         request.getRequestDispatcher("WEB-INF/view/Stand/delete.jsp").forward(request, response);
		}	
		 
			
		

}


