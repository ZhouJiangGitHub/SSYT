package com.second.ssyt.exam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.second.ssyt.common.PageModel;
import com.second.ssyt.exam.dao.ExamDao;
import com.second.ssyt.exam.entity.ExamEntity;

@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ExamDao examDao = new ExamDao();
    PageModel<ExamEntity> pageModel = new PageModel<>();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        //防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String command = request.getParameter("command");
        if ("add".equals(command)) {
            add(request, response);
        } else if ("List".equals(command)) {
            List(request, response);
        } else if ("examSelect".equals(command)) {

            examSelect(request, response);
        } else if ("examAddPage".equals(command)) {

            examAddPage(request, response);
        } else {

            throw new IllegalStateException("非法调用");
        }

    }


    /**
     * 添加班级
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void examAddPage(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {


        request.getRequestDispatcher("/WEB-INF/view/exam/exam.jsp").forward(request,
                response);

    }

    /**
     * 班级查找
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void examSelect(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/exam/select.jsp").forward(request,
                response);

    }


    private void add(HttpServletRequest request,
                     HttpServletResponse response) throws ServletException, IOException {

        //获取前台传来的参数
        ExamEntity exam = new ExamEntity();
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int exam_plan_id = Integer.parseInt(request.getParameter("exam_plan_id"));
        int get_point = Integer.parseInt(request.getParameter("get_point"));
        String date = request.getParameter("submit_time");
        String date1 = request.getParameter("start_time");
        int is_pass = Integer.parseInt(request.getParameter("is_pass"));


        exam.setUser_id(user_id);
        exam.setExam_plan_id(exam_plan_id);
        exam.setGet_point(get_point);
        exam.setSubmit_time(date);
        exam.setStart_time(date1);
        exam.setIs_pass(is_pass);

        int row = examDao.add(exam);
        if (row > 0) {

            request.getRequestDispatcher("ExamServlet?command=List").forward(request, response);
        }
    }


    private void List(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        // 1、接收参数
        int pageNo = 1;
        int pageSize = 10;
        String pageNostr = request.getParameter("pageNo");
        if (pageNostr != null) {
            pageNo = Integer.parseInt(pageNostr);
        }

        // 查询条件参数
        String name = request.getParameter("name");
        ExamEntity exam = new ExamEntity();
        exam.setName(name);

        // 2、调用业务逻辑
        PageModel<ExamEntity> pageModel = examDao.list(exam, pageSize, pageNo);
        request.setAttribute("pageModel", pageModel);

        // 3、转向
        request.setAttribute("exam", exam);
        request.getRequestDispatcher("WEB-INF/view/exam/select.jsp").forward(request, response);
    }
}


