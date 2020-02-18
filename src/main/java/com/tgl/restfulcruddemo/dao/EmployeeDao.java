package com.tgl.restfulcruddemo.dao;

import com.tgl.restfulcruddemo.utill.Department;
import com.tgl.restfulcruddemo.utill.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class EmployeeDao {

    @Autowired
    private DataSource dataSource;

    public Collection<Employee> getEmp(){

       Collection<Employee> collection=new ArrayList<>();

        try {
            Connection connection=dataSource.getConnection();
            Statement statement=connection.createStatement();
            String sql="select * from employeeinfo";
            ResultSet resultSet=statement.executeQuery(sql);
            Integer id;
            String name;
            String adress;
            Integer gender;
            Date birth;
            String department;
            Employee employee;
            while(resultSet.next()){
                id=Integer.valueOf(resultSet.getInt("id"));
                name=resultSet.getString("name");
                adress=resultSet.getString("adress");
                gender=Integer.valueOf(resultSet.getInt("gender"));
                birth=resultSet.getDate("birth");
                department=resultSet.getString("eployeement");
                employee=new Employee(id,name,gender,adress,birth,department);
                collection.add(employee);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return collection;
    }

    public void save(Employee employee){
            String sql1,sql2,sql3,sql4;
            if(employee.getId()!=null){
                sql1="id,";
                sql2="'"+employee.getId()+"',";
            }else{
                sql1="";
                sql2="";
            }
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String birthstr = dateFormat.format(employee.getBirth());
                Date birth = dateFormat.parse(birthstr);
                Integer id = employee.getId();

                Connection connection = dataSource.getConnection();
                String sql = "replace into employeeinfo("+sql1+"name,gender,adress,birth,"
                        + "eployeement)values("+sql2
                        +"'" + employee.getName() + "','" + employee.getGender()
                        + "','" + employee.getAdress() + "','" + birthstr + "',"
                        + "'" + employee.getDepartment() + "')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                connection.close();
            } catch (Exception e) {

            }
        }


    public Employee getEmpById(Integer id){
        Employee employee=new Employee();
        try{
            Connection connection=dataSource.getConnection();
            System.err.println("11111111");
            String sql="select * from employeeinfo where id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                System.err.println("2222");
                String name=resultSet.getString("name");
                String adress=resultSet.getString("adress");
                Date birth=resultSet.getDate("birth");
                String department=resultSet.getString("eployeement");
                Integer gender=resultSet.getInt("gender");
                employee=new Employee(id,name,gender,adress,birth,department);
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return employee;
    }

    public void deleteById(Integer id){
        try{
           Connection connection= dataSource.getConnection();
           String sql="delete from employeeinfo where id=?";
           PreparedStatement preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setInt(1,id);
           preparedStatement.executeQuery();
           connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
