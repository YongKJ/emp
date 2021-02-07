package com.etc.service.impl;

import com.etc.domain.MajorType;
import com.etc.mapper.MajorTypeMapper;
import com.etc.service.MajorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("majorTypeService")
public class MajorTypeServiceImpl implements MajorTypeService {

    @Autowired
    MajorTypeMapper majorTypeMapper;

    @Override
    public MajorType getMajorTypeById(int id) {
        return majorTypeMapper.getMajorTypeById(id);
    }

    @Override
    public List<MajorType> getAllMajorType() {
        return majorTypeMapper.getAllMajorType();
    }

    @Override
    public void addMajorType(MajorType majorType) {
        majorTypeMapper.addMajorType(majorType);
    }

    @Override
    public void modMajorTypeById(MajorType majorType) {
        majorTypeMapper.modMajorTypeById(majorType);
    }

    @Override
    public void delMajorTypeById(int id) {
        majorTypeMapper.delMajorTypeById(id);
    }
}
