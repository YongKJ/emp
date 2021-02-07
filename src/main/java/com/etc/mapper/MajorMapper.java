package com.etc.mapper;

import com.etc.domain.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorMapper {

    Major getMajorById(@Param("id") int id);

    List<Major> getAllMajor();

    void addMajor(Major major);

    void modMajorById(Major major);

    void delMajorById(@Param("id") int id);
    
}
