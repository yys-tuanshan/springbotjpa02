package com.com.springbotjpa.dao;

import com.com.springbotjpa.bean.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptDao extends JpaRepository<Dept,Integer> {

    List<Dept> findDeptByDeptnoIsNot(Integer eid);

}
