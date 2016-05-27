package by.rdepam.news.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import by.rdepam.news.domain.Catalog;
import by.rdepam.news.domain.Category;
import by.rdepam.news.domain.News;
import by.rdepam.news.domain.Subcategory;
import by.rdepam.news.service.NewsJAXBMarshaller;
import by.rdepam.news.service.NewsJAXBUnmarshaller;

public class Main {

	public static void main(String[] args) throws JAXBException, IOException {
		NewsJAXBUnmarshaller unm = new NewsJAXBUnmarshaller();
		unm.unmarsh("src/main/resources/news.xml");
		Catalog newsList = unm.getCatalog();
		search(newsList);
		newsList=add(newsList);
		NewsJAXBMarshaller m = new NewsJAXBMarshaller();
		m.marshal(newsList);
	}

	public static void search(Catalog newsList) throws IOException{
		System.out.println("Do you what to find news? (0 - no; 1 - yes)");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		if (Integer.parseInt(in.readLine()) == 1) {
			System.out.println("Select search criterion:\n1-news name;\n2-author\n3-date of issue\n4-news category;");
			int a = Integer.parseInt(in.readLine());
			List<News> allNews = new ArrayList<News>();
			List<Category> allCategories = newsList.getCategories();
			int len = allCategories.size();
			for (int i = 0; i < len; i++) {
				List<Subcategory> allSubcategories = allCategories.get(i).getSubcategories();
				int len2 = allSubcategories.size();
				for (int j = 0; j < len2; j++) {
					allNews.addAll(allSubcategories.get(j).getNews());
				}
			}
			int length = allNews.size();
			switch (a) {
			case 1: {
				System.out.println("enter news name");
				String s = in.readLine();
				for (int i = 0; i < length; i++) {
					if (s.equals(allNews.get(i).getName())) {
						System.out.println(allNews.get(i).show());
					}
				}
			}
				break;
			case 2: {

				System.out.println("enter author");
				String s = in.readLine();
				for (int i = 0; i < length; i++) {
					if (allNews.get(i).getProviders().hasAuthor(s)) {
						System.out.println(allNews.get(i).show());
					}
				}
			}
				break;
			case 3: {

				System.out.println("enter date of issue");
				String s = in.readLine();
				for (int i = 0; i < length; i++) {
					if (s.equals(allNews.get(i).getDateOfIssue())) {
						System.out.println(allNews.get(i).show());
					}
				}
			}
				break;
			case 4: {
				System.out.println("enter name of news category");
				String s = in.readLine();
				for (int i = 0; i < len; i++) {
					if (s.equals(allCategories.get(i).getName())) {
						System.out.println(allCategories.get(i).show());
					}
				}

			}
				break;
			}
		}
	}
	

	private static Catalog add(Catalog list) throws NumberFormatException, IOException {
		System.out.println("Do you what to add news? (0 - no; 1 - yes)");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		News newNews=new News();
		if (Integer.parseInt(in.readLine()) == 1) {
			System.out.println("Enter news name");
			newNews.setName(in.readLine());
			System.out.println("Enter authors (seperator - ;)");
			newNews.setProviders(in.readLine());
			System.out.println("Enter data of issue");
			newNews.setDateOfIssue(in.readLine());
			System.out.println("Enter news body");
			newNews.setBody(in.readLine());
			System.out.println("Enter news category name");
			String categoryN = in.readLine();
			System.out.println("Enter news subcategory name");
			String subcategoryN = in.readLine();
			Category category = list.getCategoryByName(categoryN);
			Subcategory subcategory = category.getSubcategoryByName(subcategoryN);
			subcategory.addNews(newNews);
			category.addSubcategory(subcategory);
			list.addCategory(category);
			return list;
		}
		return list;
	}
}
