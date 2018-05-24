package com.second.ssyt.role.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.second.ssyt.common.PageModel;
import com.second.ssyt.common.exception.CommonRuntimeException;
import com.second.ssyt.common.util.DBUtil;
import com.second.ssyt.role.entity.SysRoleEntity;

public class SysRoleDao {

    /**
     * 一般查询 失效
     *
     * @return
     * @throws SQLException
     */
    public PageModel<SysRoleEntity> listPage(int pageNo, int pageSize) throws SQLException {
        String sql = "select r.id,r.name,u.name as 'sysUser$Name' ,r.operate_time as 'operateTime' ,r.state,r.memo from sys_role r,sys_user u where r.id = u.role_id order r.id desc";
        List<SysRoleEntity> sysRoleList = new ArrayList<SysRoleEntity>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yec", "root", "123");
            List<Object> paramList = new ArrayList<>();
            paramList.add((pageNo - 1) * pageSize);
            paramList.add(pageSize);
            preparedStatement = connection.prepareStatement(sql);
            if (paramList != null) {
                for (int i = 0; i < paramList.size(); i++) {
                    preparedStatement.setObject(i + 1, paramList.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SysRoleEntity sysRole = new SysRoleEntity();
                sysRole.setId(resultSet.getInt("id"));
                sysRole.setName(resultSet.getString("name"));
                sysRole.setSysUser$Name(resultSet.getString("sysUser$Name"));
                sysRole.setOperateTime(resultSet.getDate("operateTime"));
                sysRole.setState(resultSet.getShort("state"));
                sysRole.setMemo(resultSet.getString("memo"));
                sysRoleList.add(sysRole);
            }
            return new PageModel<SysRoleEntity>(sysRoleList, pageNo, pageSize,
                    getAllcount());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            sysRoleList = Collections.emptyList();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;

    }

    /**
     * 得到总条数
     *
     * @return
     */
    private static int getAllcount() {
        String sql = "SELECT COUNT(*) FROM  sys_role";

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
     * 新增
     *
     * @param sysRoleList
     * @return
     */
    public int add(SysRoleEntity sysRoleList) {
        String sql = "INSERT INTO sys_role VALUES(NULL ,?,?,?,NOW(),?,?)";
        List<Object> paramList = new ArrayList<>();
        paramList.add(sysRoleList.getName());
        paramList.add(sysRoleList.getMenuIds());
        paramList.add(sysRoleList.getOperateUserId());
        paramList.add(sysRoleList.getState());
        paramList.add(sysRoleList.getMemo());
        //return DBUtil.executeUpdate(sql, params);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParameter(paramList, preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
    }


    /**
     * 设置参数个数
     *
     * @param paramList
     * @param preparedStatement
     * @throws SQLException
     */
    public static void setParameter(List<Object> paramList, PreparedStatement preparedStatement) throws SQLException {
        if (paramList != null) {
            for (int i = 0; i < paramList.size(); i++) {
                preparedStatement.setObject(i + 1, paramList.get(i));
            }
        }
    }

//	public int update(SysRoleEntity sysRole,int id) {
//		List<Object> params = new ArrayList<>();
//		params.add(sysRole.getName());
//		params.add(sysRole.getState());
//		params.add(sysRole.getMemo());
//		String sql = "UPDATE sys_role SET name=?,state=?, memo=? WHERE id=?";
//		return DBUtil.executeUpdate(sql, params);
//	}


    /**
     * 消息回显
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public static List<SysRoleEntity> get(int id) throws SQLException {
        String sql = "SELECT * FROM sys_role WHERE id=? ";
//		 List<SysRoleEntity> sysRoleList = DBUtil.executeQuery(SysRoleEntity.class, sql, id);
//		return sysRoleList;

        List<SysRoleEntity> sysRoleList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yec", "root", "123");
            List<Object> paramList = new ArrayList<>();
            preparedStatement = connection.prepareStatement(sql);
            paramList.add(id);
            if (paramList != null) {
                for (int i = 0; i < paramList.size(); i++) {
                    preparedStatement.setObject(i + 1, paramList.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SysRoleEntity sysRole = new SysRoleEntity();
                sysRole.setId(resultSet.getInt("id"));
                sysRole.setName(resultSet.getString("name"));
                sysRole.setState(resultSet.getShort("state"));
                sysRole.setMemo(resultSet.getString("memo"));
                sysRoleList.add(sysRole);
            }
            return sysRoleList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    /**
     * 条件查询
     *
     * @param sysRoleEntity
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageModel<SysRoleEntity> list(SysRoleEntity sysRoleEntity, int pageSize, int pageNo) {
        List<SysRoleEntity> sysRoleList = new ArrayList<>();
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
            sb.append("select * from sys_role r where 1=1 ");
            short state = sysRoleEntity.getState();
            if (state != 0) {

                sb.append("AND r.state = ? ");
                paramList.add(sysRoleEntity.getState());
            }
            sb.append("LIMIT ?,? ");
            paramList.add((pageNo - 1) * pageSize);
            paramList.add(pageSize);
            preparedStatement = connection.prepareStatement(sb.toString());
            if (paramList != null) {
                for (int i = 0; i < paramList.size(); i++) {
                    preparedStatement.setObject(i + 1, paramList.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SysRoleEntity sysRole = new SysRoleEntity();
                sysRole.setId(resultSet.getInt("id"));
                sysRole.setName(resultSet.getString("name"));
                sysRole.setOperateUserId(resultSet.getInt("operate_user_id"));
                sysRole.setOperateTime(resultSet.getDate("operate_time"));
                sysRole.setState(resultSet.getShort("state"));
                sysRole.setMemo(resultSet.getString("memo"));
                sysRoleList.add(sysRole);
            }
            return new PageModel<SysRoleEntity>(sysRoleList, pageNo, pageSize, getAllcount());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            sysRoleList = Collections.emptyList();
        } finally {
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
     * 状态修改
     *
     * @param id
     * @param state
     * @return
     */
    public int updateState(int id, Short state) {
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
                    .prepareStatement("UPDATE sys_role SET state=? WHERE id=?");
            preparedStatement.setInt(1, state == 1 ? 2 : 1);
            preparedStatement.setInt(2, id);
            row = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
     * 新增分页
     *
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageModel<SysRoleEntity> listAddPage(int pageSize, int pageNo) {
        List<SysRoleEntity> sysRoleList = new ArrayList<>();
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
            sb.append("select name, ");
            sb.append("state  ");
            sb.append("from sys_role order by id desc ");
            sb.append("LIMIT ?,?");
            paramList.add((pageNo - 1) * pageSize);
            paramList.add(pageSize);
            preparedStatement = connection.prepareStatement(sb.toString());
            if (paramList != null) {
                for (int i = 0; i < paramList.size(); i++) {
                    preparedStatement.setObject(i + 1, paramList.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SysRoleEntity sysRole = new SysRoleEntity();
                sysRole.setName(resultSet.getString("name"));
                sysRole.setState(resultSet.getShort("state"));
                sysRoleList.add(sysRole);
            }

            // 查询总记录
            List<Object> paramList2 = new ArrayList<>();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("SELECT COUNT(*) AS 'count' ");
            sb2.append("FROM sys_role  ");
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

            return new PageModel<SysRoleEntity>(sysRoleList, pageNo, pageSize, count);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            sysRoleList = Collections.emptyList();
        } finally {
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
     * 角色修改
     *
     * @param sysRole
     * @param id
     * @return
     */
    public int update(SysRoleEntity sysRoleEntity, int id) {
        StringBuilder sb = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sb.append("UPDATE sys_role ");
        sb.append("SET name=?,state=?,memo =? ");
        sb.append("WHERE id=?");
        paramList.add(sysRoleEntity.getName());
        paramList.add(sysRoleEntity.getState());
        paramList.add(sysRoleEntity.getMemo());
        paramList.add(id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sb.toString());
            setParameter(paramList, preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
    }


}
