package by.trepam.multithreading.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.multithreading.command.Command;
import by.trepam.multithreading.service.FileService;
import by.trepam.multithreading.util.ConstString;

public class Controller {

	private final static Logger logger = LogManager.getLogger(Logger.class.getName());
	private File outFile;
	private FileService service;
	private Double result = 0.0;
	private List<ThreadCalculator> threads = new ArrayList<ThreadCalculator>();

	public void count(String path) throws IOException, InterruptedException {
		createOutputFile(path);
		service = new FileService();
		String name = "in?.dat";
		Integer i = 1;
		String current = path + ConstString.DOUBLE_SLASH
				+ name.replace(ConstString.QUESTION_MARK, (CharSequence) i.toString());
		File file = new File(current);
		ThreadCalculator thread = null;
		while (file.exists()) {
			thread =  new ThreadCalculator(current);			
			threads.add(thread);
			thread.start();
			i++;
			current = path + ConstString.DOUBLE_SLASH
					+ name.replace(ConstString.QUESTION_MARK, (CharSequence) i.toString());
			file = new File(current);
		}
		for (Thread t: threads) {
		    t.join();
		}
		service.write(result, outFile);

	}

	private void createOutputFile(String path) {
		outFile = new File(path + ConstString.DOUBLE_SLASH + ConstString.OUT_FILE_NAME);
		if (outFile.exists()) {
			try (PrintWriter writer = new PrintWriter(outFile)) {
				writer.print(ConstString.EMPTY_STRING);
			} catch (IOException e) {
				logger.error("Exception occurred during creating output file", e);
			}
		} else {
			try {
				outFile.createNewFile();
			} catch (IOException e) {
				logger.error("Exception occurred during creating output file", e);
			}
		}

	}

	class ThreadCalculator extends Thread {
		private String fileName;

		public ThreadCalculator(String fileName) {
			this.fileName = fileName;
		}

		public void run() {
			Command command;
			String file;
			file = service.read(fileName);
			if (file != null) {
				file = service.read(fileName);
				if (file.length() > 0) {
					command = service.getCommand(file);
					if (command != null) {
						double[] in = service.getArray(file);
						if (in != null) {
							command.setInput(in);
							command.execute();
							double out = command.getResult();
							synchronized (result) {
								result += out;
							}
						}
					}
				} else {
					logger.error("File is empty");
				}
			} else {
				logger.error("Can't read file");
			}
		}
	}

}
