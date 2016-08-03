package by.trepam.like_it.command;

import java.util.HashMap;
import java.util.Map;

import by.trepam.like_it.command.impl.LocalizationCommand;
import by.trepam.like_it.command.impl.SitemapCommand;
import by.trepam.like_it.command.impl.account.ChangePersonalAccountCommand;
import by.trepam.like_it.command.impl.account.GetPersonalAccountCommand;
import by.trepam.like_it.command.impl.account.GotoChangePersonalAccountCommand;
import by.trepam.like_it.command.impl.account.LoginCommand;
import by.trepam.like_it.command.impl.account.LogoutCommand;
import by.trepam.like_it.command.impl.account.RegistrationCommand;
import by.trepam.like_it.command.impl.answer.RateCommand;
import by.trepam.like_it.command.impl.category.AddCategoryCommand;
import by.trepam.like_it.command.impl.category.ChangeCategoryCommand;
import by.trepam.like_it.command.impl.category.DeleteCategoryCommand;
import by.trepam.like_it.command.impl.category.GetCategoriesCommand;
import by.trepam.like_it.command.impl.category.GetCategoryCommand;
import by.trepam.like_it.command.impl.category.GotoChangeCategoryCommand;
import by.trepam.like_it.command.impl.message.AddMessageCommand;
import by.trepam.like_it.command.impl.message.ChangeMessageCommand;
import by.trepam.like_it.command.impl.message.DeleteMessageCommand;
import by.trepam.like_it.command.impl.message.GetMessageCommand;
import by.trepam.like_it.command.impl.message.GotoChangeMessageCommand;

public class CommandHandler {
	private static CommandHandler handler = new CommandHandler();
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

	private CommandHandler() {
		commands.put(CommandName.LOGIN, LoginCommand.getInstance());
		commands.put(CommandName.LOGOUT, LogoutCommand.getInstance());
		commands.put(CommandName.LOCALIZATION, LocalizationCommand.getInstance());
		commands.put(CommandName.REGISTRATION, RegistrationCommand.getInstance());
		commands.put(CommandName.CATEGORIES, GetCategoriesCommand.getInstance());
		commands.put(CommandName.CATEGORY, GetCategoryCommand.getInstance());
		commands.put(CommandName.MESSAGE, GetMessageCommand.getInstance());
		commands.put(CommandName.RATE, RateCommand.getInstance());
		commands.put(CommandName.SITEMAP, SitemapCommand.getInstance());
		commands.put(CommandName.PERSONAL_ACCOUNT, GetPersonalAccountCommand.getInstance());
		commands.put(CommandName.ADD_CATEGORY, AddCategoryCommand.getInstance());
		commands.put(CommandName.GOTO_CHANGE_CATEGORY, GotoChangeCategoryCommand.getInstance());
		commands.put(CommandName.CHANGE_CATEGORY, ChangeCategoryCommand.getInstance());
		commands.put(CommandName.DELETE_CATEGORY, DeleteCategoryCommand.getInstance());
		commands.put(CommandName.ADD_MESSAGE, AddMessageCommand.getInstance());
		commands.put(CommandName.GOTO_CHANGE_MESSAGE, GotoChangeMessageCommand.getInstance());
		commands.put(CommandName.CHANGE_MESSAGE, ChangeMessageCommand.getInstance());
		commands.put(CommandName.DELETE_MESSAGE, DeleteMessageCommand.getInstance());
		commands.put(CommandName.GOTO_CHANGE_PERSONAL_ACCOUNT, GotoChangePersonalAccountCommand.getInstance());
		commands.put(CommandName.CHANGE_PERSONAL_ACCOUNT, ChangePersonalAccountCommand.getInstance());
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
