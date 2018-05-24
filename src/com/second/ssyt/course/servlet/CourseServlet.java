package com.second.ssyt.course.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.second.ssyt.common.exception.DaoException;
import com.second.ssyt.course.dao.CourseDao;
import com.second.ssyt.course.entity.CourseEntity;
import com.second.ssyt.login.entity.UserEntity;

/**
 * Servlet implementation class CourseServlet
 */
public class CourseServlet extends HttpServlet {

    CourseDao courseDao = new CourseDao();
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String command = request.getParameter("command");

        if ("List".equals(command)) {
            courseList(request, response);
        } else if ("getCourse".equals(command)) {

            getCourse(request, response);
        } else if ("courseUpdate".equals(command)) {

            courseUpdate(request, response);
        } else if ("validateCourse".equals(command)) {

            validateCourse(request, response);
        } else if ("courseInsert".equals(command)) {

            courseInsert(request, response);
        }
    }


    /**
     * 课程新增
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void courseInsert(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {


        //获取参数
        List<Object> parameters = new ArrayList<>();

        String pId = request.getParameter("newPId");

        String courseName = request.getParameter("newName");

        String memo = request.getParameter("memo");

        int operateUserId = ((UserEntity) request.getSession().getAttribute("user")).getId();

        Date operateTime = new Date();

        String stateStr = request.getParameter("state");

        if ("有效".equals(stateStr)) {
            stateStr = "1";
        } else if ("无效".equals(stateStr)) {
            stateStr = "2";
        } else {
            new RuntimeException("存在编码错误");
        }

        parameters.add(pId);
        parameters.add(1);
        parameters.add(courseName);
        parameters.add(operateUserId);
        parameters.add(operateTime);
        parameters.add(stateStr);
        parameters.add(memo);
        //调用dao层，添加课程

        int rows = courseDao.insertCourse(parameters);

        if (rows < 0) {
            new DaoException("课程新增失败！");
        }


        //（注:json会吧”0“转换为'null'）

        //更新父节点为非叶子节点

        if (courseDao.updateParentIsLeaf(pId) < 1) {
            new DaoException("叶子节点修改为非叶子节点出现错误！");
        }

        request.getRequestDispatcher("CourseServlet?command=List").forward(request, response);


    }


    /**
     * 课程唯一验证
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void validateCourse(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        //获取参数
        String courseName = request.getParameter("courseName");
        String courseId = request.getParameter("courseId");

        int rows = courseDao.validateCourse(courseName, courseId);

        if (rows > 0) {
            //该课程名已存在
            response.getWriter().print(true);
        } else {
            //该课程可用
            response.getWriter().print(false);
        }

    }


    /**
     * 修改课程
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void courseUpdate(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
        //获取前台参数


        List<Object> parameters = new ArrayList<>();

        parameters.add(request.getParameter("name"));

        String stateStr = request.getParameter("state");

        if ("有效".equals(stateStr)) {
            stateStr = "1";
        } else if ("无效".equals(stateStr)) {
            stateStr = "2";
        } else {
            new RuntimeException("存在编码错误");
        }

        parameters.add(stateStr);

        parameters.add(request.getParameter("memo"));

        UserEntity user = (UserEntity) request.getSession().getAttribute("user");

        parameters.add(user.getId());

        parameters.add(request.getParameter("id"));

        int rows = courseDao.courseUpdate(parameters);

        if (rows > 0) {
            request.getRequestDispatcher("CourseServlet?command=List").forward(request, response);
        }
    }


    private void getCourse(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("courseId"));

        CourseEntity course = courseDao.getCourse(id);

        String courseStr = JSONObject.toJSONString(course);

        request.setAttribute("courseStr", courseStr);

        response.getWriter().print(courseStr);

        //request.getRequestDispatcher("/WEB-INF/view/course/course-list.jsp").forward(request, response);
    }


    private void courseList(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {

        //int id = Integer.parseInt(request.getParameter("id"));

        List<CourseEntity> parenList = courseDao.getParentMenus();

        List<CourseEntity> childList = courseDao.getChildMenus();

        List<CourseEntity> allCourseList = courseDao.getAllCourse();

        String json = JSONObject.toJSONString(allCourseList);

        //System.out.println(allCourseList);


        request.setAttribute("parenList", parenList);

        request.setAttribute("childList", childList);

        request.setAttribute("json", json);

        request.getRequestDispatcher("/WEB-INF/view/course/course-list.jsp").forward(request, response);

    }

}
