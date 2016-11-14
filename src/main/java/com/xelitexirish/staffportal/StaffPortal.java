package com.xelitexirish.staffportal;

import com.xelitexirish.staffportal.commands.HelpCommand;
import com.xelitexirish.staffportal.commands.ICommand;
import com.xelitexirish.staffportal.handlers.BotListener;
import com.xelitexirish.staffportal.utils.BotLogger;
import com.xelitexirish.staffportal.utils.CommandParser;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class StaffPortal {

    public static JDA jda;
    public static final CommandParser parser = new CommandParser();
    public static HashMap<String, ICommand> commands = new HashMap<>();

    private static String DISCORD_TOKEN;
    public static String MAINTAINER_ID;
    public static String API_BASE_ENDPOINT;

    public static void main(String[] args) {

        if (args.length >= 2){
            DISCORD_TOKEN = args[0];
            MAINTAINER_ID = args[1];

            if (args.length >= 3){
                API_BASE_ENDPOINT = args[2];
            }

        }else {
            BotLogger.error("Please enter a valid discord token and maintainer ID and try again!");
            return;
        }

        try {
            jda = new JDABuilder(AccountType.BOT).setToken(DISCORD_TOKEN).setAutoReconnect(true).addListener(new BotListener()).buildAsync();

        } catch (LoginException | RateLimitedException e) {
            e.printStackTrace();
        }

        registerCommands();

        BotLogger.info("StaffPortal bot activated...");
    }

    private static void registerCommands(){
        commands.put("help", new HelpCommand());
    }



    /**
     * Helper Methods
     */

    public static void handleCommand(CommandParser.CommandContainer cmd){
        if(commands.containsKey(cmd.invoke)){
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if(safe){
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
    }
}
