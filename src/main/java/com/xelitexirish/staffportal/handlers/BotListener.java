package com.xelitexirish.staffportal.handlers;

import com.xelitexirish.staffportal.StaffPortal;
import com.xelitexirish.staffportal.utils.Constants;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Objects;

/**
 * Created by XeliteXirish on 14/11/2016. www.xelitexirish.com
 */
public class BotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getChannelType() != ChannelType.PRIVATE) {

            if (event.getMessage().getContent().startsWith(Constants.COMMAND_PREFIX) && !Objects.equals(event.getMessage().getAuthor().getId(), event.getJDA().getSelfUser().getId())) {
                StaffPortal.handleCommand(StaffPortal.parser.parse(event.getMessage().getContent().toLowerCase(), event));
            }
        }
    }
}
