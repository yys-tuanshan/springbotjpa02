package com.com.springbotjpa.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "emp")
@AllArgsConstructor
@NoArgsConstructor
public class Emp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eid;
    @Column(length = 64,nullable = false)
    private String ename;
    @Column
    private Integer sal ;

    //单独学习hibernate注解  jpa 基于hibernate注解完成数据库的操作的
    @ManyToOne
    private Dept dept;


}
