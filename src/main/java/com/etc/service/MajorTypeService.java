package com.etc.service;

import com.etc.domain.MajorType;

import java.util.List;

public interface MajorTypeService {

    MajorType getMajorTypeById(int id);

    List<MajorType> getAllMajorType();

    void addMajorType(MajorType majorType);

    void modMajorTypeById(MajorType majorType);

    void delMajorTypeById(int id);

}
