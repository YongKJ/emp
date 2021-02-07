package com.etc.service.impl;

import com.etc.domain.Depart;
import com.etc.mapper.DepartMapper;
import com.etc.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departService")
public class DepartServiceImpl implements DepartService {

    @Autowired
    DepartMapper departMapper;

    @Override
    public Depart getDepartById(int id) {
        return departMapper.getDepartById(id);
    }

    @Override
    public List<Depart> getAllDepart() {
        return departMapper.getAllDepart();
    }

    @Override
    public void addDepart(Depart depart) {
        departMapper.addDepart(depart);
    }

    @Override
    public void modDepartById(Depart depart) {
        departMapper.modDepartById(depart);
    }

    @Override
    public void delDepartById(int id) {
        departMapper.delDepartById(id);
    }

}
