package by.trepam.dao;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.DAOFactory;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Account;

public class PostgresqlAccountDAODeleteAccountTest {
	@Test
	public void deleteInformationAboutAccountTest() throws DAOException {

		DAOFactory df = PostgresqlDAOFactory.getInstance();
		AccountDAO acdao = df.getAccountDAO();
		Account before = acdao.getAccount(18);
		if(before!=null){
			acdao.delete(18);	
			Account actual = acdao.getAccount(18);
			assertNull(actual);
		}

	}
}
