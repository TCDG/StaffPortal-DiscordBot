package com.xelitexirish.staffportal.handlers;

import com.xelitexirish.staffportal.StaffPortal;
import net.dv8tion.jda.core.entities.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by XeliteXirish on 14/11/2016. www.xelitexirish.com
 */
public class APIHandler {

    public static final String READ_URL = StaffPortal.API_BASE_ENDPOINT + "read";
    public static final String BAN_URL = StaffPortal.API_BASE_ENDPOINT + "submit";

    public static void submitReport(User author, User bannedUser, String reason, String action, String info, String proof, String expiry) {

        String date = new SimpleDateFormat("yyyy:MM:dd").format(Calendar.getInstance().getTime());

        try {
            URL submitUrl = new URL(BAN_URL);
            HttpURLConnection connection = (HttpURLConnection) submitUrl.openConnection();

            // Add request header
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Opera");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            StringBuilder params = new StringBuilder();
            params.append("date=" + date + "&");
            params.append("offender=" + bannedUser.getName() + "&");
            params.append("admin=" + author.getName() + "&");
            params.append("reason=" + reason + "&");
            params.append("action=" + action + "&");
            params.append("info=" + info + "&");
            params.append("proof=" + proof + "&");
            params.append("expire=" + expiry + "&");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(params.toString());
            outputStream.flush();
            outputStream.close();

            int responceCode = connection.getResponseCode();
            System.out.println(responceCode);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
