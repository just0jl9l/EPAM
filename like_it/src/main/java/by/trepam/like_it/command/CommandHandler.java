package by.trepam.like_it.command;

import java.util.HashMap;
import java.util.Map;

import by.trepam.like_it.command.impl.AddCategoryCommand;
import by.trepam.like_it.command.impl.AddMessageCommand;
import by.trepam.like_it.command.impl.CategoriesCommand;
import by.trepam.like_it.command.impl.CategoryCommand;
import by.trepam.like_it.command.impl.ChangeCategoryCommand;
import by.trepam.like_it.command.impl.ChangeMessageCommand;
import by.trepam.like_it.command.impl.ChangePersonalAccountCommand;
import by.trepam.like_it.command.impl.DeleteCategoryCommand;
import by.trepam.like_it.command.impl.DeleteMessageCommand;
import by.trepam.like_it.command.impl.GotoChangeCategoryCommand;
import by.trepam.like_it.command.impl.GotoChangeMessageCommand;
import by.trepam.like_it.command.impl.GotoChangePersonalAccountCommand;
import by.trepam.like_it.command.impl.LocalizationCommand;
import by.trepam.like_it.command.impl.LoginCommand;
import by.trepam.like_it.command.impl.LogoutCommand;
import by.trepam.like_it.command.impl.MessageCommand;
import by.trepam.like_it.command.impl.PersonalAccountCommand;
import by.trepam.like_it.command.impl.RateCommand;
import by.trepam.like_it.command.impl.RegistrationCommand;
import by.trepam.like_it.command.impl.SitemapCommand;

public class CommandHandler {
	private static CommandHandler handler = new CommandHandler();
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

	private CommandHandler() {
		commands.put(CommandName.LOGIN, new LoginCommand());
		commands.put(CommandName.LOGOUT, new LogoutCommand());
		commands.put(CommandName.LOCALIZATION, new LocalizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.CATEGORIES, new CategoriesCommand());
		commands.put(CommandName.CATEGORY, new CategoryCommand());
		commands.put(CommandName.MESSAGE, new MessageCommand());
		commands.put(CommandName.RATE, new RateCommand());
		commands.put(CommandName.SITEMAP, new SitemapCommand());
		commands.put(CommandName.PERSONAL_ACCOUNT, new PersonalAccountCommand());
		commands.put(CommandName.ADD_CATEGORY, new AddCategoryCommand());
		commands.put(CommandName.GOTO_CHANGE_CATEGORY, new GotoChangeCategoryCommand());
		commands.put(CommandName.CHANGE_CATEGORY, new ChangeCategoryCommand());
		commands.put(CommandName.DELETE_CATEGORY, new DeleteCategoryCommand());
		commands.put(CommandName.ADD_MESSAGE, new AddMessageCommand());
		commands.put(CommandName.GOTO_CHANGE_MESSAGE, new GotoChangeMessageCommand());
		commands.put(CommandName.CHANGE_MESSAGE, new ChangeMessageCommand());
		commands.put(CommandName.DELETE_MESSAGE, new DeleteMessageCommand());
		commands.put(CommandName.GOTO_CHANGE_PERSONAL_ACCOUNT, new GotoChangePersonalAccountCommand());
		commands.put(CommandName.CHANGE_PERSONAL_ACCOUNT, new ChangePersonalAccountCommand());
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
