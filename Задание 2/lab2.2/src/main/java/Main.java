import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
	public static void main(String[] args){
		JFileChooser fileopen = new JFileChooser("E:/!еоюл!");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.xml", "xml");
		fileopen.setFileFilter(filter);
		int ret = fileopen.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileopen.getSelectedFile();
			try {
				DOMParser parser=new DOMParser();
				parser.parse(file);
				DOMDocument document = parser.getDocument();
				DOMViewer view = new DOMViewer();
				System.out.println(view.show(document));
			} catch (IOException e) {
				System.out.println(":(");
			}
		}
		
	}
}
