package com.etc;

import com.wxapi.WxApiCall.WxApiCall;
import com.wxapi.model.RequestModel;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        RequestModel model = new RequestModel();
        model.setGwUrl("https://way.jd.com/showapi/areaid");
        model.setAppkey("2142850432f4bd7a073771d42d525b85");
        Map queryMap = new HashMap();
        queryMap.put("area", "丽江"); //访问参数
        model.setQueryParams(queryMap);
        WxApiCall call = new WxApiCall();
        call.setModel(model);
        String s = call.request();

        System.out.println(s);
    }
}