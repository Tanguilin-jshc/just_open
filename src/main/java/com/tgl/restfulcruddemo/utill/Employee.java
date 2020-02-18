package com.tgl.restfulcruddemo.utill;


import java.util.Date;

public class Employee {

    private Integer id;
    private String name;
    private Integer gender;
    private String adress;
    private Date birth;
    private String department;

    public Employee(Integer id, String name, Integer gender, String adress, Date birth, String department) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.adress = adress;
        this.birth = birth;
        this.department = department;
    }

    public Employee(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
