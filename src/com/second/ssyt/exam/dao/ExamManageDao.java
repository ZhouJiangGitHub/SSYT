package com.second.ssyt.exam.dao;

import java.lang.reflect.Field;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import com.second.ssyt.Class.entity.ClassEntity;
import com.second.ssyt.common.Constant;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.common.exception.CommonRuntimeException;
import com.second.ssyt.common.util.DBUtil;
import com.second.ssyt.common.util.LogUtil;
import com.second.ssyt.exam.entity.ExamManageEntity;

public class ExamManageDao {

    private static final Logger LOG = Logger.getLogger(DBUtil.class);


    /**
     * 分页查询管理信息
     *
     * @param pageNo
     * @param pageSize
     * @return 分页组件
     */
    public static PageModel<ExamManageEntity> listExamManageByPage(int pageNo, int pageSize) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("b.name AS 'paperName', ");
        sb.append("a.id, ");
        sb.append("a.exam_time_start, ");
        sb.append("a.exam_time_stop, ");
        sb.append("a.exam_classroom, ");
        sb.append("a.exam_paper_id,  ");
        sb.append("a.to_user_ids,  ");
        sb.append("a.to_class_id,  ");
        sb.append("a.operate_user_id,  ");
        sb.append("a.operate_time,  ");
        sb.append("a.state,  ");
        sb.append("a.memo  ");
        sb.append("FROM  exam_plan a , qes_exam_paper b  ");
        sb.append("Where a.exam_paper_id = b.id LIMIT ?,?  ");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sb.toString());
            preparedStatement.setInt(1, (pageNo - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            List<ExamManageEntity> examManageList = new ArrayList<>();
            while (resultSet.next()) {
                ExamManageEntity examManagelist = new ExamManageEntity();
                examManagelist.setId(resultSet.getInt("id"));
                examManagelist.setExam_time_start(resultSet.getDate("exam_time_start"));
                examManagelist.setExam_time_stop(resultSet.getDate("exam_time_stop"));
                examManagelist.setExam_classroom(resultSet.getString("exam_classroom"));
                //examManagelist.setExam_paper_id(resultSet.getInt("exam_paper_id"));
                examManagelist.setPaperName(resultSet.getString("paperName"));
                examManagelist.setTo_user_ids(resultSet.getString("to_user_ids"));
                examManagelist.setTo_class_id(resultSet.getInt("to_class_id"));
                examManagelist.setOperate_user_id(resultSet.getInt("operate_user_id"));
                examManagelist.setOperate_time(resultSet.getDate("operate_time"));
                examManagelist.setState(resultSet.getInt("state"));
                examManagelist.setMemo(resultSet.getString("memo"));
                examManageList.add(examManagelist);
            }
            // 构建分页实体
            return new PageModel<ExamManageEntity>(examManageList, pageNo, pageSize,
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
    public static PageModel<ExamManageEntity> listManageVerifyByPage(
            ExamManageEntity findSys, int pageNo, int PageSize) {
        List<Object> paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        //晚点多表拼C
        sb.append("SELECT ");
        sb.append("b.name AS 'paperName', ");
        sb.append("a.id, ");
        sb.append("a.exam_time_start, ");
        sb.append("a.exam_time_stop, ");
        sb.append("a.exam_classroom, ");
        sb.append("a.exam_paper_id,  ");
        sb.append("a.to_user_ids,  ");
        sb.append("a.to_class_id,  ");
        sb.append("a.operate_user_id,  ");
        sb.append("a.operate_time,  ");
        sb.append("a.state,  ");
        sb.append("a.memo  ");
        sb.append("FROM  exam_plan a , qes_exam_paper b  ");
        sb.append("Where a.exam_paper_id = b.id   ");
        //sb.append("SELECT * FROM  exam_plan a, qes_exam_paper b  where a.exam_paper_id = b.id   ");
        int mold = findSys.getExam_paper_id();
        if (mold != 0) {
            sb.append("and a.exam_paper_id LIKE ?  ");
            paramList.add("%" + findSys.getExam_paper_id() + "%");
        }
        int state = findSys.getState();
        if (state != 0) {
            sb.append("and a.state LIKE ?  ");
            paramList.add("%" + findSys.getState() + "%");
        }
		/*String date = findSys.getExam_time_start();
		if (StringUtils.isNoneBlank(date)) {
			sb.append("and a.exam_time_start LIKE ?  ");
			paramList.add("%"+ findSys.getExam_time_start() + "%");
		}*/
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
            List<ExamManageEntity> examManageList = new ArrayList<>();
            while (resultSet.next()) {
                ExamManageEntity examManagelist = new ExamManageEntity();
                examManagelist.setId(resultSet.getInt("id"));
                examManagelist.setExam_time_start(resultSet.getDate("exam_time_start"));
                examManagelist.setExam_time_stop(resultSet.getDate("exam_time_stop"));
                examManagelist.setExam_classroom(resultSet.getString("exam_classroom"));
                examManagelist.setExam_paper_id(resultSet.getInt("exam_paper_id"));
                examManagelist.setPaperName(resultSet.getString("paperName"));
                examManagelist.setTo_user_ids(resultSet.getString("to_user_ids"));
                examManagelist.setTo_class_id(resultSet.getInt("to_class_id"));
                examManagelist.setOperate_user_id(resultSet.getInt("operate_user_id"));
                examManagelist.setOperate_time(resultSet.getDate("operate_time"));
                examManagelist.setState(resultSet.getInt("state"));
                examManagelist.setMemo(resultSet.getString("memo"));
                examManageList.add(examManagelist);
            }
            // 构建分页实体
            return new PageModel<ExamManageEntity>(examManageList, pageNo, PageSize,
                    listExamManageVerifyByPageCount(findSys));
        } catch (Exception e) {
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
    private static int listExamManageVerifyByPageCount(ExamManageEntity findSys) {
        List<Object> paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  ");
        sb.append("COUNT(*)  ");
        sb.append("FROM exam_plan a, qes_exam_paper b  where a.exam_paper_id = b.id  ");
        int mold = findSys.getExam_paper_id();
        if (mold != 0) {
            sb.append("and a.exam_paper_id LIKE ?  ");
            paramList.add("%" + findSys.getExam_paper_id() + "%");
        }
        int state = findSys.getState();
        if (state != 0) {
            sb.append("and a.state LIKE ?  ");
            paramList.add("%" + findSys.getState() + "%");
        }
		/*String date = findSys.getExam_time_start();
		if (StringUtils.isNoneBlank(date)) {
			sb.append("and a.exam_time_start LIKE ?  ");
			paramList.add("%"+ findSys.getExam_time_start() + "%");
		}*/
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
     * 改变用户状态
     *
     * @param id
     * @return
     */
    public static int change(int status, int id) {
        String sql = " update exam_plan set state=? WHERE id=?";
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
     * 获取需要修改的考试安排信息
     *
     * @param id
     * @return
     */
    public static ExamManageEntity getExamManageMessage(int id) {
        //String sql = "SELECT * FROM exam_plan WHERE id=?";
        List<Object> paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("b.name AS 'paperName', ");
        sb.append("d.name AS 'className', ");
        sb.append("c.name AS 'recetName', ");
        sb.append("a.id, ");
        sb.append("a.exam_time_start, ");
        sb.append("a.exam_time_stop, ");
        sb.append("a.exam_classroom, ");
        sb.append("a.exam_paper_id,  ");
        sb.append("a.to_user_ids,  ");
        sb.append("a.to_class_id,  ");
        sb.append("a.operate_user_id,  ");
        sb.append("a.operate_time,  ");
        sb.append("a.state,  ");
        sb.append("a.memo  ");
        sb.append("FROM  exam_plan a , qes_exam_paper b ,sys_user c,sys_class d ");
        sb.append("Where a.exam_paper_id = b.id and b.opreate_user_id = c.id   ");
        sb.append("and c.class_id = d.id and a.id=?");
        paramList.add(id);
        //return DBUtil.getUniqueResult(SysEntity.class, sql, id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sb.toString());
            setParameter(paramList, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            List<ExamManageEntity> examDetailList = new ArrayList<>();
            while (resultSet.next()) {
                ExamManageEntity examManagelist = new ExamManageEntity();
                examManagelist.setId(resultSet.getInt("id"));
                examManagelist.setExam_time_start(resultSet.getDate("exam_time_start"));
                examManagelist.setExam_time_stop(resultSet.getDate("exam_time_stop"));
                examManagelist.setExam_classroom(resultSet.getString("exam_classroom"));
                examManagelist.setExam_paper_id(resultSet.getInt("exam_paper_id"));
                examManagelist.setPaperName(resultSet.getString("paperName"));
                examManagelist.setTo_user_ids(resultSet.getString("to_user_ids"));
                examManagelist.setTo_class_id(resultSet.getInt("to_class_id"));
                examManagelist.setClassName(resultSet.getString("className"));
                examManagelist.setOperate_user_id(resultSet.getInt("operate_user_id"));
                examManagelist.setRecetName(resultSet.getString("recetName"));
                examManagelist.setOperate_time(resultSet.getDate("operate_time"));
                examManagelist.setState(resultSet.getInt("state"));
                examManagelist.setMemo(resultSet.getString("memo"));
                examDetailList.add(examManagelist);
            }
            // 构建查询实体
            if (examDetailList.isEmpty()) {
                return null;
            } else {
                return examDetailList.get(0);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }

    }

    /**
     * 获取需要的详情考试安排信息
     *
     * @param id
     * @return
     */
    public static ExamManageEntity get(int id) {
        String sql = "SELECT * FROM exam_plan WHERE id=?";
        //return DBUtil.getUniqueResult(SysEntity.class, sql, id);
        List<Object> paramList = new ArrayList<>();
        Class<ExamManageEntity> clazz = ExamManageEntity.class;
        List<ExamManageEntity> examDetailList = new ArrayList<ExamManageEntity>();
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
                ExamManageEntity obj = clazz.newInstance();
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
                examDetailList.add(obj);
            }
            if (examDetailList.isEmpty()) {
                return null;
            } else {
                return examDetailList.get(0);
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
     * 修改用户
     *
     * @param addExamManageEntity
     * @return
     */
    public static int updateExamManage(ExamManageEntity addExamManageEntity, int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE exam_plan ");
        sb.append("SET exam_time_start=?, ");
        sb.append("exam_time_stop=?,  ");
        sb.append("exam_classroom=?,  ");
        sb.append("exam_paper_id=?,  ");
        sb.append("to_user_ids=?,  ");
        sb.append("to_class_id=?,  ");
        sb.append("operate_user_id=?,  ");
        sb.append("operate_time=NOW(), ");
        sb.append("state=?, ");
        sb.append("memo=?");
        sb.append("WHERE id=? ");
        List<Object> paramList = new ArrayList<>();
        paramList.add(addExamManageEntity.getExam_time_start());
        paramList.add(addExamManageEntity.getExam_time_stop());
        paramList.add(addExamManageEntity.getExam_classroom());
        //System.out.println("addExamManageEntity.getSex()" + addExamManageEntity.getSex());
        paramList.add(addExamManageEntity.getExam_paper_id());
        paramList.add(addExamManageEntity.getTo_user_ids());
        paramList.add(addExamManageEntity.getTo_class_id());
        paramList.add(addExamManageEntity.getOperate_user_id());
        //paramList.add(addExamManageEntity.getOperate_time());
        paramList.add(addExamManageEntity.getState());
        paramList.add(addExamManageEntity.getMemo());
        paramList.add(id);
        //老徐喊不准用DBUtil .... wo  yue
        //return DBUtil.executeUpdate(sql, paramList);
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
     * 添加用户
     *
     * @param addExamManageEntity
     * @return
     */
    public static int addExamManage(ExamManageEntity addExamManageEntity) {
        //String sql ="INSERT INTO sys_user VALUES(NULL,1, 1, 1, 1, 1, 1, NOW(),1,NOW(),1,1,1,1)";
        String sql = "INSERT INTO exam_plan VALUES(NULL,?,?,?,?,?,?,?,NOW(),?,?)";
        List<Object> paramList = new ArrayList<>();
        paramList.add(addExamManageEntity.getExam_time_start());
        paramList.add(addExamManageEntity.getExam_time_stop());
        paramList.add(addExamManageEntity.getExam_classroom());
        paramList.add(addExamManageEntity.getExam_paper_id());
        paramList.add(addExamManageEntity.getTo_user_ids());
        paramList.add(addExamManageEntity.getTo_class_id());
        paramList.add(addExamManageEntity.getOperate_user_id());
        //paramList.add(addExamManageEntity.getOperate_time());
        paramList.add(addExamManageEntity.getState());
        paramList.add(addExamManageEntity.getMemo());
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
     * 查询所有班级
     */
    public static List<ClassEntity> getAllClass() {
        String sql = "select * from sys_class";
        return DBUtil.executeQuery(ClassEntity.class, sql);
    }

    /**
     * 获取信息
     *
     * @return
     */
    public static List<ExamManageEntity> getExamManage() {
        String sql = "SELECT * FROM sys_class ";
        //return DBUtil.getUniqueResult(SysEntity.class, sql, id);
        List<Object> paramList = new ArrayList<>();
        Class<ExamManageEntity> clazz = ExamManageEntity.class;
        List<ExamManageEntity> ClassEntityList = new ArrayList<ExamManageEntity>();
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
                ExamManageEntity obj = clazz.newInstance();
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

}
