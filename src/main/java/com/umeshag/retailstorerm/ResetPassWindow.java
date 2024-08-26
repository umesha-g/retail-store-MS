package com.umeshag.retailstorerm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ResetPassWindow extends JFrame implements Serializable {
    private static final long serialVersionUID = 9;

//================================== OBJECTS ===================================
    JLabel stockResetPopupLbl = new JLabel();
    JPanel stockResetPopupPnl = new JPanel();
    JButton stockResetSubmitBtn = new JButton();
    JPasswordField stockResetPopupTxt = new JPasswordField();
    int stockReset = 0;

    String sqlUrl = "jdbc:mysql://localhost:3306/retail_store_database";
    String sqlUsername = "user";
    String sqlPassword = "pass";

//============================= CONSTRUCTOR ====================================
    public ResetPassWindow(AdminWindow admin,String id){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 130);
        setUndecorated(true);
        setLocationRelativeTo(null);

        stockResetPopupPnl.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout stockResetPopupPnlLayout = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 10);
        stockResetPopupPnlLayout.setAlignOnBaseline(true);
        stockResetPopupPnl.setLayout(stockResetPopupPnlLayout);
        
        stockResetPopupLbl.setText("Enter Password");
        stockResetPopupLbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stockResetPopupPnl.add(stockResetPopupLbl);
        
        stockResetPopupTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 2));
        stockResetPopupTxt.setPreferredSize(new java.awt.Dimension(160, 30));
        stockResetPopupTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {}
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {// continue on enter
                    reset(id);
                    admin.stockIntResetHasRun = false;
                    dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent evt) {}
        });
        stockResetPopupPnl.add(stockResetPopupTxt);
        
        stockResetSubmitBtn.setBackground(new java.awt.Color(0, 102, 102));
        stockResetSubmitBtn.setForeground(new java.awt.Color(255, 255, 255));
        stockResetSubmitBtn.setText("Confirm");
        stockResetSubmitBtn.setPreferredSize(new java.awt.Dimension(100, 27));
        stockResetSubmitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    reset(id);
                    admin.stockIntResetHasRun = false;
                    dispose();
                }
        });

        stockResetPopupPnl.add(stockResetSubmitBtn);
        getContentPane().add(stockResetPopupPnl);
    }

//================================ METHODS =====================================  
    // method for reset stocks -------------------------------------------------
    private void reset(String id) {
        String pass = "" ;
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                String query = "SELECT emp_passw FROM employees WHERE emp_id LIKE ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, id );
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            pass = resultSet.getString("emp_passw");
                        }
                    }   
                }
            }
            connection.close();   
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (pass.equals(stockResetPopupTxt.getText())) {
            int result = JOptionPane.showConfirmDialog(this, "Do You Want To Proceed ?","Confirmation",JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                System.out.println("Done");
                /*try{
                    Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                        String query = "INSERT INTO initial_stocks SELECT * FROM  stocks";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.executeUpdate();
                        }
                    }
                    connection.close(); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }*/
                JOptionPane.showMessageDialog(this,"Initial Stocks Reseted"); 
            }
            else{
                dispose();
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Access Denied","Wrong Password",JOptionPane.ERROR_MESSAGE);
        }
    }
}