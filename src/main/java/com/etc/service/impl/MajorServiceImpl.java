package com.etc.service.impl;

import com.etc.domain.Major;
import com.etc.mapper.MajorMapper;
import com.etc.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("majorService")
public class MajorServiceImpl implements MajorService {

    @Autowired
    MajorMapper majorMapper;

    @Override
    public Major getMajorById(int id) {
        return majorMapper.getMajorById(id);
    }

    @Override
    public List<Major> getAllMajor() {
        return majorMapper.getAllMajor();
    }

    @Override
    public void addMajor(Major major) {
        majorMapper.addMajor(major);
    }

    @Override
    public void modMajorById(Major major) {
        majorMapper.modMajorById(major);
    }

    @Override
    public void delMajorById(int id) {
        majorMapper.delMajorById(id);
    }
}
