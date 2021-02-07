package com.etc.controller;


import com.etc.basic.controller.BasicController;
import com.etc.domain.Classes;
import com.etc.domain.Student;
import com.etc.service.ClassesService;
import com.etc.service.StudentService;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController extends BasicController {

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Autowired
    @Qualifier("classesService")
    private ClassesService classesService;

    @ModelAttribute
    @RequestMapping("/getStudentsList")
    public void getStudentsList(HttpServletRequest request, HttpServletResponse response, int page, String limit) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        int pageSize = Integer.valueOf(limit);
        int start = -1;
        int end = -1;

        List<Student> studentsList = studentService.getAllStudent();
        json.put("count", studentsList.size());

        int listSum = studentsList.size();
        start = (page - 1) * pageSize;
        end = start + pageSize <= listSum ? start + pageSize : listSum;

        List<Student> studentsListPage = new ArrayList<>();
        List<Classes> classesLists = classesService.getAllClasses();
        for(int i = start; i < end; i++) {
            for(int j = 0; j < classesLists.size(); j++) {
                if(studentsList.get(i).getClassId() == classesLists.get(j).getId()) {
                    studentsList.get(i).setClassName(classesLists.get(j).getClassName());
                    break;
                }
            }
            studentsListPage.add(studentsList.get(i));
        }
        json.put("data", studentsListPage);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/searchStudents")
    public void searchStudents(HttpServletRequest request, HttpServletResponse response, int page, String limit, String name, int class_id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg", "");

        int pageSize = Integer.valueOf(limit);
        int start = -1;
        int end = -1;

        List<Student> allStudents = studentService.getAllStudent();
        List<Student> studentsList = new ArrayList<>();
        for(int i = 0; i < allStudents.size(); i++) {
            if(name != null) {
                if(name != "" && class_id != -1) {
                    if(allStudents.get(i).getName().indexOf(name) != -1 && allStudents.get(i).getClassId() == class_id) {
                        studentsList.add(allStudents.get(i));
                    }
                }else if(name == "" && class_id != -1) {
                    if(allStudents.get(i).getClassId() == class_id) {
                        studentsList.add(allStudents.get(i));
                    }
                }else if(name != "" && class_id == -1) {
                    if(allStudents.get(i).getName().indexOf(name) != -1) {
                        studentsList.add(allStudents.get(i));
                    }
                }
            }else{
                if(class_id != -1) {
                    if(allStudents.get(i).getClassId() == class_id) {
                        studentsList.add(allStudents.get(i));
                    }
                }
            }
        }

        json.put("count", studentsList.size());

        int listSum = studentsList.size();
        start = (page - 1) * pageSize;
        end = start + pageSize <= listSum ? start + pageSize : listSum;

        List<Student> studentsListPage = new ArrayList<>();
        List<Classes> classesLists = classesService.getAllClasses();
        for(int i = start; i < end; i++) {
            for(int j = 0; j < classesLists.size(); j++) {
                if(studentsList.get(i).getClassId() == classesLists.get(j).getId()) {
                    studentsList.get(i).setClassName(classesLists.get(j).getClassName());
                    break;
                }
            }
            studentsListPage.add(studentsList.get(i));
        }
        json.put("data", studentsListPage);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getStudent")
    public void getStudent(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        Student student = studentService.getStudentById(id);
        List<Classes> classesLists = classesService.getAllClasses();
        for(int i = 0; i < classesLists.size(); i++) {
            if(student.getClassId() == classesLists.get(i).getId()) {
                student.setClassName(classesLists.get(i).getClassName());
                break;
            }
        }

        json.put("student", student);
        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/getStudentsListExcel")
    public void getStudentsListExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Student> studentsList = studentService.getAllStudent();
        List<Classes> classesLists = classesService.getAllClasses();
        for(int i = 0; i < studentsList.size(); i++) {
            for(int j = 0; j < classesLists.size(); j++) {
                if(studentsList.get(i).getClassId() == classesLists.get(j).getId()) {
                    studentsList.get(i).setClassName(classesLists.get(j).getClassName());
                    break;
                }
            }
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生信息表");

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("学号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("所属班级");
        row.createCell(3).setCellValue("性别");
        row.createCell(4).setCellValue("邮箱");
        row.createCell(5).setCellValue("手机号");
        row.createCell(6).setCellValue("QQ");
        row.createCell(7).setCellValue("身份证号");
        row.createCell(8).setCellValue("毕业学校");
        row.createCell(9).setCellValue("学历");
        row.createCell(10).setCellValue("出生日期");
        row.createCell(11).setCellValue("入学日期");

        for(int i = 0, j = 1; i < studentsList.size(); i++, j++) {
            HSSFRow studentRow = sheet.createRow(j);
            studentRow.createCell(0).setCellValue(studentsList.get(i).getNo());
            studentRow.createCell(1).setCellValue(studentsList.get(i).getName());
            studentRow.createCell(2).setCellValue(studentsList.get(i).getClassName());
            studentRow.createCell(3).setCellValue(studentsList.get(i).getSex());
            studentRow.createCell(4).setCellValue(studentsList.get(i).getEmail());
            studentRow.createCell(5).setCellValue(studentsList.get(i).getPhone());
            studentRow.createCell(6).setCellValue(studentsList.get(i).getQq());
            studentRow.createCell(7).setCellValue(studentsList.get(i).getCardno());
            studentRow.createCell(8).setCellValue(studentsList.get(i).getSchool());
            studentRow.createCell(9).setCellValue(studentsList.get(i).getEducation());
            studentRow.createCell(10).setCellValue(studentsList.get(i).getBirthday());
            studentRow.createCell(11).setCellValue(studentsList.get(i).getCreatedate());
        }

        String realPath = request.getServletContext().getRealPath("");
        String userHeadPhoto = realPath + "upload/studentInfo";
        File uploadPath = new File(userHeadPhoto);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String studentExcel = "studentExcel.xls";
        String excelPath = userHeadPhoto + "/" + studentExcel;
        File excel = new File(excelPath);
        if(excel.exists()) {
            excel.delete();
        }

        FileOutputStream output = new FileOutputStream(excelPath);
        workbook.write(output);
        output.flush();

        String fileName = "studentExcel.xls";
        String filePath = excelPath;

        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        File f= new File(filePath);
        response.setHeader("Content-Length",String.valueOf(f.length()));
        response.setContentType("multipart/form-data");

        FileInputStream in = new FileInputStream(filePath);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while((len = in.read(buffer)) > 0){
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    @ModelAttribute
    @RequestMapping("/uploadExcel")
    public void uploadExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("excelFile") MultipartFile excelFile) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        String realPath = request.getServletContext().getRealPath("");
        String userHeadPhoto = realPath + "upload/studentInfo";
        File uploadPath = new File(userHeadPhoto);
        if(!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String studentExcel = "studentExcelUpload.xls";
        String excelPath = userHeadPhoto + "/" + studentExcel;
        File excel = new File(excelPath);
        if(excel.exists()) {
            excel.delete();
        }

        excelFile.transferTo(new File(excelPath));

        FileInputStream fis = new FileInputStream(excelPath);
        Workbook workbook = new HSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int firstRowNum  = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        List<Student> studentsList = new ArrayList<>();
        for(int i = firstRowNum + 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            String no = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            String class_name = row.getCell(2).getStringCellValue();
            String sex = row.getCell(3).getStringCellValue();
            String email = row.getCell(4).getStringCellValue();
            String phone = row.getCell(5).getStringCellValue();
            String qq = row.getCell(6).getStringCellValue();
            String cardno = row.getCell(7).getStringCellValue();
            String school = row.getCell(8).getStringCellValue();
            String education = row.getCell(9).getStringCellValue();
            String birthday = row.getCell(10).getStringCellValue();
            String createdate = row.getCell(11).getStringCellValue();

            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setClassName(class_name);
            student.setSex(sex);
            student.setEmail(email);
            student.setPhone(phone);
            student.setQq(qq);
            student.setCardno(cardno);
            student.setSchool(school);
            student.setEducation(education);
            student.setBirthday(birthday);
            student.setCreatedate(createdate);
            student.setFlag(0);
            student.setPhoto("photos\\e49c64f2-0df8-464c-93ad-7ab95fb7867e_cw1.jpg");
            studentsList.add(student);
        }

        List<Classes> classesLists = classesService.getAllClasses();
        for(int i = 0; i < studentsList.size(); i++) {
            for(int j = 0; j < classesLists.size(); j++) {
                if(studentsList.get(i).getClassName().equals(classesLists.get(j).getClassName())) {
                    studentsList.get(i).setClassId(classesLists.get(j).getId());
                    studentService.addStudent(studentsList.get(i));
                    break;
                }
            }
        }

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/addStudent")
    public void addStudent(HttpServletRequest request, HttpServletResponse response, Student student) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        studentService.addStudent(student);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/modStudent")
    public void modStudent(HttpServletRequest request, HttpServletResponse response, Student student) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        studentService.modStudentById(student);

        this.writeJson(json.toString(), response);
    }

    @ModelAttribute
    @RequestMapping("/delStudent")
    public void delStudent(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        JSONObject json = new JSONObject();
        json.put("message", "");

        studentService.delStudentById(id);

        this.writeJson(json.toString(), response);
    }

}
