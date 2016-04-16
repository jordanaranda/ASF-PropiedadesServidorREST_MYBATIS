package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private static Database	instance;
	private Connection		connection;
	private String			dataSource	= "//localhost/propiedades";
	private String			username	= "root";
	private String			password	= "";
	private String			driver		= "com.mysql.jdbc.Driver";
	private String			protocol	= "jdbc:mysql";

	private Database() {

	}

	public void createConnection() {
		try {
			Class.forName(driver);
			String url = protocol + ":" + dataSource;
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Statement create_statement() {
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Error creating statement.");
		}
		return statement;
	}

	public ResultSet consult(String consult) {
		Statement statement = this.create_statement();
		try {
			return statement.executeQuery(consult);
		} catch (SQLException e) {
			System.out.println("Error consulting database.");
			return null;
		}
	}

	public int update(String consult) {
		Statement statement = this.create_statement();
		try {
			return statement.executeUpdate(consult);
		} catch (SQLException e) {
			System.out.println("Error updating database.\n " + e.toString());
			return 0;
		}
	}

	public int count(String table, String condition) {
		int number = 0;
		String where = condition == null ? "" : " WHERE " + condition;
		String consult = "SELECT COUNT(*) as number FROM " + table + where + ";";
		ResultSet result = consult(consult);
		try {
			while (result.next()) {
				number = result.getInt("number");
			}
		} catch (SQLException e) {
			System.out.println("Error counting tables.");
		}
		return number;
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
}