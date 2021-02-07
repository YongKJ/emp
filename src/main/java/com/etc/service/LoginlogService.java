package com.etc.service;

import com.etc.domain.Loginlog;

import java.util.List;

public interface LoginlogService {

    Loginlog getLoginlogById(int id);

    List<Loginlog> getAllLoginlog();

    List<Loginlog> getLoginlogByNo(String no);

    void addLoginlog(Loginlog loginlog);

    void modLoginlogById(Loginlog loginlog);

    void delLoginlogById(int id);

}
