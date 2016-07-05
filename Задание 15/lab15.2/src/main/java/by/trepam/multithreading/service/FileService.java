package by.trepam.multithreading.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.trepam.multithreading.command.Command;
import by.trepam.multithreading.command.impl.CommandAddition;
import by.trepam.multithreading.command.impl.CommandMultiplication;
import by.trepam.multithreading.command.impl.CommandSumOfSquares;
import by.trepam.multithreading.util.ConstString;

public class FileService {
	private final static Logger logger = LogManager.getLogger(Logger.class.getName());

	public String read(String fileName) {
		try (FileInputStream inFile = new FileInputStream(fileName)) {
			byte[] str;
			str = new byte[inFile.available()];
			inFile.read(str);
			return new String(str);
		} catch (IOException ex) {
			logger.error("Exception occurred during reading input file", ex);
			return null;
		}

	}

	public Command getCommand(String file) {
		Command command = null;
		int newLine = file.indexOf(ConstString.NEW_LINE);
		if (newLine > 0) {
			String commandNumber = file.substring(0, newLine).trim();
			int commandCase = Integer.parseInt(commandNumber);
			switch (commandCase) {
			case 1: {
				command = new CommandAddition();
			}
				break;

			case 2: {
				command = new CommandMultiplication();
			}
				break;

			case 3: {
				command = new CommandSumOfSquares();
			}
				break;
			}
			return command;
		} else {
			logger.error("Can't find input command");
			return null;
		}
	}

	public double[] getArray(String str) {
		int newLine = str.indexOf(ConstString.NEW_LINE);
		if (newLine > 0) {
			str = str.substring(newLine).trim();
			if (!str.isEmpty()) {
				double[] array = Arrays.stream(str.split(" ")).mapToDouble(Double::parseDouble).toArray();
				return array;
			} else {
				logger.error("Can't find input array");
				return null;
			}
		} else {
			logger.error("Can't find input array");
			return null;
		}
	}

	public void write(Double result, File outFile) {
		try (FileWriter writer = new FileWriter(outFile, true)) {
			writer.write(result.toString());
		} catch (IOException ex) {
			logger.error("Exception occurred during writing output file", ex);
		}
	}

}
