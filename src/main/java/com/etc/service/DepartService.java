package com.etc.service;

import com.etc.domain.Depart;

import java.util.List;

public interface DepartService {

    Depart getDepartById(int id);

    List<Depart> getAllDepart();

    void addDepart(Depart depart);

    void modDepartById(Depart depart);

    void delDepartById(int id);
}
