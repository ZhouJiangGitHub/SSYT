package com.second.ssyt.Stand.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;

import com.second.ssyt.PublicEntity.SysUserEntity;
import com.second.ssyt.Stand.Entity.StandEntity;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.common.util.DBUtil;
import com.second.ssyt.common.util.LogUtil;


public class StandDao {
	private static final Logger LOG = Logger.getLogger(DBUtil.class);
	
	public int  add(StandEntity  stand){
		if(stand==null){
			throw  new NullPointerException();
		}
		Connection  connection= null;
		PreparedStatement  preparedStatement=null;
		try{
		connection = DBUtil.getConnection();
		String sql="INSERT INTO assist_message values(null,?,?,?,?,?,?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, stand.getTitle());
		preparedStatement.setString(2, stand.getContent());
		preparedStatement.setString(3, stand.getSend_time());
		preparedStatement.setInt(4, stand.getSender_id());
		preparedStatement.setString(5, stand.getReceiver_user_ids()); 
		preparedStatement.setInt(6, stand.getReceiver_class_ids());	
		preparedStatement.setString(7, stand.getExpire_time());
		preparedStatement.setInt(8, stand.getState());
		
		
		return preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			LogUtil.e(LOG,"数据新增失败", e);
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
 * 条件，分页查询
 * @param pageNo
 * @param pageSize
 * @return
 */
	public PageModel<StandEntity> List(StandEntity  standEntity , int pageNo, int pageSize) {
		List<StandEntity> standList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// 1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2、连接数据库
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/yec", "root", "root");

			// 3、操作数据库
			List<Object> paramList = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			sb.append("select m.id, m.title,m.send_time,m.expire_time,m.state, ");
			sb.append("u.name,r.name As 'sys_role_name'  ");
			sb.append("from assist_message  m, sys_user u,sys_role r  ");
			sb.append("where u.role_id=r.id AND m.sender_id=u.id ");
			sb.append("AND r.operate_user_id=u.id   ");
			if (StringUtils.isNotBlank(standEntity.getTitle())) {
				sb.append("AND m.title LIKE ? ");
				paramList.add("%" + standEntity.getTitle() + "%");
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
				 StandEntity  sta = new StandEntity();
				   sta.setId(resultSet.getInt("id")); 
				   sta.setTitle(resultSet.getString("title"));
				   sta.setName(resultSet.getString("name"));
				   sta.setSys_role_name(resultSet.getString("sys_role_name"));
				   sta.setSend_time(resultSet.getString("send_time"));
				   sta.setExpire_time(resultSet.getString("expire_time"));
				   sta.setState(resultSet.getInt("state"));
				   standList.add(sta);		   
			}
			List<Object> paramList2 = new ArrayList<>();
			StringBuilder sb2 = new StringBuilder();
			sb2.append("SELECT COUNT(*) AS 'count' ");
			sb2.append("from assist_message  m, sys_user u,sys_role r  ");
			sb2.append("where u.role_id=r.id AND m.sender_id=u.id ");
			sb2.append("AND r.operate_user_id=u.id   ");
			if (StringUtils.isNotBlank(standEntity.getTitle())) {
				sb2.append("AND m.title LIKE ? ");
				paramList2.add("%" + standEntity.getTitle() + "%");
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

			return new PageModel<StandEntity>(standList, pageNo, pageSize, count);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			// userList = new ArrayList<>();
			standList = Collections.emptyList();
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
 * 根据用户标号删除信息
* @param id
* @return
*/
public int  deleteStandById(int id){
	// 定义操作受影响行数的变量
	int row=0;
	// 定义数据库连接相关句柄
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	try{
		// 加载驱动
	connection = DBUtil.getConnection();
	// 创建删除用户信息的SQL指令
	String sql="delete from assist_message where id=?";
	// 通过连接对象创建编译对象
	preparedStatement=connection.prepareStatement(sql);
	// 设置参
	 preparedStatement.setInt(1, id);
	 
	// 通过编译对象执行SQl指令
	 row=preparedStatement.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
}
	// 释放操作资源
	finally{
	
	try {
		if (preparedStatement!= null) {
			preparedStatement.close();
		}
		if (connection != null ) {
			connection.close();
			
		}
	} catch (Exception e2) {
		e2.printStackTrace();
	}
}

return row;
}

/**
 *根据用户编号 查询用户信息
* @return
*/
	  
public StandEntity  getstandById(int id){
	
	StandEntity  sta=  new  StandEntity();
	 
	// 定义数据库连接相关句柄
			Connection connection=null;
			PreparedStatement preparedStatement=null;
			ResultSet  resultSet= null;
			try{
				connection = DBUtil.getConnection();
			// 创建添加用户信息的SQL指令
			String sql="SELECT * FROM  assist_message  where id=?";
			// 通过连接对象创建编译对象
			preparedStatement=connection.prepareStatement(sql);
			 preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			 if(resultSet.next()){
				   sta.setId(resultSet.getInt("id"));
				   sta.setTitle(resultSet.getString("title"));
				   sta.setContent(resultSet.getString("content"));
				   sta.setReceiver_user_ids(resultSet.getString("receiver_user_ids"));
				   sta.setExpire_time(resultSet.getString("expire_time"));
				   sta.setState(resultSet.getInt("state"));
				  }
			}catch (Exception e) {
				e.printStackTrace();
		}
			
			
			// 释放操作资源
			finally{
			
			try {
				if (preparedStatement!= null) {
					preparedStatement.close();
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
					connection = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
			return sta;
}


public int updateUser(StandEntity stand) {

	// 定义操作受影响行数的变量
	int row = 0;

	// 定义数据库连接相关句柄
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	try {
		// 加载驱动
		connection = DBUtil.getConnection();
		// 创建根据用户编号删除用户信息的SQL指令
		String sql = "update  assist_message set title=?,content=?,receiver_user_ids=?,expire_time=?,state=? where id=?";

		// 通过连接对象创建编译对象
		preparedStatement = connection.prepareStatement(sql);

		// 设置参数
		System.out.println("stand"+stand);
		
		preparedStatement.setString(1, stand.getTitle());
		preparedStatement.setString(2, stand.getContent());
		preparedStatement.setString(3, stand.getReceiver_user_ids()); 
		preparedStatement.setString(4, stand.getExpire_time());
		preparedStatement.setInt(5, stand.getState());
		preparedStatement.setInt(6, stand.getId());
		
		
		// 通过编译对象执行SQl指令
		row = preparedStatement.executeUpdate();
	
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		// 释放操作资源
		try {
			if (preparedStatement!= null) {
				preparedStatement.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
				connection = null;
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	return row;

}
/**
 * 状态更新
 * 
 * @param id
 * @param state
 * @return
 */
public int update(int id, int state) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	int row = 0;
	try {
		// 1、加载驱动
		Class.forName("com.mysql.jdbc.Driver");

		// 2、连接数据库
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/yec", "root", "root");

		// 3、操作数据库
		preparedStatement = connection
				.prepareStatement("UPDATE  assist_message SET state=? WHERE id=?");
		preparedStatement.setInt(1, state == 0 ? 1 : 0);
		preparedStatement.setInt(2, id);
		row = preparedStatement.executeUpdate();
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	} finally {// 4、关闭资源
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
	return row;
}




/**
 班级的唯一验证
 * @param courseName
 * @param courseId
 * @return
 */
public int validateClass(String standtitle,String  titleId){
	int rows = 0;
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	StringBuilder sb = new StringBuilder();
	sb.append("select count(*) from assist_message where title='"+standtitle+"'");
	
	if(titleId != null && titleId != ""){
		sb.append(" and id!="+titleId);
	}

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


/**
 * 站内信息详情查询
 * @param pageNo
 * @param pageSize
 * @return
 */
public List<StandEntity>List() {
	List<StandEntity> detailsList = new ArrayList<>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	try {
		// 1、加载驱动
		Class.forName("com.mysql.jdbc.Driver");

		// 2、连接数据库
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/yec", "root", "root");

		// 3、操作数据库
		String sql="select m.id, m.title,m.content,m.send_time,m.expire_time,m.state,m.receiver_user_ids, u.name,r.name As 'sys_role_name' from assist_message  m, sys_user u,sys_role r where u.role_id=r.id AND m.sender_id=u.id AND r.operate_user_id=u.id";
		preparedStatement = connection.prepareStatement(sql);
		 resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			 StandEntity  sta = new StandEntity();
			   sta.setId(resultSet.getInt("id")); 
			   System.out.println(resultSet.getInt("id"));
			   sta.setTitle(resultSet.getString("title")); 
			   System.out.println(resultSet.getString("title"));
			   sta.setContent(resultSet.getString("content"));
			  
			   sta.setName(resultSet.getString("name"));
			   sta.setSys_role_name(resultSet.getString("sys_role_name"));
			   sta.setSend_time(resultSet.getString("send_time"));
			   sta.setId(resultSet.getInt("receiver_user_ids")); 
			   sta.setExpire_time(resultSet.getString("expire_time"));
			   sta.setState(resultSet.getInt("state"));
			   detailsList.add(sta);	   
		}

	    } catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
		// userList = new ArrayList<>();
		detailsList = Collections.emptyList();
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
	return detailsList;
}
/**
 * 查询要删除的内容
 * @return
 */
public StandEntity  getById(int id){
	
	StandEntity  sta=  new  StandEntity();
	 
	// 定义数据库连接相关句柄
			Connection connection=null;
			PreparedStatement preparedStatement=null;
			ResultSet  resultSet= null;
			try{
				connection = DBUtil.getConnection();
			// 创建添加用户信息的SQL指令
			String sql="SELECT * FROM  assist_message  where id=?";
			// 通过连接对象创建编译对象
			 preparedStatement=connection.prepareStatement(sql);
			 preparedStatement.setInt(1, id);
			 resultSet=preparedStatement.executeQuery();
			 if(resultSet.next()){
				   sta.setId(resultSet.getInt("id")); 
				   sta.setTitle(resultSet.getString("title")); 
				   sta.setSender_id(resultSet.getInt("sender_id"));
				   sta.setContent(resultSet.getString("content"));
				   sta.setSend_time(resultSet.getString("send_time"));
				   sta.setReceiver_class_ids(resultSet.getInt("receiver_class_ids"));
				   sta.setReceiver_user_ids(resultSet.getString("receiver_user_ids"));
				   sta.setExpire_time(resultSet.getString("expire_time"));
				   sta.setState(resultSet.getInt("state"));
				  }
			  }catch (Exception e) {
				e.printStackTrace();
		     }
			
			
			// 释放操作资源
			finally{
			
			try {
				if (preparedStatement!= null) {
					preparedStatement.close();
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
					connection = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
			return sta;
}


public SysUserEntity getUser(int sender_id) {
	String sql="select u.name,r.name as 'rolename' from sys_user u,sys_role r where u.role_id=r.id and u.id=?";
	return DBUtil.getUniqueResult(SysUserEntity.class, sql, sender_id);
	
}


public List<SysUserEntity> getUsers(String[] userId) {
	if(userId!=null){
		List<SysUserEntity> SysUserEntityList=new ArrayList<SysUserEntity>();
	for(int i=0;i<userId.length;i++){
	String sql="select name,id from sys_user where id=?";
	SysUserEntity SysUserEntity=DBUtil.getUniqueResult(SysUserEntity.class, sql,Integer.parseInt(userId[i]));
	 SysUserEntityList.add(SysUserEntity);
		
	}	
		
	return SysUserEntityList;
		
		
}
	return null;
}


}