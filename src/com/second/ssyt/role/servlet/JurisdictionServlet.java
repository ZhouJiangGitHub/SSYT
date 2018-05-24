package com.second.ssyt.role.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.second.ssyt.login.dao.LoginDao;

@WebServlet("/JurisdictionServlet")
public class JurisdictionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    LoginDao loginDao = new LoginDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码格式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取流对象
        PrintWriter out = response.getWriter();
        String command = request.getParameter("command");
        // 初始化用户菜单方法
        if ("initMenu".equals(command)) {
            initMenu(request, response, out, loginDao);
        }
        out.flush();
        out.close();
    }

    /**
     * 初始化菜单
     *
     * @param request
     * @param response
     * @param out
     * @param loginDao
     * @throws IOException
     * @throws ServletException
     */
    private void initMenu(HttpServletRequest request,
                          HttpServletResponse response, PrintWriter out, LoginDao loginDao) throws ServletException, IOException {
        //从session中获取当前登录用户
        String code = request.getSession().getAttribute("code") + "";
        System.out.println("sssss" + code);
        //获取主子菜单
        List<Map<String, Object>> mainMenus = loginDao.getMainMenu(code);
        List<Map<String, Object>> childMenus = loginDao.getChildMenu(code);
        // 把主子菜单存储到requst对象中
        request.setAttribute("mainMenus", mainMenus);
        request.setAttribute("childMenus", childMenus);
        // 转发到主页
        request.getRequestDispatcher("WEB-INF/view/role/main.jsp").forward(request, response);
    }

}
