package BridgeLab_Training.Jdbc.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	private static String url;
	private static String username;
	private static String password;
	
	static {
		try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
			Properties props = new Properties();
			props.load(input);
			url = props.getProperty("db.url");
			username = props.getProperty("db.username");
			password = props.getProperty("db.password");
		} catch (Exception e) {
			throw new RuntimeException("Failed to load DB Config", e);	
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException("Failed To Connect To DB", e);
		}
	}
}