package com.etc.mapper;

import com.etc.domain.Loginlog;
import com.etc.domain.Loginlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginlogMapper {

    Loginlog getLoginlogById(@Param("id") int id);

    List<Loginlog> getAllLoginlog();

    List<Loginlog> getLoginlogByNo(@Param("no") String no);

    void addLoginlog(Loginlog loginlog);

    void modLoginlogById(Loginlog loginlog);

    void delLoginlogById(@Param("id") int id);
    
}
