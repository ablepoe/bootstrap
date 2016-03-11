package com.db;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static Statement stmt;
	private static ResultSet rs;
	
	public static Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8";
			String url = "jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf8";
			String username = "root";
			String password = "";
			conn = DriverManager.getConnection(url,username,password);
			conn.setAutoCommit(false);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static PreparedStatement getPstmt(Connection conn, String sql){
		try {
			pstmt = conn.prepareStatement(sql);
			return pstmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Statement getStmt(Connection conn){
		try {
			stmt = conn.createStatement();
			return stmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet getRs(Statement stmt, String sql){
		try {
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet getRs(PreparedStatement pstmt){
		try {
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConn(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStmt(Statement stmt){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeRs(ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeAll(){
		closeConn(conn);
		closeStmt(stmt);
		closeRs(rs);
	}
	
	public SqlSession getSqlsession() throws IOException{
		//获取数据库链接信息
		Reader reader = Resources.getResourceAsReader("com/config/Configuration.xml");
		//构建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//打开一个回话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
