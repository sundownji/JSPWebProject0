package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBConnect {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public JDBConnect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "sua_project";
			String pwd = "1234";
			
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본생성자)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public JDBConnect(String driver, String url, String id, String pwd) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(인자생성자1)");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
    }
	
	
	
    public JDBConnect(ServletContext application) {
    	//Java클래스의 메서드내에서 web.xml을 접근한다.
    	String driver = application.getInitParameter("OracleDriver"); 
        String url = application.getInitParameter("OracleURL");
        String id = application.getInitParameter("OracleId");
        String pwd = application.getInitParameter("OraclePwd");
        
        try {
            Class.forName(driver);  
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(인자생성자2)");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
    }
    //자원반납
    public void close() { 
        try {
            if (rs != null) rs.close(); 
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            if (con != null) con.close(); 

            System.out.println("JDBC 자원 해제");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
