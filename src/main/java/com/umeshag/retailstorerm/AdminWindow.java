package com.umeshag.retailstorerm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class AdminWindow extends javax.swing.JFrame implements Serializable {
    private static final long serialVersionUID = 2;

//================================== OBJECTS ===================================

    private DefaultTableModel cusTableModel;
    private DefaultTableModel empTableModel;
    private DefaultTableModel stockTableModel;
    private DefaultTableModel traTableModel;

    JTabbedPane tabedPnls = new JTabbedPane();
    JPanel stockPnl = new JPanel();
    JPanel stockCtrlPnl = new JPanel();
    JPanel stockAddPnl = new JPanel();
    JLabel stockAddLbl = new JLabel();
    JLabel stockIdLbl = new JLabel();
    JTextField stockIdTxt = new JTextField();
    JLabel stockNameLbl = new JLabel();
    JTextField stockNameTxt = new JTextField();
    JLabel stockQntityLbl = new JLabel();
    JTextField stockQntityTxt = new JTextField();
    JLabel stockPriceLbl = new JLabel();
    JTextField stockPriceTxt = new JTextField();
    JButton stockAddBtn = new JButton();
    JPanel stockRemovePnl = new JPanel();
    JLabel stockRemoveLbl = new JLabel();
    JLabel stockSearchLbl = new JLabel();
    JTextField stockSearchTxt = new JTextField();
    JButton stockRemoveBtn = new JButton();
    JButton stockResetBtn = new JButton();
    JPanel stockReportPnl = new JPanel();
    JButton stockReportBtn = new JButton();
    JButton stockIntResetBtn = new JButton();
    JPanel stockTblPnl = new JPanel();
    JPanel stockBorderPnl = new JPanel();
    JScrollPane stockScrollPane = new JScrollPane();
    JTable stockDetailsTable = new JTable();
    JPanel cusPnl = new JPanel();
    JPanel cusControlPnl = new JPanel();
    JPanel cusAddPnl = new JPanel();
    JLabel cusAddLbl = new JLabel();
    JLabel cusIdLbl = new JLabel();
    JTextField cusIdTxt = new JTextField();
    JLabel cusNameLbl = new JLabel();
    JTextField cusNameTxt = new JTextField();
    JLabel cusPhoneLbl = new JLabel();
    JTextField cusPhoneTxt = new JTextField();
    JLabel cusLoyaltylbl = new JLabel();
    JTextField cusLoyaltyTxt = new JTextField();
    JButton cusAddBtn = new JButton();
    JPanel cusRemovePnl = new JPanel();
    JLabel cusRemoveLbl = new JLabel();
    JLabel cusSearchLbl = new JLabel();
    JTextField cusSearchTxt = new JTextField();
    JButton cusRemoveBtn = new JButton();
    JButton cusResetBtn = new JButton();
    JPanel cusReportPnl = new JPanel();
    JButton cusReportBtn = new JButton();
    JPanel cusTblPnl = new JPanel();
    JPanel cusBorderPnl = new JPanel();
    JScrollPane cusScrollPane = new JScrollPane();
    JTable cusDetailsTable = new JTable();
    JPanel empPnl = new JPanel();
    JPanel empControlPnl = new JPanel();
    JPanel empAddPnl = new JPanel();
    JLabel empAddLbl = new JLabel();
    JLabel empIdLbl = new JLabel();
    JTextField empIdTxt = new JTextField();
    JLabel empNameLbl = new JLabel();
    JTextField empNameTxt = new JTextField();
    JLabel empJobLbl = new JLabel();
    JTextField empJobTxt = new JTextField();
    JLabel empPwlbl = new JLabel();
    JTextField empPwTxt = new JTextField();
    JButton empAddBtn = new JButton();
    JPanel empRemovePnl = new JPanel();
    JLabel empRemoveLbl = new JLabel();
    JLabel empSearchLbl = new JLabel();
    JTextField empSearchTxt = new JTextField();
    JButton empRemoveBtn = new JButton();
    JButton empResetBtn = new JButton();
    JPanel empReportPnl = new JPanel();
    JButton empReportBtn = new JButton();
    JPanel empTblPnl = new JPanel();
    JPanel empBorderPnl = new JPanel();
    JScrollPane empScrollPane = new JScrollPane();
    JTable empDetailsTable = new JTable();
    JButton logOutBtn = new JButton();
    JScrollPane traScrollPane = new JScrollPane();
    JPanel traBorderPnl = new JPanel();
    JPanel traPnl = new JPanel();
    JTable traDetailsTable = new JTable();
    JLabel traSearchLbl = new JLabel();
    JTextField traSearchTxt = new JTextField();
    
    RefreshTable refreshTable = new RefreshTable();
    
    String sqlUrl = "jdbc:mysql://localhost:3306/retail_store_database";
    String sqlUsername = "maths";
    String sqlPassword = "maths";
    boolean stockIntResetHasRun = false;

//============================= CONSTRUCTOR ====================================
    public AdminWindow(String id,String username) {
        components(id);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Administrator Window");
    }

//=================== GUI COMPONENTS POSITIONING ===============================
    private void components(String id) {
        //table model for transaction table
        traTableModel = new DefaultTableModel(new Object[]{"tra_number", "bill_num", "tra_date", "tra_time", "cashier_id", "cashier_name", "tra_total_qty", "cus_id", "cus_name", "sub_total", "pay_method", "paid_amount", "balance"},0 ){
            public boolean isCellEditable(int row, int column) {
                // Make all rows non-editable
                return false;
            }
        };
        //table model for customer table
        cusTableModel = new DefaultTableModel(new Object[]{"ID","Name","Phone","Loyalty"},0 ){
            public boolean isCellEditable(int row, int column) {
                // Make all rows non-editable
                return false;
            }
        };
        //table model for employee table
        empTableModel = new DefaultTableModel(new Object[]{"ID","Name","Job Role","Password"},0 ){
            public boolean isCellEditable(int row, int column) {
                // Make all rows non-editable
                return false;
            }
        };
        //table model fro stocks table
        stockTableModel = new DefaultTableModel(new Object[]{"Code","Product Name","Quantity","Unit Price"},0 ){
            public boolean isCellEditable(int row, int column) {
                // Make all rows non-editable
                return false;
            }
        };

        setUndecorated(true); // remove window order
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 51));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        // tabed panels properties
        tabedPnls.setForeground(new java.awt.Color(51, 51, 51));
        tabedPnls.setToolTipText("");
        tabedPnls.setFont(new java.awt.Font("Noto Sans", 1, 15)); 

        //stock main panel properties
        stockPnl.setLayout(new javax.swing.BoxLayout(stockPnl, javax.swing.BoxLayout.LINE_AXIS));

        //stock control panel properties
        stockCtrlPnl.setBackground(new java.awt.Color(0, 102, 102));
        stockCtrlPnl.setMaximumSize(new java.awt.Dimension(400, 800));
        stockCtrlPnl.setPreferredSize(new java.awt.Dimension(400, 520));
        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 30);
        flowLayout3.setAlignOnBaseline(true);
        stockCtrlPnl.setLayout(flowLayout3);

        // stock adding panel properties
        stockAddPnl.setBackground(new java.awt.Color(0, 51, 51));
        stockAddPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        stockAddPnl.setPreferredSize(new java.awt.Dimension(380, 300));
        stockAddPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 15));

        // stock add main lable properties
        stockAddLbl.setFont(new java.awt.Font("Noto Sans", 1, 16)); 
        stockAddLbl.setForeground(new java.awt.Color(255, 255, 255));
        stockAddLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stockAddLbl.setText("Add New or Update");
        stockAddLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        stockAddPnl.add(stockAddLbl);

        //stock Id lable properties
        stockIdLbl.setForeground(new java.awt.Color(255, 255, 255));
        stockIdLbl.setText("Product Code");
        stockIdLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        stockAddPnl.add(stockIdLbl);

        // stock Id text properties
        stockIdTxt.setBackground(new java.awt.Color(0, 51, 51));
        stockIdTxt.setForeground(new java.awt.Color(255, 255, 255));
        stockIdTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        stockIdTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        PlainDocument stockDoc = new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if ((getLength() + str.length()) <= 10) { 
                    super.insertString(offset, str, attr);
                } else {}
            }
        };
        stockIdTxt.setDocument(stockDoc);
        stockIdTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {}
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    stockNameTxt.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent evt) {}
        });
        stockAddPnl.add(stockIdTxt);

        // stock name label properties
        stockNameLbl.setForeground(new java.awt.Color(255, 255, 255));
        stockNameLbl.setText("Product Name");
        stockNameLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        stockAddPnl.add(stockNameLbl);

        // stock name text properties
        stockNameTxt.setBackground(new java.awt.Color(0, 51, 51));
        stockNameTxt.setForeground(new java.awt.Color(255, 255, 255));
        stockNameTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        stockNameTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        stockNameTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {}
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    stockQntityTxt.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent evt) {}
        });
        stockAddPnl.add(stockNameTxt);

        // stock quantity label properties
        stockQntityLbl.setForeground(new java.awt.Color(255, 255, 255));
        stockQntityLbl.setText("Quantity");
        stockQntityLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        stockAddPnl.add(stockQntityLbl);

        // stock quantity text properties
        stockQntityTxt.setBackground(new java.awt.Color(0, 51, 51));
        stockQntityTxt.setForeground(new java.awt.Color(255, 255, 255));
        stockQntityTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        stockQntityTxt.setMinimumSize(new java.awt.Dimension(164, 27));
        stockQntityTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        stockQntityTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {}
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    stockPriceTxt.requestFocus();
                }
            }
            @Override
            public void keyReleased(KeyEvent evt) {}
        });
        stockAddPnl.add(stockQntityTxt);

        // stock price label properties
        stockPriceLbl.setForeground(new java.awt.Color(255, 255, 255));
        stockPriceLbl.setText("Unit Price");
        stockPriceLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        stockAddPnl.add(stockPriceLbl);

        // stock price text properties
        stockPriceTxt.setBackground(new java.awt.Color(0, 51, 51));
        stockPriceTxt.setForeground(new java.awt.Color(255, 255, 255));
        stockPriceTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        stockPriceTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        stockPriceTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {}
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    stockAddBtnMouseClicked();
                }
            }
            @Override
            public void keyReleased(KeyEvent evt) {}
        });
        stockAddPnl.add(stockPriceTxt);

        // stock add/update button properties
        stockAddBtn.setBackground(new java.awt.Color(0, 102, 102));
        stockAddBtn.setForeground(new java.awt.Color(255, 255, 255));
        stockAddBtn.setText("Add / Update");
        stockAddBtn.setPreferredSize(new java.awt.Dimension(200, 34));
        stockAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockAddBtnMouseClicked();
            }
        });
        stockAddPnl.add(stockAddBtn);

        stockCtrlPnl.add(stockAddPnl);

        // stock remove panel properties
        stockRemovePnl.setBackground(new java.awt.Color(0, 51, 51));
        stockRemovePnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        stockRemovePnl.setPreferredSize(new java.awt.Dimension(380, 180));
        stockRemovePnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 20));

        //stock remove main lable pproperties
        stockRemoveLbl.setFont(new java.awt.Font("Noto Sans", 1, 16)); 
        stockRemoveLbl.setForeground(new java.awt.Color(255, 255, 255));
        stockRemoveLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stockRemoveLbl.setText("Search and Remove");
        stockRemoveLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        stockRemovePnl.add(stockRemoveLbl);

        // stock search lable properties
        stockSearchLbl.setForeground(new java.awt.Color(255, 255, 255));
        stockSearchLbl.setText("Stock Search");
        stockSearchLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        stockRemovePnl.add(stockSearchLbl);

        // stock search text properties
        stockSearchTxt.setBackground(new java.awt.Color(0, 51, 51));
        stockSearchTxt.setForeground(new java.awt.Color(255, 255, 255));
        stockSearchTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        stockSearchTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        stockSearchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stockSearchTxtKeyPressed(evt,stockTableModel);
            }
        });
        stockRemovePnl.add(stockSearchTxt);

        // stock remove button properties
        stockRemoveBtn.setBackground(new java.awt.Color(0, 102, 102));
        stockRemoveBtn.setForeground(new java.awt.Color(255, 255, 255));
        stockRemoveBtn.setText("Remove");
        stockRemoveBtn.setToolTipText("");
        stockRemoveBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        stockRemoveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockRemoveBtnMouseClicked(evt,stockDetailsTable);
            }
        });
        stockRemovePnl.add(stockRemoveBtn);

        // stocks text and table reset button
        stockResetBtn.setBackground(new java.awt.Color(0, 102, 102));
        stockResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        stockResetBtn.setText("Reset");
        stockResetBtn.setToolTipText("");
        stockResetBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        stockResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockResetBtnMouseClicked(evt);
            }
        });
        stockRemovePnl.add(stockResetBtn);

        stockCtrlPnl.add(stockRemovePnl);

        // stock report panel properties
        stockReportPnl.setBackground(new java.awt.Color(0, 51, 51));
        stockReportPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        stockReportPnl.setPreferredSize(new java.awt.Dimension(380, 80));
        stockReportPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 20));

        // stock report button properties
        stockReportBtn.setBackground(new java.awt.Color(0, 102, 102));
        stockReportBtn.setForeground(new java.awt.Color(255, 255, 255));
        stockReportBtn.setText("Stock Report");
        stockReportBtn.setToolTipText("");
        stockReportBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        stockReportBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockReportBtnMouseClicked(evt);
            }
        });
        stockReportPnl.add(stockReportBtn);

        // initial stock resetting button properties
        stockIntResetBtn.setForeground(new java.awt.Color(0, 51, 51));
        stockIntResetBtn.setText("Stock Reset");
        stockIntResetBtn.setToolTipText("");
        stockIntResetBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        stockIntResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockIntResetBtnMouseClicked(evt,id);
            }
        });
        stockReportPnl.add(stockIntResetBtn);

        stockCtrlPnl.add(stockReportPnl);

        stockPnl.add(stockCtrlPnl);

        // stock details table container panel properties
        stockTblPnl.setBackground(new java.awt.Color(0, 102, 102));
        stockTblPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 30));

        // stock table border panel properties
        stockBorderPnl.setBackground(new java.awt.Color(0, 51, 51));
        stockBorderPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        stockBorderPnl.setPreferredSize(new java.awt.Dimension(930, 620));
        stockBorderPnl.setLayout(new java.awt.CardLayout(10, 10));

        stockScrollPane.setPreferredSize(new java.awt.Dimension(702, 650));

        // stock details table properties
        stockDetailsTable.setModel(stockTableModel);
        stockDetailsTable.setGridColor(new java.awt.Color(220,220,220));
        stockDetailsTable.setShowGrid(true);
        stockDetailsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                stockDetailsTableMouseDoubleClicked(evt,stockDetailsTable);
                }
            }
        });
        stockScrollPane.setViewportView(stockDetailsTable);

        stockBorderPnl.add(stockScrollPane, "card2");

        stockTblPnl.add(stockBorderPnl);

        stockPnl.add(stockTblPnl);

        tabedPnls.addTab("STOCKS", stockPnl); //adding stock main panel to tabs

        cusPnl.setLayout(new javax.swing.BoxLayout(cusPnl, javax.swing.BoxLayout.LINE_AXIS));

        //left customer control panel properties
        cusControlPnl.setBackground(new java.awt.Color(0, 102, 102));
        cusControlPnl.setMaximumSize(new java.awt.Dimension(400, 800));
        cusControlPnl.setPreferredSize(new java.awt.Dimension(400, 520));
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 30);
        flowLayout2.setAlignOnBaseline(true);
        cusControlPnl.setLayout(flowLayout2);

        //customer adding panel properties
        cusAddPnl.setBackground(new java.awt.Color(0, 51, 51));
        cusAddPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cusAddPnl.setPreferredSize(new java.awt.Dimension(380, 300));
        cusAddPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 15));

        //customer add main label properties
        cusAddLbl.setFont(new java.awt.Font("Noto Sans", 1, 16)); 
        cusAddLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusAddLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cusAddLbl.setText("Add New or Update");
        cusAddLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        cusAddPnl.add(cusAddLbl);

        // customer ID label properties
        cusIdLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusIdLbl.setText("Customer ID");
        cusIdLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        cusAddPnl.add(cusIdLbl);

        // customer ID text properties
        cusIdTxt.setBackground(new java.awt.Color(0, 51, 51));
        cusIdTxt.setForeground(new java.awt.Color(255, 255, 255));
        cusIdTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        cusIdTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        PlainDocument cusDoc = new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if ((getLength() + str.length()) <= 10) { 
                    super.insertString(offset, str, attr);
                } else {}
            }
        };
        cusIdTxt.setDocument(cusDoc);
        cusAddPnl.add(cusIdTxt);

        // customer name label properties
        cusNameLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusNameLbl.setText("Customer Name");
        cusNameLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        cusAddPnl.add(cusNameLbl);

        // customer name text properties
        cusNameTxt.setBackground(new java.awt.Color(0, 51, 51));
        cusNameTxt.setForeground(new java.awt.Color(255, 255, 255));
        cusNameTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        cusNameTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        cusAddPnl.add(cusNameTxt);

        // customer phone label properties
        cusPhoneLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusPhoneLbl.setText("Phone Number");
        cusPhoneLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        cusAddPnl.add(cusPhoneLbl);

        // customer phone text properties
        cusPhoneTxt.setBackground(new java.awt.Color(0, 51, 51));
        cusPhoneTxt.setForeground(new java.awt.Color(255, 255, 255));
        cusPhoneTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        cusPhoneTxt.setMinimumSize(new java.awt.Dimension(164, 27));
        cusPhoneTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        cusAddPnl.add(cusPhoneTxt);

        // customer loyalty label properties
        cusLoyaltylbl.setForeground(new java.awt.Color(255, 255, 255));
        cusLoyaltylbl.setText("Loyalty Points");
        cusLoyaltylbl.setPreferredSize(new java.awt.Dimension(120, 21));
        cusAddPnl.add(cusLoyaltylbl);

        // customer loyalty text properties
        cusLoyaltyTxt.setBackground(new java.awt.Color(0, 51, 51));
        cusLoyaltyTxt.setForeground(new java.awt.Color(255, 255, 255));
        cusLoyaltyTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        cusLoyaltyTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        cusAddPnl.add(cusLoyaltyTxt);

        // customer add/update button properties
        cusAddBtn.setBackground(new java.awt.Color(0, 102, 102));
        cusAddBtn.setForeground(new java.awt.Color(255, 255, 255));
        cusAddBtn.setText("Add / Update");
        cusAddBtn.setPreferredSize(new java.awt.Dimension(200, 34));
        cusAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cusAddBtnMouseClicked(evt);
            }
        });
        cusAddPnl.add(cusAddBtn);

        cusControlPnl.add(cusAddPnl);

        //customer remove panel properties
        cusRemovePnl.setBackground(new java.awt.Color(0, 51, 51));
        cusRemovePnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cusRemovePnl.setPreferredSize(new java.awt.Dimension(380, 180));
        cusRemovePnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 20));

        //customer remove main label properties
        cusRemoveLbl.setFont(new java.awt.Font("Noto Sans", 1, 16)); 
        cusRemoveLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusRemoveLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cusRemoveLbl.setText("Search and Remove");
        cusRemoveLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        cusRemovePnl.add(cusRemoveLbl);

        //customer search label properties
        cusSearchLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusSearchLbl.setText("Customer Search");
        cusSearchLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        cusRemovePnl.add(cusSearchLbl);

        // customer search text properties
        cusSearchTxt.setBackground(new java.awt.Color(0, 51, 51));
        cusSearchTxt.setForeground(new java.awt.Color(255, 255, 255));
        cusSearchTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        cusSearchTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        cusSearchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cusSearchTxtKeyPressed(evt,cusTableModel);
            }
        });
        cusRemovePnl.add(cusSearchTxt);

        // customer remove button properties
        cusRemoveBtn.setBackground(new java.awt.Color(0, 102, 102));
        cusRemoveBtn.setForeground(new java.awt.Color(255, 255, 255));
        cusRemoveBtn.setText("Remove");
        cusRemoveBtn.setToolTipText("");
        cusRemoveBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        cusRemoveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cusRemoveBtnMouseClicked(evt,cusDetailsTable);
            }
        });
        cusRemovePnl.add(cusRemoveBtn);

        // customer text and table reset button properties
        cusResetBtn.setBackground(new java.awt.Color(0, 102, 102));
        cusResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        cusResetBtn.setText("Reset");
        cusResetBtn.setToolTipText("");
        cusResetBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        cusResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cusResetBtnMouseClicked(evt);
            }
        });
        cusRemovePnl.add(cusResetBtn);

        cusControlPnl.add(cusRemovePnl);

        // customer report panel properties
        cusReportPnl.setBackground(new java.awt.Color(0, 51, 51));
        cusReportPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cusReportPnl.setPreferredSize(new java.awt.Dimension(380, 80));
        cusReportPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 20));

        // customer report button properties
        cusReportBtn.setBackground(new java.awt.Color(0, 102, 102));
        cusReportBtn.setForeground(new java.awt.Color(255, 255, 255));
        cusReportBtn.setText("Customer Report");
        cusReportBtn.setToolTipText("");
        cusReportBtn.setPreferredSize(new java.awt.Dimension(200, 34));
        cusReportBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cusReportBtnMouseClicked(evt);
            }
        });
        cusReportPnl.add(cusReportBtn);

        cusControlPnl.add(cusReportPnl);

        cusPnl.add(cusControlPnl);

        // customer details table container panel properties
        cusTblPnl.setBackground(new java.awt.Color(0, 102, 102));
        cusTblPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 30));

        // table border panel properties
        cusBorderPnl.setBackground(new java.awt.Color(0, 51, 51));
        cusBorderPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cusBorderPnl.setPreferredSize(new java.awt.Dimension(930, 620));
        cusBorderPnl.setLayout(new java.awt.CardLayout(10, 10));

        cusScrollPane.setPreferredSize(new java.awt.Dimension(702, 650));

        // customer details table properties
        cusDetailsTable.setModel(cusTableModel);
        cusDetailsTable.setGridColor(new java.awt.Color(220,220,220));
        cusDetailsTable.setShowGrid(true);
        cusDetailsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    cusDetailsTableMouseDoubleClicked(evt,cusDetailsTable);
                }
            }
        });
        cusScrollPane.setViewportView(cusDetailsTable);

        cusBorderPnl.add(cusScrollPane, "card2");

        cusTblPnl.add(cusBorderPnl);

        cusPnl.add(cusTblPnl);

        tabedPnls.addTab("CUSTOMERS", cusPnl);// add cus main panel to tabs

        empPnl.setLayout(new javax.swing.BoxLayout(empPnl, javax.swing.BoxLayout.LINE_AXIS));

        // employee control left panel properties
        empControlPnl.setBackground(new java.awt.Color(0, 102, 102));
        empControlPnl.setMaximumSize(new java.awt.Dimension(400, 800));
        empControlPnl.setPreferredSize(new java.awt.Dimension(400, 520));
        java.awt.FlowLayout flowLayout4 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 30);
        flowLayout4.setAlignOnBaseline(true);
        empControlPnl.setLayout(flowLayout4);

        // emp add panel properties
        empAddPnl.setBackground(new java.awt.Color(0, 51, 51));
        empAddPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        empAddPnl.setPreferredSize(new java.awt.Dimension(380, 300));
        empAddPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 15));

        // emp add main label properties
        empAddLbl.setFont(new java.awt.Font("Noto Sans", 1, 16)); 
        empAddLbl.setForeground(new java.awt.Color(255, 255, 255));
        empAddLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empAddLbl.setText("Add New or Update");
        empAddLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        empAddPnl.add(empAddLbl);

        // emp ID label properties
        empIdLbl.setForeground(new java.awt.Color(255, 255, 255));
        empIdLbl.setText("Employee ID");
        empIdLbl.setToolTipText("");
        empIdLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        empAddPnl.add(empIdLbl);

        //emp ID text properties
        empIdTxt.setBackground(new java.awt.Color(0, 51, 51));
        empIdTxt.setForeground(new java.awt.Color(255, 255, 255));
        empIdTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        empIdTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        empIdTxt.setOpaque(true);
        PlainDocument empDoc = new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null)
                    return;
                if ((getLength() + str.length()) <= 10) { 
                    super.insertString(offset, str, attr);
                } else {}
            }
        };
        empIdTxt.setDocument(empDoc);
        empAddPnl.add(empIdTxt);

        // emp name label properties
        empNameLbl.setForeground(new java.awt.Color(255, 255, 255));
        empNameLbl.setText("Employee Name");
        empNameLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        empAddPnl.add(empNameLbl);

        // emp name text properties
        empNameTxt.setBackground(new java.awt.Color(0, 51, 51));
        empNameTxt.setForeground(new java.awt.Color(255, 255, 255));
        empNameTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        empNameTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        empAddPnl.add(empNameTxt);

        // emp job label properties
        empJobLbl.setForeground(new java.awt.Color(255, 255, 255));
        empJobLbl.setText("Emp. Position");
        empJobLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        empAddPnl.add(empJobLbl);

        //emp job text properties
        empJobTxt.setBackground(new java.awt.Color(0, 51, 51));
        empJobTxt.setForeground(new java.awt.Color(255, 255, 255));
        empJobTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        empJobTxt.setMinimumSize(new java.awt.Dimension(164, 27));
        empJobTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        empAddPnl.add(empJobTxt);

        // emp password label
        empPwlbl.setForeground(new java.awt.Color(255, 255, 255));
        empPwlbl.setText("Emp. Password");
        empPwlbl.setToolTipText("");
        empPwlbl.setPreferredSize(new java.awt.Dimension(120, 21));
        empAddPnl.add(empPwlbl);

        // emp password text properties
        empPwTxt.setBackground(new java.awt.Color(0, 51, 51));
        empPwTxt.setForeground(new java.awt.Color(255, 255, 255));
        empPwTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        empPwTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        empAddPnl.add(empPwTxt);

        // emp add/update button properties
        empAddBtn.setBackground(new java.awt.Color(0, 102, 102));
        empAddBtn.setForeground(new java.awt.Color(255, 255, 255));
        empAddBtn.setText("Add / Update");
        empAddBtn.setPreferredSize(new java.awt.Dimension(200, 34));
        empAddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empAddBtnMouseClicked(evt );
            }
        });
        empAddPnl.add(empAddBtn);

        empControlPnl.add(empAddPnl);

        // emp remove panel properties
        empRemovePnl.setBackground(new java.awt.Color(0, 51, 51));
        empRemovePnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        empRemovePnl.setPreferredSize(new java.awt.Dimension(380, 180));
        empRemovePnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 20));

        // emp remove label properties
        empRemoveLbl.setFont(new java.awt.Font("Noto Sans", 1, 16)); 
        empRemoveLbl.setForeground(new java.awt.Color(255, 255, 255));
        empRemoveLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empRemoveLbl.setText("Search and Remove");
        empRemoveLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        empRemovePnl.add(empRemoveLbl);

        // emp search label properties
        empSearchLbl.setForeground(new java.awt.Color(255, 255, 255));
        empSearchLbl.setText("Emp. Search");
        empSearchLbl.setPreferredSize(new java.awt.Dimension(120, 21));
        empRemovePnl.add(empSearchLbl);

        // emp search text properties
        empSearchTxt.setBackground(new java.awt.Color(0, 51, 51));
        empSearchTxt.setForeground(new java.awt.Color(255, 255, 255));
        empSearchTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        empSearchTxt.setPreferredSize(new java.awt.Dimension(200, 30));
        empSearchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            empSearchTxtKeyPressed(evt,empTableModel);
        }
        });
        empRemovePnl.add(empSearchTxt);

        // emp remove button properties
        empRemoveBtn.setBackground(new java.awt.Color(0, 102, 102));
        empRemoveBtn.setForeground(new java.awt.Color(255, 255, 255));
        empRemoveBtn.setText("Remove");
        empRemoveBtn.setToolTipText("");
        empRemoveBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        empRemoveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empRemoveBtnMouseClicked(evt , empDetailsTable);
            }
        });
        empRemovePnl.add(empRemoveBtn);

        // employee text and table reset button properties
        empResetBtn.setBackground(new java.awt.Color(0, 102, 102));
        empResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        empResetBtn.setText("Reset");
        empResetBtn.setToolTipText("");
        empResetBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        empResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empResetBtnMouseClicked(evt);
            }
        });

        empRemovePnl.add(empResetBtn);

        empControlPnl.add(empRemovePnl);

        //emp report panel properties
        empReportPnl.setBackground(new java.awt.Color(0, 51, 51));
        empReportPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        empReportPnl.setPreferredSize(new java.awt.Dimension(380, 80));
        empReportPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 20));

        //emp report button properties
        empReportBtn.setBackground(new java.awt.Color(0, 102, 102));
        empReportBtn.setForeground(new java.awt.Color(255, 255, 255));
        empReportBtn.setText("Employee Report");
        empReportBtn.setToolTipText("");
        empReportBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        empReportBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empReportBtnMouseClicked(evt);
            }
        });
        empReportPnl.add(empReportBtn);

        //admin window logout button properties
        logOutBtn.setText("Log out");
        logOutBtn.setForeground(new java.awt.Color(0, 102, 102));
        logOutBtn.setBackground(new java.awt.Color(255, 255, 255));
        logOutBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        logOutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutBtnMouseClicked(evt);
            }
        });
        empReportPnl.add(logOutBtn);

        empControlPnl.add(empReportPnl);

        empPnl.add(empControlPnl);

        // employee details table container panel properties
        empTblPnl.setBackground(new java.awt.Color(0, 102, 102));
        empTblPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 30));

        // emp table border panel properties
        empBorderPnl.setBackground(new java.awt.Color(0, 51, 51));
        empBorderPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        empBorderPnl.setPreferredSize(new java.awt.Dimension(930, 620));
        empBorderPnl.setLayout(new java.awt.CardLayout(10, 10));

        empScrollPane.setPreferredSize(new java.awt.Dimension(702, 650));

        //employee details table properties
        empDetailsTable.setModel(empTableModel);
        empDetailsTable.setGridColor(new java.awt.Color(220,220,220));
        empDetailsTable.setShowGrid(true);
        empDetailsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                empDetailsTableMouseDoubleClicked(evt,empDetailsTable);
                }
            }
        });
        empScrollPane.setViewportView(empDetailsTable);

        empBorderPnl.add(empScrollPane, "card2");

        empTblPnl.add(empBorderPnl);

        empPnl.add(empTblPnl);

        tabedPnls.addTab("EMPLOYEES", empPnl); //add emp main panel to tabs

        // transaction main panel properties
        traPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 20));
        traPnl.setBackground(new java.awt.Color(0, 102, 102));
        traPnl.setPreferredSize(new java.awt.Dimension(1300, 800));

        // tra search label properties
        traSearchLbl.setForeground(new java.awt.Color(255, 255, 255));
        traSearchLbl.setText("Search");
        traSearchLbl.setPreferredSize(new java.awt.Dimension(60, 30));
        traPnl.add(traSearchLbl);

        // tra search text properties
        traSearchTxt.setBackground(new java.awt.Color(0, 102, 102));
        traSearchTxt.setForeground(new java.awt.Color(255, 255, 255));
        traSearchTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        traSearchTxt.setPreferredSize(new java.awt.Dimension(300, 30));
        traSearchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                traSearchTxtKeyPressed(evt,traTableModel);
            }
        });
        traPnl.add(traSearchTxt);

        // tra table border panel properties
        traBorderPnl.setBackground(new java.awt.Color(0, 51, 51));
        traBorderPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        traBorderPnl.setPreferredSize(new java.awt.Dimension(1300, 600));
        traBorderPnl.setMaximumSize(new java.awt.Dimension(1300, 600));
        traBorderPnl.setLayout(new java.awt.CardLayout(10, 10));

        // transaction details table properties
        traDetailsTable.setModel(traTableModel);
        traDetailsTable.setGridColor(new java.awt.Color(220,220,220));
        traDetailsTable.setShowGrid(true);
       
        traScrollPane.setViewportView(traDetailsTable);

        traBorderPnl.add(traScrollPane, "card2");

        traPnl.add(traBorderPnl);

        tabedPnls.addTab("TRANSACTIONS", traPnl); // add tra main panel to tabs

        getContentPane().add(tabedPnls);

        pack();

        // refresh all tables at start of the window
        refreshTable.refreshTable("stock",stockTableModel);
        refreshTable.refreshTable("employees",empTableModel);
        refreshTable.refreshTable("cust",cusTableModel);
        refreshTable.refreshTable("tra",traTableModel);
    }  

//================================ METHODS =====================================  
    // log out method ----------------------------------------------------------
    private void logOutBtnMouseClicked(java.awt.event.MouseEvent evt) { 
      main.openLogin(); 
      dispose();
    }

    //add/update stocks to database method--------------------------------------
    private void stockAddBtnMouseClicked() { 
        String id = stockIdTxt.getText();
        String name = stockNameTxt.getText();
        String qntity = stockQntityTxt.getText();
        String price = stockPriceTxt.getText();
        String category = "stock";
        int quant =1;
        String result = "";
        try {
            // Parse the text into an integer
            quant  = Integer.parseInt(qntity);
            } catch (NumberFormatException ex) {}

        if (id.isEmpty() || name.isEmpty() || qntity.isEmpty() || price.isEmpty()){}
        else{
            try {
                Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                    String query = "SELECT stock_id FROM stocks WHERE stock_id LIKE ? ";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, id);
                        try (ResultSet resultSet = statement.executeQuery()) {
                            while (resultSet.next()) {
                                result = resultSet.getString("stock_id");
                            }
                        }
                    }

                    if (result =="") {
                        query = "INSERT INTO stocks(stock_id, stock_name, stock_qntity,stock_price) VALUES (?, ?, ?,?)";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, id);
                            statement.setString(2, name);
                            statement.setInt(3, quant);
                            statement.setString(4, price);
                            statement.executeUpdate();
                        }
                    }
            
                    else{
                        query = "UPDATE stocks SET stock_name=?, stock_qntity =?, stock_price = ? WHERE stock_id = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, name);
                            statement.setInt(2, quant);
                            statement.setString(3, price);
                            statement.setString(4, id);
                            statement.executeUpdate();
                        }
                    }  
                } 
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            refreshTable.refreshTable(category,stockTableModel);
            stockIdTxt.setText("");
            stockNameTxt.setText("");
            stockQntityTxt.setText("");
            stockPriceTxt.setText("");
            stockIdTxt.requestFocus();
        }
    }

    // stock panel text and table reseting method ------------------------------
    private void stockResetBtnMouseClicked(java.awt.event.MouseEvent evt) { 
        refreshTable.refreshTable("stock",stockTableModel);
        stockSearchTxt.setText("");
        stockIdTxt.setText("");
        stockNameTxt.setText("");
        stockQntityTxt.setText("");
        stockPriceTxt.setText("");
    }

    // stock remove from database method ---------------------------------------
    private void stockRemoveBtnMouseClicked(java.awt.event.MouseEvent evt, JTable detailsTable) { 
        int selectedRow = detailsTable.getSelectedRow();
        String category = "stock";
        if (selectedRow >= 0 ) {
            String removeId =(String)detailsTable.getValueAt(selectedRow,0);
            try {
                Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                    String query = "DELETE FROM stocks WHERE stock_id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, removeId);
                        statement.executeUpdate();
                    }
                }
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            refreshTable.refreshTable(category,stockTableModel);
        }
    }

    //add/update customers to database method-----------------------------------
    private void cusAddBtnMouseClicked(java.awt.event.MouseEvent evt) { 
        String id = cusIdTxt.getText();
        String name = cusNameTxt.getText();
        String phone = cusPhoneTxt.getText();
        double loyalty = Double.parseDouble(cusLoyaltyTxt.getText());
        String category = "cust";
        String result = "";

        if (id.isEmpty() || name.isEmpty() || loyalty==0.0 || phone.isEmpty()){}
        else{
            try {
                Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                    String query = "SELECT cus_id FROM customers WHERE cus_id LIKE ? ";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, id);
                        try (ResultSet resultSet = statement.executeQuery()) {
                            while (resultSet.next()) {
                                result = resultSet.getString("cus_id");
                            }
                        }
                    }
                    if (result =="") {
                        query = "INSERT INTO customers (cus_id, cus_name, cus_phone, cus_loyal) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, id);
                            statement.setString(2, name);
                            statement.setString(3, phone);
                            statement.setDouble(4, loyalty);
                            statement.executeUpdate();
                        }
                    }
                    else{
                        query = "UPDATE customers SET cus_name=?, cus_phone =?, cus_loyal = ? WHERE cus_id = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, name);
                            statement.setString(2, phone);
                            statement.setDouble(3, loyalty);
                            statement.setString(4, id);
                            statement.executeUpdate();
                        }
                    }
                }
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            refreshTable.refreshTable(category,cusTableModel);
            cusIdTxt.setText("");
            cusNameTxt.setText("");
            cusPhoneTxt.setText("");
            cusLoyaltyTxt.setText("");
        }   
    }

    // reset customer panel all text and table method --------------------------
    private void cusResetBtnMouseClicked(java.awt.event.MouseEvent evt) { 
        refreshTable.refreshTable("cust",cusTableModel);
        cusSearchTxt.setText("");
        cusIdTxt.setText("");
        cusLoyaltyTxt.setText("");
        cusNameTxt.setText("");
        cusPhoneTxt.setText("");
    }

    // remove customers from database method -----------------------------------
    private void cusRemoveBtnMouseClicked(java.awt.event.MouseEvent evt,JTable detailsTable) { 
        int selectedRow = detailsTable.getSelectedRow();
        String category = "cust";
        if (selectedRow >= 0 ) {
            String removeId =(String)detailsTable.getValueAt(selectedRow,0);
            try {
                Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                    String query = "DELETE FROM customers WHERE cus_id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, removeId);
                        statement.executeUpdate();
                    }
                }
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            refreshTable.refreshTable(category,cusTableModel);
        }
    }

    //add/update employees to database method-----------------------------------
    private void empAddBtnMouseClicked(java.awt.event.MouseEvent evt) { 
        String id = empIdTxt.getText();
        String name = empNameTxt.getText();
        String job = empJobTxt.getText();
        String passw = empPwTxt.getText();
        String category = "employees";
        String result = "";

        if (id.isEmpty() || name.isEmpty() || job.isEmpty() || passw.isEmpty()){}
        else{
            try {
                Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                    String query = "SELECT emp_id FROM employees WHERE emp_id LIKE ? ";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, id);
                        try (ResultSet resultSet = statement.executeQuery()) {
                            while (resultSet.next()) {
                                result = resultSet.getString("emp_id");
                            }
                        }
                    }
                    if (result =="") {
                        query = "INSERT INTO employees (emp_id, emp_name, emp_job, emp_passw) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, id);
                            statement.setString(2, name);
                            statement.setString(3, job);
                            statement.setString(4, passw);
                            statement.executeUpdate();
                        }
                    }
                    else {
                        query = "UPDATE employees SET emp_name=?, emp_job =?, emp_passw = ? WHERE emp_id = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, name);
                            statement.setString(2, job);
                            statement.setString(3, passw);
                            statement.setString(4, id);
                            statement.executeUpdate();
                        }
                    }
                }
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            refreshTable.refreshTable(category,empTableModel);
            empIdTxt.setText("");
            empNameTxt.setText("");
            empJobTxt.setText("");
            empPwTxt.setText("");
        }
    }

    // reset all text and table in employee panel ------------------------------
    private void empResetBtnMouseClicked(java.awt.event.MouseEvent evt) {
        refreshTable.refreshTable("employees",empTableModel);
        empSearchTxt.setText("");
        empIdTxt.setText("");
        empNameTxt.setText("");
        empJobTxt.setText("");
        empPwTxt.setText("");
    }

    // employee remove from database method-------------------------------------
    private void empRemoveBtnMouseClicked(java.awt.event.MouseEvent evt,JTable detailsTable) { 
        int selectedRow = detailsTable.getSelectedRow();
        String category = "employees";
        int count = 0;
        if (selectedRow >= 0 ) {
            String removeId =(String)detailsTable.getValueAt(selectedRow,0);
            String removeJob = (String)detailsTable.getValueAt(selectedRow,2);
            if (removeJob.equals("Admin")){
                try {
                    Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
                        String query = "SELECT COUNT(*) FROM employees WHERE emp_job = \"Admin\" ";
                        try (PreparedStatement statement1 = connection.prepareStatement(query)) {
                            try (ResultSet resultSet = statement1.executeQuery()) {
                                while (resultSet.next()) {
                                count = resultSet.getInt(1);
                                }
                                if ( count > 1){
                                    query = "DELETE FROM employees WHERE emp_id = ?";
                                    try (PreparedStatement statement2 = connection.prepareStatement(query)) {
                                        statement2.setString(1, removeId);
                                        statement2.executeUpdate();
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
            else{
                try {
                    Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                        String query = "DELETE FROM employees WHERE emp_id = ?";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setString(1, removeId);
                            statement.executeUpdate();
                        }
                    }
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }   
            }
            refreshTable.refreshTable(category,empTableModel);
        }
    }

    // stock graph window opening method ---------------------------------------
    private void stockReportBtnMouseClicked(java.awt.event.MouseEvent evt) {
        StockReportWindow stockReportWindow = new StockReportWindow();
        stockReportWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        stockReportWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        stockReportWindow.setVisible(true);
    }

    // customer graphs window opening method -----------------------------------
    private void cusReportBtnMouseClicked(java.awt.event.MouseEvent evt) {
        CusReportWindow cusReportWindow = new CusReportWindow();
        cusReportWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cusReportWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cusReportWindow.setVisible(true);
    }

    // employee graph window opening method ------------------------------------
    private void empReportBtnMouseClicked(java.awt.event.MouseEvent evt) {
        EmployeeReportWindow employeeReportWindow = new EmployeeReportWindow();
        employeeReportWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        employeeReportWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        employeeReportWindow.setVisible(true);
    }

    // stock key press search trigger method -----------------------------------
    private void stockSearchTxtKeyPressed(java.awt.event.KeyEvent evt,DefaultTableModel tableModel) {                                          
        String text = stockSearchTxt.getText();    
        tableModel.setRowCount(0);

        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                String query = "SELECT stock_id, stock_name, stock_qntity, stock_price FROM stocks WHERE stock_id LIKE ? OR stock_name LIKE ? OR stock_qntity LIKE ? OR stock_price LIKE ? ";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, "%" + text + "%");
                    statement.setString(2, "%" + text + "%");
                    statement.setString(3, "%" + text + "%");
                    statement.setString(4, "%" + text + "%");
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String id = resultSet.getString("stock_id");
                            String name = resultSet.getString("stock_name");
                            String qntity= resultSet.getString("stock_qntity");
                            String price= resultSet.getString("stock_price");
                            tableModel.addRow(new Object[]{id, name, qntity,price});
                        }
                    }
                } 
            }  
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // employee key press search trigger method --------------------------------
    private void empSearchTxtKeyPressed(java.awt.event.KeyEvent evt,DefaultTableModel tableModel) { 
        String text = empSearchTxt.getText();
        tableModel.setRowCount(0);
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                String query = "SELECT emp_id, emp_name, emp_job, emp_passw FROM employees WHERE emp_id LIKE ? OR emp_name LIKE ? OR emp_job LIKE ? OR emp_passw LIKE ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, "%" + text + "%");
                    statement.setString(2, "%" + text + "%");
                    statement.setString(3, "%" + text + "%");
                    statement.setString(4, "%" + text + "%");
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String id = resultSet.getString("emp_id");
                            String name = resultSet.getString("emp_name");
                            String job = resultSet.getString("emp_job");
                            String passw = resultSet.getString("emp_passw");
                            tableModel.addRow(new Object[]{id, name, job, passw});
                        }
                    }
                } 
            }  
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // customers key press search trigger method -------------------------------
    private void cusSearchTxtKeyPressed(java.awt.event.KeyEvent evt,DefaultTableModel tableModel) { 
        String text = cusSearchTxt.getText();    
        tableModel.setRowCount(0);
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                String query = "SELECT cus_id, cus_name, cus_phone, cus_loyal FROM customers WHERE cus_id LIKE ? OR cus_name LIKE ? OR cus_phone LIKE ? OR cus_loyal LIKE ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, "%" + text + "%");
                    statement.setString(2, "%" + text + "%");
                    statement.setString(3, "%" + text + "%");
                    statement.setString(4, "%" + text + "%");
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String id = resultSet.getString("cus_id");
                            String name = resultSet.getString("cus_name");
                            String phone = resultSet.getString("cus_phone");
                            double loyalty = resultSet.getDouble("cus_loyal");
                            tableModel.addRow(new Object[]{id, name, phone, loyalty});
                        }
                    }
                }
            }   
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //transactions key press search trigger method -----------------------------
    private void traSearchTxtKeyPressed(java.awt.event.KeyEvent evt,DefaultTableModel tableModel) { 
        String text = traSearchTxt.getText();
        if(text.isEmpty()){refreshTable.refreshTable("tra",tableModel);}
        else{
            tableModel.setRowCount(0);
            try {
                Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                    String query = "SELECT * FROM transactions WHERE ? IN (tra_number, bill_num, tra_date, tra_time, cashier_id, cashier_name, tra_total_qty, cus_id, cus_name, sub_total, pay_method, paid_amount, balance)";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, text );
                        try (ResultSet resultSet = statement.executeQuery()) {
                            while (resultSet.next()) {
                                int tra_number = resultSet.getInt("tra_number");
                                String bill_num = resultSet.getString("bill_num");
                                String tra_date = resultSet.getString("tra_date");
                                String tra_time = resultSet.getString("tra_time");
                                String cashier_id = resultSet.getString("cashier_id");
                                String cashier_name = resultSet.getString("cashier_name");
                                int total_qty = resultSet.getInt("tra_total_qty");
                                String cus_id = resultSet.getString("cus_id");
                                String cus_name = resultSet.getString("cus_name");
                                double sub_total = resultSet.getDouble("sub_total");
                                String pay_method = resultSet.getString("pay_method");
                                double paid_amount = resultSet.getDouble("paid_amount");
                                double balance = resultSet.getDouble("balance");
                                tableModel.addRow(new Object[]{tra_number, bill_num, tra_date, tra_time, cashier_id, cashier_name, total_qty, cus_id, cus_name, sub_total, pay_method, paid_amount, balance});
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

    // customer details text refilled accordingly from table -------------------
    private void cusDetailsTableMouseDoubleClicked(java.awt.event.MouseEvent evt, JTable cusDetailsTable){
        int selectedRow = cusDetailsTable.getSelectedRow();
        cusIdTxt.setText(cusDetailsTable.getValueAt(selectedRow, 0).toString());
        cusNameTxt.setText(cusDetailsTable.getValueAt(selectedRow, 1).toString());
        cusPhoneTxt.setText(cusDetailsTable.getValueAt(selectedRow, 2).toString());
        cusLoyaltyTxt.setText(cusDetailsTable.getValueAt(selectedRow, 3).toString());
    }

    // stocks details text refilled accordingly from table ---------------------
    private void stockDetailsTableMouseDoubleClicked(java.awt.event.MouseEvent evt, JTable stockDetailsTable){
        int selectedRow = stockDetailsTable.getSelectedRow();
        stockIdTxt.setText(stockDetailsTable.getValueAt(selectedRow, 0).toString());
        stockNameTxt.setText(stockDetailsTable.getValueAt(selectedRow, 1).toString());
        stockQntityTxt.setText(stockDetailsTable.getValueAt(selectedRow, 2).toString());
        stockPriceTxt.setText(stockDetailsTable.getValueAt(selectedRow, 3).toString());
    }

    // employee details text refilled accordingly from table -------------------
    private void empDetailsTableMouseDoubleClicked(java.awt.event.MouseEvent evt, JTable empDetailsTable){
        int selectedRow = empDetailsTable.getSelectedRow();
        empIdTxt.setText(empDetailsTable.getValueAt(selectedRow, 0).toString());
        empNameTxt.setText(empDetailsTable.getValueAt(selectedRow, 1).toString());
        empJobTxt.setText(empDetailsTable.getValueAt(selectedRow, 2).toString());
        empPwTxt.setText(empDetailsTable.getValueAt(selectedRow, 3).toString());
    }

    // trigger authenticating dialog for stock resetting method ----------------
    private void stockIntResetBtnMouseClicked(java.awt.event.MouseEvent evt,String id){
        if (!stockIntResetHasRun) {
            stockIntResetHasRun = true;
            ResetPassWindow resetPassWindow = new ResetPassWindow(this,id);
            resetPassWindow.setVisible(true);
        } 
    }
}