package by.trepam.connection_pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import by.trepam.connection_pool.ConnectionPool;
import by.trepam.connection_pool.exception.ConnectionPoolException;

public class ConnectionPoolTest{

	@Test
	public void connectionPoolTest() {
		final ConnectionPool pool = new ConnectionPool();
		try {
			pool.init();
			int i = 0;
			while (i < 1000) {
				i++;
				Thread myThready = new Thread(new Runnable() {
					public void run() {
						Connection connection;
						try {
							connection = pool.getConnection();
							String sql = "SELECT name, surname, id_account FROM account";
							PreparedStatement ps = connection.prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							String s = "";
							while (rs.next()) {
								s += rs.getString("name");
								s += " ";
								s += rs.getString("surname");
								s += " ";
								s += rs.getString("id_account");
								s += "\n";
							}
							ps.close();
							connection.close();
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (ConnectionPoolException e) {
							e.printStackTrace();
						}

					}
				});
				myThready.start();
				myThready.join();
			}
			pool.close();
		} catch (ConnectionPoolException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
