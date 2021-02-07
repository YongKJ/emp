package com.etc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Major {

    private int id;
    private String major_name;
    private String major_time;
    private String major_date;
    private int major_type;
    private String major_type_name;
    private int major_delete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMajorName() {
        return major_name;
    }

    public void setMajorName(String major_name) {
        this.major_name = major_name;
    }

    public String getMajorTypeName() {
        return major_type_name;
    }

    public void setMajorTypeName(String major_type_name) {
        this.major_type_name = major_type_name;
    }

    public String getMajorTime() {
        return major_time;
    }

    public void setMajorTime(String major_time) {
        this.major_time = major_time;
    }

    public String getMajorDate() {
        return major_date;
    }

    public void setMajorDate(String major_date) {
        this.major_date = major_date;
    }

    public int getMajorType() {
        return major_type;
    }

    public void setMajorType(int major_type) {
        this.major_type = major_type;
    }

    public int getMajorDelete() {
        return major_delete;
    }

    public void setMajorDelete(int major_delete) {
        this.major_delete = major_delete;
    }

}
