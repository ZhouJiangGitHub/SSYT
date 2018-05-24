package com.second.ssyt.student.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.second.ssyt.student.dao.StationDao;
import com.second.ssyt.student.entity.StationEntity;

@WebServlet("/StationServlet")
public class StationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    StationDao stationDao = new StationDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String command = request.getParameter("command");
        if ("list".equals(command)) {
            try {
                list(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("MessageDetail".equals(command)) {
            MessageDetail(request, response);
        }
    }

    /**
     * 查看详细信息
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void MessageDetail(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<StationEntity> stationList = stationDao.MessageDetail(id);
        request.setAttribute("stationList", stationList);
        request.getRequestDispatcher("WEB-INF/view/student/MessageDetail.jsp").forward(request, response);
    }

    /**
     * 查看站内消息
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws SQLException
     */
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<StationEntity> stationList = stationDao.list();
        request.setAttribute("stationList", stationList);
        request.getRequestDispatcher("WEB-INF/view/student/stationIndex.jsp").forward(request, response);
    }

}
