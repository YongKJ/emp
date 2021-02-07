package com.etc.controller;

import com.etc.basic.controller.BasicController;
import com.etc.domain.MajorType;
import com.etc.service.MajorTypeService;
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
@RequestMapping("/majorType")
public class MajorTypeController extends BasicController {

    @Autowired
    @Qualifier("majorTypeService")
    private MajorTypeService majorTypeService;

    @ModelAttribute
    @RequestMapping("/getMajorTypesList")
    public void getMajorTypesList(HttpServletRequest request, HttpServletResponse response, int page, String limit) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        int pageSize = Integer.valueOf(limit);
        int start = -1;
        int end = -1;

        List<MajorType> majorTypesList = majorTypeService.getAllMajorType();
        json.put("count", majorTypesList.size());

        int listSum = majorTypesList.size();
        start = (page - 1) * pageSize;
        end = start + pageSize <= listSum ? start + pageSize : listSum;

        List<MajorType> majorTypesListPage = new ArrayList<>();
        for(int i = start; i < end; i++) {
            majorTypesListPage.add(majorTypesList.get(i));
        }
        json.put("data", majorTypesListPage);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getAllMajorTypes")
    public void getAllMajorTypes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        List<MajorType> majorTypesList = majorTypeService.getAllMajorType();
        json.put("majorTypesList", majorTypesList);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getMajorType")
    public void getMajorType(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        MajorType majorType = majorTypeService.getMajorTypeById(id);

        json.put("majorType", majorType);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/addMajorType")
    public void addMajorType(HttpServletRequest request, HttpServletResponse response, MajorType majorType) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        majorTypeService.addMajorType(majorType);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modMajorType")
    public void modMajorType(HttpServletRequest request, HttpServletResponse response, MajorType majorType) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        majorTypeService.modMajorTypeById(majorType);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/delMajorType")
    public void delMajorType(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        majorTypeService.delMajorTypeById(id);

        this.writeJson(json.toString(), response);
    }

}
