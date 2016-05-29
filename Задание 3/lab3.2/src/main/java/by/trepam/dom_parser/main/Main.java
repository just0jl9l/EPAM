package by.trepam.dom_parser.main;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import by.rdepam.factory.DOMFactory;
import by.rdepam.parser.domain.DOMDocument;
import by.rdepam.parser.service.DOMViewer;
import by.rdepam.parser.service.IParser;


public class Main {
	public static void main(String[] args){
		JFileChooser fileopen = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.xml", "xml");
		fileopen.setFileFilter(filter);
		int ret = fileopen.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileopen.getSelectedFile();
			DOMFactory factory=DOMFactory.getInstance();
			IParser parser = factory.getParser();
			parser.parse(file);
			DOMDocument document = parser.getDocument();
			DOMViewer creator = new DOMViewer();
			System.out.println(creator.show(document));
		}
		
	}
}
