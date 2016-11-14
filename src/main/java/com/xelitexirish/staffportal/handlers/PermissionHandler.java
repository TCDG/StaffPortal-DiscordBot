package com.xelitexirish.staffportal.handlers;

import com.xelitexirish.staffportal.StaffPortal;
import com.xelitexirish.staffportal.utils.Constants;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;

/**
 * Created by XeliteXirish on 14/11/2016. www.xelitexirish.com
 */
public class PermissionHandler {

    public static final Permission ADMIN_PERMISSION = Permission.MESSAGE_MANAGE;

    public static boolean isUserAdmin(Guild guild, User user) {
        return hasPermission(guild, user, ADMIN_PERMISSION);
    }

    public static boolean isUserMaintainer(User user){
        return user.getId().equals(StaffPortal.MAINTAINER_ID);
    }

    public static boolean hasPermission(Guild guild, User user, Permission permission) {
        return user.getId().equals(Constants.XELITEXIRISH_ID) || isUserMaintainer(user) || guild.getMember(user).hasPermission(permission);
    }
}
