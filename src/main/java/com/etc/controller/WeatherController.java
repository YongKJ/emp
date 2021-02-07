package com.etc.controller;

import com.etc.basic.controller.BasicController;
import com.wxapi.WxApiCall.WxApiCall;
import com.wxapi.model.RequestModel;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WeatherController extends BasicController {

    @RequestMapping("/getWeather")
    public void getWeather(HttpServletRequest request, HttpServletResponse response, String city) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        RequestModel model = new RequestModel();
        model.setGwUrl("https://way.jd.com/jisuapi/weather");
        model.setAppkey("a102f3a731d018c6ba95d5df7b34d240");
        Map queryMap = new HashMap();
        queryMap.put("city", city); //访问参数
        model.setQueryParams(queryMap);
        WxApiCall call = new WxApiCall();
        call.setModel(model);

        JSONObject weather = JSONObject.fromObject(call.request());
        json.put("weather", weather);
        this.writeJson(json.toString(), response);
    }

}
