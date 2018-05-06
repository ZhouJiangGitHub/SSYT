package com.second.ssyt.student.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.second.ssyt.common.Constant;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.common.util.DateUtil;
import com.second.ssyt.exam.entity.ExamEntity;
import com.second.ssyt.exam.entity.ExamManageEntity;
import com.second.ssyt.examinationpaper.entity.ExaminationPaperEntity;
import com.second.ssyt.login.entity.UserEntity;
import com.second.ssyt.question.entity.QuestionEntity;
import com.second.ssyt.student.dao.StudentDao;
import com.second.ssyt.student.entity.StudentEntity;
import com.second.ssyt.sys.dao.SysDao;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int examPlanId = 0;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//防乱码
		request.setCharacterEncoding(Constant.ENCODING_UTF8);
		String command = request.getParameter("command");
		if ("list".equals(command)) {
			List(request,response);
		}else if ("producePaper".equals(command)) {
			producePaper(request,response);
		}else if ("getAnswer".equals(command)) {
			getAnswer(request,response);
		}else if ("addScore".equals(command)) {
			addScore(request,response);
		}else if ("listStudentByPage".equals(command)) {
			listStudentByPage(request,response);
		}else if ("listStudentVerifyByPage".equals(command)) {
			listStudentVerifyByPage(request,response);
		}else if ("error_questions".equals(command)) {
			errorQuestions(request,response);
		}else{
			
			request.getRequestDispatcher("/WEB-INF/view/common/error.jsp");
		}
		
	}
	private void errorQuestions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PageModel<QuestionEntity> pageModel = new PageModel<>();
		
		if(request.getParameter("pageNo")!=null){
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageModel.setPageNo(pageNo);
		}
		System.out.println("pageNo="+pageModel.getPageNo());
		if(request.getParameter("pageSize")!=null){
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			pageModel.setPageNo(pageSize);
		}
		
		//获取前台参数
		String questionName = request.getParameter("questionName");
		//转为数字 如："1","2"
		String questionType = request.getParameter("questionType");
		questionType = positiveConvertQuestionType(questionType);
		
		String difficulty = request.getParameter("difficulty");
		difficulty = positiveConvertDifficulty(difficulty);
		
		//获取当前用户Id
		UserEntity user = ((UserEntity)request.getSession().getAttribute("user"));
		int id = user.getId();
		String userId = id+""; 
		
		
//		System.out.println("questionName="+questionName);
//		System.out.println("questionType="+questionType);
//		System.out.println("difficulty="+difficulty);
		
		//查询当前用户所有错题
		pageModel = StudentDao.getAllErrorQuestions(questionName,questionType,difficulty,userId,pageModel);
		
		
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("questionName", questionName);
		request.setAttribute("questionType", questionType);
		request.setAttribute("difficulty", difficulty);
		
		
		request.getRequestDispatcher("/WEB-INF/view/student/errorquestions.jsp").forward(request, response);
	}

	/**
	 * 将试题类型转为数字
	 * @param questionType
	 * @return
	 */
	private String positiveConvertQuestionType(String questionType){
		
		if("-全部-".equals(questionType)){
			return null;
		}else if("单选题".equals(questionType)){
			return "1";
		}else if("多选题".equals(questionType)){
			return "2";
		}else if("判断题".equals(questionType)){
			return "3";
		}else{
			return questionType;
		}
	}
	
	/**
	 * 将试题难度转为数字
	 * @param difficulty
	 * @return
	 */
	private String positiveConvertDifficulty(String difficulty){
		
		if("-全部-".equals(difficulty)){
			return null;
		}else if("低".equals(difficulty)){
			return "1";
		}else if("中".equals(difficulty)){
			return "2";
		}else if("较高".equals(difficulty)){
			return "3";
		}else if("高".equals(difficulty)){
			return "4";
		}else{
			return difficulty;
		}
	}
	
	/**
	 * 将试题类型转为中文
	 * @param questionType
	 * @return
	 */
	private String directionConvertQuestionType(String questionType){

		if("1".equals(questionType)){
			return "单选题";
		}else if("2".equals(questionType)){
			return "多选题";
		}else if("3".equals(questionType)){
			return "判断题";
		}else{
			return "-全部-";
		}
	}
	/**
	 * 将试题难度转为中文
	 * @param difficulty
	 * @return
	 */
	private String directionConvertDifficulty(String difficulty){
		
		if("1".equals(difficulty)){
			return "低";
		}else if("2".equals(difficulty)){
			return "中";
		}else if("3".equals(difficulty)){
			return "较高";
		}else if("4".equals(difficulty)){
			return "高";
		}else{
			return "-全部-";
		}
	}

	/**
	 * 添加考试成绩与结果
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addScore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ExamEntity  exam = new ExamEntity();
		
		//1.获取前台参数
		//int examPlanId = Integer.parseInt(request.getParameter("examPlanId"));
		int totalScore = Integer.parseInt(request.getParameter("totalScore"));
		
		ExamManageEntity examManage = StudentDao.getExamManage(examPlanId);
		
		UserEntity user = (UserEntity)request.getSession().getAttribute("user");
		exam.setUser_id(user.getId());
		exam.setSys_class_name(examManage.getClassName());
		exam.setQes_exam_paper_name(examManage.getPaperName());
		exam.setStart_time(DateUtil.format(examManage.getExam_time_start()));
		exam.setSubmit_time(DateUtil.format(new Date()));
		exam.setTotal_point(100);
		exam.setExam_plan_id(examPlanId);
		exam.setGet_point(totalScore);
		
		if(totalScore >= 60){
			exam.setIs_pass(1);
		}else{
			exam.setIs_pass(2);
		}
		
		
	
	 	

	   int row = StudentDao.add(exam);
	   if(row>0){
		   
		   request.getRequestDispatcher("ExamServlet?command=List").forward(request, response);
	   }
	}


	/**
	 * 获取正确答案
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getAnswer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取前台参数
		
		String paperId = request.getParameter("paperId");
		
		//用于存储错题信息
		List<List<Object>> errorQuestions = new ArrayList<>();
		
		int userId = ((UserEntity)request.getSession().getAttribute("user")).getId();
		
		//String ExamManageEntity=request.getParameter("ExamManageEntity");
    	//int examPlanId = Integer.parseInt(request.getParameter("examPlanId"));
		// 调用通过试卷编号获取试卷信息的方法
		Map<String,Object> paperinfo = StudentDao.getExamInfos(paperId);
		// 获取每种题型每题分数
		int choiceEachPoint = Integer.valueOf(paperinfo.get("single_option_each_point")+"");
		int multiEachPoint = Integer.valueOf(paperinfo.get("multi_option_each_point")+"");
		int judgeEachPoint = Integer.valueOf(paperinfo.get("judge_each_point")+"");
		
		// 获取所有的试题编号
		String[] subjectIds = request.getParameterValues("examId");
	
		
		// 定义选择题（单选，多选）正确数，判断题正确数
		int choiceCount = 0;
		int multiCount = 0;
		int judgeCount = 0;
		
		for (String examId : subjectIds)  {
			
			List<Object> list = new ArrayList<>();
			// 获取每题的正确答案
			String answer = StudentDao.getAnswerByExamId(examId).get("answer")+"";
			
			// 获取每题考生提交试题答案[A,B,C],[A]
			String[] answers = request.getParameterValues(examId);
			String studentAnswer = "";
			
			if(answers!=null && answers.length>0) {
				
				// 存储试题的类型名称
				String subjectTypeName = "";
				
				// 组合学生提交试题答案
				for (String value : answers) {
					// 把答案和题目类型分割出来
					String[] val = value.split("-");
					subjectTypeName = val[1];
					studentAnswer+=val[0];
				}
				
				// 根据不同的题型计算学生正确答题数
				if (subjectTypeName.equals("choice")) {
					if (answer.equals(studentAnswer)) {
						choiceCount++;
					}else{
						list.add(examId);
						if(StringUtils.isBlank(studentAnswer)){
							studentAnswer="没选";
						}
						list.add(studentAnswer);
						list.add(userId);
					}
					
				} else if (subjectTypeName.equals("multi")) {
					if (answer.equals(studentAnswer)) {
						multiCount++;
					}else{
						list.add(examId);
						if(StringUtils.isBlank(studentAnswer)){
							studentAnswer="没选";
						}
						list.add(studentAnswer);
						list.add(userId);
					}
					
				} else if (subjectTypeName.equals("judge")) {
					if (answer.equals(studentAnswer)) {
						judgeCount++;
					}else{
						list.add(examId);
						if(StringUtils.isBlank(studentAnswer)){
							studentAnswer="没选";
						}
						list.add(studentAnswer);
						list.add(userId);
					}
				}
			}
			if(list.size() > 0){
				
				errorQuestions.add(list);
			}
			
		}
		
		//存储错题
		StudentDao.setErrorQuestions(errorQuestions);
		
		// 计算总分数
		int totalScore = choiceEachPoint*choiceCount+multiEachPoint*multiCount+judgeEachPoint*judgeCount;
		request.setAttribute("totalScore", totalScore);
		request.getRequestDispatcher("StudentServlet?command=addScore&totalScore="+totalScore).forward(request, response);
		
	}
		



	/**
	 * 生成试卷
	 * producePaper
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
    private void producePaper(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	//获取参数
    	
    	//String ExamManageEntity=request.getParameter("ExamManageEntity");
    	examPlanId = Integer.parseInt(request.getParameter("examPlanId"));
    	Map<String,Object> paper= StudentDao.getExamInfos("1");
		request.setAttribute("paper", paper);
		
		
		
		// 根据试卷信息查询对应试卷试题 
		
		// 获取选择题信息
		List<Map<String,Object>> choiceList = StudentDao.getSubjectByQuestionTypeId("1", paper.get("single_option_number")+"");
		
		// 获取多选题信息
		List<Map<String,Object>> multipleList = StudentDao.getSubjectByQuestionTypeId("2", paper.get("multi_option_number")+"");
		
		// 获取判断题信息
		List<Map<String,Object>> judgeList = StudentDao.getSubjectByQuestionTypeId("3", paper.get("judge_number")+"");
		
		// 把所有试题存储在request对象中
		request.setAttribute("choiceList", choiceList);
		request.setAttribute("multipleList", multipleList);
		request.setAttribute("judgeList", judgeList);
		//request.setAttribute("ExamManageEntity", ExamManageEntity);
		
		
		request.getRequestDispatcher("/WEB-INF/view/student/paper.jsp?paperId=1").forward(request, response);
		
	}


	/**
     * 条件查询
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    private void listStudentVerifyByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///吧查询条件放到Student实体
    	StudentEntity findStudent = new StudentEntity();
    	String Code = request.getParameter("Code");
		String Name = request.getParameter("Name");
		if (StringUtils.isNotBlank(Code)) {
			findStudent.setCode(Code);
		}
		if (StringUtils.isNotBlank(Name)) {
			findStudent.setName(Name);
		}
		int pageNo = Constant.DEFAULT_PAGE_NO;
    	//获取前台参数
    	String pageNoStr = request.getParameter("pageNo");
    	if (pageNoStr != null) {
			pageNo = Integer.parseInt(pageNoStr);
		}
    	PageModel<StudentEntity> PageModel = StudentDao.listStudentVerifyByPage(findStudent,pageNo,Constant.DEFAULT_PAGE_SIZE);
    	request.setAttribute("pageModel", PageModel);
    	request.setAttribute("findStudent", findStudent);
    	 //转向
    	request.getRequestDispatcher("/WEB-INF/view/student/index.jsp").forward(request, response);
	   
		
	}


	/**
     * 分页查询学生信息
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    private void listStudentByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int pageNo = Constant.DEFAULT_PAGE_NO;
    	//获取前台参数
    	String pageNoStr = request.getParameter("pageNo");
    	if (pageNoStr != null) {
			pageNo = Integer.parseInt(pageNoStr);
		}
    	//调用逻辑
    	 PageModel<StudentEntity> PageModel = StudentDao.listStudentByPage(pageNo,Constant.DEFAULT_PAGE_SIZE);
    	 request.setAttribute("pageModel", PageModel);
    	 //转向
    	 request.getRequestDispatcher("/WEB-INF/view/student/index.jsp").forward(request, response);
	}


	/**
     * 学生信息查询
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
	private void List(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1获取前台参数
		//2调用逻辑
		UserEntity user = ((UserEntity)request.getSession().getAttribute("user"));
		String opreadname = user.getName();
		List<ExamManageEntity> ExamManageEntity = StudentDao.list();
		List<ExaminationPaperEntity> ExaminationPaperEntity= SysDao.ExaminationPaperName();
		List<ExamManageEntity> examManageEntityList = new ArrayList<>();
		String userId = user.getId()+"";
		int classId = user.getClassId();
		for(ExamManageEntity examManageEntity:ExamManageEntity){
			String[] ids = examManageEntity.getTo_user_ids().split(",");
			int clazzId = examManageEntity.getTo_class_id();
			
			for(int i = 0;i < ids.length;i++){
				if(ids[i].equals(userId) || clazzId == classId){
					examManageEntityList.add(examManageEntity);
					break;
				}
			}
			
		}

		
		request.setAttribute("opreadname", opreadname);
		request.setAttribute("ExamManageEntity", examManageEntityList);
		request.setAttribute("ExaminationPaperEntity", ExaminationPaperEntity);
		//3转向
		request.getRequestDispatcher("/WEB-INF/view/student/index.jsp").forward(request, response);	
		
	}
	
}
