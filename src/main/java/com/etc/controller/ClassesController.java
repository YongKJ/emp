package com.etc.controller;

import com.etc.basic.controller.BasicController;
import com.etc.domain.Classes;
import com.etc.domain.Major;
import com.etc.domain.Student;
import com.etc.service.ClassesService;
import com.etc.service.MajorService;
import com.etc.service.StudentService;
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
@RequestMapping("/classes")
public class ClassesController extends BasicController {

    @Autowired
    @Qualifier("classesService")
    private ClassesService classesService;

    @Autowired
    @Qualifier("majorService")
    private MajorService majorService;

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @ModelAttribute
    @RequestMapping("/getClassesList")
    public void getClassesList(HttpServletRequest request, HttpServletResponse response, int page, String limit) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        int pageSize = Integer.valueOf(limit);
        int start = -1;
        int end = -1;

        List<Classes> classesLists = classesService.getAllClasses();
        json.put("count", classesLists.size());

        int listSum = classesLists.size();
        start = (page - 1) * pageSize;
        end = start + pageSize <= listSum ? start + pageSize : listSum;

        List<Classes> classesListsPage = new ArrayList<>();
        List<Major> majorsList = majorService.getAllMajor();
        List<Student> studentsList = studentService.getAllStudent();
        for(int i = start; i < end; i++) {
            for(int j = 0; j < majorsList.size(); j++) {
                if(classesLists.get(i).getMajorId() == majorsList.get(j).getId()) {
                    classesLists.get(i).setMajorName(majorsList.get(j).getMajorName());
                    break;
                }
            }
            int studentNum = 0;
            for(int j = 0; j < studentsList.size(); j++) {
                if(classesLists.get(i).getId() == studentsList.get(j).getClassId()) {
                    studentNum++;
                }
            }
            classesLists.get(i).setStudentNum(studentNum);
            classesListsPage.add(classesLists.get(i));
        }
        json.put("data", classesListsPage);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getAllClasses")
    public void getAllClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        List<Classes> classesLists = classesService.getAllClasses();
        json.put("classesLists", classesLists);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getClasses")
    public void getClasses(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        Classes classes = classesService.getClassesById(id);
        List<Major> majorsList = majorService.getAllMajor();
        for(int i = 0; i < majorsList.size(); i++) {
            if(classes.getMajorId() == majorsList.get(i).getId()) {
                classes.setMajorName(majorsList.get(i).getMajorName());
                break;
            }
        }

        json.put("classes", classes);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/addClasses")
    public void addClasses(HttpServletRequest request, HttpServletResponse response, Classes classes) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        classesService.addClasses(classes);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modClasses")
    public void modClasses(HttpServletRequest request, HttpServletResponse response, Classes classes) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        classesService.modClassesById(classes);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/delClasses")
    public void delClasses(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        classesService.delClassesById(id);

        this.writeJson(json.toString(), response);
    }

}
