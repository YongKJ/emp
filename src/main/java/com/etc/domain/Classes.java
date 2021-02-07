package com.etc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classes {

    private int id;
    private int major_id;
    private String major_name;
    private String class_name;
    private int studentNum;
    private String class_date;
    private String class_time;
    private String class_address;
    private int class_delete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMajorId() {
        return major_id;
    }

    public void setMajorId(int major_id) {
        this.major_id = major_id;
    }

    public String getMajorName() {
        return major_name;
    }

    public void setMajorName(String major_name) {
        this.major_name = major_name;
    }

    public String getClassName() {
        return class_name;
    }

    public void setClassName(String class_name) {
        this.class_name = class_name;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public String getClassDate() {
        return class_date;
    }

    public void setClassDate(String class_date) {
        this.class_date = class_date;
    }

    public String getClassTime() {
        return class_time;
    }

    public void setClassTime(String class_time) {
        this.class_time = class_time;
    }

    public String getClassAddress() {
        return class_address;
    }

    public void setClassAddress(String class_address) {
        this.class_address = class_address;
    }

    public int getClassDelete() {
        return class_delete;
    }

    public void setClassDelete(int class_delete) {
        this.class_delete = class_delete;
    }
}
