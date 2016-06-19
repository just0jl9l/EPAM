package by.trepam.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.DAOFactory;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Account;
import by.trepam.domain.Image;


public class PostgresqlAccountDAOInsertAccountTest {

	@Test
	public void insertInformationAboutAccountTest() throws DAOException {

		DAOFactory df = new PostgresqlDAOFactory();
		AccountDAO acdao = df.getAccountDAO();
		if(acdao.getAccount(1000)==null){
			Account account = new Account();
			account.setId(100);
			account.setName("name");
			account.setSurname("surname");
			account.setStatus("client");
			account.setPhoto(new Image(0));
			account.setLogin("client_login0");
			account.setPassword("password");
			acdao.insert(account);

			account.setLogin("");
			account.setPassword("");
			Account actual = acdao.getAccount(1000);
			assertEquals(account, actual);
		}

	}
}
