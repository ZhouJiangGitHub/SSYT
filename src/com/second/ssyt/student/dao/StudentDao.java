package com.second.ssyt.student.dao;

import com.second.ssyt.common.Constant;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.common.exception.CommonRuntimeException;
import com.second.ssyt.common.exception.DaoRuntimeException;
import com.second.ssyt.common.util.DBUtil;
import com.second.ssyt.common.util.LogUtil;
import com.second.ssyt.exam.entity.ExamEntity;
import com.second.ssyt.exam.entity.ExamManageEntity;
import com.second.ssyt.question.entity.QuestionEntity;
import com.second.ssyt.student.entity.StudentEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDao {
	/**
	 * 学生信息查询
	 * 
	 * @return
	 */
	public static List<ExamManageEntity> list() {
		String sql = "select *  from  exam_plan";
		//用次Butil
		List<ExamManageEntity> studentList = DBUtil.executeQuery(
				ExamManageEntity.class, sql);
		return studentList;
	}

	/**
	 * 分页查询学生信息
	 * 
	 * @param pageNo
	 * @param defaultPageSize
	 * @return
	 */
	public static PageModel<StudentEntity> listStudentByPage(int pageNo,
			int PageSize) {
		String sql = "SELECT * FROM  exam_plan LIMIT ?,?";
		/*
		 * PageModel<StudentEntity> PageModel =
		 * DBUtil.executeQueryByPage(StudentEntity.class, pageNo,
		 * defaultPageSize, sql);
		 */
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, (pageNo - 1) * PageSize);
			preparedStatement.setInt(2, PageSize);
			resultSet = preparedStatement.executeQuery();
			List<StudentEntity> StudentList = new ArrayList<>();
			while (resultSet.next()) {
				StudentEntity studentlist = new StudentEntity();
				studentlist.setId(resultSet.getInt("id"));
				studentlist.setCode(resultSet.getString("code"));
				studentlist.setPassword(resultSet.getString("password"));
				studentlist.setName(resultSet.getString("name"));
				studentlist.setPhone(resultSet.getString("phone"));
				studentlist.setEmail(resultSet.getString("email"));
				studentlist.setAge(resultSet.getInt("age"));
				studentlist.setGender(resultSet.getInt("gender"));
				studentlist.setHobby(resultSet.getString("hobby"));
				StudentList.add(studentlist);
			}
			// 构建分页实体
			return new PageModel<StudentEntity>(StudentList, pageNo, PageSize,
					getAllcount());

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			DBUtil.close(connection, preparedStatement, resultSet);
		}
	}

	/**
	 * 总数
	 * 
	 * @param talentId
	 * @return
	 */
	private static int getAllcount() {
		String sql = "SELECT COUNT(*) FROM  exam_plan";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			DBUtil.close(connection, preparedStatement, resultSet);
		}
	}

	/**
	 * 条件查询
	 * 
	 * @param findStudent
	 * @param pageNo
	 * @param defaultPageSize
	 * @return
	 */
	public static PageModel<StudentEntity> listStudentVerifyByPage(
			StudentEntity findStudent, int pageNo, int PageSize) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM  student where 1 = 1  ");
		String Code = findStudent.getCode();
		if (StringUtils.isNoneBlank(Code)) {
			sb.append("and code LIKE ?  ");
			paramList.add("%" + findStudent.getCode() + "%");
		}
		
		String Name = findStudent.getName();
		if (StringUtils.isNoneBlank(Name)) {
			sb.append("and name LIKE ?  ");
			paramList.add("%" + findStudent.getName() + "%");
		}
		sb.append("LIMIT ?,? ");
		paramList.add((pageNo - 1) * PageSize);
		paramList.add(PageSize);
		/*
		 * PageModel<StudentEntity> PageModel =
		 * DBUtil.executeQueryByPage(StudentEntity.class, pageNo,
		 * defaultPageSize, sql);
		 */
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sb.toString());
			DBUtil.setParameter(paramList, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			List<StudentEntity> StudentList = new ArrayList<>();
			while (resultSet.next()) {
				StudentEntity studentlist = new StudentEntity();
				studentlist.setId(resultSet.getInt("id"));
				studentlist.setCode(resultSet.getString("code"));
				studentlist.setPassword(resultSet.getString("password"));
				studentlist.setName(resultSet.getString("name"));
				studentlist.setPhone(resultSet.getString("phone"));
				studentlist.setEmail(resultSet.getString("email"));
				studentlist.setAge(resultSet.getInt("age"));
				studentlist.setGender(resultSet.getInt("gender"));
				studentlist.setHobby(resultSet.getString("hobby"));
				StudentList.add(studentlist);
			}
			// 构建分页实体
			return new PageModel<StudentEntity>(StudentList, pageNo, PageSize,
					listStudentVerifyByPageCount(findStudent));
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			DBUtil.close(connection, preparedStatement, resultSet);
		}

	}
    /**
     * 条件查询总数
     * @return
     */
	private static int listStudentVerifyByPageCount( StudentEntity findStudent) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("COUNT(*) ");
		sb.append("FROM  student a where 1 = 1  ");
		String Code = findStudent.getCode();
		if (StringUtils.isNoneBlank(Code)) {
			sb.append("AND a.code LIKE ?  ");
			paramList.add("%" + findStudent.getCode() + "%");
		}
		String Name = findStudent.getName();
		if (StringUtils.isNoneBlank(Name)) {
			sb.append("AND a.name LIKE ?  ");
			paramList.add("%" + findStudent.getName() + "%");
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sb.toString());
			DBUtil.setParameter(paramList, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);						
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			DBUtil.close(connection, preparedStatement, resultSet);
		}
	}
 /**
  * 查询考试信息
  * @param examPlanId
  * @return
  */
 public static Map<String,Object> getExamInfos(String examPlanId) {
		
		List<Map<String,Object>> list = getExamInfoss(examPlanId);
		
		return list!=null && list.size()>0?list.get(0):null;
		
	}
	/**
	 * 查询试题
	 * @param typeId
	 * @param subjectNumber
	 * @return
	 */
	public static List<Map<String,Object>> getSubjectByQuestionTypeId(String typeId,String subjectNumber) {
		return getSubjectByQuestionTypeIds(typeId, subjectNumber);
	}
	/**
	 * 查询答案
	 * @param examId
	 * @return
	 */
	public static Map<String,Object> getAnswerByExamId(String examId) {
		List<Map<String,Object>> list = getAnswerByExamIds(examId);
		
		return list!=null && list.size()>0?list.get(0):null;
	}
	/**
	 * 根据考试安排表中试卷id获取考试试卷信息
	 * @return
	 */
	public static List<Map<String,Object>> getExamInfoss(String examPlanId) {
		
		String sql = "select * from qes_exam_paper where id=?";
		
		return executeQuery(sql, examPlanId);
		
	}
	
	/**
	 * 根据试题类型查询对应类型信息
	 */
	public static List<Map<String,Object>> getSubjectByQuestionTypeIds(String typeId,String subjectNumber) {
		
		String sql = "select * from qes_qestions where question_type=? order by rand() limit ?";
		
		return executeQuery(sql, typeId,Integer.valueOf(subjectNumber));
	}
	/**
	 * 通过试题编号获取正确答案
	 */
	public static List<Map<String,Object>> getAnswerByExamIds(String examId) {
		String sql = "select answer from qes_qestions where id=?";
		
		return executeQuery(sql, examId);
	}
	/**
	 * 执行查询SQL指令操作
	 * @param sql 调用处传入SQL命令
	 * @param parameters 与SQL命令对应的SQL参数数组
	 * @return 返回才查询结果集合
	 */
	public static List<Map<String, Object>> executeQuery(String sql,
			Object... parameters) {
		List<Map<String, Object>> table = new ArrayList<>();

		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement(sql);
			// 判断是否设置参数
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					pst.setObject(i + 1, parameters[i]);
				}
			}

			rs = pst.executeQuery();

			if (rs != null) {
				// 把结果集转换为虚拟表
				ResultSetMetaData rsd = rs.getMetaData();
				// 获取表格中的列数
				int count = rsd.getColumnCount();

				while (rs.next()) {
					// 创建一个存储当前遍历行每个单元格数据的map集合对象
					Map<String, Object> row = new HashMap<>();

					for (int i = 0; i < count; i++) {
						row.put(rsd.getColumnName(i + 1),
								rs.getObject(rsd.getColumnName(i + 1)));
					}

					// 把当前遍历行组成集合对象存储到table集合中
					table.add(row);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pst,rs);
		}

		return table;
	}

	/**
	 * 添加考试记录
	 * 
	 * @param exam
	 * @return
	 */
	public static int add(ExamEntity exam) {


		if(exam==null){
			throw  new NullPointerException();
		}
		Connection  connection= null;
		PreparedStatement  preparedStatement=null;
		try{
		connection = DBUtil.getConnection();
		String sql="INSERT INTO exam_records values(null,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,exam.getUser_id());
		preparedStatement.setInt(2,exam.getExam_plan_id());
		preparedStatement.setInt(3,exam.getGet_point());
		preparedStatement.setString(4,exam.getSubmit_time());
		preparedStatement.setString(5,exam.getStart_time());
		//System.out.println(exam.getSubmit_time());
		preparedStatement.setInt(6,exam.getIs_pass());
	
		
        return preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw  new RuntimeException("数据新增失败");
		}
		
		finally{
			try{
			if(preparedStatement!=null){
				preparedStatement.close();
			}
			if(connection!=null){
				connection.close();
			}
			}catch(SQLException e){
				e.printStackTrace();
			}
				
		}
		
		
	}
	
	/**
	 * 查询考试安排信息
	 * @param examPlanId
	 * @return
	 */
	public static ExamManageEntity getExamManage(int examPlanId){
		
		String sql = "select * from exam_plan where id=?";
		return DBUtil.getUniqueResult(ExamManageEntity.class, sql, examPlanId);
		
	}
	
	/**
	 * 用于存储错题信息
	 * 
	 * @param errorQuestions
	 */
	public static void setErrorQuestions(List<List<Object>> errorQuestions){

		String sql = "insert into error_questions values(null,?,?,?)";
		
		
		for(int i = 0;i < errorQuestions.size();i++){
			
			
			try{
				
				DBUtil.executeUpdate(sql, errorQuestions.get(i));
			}catch(DaoRuntimeException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static PageModel<QuestionEntity> getAllErrorQuestions(String questionName,String questionType,String difficulty,String userId,PageModel<QuestionEntity> pageModel){
		
		
		
		StringBuilder sb = new StringBuilder();
		//用于查询总记录数
		StringBuilder sbStr = new StringBuilder();
		
		List<Object> paramList = new ArrayList<>();
		
		paramList.add(userId);
		
		sb.append("select q.id,q.question,q.answer_a, q.answer_b,q.answer_c,q.answer_d,q.answer,e.error_answer from qes_qestions q, error_questions e where q.id= e.question_id ");
		
		sbStr.append("select count(*) from qes_qestions, error_questions where qes_qestions.id= error_questions.question_id and error_questions.user_id=? ");
		
		sb.append("and e.user_id=? ");
		
		
		if(StringUtils.isNoneBlank(difficulty)){
			
			sb.append("and q.difficulty=? ");
			sbStr.append("and qes_qestions.difficulty=? ");
			paramList.add(difficulty);
		}
		if(StringUtils.isNoneBlank(questionName)){
			sb.append("and q.question like ? ");
			sbStr.append("and qes_qestions.question like ? ");
			paramList.add("%"+questionName+"%");
		}
		if(StringUtils.isNoneBlank(questionType)){
			sb.append("and q.question_type=? ");
			sbStr.append("and qes_qestions.question_type=? ");
			paramList.add(questionType);
		}
		
		sb.append("limit ?,?");
		
		
		paramList.add((pageModel.getPageNo()-1)*pageModel.getPageSize());
		paramList.add(pageModel.getPageSize());
		
		List<QuestionEntity> list = DBUtil.executeQuery(QuestionEntity.class, sb.toString(), paramList);
		
		pageModel.setAllRecords(getCount(sbStr.toString(), paramList));
		
		pageModel.setList(list);
		
		return pageModel;
		
		
	}
	
	/**
	 * 统计总记录数
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int getCount(String sql, List<Object> paramList) {
		Validate.notBlank(sql, "sql" + Constant.VALIDATE_NOT_BLANK);
		
		List<Object> subList = paramList.subList(0, paramList.size() - 2);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			if (subList != null) {
				for (int i = 0; i < subList.size(); i++) {
					preparedStatement.setObject(i + 1, subList.get(i));
				}
			}
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			LogUtil.e(Logger.getLogger(StudentDao.class), "分页时，查询总记录数失败", e);
			throw new CommonRuntimeException();
		} finally {
			DBUtil.close(connection, preparedStatement, resultSet);
		}
	}
}
