package com.second.ssyt.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.second.ssyt.common.util.DBUtil;
import com.second.ssyt.login.entity.UserEntity;

public class LoginDao {
	/**
	 * @param code
	 * @param password
	 * @return
	 */
	public UserEntity login(String code, String password) {
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("a.id,a.code,a.password,a.name,a.sex,a.phone,a.email,a.create_time,");
		sb.append("a.operate_time,a.operate_user_id,");
		sb.append("a.role_id,a.class_id,");
		sb.append("a.state,b.state role$state,c.state class$state,");
		sb.append("b.name role$name,c.name class$name ");
		sb.append("from sys_user a,sys_role b,sys_class c where a.role_id=b.id and a.class_id=c.id and ");
		sb.append("a.code='"+code+"' and a.password ='"+password+"'");
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		UserEntity user = new UserEntity();
		try {

			connection = DBUtil.getConnection();
			
			preparedStatement = connection.prepareStatement(sb.toString());
			
			// 閹垮秳缍旈弫鐗堝祦

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				
				user.setId(resultSet.getInt("id"));
				user.setCode(resultSet.getString("code"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("name"));
				user.setSex(resultSet.getInt("sex"));
				user.setPhone(resultSet.getString("phone"));
				user.setEmail(resultSet.getString("email"));
				user.setCreateTime(resultSet.getDate("create_time"));
				user.setRoleId(resultSet.getInt("role_id"));
				user.setClassId(resultSet.getInt("class_id"));
				user.setState(resultSet.getInt("state"));
				user.setClass$state(resultSet.getInt("class$state"));
				user.setRole$state(resultSet.getInt("role$state"));
				user.setRole$name(resultSet.getString("role$name"));
				user.setClass$name(resultSet.getString("class$name"));
				
			}


		} catch (SQLException e) {

			System.out.println("閻€劍鍩涢弫鐗堝祦閺屻儴顕楁径杈Е");
			e.printStackTrace();
		}

		
		return user;
	}
	
	
	/**
	 * 閻€劋绨悽銊﹀煕閺佺増宓侀弴瀛樻煀
	 * 
	 * @param id
	 * @param phone
	 * @param email
	 * @return
	 */
	public int userUpdate(int id,String phone,String email){
		
		String sql = "update sys_user set phone=?,email=? where id=?";
		return DBUtil.executeUpdate(sql,phone,email,id);
	}
	/**
	 * 璇ユ柟娉曠敤浜庣敤鎴蜂慨鏀瑰瘑鐮�
	 * 
	 * @param id
	 * @param newPW
	 * @return
	 * @throws SQLException 
	 */
	public int updatePW(int id,String newPW){
		
		String sql = "update sys_user set password=? where id=?";
		
		int rows = 0;
		
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, newPW);
			
			preparedStatement.setInt(2, id);
			
			rows = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rows;
	}
	
	
	public int validatePhone(String phone,String userId){
		
		String sql = "select count(*) from sys_user where phone =? and id<>?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rows = 0;
		
		connection = DBUtil.getConnection();
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, userId);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			
			rows = resultSet.getInt(1);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rows;
	}
	
public int validateEmail(String email,String userId){
		
		String sql = "select count(*) from sys_user where email =? and id<>?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rows = 0;
		
		connection = DBUtil.getConnection();
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, userId);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			
			rows = resultSet.getInt(1);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rows;
	}


/**
 * 获取主菜单
 * @param name
 * @return
 */
public List<Map<String,Object>> getMainMenu(String code) {
	String sql = "select * from rightinfo where rightId in "
			+ "(select rightId from rolerightinfo where roleId = "
			+ "(select roleId from sys_user where code=?)) and parentId='root'";
	System.out.println(executeQuery(sql,code));
	return executeQuery(sql,code);
}
/**
 * 获取子菜单
 * @param username
 * @return
 */
public List<Map<String,Object>> getChildMenu(String code) {
	String sql = "select * from rightinfo where rightId in "
			+ "(select rightId from rolerightinfo where roleId = "
			+ "(select roleId from sys_user where code=?)) and parentId<>'root'";
	System.out.println(executeQuery(sql,code));
	return executeQuery(sql, code);
}

/**
 * 执行查询SQL指令操作
 * @param sql 调用处传入SQL命令
 * @param parameters 与SQL命令对应的SQL参数数组
 * @return 返回才查询结果集合
 */
public static List<Map<String,Object>> executeQuery(String sql,Object...parameters) {
	List<Map<String,Object>> table = new ArrayList<>();
	

	Connection conn = DBUtil.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	try {
		pst = conn.prepareStatement(sql);
		// 判断是否设置参数
		if (parameters!=null && parameters.length>0) {
			for(int i=0;i<parameters.length;i++) {
				pst.setObject(i+1, parameters[i]);
			}
		}
		
		rs = pst.executeQuery();
		
		if (rs != null) {
			// 把结果集转换为虚拟表
			ResultSetMetaData rsd = rs.getMetaData();
			// 获取表格中的列数
			int count = rsd.getColumnCount();
			
			
			
			while(rs.next()) {
				// 创建一个存储当前遍历行每个单元格数据的map集合对象
				Map<String,Object> row = new HashMap<>();
				
				for(int i =0 ;i<count;i++) {
					row.put(rsd.getColumnName(i+1), rs.getObject(rsd.getColumnName(i+1)));
				}
				
				// 把当前遍历行组成集合对象存储到table集合中
				table.add(row);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		
	}
	
	return table;
}


}
