package com.etc.mapper;

import com.etc.domain.MajorType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorTypeMapper {

    MajorType getMajorTypeById(@Param("id") int id);

    List<MajorType> getAllMajorType();

    void addMajorType(MajorType majorType);

    void modMajorTypeById(MajorType majorType);

    void delMajorTypeById(@Param("id") int id);

}
