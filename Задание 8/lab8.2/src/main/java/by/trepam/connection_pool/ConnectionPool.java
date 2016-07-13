package by.trepam.connection_pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.connection_pool.exception.ConnectionPoolException;
import by.trepam.connection_pool.util.DBConnectionProperty;
import by.trepam.connection_pool.util.PropertiesConstant;

public class ConnectionPool {

	private ArrayBlockingQueue<Connection> freeConnections;
	private ArrayBlockingQueue<Connection> givenConnections;

	private String driver;
	private String url;
	private String user;
	private String password;
	private int numberOfConnections;
	
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public ConnectionPool() {
		DBConnectionProperty property = DBConnectionProperty.getInstance();
		this.driver = property.getValue(PropertiesConstant.DRIVER);
		this.url = property.getValue(PropertiesConstant.URL);
		this.password = property.getValue(PropertiesConstant.PASSWORD);
		this.user = property.getValue(PropertiesConstant.USER);
		this.numberOfConnections = Integer.parseInt(property.getValue(PropertiesConstant.CONNECTIONS_NUMBER));
	}

	public void init() throws ConnectionPoolException {
		try {
			Class.forName(driver);
			freeConnections = new ArrayBlockingQueue<Connection>(numberOfConnections);
			givenConnections = new ArrayBlockingQueue<Connection>(numberOfConnections);
			for (int i = 0; i < numberOfConnections; i++) {
				freeConnections.add(new DBConnection(DriverManager.getConnection(url, user, password)));
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException", e);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("ClassNotFoundException", e);
		}

		logger.info("ConnectionPool initialized");
	}

	public Connection getConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = freeConnections.take();
			givenConnections.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("InterruptedException", e);
		}
		return connection;
	}

	public void close() throws ConnectionPoolException {
		try {
			close(freeConnections);
			close(givenConnections);
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException ", e);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("InterruptedException ", e);
		}

		logger.info("ConnectionPool closed");
	}

	private void close(ArrayBlockingQueue<Connection> queue) throws SQLException, InterruptedException {
		DBConnection dbconnection;
		for (Connection con : queue) {
			dbconnection = (DBConnection) con;
			dbconnection.closeConnection();
			queue.remove(dbconnection);

		}
	}

	class DBConnection implements Connection {
		private Connection connection;

		public DBConnection(Connection connection) throws SQLException {
			this.connection = connection;
			connection.setAutoCommit(true);
		}

		public void closeConnection() throws SQLException {
			connection.close();
		}

		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		public void close() {
			try {
				connection.setAutoCommit(true);
				connection.setReadOnly(false);
				givenConnections.remove(this);
				freeConnections.put(this);
			} catch (InterruptedException e) {
				throw new RuntimeException("InterruptedException occurred during connection closing",e);
			} catch (SQLException e) {
				throw new RuntimeException("SQLException occurred during connection closing",e);
			}

		}

		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return connection.isWrapperFor(iface);
		}

		public <T> T unwrap(Class<T> iface) throws SQLException {
			return connection.unwrap(iface);
		}

		public void abort(Executor executor) throws SQLException {
			connection.abort(executor);

		}

		public void clearWarnings() throws SQLException {
			connection.clearWarnings();

		}

		public void commit() throws SQLException {
			connection.commit();

		}

		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return connection.createArrayOf(typeName, elements);
		}

		public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

		public Clob createClob() throws SQLException {
			return connection.createClob();
		}

		public NClob createNClob() throws SQLException {
			return connection.createNClob();
		}

		public SQLXML createSQLXML() throws SQLException {

			return connection.createSQLXML();
		}

		public Statement createStatement() throws SQLException {

			return connection.createStatement();
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {

			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {

			return connection.createStruct(typeName, attributes);
		}

		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

		public String getClientInfo(String name) throws SQLException {
			return connection.getClientInfo(name);
		}

		public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

		public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

		public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}

		public String getSchema() throws SQLException {
			return connection.getSchema();
		}

		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		public boolean isValid(int timeout) throws SQLException {
			return connection.isValid(timeout);
		}

		public String nativeSQL(String sql) throws SQLException {
			return connection.nativeSQL(sql);
		}

		public CallableStatement prepareCall(String sql) throws SQLException {
			return connection.prepareCall(sql);
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public PreparedStatement prepareStatement(String sql) throws SQLException {

			return connection.prepareStatement(sql);
		}

		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			return connection.prepareStatement(sql, columnIndexes);
		}

		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			return connection.prepareStatement(sql, columnNames);
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			connection.releaseSavepoint(savepoint);

		}

		public void rollback() throws SQLException {
			connection.rollback();

		}

		public void rollback(Savepoint savepoint) throws SQLException {
			connection.rollback(savepoint);

		}

		public void setAutoCommit(boolean autoCommit) throws SQLException {
			connection.setAutoCommit(autoCommit);

		}

		public void setCatalog(String catalog) throws SQLException {
			connection.setCatalog(catalog);

		}

		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			connection.setClientInfo(properties);

		}

		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			connection.setClientInfo(name, value);

		}

		public void setHoldability(int holdability) throws SQLException {
			connection.setHoldability(holdability);

		}

		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			connection.setNetworkTimeout(executor, milliseconds);

		}

		public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);

		}

		public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

		public Savepoint setSavepoint(String name) throws SQLException {
			return connection.setSavepoint(name);
		}

		public void setSchema(String schema) throws SQLException {
			connection.setSchema(schema);

		}

		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);

		}

		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			connection.setTypeMap(map);

		}

	}

}
