package com.etc.service.impl;

import com.etc.domain.Loginlog;
import com.etc.mapper.LoginlogMapper;
import com.etc.service.LoginlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//一定要加@Service

@Service("loginlogService")
public class LoginlogServiceImpl implements LoginlogService {

    @Autowired
    LoginlogMapper loginlogMapper;


    @Override
    public Loginlog getLoginlogById(int id) {
        return loginlogMapper.getLoginlogById(id);
    }

    @Override
    public List<Loginlog> getAllLoginlog() {
        return loginlogMapper.getAllLoginlog();
    }

    @Override
    public List<Loginlog> getLoginlogByNo(String no) {
        return loginlogMapper.getLoginlogByNo(no);
    }

    @Override
    public void addLoginlog(Loginlog loginlog) {
        loginlogMapper.addLoginlog(loginlog);
    }

    @Override
    public void modLoginlogById(Loginlog loginlog) {
        loginlogMapper.modLoginlogById(loginlog);
    }

    @Override
    public void delLoginlogById(int id) {
        loginlogMapper.delLoginlogById(id);
    }
}
