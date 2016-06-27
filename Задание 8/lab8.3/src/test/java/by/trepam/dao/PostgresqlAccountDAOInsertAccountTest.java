package by.trepam.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.DAOFactory;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Account;


public class PostgresqlAccountDAOInsertAccountTest {

	@Test
	public void insertInformationAboutAccountTest() throws DAOException {

		DAOFactory df = PostgresqlDAOFactory.getInstance();
		AccountDAO acdao = df.getAccountDAO();
		
			Account account = new Account();
			account.setName("Федор");
			account.setSurname("Федоров");
			account.setStatus("client");
			account.setLogin("fedor");
			account.setPassword("password");
			acdao.insert(account);

			Account actual = acdao.logIN("fedor","password");
			account.setId(actual.getId());
			assertTrue(account.isEquals(actual));

	}
}
