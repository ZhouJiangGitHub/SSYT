package com.second.ssyt.role.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.second.ssyt.common.PageModel;
import com.second.ssyt.login.entity.UserEntity;
import com.second.ssyt.role.dao.SysRoleDao;
import com.second.ssyt.role.entity.SysRoleEntity;

public class RoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final SysRoleDao sysRoleDao = new SysRoleDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");
        if ("list".equals(command)) {
            list(request, response);
        } else if ("listPage".equals(command)) {
            try {
                listPage(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("add".equals(command)) {
            add(request, response);
        } else if ("addTest".equals(command)) {
            addTest(request, response);
        } else if ("listAddPage".equals(command)) {
            listAddPage(request, response);
        } else if ("updateMessage".equals(command)) {
            try {
                updateMessage(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("update".equals(command)) {
            update(request, response);
        } else if ("updateState".equals(command)) {
            updateState(request, response);
        } else {
            throw new IllegalStateException("请求非法");
        }
    }

    /**
     * 新增查询
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void listAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = 1;
        int pageSize = 10;
        String pageNoStr = request.getParameter("pageNo");
        if (pageNoStr != null) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        PageModel<SysRoleEntity> pageModel = sysRoleDao.listAddPage(pageSize, pageNo);
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("WEB-INF/view/role/AddIndex.jsp").forward(request, response);
    }


    /**
     * 新增跳转页面
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void addTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取当前用户id
        UserEntity user = ((UserEntity) request.getSession().getAttribute("user"));
        int opread_user_id = user.getId();
        //把id传带前台
        request.setAttribute("opread_user_id", opread_user_id);
        request.getRequestDispatcher("WEB-INF/view/role/add.jsp").forward(request, response);
    }

    /**
     * 新增
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String menuIds = request.getParameter("menuIds");
        //Short operateUserId = (short) Integer.parseInt(request.getParameter("operateUserId"));
        //String operateTime = request.getParameter("operateTime");
        Short state = (short) Integer.parseInt(request.getParameter("state"));
        String memo = request.getParameter("memo");
        SysRoleEntity sysRoleList = new SysRoleEntity();
        sysRoleList.setName(name);
        sysRoleList.setMenuIds(menuIds);
        //sysRoleList.setOperateUserId(operateUserId);
        //sysRoleList.setOperateTime(operateTime);
        sysRoleList.setState(state);
        sysRoleList.setMemo(memo);
        sysRoleDao.add(sysRoleList);
        request.getRequestDispatcher("RoleServlet?command=list").forward(request, response);
    }

    /**
     * 状态修改
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void updateState(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Short state = (short) Integer.parseInt(request.getParameter("state"));

        int row = sysRoleDao.updateState(id, state);

        PrintWriter printWriter = response.getWriter();
        try {
            printWriter.print(row);
        } finally {
            printWriter.close();
        }
    }


    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     * @throws ServletException
     */
    private void listPage(HttpServletRequest request,
                          HttpServletResponse response) throws SQLException, ServletException, IOException {
        // 1、接收参数
        int pageNo = 1;
        int pageSize = 10;
        String pageNoStr = request.getParameter("pageNo");
        if (pageNoStr != null) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        PageModel<SysRoleEntity> pageModel = sysRoleDao.listPage(pageSize, pageNo);
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("WebContent/WEB-INF/view/role/index.jsp").forward(request, response);
    }

    /**
     * 条件查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收参数
        int pageNo = 1;
        int pageSize = 10;
        String pageNoStr = request.getParameter("pageNo");
        if (pageNoStr != null) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        //查询条件
        Short state = 0;
        String stateStr = request.getParameter("state");
        if (stateStr != null) {
            state = (short) Integer.parseInt(stateStr);
        }
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        sysRoleEntity.setState(state);
        // 2、调用业务逻辑
        PageModel<SysRoleEntity> pageModel = sysRoleDao.list(sysRoleEntity, pageSize, pageNo);
        request.setAttribute("pageModel", pageModel);
        request.setAttribute("sysRoleEntity", sysRoleEntity);
        // 3、转向
        request.getRequestDispatcher("WEB-INF/view/role/index.jsp").forward(request, response);
    }


    /**
     * 需要修改的角色消息回显
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    private void updateMessage(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<SysRoleEntity> sysRoleList = SysRoleDao.get(id);
        SysRoleEntity sysRoleEntity = sysRoleList.get(0);
        request.setAttribute("sysRoleEntity", sysRoleEntity);
        request.getRequestDispatcher("WEB-INF/view/role/update.jsp").forward(request,
                response);
    }

    /**
     * 修改
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Short state = (short) Integer.parseInt(request.getParameter("state"));
        String memo = request.getParameter("memo");
        SysRoleEntity sysRole = new SysRoleEntity();
        sysRole.setName(name);
        sysRole.setState(state);
        sysRole.setMemo(memo);
        sysRoleDao.update(sysRole, id);
        request.getRequestDispatcher("RoleServlet?command=list").forward(request, response);
    }
}