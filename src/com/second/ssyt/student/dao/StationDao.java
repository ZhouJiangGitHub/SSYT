package com.second.ssyt.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.second.ssyt.student.entity.StationEntity;
public class StationDao {

	/**
	 * 
	 * 站内查询
	 * @return
	 * @throws SQLException 
	 */
	public List<StationEntity> list() throws SQLException {
		String sql = "select a.id,a.title,b.name as 'uName',c.name as 'rName',a.send_time as 'sendTime',a.expire_time as 'expireTime' from assist_message a,sys_user b,sys_role c where a.sender_id = b.id and b.role_id = c.id order by a.id desc";        
		List<StationEntity> stationList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet =null;
         try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yec","root","root");
			preparedStatement= connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				StationEntity station = new StationEntity();
				station.setId(resultSet.getInt("id"));
				station.setTitle(resultSet.getString("title"));
				station.setuName(resultSet.getString("uName"));
				station.setrName(resultSet.getString("rName"));
				station.setSendTime(resultSet.getDate("sendTime"));
				station.setExpireTime(resultSet.getDate("expireTime"));
				stationList.add(station);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			stationList = Collections.emptyList();
		}finally{
			if(resultSet != null){
				resultSet.close();
			}
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(connection != null){
				connection.close();
			}
		}
         return stationList;
	}

	/**
	 * 查看详情
	 * @param id
	 * @return
	 */
	public List<StationEntity> MessageDetail(int id) {
		String sql = "select a.id,a.title,a.content,b.name as 'uName',c.name as 'rName',a.send_time as 'sendTime',a.expire_time as 'expireTime' from assist_message a,sys_user b,sys_role c where a.sender_id = b.id and b.role_id = c.id and a.id=?";
		List<StationEntity> stationList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Object> paramList = new ArrayList<>();
		try {
			// 1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2、连接数据库
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yec", "root", "root");
			// 3、操作数据库
			preparedStatement = connection.prepareStatement(sql);
			paramList.add(id);
			setParameter(paramList, preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StationEntity station = new StationEntity();
				station.setId(resultSet.getInt("id"));
				station.setTitle(resultSet.getString("title"));
				station.setContent(resultSet.getString("content"));
				station.setuName(resultSet.getString("uName"));
				station.setrName(resultSet.getString("rName"));
				station.setSendTime(resultSet.getDate("sendTime"));
				station.setExpireTime(resultSet.getDate("expireTime"));
				stationList.add(station);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			// userList = new ArrayList<>();
			stationList = Collections.emptyList();
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
		return stationList;

	}
	/**
	 * 参数设置
	 * 
	 * @param paramList
	 * @param preparedStatement
	 * @throws SQLException
	 */
	public  void setParameter(List<Object> paramList, PreparedStatement preparedStatement) throws SQLException {
		if (paramList != null) {
			for (int i = 0; i < paramList.size(); i++) {
				preparedStatement.setObject(i + 1, paramList.get(i));
			}
		}
	}
}