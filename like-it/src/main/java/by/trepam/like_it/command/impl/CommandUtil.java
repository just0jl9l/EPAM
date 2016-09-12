package by.trepam.like_it.command.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class CommandUtil {
	private final static CommandUtil command = new CommandUtil();

	private CommandUtil() {
	}

	public static CommandUtil getInstance() {
		return command;
	}

	public String getImage(HttpServletRequest request, String name) {
		Part part = null;
		File file = null;
		try {
			part = request.getPart(name);
			file = new File(
					request.getServletContext().getRealPath("") + "/content" + System.currentTimeMillis() + ".png");
		} catch (IOException | IllegalStateException | ServletException e) {

		}
		if (part != null) {
			try (InputStream input = part.getInputStream(); OutputStream output = new FileOutputStream(file)) {
				int read;
				byte[] bytes = new byte[1024];
				while ((read = input.read(bytes)) != -1) {
					output.write(bytes, 0, read);
				}
			} catch (IOException e) {

			}

			return file.getName();
		}
		return null;
	}
}
