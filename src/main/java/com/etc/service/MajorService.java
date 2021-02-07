package com.etc.service;

import com.etc.domain.Major;

import java.util.List;

public interface MajorService {

    Major getMajorById(int id);

    List<Major> getAllMajor();

    void addMajor(Major major);

    void modMajorById(Major major);

    void delMajorById(int id);

}
