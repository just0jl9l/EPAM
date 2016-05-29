package by.trepam.news.start;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import by.trepam.news.controller.Controller;
import by.trepam.news.domain.request.Request;
import by.trepam.news.domain.response.Response;
import by.trepam.news.view.View;

public class Main {

	public static void main(String[] args) throws JAXBException, IOException {
		Controller controller = new Controller();
		View view = new View();

		Request request = view.start();
		System.out.println("Main");
		Response response = controller.doAction(request);
		view.printAnswer(response);
	}
}
