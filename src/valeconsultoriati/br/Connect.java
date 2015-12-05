package valeconsultoriati.br;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	private Connection db;

	Connect(String url, String user, String password) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException");
			System.err.println(e.getMessage());
		}

		try {

			this.db = DriverManager.getConnection(url, user, password);

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	public boolean executeUpdate(String query) {
		Statement sq_stmt = null;

		try {
			sq_stmt = this.db.createStatement();

			return sq_stmt.execute(query);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
		}
		return false;
	}

	public ResultSet execute(String query) {
		Statement sq_stmt = null;

		try {
			sq_stmt = this.db.createStatement();

			return sq_stmt.executeQuery(query);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return null;
	}

}
