package com.lu.single.modular.business.model;

import javax.persistence.*;

/**
 * @program LuBoot
 * @description: jpa 通过实体类建表
 * @author: zhanglu
 * @create: 2019-09-06 16:27:00
 */
@Entity
@Table(name = "test_table")
public class TestTable {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @Column(name = "last_name",length = 50) //这是和数据表对应的一个列
    private String lastName;
    @Column //省略默认列名就是属性名
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
