package by.trepam.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.DAOFactory;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Account;
import by.trepam.domain.Image;

public class PostgresqlAccountDAOUpdateAccountTest {
	@Test
	public void updateInformationAboutAccountTest() throws DAOException {

		DAOFactory df = PostgresqlDAOFactory.getInstance();
		AccountDAO acdao = df.getAccountDAO();
		Account before = acdao.getAccount(18);
		Account account = new Account();
		account.setId(18);
		account.setName("Фёдор");
		account.setSurname("Фёдоров");
		account.setStatus(before.getStatus());
		account.setPhoto(new Image(2));
		acdao.update(account);

		Account actual = acdao.getAccount(18);
		assertTrue(account.isEquals(actual));

	}
}
