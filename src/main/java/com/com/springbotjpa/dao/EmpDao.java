package com.com.springbotjpa.dao;

import com.com.springbotjpa.bean.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface EmpDao extends PagingAndSortingRepository<Emp ,Integer> {


    Emp findByEidAndEname(Integer eid,String ename);
    //根据eid获取信息
    Emp findByEid(Integer eid);



}
