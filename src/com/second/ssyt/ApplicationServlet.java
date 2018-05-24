package com.second.ssyt;

import java.io.IOException;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.second.ssyt.common.Constant;
import com.second.ssyt.sys.dao.SysDao;

public class ApplicationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding(Constant.ENCODING_UTF8);
        String command = request.getParameter("command");

        if ("top".equals(command)) {
            top(request, response);
        } else if ("initMenu".equals(command)) {
            initMenu(request, response);
        } else if ("index".equals(command)) {
            index(request, response);
        } else if ("main".equals(command)) {
            main(request, response);
        } else if ("defaults".equals(command)) {
            defaults(request, response);
        } else if ("imgtable".equals(command)) {
            imgtable(request, response);
        } else if ("imglist".equals(command)) {
            imglist(request, response);
        } else if ("tools".equals(command)) {
            tools(request, response);
        } else if ("computer".equals(command)) {
            computer(request, response);
        } else if ("tab".equals(command)) {
            tab(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/view/common/404.jsp").forward(request, response);
        }
    }


    /**
     * 到top.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void top(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/common/top.jsp").forward(request,
                response);
    }

    /**
     * 到left.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void left(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.getRequestDispatcher("/WEB-INF/view/common/left.jsp").forward(request,
                response);
    }

    /**
     * 初始化用户信息权限菜单
     *
     * @param request
     * @param response
     * @param out
     * @param userDao
     * @throws ServletException
     * @throws IOException
     */
    private void initMenu(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 从session中获取当前用户名
        String name = request.getSession().getAttribute("username") + "";

        // 获取主菜单
        List<Map<String, Object>> mainMenus = SysDao.getMainMenu(name);
        List<Map<String, Object>> childMenus = SysDao.getChildMenu(name);
        // 把主子菜单存储到requst对象中
        request.setAttribute("mainMenus", mainMenus);
        request.setAttribute("childMenus", childMenus);

        // 转发到主页
        request.getRequestDispatcher("/WEB-INF/view/common/left.jsp").forward(request, response);

    }

    /**
     * 到right.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/common/index.jsp").forward(request, response);

    }

    /**
     * 到main.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void main(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/common/main.jsp").forward(
                request, response);
    }

    /**
     * 到default.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void defaults(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/common/default.jsp").forward(request,
                response);
    }

    /**
     * 到imgtable.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void imgtable(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/common/imgtable.jsp").forward(request,
                response);
    }

    /**
     * 到imglist.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void imglist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/common/imglist.jsp").forward(request,
                response);
    }

    /**
     * 到tools.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void tools(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/common/tools.jsp").forward(request,
                response);
    }

    /**
     * 到computer.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void computer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/common/computer.jsp").forward(request,
                response);
    }

    /**
     * 到tab.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void tab(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/common/tab.jsp").forward(request,
                response);
    }


}
