package by.trepam.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

		DAOFactory df = new PostgresqlDAOFactory();
		AccountDAO acdao = df.getAccountDAO();
		Account before = acdao.getAccount(1000);
		Account account = new Account();
		account.setId(1000);
		account.setName("new_name");
		account.setSurname("new_surname");
		account.setStatus(before.getStatus());
		account.setPhoto(new Image(2));
		acdao.update(account);

		Account actual = acdao.getAccount(1000);
		assertFalse(before.equals(actual));
		assertTrue(account.getId()==actual.getId());
		assertEquals(account.getName(), actual.getName());
		assertEquals(account.getPhoto().getId(), actual.getPhoto().getId());
		assertEquals(account.getSurname(), actual.getSurname());
		assertEquals(account.getStatus(), actual.getStatus());

	}
}
