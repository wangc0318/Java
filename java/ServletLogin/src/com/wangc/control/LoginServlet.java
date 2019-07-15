package com.wangc.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wangc.utils.DBUtil;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try {
			conn = DBUtil.getConn();
			String sql = "select * from user where name=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, request.getParameter("user"));
			ps.setString(2, request.getParameter("pwd"));
			rs = ps.executeQuery();
			if (rs.next()) {
				response.getWriter().write("µÇÂ½³É¹¦£¡");
			}else {
				response.getWriter().write("µÇÂ½Ê§°Ü£¡");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.release(conn, ps, rs);
		}
	}

}
