package com.tgl.restfulcruddemo.dao;

import com.tgl.restfulcruddemo.utill.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

@Component
public class DepartmentDao {

    @Autowired
    DataSource dataSource;

    public Collection<Department> getDept(){
        Collection<Department> collection=new ArrayList<>();
        try{
            Connection connection=dataSource.getConnection();
            Statement statement=connection.createStatement();
            String sql="select * from depts";
            ResultSet resultSet=statement.executeQuery(sql);
            Integer id;
            String name;
            Department dept;
            while(resultSet.next()){
                id=resultSet.getInt("id");
                name=resultSet.getString("name");
                dept=new Department(name,id);
                collection.add(dept);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return collection;
    }
}
