package persistlayer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbAccess {
	
	static final String DRIVE_NAME = "com.mysql.jdbc.Driver";
	
	static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/apartmentsDB";
	
	static final String DB_CONNECTION_USERNAME = "root";
	
	static final String DB_CONNECTION_PASSWORD = "peoples12";
	
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName(DRIVE_NAME);
			con = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	} // end of connect
	
	public static int insert (String query){
		int rows = 0;
		try {
			Connection con = connect();
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			rows = stmt.executeUpdate();
			disconnect(con);
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	public static ResultSet retrieve (Connection con, String query) {
		ResultSet rset = null;
		try {
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			return rset;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rset;
	}// end of retrieve
	
	public static void disconnect(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of closeConnection
}
