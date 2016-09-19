package by.trepam.like_it.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.trepam.like_it.dao.connection_pool.ConnectionPool;
import by.trepam.like_it.dao.connection_pool.exception.ConnectionPoolException;
import by.trepam.like_it.dao.connection_pool.impl.PostgresqlConnectionPool;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.DAOFactory;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;

public class PostgresqlAccountDAOTest {
	private static ConnectionPool pool;
	private static DAOFactory daoFactory;
	private static AccountDAO accdao;
	private Integer id_update = 10;
	private Integer id_delete = 30;
	private  String name = "name";
	private  String newname = "Алексей";
	private  String surname = "Алексеев";
	private  String status = "client";
	private  String login = "test";
	private  String password = "password";
	
	@BeforeClass
	public static void initPostgresqlAccountDAOTest(){
		try {
			pool = PostgresqlConnectionPool.getInstance();
			pool.init();
			daoFactory = PostgresqlDAOFactory.getInstance();
			accdao = daoFactory.getAccountDAO();
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("JDBC Driver error", e);
		}
	}
	
	@Test
	public void insertPostgresqlAccountDAOTest() throws DAOException{
		Account account = new Account();
		account.setName(name);
		account.setSurname(surname);
		account.setStatus(status);
		account.setLogin(login);
		account.setPassword(password);
		accdao.insert(account);		
		Account actual = accdao.logIN(login,password);
		assertTrue(account.getName().equals(actual.getName()));
		assertTrue(account.getSurname().equals(actual.getSurname()));
		assertTrue(account.getStatus().equals(actual.getStatus()));
		assertTrue(account.getLogin().equals(actual.getLogin()));
		assertTrue(account.getPassword().equals(actual.getPassword()));
	}

	
	@Test
	public void updatePostgresqlAccountDAOTest() throws DAOException{
		Account before = accdao.getAccount(id_update);
		Account account = new Account();
		account.setId(id_update);
		account.setName(newname);
		account.setSurname(surname);
		account.setStatus(before.getStatus());
		accdao.update(account);
		Account actual = accdao.getAccount(id_update);
		assertTrue(account.equals(actual));
	}
	
	@Test
	public void deletePostgresqlAccountDAOTest() throws DAOException{
		Account before = accdao.getAccount(id_delete);
		if(before!=null){
			accdao.delete(id_delete);	
			Account actual = accdao.getAccount(id_delete);
			assertNull(actual);
		}
	}
	
	@AfterClass
	public static void destroyPostgresqlAccountDAOTest(){
		try {
			if(pool!=null){
				pool.close();
			}
		} catch (ConnectionPoolException e) {
			throw new RuntimeException("JDBC Driver error", e);
		}	
	}
	

}
