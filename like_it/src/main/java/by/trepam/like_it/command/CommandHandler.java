package by.trepam.like_it.command;

import java.util.HashMap;
import java.util.Map;

import by.trepam.like_it.command.impl.CategoriesCommand;
import by.trepam.like_it.command.impl.CategoryCommand;
import by.trepam.like_it.command.impl.LocalizationCommand;
import by.trepam.like_it.command.impl.LoginCommand;
import by.trepam.like_it.command.impl.LogoutCommand;

public class CommandHandler {
	private static CommandHandler handler = new CommandHandler();
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

	private CommandHandler() {
		commands.put(CommandName.LOGIN, new LoginCommand());
		commands.put(CommandName.LOGOUT, new LogoutCommand());
		commands.put(CommandName.LOCALIZATION, new LocalizationCommand());
		commands.put(CommandName.CATEGORIES, new CategoriesCommand());
		commands.put(CommandName.CATEGORY, new CategoryCommand());
	}
	
	public static CommandHandler getInstance(){
		return handler;
	}

	public Command getCommand(String name) {
		CommandName commandName = CommandName.valueOf(name);
		Command command = commands.get(commandName);

		return command;

	}

}
