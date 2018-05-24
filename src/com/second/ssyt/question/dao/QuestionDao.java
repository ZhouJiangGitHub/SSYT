package com.second.ssyt.question.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.second.ssyt.common.Constant;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.common.exception.CommonRuntimeException;
import com.second.ssyt.common.exception.DaoRuntimeException;
import com.second.ssyt.common.util.DBUtil;
import com.second.ssyt.question.entity.QuestionEntity;

public class QuestionDao {

    /**
     * 普通查询
     *
     * @return
     * @throws SQLException
     */
    public static List<QuestionEntity> list() throws SQLException {
        String sql = "select q.id,q.question,q.question_type as 'questionType' ,s.name as 'sysCourse$Name' ,q.difficulty,q.state from qes_qestions q,sys_course s where s.id = q.course_id order by q.id desc";
        List<QuestionEntity> qesQustionList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yec", "root", "root");
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                QuestionEntity qesQuestion = new QuestionEntity();
                qesQuestion.setId(resultSet.getInt("id"));
                qesQuestion.setQuestion(resultSet.getString("question"));
                qesQuestion.setQuestionType(resultSet.getShort("questionType"));
                qesQuestion.setSysCourse$Name(resultSet.getString("sysCourse$Name"));
                qesQuestion.setDifficulty(resultSet.getShort("difficulty"));
                qesQuestion.setState(resultSet.getShort("state"));
                qesQustionList.add(qesQuestion);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            qesQustionList = Collections.emptyList();
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
        return qesQustionList;
    }

    /**
     * 分页
     *
     * @param pageSize
     * @param pageNo
     * @return
     */
    public PageModel<QuestionEntity> listPage(int pageSize, int pageNo) {
        List<QuestionEntity> questionList = new ArrayList<>();
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
            sb.append("select * ");
            sb.append("from qes_qestions q,sys_course s ");
            sb.append("where s.id = q.course_id order by q.id desc ");
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
                QuestionEntity question = new QuestionEntity();
                question.setId(resultSet.getInt("id"));
                question.setAttachment(resultSet.getString("attachment"));
                question.setQuestion(resultSet.getString("question"));
                question.setQuestionType(resultSet.getShort("question_type"));
                question.setCourseId(resultSet.getShort("course_id"));
                question.setAnswerA(resultSet.getString("answer_a"));
                question.setAnswerB(resultSet.getString("answer_b"));
                question.setAnswerC(resultSet.getString("answer_c"));
                question.setAnswerD(resultSet.getString("answer_d"));
                question.setAnswer(resultSet.getString("answer"));
                question.setDifficulty(resultSet.getShort("difficulty"));
                question.setAnalysis(resultSet.getString("analysis"));
                question.setKeywords(resultSet.getString("keywords"));
                question.setOperateUserId(resultSet.getShort("operate_user_id"));
                question.setState(resultSet.getShort("state"));
                questionList.add(question);
            }

            return new PageModel<QuestionEntity>(questionList, pageNo, pageSize, getAllcount());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            questionList = Collections.emptyList();
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
     * 查询总记录
     * 管理数据总数
     *
     * @param
     * @return
     */
    private static int getAllcount() {
        String sql = "SELECT COUNT(*) FROM  qes_qestions";

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
                    .prepareStatement("UPDATE qes_qestions SET state=? WHERE id=?");
            preparedStatement.setInt(1, state == 2 ? 2 : 1);
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

    public List<QuestionEntity> details(int id) {
        List<QuestionEntity> questionList = new ArrayList<>();
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
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT u.id,u.name as 'user$Name',c.operate_user_id ");
            sb.append("as 'operateUserId',q.question,q.attachment,c.name as 'sysCourse$Name',q.question_type as 'questionType', ");
            sb.append("q.answer_a as'answerA',q.answer_b as'answerB', ");
            sb.append("q.answer_c as'answerC',q.answer_d as'answerD',q.answer, ");
            sb.append("q.difficulty,q.analysis,q.keywords,q.state,q.memo,q.operate_time ");
            sb.append("as 'operateTime' FROM sys_course c,qes_qestions q,sys_user u ");
            sb.append("WHERE c.id = q.course_id AND c.operate_user_id = u.id and q.id = ? ");
            preparedStatement = connection.prepareStatement(sb.toString());
            paramList.add(id);
            DBUtil.setParameter(paramList, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                QuestionEntity question = new QuestionEntity();
                question.setId(resultSet.getInt("id"));
                question.setUser$Name(resultSet.getString("user$Name"));
                question.setQuestion(resultSet.getString("question"));
                question.setOperateUserId(resultSet.getInt("operateUserId"));
                question.setAttachment(resultSet.getString("attachment"));
                question.setSysCourse$Name(resultSet.getString("sysCourse$Name"));
                question.setQuestionType(resultSet.getShort("questionType"));
                question.setAnswerA(resultSet.getString("answerA"));
                question.setAnswerB(resultSet.getString("answerB"));
                question.setAnswerC(resultSet.getString("answerC"));
                question.setAnswerD(resultSet.getString("answerD"));
                question.setAnswer(resultSet.getString("answer"));
                question.setDifficulty(resultSet.getShort("difficulty"));
                question.setAnalysis(resultSet.getString("analysis"));
                question.setKeywords(resultSet.getString("keywords"));
                question.setState(resultSet.getShort("state"));
                question.setMemo(resultSet.getString("memo"));
                question.setOperateTime(resultSet.getDate("operateTime"));
                questionList.add(question);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // userList = new ArrayList<>();
            questionList = Collections.emptyList();
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
        return questionList;
    }

    /**
     * 试题新增
     *
     * @param questionEntity
     * @return
     */
    public int add1(QuestionEntity questionEntity) {
        String sql = "INSERT INTO qes_qestions VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?)";

        List<Object> paramList = new ArrayList<>();
        paramList.add(questionEntity.getQuestion());
        paramList.add(questionEntity.getAttachment());
        paramList.add(questionEntity.getQuestionType());
        //paramList.add(questionEntity.getSysCourse$Name());
        paramList.add(questionEntity.getAnswerA());
        paramList.add(questionEntity.getAnswerB());
        paramList.add(questionEntity.getAnswerC());
        paramList.add(questionEntity.getAnswerD());
        paramList.add(questionEntity.getAnswer());
        paramList.add(questionEntity.getDifficulty());
        paramList.add(questionEntity.getAnalysis());
        paramList.add(questionEntity.getKeywords());
        paramList.add(questionEntity.getMemo());
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

    private static void setParameter(List<Object> paramList, PreparedStatement preparedStatement) throws SQLException {
        if (paramList != null) {
            for (int i = 0; i < paramList.size(); i++) {
                preparedStatement.setObject(i + 1, paramList.get(i));
            }
        }

    }

    /**
     * 条件查询
     *
     * @param que
     * @return
     */
    public PageModel<QuestionEntity> listParameterPage(int pageSize,
                                                       int pageNo, QuestionEntity questionEn) {
        List<QuestionEntity> questionList = new ArrayList<>();
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
            sb.append("select * ");
            sb.append("from qes_qestions q,sys_course s ");
            sb.append("where s.id = q.course_id ");
            if (StringUtils.isNotBlank(questionEn.getQuestion())) {
                sb.append("AND q.question LIKE ? ");
                paramList.add("%" + questionEn.getQuestion() + "%");
            }
            Short questionType = questionEn.getQuestionType();
            if (questionType != 0) {
                sb.append("AND q.question_type LIKE ? ");
                paramList.add("%" + questionEn.getQuestionType() + "%");
            }
            if (questionEn.getCourseId() != 0) {
                sb.append("AND q.course_id LIKE ? ");
                paramList.add("%" + questionEn.getCourseId() + "%");
            }
            if (questionEn.getState() != 0) {
                sb.append("AND q.state LIKE ? ");
                paramList.add("%" + questionEn.getState() + "%");
            }
            if (questionEn.getDifficulty() != 0) {
                sb.append("AND q.difficulty LIKE ?");
                paramList.add("%" + questionEn.getDifficulty() + "%");
            }
            sb.append("LIMIT ?,? ");
            paramList.add((pageNo - 1) * pageSize);
            paramList.add(pageSize);
            preparedStatement = connection.prepareStatement(sb.toString());
            setParameter(paramList, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                QuestionEntity question = new QuestionEntity();
                question.setId(resultSet.getInt("id"));
                question.setAttachment(resultSet.getString("question"));
                question.setQuestion(resultSet.getString("attachment"));
                question.setQuestionType(resultSet.getShort("question_type"));
                question.setCourseId(resultSet.getShort("course_id"));
                question.setAnswerA(resultSet.getString("answer_a"));
                question.setAnswerB(resultSet.getString("answer_b"));
                question.setAnswerC(resultSet.getString("answer_c"));
                question.setAnswerD(resultSet.getString("answer_d"));
                question.setAnswer(resultSet.getString("answer"));
                question.setDifficulty(resultSet.getShort("difficulty"));
                question.setAnalysis(resultSet.getString("analysis"));
                question.setKeywords(resultSet.getString("keywords"));
                question.setOperateUserId(resultSet.getShort("operate_user_id"));
                question.setState(resultSet.getShort("state"));
                questionList.add(question);
            }
            return new PageModel<QuestionEntity>(questionList, pageNo, pageSize, getParameterAllcount(questionEn));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // userList = new ArrayList<>();
            questionList = Collections.emptyList();
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
     * 条件查询总数
     *
     * @return
     */
    private static int getParameterAllcount(QuestionEntity questionEn) {
        List<Object> paramList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("select COUNT(*) ");
        sb.append("from qes_qestions q,sys_course s ");
        sb.append("where s.id = q.course_id ");
        if (StringUtils.isNotBlank(questionEn.getQuestion())) {
            sb.append("AND q.question LIKE ? ");
            paramList.add("%" + questionEn.getQuestion() + "%");
        }
        Short questionType = questionEn.getQuestionType();
        if (questionType != 0) {
            sb.append("AND q.question_type LIKE ? ");
            paramList.add("%" + questionEn.getQuestionType() + "%");
        }
        if (questionEn.getCourseId() != 0) {
            sb.append("AND q.course_id LIKE ? ");
            paramList.add("%" + questionEn.getCourseId() + "%");
        }
        if (questionEn.getState() != 0) {
            sb.append("AND q.state LIKE ? ");
            paramList.add("%" + questionEn.getState() + "%");
        }
        if (questionEn.getDifficulty() != 0) {
            sb.append("AND q.difficulty LIKE ?");
            paramList.add("%" + questionEn.getDifficulty() + "%");
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
     * 修改试题
     *
     * @param id
     * @param questionEntity
     */
    public int updateMessage(int id, QuestionEntity questionEntity) {
        StringBuilder sb = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sb.append("update qes_qestions set question=?,attachment=?,question_type=?, ");
        sb.append("answer_a=?,answer_b=?,answer_c=?,answer_d=?,answer=?, ");
        sb.append("difficulty=?,analysis=?,keywords=?,");
        sb.append("state=?,memo=? where id=? ");
        paramList.add(questionEntity.getQuestion());
        paramList.add(questionEntity.getAttachment());
        paramList.add(questionEntity.getQuestionType());
        paramList.add(questionEntity.getAnswerA());
        paramList.add(questionEntity.getAnswerB());
        paramList.add(questionEntity.getAnswerC());
        paramList.add(questionEntity.getAnswerD());
        paramList.add(questionEntity.getAnswer());
        paramList.add(questionEntity.getDifficulty());
        paramList.add(questionEntity.getAnalysis());
        paramList.add(questionEntity.getKeywords());
        paramList.add(questionEntity.getState());
        paramList.add(questionEntity.getMemo());
        paramList.add(id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        System.out.println("sssssssssssss");
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

    /**
     * 查询需要修改的信息
     *
     * @param id
     * @return
     */
    public QuestionEntity getMessage(int id) {
        String sql = "SELECT * FROM qes_qestions WHERE id=?";
        //return DBUtil.getUniqueResult(SysEntity.class, sql, id);
        List<Object> paramList = new ArrayList<>();
        Class<QuestionEntity> clazz = QuestionEntity.class;
        List<QuestionEntity> questionEntityList = new ArrayList<QuestionEntity>();
        paramList.add(id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParameter(paramList, preparedStatement);
            resultSet = preparedStatement.executeQuery();
            // ��ȡȡ���ݿ�ṹ
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            // �õ��ж�����
            int columns = resultSetMetaData.getColumnCount();
            // ѭ��ÿ����������ѽ����װ�ɾ����ʵ��������ŵ����ؼ�����
            while (resultSet.next()) {
                QuestionEntity obj = clazz.newInstance();
                // ��ÿ��ѭ�����Ѷ�Ӧ��ֵ����obj����Ӧ����
                for (int i = 1; i <= columns; i++) {
                    String columnLabel = resultSetMetaData.getColumnLabel(i);
                    // ѭ�����е�����
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Class<?> fieldClass = field.getType();
                        String fieldName = field.getName();
                        // ĳ�������д�д����ʾ��Ҫ�����������ݿ�������Ӧ
                        if (isContainsAlphaUpper(fieldName)) {
                            String columnName = toColumnName(fieldName);
                            if (columnName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        } else {
                            // ��ʾ�����������ݿ�������ͬ
                            if (fieldName.equals(columnLabel)) {
                                setFieldValue(resultSet, obj, columnLabel, field, fieldClass);
                                break;
                            }
                        }
                    }
                }
                questionEntityList.add(obj);
            }
            if (questionEntityList.isEmpty()) {
                return null;
            } else {
                return questionEntityList.get(0);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | SecurityException e) {
            throw new CommonRuntimeException();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
    }


    /**
     * �ж��ַ������Ƿ��д�д��ĸ
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
     * Java���� -> ���ݿ��ֶ�����helloWorld -> hello_world
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
     * ���÷���ʵ��JavaBean���Զ���ֵ
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

        // FIXME ����ҵ����չ
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
     * Excel试题批量导入
     *
     * @param questionList
     * @return
     */
    public void insertQuesionForBatch(List<List<Object>> questionList) {


        String sql = "insert into qes_qestions values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        for (int i = 0; i < questionList.size(); i++) {

            try {
                DBUtil.executeUpdate(sql, questionList.get(i));
            } catch (DaoRuntimeException e) {
                System.out.println("Excel试题批量导入失败！");
                e.printStackTrace();
            }
        }


    }


}


