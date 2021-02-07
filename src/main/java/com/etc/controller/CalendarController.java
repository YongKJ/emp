package com.etc.controller;

import com.etc.basic.controller.BasicController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class CalendarController extends BasicController {

    @RequestMapping("/getCalendar")
    public void getCalendar(HttpServletRequest request, HttpServletResponse response, String year, String month, String holiday) throws Exception {

        String myurl = "https://wnl.idcd.com/json?year=" + year + "&month=" + month + "&holiday=" + holiday;

        URL url = new URL(myurl);
        URLConnection urlConnection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

        StringBuilder calendarJson = new StringBuilder();
        String inputLine = null;
        while ((inputLine = in.readLine()) != null) {
            calendarJson.append(inputLine);
        }

        this.writeJson(calendarJson.toString(), response);
    }

}
