package com.second.ssyt.sys.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.second.ssyt.Class.entity.ClassEntity;
import com.second.ssyt.common.Constant;
import com.second.ssyt.common.PageModel;
import com.second.ssyt.login.entity.UserEntity;
import com.second.ssyt.role.entity.SysRoleEntity;
import com.second.ssyt.sys.dao.SysDao;
import com.second.ssyt.sys.entity.RoleRightEntity;
import com.second.ssyt.sys.entity.SysEntity;

public class SysServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防乱码
        request.setCharacterEncoding(Constant.ENCODING_UTF8);
        response.setContentType("text/html;charset=UTF-8");
        String command = request.getParameter("command");
        if ("list".equals(command)) {
            List(request, response);
        } else if ("sysindex".equals(command)) {
            sysindex(request, response);
        } else if ("adduserPage".equals(command)) {
            adduserPage(request, response);
        } else if ("change".equals(command)) {
            change(request, response);
        } else if ("getMessege".equals(command)) {
            getMessege(request, response);
        } else if ("getPassword".equals(command)) {
            getPassword(request, response);
        } else if ("changMessege".equals(command)) {
            changMessege(request, response);
        } else if ("changePassword".equals(command)) {
            changePassword(request, response);
        } else if ("adduser".equals(command)) {
            adduser(request, response);
        } else if ("getRoles".equals(command)) {
            getRoles(request, response);
        } else if ("initGrant".equals(command)) {
            initGrant(request, response);
        } else if ("grant".equals(command)) {
            grant(request, response);
        } else if ("listManageByPage".equals(command)) {
            listManageByPage(request, response);
        } else if ("listManageVerifyByPage".equals(command)) {
            listManageVerifyByPage(request, response);
        } else {

            request.getRequestDispatcher("/WEB-INF/view/common/error.jsp");
        }

    }

    /**
     * 为不同角色授权的方法
     *
     * @param request
     * @param response
     * @param out
     * @param rightDao
     * @throws IOException
     * @throws ServletException
     */
    private void grant(HttpServletRequest request, HttpServletResponse response
    ) throws IOException, ServletException {
        // 获取权限编号字符串和角色编号
        String roleId = request.getParameter("roleId");
        String menuString = request.getParameter("menuString");
        // 把获取的菜单编号字符串转换为菜单编号数组
        String[] menuIds = menuString.split(",");
        boolean flag = false;

        if (SysDao.isGrant(roleId)) {
            if (SysDao.deleteAllGrant(roleId)) {
                flag = grantRight(roleId, menuIds);
            }
        } else {
            flag = grantRight(roleId, menuIds);
        }

        if (!flag) {
            request.getRequestDispatcher("SysServlet?command=getRoles").forward(request,
                    response);
        }

    }

    /**
     * 为角色添加权限
     *
     * @param roleId
     * @param menuIds
     * @param rightDao
     * @return
     */
    private boolean grantRight(String roleId, String[] menuIds) {
        boolean flag = false;
        if (menuIds != null && menuIds.length > 0) {
            for (String rightId : menuIds) {
                RoleRightEntity rr = new RoleRightEntity();
                rr.setRightId(rightId);
                rr.setRoleId(roleId);
                SysDao.grant(rr);
            }
        }

        return flag;
    }


    /**
     * 初始化授权操作界面的方法
     *
     * @param request
     * @param response
     * @param out
     * @param rightDao
     * @throws IOException
     * @throws ServletException
     */
    private void initGrant(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        // 查询所有的系统菜单并存储在request对象中
        List<Map<String, Object>> mainMenus = SysDao.getMainMenus();
        List<Map<String, Object>> childMenus = SysDao.getChildMenus();

        request.setAttribute("mainMenus", mainMenus);
        request.setAttribute("childMenus", childMenus);

        // 获取当前角色对应得主子菜单并存储在request对象中
        String roleId = request.getParameter("roleId");
        List<Map<String, Object>> roleMainMenus = SysDao.getMainMenuByRoleId(roleId);
        List<Map<String, Object>> roleChildMenus = SysDao.getChildMenuByRoleId(roleId);
        request.setAttribute("roleMainMenus", roleMainMenus);
        request.setAttribute("roleChildMenus", roleChildMenus);

        request.getRequestDispatcher("/WEB-INF/view/system/grant.jsp?roleId=" + roleId).forward(request, response);

    }


    /**
     * 查询需要授权的角色
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void getRoles(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 2、调用业务逻辑
        List<SysRoleEntity> sysRoleEntityList = SysDao.getRoles();
        // 3、转向
        request.setAttribute("sysRoleEntityList", sysRoleEntityList);
        request.getRequestDispatcher("/WEB-INF/view/system/roleList.jsp").forward(request,
                response);

    }


    /**
     * 到添加用户
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void adduserPage(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        //1.接受前台参数
        //2.调用逻辑
        List<ClassEntity> classNameList = SysDao.getClassName();
        UserEntity user = ((UserEntity) request.getSession().getAttribute("user"));
        int opread_id = user.getId();
        System.out.println("opread_id" + opread_id);
        request.setAttribute("opread_id", opread_id);
        request.setAttribute("classNameList", classNameList);
        request.getRequestDispatcher("/WEB-INF/view/system/userAdd.jsp").forward(request,
                response);

    }

    /**
     * 到系统用户管理页面.jsp
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void sysindex(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("SysServlet?command=listManageByPage").forward(request,
                response);

    }

    /**
     * 添加用户
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void adduser(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // 1、获取前台传过来的参数
        String code = request.getParameter("code");
        String password = request.getParameter("password");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String userName = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int role = Integer.parseInt(request.getParameter("role"));

        int classid = Integer.parseInt(request.getParameter("classid"));
        int operate_user_id = Integer.parseInt(request.getParameter("operate_user_id"));
        System.out.println("operate_user_id" + operate_user_id);
        int state = Integer.parseInt(request.getParameter("state"));
        String memo = request.getParameter("memo");
        String vrafyPassworf = request.getParameter("vrafyPassworf");
        String kaptchaSessionKey = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //验证码
        if (!kaptchaSessionKey.equals(vrafyPassworf)) {
            request.setAttribute("errorInfo", "验证码错误！");
            request.getRequestDispatcher("/WEB-INF/view/system/userAdd.jsp")
                    .forward(request, response);
        }
        //String vrafyPassworf = request.getParameter("vrafyPassworf");
        //String kaptchaSessionKey = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        // 2、调用业务逻辑
        SysEntity addUserEntity = new SysEntity();
        addUserEntity.setCode(code);
        addUserEntity.setPassword(password);
        addUserEntity.setSex(gender);
        addUserEntity.setName(userName);
        addUserEntity.setEmail(email);
        addUserEntity.setPhone(phone);
        addUserEntity.setRole_id(role);
        addUserEntity.setClass_id(classid);
        addUserEntity.setOperate_user_id(operate_user_id);
        addUserEntity.setState(state);
        addUserEntity.setMemo(memo);
        SysDao.addUser(addUserEntity);
        // 3、转向
        request.getRequestDispatcher("SysServlet?command=listManageByPage").forward(
                request, response);
    }


    /**
     * 修改用户密码
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */

    private void changePassword(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("Id"));
        String oldPassword = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String inPutpassword = request.getParameter("newPassword");
        // 2、调用业务逻辑
        if (inPutpassword.equals(oldPassword) || inPutpassword == "") {
            request.setAttribute("inPutpassword", inPutpassword);
            request.setAttribute("errorInfo", "原密码输入错误！");
            request.getRequestDispatcher("/WEB-INF/view/system/updatePassword.jsp")
                    .forward(request, response);
        }
        SysEntity SysUserEntity = new SysEntity();
        SysUserEntity.setPassword(newPassword);
        SysDao.changPassword(SysUserEntity, id);
        request.setAttribute("inPutpassword", inPutpassword);
        // 3、转向
        request.getRequestDispatcher("SysServlet?command=listManageByPage").forward(
                request, response);


    }


    /**
     * 修改用户信息
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void changMessege(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
        // 1、获取前台传过来的参数
        int id = Integer.parseInt(request.getParameter("Id"));
        String code = request.getParameter("code");
        String name = request.getParameter("Name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int role = Integer.parseInt(request.getParameter("role"));
        int state = Integer.parseInt(request.getParameter("state"));
        int classs = Integer.parseInt(request.getParameter("classs"));
        String memo = request.getParameter("memo");
        //int numbers = Integer.parseInt(request.getParameter("numbers"));
        // 2、调用业务逻辑  把要修改的数据放到实体
        SysEntity SysUserEntity = new SysEntity();
        SysUserEntity.setCode(code);
        SysUserEntity.setPassword(password);
        SysUserEntity.setName(name);
        SysUserEntity.setEmail(email);
        SysUserEntity.setPhone(phone);
        SysUserEntity.setRole_id(role);
        SysUserEntity.setState(state);
        SysUserEntity.setClass_id(classs);
        SysUserEntity.setMemo(memo);
        SysDao.changMessege(SysUserEntity, id);
        // 3、转向
        request.getRequestDispatcher("SysServlet?command=listManageByPage").forward(
                request, response);
        //response.sendRedirect("SysServlet?command=listManageByPage");
    }


    /**
     * 获取需要修改用户数据修改
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void getMessege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取前台传过来的参数
        int id = Integer.parseInt(request.getParameter("id"));

        // 2、调用业务逻辑
        SysEntity sysUserEntity = SysDao.get(id);
        // 3、转向
        request.setAttribute("sysUserEntity", sysUserEntity);
        request.getRequestDispatcher("/WEB-INF/view/system/update.jsp").forward(request,
                response);

    }

    /**
     * 获取需要修改用密码
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void getPassword(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        // 1、获取前台传过来的参数
        int id = Integer.parseInt(request.getParameter("id"));
        // 2、调用业务逻辑
        SysEntity sysUserEntity = SysDao.get(id);
        // 3、转向
        request.setAttribute("sysUserEntity", sysUserEntity);
        request.getRequestDispatcher("/WEB-INF/view/system/updatePassword.jsp").forward(request,
                response);

    }

    /**
     * 改变用户状态
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void change(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1、调用前台传来的参数
        int status = Integer.parseInt(request.getParameter("status"));
        int id = Integer.parseInt(request.getParameter("Id"));
        // 2、调用业务逻辑
        SysDao.change(status, id);
        // 3、转向
        response.sendRedirect("SysServlet?command=listManageVerifyByPage");

    }

    /**
     * 条件查询
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void listManageVerifyByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ///吧查询条件放到Student实体
        SysEntity findSys = new SysEntity();
        String Name = request.getParameter("Name");

        String rolestr = request.getParameter("role");
        String classstr = request.getParameter("class");
        String statestr = request.getParameter("state");
        if (StringUtils.isNotBlank(Name)) {
            findSys.setName(Name);
        }
        if (StringUtils.isNotBlank(rolestr)) {
            int role = Integer.parseInt(rolestr);
            findSys.setRole_id(role);
        }
        if (StringUtils.isNotBlank(classstr)) {
            int calss = Integer.parseInt(classstr);
            findSys.setClass_id(calss);
        }
        if (StringUtils.isNotBlank(statestr)) {
            int state = Integer.parseInt(statestr);
            findSys.setState(state);
        }
        int pageNo = Constant.DEFAULT_PAGE_NO;
        //获取前台参数
        String pageNoStr = request.getParameter("pageNo");
        if (pageNoStr != null) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        PageModel<SysEntity> PageModel = SysDao.listManageVerifyByPage(findSys, pageNo, Constant.DEFAULT_PAGE_SIZE);
        request.setAttribute("pageModel", PageModel);
        request.setAttribute("findSys", findSys);
        //转向
        request.getRequestDispatcher("/WEB-INF/view/system/index.jsp").forward(request, response);


    }


    /**
     * 分页查询管理信息
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void listManageByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = Constant.DEFAULT_PAGE_NO;
        //获取前台参数
        String pageNoStr = request.getParameter("pageNo");
        if (pageNoStr != null) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        //调用逻辑
        PageModel<SysEntity> PageModel = SysDao.listManageByPage(pageNo, Constant.DEFAULT_PAGE_SIZE);
        request.setAttribute("pageModel", PageModel);
        //转向
        request.getRequestDispatcher("/WEB-INF/view/system/index.jsp").forward(request, response);
    }


    /**
     * 学生信息查询
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void List(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取前台参数
        //2调用逻辑
        List<SysEntity> SysUser = SysDao.list();
        request.setAttribute("SysUserMessage", SysUser);
        //3转向
        request.getRequestDispatcher("/WEB-INF/view/system/index.jsp").forward(request, response);

    }
}//servlet
