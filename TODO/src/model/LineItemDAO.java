package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAO {
	public static void addLineItem(int iditem, String item, String login, String date) {
		PreparedStatement prepStatement = null;
		try {
			String preparedSQL = "INSERT INTO list VALUES(?, ?, DEFAULT, ?, ?)";
			prepStatement = Database.getConnection().prepareStatement(preparedSQL);
			prepStatement.setInt(1, iditem);
			prepStatement.setString(2, item);
			prepStatement.setString(3, login);
			prepStatement.setString(4, date);
			prepStatement.execute();

		} catch (SQLException e) {
			System.out.println("Could not add item to database: \"" + item + "\" " + e.getSQLState() + " " + e.getMessage());
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

	public static List<LineItem> getAllLineItems(String login) {
		PreparedStatement statement = null;
		List<LineItem> items = new ArrayList<>();
		try {
			String query = "SELECT * FROM list WHERE login = ?;";
			statement = Database.getConnection().prepareStatement(query);
			statement.setString(1, login);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			while (rs.next()) {
				boolean checked = false;
				if (rs.getInt(3) == 1) {
					checked = true;
				}
				LineItem lineItem = new LineItem(rs.getInt(1), rs.getString(2), checked, login, rs.getString(5));
				items.add(lineItem);
			}
		} catch (SQLException e) {
			System.out.println("Could not retrieve items from database: " + e.getMessage());
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

	public static void updateItem(LineItem lineItem) {
		PreparedStatement statement = null;
		String query = "UPDATE list SET item = ? WHERE iditem = ? AND login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setString(1, lineItem.getItem());
			statement.setInt(2, lineItem.getIditem());
			statement.setString(3, lineItem.getLogin());
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

	public static void checkItem(int iditem, String login) {

		PreparedStatement statement = null;
		String query = "UPDATE list SET checked = 1 WHERE iditem = ? AND login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, iditem);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not check item " + e.getMessage());
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

	public static void uncheckItem(int iditem, String login) {
		PreparedStatement statement = null;
		String query = "UPDATE list SET checked = 0 WHERE iditem = ? AND login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, iditem);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not uncheck item " + e.getMessage());
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

	public static void deleteItem(int iditem, String login) {
		PreparedStatement s1 = null;
		PreparedStatement s2 = null;
		String query1 = "DELETE FROM list WHERE iditem = ? AND login = ?;";
		String query2 = "DELETE FROM sublist WHERE idparent = ? AND login = ?;";
		try {
			s1 = Database.getConnection().prepareStatement(query1);
			s1.setInt(1, iditem);
			s1.setString(2, login);
			s2 = Database.getConnection().prepareStatement(query2);
			s2.setInt(1, iditem);
			s2.setString(2, login);
			s2.executeUpdate();
			s1.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Could not delete item " + e.getMessage());
		} finally {
			if (s1 != null) {
				try {
					s1.close();
				} catch (SQLException e) {
					System.out.println("Could not close statement");
				}
			}
			if (s2 != null) {
				try {
					s2.close();
				} catch (SQLException e) {
					System.out.println("Could not close statement");
				}
			}
		}
	}
	
	public static int getNumOfSubitems(int iditem, String login) {
		int result = 0;
		PreparedStatement statement = null;
		String query = "SELECT COUNT(*) FROM sublist WHERE idparent = ? AND login = ?;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, iditem);
			statement.setString(2, login);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Could not delete item " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println("Could not close statement");
				}
			}
		}
		return result;
	}
	
	public static int getNumOfCheckedSubitems(int iditem, String login) {
		int result = 0;
		PreparedStatement statement = null;
		String query = "SELECT COUNT(*) FROM sublist WHERE idparent = ? AND login = ? AND checked = 1;";
		try {
			statement = Database.getConnection().prepareStatement(query);
			statement.setInt(1, iditem);
			statement.setString(2, login);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Could not delete item " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println("Could not close statement");
				}
			}
		}
		return result;
	}
}
