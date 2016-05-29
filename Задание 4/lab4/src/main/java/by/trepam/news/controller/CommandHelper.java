package by.trepam.news.controller;

import java.util.HashMap;
import java.util.Map;

import by.trepam.news.command.Command;
import by.trepam.news.command.impl.FindNewsCommand;
import by.trepam.news.command.impl.GetCatalogCommand;
import by.trepam.news.command.impl.SaveNewNewsCommand;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

	CommandHelper() {
		commands.put(CommandName.SAVE_NEW_NEWS, new SaveNewNewsCommand());
		commands.put(CommandName.FIND_NEWS, new FindNewsCommand());
		commands.put(CommandName.GET_CATALOG, new GetCatalogCommand());
	}

	public Command getCommand(String name) {
		System.out.println("CommandHelper getCommand");
		CommandName commandName = CommandName.valueOf(name);
		Command command = commands.get(commandName);

		return command;

	}
}
