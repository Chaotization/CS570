package com.example.dataset;

import java.io.IOException;
import java.sql.*;

public class mySQL {
    public static void main(String[] args) throws IOException,SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dataset","root","0812");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        while(resultSet.next()){
            System.out.println(resultSet.getString("firstName"));
        }
    }
}