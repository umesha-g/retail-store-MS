package com.umeshag.retailstorerm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class RefreshTable{

    String sqlUrl = "jdbc:mysql://localhost:3306/retail_store_database";
    String sqlUsername = "user";
    String sqlPassword = "pass";

    // table Reseting method -----------------------------------------------------------------------------------------------------------------------------------------------
    public void refreshTable( String category,DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
                if(category =="employees"){ // for employee table
                    String query = "SELECT emp_id, emp_name, emp_job ,emp_passw FROM employees";
                    try(PreparedStatement statement = connection.prepareStatement(query)){
                        try(ResultSet resultSet = statement.executeQuery(query)){
                            while (resultSet.next()) {
                                String id = resultSet.getString("emp_id");
                                String name = resultSet.getString("emp_name");
                                String job = resultSet.getString("emp_job");
                                String passw = resultSet.getString("emp_passw");
                                tableModel.addRow(new Object[]{id, name, job ,passw});
                            }
                        }
                    }
                }

                else if(category =="stock"){ // for stocks table
                    String query = "SELECT stock_id, stock_name, stock_qntity,stock_price FROM stocks";
                    try (PreparedStatement statement = connection.prepareStatement(query)){
                        try (ResultSet resultSet = statement.executeQuery(query)) {
                            while (resultSet.next()) {
                                String id = resultSet.getString("stock_id");
                                String name = resultSet.getString("stock_name");
                                String qntity = resultSet.getString("stock_qntity");
                                String price = resultSet.getString("stock_price");
                                tableModel.addRow(new Object[]{id, name, qntity,price});
                            }
                        }
                    }
                }

                else if(category =="cust"){ // for customer table
                    String query = "SELECT cus_id, cus_name, cus_phone,cus_loyal FROM customers";
                    try (PreparedStatement statement = connection.prepareStatement(query)){
                        try(ResultSet resultSet = statement.executeQuery(query)) {
                            while (resultSet.next()) {
                                String id = resultSet.getString("cus_id");
                                String name = resultSet.getString("cus_name");
                                String phone = resultSet.getString("cus_phone");
                                String loyal = resultSet.getString("cus_loyal");
                                tableModel.addRow(new Object[]{id, name, phone,loyal});
                            }
                        }
                    }
                }

                else if(category =="tra"){ // for transactions table
                    String query = "SELECT * FROM transactions"; 
                    try (PreparedStatement statement = connection.prepareStatement(query)){
                        try(ResultSet resultSet = statement.executeQuery(query)) {
                            while (resultSet.next()) {
                                int tra_number = resultSet.getInt("tra_number");
                                String bill_num = resultSet.getString("bill_num");
                                String tra_date = resultSet.getString("tra_date");
                                String tra_time = resultSet.getString("tra_time");
                                String cashier_id = resultSet.getString("cashier_id");
                                String cashier_name = resultSet.getString("cashier_name");
                                int tra_total_qty = resultSet.getInt("tra_total_qty");
                                String cus_id = resultSet.getString("cus_id");
                                String cus_name = resultSet.getString("cus_name");
                                double sub_total = resultSet.getDouble("sub_total");
                                String pay_method = resultSet.getString("pay_method");
                                double paid_amount = resultSet.getDouble("paid_amount");
                                double balance = resultSet.getDouble("balance");
                                tableModel.addRow(new Object[]{tra_number, bill_num, tra_date, tra_time, cashier_id, cashier_name, tra_total_qty, cus_id, cus_name, sub_total, pay_method, paid_amount, balance});
                            }
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}