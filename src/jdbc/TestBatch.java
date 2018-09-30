package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestBatch {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ppstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "zp", "123456");
			ppstmt = conn.prepareStatement("insert into dept2 (deptno,dname,loc) values (?,?,?)");
			
			ppstmt.setInt(1, 99);
			ppstmt.setString(2, "99");
			ppstmt.setString(3, "99");
			ppstmt.addBatch();
			
			ppstmt.setInt(1, 98);
			ppstmt.setString(2, "98");
			ppstmt.setString(3, "98");
			ppstmt.addBatch();
			
			ppstmt.setInt(1, 97);
			ppstmt.setString(2, "97");
			ppstmt.setString(3, "97");
			ppstmt.addBatch();
			
			System.out.println(ppstmt.executeBatch());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
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

}
