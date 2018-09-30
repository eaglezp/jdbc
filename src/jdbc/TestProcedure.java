package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class TestProcedure {

	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "zp", "123456");
		
		CallableStatement cstmt = conn.prepareCall("{call p(?,?,?,?)}");
		
		cstmt.registerOutParameter(3, Types.INTEGER);
		cstmt.registerOutParameter(4, Types.INTEGER);
		
		cstmt.setInt(1, 3);
		cstmt.setInt(2, 4);
		cstmt.setInt(4, 5);
		
		cstmt.execute();
		
		System.out.println(cstmt.getInt(3));
		System.out.println(cstmt.getInt(4));
		
		cstmt.close();
		conn.close();
	}

}
