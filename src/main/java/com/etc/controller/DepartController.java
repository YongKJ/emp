package com.etc.controller;

import com.etc.basic.controller.BasicController;
import com.etc.domain.*;
import com.etc.service.DepartService;
import com.etc.service.EmpService;
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
@RequestMapping("/depart")
public class DepartController extends BasicController {

    @Autowired
    @Qualifier("departService")
    private DepartService departService;

    @Autowired
    @Qualifier("empService")
    private EmpService empService;

    @ModelAttribute
    @RequestMapping("/getDepartsList")
    public void getDepartsList(HttpServletRequest request, HttpServletResponse response, int page, String limit) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        int pageSize = Integer.valueOf(limit);
        int start = -1;
        int end = -1;

        List<Depart> departsList = departService.getAllDepart();
        json.put("count", departsList.size());

        int listSum = departsList.size();
        start = (page - 1) * pageSize;
        end = start + pageSize <= listSum ? start + pageSize : listSum;

        List<Depart> departsListPage = new ArrayList<>();
        List<Emp> empsList = empService.getAllEmp();
        for(int i = start; i < end; i++) {
            int empNum = 0;
            for(int j = 0; j < empsList.size(); j++) {
                if(departsList.get(i).getId() == empsList.get(j).getDid()) {
                    empNum++;
                }
            }
            departsList.get(i).setEmpNum(empNum);
            departsListPage.add(departsList.get(i));
        }
        json.put("data", departsListPage);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getAllDeparts")
    public void getDepartsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        List<Depart> departsList = departService.getAllDepart();

        json.put("departsList", departsList);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getDepart")
    public void getDepart(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        Depart depart = departService.getDepartById(id);

        json.put("depart", depart);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/addDepart")
    public void addDepart(HttpServletRequest request, HttpServletResponse response, Depart depart) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        departService.addDepart(depart);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modDepart")
    public void modDepart(HttpServletRequest request, HttpServletResponse response, Depart depart) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        departService.modDepartById(depart);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/delDepart")
    public void delDepart(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        departService.delDepartById(id);

        this.writeJson(json.toString(), response);
    }
    
}
