package com.second.ssyt.question.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.second.ssyt.common.Constant;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.common.util.DateUtil;
import com.second.ssyt.login.entity.UserEntity;
import com.second.ssyt.question.dao.QuestionDao;
import com.second.ssyt.question.entity.QuestionEntity;

@MultipartConfig
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       QuestionDao questionDao =new QuestionDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	         //防乱码
			request.setCharacterEncoding(Constant.ENCODING_UTF8);
			response.setContentType("text/html,UTF-8");
			String command = request.getParameter("command");
			 if ("list".equals(command)) {
				try {
					list(request,response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else if("listPage".equals(command)){
				listPage(request,response);
			}else if("listParameterPage".equals(command)){
				listParameterPage(request,response);
			}else if("updateState".equals(command)){
				updateState(request,response);
			}else if("details".equals(command)){
				details(request,response);
			}else if("add".equals(command)){
				add(request,response);
			}else if("addTest".equals(command)){
				addTest(request,response);
			}else if("updateMessageTest".equals(command)){
				updateMessageTest(request,response);
			}else if("updateMessage".equals(command)){
				updateMessage(request,response);
			}else if("getMessage".equals(command)){
				getMessage(request,response);
			}else if("file".equals(command)){
				setQuestions(request,response);
			}else{
				throw new IllegalStateException("请求非法");
			}
	}
    /**
     * Excel导入试题
     * 
     * @param request
     * @param response
     * @throws ServletException 
     * @throws IOException 
     */
    private void setQuestions(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
    		
    	//用于存放接收的所有试题
    	List<List<Object>> questionList = new ArrayList<>();
    	
    	Part part = request.getPart("excelFile");
    	
    	String headerString = part.getHeader("Content-Disposition");
    	
    	InputStream is = part.getInputStream();
		
		Workbook workbook = null;
		if(headerString.endsWith(".xls\"")){
			workbook = new HSSFWorkbook(is);
		}
		if(headerString.endsWith(".xlsx\"")){
			workbook = new XSSFWorkbook(is);
		}
		
		int count = workbook.getNumberOfSheets();
		for(int i =0;i<count;i++){
			
			Sheet sheet = workbook.getSheetAt(i);
			
			// 获取当前遍历sheet的名称
			String sheetName = sheet.getSheetName();
			
			if(sheetName.equals("qes_qestions")) {
			
				Iterator<Row> iterator = sheet.iterator();
				
				while(iterator.hasNext()){
					Row row = iterator.next();
					//System.out.println(row.getPhysicalNumberOfCells());
					List<Object> list = new ArrayList<>();
					
					for(int j = 1;j < 17;j++){
						//设置Cell的类型，把纯数字作为String类型读进来了
						if(row.getCell(j)!=null){
					          if(j==14){

					        	  list.add(DateUtil.format(row.getCell(j).getDateCellValue()));
					        	  
					        	  
					          }else{
					        	  row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
					        	  
					        	  list.add(row.getCell(j).getStringCellValue());
					          }
					     }else{
					    	 list.add("");
					     }
						
					}
					
					/*list.add(row.getCell(1).getStringCellValue());
					list.add(row.getCell(2).getStringCellValue());
					row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					list.add(row.getCell(3).getStringCellValue());
					list.add(row.getCell(4).getStringCellValue());
					list.add(row.getCell(5).getStringCellValue());
					list.add(row.getCell(6).getStringCellValue());
					list.add(row.getCell(7).getStringCellValue());
					list.add(row.getCell(8).getStringCellValue());
					list.add(row.getCell(9).getStringCellValue());
					list.add(row.getCell(10).getStringCellValue());
					list.add(row.getCell(11).getStringCellValue());
					list.add(row.getCell(12).getStringCellValue());
					list.add(row.getCell(13).getStringCellValue());
					list.add(row.getCell(14).getStringCellValue());
					list.add(row.getCell(15).getStringCellValue());
					list.add(row.getCell(16).getStringCellValue());*/
					
					
					
					questionList.add(list);
				}
			}
		}
		System.out.println("总题数"+questionList.size());
		//调用dao层insertQuesionForBatch（）添加试题
		questionDao.insertQuesionForBatch(questionList);
		
		request.getRequestDispatcher("QuestionServlet?command=listPage").forward(request, response);
		
	}

	/**
     * 获取需要修改的信息
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
	private void getMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取前台参数
		int id = Integer.parseInt(request.getParameter("id"));
		//调用逻辑
		QuestionEntity questionEntity = questionDao.getMessage(id);
		request.setAttribute("questionEntity", questionEntity);
		//转向
		request.getRequestDispatcher("WEB-INF/view/questions/update.jsp").forward(request, response);
		
	}

	/**
	 * 试题修改
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));	
        String question = request.getParameter("question");
        String attachment = request.getParameter("attachment");
        Short questionType = (short) Integer.parseInt(request.getParameter("questionType"));
        //String sysCourse$Name = request.getParameter("sysCourse$Name");
        String answerA = request.getParameter("answerA");
        String answerB = request.getParameter("answerB");
        String answerC = request.getParameter("answerC");
        String answerD = request.getParameter("answerD");
        String answer = request.getParameter("answer");
        Short difficulty = (short) Integer.parseInt(request.getParameter("difficulty"));
        String analysis = request.getParameter("analysis");
        String keywords = request.getParameter("keywords");
        Short state = (short) Integer.parseInt(request.getParameter("state"));
        String memo = request.getParameter("memo");
        QuestionEntity questionE= new QuestionEntity();
        questionE.setId(id);
        questionE.setQuestion(question);
        questionE.setQuestionType(questionType);
        questionE.setAttachment(attachment);
        //questionEntity.setSysCourse$Name(sysCourse$Name);
        questionE.setAnswerA(answerA);
        questionE.setAnswerB(answerB);
        questionE.setAnswerC(answerC);
        questionE.setAnswerD(answerD);
        questionE.setAnswer(answer);
        questionE.setDifficulty(difficulty);
        questionE.setAnalysis(analysis);
        questionE.setKeywords(keywords);
        questionE.setState(state);
        questionE.setMemo(memo);
        questionDao.updateMessage(id,questionE);
        request.setAttribute("questionEntity", questionE);
        request.getRequestDispatcher("QuestionServlet?command=listPage").forward(request, response);
	}

	/**
	 * 修改
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateMessageTest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WebContent/WEB-INF/view/questions/update.jsp").forward(request, response);
		 
	}

	/**
	 * 条件查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void listParameterPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//条件查询条件
		int pageNo = 1;
		int pageSize = 10;
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		QuestionEntity questionEn = new QuestionEntity();
		String question = request.getParameter("question");
		System.out.println("question"+question);
		if (StringUtils.isNotBlank(question)) {
			questionEn.setQuestion(question);
		}
		Short questionType =0;
		String questionTypeStr =request.getParameter("questionType");
		if (StringUtils.isNotBlank(questionTypeStr)) {
			 questionType = (short) Integer.parseInt(questionTypeStr) ;
			questionEn.setQuestionType(questionType);
		}
		Short courseId =0;
		String courseIdStr =request.getParameter("courseId");
		if (StringUtils.isNotBlank(courseIdStr)) {
			 courseId = (short) Integer.parseInt(courseIdStr) ;
			questionEn.setQuestionType(courseId);
		}
		Short state =0;
		String stateIdStr =request.getParameter("state");
		if (StringUtils.isNotBlank(stateIdStr)) {
			 state = (short) Integer.parseInt(stateIdStr) ;
			questionEn.setState(state);
		}
		Short difficulty =0;
		String difficultyStr =request.getParameter("difficulty");
		if (StringUtils.isNotBlank(difficultyStr)) {
			 difficulty = (short) Integer.parseInt(difficultyStr) ;
			questionEn.setDifficulty(difficulty);
		}
		//List<QuestionEntity> questionList = questionDao.listParameterPage(questionEn);
		PageModel<QuestionEntity> pageModel = questionDao.listParameterPage(pageSize, pageNo,questionEn);
		request.setAttribute("questionEn", questionEn);
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("WEB-INF/view/questions/indexPage.jsp").forward(request, response);
	}

	/**
	 * 添加跳转页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addTest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//从session中获取当前用户id
			UserEntity user = ((UserEntity)request.getSession().getAttribute("user"));
		     int opread_user_id = user.getId();
		//把id传带前台
		request.setAttribute("opread_user_id", opread_user_id);
		request.getRequestDispatcher("WEB-INF/view/questions/add.jsp").forward(request, response);		
	}

	/**
	 * 题目新增
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String question = request.getParameter("question");
		String attachment = request.getParameter("attachment");
		Short questionType = 1;
		String questionTypeStr=request.getParameter("question_type");
		if(questionTypeStr != null && questionTypeStr.length()>0){
		questionType = (short) Integer.parseInt(questionTypeStr);
		}
		//String sysCourse$Name = request.getParameter("sysCourse$Name");
		String answerA= request.getParameter("answer_a");
		String answerB = request.getParameter("answer_b");
    	String answerC = request.getParameter("answer_c");
    	String answerD = request.getParameter("answer_d");
    	String answer = request.getParameter("answer");
    	Short difficulty=1;
    	 String difficultyStr = request.getParameter("difficulty");
    	if(difficultyStr != null && difficultyStr.length()>0){
    		difficulty = (short) Integer.parseInt(difficultyStr);
    	}
    	String analysis = request.getParameter("analysis");
    	String keywords = request.getParameter("keywords");
    	String memo = request.getParameter("memo");
    	QuestionEntity questionEntity = new QuestionEntity();
    	questionEntity.setQuestion(question);
    	questionEntity.setAttachment(attachment);
    	questionEntity.setQuestionType(questionType);
    	//questionEntity.setSysCourse$Name(sysCourse$Name);
    	questionEntity.setAnswerA(answerA);
    	questionEntity.setAnswerB(answerB);
    	questionEntity.setAnswerC(answerC);
    	questionEntity.setAnswerD(answerD);
    	questionEntity.setAnswer(answer);
    	questionEntity.setDifficulty(difficulty);
    	questionEntity.setAnalysis(analysis);
    	questionEntity.setKeywords(keywords);
    	questionEntity.setMemo(memo);
    	questionDao.add1(questionEntity);
    	request.getRequestDispatcher("QuestionServlet?command=listPage").forward(request, response);
	}

	/**
	 *
	 * 详情  
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void details(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//接受前台传来的参数
		   int  id = Integer.parseInt(request.getParameter("id"));
			  List<QuestionEntity> questionList = questionDao.details(id);
			  request.setAttribute("questionList", questionList);
				request.getRequestDispatcher("WEB-INF/view/questions/details.jsp").forward(request, response);
	
	}
	/**
	 * 分页
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void listPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、接收参数
				int pageNo = 1;
				int pageSize = 10;
				String pageNoStr = request.getParameter("pageNo");
				if (pageNoStr != null) {
					pageNo = Integer.parseInt(pageNoStr);
				}
		PageModel<QuestionEntity> pageModel = questionDao.listPage(pageSize, pageNo);
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("WEB-INF/view/questions/indexPage.jsp").forward(request, response);
	}
     
	/**
	 * 状态更改
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updateState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int state = Integer.parseInt(request.getParameter("state"));

		int row = questionDao.update(id, state);

		PrintWriter printWriter = response.getWriter();
		try {
			printWriter.print(row);
		} finally {
			printWriter.close();
		}
	}
	/**
	 * 普通查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<QuestionEntity> QesQestionList = questionDao.list();
		request.setAttribute("QesQestionList", QesQestionList);
		
		request.getRequestDispatcher("WEB-INF/view/questions/index.jsp").forward(request, response);
	}

}
