package com.wangc.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBUtil {

	public static final String DRIVERNAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/javadata";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	
	public static BasicDataSource bSource = null;

	static{
		bSource = new BasicDataSource();
		bSource.setDriverClassName(DRIVERNAME);
		bSource.setUrl(URL);
		bSource.setUsername(USERNAME);
		bSource.setPassword(PASSWORD);
	}
	
	public static Connection getConn(){
		try {
			return bSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void release(Connection conn, Statement stat, ResultSet rs){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
