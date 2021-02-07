package com.etc.mapper;

import com.etc.domain.Classes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassesMapper {

    Classes getClassesById(@Param("id") int id);

    List<Classes> getAllClasses();

    void addClasses(Classes classes);

    void modClassesById(Classes classes);

    void delClassesById(@Param("id") int id);

}
