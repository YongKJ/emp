package com.etc.mapper;

import com.etc.domain.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {

    Emp getEmpById(@Param("id") int id);

    Emp getEmpByNoAndPass(@Param("no") String no, @Param("pass") String pass);

    List<Emp> getAllEmp();

    void addEmp(Emp emp);

    void modEmpById(Emp emp);

    void delEmpById(@Param("id") int id);
}
