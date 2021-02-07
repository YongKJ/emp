package com.etc.controller;

import com.etc.basic.controller.BasicController;
import com.etc.domain.Major;
import com.etc.domain.MajorType;
import com.etc.service.MajorService;
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
@RequestMapping("/major")
public class MajorController extends BasicController {

    @Autowired
    @Qualifier("majorService")
    private MajorService majorService;

    @Autowired
    @Qualifier("majorTypeService")
    private MajorTypeService majorTypeService;

    @ModelAttribute
    @RequestMapping("/getMajorsList")
    public void getMajorsList(HttpServletRequest request, HttpServletResponse response, int page, String limit) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        int pageSize = Integer.valueOf(limit);
        int start = -1;
        int end = -1;

        List<Major> majorsList = majorService.getAllMajor();
        json.put("count", majorsList.size());

        int listSum = majorsList.size();
        start = (page - 1) * pageSize;
        end = start + pageSize <= listSum ? start + pageSize : listSum;

        List<Major> majorsListPage = new ArrayList<>();
        List<MajorType> majorTypesList = majorTypeService.getAllMajorType();
        for(int i = start; i < end; i++) {
            for(int j = 0; j < majorTypesList.size(); j++) {
                if(majorsList.get(i).getMajorType() == majorTypesList.get(j).getId()) {
                    majorsList.get(i).setMajorTypeName(majorTypesList.get(j).getMajortype());
                    break;
                }
            }
            majorsListPage.add(majorsList.get(i));
        }
        json.put("data", majorsListPage);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getAllMajors")
    public void getMajorsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        List<Major> majorsList = majorService.getAllMajor();
        json.put("majorsList", majorsList);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getMajor")
    public void getMajor(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        Major major = majorService.getMajorById(id);
        List<MajorType> majorTypesList = majorTypeService.getAllMajorType();
        for(int i = 0; i < majorTypesList.size(); i++) {
            if(major.getMajorType() == majorTypesList.get(i).getId()) {
                major.setMajorTypeName(majorTypesList.get(i).getMajortype());
                break;
            }
        }

        json.put("major", major);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/addMajor")
    public void addMajor(HttpServletRequest request, HttpServletResponse response, Major major) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        majorService.addMajor(major);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modMajor")
    public void modMajor(HttpServletRequest request, HttpServletResponse response, Major major) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        majorService.modMajorById(major);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/delMajor")
    public void delMajor(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        majorService.delMajorById(id);

        this.writeJson(json.toString(), response);
    }

}
