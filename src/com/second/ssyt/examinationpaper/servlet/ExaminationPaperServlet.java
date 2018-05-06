package com.second.ssyt.examinationpaper.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.second.ssyt.common.PageModel;
import com.second.ssyt.examinationpaper.dao.ExaminationPaperDao;
import com.second.ssyt.examinationpaper.entity.ExaminationPaperEntity;

@WebServlet("/ExaminationPaperServlet")
public class ExaminationPaperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ExaminationPaperDao examinationPaperDao = new ExaminationPaperDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		if("list".equals(command)){			
		list(request,response);		
		}else
			request.getRequestDispatcher("/WEB-INF/view/common/error.jsp");
		
	}



	private void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
				int pageNo = 1;
				int pageSize = 1;
				/**
				 * 判断前台传来的参数，如果不为空，就放入实�?
				 */
				String pageNoStr = request.getParameter("pageNo");
				if (StringUtils.isNotBlank(pageNoStr)) {
					pageNo = Integer.parseInt(pageNoStr);
				}				
				String name = request.getParameter("name");
				
				int state = 1;
				String stateStr = request.getParameter("state");
				if (StringUtils.isNotBlank(stateStr)) {
					state = Integer.parseInt(stateStr);
				}
				ExaminationPaperEntity examinationPaperEntity = new ExaminationPaperEntity();
				examinationPaperEntity.setName(name);
				examinationPaperEntity.setState(state);
			request.setAttribute("examinationPaperEntity", examinationPaperEntity);
			/**
			 * 将实体传入方法，方法得到pageModel
			 */				
			PageModel<ExaminationPaperEntity> pageModel = ExaminationPaperDao.list(examinationPaperEntity, pageSize, pageNo);
				/**
				 * 得到实体pageModel，并封装进request，最后转�?
				 */
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("/WEB-INF/view/examinationpaper/examinationPaper.jsp").forward(
				request, response);
	}
	
    
	

}
