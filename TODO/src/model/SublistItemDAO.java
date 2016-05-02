package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SublistItemDAO {

	public static void addLineItem(int iditem, int idparent, String login, String item) {
		PreparedStatement prepStatement = null;
		try {
			String preparedSQL = "INSERT INTO sublist VALUES(?, ?, ?, ?, DEFAULT)";
			prepStatement = Database.getConnection().prepareStatement(preparedSQL);
			prepStatement.setInt(1, iditem);
			prepStatement.setInt(2, idparent);
			prepStatement.setString(3, login);
			prepStatement.setString(4, item);
			prepStatement.execute();

		} catch (SQLException e) {
			System.out.println("Could not add sublist item to database: \"" + item + "\" " + e.getSQLState() + " " + e.getMessage());
		} finally {
			if (prepStatement != null) {
				try {
					prepStatement.close();
				} catch (SQLException e) {
					System.out.println("Could not close statement");
				}
			}
		}
	}

	public static List<SublistItem> getAllLineItems(int idparent, String login) {
		PreparedStatement statement = null;
		List<SublistItem> items = new ArrayList<>();
		try {
			String query = "SELECT * FROM sublist WHERE idparent = ? AND login = ?;";
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, idparent);
			statement.setString(2, login);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			while (rs.next()) {
				boolean checked = false;
				if (rs.getInt(5) == 1) {
					checked = true;
				}
				SublistItem sublistItem = new SublistItem(rs.getInt(1), idparent, login, rs.getString(4), checked);
				items.add(sublistItem);
			}
		} catch (SQLException e) {
			System.out.println("Could not retrieve subitems from database: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println("Could not close statement");
				}
			}
		}
		return items;
	}

	public static void updateItem(SublistItem sublistItem) {
		PreparedStatement statement = null;
		String query = "UPDATE sublist SET item = ? WHERE iditem = ? AND idparent = ? AND login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setString(1, sublistItem.getItem());
			statement.setInt(2, sublistItem.getIditem());
			statement.setInt(3, sublistItem.getIdparent());
			statement.setString(4, sublistItem.getLogin());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not update item " + e.getMessage());
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

	public static void checkItem(int iditem, int idparent, String login) {

		PreparedStatement statement = null;
		String query = "UPDATE sublist SET checked = 1 WHERE iditem = ? AND idparent = ? AND login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, iditem);
			statement.setInt(2, idparent);
			statement.setString(3, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not check sublist item " + e.getMessage());
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

	public static void uncheckItem(int iditem, int idparent, String login) {
		PreparedStatement statement = null;
		String query = "UPDATE sublist SET checked = 0 WHERE iditem = ? AND idparent = ? AND login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, iditem);
			statement.setInt(2, idparent);
			statement.setString(3, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not uncheck sublist item " + e.getMessage());
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

	public static void deleteItem(int iditem, int idparent, String login) {
		PreparedStatement statement = null;
		String query = "DELETE FROM sublist WHERE iditem = ? AND idparent = ? AND login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, iditem);
			statement.setInt(2, idparent);
			statement.setString(3, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not delete sublist item " + e.getMessage());
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

	public static int sublistItemCount(int idparent, String login){
		int result = 0;
		PreparedStatement statement = null;
		String query = "SELECT MAX(iditem) FROM sublist WHERE idparent = ? AND login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, idparent);
			statement.setString(2, login);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Could not get sublist item count: " + e.getMessage() + " " + e.getSQLState());
		}
		return result;
	}
}
