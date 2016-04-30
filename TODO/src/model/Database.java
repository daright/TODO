package model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	private static Connection connection;

	public static Connection getConnection() {
		if (connection == null) {
			String propFile = "database.properties";

			Properties props = new Properties();
			InputStream in = Database.class.getClassLoader().getResourceAsStream(propFile);
			try {
				props.load(in);
			} catch (IOException e1) {
				System.out.println("Error loading properties from database.properties: " + e1.getMessage());
			}
			String host = props.getProperty("host");
			String port = props.getProperty("port");
			String name = props.getProperty("name");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			String url = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&autoReconnect=true&useSSL=false", host, port, name, user, password);
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				connection = DriverManager.getConnection(url);

			} catch (SQLException e) {
				System.out.println("Could not connect to the database " + e.getMessage() + " " + e.getSQLState());
			}
		}
		return connection;
	}
}
