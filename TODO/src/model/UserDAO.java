package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDAO {
	public static void addUser(User user) {
		PreparedStatement statement = null;
		try {
			statement = Database.getConnection().prepareStatement("INSERT INTO users VALUES(?,?,?,DEFAULT)");
			String login = user.getLogin();
			String password = user.getPassword();
			statement.setString(1, login);
			byte[] salt = Passwords.getNextSalt();
			statement.setBytes(2, Passwords.hash(password.toCharArray(), salt));
			statement.setBytes(3, salt);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error opening statement UserDAO.addUser: " + e.getMessage() + " " + e.getSQLState());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void updateItemCount(int itemCount, String login){

		PreparedStatement statement = null;
		String query = "UPDATE users SET itemCount = ? WHERE login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, itemCount);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not update itemCount " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println("Could not close statement");
				}
			}
		}
	}

	public static boolean checkIfUserExist(String login) {
		boolean result = false;
		PreparedStatement statement = null;
		try {
			statement = Database.getConnection().prepareStatement("SELECT login FROM users WHERE login = ?");
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean checkLogin(User user) {
		boolean result = false;
		String login = user.getLogin();
		String password = user.getPassword();
		PreparedStatement statement = null;
		try {
			statement = Database.getConnection().prepareStatement("SELECT hash, salt FROM users WHERE login = ?");
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				byte[] hash = rs.getBytes(1);
				byte[] salt = rs.getBytes(2);
				if (Arrays.equals(hash, Passwords.hash(password.toCharArray(), salt))) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int getItemCount(String login){
		int result = 0;

		PreparedStatement statement = null;
		try {
			statement = Database.getConnection().prepareStatement("SELECT itemCount FROM users WHERE login = ?");
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result; 
	}
}
