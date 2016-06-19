package by.trepam.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.trepam.dao.exception.DAOException;
import by.trepam.dao.factory.DAOFactory;
import by.trepam.dao.factory.PostgresqlDAOFactory;
import by.trepam.domain.Account;

@RunWith(Parameterized.class)
public class PostgresqlAccountDAOGetAccountTest {

	private String answer;
	private int accountID;
	
	@Parameters
	public static Collection<Object[]> stepUpCoefficientTableValues(){
		return Arrays.asList(new Object[][]{
				{ 1, "1 Иван Иванов super_admin" },
				{ 2, "2 Пётр Петров general_admin" },
				{ 3, "3 Сидор Сидоров general_admin" },
				{ 15, "15 Евгений Евгеньев client" }
			});
	}
	
	public PostgresqlAccountDAOGetAccountTest(int accountID, String answer){
		this.accountID=accountID;
		this.answer=answer;
	}
	

	@Test
	public void getInformationAboutAccountTest() throws DAOException {
		DAOFactory df = new PostgresqlDAOFactory();
		AccountDAO acdao = df.getAccountDAO();
		Account account = acdao.getAccount(accountID);
		String expected = "";
		expected+=account.getId()+" ";
		expected+=account.getName()+" ";
		expected+=account.getSurname()+" ";
		expected+=account.getStatus();
		String actual = this.answer;
		assertEquals(expected, actual);

	}
}
