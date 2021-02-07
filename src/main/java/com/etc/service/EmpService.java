package com.etc.service;

import com.etc.domain.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpService {

    Emp getEmpById(int id);

    Emp getEmpByNoAndPass(String no, String pass);

    List<Emp> getAllEmp();

    void addEmp(Emp emp);

    void modEmpById(Emp emp);

    void delEmpById(int id);

}
