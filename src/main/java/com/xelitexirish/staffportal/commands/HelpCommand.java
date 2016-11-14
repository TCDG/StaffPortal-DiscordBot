package com.xelitexirish.staffportal.commands;

import com.xelitexirish.staffportal.StaffPortal;
import com.xelitexirish.staffportal.utils.Constants;
import com.xelitexirish.staffportal.utils.MessageUtils;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by XeliteXirish on 14/11/2016. www.xelitexirish.com
 */
public class HelpCommand implements ICommand {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        if (args.length == 0){
            event.getTextChannel().sendMessage(getHelpMessage());
        }else {
            String helpCommand = args[0];

            ICommand command = getCommandFromString(helpCommand);
            sendHelpMessage(event, command);
        }
    }

    @Override
    public String help() {
        return "Use '" + Constants.COMMAND_PREFIX + "help <command name>' to view more information about that command!";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String getTag() {
        return "help";
    }

    private Message getHelpMessage(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hey I'm StaffPortal-Bot, my author is XeliteXirish! Check his website out (www.xelitexirish.com) \n");
        stringBuilder.append("To use a command, start it with: " + Constants.COMMAND_PREFIX + "\n\n");
        stringBuilder.append("The following commands can be used by this bot: \n");
        for (ICommand command : StaffPortal.commands.values()){
            stringBuilder.append("\t" + command.getTag() + ": " + command.help() + "\n");
        }

        return MessageUtils.wrapStringInCodeBlock(stringBuilder.toString(), "css");
    }

    private ICommand getCommandFromString(String commandName) {
        if (StaffPortal.commands.containsKey(commandName)){
            return StaffPortal.commands.get(commandName);
        }
        return null;
    }

    private static void sendHelpMessage(MessageReceivedEvent event, ICommand command) {
        if (command != null) {
            if (command.help() != null) {
                event.getTextChannel().sendMessage(command.help());
            } else {
                event.getTextChannel().sendMessage("Sorry there is no info available for this command, please contact a bot administrator.");
            }
        }else {
            event.getTextChannel().sendMessage("Sorry but that is not a recognised command!");
        }
    }
}
