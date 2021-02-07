package com.etc.controller;

import com.etc.basic.controller.BasicController;
import com.etc.domain.*;
import com.etc.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpController extends BasicController {

    @Autowired
    @Qualifier("empService")
    private EmpService empService;

    @Autowired
    @Qualifier("departService")
    private DepartService departService;

    @ModelAttribute
    @RequestMapping("/getEmpsList")
    public void getEmpsList(HttpServletRequest request, HttpServletResponse response, int page, String limit) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        int pageSize = Integer.valueOf(limit);
        int start = -1;
        int end = -1;

        List<Emp> empsList = empService.getAllEmp();
        json.put("count", empsList.size());

        int listSum = empsList.size();
        start = (page - 1) * pageSize;
        end = start + pageSize <= listSum ? start + pageSize : listSum;

        List<Emp> empsListPage = new ArrayList<>();
        List<Depart> departsList = departService.getAllDepart();
        for(int i = start; i < end; i++) {
            for(int j = 0; j < departsList.size(); j++) {
                if(empsList.get(i).getDid() == departsList.get(j).getId()) {
                    empsList.get(i).setDname(departsList.get(j).getName());
                    break;
                }
            }
            empsList.get(i).setPassLength(empsList.get(i).getPass().length());
            empsList.get(i).setPass(this.md5(empsList.get(i).getPass()));
            empsListPage.add(empsList.get(i));
        }
        json.put("data", empsListPage);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getEmp")
    public void getEmp(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        Emp emp = empService.getEmpById(id);
        List<Depart> departsList = departService.getAllDepart();
        for(int i = 0; i < departsList.size(); i++) {
            if(emp.getDid() == departsList.get(i).getId()) {
                emp.setDname(departsList.get(i).getName());
                break;
            }
        }
        emp.setPassLength(emp.getPass().length());
        emp.setPass(this.md5(emp.getPass()));

        json.put("emp", emp);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response, String no, String pass) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        Emp emp = empService.getEmpByNoAndPass(no, pass);
        if(emp != null) {
            json.put("message", "登录成功！");
            json.put("id", emp.getId());
        }else{
            json.put("message", "登录失败！用户名或密码错误！");
        }

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/addEmp")
    public void addEmp(HttpServletRequest request, HttpServletResponse response, Emp emp) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        empService.addEmp(emp);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modEmp")
    public void modEmp(HttpServletRequest request, HttpServletResponse response, Emp emp) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        empService.modEmpById(emp);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modEmpPhoto")
    public void modEmpPhoto(HttpServletRequest request, HttpServletResponse response, int id, @RequestParam("photo") MultipartFile photo) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        String realPath = request.getServletContext().getRealPath("");
        String userHeadPhoto = realPath + "upload/userHeadPhoto";
        File uploadPath = new File(userHeadPhoto);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        Emp emp = empService.getEmpById(id);
        String oldPhotoName = emp.getPhoto();
        String oldPhotoPath = userHeadPhoto + "/" + oldPhotoName;
        File oldPhoto = new File(oldPhotoPath);
        if(oldPhoto.exists()) {
            oldPhoto.delete();
        }

        String newPhotoName = this.getUUID() + "-" + photo.getOriginalFilename();
        String newPhotoPath = userHeadPhoto + "/" + newPhotoName;
        photo.transferTo(new File(newPhotoPath));

        Emp empNew = new Emp();
        empNew.setId(id);
        empNew.setPhoto(newPhotoName);
        empService.modEmpById(empNew);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/uploadEmpPhoto")
    public void uploadEmpPhoto(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        String realPath = request.getServletContext().getRealPath("");
        String userHeadPhoto = realPath + "upload/userHeadPhoto";
        File uploadPath = new File(userHeadPhoto);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String photoName = this.getUUID() + "-" + file.getOriginalFilename();
        String photoPath = userHeadPhoto + "/" + photoName;
        file.transferTo(new File(photoPath));

        json.put("photo", photoName);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modUploadEmpPhoto")
    public void modUploadEmpPhoto(HttpServletRequest request, HttpServletResponse response, int id, @RequestParam("file") MultipartFile file) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        String realPath = request.getServletContext().getRealPath("");
        String userHeadPhoto = realPath + "upload/userHeadPhoto";
        File uploadPath = new File(userHeadPhoto);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        Emp emp = empService.getEmpById(id);
        String oldPhotoName = emp.getPhoto();
        String oldPhotoPath = userHeadPhoto + "/" + oldPhotoName;
        File oldPhoto = new File(oldPhotoPath);
        if(oldPhoto.exists()) {
            oldPhoto.delete();
        }

        String newPhotoName = this.getUUID() + "-" + file.getOriginalFilename();
        String newPhotoPath = userHeadPhoto + "/" + newPhotoName;
        file.transferTo(new File(newPhotoPath));

        json.put("photo", newPhotoName);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/delEmp")
    public void delEmp(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        String realPath = request.getServletContext().getRealPath("");
        String userHeadPhoto = realPath + "upload/userHeadPhoto";
        File uploadPath = new File(userHeadPhoto);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        Emp emp = empService.getEmpById(id);
        String oldPhotoName = emp.getPhoto();
        String oldPhotoPath = userHeadPhoto + "/" + oldPhotoName;
        File oldPhoto = new File(oldPhotoPath);
        if(oldPhoto.exists()) {
            oldPhoto.delete();
        }

        empService.delEmpById(id);

        this.writeJson(json.toString(), response);
    }
    
}
