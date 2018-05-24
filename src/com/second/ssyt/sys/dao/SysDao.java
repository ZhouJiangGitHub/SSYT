package com.second.ssyt.sys.dao;

import java.lang.reflect.Field;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import com.second.ssyt.Class.entity.ClassEntity;
import com.second.ssyt.common.exception.CommonRuntimeException;
import com.second.ssyt.common.util.DBUtil;
import com.second.ssyt.common.util.LogUtil;
import com.second.ssyt.role.entity.SysRoleEntity;
import com.second.ssyt.sys.entity.RoleRightEntity;
import com.second.ssyt.sys.entity.SysEntity;
import com.second.ssyt.common.Constant;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.examinationpaper.entity.ExaminationPaperEntity;

public class SysDao {
    private static final Logger LOG = Logger.getLogger(DBUtil.class);

    /**
     * 学生信息查询
     *
     * @param <T>
     * @return
     */
    public static List<SysEntity> list() {
        String sql = "select *  from  sys_user";
        Class<SysEntity> clazz = SysEntity.class;
        List<SysEntity> SysuserList = new ArrayList<SysEntity>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            // 读取取数据库结构
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 得到有多少列
            int columns = resultSetMetaData.getColumnCount();
            // 循环每条结果，并把结果封装成具体的实体对象，最后放到返回集合中
            while (resultSet.next()) {
                SysEntity obj = clazz.newInstance();
                // 对每列循环，把对应的值赋对obj的相应属性
                for (int i = 1; i <= columns; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i);
                    // 循环所有的属性
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Class<?> fieldClass = field.getType();
                        String fieldName = field.getName();
                        // 某个属性有大写，表示需要属性名与数据库列名对应
                        if (isContainsAlphaUpper(fieldName)) {
                            String columnName = toColumnName(fieldName);
                            if (columnName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        } else {
                            // 表示属性名与数据库列名相同
                            if (fieldName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        }
                    }
                }
                SysuserList.add(obj);
            }
            return SysuserList;
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | SecurityException e) {
            LogUtil.e(LOG, "数据查询失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 改变用户状态
     *
     * @param id
     * @return
     */
    public static int change(int status, int id) {
        String sql = " update sys_user set state=? WHERE id=?";
        //return DBUtil.executeUpdate(sql, status, id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LogUtil.e(LOG, "数据更新失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
    }

    /**
     * 分页查询管理信息
     *
     * @param pageNo
     * @param defaultPageSize
     * @return
     */
    public static PageModel<SysEntity> listManageByPage(int pageNo,
                                                        int PageSize) {
        StringBuilder sb = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sb.append("SELECT c.name AS rolename,b.name AS classname, ");
        sb.append("a.id,a.code,a.name,a.sex,a.phone,a.email, ");
        sb.append("a.create_time,a.operate_user_id,a.operate_time,a.role_id,a.class_id, ");
        sb.append("a.state,a.memo ");
        sb.append("FROM  sys_user a , sys_class b , sys_role c  ");
        sb.append("where a.role_id = c.id and a.class_id = b.id LIMIT ?,? ");
        //String sql = "SELECT * FROM  sys_user a , sys_class b , sys_role c where a.role_id = c.id and a.class_id = b.id  LIMIT ?,?";

        /* PageModel<StudentEntity> PageModel =
         * DBUtil.executeQueryByPage(StudentEntity.class, pageNo,
         * defaultPageSize, sql);
         * */
        paramList.add((pageNo - 1) * PageSize);
        paramList.add(PageSize);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sb.toString());
			/*preparedStatement.setInt(1, (pageNo - 1) * PageSize);
			preparedStatement.setInt(2, PageSize);*/
            setParameter(paramList, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            List<SysEntity> SysManageList = new ArrayList<>();
            while (resultSet.next()) {
                SysEntity userMessagelist = new SysEntity();
                userMessagelist.setId(resultSet.getInt("id"));
                userMessagelist.setCode(resultSet.getString("code"));
                //userMessagelist.setPassword(resultSet.getString("password"));
                userMessagelist.setName(resultSet.getString("name"));
                userMessagelist.setSex(resultSet.getInt("sex"));
                userMessagelist.setPhone(resultSet.getString("phone"));
                userMessagelist.setEmail(resultSet.getString("email"));
                userMessagelist.setCreate_time(resultSet.getDate("create_time"));
                userMessagelist.setOperate_user_id(resultSet.getInt("operate_user_id"));
                userMessagelist.setOperate_time(resultSet.getDate("operate_time"));
                userMessagelist.setRole_id(resultSet.getInt("role_id"));
                userMessagelist.setClass_id(resultSet.getInt("class_id"));
                userMessagelist.setClassname(resultSet.getString("classname"));
                userMessagelist.setState(resultSet.getInt("state"));
                userMessagelist.setMemo(resultSet.getString("memo"));
                SysManageList.add(userMessagelist);
            }
            // 构建分页实体
            return new PageModel<SysEntity>(SysManageList, pageNo, PageSize,
                    getAllcount());

        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 管理数据总数
     *
     * @param
     * @return
     */
    private static int getAllcount() {
        String sql = "SELECT COUNT(*) FROM  sys_user";

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
    public static PageModel<SysEntity> listManageVerifyByPage(
            SysEntity findSys, int pageNo, int PageSize) {
        List<Object> paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c.name AS rolename,b.name AS classname, ");
        sb.append("a.id,a.code,a.name,a.sex,a.phone,a.email, ");
        sb.append("a.create_time,a.operate_user_id,a.operate_time,a.role_id,a.class_id, ");
        sb.append("a.state,a.memo ");
        sb.append("FROM  sys_user a , sys_class b , sys_role c  ");
        sb.append("where a.role_id = c.id and a.class_id = b.id  ");
        //晚点多表拼C
        //sb.append("SELECT * FROM  sys_user where 1 = 1  ");
        String Name = findSys.getName();
        if (StringUtils.isNoneBlank(Name)) {
            sb.append("and a.name LIKE ?  ");
            paramList.add("%" + findSys.getName() + "%");
        }
        int role = findSys.getRole_id();
        if (role != 0) {
            sb.append("and a.role_id LIKE ?  ");
            paramList.add("%" + findSys.getRole_id() + "%");
        }
        int classs = findSys.getClass_id();
        if (classs != 0) {
            sb.append("and a.class_id LIKE ?  ");
            paramList.add("%" + findSys.getClass_id() + "%");
        }
        int state = findSys.getState();
        if (state != 0) {
            sb.append("and a.state LIKE ?  ");
            paramList.add("%" + findSys.getState() + "%");
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
            List<SysEntity> SysList = new ArrayList<>();
            while (resultSet.next()) {
                SysEntity userMessagelist = new SysEntity();
                userMessagelist.setId(resultSet.getInt("id"));
                userMessagelist.setCode(resultSet.getString("code"));
                //userMessagelist.setPassword(resultSet.getString("password"));
                userMessagelist.setName(resultSet.getString("name"));
                userMessagelist.setSex(resultSet.getInt("sex"));
                userMessagelist.setPhone(resultSet.getString("phone"));
                userMessagelist.setEmail(resultSet.getString("email"));
                userMessagelist.setCreate_time(resultSet.getDate("create_time"));
                userMessagelist.setOperate_user_id(resultSet.getInt("operate_user_id"));
                userMessagelist.setOperate_time(resultSet.getDate("operate_time"));
                userMessagelist.setRole_id(resultSet.getInt("role_id"));
                userMessagelist.setClass_id(resultSet.getInt("class_id"));
                userMessagelist.setClassname(resultSet.getString("classname"));
                userMessagelist.setState(resultSet.getInt("state"));
                userMessagelist.setMemo(resultSet.getString("memo"));
                SysList.add(userMessagelist);
            }
            // 构建分页实体
            return new PageModel<SysEntity>(SysList, pageNo, PageSize,
                    listManageVerifyByPageCount(findSys));
        } catch (Exception e) {
            LogUtil.e(LOG, "分页时，查询总记录数失败", e);
            throw new RuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }

    }

    /**
     * 条件查询总数
     *
     * @return
     */
    private static int listManageVerifyByPageCount(SysEntity findSys) {
        List<Object> paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("COUNT(*) ");
        sb.append("FROM  sys_user a , sys_class b , sys_role c  ");
        sb.append("where a.role_id = c.id and a.class_id = b.id  ");
        String Code = findSys.getCode();
        if (StringUtils.isNoneBlank(Code)) {
            sb.append("AND a.code LIKE ?  ");
            paramList.add("%" + findSys.getCode() + "%");
        }
        String Name = findSys.getName();
        if (StringUtils.isNoneBlank(Name)) {
            sb.append("AND a.name LIKE ?  ");
            paramList.add("%" + findSys.getName() + "%");
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sb.toString());
            setParameter(paramList, preparedStatement);
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
     * 获取需要修改的用户信息
     *
     * @param id
     * @return
     */
    public static SysEntity get(int id) {
        String sql = "SELECT * FROM sys_user WHERE id=?";
        //return DBUtil.getUniqueResult(SysEntity.class, sql, id);
        List<Object> paramList = new ArrayList<>();
        Class<SysEntity> clazz = SysEntity.class;
        List<SysEntity> SysuserList = new ArrayList<SysEntity>();
        paramList.add(id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParameter(paramList, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            // 读取取数据库结构
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 得到有多少列
            int columns = resultSetMetaData.getColumnCount();
            // 循环每条结果，并把结果封装成具体的实体对象，最后放到返回集合中
            while (resultSet.next()) {
                SysEntity obj = clazz.newInstance();
                // 对每列循环，把对应的值赋对obj的相应属性
                for (int i = 1; i <= columns; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i);
                    // 循环所有的属性
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Class<?> fieldClass = field.getType();
                        String fieldName = field.getName();
                        // 某个属性有大写，表示需要属性名与数据库列名对应
                        if (isContainsAlphaUpper(fieldName)) {
                            String columnName = toColumnName(fieldName);
                            if (columnName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        } else {
                            // 表示属性名与数据库列名相同
                            if (fieldName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        }
                    }
                }
                SysuserList.add(obj);
            }
            if (SysuserList.isEmpty()) {
                return null;
            } else {
                return SysuserList.get(0);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | SecurityException e) {
            LogUtil.e(LOG, "数据查询失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 修改信息
     *
     * @param sysUserEntity
     */
    public static int changMessege(SysEntity UserEntity, int id) {
        StringBuilder sb = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sb.append("UPDATE sys_user a,sys_course b,sys_role c  ");
        sb.append("SET a.code=?,a.password= ?,a.name =?, a.email =?,   ");
        sb.append("a.phone = ?,a.role_id = ? ,a.state = ?,a.class_id = ?,a.memo= ?  ");
        sb.append("WHERE a.role_id = c.id and a.class_id = b.id and a.id=?  ");
        paramList.add(UserEntity.getCode());
        paramList.add(UserEntity.getPassword());
        paramList.add(UserEntity.getName());
        paramList.add(UserEntity.getEmail());
        paramList.add(UserEntity.getPhone());
        paramList.add(UserEntity.getRole_id());
        paramList.add(UserEntity.getState());
        paramList.add(UserEntity.getClass_id());
        paramList.add(UserEntity.getMemo());
        paramList.add(id);
        //return DBUtil.executeUpdate(sb.toString(), paramList);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sb.toString());
            setParameter(paramList, preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LogUtil.e(LOG, "数据更新失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
    }

    /**
     * 修改用户密码
     *
     * @param sysUserEntity
     * @param id
     * @return
     */
    public static int changPassword(SysEntity sysUserEntity, int id) {
        String sql = "update  sys_user  set  password = ?  where id=?";
        List<Object> paramList = new ArrayList<>();
        paramList.add(sysUserEntity.getPassword());
        paramList.add(id);
        //return DBUtil.executeUpdate(sql, paramList);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParameter(paramList, preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LogUtil.e(LOG, "数据更新失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
    }

    /**
     * 添加用户
     *
     * @param addUserEntity
     * @return
     */
    public static int addUser(SysEntity addUserEntity) {
        //String sql ="INSERT INTO sys_user VALUES(NULL,1, 1, 1, 1, 1, 1, NOW(),1,NOW(),1,1,1,1)";
        String sql = "INSERT INTO sys_user VALUES(NULL,?,?,?,?,?,?,NOW(),?,NOW(),?,?,?,?)";
        List<Object> paramList = new ArrayList<>();
        paramList.add(addUserEntity.getCode());
        paramList.add(addUserEntity.getPassword());
        paramList.add(addUserEntity.getName());
        paramList.add(addUserEntity.getSex());
        paramList.add(addUserEntity.getPhone());
        paramList.add(addUserEntity.getEmail());
        //paramList.add(addUserEntity.getName());

        System.out.println("addUserEntity.getRole_id()" + addUserEntity.getRole_id());
        paramList.add(addUserEntity.getClass_id());
        paramList.add(addUserEntity.getRole_id());
        paramList.add(addUserEntity.getOperate_user_id());
        paramList.add(addUserEntity.getState());
        paramList.add(addUserEntity.getMemo());
        //老徐喊不准用DBUtil .... wo  yue
        //return DBUtil.executeUpdate(sql, paramList);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParameter(paramList, preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LogUtil.e(LOG, "数据更新失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
    }

    /**
     * 判断字符串里是否有大写字母
     *
     * @param fieldName
     * @return
     */
    private static boolean isContainsAlphaUpper(String fieldName) {
        Validate.notNull(fieldName);

        for (int i = 0; i < fieldName.length(); i++) {
            if (CharUtils.isAsciiAlphaUpper(fieldName.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Java属性 -> 数据库字段名：helloWorld -> hello_world
     *
     * @param fieldName
     * @return
     */
    private static String toColumnName(String fieldName) {
        if (fieldName == null) {
            return "";
        }

        char[] chars = fieldName.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (CharUtils.isAsciiAlphaUpper(c)) {
                sb.append("_" + (char) (c + 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 利用反射实现JavaBean的自动赋值
     *
     * @param resultSet
     * @param obj
     * @param columnName
     * @param field
     * @param fieldClass
     * @throws IllegalAccessException
     * @throws SQLException
     */
    private static <T> void setFieldValue(ResultSet resultSet, T obj, String columnName, Field field,
                                          Class<?> fieldClass) throws IllegalAccessException, SQLException {
        Validate.notNull(resultSet, "resultSet" + Constant.VALIDATE_NOT_NULL);
        Validate.notNull(obj, "obj" + Constant.VALIDATE_NOT_NULL);
        Validate.notBlank(columnName, "columnName" + Constant.VALIDATE_NOT_BLANK);
        Validate.notNull(field, "field" + Constant.VALIDATE_NOT_NULL);
        Validate.notNull(fieldClass, "fieldClass" + Constant.VALIDATE_NOT_NULL);

        // FIXME 根据业务扩展
        if (fieldClass.equals(int.class) || fieldClass.equals(Integer.class)) {
            field.set(obj, resultSet.getInt(columnName));
        } else if (fieldClass.equals(String.class)) {
            field.set(obj, resultSet.getString(columnName));
        } else if (fieldClass.equals(BigDecimal.class)) {
            field.set(obj, resultSet.getBigDecimal(columnName));
        } else if (fieldClass.equals(Date.class)) {
            field.set(obj, resultSet.getTimestamp(columnName));
        }
    }

    /**
     * 参数设置
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

    /**
     * 获取主菜单
     *
     * @param username
     * @return
     */
    public static List<Map<String, Object>> getMainMenu(String username) {
        String sql = "select * from rightinfo where rightId in (select rightId from rolerightinfo where roleId = (select role_id from sys_user where code=?)) and parentId='root'";
        return executeQuery(sql, username);
    }

    /**
     * 获取子菜单
     *
     * @param username
     * @return
     */
    public static List<Map<String, Object>> getChildMenu(String username) {
        String sql = "select * from rightinfo where rightId in (select rightId from rolerightinfo where roleId = (select role_id from sys_user where code=?)) and parentId<>'root'";
        return executeQuery(sql, username);
    }

    /**
     * 执行查询SQL指令操作
     *
     * @param sql        调用处传入SQL命令
     * @param parameters 与SQL命令对应的SQL参数数组
     * @return 返回才查询结果集合
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... parameters) {
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
                        row.put(rsd.getColumnName(i + 1), rs.getObject(rsd.getColumnName(i + 1)));
                    }

                    // 把当前遍历行组成集合对象存储到table集合中
                    table.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pst, rs);
        }
        return table;

    }

    /**
     * 查询需要授权的角色
     *
     * @return
     */
    public static List<SysRoleEntity> getRoles() {
        String sql = "SELECT * FROM sys_role ";
        //return DBUtil.getUniqueResult(SysEntity.class, sql, id);
        Class<SysRoleEntity> clazz = SysRoleEntity.class;
        List<SysRoleEntity> SysRoleEntityList = new ArrayList<SysRoleEntity>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //setParameter(paramList, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            // 读取取数据库结构
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 得到有多少列
            int columns = resultSetMetaData.getColumnCount();
            // 循环每条结果，并把结果封装成具体的实体对象，最后放到返回集合中
            while (resultSet.next()) {
                SysRoleEntity obj = clazz.newInstance();
                // 对每列循环，把对应的值赋对obj的相应属性
                for (int i = 1; i <= columns; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i);
                    // 循环所有的属性
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Class<?> fieldClass = field.getType();
                        String fieldName = field.getName();
                        // 某个属性有大写，表示需要属性名与数据库列名对应
                        if (isContainsAlphaUpper(fieldName)) {
                            String columnName = toColumnName(fieldName);
                            if (columnName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        } else {
                            // 表示属性名与数据库列名相同
                            if (fieldName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        }
                    }
                }
                SysRoleEntityList.add(obj);
            }
            return SysRoleEntityList;
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | SecurityException e) {
            LogUtil.e(LOG, "数据查询失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 获取当前系统所有的主菜单信息
     *
     * @return
     */
    public static List<Map<String, Object>> getMainMenus() {
        return executeQuery("select * from rightinfo where parentId='root'");
    }

    /**
     * 获取当前系统中所有的子菜单
     *
     * @return
     */
    public static List<Map<String, Object>> getChildMenus() {
        return executeQuery("select * from rightinfo where parentId<>'root'");
    }

    /**
     * 根据角色编号查询所有的主菜单
     *
     * @param roleId
     * @return
     */
    public static List<Map<String, Object>> getMainMenuByRoleId(String roleId) {
        return executeQuery("select * from rightinfo where rightId in (select rightId from rolerightInfo where roleId=" + roleId + ") and parentId='root'");
    }

    /**
     * 根据角色编号获取所有子菜单
     *
     * @param roleId
     * @return
     */
    public static List<Map<String, Object>> getChildMenuByRoleId(String roleId) {
        return executeQuery("select * from rightInfo where rightId in (select rightId from rolerightInfo where roleId=" + roleId + ") and parentId<>'root'");
    }

    /**
     * 定义判断该角色是否已经分配权限
     */
    public static boolean isGrant(String roleId) {
        String sql = "select 1 from roleRightinfo where roleId=" + roleId + "";
        return executeQuery(sql).size() > 0;
    }

    /**
     * 定义根据角色删除该角色所有的权限方法
     */
    public static boolean deleteAllGrant(String roleId) {
        String sql = "delete from roleRightinfo where roleId=" + roleId + "";
        return executeUpdate(sql) > 0;
    }

    /**
     * 定义给角色添加权限的方法
     */
    public static int grant(RoleRightEntity roleRight) {
        String sql = "insert into roleRightinfo values(null,?,?)";
        List<Object> paramList = new ArrayList<>();
        paramList.add(roleRight.getRightId());
        paramList.add(roleRight.getRoleId());
        //老徐喊不准用DBUtil .... wo  yue
        //return DBUtil.executeUpdate(sql, paramList);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParameter(paramList, preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LogUtil.e(LOG, "数据更新失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
    }

    /**
     * 执行简单SQL的增，删，改
     *
     * @param sql        调用处传入SQL命令
     * @param parameters 与SQL命令对应的SQL参数数组
     * @return 返回操作受影响的行数
     */
    public static int executeUpdate(String sql, Object... parameters) {
        int row = 0;

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            // 判断是否设置参数
		/*	if (parameters!=null && parameters.length>0) {
				for(int i=0;i<parameters.length;i++) {
					pst.setObject(i+1, parameters[i]);
				}
			}*/

            row = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pst);
        }

        return row;
    }

    /**
     * 查询班级名字
     *
     * @return
     */
    public static List<ClassEntity> getClassName() {
        String sql = "SELECT * FROM sys_class ";
        //return DBUtil.getUniqueResult(SysEntity.class, sql, id);
        List<Object> paramList = new ArrayList<>();
        Class<ClassEntity> clazz = ClassEntity.class;
        List<ClassEntity> ClassEntityList = new ArrayList<ClassEntity>();
        //paramList.add(id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //setParameter(paramList, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            // 读取取数据库结构
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 得到有多少列
            int columns = resultSetMetaData.getColumnCount();
            // 循环每条结果，并把结果封装成具体的实体对象，最后放到返回集合中
            while (resultSet.next()) {
                ClassEntity obj = clazz.newInstance();
                // 对每列循环，把对应的值赋对obj的相应属性
                for (int i = 1; i <= columns; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i);
                    // 循环所有的属性
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Class<?> fieldClass = field.getType();
                        String fieldName = field.getName();
                        // 某个属性有大写，表示需要属性名与数据库列名对应
                        if (isContainsAlphaUpper(fieldName)) {
                            String columnName = toColumnName(fieldName);
                            if (columnName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        } else {
                            // 表示属性名与数据库列名相同
                            if (fieldName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        }
                    }
                }
                ClassEntityList.add(obj);
            }
            return ClassEntityList;

        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | SecurityException e) {
            LogUtil.e(LOG, "数据查询失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 获取试卷名
     */
    public static List<ExaminationPaperEntity> ExaminationPaperName() {
        String sql = "SELECT * FROM qes_exam_paper ";
        //return DBUtil.getUniqueResult(SysEntity.class, sql, id);
        Class<ExaminationPaperEntity> clazz = ExaminationPaperEntity.class;
        List<ExaminationPaperEntity> ClassEntityList = new ArrayList<ExaminationPaperEntity>();
        //paramList.add(id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //setParameter(paramList, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            // 读取取数据库结构
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // 得到有多少列
            int columns = resultSetMetaData.getColumnCount();
            // 循环每条结果，并把结果封装成具体的实体对象，最后放到返回集合中
            while (resultSet.next()) {
                ExaminationPaperEntity obj = clazz.newInstance();
                // 对每列循环，把对应的值赋对obj的相应属性
                for (int i = 1; i <= columns; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i);
                    // 循环所有的属性
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Class<?> fieldClass = field.getType();
                        String fieldName = field.getName();
                        // 某个属性有大写，表示需要属性名与数据库列名对应
                        if (isContainsAlphaUpper(fieldName)) {
                            String columnName = toColumnName(fieldName);
                            if (columnName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        } else {
                            // 表示属性名与数据库列名相同
                            if (fieldName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        }
                    }
                }
                ClassEntityList.add(obj);
            }
            return ClassEntityList;

        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | SecurityException e) {
            LogUtil.e(LOG, "数据查询失败", e);
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }


}//dao

