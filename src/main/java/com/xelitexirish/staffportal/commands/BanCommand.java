package com.xelitexirish.staffportal.commands;

import com.xelitexirish.staffportal.handlers.PermissionHandler;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by XeliteXirish on 14/11/2016. www.xelitexirish.com
 */
public class BanCommand implements ICommand {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        // Check if the user has kick/ban perms
        if (PermissionHandler.hasPermission(event.getGuild(), event.getAuthor(), Permission.KICK_MEMBERS) || PermissionHandler.hasPermission(event.getGuild(), event.getAuthor(), Permission.BAN_MEMBERS)){

            // Check if there is a mentioned user
            for (User mentionedUsers : event.getMessage().getMentionedUsers()){

            }
        }
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String getTag() {
        return "ban";
    }
}
