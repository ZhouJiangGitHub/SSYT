package com.second.ssyt.Class.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.second.ssyt.Class.entity.ClassEntity;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.common.util.DBUtil;

public class ClassDao {
    public int add(ClassEntity user) {
        if (user == null) {
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "INSERT INTO sys_class values(null,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getCourse_id());
            preparedStatement.setInt(3, user.getOperate_user_id());
            preparedStatement.setString(4, user.getOperate_time());
            preparedStatement.setInt(5, user.getState());
            preparedStatement.setString(6, user.getMemo());


            return preparedStatement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
            throw new RuntimeException("数据新增失败");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }


//查询


/**
 * 分页查询班级（包括总记录数）
 *
 * @param pageModel
 * @return
 */
/*public PageModel<ClassEntity> List(PageModel<ClassEntity> pageModel) {
	 List<ClassEntity> classList= new ArrayList<>();
		

		Connection  connection= null;
		PreparedStatement  preparedStatement=null;
		ResultSet  resultSet=null;
		
		int pageNo = pageModel.getPageNo();
		int pageSize = pageModel.getPageSize();
		
		try{
			connection = DBUtil.getConnection();
		String sql="select c.name, c.id,c.state, c.memo,c.operate_time ,c.course_id ,c.operate_user_id  from "
				+ "sys_class  c, sys_course a, sys_user u "
				+ "where c.course_id=a.id and c.operate_user_id=u.id and a.operate_user_id=u.id LIMIT ?,? ";
	   preparedStatement = connection.prepareStatement(sql);
	   preparedStatement.setInt(1, (pageNo - 1)*pageSize);
	   preparedStatement.setInt(2, pageSize);
	   resultSet=preparedStatement.executeQuery();
		   while(resultSet.next()){
			   ClassEntity  cla = new ClassEntity();
			  cla.setId(resultSet.getInt("id"));
			   cla.setName(resultSet.getString("name"));
			   System.out.println(2);
			   cla.setCourse_id(resultSet.getInt("course_id"));
			   System.out.println(3);
			   cla.setOperate_user_id(resultSet.getInt("operate_user_id"));
			   System.out.println(4);
			   cla.setOperate_time(resultSet.getString("operate_time"));
			   System.out.println(5);
			   cla.setState(resultSet.getInt("state"));
			   System.out.println(6);
			   cla.setMemo(resultSet.getString("memo"));
			   System.out.println(7);
			   System.out.println(cla.getName());

			   classList.add(cla);	   
		   }
			  
		pageModel.setList(classList);   

	   //查询总记录数
	      
	     preparedStatement=connection.prepareStatement("SELECT COUNT(*) FROM sys_class");
	     
	     resultSet=preparedStatement.executeQuery();
	     while(resultSet.next()){
	    	 pageModel.setAllRecords(resultSet.getInt(1));
	     }
	     return pageModel;
		
		
	     }
		catch (SQLException e) {
			
			e.printStackTrace();
			throw  new RuntimeException("数据查询失败");
		}
		
	finally{
			try{
				
		    if(resultSet!=null){
		    	resultSet.close();
		    }
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

*/

    /**
     * 根据用户标号删除信息
     *
     * @param id
     * @return
     */
    public int deleteUserById(int id) {
        // 定义操作受影响行数的变量
        int row = 0;
        // 定义数据库连接相关句柄
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 加载驱动
            connection = DBUtil.getConnection();
            // 创建删除用户信息的SQL指令
            String sql = "delete from sys_class where id=?";
            // 通过连接对象创建编译对象
            preparedStatement = connection.prepareStatement(sql);
            // 设置参
            preparedStatement.setInt(1, id);

            // 通过编译对象执行SQl指令
            row = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 释放操作资源
        finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();

                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return row;
    }

    /**
     * 根据用户编号 查询用户信息
     *
     * @return
     */

    public ClassEntity getclassById(int id) {

        ClassEntity cla = new ClassEntity();

        // 定义数据库连接相关句柄
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            // 创建添加用户信息的SQL指令
            String sql = "SELECT * FROM sys_class  where id=?";
            // 通过连接对象创建编译对象
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cla.setId(resultSet.getInt("id"));
                cla.setName(resultSet.getString("name"));
                cla.setCourse_id(resultSet.getInt("course_id"));
                cla.setOperate_user_id(resultSet.getInt("operate_user_id"));
                cla.setOperate_time(resultSet.getString("operate_time"));
                cla.setState(resultSet.getInt("state"));
                cla.setMemo(resultSet.getString("memo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 释放操作资源
        finally {

            try {
                if (preparedStatement != null) {
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
        return cla;
    }


    public int updateUser(ClassEntity user) {

        // 定义操作受影响行数的变量
        int row = 0;

        // 定义数据库连接相关句柄
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 加载驱动
            connection = DBUtil.getConnection();
            // 创建根据用户编号删除用户信息的SQL指令
            String sql = "update  sys_class set name=?,course_id=?,operate_user_id=? , Operate_time=?,state=?,memo=? where id=?";

            // 通过连接对象创建编译对象
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数

            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getCourse_id());
            preparedStatement.setInt(3, user.getOperate_user_id());
            preparedStatement.setString(4, user.getOperate_time());
            preparedStatement.setInt(5, user.getState());
            preparedStatement.setString(6, user.getMemo());
            preparedStatement.setInt(7, user.getId());


            // 通过编译对象执行SQl指令
            row = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放操作资源
            try {
                if (preparedStatement != null) {
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
                    "jdbc:mysql://localhost:3306/yec", "root", "123");

            // 3、操作数据库
            preparedStatement = connection
                    .prepareStatement("UPDATE sys_class SET state=? WHERE id=?");
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
     * 条件，分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageModel<ClassEntity> List(ClassEntity classEntity, int pageNo, int pageSize) {
        List<ClassEntity> classList = new ArrayList<>();
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
            sb.append("select c.name, c.id,c.state, c.memo,c.operate_time ,a.name as 'courseName',u.name as 'userName' ");
            sb.append("from sys_class  c, sys_course a, sys_user u  ");
            sb.append("where c.course_id=a.id and c.operate_user_id=u.id ");
            if (StringUtils.isNotBlank(classEntity.getName())) {
                sb.append("AND c.name LIKE ? ");
                paramList.add("%" + classEntity.getName() + "%");
            }
            sb.append("LIMIT ?,? ");
            paramList.add((pageNo - 1) * pageSize);
            paramList.add(pageSize);
            System.out.println((pageNo - 1) * pageSize);
            System.out.println(pageSize);
            System.out.println(sb.toString());
            System.out.println(paramList.size());
            preparedStatement = connection.prepareStatement(sb.toString());

            for (int i = 0; i < paramList.size(); i++) {
                preparedStatement.setObject(i + 1, paramList.get(i));
            }

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("gfdfdfdf");
                ClassEntity cla = new ClassEntity();
                cla.setId(resultSet.getInt("id"));
                cla.setName(resultSet.getString("name"));
                cla.setCourseName(resultSet.getString("courseName"));
                cla.setUserName(resultSet.getString("userName"));
                cla.setOperate_time(resultSet.getString("operate_time"));
                cla.setState(resultSet.getInt("state"));
                cla.setMemo(resultSet.getString("memo"));
                System.out.println(cla.getName());

                classList.add(cla);
            }
            System.out.println(classList.size());
            // 查询总记录
            List<Object> paramList2 = new ArrayList<>();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("SELECT COUNT(*) AS 'count' ");
            sb2.append("from sys_class  c, sys_course a, sys_user u ");
            sb2.append("where c.course_id=a.id and c.operate_user_id=u.id and a.operate_user_id=u.id ");
            if (StringUtils.isNotBlank(classEntity.getName())) {
                sb2.append("AND c.name LIKE ? ");
                paramList2.add("%" + classEntity.getName() + "%");
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

            return new PageModel<ClassEntity>(classList, pageNo, pageSize, count);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // userList = new ArrayList<>();
            classList = Collections.emptyList();
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
     * 班级的唯一验证
     *
     * @param courseName
     * @param courseId
     * @return
     */
    public int validateClass(String className, String classId) {
        int rows = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from sys_class where name='" + className + "'");

        if (classId != null && classId != "") {
            sb.append(" and id!=" + classId);
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
}
