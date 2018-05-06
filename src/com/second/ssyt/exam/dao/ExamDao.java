package com.second.ssyt.exam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.second.ssyt.common.PageModel;
import com.second.ssyt.common.util.DBUtil;
import com.second.ssyt.exam.entity.ExamEntity;

public class ExamDao {
	public int  add(ExamEntity exam){
		if(exam==null){
			throw  new NullPointerException();
		}
		Connection  connection= null;
		PreparedStatement  preparedStatement=null;
		try{
		connection = DBUtil.getConnection();
		String sql="INSERT INTO exam_records values(null,?,?,?,NOW(),NOW(),?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,exam.getUser_id());
		preparedStatement.setInt(2,exam.getExam_plan_id());
		preparedStatement.setInt(3,exam.getGet_point());
		//preparedStatement.setString(4,exam.getSubmit_time());
		//System.out.println(exam.getSubmit_time());
		//preparedStatement.setString(5,exam.getStart_time());
		preparedStatement.setInt(4,exam.getIs_pass());
	
		
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
	 * 条件，分页 查询
	 * @param user
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public PageModel<ExamEntity> list(ExamEntity  examEntity, int pageSize, int pageNo) {
		
		List<ExamEntity> examList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// 1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2、连接数据库
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yec", "root", "123");

			// 3、操作数据库
			List<Object> paramList = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT r.start_time, r.submit_time ,r.is_pass,r.submit_time, ");             
			sb.append("r.get_point,u.name,   ");                                        
			sb.append("c.name as 'sys_class_name' , q.name as 'qes_exam_paper_name',  ");
		    sb.append("q.total_point FROM qes_exam_paper q,exam_records r,     ");      
			sb.append("exam_plan p,sys_user u,sys_class c    ");                        
			sb.append("WHERE r.exam_plan_id=p.id ");
			sb.append("AND r.user_id=u.id  "); 
			sb.append("AND u.operate_user_id = p.operate_user_id ");
			sb.append("AND c.id=p.to_class_id   "); 
			if (StringUtils.isNotBlank(examEntity.getName())){
				sb.append("AND u.name   LIKE ? ");
				paramList.add("%" + examEntity.getName() + "%");
			}
			sb.append("LIMIT ?,? ");
			paramList.add((pageNo - 1) * pageSize);
			paramList.add(pageSize);
			preparedStatement = connection.prepareStatement(sb.toString());
			
				for (int i = 0; i < paramList.size(); i++) {
					preparedStatement.setObject(i + 1, paramList.get(i));
				}
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				 ExamEntity  exam = new ExamEntity();
				   exam.setName(resultSet.getString("name"));
				   exam.setSys_class_name(resultSet.getString("sys_class_name"));
				   exam.setQes_exam_paper_name(resultSet.getString("qes_exam_paper_name"));
				   exam.setStart_time(resultSet.getString("start_time"));
				   exam.setSubmit_time(resultSet.getString("submit_time"));
				   exam.setTotal_point(resultSet.getInt("total_point"));
				   exam.setGet_point(resultSet.getInt("get_point"));
				   exam.setIs_pass(resultSet.getInt("is_pass"));
				   examList.add(exam);	 
			}
			// 查询总记录
			List<Object> paramList2 = new ArrayList<>();
			StringBuilder sb2 = new StringBuilder();
			sb2.append("SELECT COUNT(*) AS 'count' ");
			sb2.append("FROM qes_exam_paper q, exam_records r ,  ");
			sb2.append("exam_plan p, sys_user u,sys_class c  ");
			sb2.append("WHERE r.user_id=u.operate_user_id AND r.exam_plan_id=p.id  ");
			sb2.append("AND p.exam_paper_id=q.id ");
			sb2.append("AND p.operate_user_id=u.operate_user_id AND u.class_id=c.id  ");
			sb2.append("AND c.operate_user_id=u.operate_user_id  ");
			if (StringUtils.isNotBlank(examEntity.getName())) {
				sb2.append("AND u.name LIKE ? ");
				paramList2.add("%" + examEntity.getName()+ "%");
			}
			
			preparedStatement = connection.prepareStatement(sb2.toString());
			if (paramList2 != null) {
				for (int i = 0; i < paramList2.size(); i++) {
					preparedStatement.setObject(i + 1, paramList2.get(i));
				}
			}

			resultSet = preparedStatement.executeQuery();
			int count = 0;
			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}

			return new PageModel<ExamEntity>(examList, pageNo, pageSize, count);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			// userList = new ArrayList<>();
			examList = Collections.emptyList();
		} finally {// 4、关闭资源
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	

/**
 * 分页查询班级（包括总记录数）
 * 
 * @param pageModel
 * @return
 */
	/*public PageModel<ExamEntity> list(ExamEntity examEntity, int pageSize,
			int pageNo) {
		List<ExamEntity> examList = new ArrayList<>();

			// 3、操作数据库Exam
			List<Object> paramList = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			String sql="SELECT r.start_time, r.submit_time ,r.is_pass,r.get_point,u.name as 'sys_user_name', "
					+ "c.name as 'sys_class_name' , q.name as 'qes_exam_paper_name', q.total_point FROM qes_exam_paper q,exam_records r,"
					+ "exam_plan p,sys_user u,sys_class c WHERE r.user_id=u.operate_user_id AND r.exam_plan_id=p.id AND p.exam_paper_id=q.id "
					+ "AND p.operate_user_id=u.operate_user_id AND u.class_id=c.id AND c.operate_user_id=u.operate_user_id ";
			sb.append(sql);
			if (StringUtils.isNotBlank(examEntity.getSys_user_name())){
				sb.append("AND u.name as sys_user_name LIKE ? ");
				paramList.add("%" + examEntity.getSys_user_name() + "%");
			}
			sb.append("ORDER BY r.id ");
			sb.append("LIMIT ?,? ");
			paramList.add((pageNo - 1) * pageSize);
			paramList.add(pageSize);
			System.out.println(sb.toString());
			for(Object value:paramList){
				System.out.println(value);
			}
			return DBUtil.executeQueryByPage(ExamEntity.class, pageNo, pageSize, sb.toString(), paramList);
			
	}		*/
	

}