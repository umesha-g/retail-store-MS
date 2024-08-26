import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.text.*;
import java.sql.*;
import java.io.Serializable;

public class LoginWindow extends JFrame implements Serializable {
    private static final long serialVersionUID = 1;

//================================== OBJECTS ===================================

    JPanel loginPnl = new JPanel();
    JButton loginBtn = new JButton();
    JButton closeBtn = new JButton();
    JPasswordField passwTxt = new JPasswordField();
    JLabel userImgLbl = new JLabel();
    JLabel passImgLbl = new JLabel();
    JComboBox<String> userCmBx = new JComboBox<>();
    JLabel loginLbl = new JLabel();
    JPanel imagePnl = new JPanel();
    JLabel sideImgLbl = new JLabel();
    String job = new String();
    String[] result = new String[2];
    boolean passwTxtHasRun = false;

    String sqlUrl = "jdbc:mysql://localhost:3306/retail_store_database";
    String sqlUsername = "maths";
    String sqlPassword = "maths";

//============================= CONSTRUCTOR ====================================
    public LoginWindow() {
    components();
    setTitle("Login Window");
    }

    private LoginListener loginListener; // create login listener object 

//=================== GUI COMPONENTS POSITIONING ===============================
    private void components() {
        String[] result;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 470));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1000, 470));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
        setLocationRelativeTo(null);
        
        imagePnl.setLayout(new java.awt.CardLayout());

        sideImgLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.jpg")));
        sideImgLbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sideImgLbl.setMinimumSize(new java.awt.Dimension(610, 470));
        sideImgLbl.setPreferredSize(new java.awt.Dimension(610, 470));
        imagePnl.add(sideImgLbl, "card2");

        getContentPane().add(imagePnl);

        loginPnl.setBackground(new java.awt.Color(255, 255, 255));
        loginPnl.setMinimumSize(new java.awt.Dimension(390, 470));
        loginPnl.setPreferredSize(new java.awt.Dimension(390, 470));
        loginPnl.setLayout(null);

        loginLbl.setFont(new java.awt.Font("Baekmuk_Headline", 1, 20)); 
        loginLbl.setForeground(new java.awt.Color(0, 102, 102));
        loginLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loginLbl.setText("Sign In");
        loginLbl.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        loginLbl.setBounds(90, 100, 180, 30);
        loginPnl.add(loginLbl);

        userImgLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); 
        userImgLbl.setBounds(90, 170, 20, 15); 
        loginPnl.add(userImgLbl);

        passImgLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pass.png"))); 
        passImgLbl.setBounds(90, 215, 20, 15);
        loginPnl.add(passImgLbl);

        loginBtn.setBackground(new java.awt.Color(255, 255, 255));
        loginBtn.setForeground(new java.awt.Color(0, 102, 102));
        loginBtn.setText("Continue");
        loginBtn.setMaximumSize(new java.awt.Dimension(55, 27));
        loginBtn.setMinimumSize(new java.awt.Dimension(55, 27));
        loginBtn.setPreferredSize(new java.awt.Dimension(65, 20));
        loginBtn.setBounds(90, 270, 100, 30);
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBtnMouseClicked();
            }
        });
        loginPnl.add(loginBtn);

        userCmBx.setBackground(loginPnl.getBackground());
        userCmBx.setForeground(new java.awt.Color(0, 102, 102));
        userCmBx.setMinimumSize(new java.awt.Dimension(100, 27));
        userCmBx.setOpaque(true);
        userCmBx.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0,102,102)));
        userCmBx.setPreferredSize(new java.awt.Dimension(100, 27));
        userCmBx.setBounds(120, 165, 180, 30);
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
                String query = "SELECT emp_id FROM employees";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    try(ResultSet resultSet = statement.executeQuery(query)){
                        int i = 0;
                        while (resultSet.next()) {
                            String id = resultSet.getString("emp_id");
                            userCmBx.addItem(id);
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
        loginPnl.add(userCmBx);

        passwTxt.setBackground(loginPnl.getBackground());
        passwTxt.setForeground(new java.awt.Color(0,102,102));
        passwTxt.setText("Password");
        passwTxt.setBounds(120, 205, 180, 30);
        passwTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0,102,102)));
        passwTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwTxtMouseClicked(evt);
            }
        });
        passwTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {}
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {// continue on enter
                       loginBtnMouseClicked();
                }
            }
            @Override
            public void keyReleased(KeyEvent evt) {}
        });
        loginPnl.add(passwTxt);

        closeBtn.setForeground(new java.awt.Color(255, 255, 255));
        closeBtn.setBackground(new java.awt.Color(0,102,102));
        closeBtn.setText("Close");
        closeBtn.setPreferredSize(new java.awt.Dimension(65, 20));
        closeBtn.setBounds(200, 270, 100, 30);
        closeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });
        loginPnl.add(closeBtn);

        getContentPane().add(loginPnl);
        pack();
    }    

//================================ METHODS ===================================== 
    //method for full system exit ----------------------------------------------
    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    // method for start login validation process--------------------------------
    private void loginBtnMouseClicked() {
        String username = (String) userCmBx.getSelectedItem();
        String password = new String(passwTxt.getPassword());
        String[] result;
        result = isValidLogin(username, password);
        if (result[0]=="true") {
            if (loginListener != null) {
                loginListener.onLoginSuccess(result[1],result[2],result[3]);
             }
        } 
        else {
            passwTxt.setEchoChar((char) 0);
            passwTxt.setText("Invalid login credentials");
            passwTxt.setForeground(new java.awt.Color(204,0, 0));
            passwTxtHasRun = false;
        }
    }

    // method for empty password field for first time --------------------------
    private void passwTxtMouseClicked(java.awt.event.MouseEvent evt) {
        if (!passwTxtHasRun) {
            passwTxt.setText("");
            passwTxtHasRun = true;
        } 
        passwTxt.setEchoChar('\u2022');
        passwTxt.setForeground(new java.awt.Color(0,102,102));
    }

    // method for check valid credentials --------------------------------------
    private String[] isValidLogin(String userID, String password) {
        String pass = "" ;
        String job ="";
        String name ="";
        String id ="";
        String[] results = new String[4];
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
                String query = "SELECT emp_id, emp_name, emp_job, emp_passw FROM employees WHERE emp_id LIKE ?";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setString(1, "%" + userID + "%");
                    try(ResultSet resultSet = statement.executeQuery()){
                        while (resultSet.next()) {
                            id =  resultSet.getString("emp_id");
                            name = resultSet.getString("emp_name");
                            job = resultSet.getString("emp_job");
                            pass = resultSet.getString("emp_passw");
                        }
                    }
                }
            }
            connection.close();     
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        results[1]=id;
        results[2]=job;
        results[3]=name;

        if (pass.equals(password)) {
            results[0] ="true";
        }
        else{
            results[0] ="false";  
        }
        return results;
    }

    // new loginlistener recieving method --------------------------------------
    public void addLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }
}