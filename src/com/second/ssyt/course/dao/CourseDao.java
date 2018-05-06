package com.second.ssyt.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.second.ssyt.common.util.DBUtil;
import com.second.ssyt.course.entity.CourseEntity;


public class CourseDao {

	/**
	 * 鑾峰彇鎵�鏈夌埗鑺傜偣
	 * 
	 * @return
	 */
	public List<CourseEntity> getParentMenus(){
		
		List<CourseEntity> courseList = new ArrayList<>();
		String sql = "select * from sys_course where is_leaf=0";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = DBUtil.getConnection();
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				CourseEntity course = new CourseEntity();
				course.setId(resultSet.getInt("id"));
				course.setPId(resultSet.getInt("pid"));
				course.setIsLeaf(resultSet.getInt("is_leaf"));
				course.setName(resultSet.getString("name"));
				course.setOperateUserId(resultSet.getInt("operate_user_id"));
				course.setOperateTime(resultSet.getDate("operate_time"));
				course.setState(resultSet.getInt("state"));
				course.setMemo(resultSet.getString("memo"));
				
				courseList.add(course);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	
	/**
	 * 鑾峰彇鎵�鏈夊瓙鑺傜偣
	 * 
	 * @return
	 */
	public List<CourseEntity> getChildMenus(){
		
		List<CourseEntity> courseList = new ArrayList<>();
		String sql = "select * from sys_course where is_leaf<>0";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = DBUtil.getConnection();
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				CourseEntity course = new CourseEntity();
				course.setId(resultSet.getInt("id"));
				course.setPId(resultSet.getInt("pid"));
				course.setIsLeaf(resultSet.getInt("is_leaf"));
				course.setName(resultSet.getString("name"));
				course.setOperateUserId(resultSet.getInt("operate_user_id"));
				course.setOperateTime(resultSet.getDate("operate_time"));
				course.setState(resultSet.getInt("state"));
				course.setMemo(resultSet.getString("memo"));
				
				courseList.add(course);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	
	
public List<CourseEntity> getAllCourse(){
		
		List<CourseEntity> courseList = new ArrayList<>();
		String sql = "select * from sys_course";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = DBUtil.getConnection();
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				CourseEntity course = new CourseEntity();
				course.setId(resultSet.getInt("id"));
				course.setPId(resultSet.getInt("pid"));
				course.setIsLeaf(resultSet.getInt("is_leaf"));
				course.setName(resultSet.getString("name"));
				course.setOperateUserId(resultSet.getInt("operate_user_id"));
				course.setOperateTime(resultSet.getDate("operate_time"));
				course.setState(resultSet.getInt("state"));
				course.setMemo(resultSet.getString("memo"));
				
				courseList.add(course);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	


/**
 * 使用Id查询 一个课程实体并返回 
 * 
 * @param id
 * @return
 */
public CourseEntity getCourse(int id){
	
	
		String sql = "select * from sys_course where id = "+id;
		CourseEntity course = new CourseEntity();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = DBUtil.getConnection();
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				course.setId(resultSet.getInt("id"));
				course.setPId(resultSet.getInt("pid"));
				course.setIsLeaf(resultSet.getInt("is_leaf"));
				course.setName(resultSet.getString("name"));
				course.setOperateUserId(resultSet.getInt("operate_user_id"));
				course.setOperateTime(resultSet.getDate("operate_time"));
				course.setState(resultSet.getInt("state"));
				course.setMemo(resultSet.getString("memo"));
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}

	/**
	 * 课程修改
	 * 
	 * @param paramLists
	 * @return
	 */

	public int courseUpdate(List<Object> paramLists){
	


		String sql = "update sys_course set name=?,state=?,memo=?, operate_user_id=?,operate_time=NOW() where id = ?";

		return DBUtil.executeUpdate(sql, paramLists);
	}
	
	
	/**
	 * 修改节点为非叶子节点
	 * 
	 * @param pId
	 * @return
	 */

	public int updateParentIsLeaf(String pId){
	


		String sql = "update sys_course set is_leaf=2 where id = "+pId;

		return DBUtil.executeUpdate(sql);
	}
	
	
	
	/**
	 * 课程新增
	 * 
	 * @param paramLists
	 * @return
	 */

	public int insertCourse(List<Object> paramLists){
	


		String sql = "insert into  sys_course values(null,?,?,?,?,?,?,?)";

		return DBUtil.executeUpdate(sql, paramLists);
	}


	/**
	 * 课程修改唯一验证
	 * 
	 * @param courseName
	 * @param courseId
	 * @return
	 */
	public int validateCourse(String courseName,String courseId){
	
		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) from sys_course where name='"+courseName+"'");
		
		if(courseId != null){
			sb.append(" and id!="+courseId);
		}
	
		
		int rows = 0;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = DBUtil.getConnection();
		try {
			
			preparedStatement = connection.prepareStatement(sb.toString());
			
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			rows = resultSet.getInt(1);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rows;
	}
	   

}
