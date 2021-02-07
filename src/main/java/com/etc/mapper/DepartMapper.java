package com.etc.mapper;

import com.etc.domain.Depart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartMapper {

    Depart getDepartById(@Param("id") int id);

    List<Depart> getAllDepart();

    void addDepart(Depart depart);

    void modDepartById(Depart depart);

    void delDepartById(@Param("id") int id);
    
}
