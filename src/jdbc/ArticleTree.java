package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleTree {

	public static void main(String[] args) {
		
		
		Connection conn = null;
		PreparedStatement ppstmt = null;
		ResultSet rs = null;//游标
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs","root","123456");
			tree(0,conn,0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ppstmt != null){
					ppstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public static void tree(int id, Connection conn, int level) {
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<level; i++) {
			sb.append("----");
		}
		try {
			ResultSet rs = conn.createStatement().executeQuery("select * from article where pid ="+id);
			while(rs.next()){
				System.out.println(sb + rs.getString("cont"));
				if(Integer.parseInt(rs.getString("isleaf")) != 1) {
					tree(rs.getInt("id"),conn,++level);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
