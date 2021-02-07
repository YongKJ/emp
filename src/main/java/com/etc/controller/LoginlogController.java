package com.etc.controller;

import com.etc.basic.controller.BasicController;
import com.etc.domain.Loginlog;
import com.etc.service.LoginlogService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/loginlog")
public class LoginlogController extends BasicController {

    @Autowired
    @Qualifier("loginlogService")
    private LoginlogService loginlogService;

    @ModelAttribute
    @RequestMapping("/getLoginlogsList")
    public void getLoginlogsList(HttpServletRequest request, HttpServletResponse response, int page, String limit) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        int pageSize = Integer.valueOf(limit);
        int start = -1;
        int end = -1;

        List<Loginlog> loginlogsList = loginlogService.getAllLoginlog();
        json.put("count", loginlogsList.size());

        int listSum = loginlogsList.size();
        start = (page - 1) * pageSize;
        end = start + pageSize <= listSum ? start + pageSize : listSum;

        List<Loginlog> loginlogsListPage = new ArrayList<>();
        for(int i = start; i < end; i++) {
            loginlogsListPage.add(loginlogsList.get(i));
        }
        json.put("data", loginlogsListPage);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getLoginlog")
    public void getLoginlog(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        Loginlog loginlog = loginlogService.getLoginlogById(id);

        json.put("loginlog", loginlog);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getLoginlogByNo")
    public void getLoginlogByNo(HttpServletRequest request, HttpServletResponse response, String no) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        List<Loginlog> loginlogsList = loginlogService.getLoginlogByNo(no);
        List<Loginlog> loginlogsListNew = new ArrayList<>();
        int length = loginlogsList.size() > 8 ? 8 : loginlogsList.size();
        for(int i = 0; i < length; i++) {
            loginlogsListNew.add(loginlogsList.get(i));
        }

        json.put("loginlogsList", loginlogsListNew);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/addLoginlog")
    public void addLoginlog(HttpServletRequest request, HttpServletResponse response, Loginlog loginlog) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        loginlogService.addLoginlog(loginlog);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modLoginlog")
    public void modLoginlog(HttpServletRequest request, HttpServletResponse response, Loginlog loginlog) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        loginlogService.modLoginlogById(loginlog);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/delLoginlog")
    public void delLoginlog(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        loginlogService.delLoginlogById(id);

        this.writeJson(json.toString(), response);
    }
    
}
