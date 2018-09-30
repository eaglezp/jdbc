package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestPrepStmt {

	public static void main(String[] args) {
		
		if(args.length !=3) {
			System.out.println("Parameter Error! Please Input Again!");
			System.exit(-1);
		}
		
		int deptno = 0;
		
		try {
			deptno = Integer.parseInt(args[0]);
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Parameter Error! Deptno Should be Number Format!");
			System.exit(-1);
		}
		
		String dname = args[1];
		String loc = args[2];
		
		Connection conn = null;
		PreparedStatement ppstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "zp", "123456");
			ppstmt = conn.prepareStatement("insert into dept2 (deptno,dname,loc) values (?,?,?)");
			
			ppstmt.setInt(1, deptno);
			ppstmt.setString(2, dname);
			ppstmt.setString(3, loc);
			
			System.out.println(ppstmt.executeUpdate());
			
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
