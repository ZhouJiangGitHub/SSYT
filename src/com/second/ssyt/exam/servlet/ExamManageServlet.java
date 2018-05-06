package com.second.ssyt.exam.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.second.ssyt.Class.entity.ClassEntity;
import com.second.ssyt.common.Constant;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.exam.dao.ExamManageDao;
import com.second.ssyt.exam.entity.ExamManageEntity;
import com.second.ssyt.examinationpaper.entity.ExaminationPaperEntity;
import com.second.ssyt.login.entity.UserEntity;
import com.second.ssyt.sys.dao.SysDao;


@WebServlet("/ExamManageServlet")
public class ExamManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//防乱码
		request.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.setContentType("text/html;chartset=UTF-8");
		String command = request.getParameter("command");
		 if("change".equals(command)) {
		    	change(request, response);
	       }else if("deleteMnage".equals(command)) {
	    	   deleteMnage(request, response);
	       }else if("getDetailMessege".equals(command)) {
	    	   getDetailMessege(request, response);
	       }else if("getUpdateMessege".equals(command)) {
	    	   getUpdateMessege(request, response);
	       }else if("changManageMessege".equals(command)) {
	    	   try {
				changManageMessege(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	       }else if("addExamManagePage".equals(command)) {
	    	   addExamManagePage(request, response);
	       }else if("addExamManage".equals(command)) {
	    	   try {
				addExamManage(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	       }else if("listExamManageByPage".equals(command)) {
				listExamManageByPage(request, response);
	       }else if ("listExamManageVerifyByPage".equals(command)) {
				listExamManageVerifyByPage(request,response);
		   }else{
             request.getRequestDispatcher("/WEB-INF/view/common/error.jsp");
	    }
	
	}
	 /**
	 * 修改考试安排信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws ParseException 
	 */
	private void changManageMessege(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ParseException {
		  // 1、获取前台传过来的参数
		int id = Integer.parseInt(request.getParameter("Id"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String starttimestr = request.getParameter("starttime");  
		Date starttime = sdf.parse(starttimestr);
		String endtimestr = request.getParameter("endtime");
		Date endtime = sdf.parse(endtimestr);
		String classroom = request.getParameter("classroom");
		int paperName = Integer.parseInt(request.getParameter("paperName"));
		String recetpeople = request.getParameter("recetpeople");
		int classs = Integer.parseInt(request.getParameter("classid"));
		int operate_user =Integer.parseInt(request.getParameter("operate_user"));
		//String operatetimestr = request.getParameter("operatetime");
		//Date operatetime = sdf.parse(operatetimestr);
		int state = Integer.parseInt(request.getParameter("state"));
		String memo = request.getParameter("memo");
		String vrafyPassworf = request.getParameter("vrafyPassworf");
		String kaptchaSessionKey = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		 //验证码
		if (!kaptchaSessionKey.equals(vrafyPassworf)) {
			request.setAttribute("errorInfo", "验证码错误！");
			request.getRequestDispatcher("/WEB-INF/view/system/userAdd.jsp")
					.forward(request, response);
		}
		//String vrafyPassworf = request.getParameter("vrafyPassworf");
		//String kaptchaSessionKey = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 2、调用业务逻辑
		ExamManageEntity addExamManageEntity = new ExamManageEntity();
		addExamManageEntity.setExam_time_start(starttime);
		addExamManageEntity.setExam_time_stop(endtime);
		addExamManageEntity.setExam_classroom(classroom);
		addExamManageEntity.setExam_paper_id(paperName);
		addExamManageEntity.setTo_user_ids(recetpeople);
		addExamManageEntity.setTo_class_id(classs);
		addExamManageEntity.setOperate_user_id(operate_user);
		//addExamManageEntity.setOperate_time(operatetime);
		addExamManageEntity.setState(state);
		addExamManageEntity.setMemo(memo);
		ExamManageDao.updateExamManage(addExamManageEntity,id);
		// 3、转向
		request.getRequestDispatcher("ExamManageServlet?command=listExamManageByPage").forward(
				request, response);
	}


	/**
	 * 获取需要修改的考试安排数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getUpdateMessege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		       // 1、获取前台传过来的参数
		 		int id = Integer.parseInt(request.getParameter("id"));
                 
				// 2、调用业务逻辑
				ExamManageEntity examDetailEntity = ExamManageDao.get(id);
				List<ClassEntity> classNameList= SysDao.getClassName();
				List<ExaminationPaperEntity> ExaminationPaperEntity= SysDao.ExaminationPaperName();
				UserEntity user = ((UserEntity)request.getSession().getAttribute("user"));
				int opreadname = user.getId();
				request.setAttribute("opreadname", opreadname);
				request.setAttribute("classNameList", classNameList);
				request.setAttribute("ExaminationPaperEntity", ExaminationPaperEntity);
				request.setAttribute("examDetailEntity", examDetailEntity);
				// 3、转向				
		        request.getRequestDispatcher("/WEB-INF/view/exam/update.jsp").forward(request,
				response);
		
	}


	/**
	 * 删除改变考试安排状态
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
    private void deleteMnage(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	// 1、调用前台传来的参数
    	int status = Integer.parseInt(request.getParameter("status"));	
    	int id = Integer.parseInt(request.getParameter("Id"));
    	// 2、调用业务逻辑
    	ExamManageDao.change(status,id);	
    	// 3、转向
    	response.sendRedirect("ExamManageServlet?command=listExamManageByPage");
		
	}

	/**
	 * 添加考试安排
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	private void addExamManage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ParseException {
		// 1、获取前台传过来的参数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String starttimestr = request.getParameter("starttime");  
		Date starttime = sdf.parse(starttimestr);
		String endtimestr = request.getParameter("endtime");
		Date endtime = sdf.parse(endtimestr);
		String classroom = request.getParameter("classroom");
		int paperName = Integer.parseInt(request.getParameter("paperName"));
		String recetpeople = request.getParameter("recetpeople");
		int classs = Integer.parseInt(request.getParameter("classid"));
		int operate_user =Integer.parseInt(request.getParameter("operate_user"));
		String operatetimestr = request.getParameter("operatetime");
		//Date operatetime = sdf.parse(operatetimestr);
		int state = Integer.parseInt(request.getParameter("state"));
		String memo = request.getParameter("memo");
		String vrafyPassworf = request.getParameter("vrafyPassworf");
		String kaptchaSessionKey = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		 //验证码
		if (!kaptchaSessionKey.equals(vrafyPassworf)) {
			request.setAttribute("errorInfo", "验证码错误！");
			request.getRequestDispatcher("/WEB-INF/view/system/userAdd.jsp")
					.forward(request, response);
		}
		//String vrafyPassworf = request.getParameter("vrafyPassworf");
		//String kaptchaSessionKey = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 2、调用业务逻辑
		ExamManageEntity addExamManageEntity = new ExamManageEntity();
		addExamManageEntity.setExam_time_start(starttime);
		addExamManageEntity.setExam_time_stop(endtime);
		addExamManageEntity.setExam_classroom(classroom);
		addExamManageEntity.setExam_paper_id(paperName);
		addExamManageEntity.setTo_user_ids(recetpeople);
		addExamManageEntity.setTo_class_id(classs);
		addExamManageEntity.setOperate_user_id(operate_user);
		//addExamManageEntity.setOperate_time(operatetime);
		addExamManageEntity.setState(state);
		addExamManageEntity.setMemo(memo);
		ExamManageDao.addExamManage(addExamManageEntity);
		// 3、转向
		request.getRequestDispatcher("ExamManageServlet?command=listExamManageByPage").forward(
				request, response);
	}


	/**
     * 到添加考试安排
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
	private void addExamManagePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

/*		List<ClassEntity> classList = ExamManageDao.getAllClass();
		
		request.setAttribute("classList", classList);*/
		
		List<ClassEntity> classNameList= SysDao.getClassName();
		List<ExaminationPaperEntity> ExaminationPaperEntity= SysDao.ExaminationPaperName();
		UserEntity user = ((UserEntity)request.getSession().getAttribute("user"));
		int opreadname = user.getId();
		request.setAttribute("opreadname", opreadname);
		request.setAttribute("classNameList", classNameList);
		request.setAttribute("ExaminationPaperEntity", ExaminationPaperEntity);
		request.getRequestDispatcher("/WEB-INF/view/exam/examManageAdd.jsp").forward(request,
				response);
		
	}


	/**
	 * 获取需要修改考试安排数据修改
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getDetailMessege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		       // 1、获取前台传过来的参数
		 		int id = Integer.parseInt(request.getParameter("id"));
                 
				// 2、调用业务逻辑
				ExamManageEntity examDetailEntity = ExamManageDao.getExamManageMessage(id);
				UserEntity user = ((UserEntity)request.getSession().getAttribute("user"));
				int opread_id = user.getId();
				request.setAttribute("opread_id", opread_id);
				request.setAttribute("examDetailEntity", examDetailEntity);
				// 3、转向				
		        request.getRequestDispatcher("/WEB-INF/view/exam/detail.jsp").forward(request,
				response);
		
	}


	/**
	 * 改变考试安排状态
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
    private void change(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	// 1、调用前台传来的参数
    	int status = Integer.parseInt(request.getParameter("status"));	
    	int id = Integer.parseInt(request.getParameter("Id"));
    	// 2、调用业务逻辑
    	ExamManageDao.change(status,id);	
    	// 3、转向
    	response.sendRedirect("ExamManageServlet?command=listExamManageByPage");
		
	}


	/**
     * 条件查询
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    private void listExamManageVerifyByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///吧查询条件放到Student实体
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	//SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
    	ExamManageEntity findSys = new ExamManageEntity();
		String moldstr = request.getParameter("mold");
		String statestr = request.getParameter("state");
		String datestr = request.getParameter("date");
		if (StringUtils.isNotBlank(moldstr)) {
			int mold = Integer.parseInt(moldstr);
			findSys.setExam_paper_id(mold);
		}
		if (StringUtils.isNotBlank(statestr)) {
			int state = Integer.parseInt(statestr);
			findSys.setState(state);
		}
		if (StringUtils.isNotBlank(datestr)) {
			try {
				Date date = sdf.parse(datestr);
				findSys.setExam_time_start(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		int pageNo = Constant.DEFAULT_PAGE_NO;
    	//获取前台参数
    	String pageNoStr = request.getParameter("pageNo");
    	if (pageNoStr != null) {
			pageNo = Integer.parseInt(pageNoStr);
		}
    	PageModel<ExamManageEntity> PageModel = ExamManageDao.listManageVerifyByPage(findSys,pageNo,Constant.DEFAULT_PAGE_SIZE);
    	request.setAttribute("pageModel", PageModel);
    	request.setAttribute("findSys", findSys);
    	 //转向
    	request.getRequestDispatcher("/WEB-INF/view/exam/index.jsp").forward(request, response);
	   
		
	}

	/**
     * 分页查询考试管理信息
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    private void listExamManageByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int pageNo = Constant.DEFAULT_PAGE_NO;
    	//获取前台参数
    	String pageNoStr = request.getParameter("pageNo");
    	if (pageNoStr != null) {
		pageNo = Integer.parseInt(pageNoStr);
		}
    	//调用逻辑
    	 PageModel<ExamManageEntity> PageModel = ExamManageDao.listExamManageByPage(pageNo,Constant.DEFAULT_PAGE_SIZE);
    	 request.setAttribute("pageModel", PageModel);
    	 //转向
    	 request.getRequestDispatcher("/WEB-INF/view/exam/index.jsp").forward(request, response);
	}

 
	
}//servlet
