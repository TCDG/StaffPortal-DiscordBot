package com.xelitexirish.staffportal.utils;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

/**
 * Created by XeliteXirish on 14/11/2016. www.xelitexirish.com
 */
public class MessageUtils {

    public static Message getNoPermissionMsg(Permission permission) {
        return MessageUtils.wrapStringInCodeBlock("Sorry but you don't have the required permission to use this command. Required permission: " + permission.name());
    }

    public static Message wrapStringInCodeBlock(String message) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.appendCodeBlock(message, "");
        return messageBuilder.build();
    }

    public static Message wrapStringInCodeBlock(String message, String language) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.appendCodeBlock(message, language);
        return messageBuilder.build();
    }
}
