package com.umeshag.retailstorerm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CashierWindow extends javax.swing.JFrame implements Serializable {
    private static final long serialVersionUID = 3;

//============================= OBJECTS DICLARATION ============================

    private DefaultTableModel itemsTableModel;
    private DefaultTableModel billTableModel;

    JPanel leftPnl = new JPanel();
    JLabel welcomeLbl = new JLabel();
    JPanel orderCtrlPnl = new JPanel();
    JLabel orderCtrlLbl = new JLabel();
    JButton newOrderBtn = new JButton();
    JButton logOutBtn = new JButton();
    JSeparator overSep = new JSeparator();
    JLabel itemSearchLbl = new JLabel();
    JTextField itemSearchTxt = new JTextField();
    JButton itemResetBtn = new JButton();
    JSeparator underSep = new JSeparator();
    JButton printBtn = new JButton();
    JPanel cusCtrlPnl = new JPanel();
    JLabel cusCtrlLbl = new JLabel();
    JLabel cusSelectLbl = new JLabel();
    JTextField cusSearchTxt = new JTextField();
    JButton cusSearchBtn = new JButton();
    JPanel cusSelectedPnl = new JPanel();
    JLabel cusSelectedLbl = new JLabel();
    JLabel cusInfoLbl = new JLabel();
    JPanel paymentCtrlPnl = new JPanel();
    JLabel paymentCtrlLbl = new JLabel();
    JButton cashBtn = new JButton();
    JButton cardBtn = new JButton();
    JButton voucherBtn = new JButton();
    JButton paidBtn = new JButton();
    JPanel centerPnl = new JPanel();
    JPanel itemBorderPnl = new JPanel();
    JScrollPane itemsScrollPane = new JScrollPane();
    JTable itemsDispTable = new JTable();
    JLabel quanLbl = new JLabel();
    JTextField quantityTxt = new JTextField();
    JButton addToBillBtn = new JButton();
    JPanel rightPnl = new JPanel();
    JPanel billPreviewpanel = new JPanel();
    JPanel billPnl = new JPanel();
    JEditorPane billTopTxt = new JEditorPane();
    JScrollPane billScrollPane = new JScrollPane();
    JTable billTable = new JTable();
    JEditorPane billBottomTxt = new JEditorPane();
    JLabel totalTxt = new JLabel();
    JButton removeBtn = new JButton();

    double paidAmount=0.0;
    double totalBillAmount =0.0;
    String billNum = "";
    Object[] cusBillPrint = new Object[4];
    String uname = new String();
    String htmlTopContent = new String();
    String htmlBottomContent = new String();
    String payMethod="Cash";
    String username ="";
    String id="";
    boolean itemSearchHasRun = false;
    boolean cusSearchHasRun = false;
    boolean afterPayLock = false;
    boolean paidBtnHasRun = true;
    int totalQuantity =0;
    Object[] addBillOut = new Object[2];
    double balance = 0.0;
    double num =0.0;
    double newLoyal = 0.0;
    double addedLoyal = 0.0;
    int numOfOrders = 0;

    DefaultTableCellRenderer billRenderer = new DefaultTableCellRenderer();

    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    String date = dateFormat.format(new Date());
    String time = timeFormat.format(new Date());
    String loginTime = timeFormat.format(new Date());
    String loginDate = dateFormat.format(new Date());
    
    String sqlUrl = "jdbc:mysql://localhost:3306/retail_store_database";
    String sqlUsername = "maths";
    String sqlPassword = "maths";

//================================ CONSTRUCTOR =================================

    public CashierWindow(String id,String username) {
        this.username = username;
        this.id= id;
        components(username);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // start maximized
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit when close
        setTitle("Cashier Window"); // window title

        cusBillPrint[0] = "Non-Reg Customer" ;
        cusBillPrint[1] = 0.0 ;
        cusBillPrint[2] = "-" ;
        cusBillPrint[3] = "-";
    }

//============================ GUI COMPONENTS ==================================
                       
    private void components(String username) {

        // create bill tabel model
        String[] columnNames = {"##", "Code", "Name", "Unit Price", "Quantity", "Amount"};
        billTableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all rows non-editable
            }
        };

        //initial top editir pane HTML content
        htmlTopUpdateMethod();
        htmlBottomContent = "<html><head></head><body  style='font-family:Liberation Mono; text-align:left;'>"
                    +"<p>-------------------------------------------------------------------------<p>";

        setBackground(new java.awt.Color(0, 51, 51));
        setUndecorated(true); // remove window default border
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS));

        //properties for left container panel
        leftPnl.setBackground(new java.awt.Color(0, 102, 102));
        leftPnl.setMaximumSize(new java.awt.Dimension(400, 32767));
        leftPnl.setPreferredSize(new java.awt.Dimension(400, 520));
        leftPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 20));

        //properties for left welcome + name label
        welcomeLbl.setFont(new java.awt.Font("Noto Sans", 1, 16));
        String userlable ="Welcome, " +username;
        welcomeLbl.setText(userlable);
        welcomeLbl.setForeground(new java.awt.Color(255, 255, 255));
        welcomeLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        welcomeLbl.setPreferredSize(new java.awt.Dimension(350, 21));
        leftPnl.add(welcomeLbl);

        //properties for order control panel
        orderCtrlPnl.setBackground(new java.awt.Color(0, 51, 51));
        orderCtrlPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        orderCtrlPnl.setPreferredSize(new java.awt.Dimension(380, 260));
        orderCtrlPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 18));

        //properties for order control title lable
        orderCtrlLbl.setFont(new java.awt.Font("Noto Sans", 1, 16)); 
        orderCtrlLbl.setForeground(new java.awt.Color(255, 255, 255));
        orderCtrlLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orderCtrlLbl.setText("Order Control Panel");
        orderCtrlLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        orderCtrlPnl.add(orderCtrlLbl);

        //properties for new order button
        newOrderBtn.setBackground(new java.awt.Color(0, 102, 102));
        newOrderBtn.setForeground(new java.awt.Color(255, 255, 255));
        newOrderBtn.setText("Start New Order");
        newOrderBtn.setPreferredSize(new java.awt.Dimension(200, 34));
        newOrderBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billNum = newOrderBtnMouseClicked(evt,username);
            }
        });
        orderCtrlPnl.add(newOrderBtn);

        //properties for log out button
        logOutBtn.setForeground(new java.awt.Color(0, 51, 51));
        logOutBtn.setText("Log out");
        logOutBtn.setMaximumSize(new java.awt.Dimension(80, 27));
        logOutBtn.setPreferredSize(new java.awt.Dimension(100, 34));
        logOutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutBtnMouseClicked(evt);
            }
        });
        orderCtrlPnl.add(logOutBtn);

        //properties for over search seperator line
        overSep.setBackground(new java.awt.Color(255, 255, 255));
        overSep.setForeground(new java.awt.Color(255, 255, 255));
        overSep.setMinimumSize(new java.awt.Dimension(360, 2));
        overSep.setPreferredSize(new java.awt.Dimension(340, 2));
        orderCtrlPnl.add(overSep);

        //properties for item search label
        itemSearchLbl.setForeground(new java.awt.Color(255, 255, 255));
        itemSearchLbl.setText("Search");
        itemSearchLbl.setPreferredSize(new java.awt.Dimension(60, 21));
        orderCtrlPnl.add(itemSearchLbl);

        //properties for item search text field
        itemSearchTxt.setBackground(new java.awt.Color(0, 51, 51));
        itemSearchTxt.setForeground(new java.awt.Color(255, 255, 255));
        itemSearchTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        itemSearchTxt.setPreferredSize(new java.awt.Dimension(160, 30));
        itemSearchTxt.setText("for Items");
        itemSearchTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemSearchTxtMouseClicked(evt);
            }
        });
        itemSearchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemSearchTxtKeyPressed(evt,itemsTableModel);
            }
        });
        orderCtrlPnl.add(itemSearchTxt);

        //properties for item reset button
        itemResetBtn.setBackground(new java.awt.Color(0, 102, 102));
        itemResetBtn.setForeground(new java.awt.Color(255, 255, 255));
        itemResetBtn.setText("Reset");
        itemResetBtn.setPreferredSize(new java.awt.Dimension(70, 34));
        itemResetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemResetBtnMouseClicked(evt,itemsTableModel);
            }
        });
        orderCtrlPnl.add(itemResetBtn);

        //properties for under search seperator line
        underSep.setBackground(new java.awt.Color(255, 255, 255));
        underSep.setForeground(new java.awt.Color(255, 255, 255));
        underSep.setMinimumSize(new java.awt.Dimension(360, 2));
        underSep.setPreferredSize(new java.awt.Dimension(340, 2));
        orderCtrlPnl.add(underSep);

        //properties for print button
        printBtn.setBackground(new java.awt.Color(0, 102, 102));
        printBtn.setForeground(new java.awt.Color(255, 255, 255));
        printBtn.setText("Print the Bill");
        printBtn.setPreferredSize(new java.awt.Dimension(200, 34));
        printBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printBtnMouseClicked(evt);
            }
        });
        orderCtrlPnl.add(printBtn);

        leftPnl.add(orderCtrlPnl);

        //properties for customer control panel
        cusCtrlPnl.setBackground(new java.awt.Color(0, 51, 51));
        cusCtrlPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cusCtrlPnl.setPreferredSize(new java.awt.Dimension(380, 180));
        cusCtrlPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 16));

        //properties for customer control title label
        cusCtrlLbl.setFont(new java.awt.Font("Noto Sans", 1, 16)); 
        cusCtrlLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusCtrlLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cusCtrlLbl.setText("Customer Panel");
        cusCtrlLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        cusCtrlPnl.add(cusCtrlLbl);

        //properties for customer selector label
        cusSelectLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusSelectLbl.setText("ID/Phone");
        cusSelectLbl.setPreferredSize(new java.awt.Dimension(70, 21));
        cusCtrlPnl.add(cusSelectLbl);

        //properties for cutomer selector text field
        cusSearchTxt.setBackground(new java.awt.Color(0, 51, 51));
        cusSearchTxt.setForeground(new java.awt.Color(255, 255, 255));
        cusSearchTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        cusSearchTxt.setPreferredSize(new java.awt.Dimension(140, 30));
        cusSearchTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cusSearchTxtMouseClicked(evt);
            }
        });
        cusCtrlPnl.add(cusSearchTxt);

        //properties for customer search button
        cusSearchBtn.setBackground(new java.awt.Color(0, 102, 102));
        cusSearchBtn.setForeground(new java.awt.Color(255, 255, 255));
        cusSearchBtn.setText("Select");
        cusSearchBtn.setToolTipText("");
        cusSearchBtn.setPreferredSize(new java.awt.Dimension(70, 34));
        cusSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                 cusSearchBtnMouseClicked(evt);
            }
        });
        cusCtrlPnl.add(cusSearchBtn);

        //properties for selected customer panel
        cusSelectedPnl.setBackground(new java.awt.Color(0, 51, 51));
        cusSelectedPnl.setPreferredSize(new java.awt.Dimension(320, 60));
        cusSelectedPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        //properties for selected customer label
        cusSelectedLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusSelectedLbl.setText("Selected Customer");
        cusSelectedLbl.setAlignmentY(0.0F);
        cusSelectedLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        cusSelectedPnl.add(cusSelectedLbl);

        //properties for selected customer information label
        cusInfoLbl.setFont(new java.awt.Font("Noto Sans", 1, 15)); 
        cusInfoLbl.setForeground(new java.awt.Color(255, 255, 255));
        cusInfoLbl.setText("-");
        cusInfoLbl.setAlignmentY(0.0F);
        cusInfoLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        cusSelectedPnl.add(cusInfoLbl);

        cusCtrlPnl.add(cusSelectedPnl);

        leftPnl.add(cusCtrlPnl);

        //properties for payement control panel
        paymentCtrlPnl.setBackground(new java.awt.Color(0, 51, 51));
        paymentCtrlPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        paymentCtrlPnl.setPreferredSize(new java.awt.Dimension(380, 155));
        paymentCtrlPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 15));

        //properties for payment control title label
        paymentCtrlLbl.setFont(new java.awt.Font("Noto Sans", 1, 16)); 
        paymentCtrlLbl.setForeground(new java.awt.Color(255, 255, 255));
        paymentCtrlLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paymentCtrlLbl.setText("Payment Control Panel");
        paymentCtrlLbl.setPreferredSize(new java.awt.Dimension(300, 21));
        paymentCtrlPnl.add(paymentCtrlLbl);

        //properties for cash button
        cashBtn.setBackground(new java.awt.Color(0, 102, 102));
        cashBtn.setForeground(new java.awt.Color(255, 255, 255));
        cashBtn.setText("Cash");
        cashBtn.setToolTipText("");
        cashBtn.setMaximumSize(new java.awt.Dimension(100, 27));
        cashBtn.setPreferredSize(new java.awt.Dimension(100, 34));
        cashBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                payMethod=cashBtnMouseClicked(evt);
            }
        });
        paymentCtrlPnl.add(cashBtn);

        //properties for card button
        cardBtn.setBackground(new java.awt.Color(0, 102, 102));
        cardBtn.setForeground(new java.awt.Color(255, 255, 255));
        cardBtn.setText("Card");
        cardBtn.setToolTipText("");
        cardBtn.setMaximumSize(new java.awt.Dimension(100, 27));
        cardBtn.setPreferredSize(new java.awt.Dimension(100, 34));
        cardBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                payMethod=cardBtnMouseClicked(evt);
            }
        });
        paymentCtrlPnl.add(cardBtn);

        //properties for voucher button
        voucherBtn.setBackground(new java.awt.Color(0, 102, 102));
        voucherBtn.setForeground(new java.awt.Color(255, 255, 255));
        voucherBtn.setText("Voucher");
        voucherBtn.setToolTipText("");
        voucherBtn.setMaximumSize(new java.awt.Dimension(100, 27));
        voucherBtn.setPreferredSize(new java.awt.Dimension(100, 34));
        voucherBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                payMethod=voucherBtnMouseClicked(evt);
            }
        });
        paymentCtrlPnl.add(voucherBtn);

        //properties for paid button
        paidBtn.setBackground(new java.awt.Color(0, 102, 102));
        paidBtn.setForeground(new java.awt.Color(255, 255, 255));
        paidBtn.setText("Paid");
        paidBtn.setToolTipText("");
        paidBtn.setMaximumSize(new java.awt.Dimension(100, 27));
        paidBtn.setPreferredSize(new java.awt.Dimension(250, 34));
        paidBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               paidBtnMouseClicked(evt);
            }
        });
        paymentCtrlPnl.add(paidBtn);

        leftPnl.add(paymentCtrlPnl);

        getContentPane().add(leftPnl);

        //properties for center panel
        centerPnl.setBackground(new java.awt.Color(0, 102, 102));
        centerPnl.setPreferredSize(new java.awt.Dimension(450, 680));
        centerPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 40, 20));

        //properties for border panel for scrol panel table and its item display
        itemBorderPnl.setBackground(new java.awt.Color(0, 51, 51));
        itemBorderPnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        itemBorderPnl.setPreferredSize(new java.awt.Dimension(450, 675));
        itemBorderPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 15));

        //properties for scroll panel for item display table
        itemsScrollPane.setPreferredSize(new java.awt.Dimension(420, 600));

        //table model for item display table
        itemsTableModel = new DefaultTableModel(new Object[]{"Code","Name","Quantity","Unit Price"},0 ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //properties for item display table
        itemsDispTable.setModel(itemsTableModel);
        itemsDispTable.setDefaultRenderer(Object.class, new CustomRowRenderer());
        itemsDispTable.setGridColor(new java.awt.Color(220,220,220));
        itemsDispTable.setShowGrid(true);
        itemsDispTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    addToBillBtnMouseClicked(evt,itemsDispTable,billTable,billTableModel,itemsTableModel);
                }
            }
        });

        itemsScrollPane.setViewportView(itemsDispTable);

        itemBorderPnl.add(itemsScrollPane);

        //properties for quantity label
        quanLbl.setForeground(new java.awt.Color(255, 255, 255));
        quanLbl.setText("Quantity");
        itemBorderPnl.add(quanLbl);

        //properties for quantity text field
        quantityTxt.setBackground(new java.awt.Color(0, 51, 51));
        quantityTxt.setForeground(new java.awt.Color(255, 255, 255));
        quantityTxt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        quantityTxt.setText("1");
        itemBorderPnl.add(quantityTxt);

        //properties for add to bill button
        addToBillBtn.setBackground(new java.awt.Color(0, 102, 102));
        addToBillBtn.setForeground(new java.awt.Color(255, 255, 255));
        addToBillBtn.setText("Add to Bill");
        addToBillBtn.setToolTipText("");
        addToBillBtn.setMaximumSize(new java.awt.Dimension(100, 27));
        addToBillBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        addToBillBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addToBillBtnMouseClicked(evt,itemsDispTable,billTable,billTableModel,itemsTableModel);
            }
        });
        itemBorderPnl.add(addToBillBtn);

        centerPnl.add(itemBorderPnl);

        getContentPane().add(centerPnl);

        //defining new font for bill panel
        Font customFont = new Font("Liberation Mono", Font.PLAIN, 12);

        //properties for right panel
        rightPnl.setBackground(new java.awt.Color(0, 102, 102));
        rightPnl.setPreferredSize(new java.awt.Dimension(490, 700));
        rightPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 40, 20));

        //properties for bill privew panel(border)
        billPreviewpanel.setBackground(new java.awt.Color(0, 51, 51));
        billPreviewpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        billPreviewpanel.setPreferredSize(new java.awt.Dimension(489, 675));
        billPreviewpanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 15));

        //properties for bill scroll panel (bill panel)
        billScrollPane.setPreferredSize(new java.awt.Dimension(465, 600));
        billScrollPane.setMinimumSize(new java.awt.Dimension(465, 600));
        billScrollPane.setName(""); 
        billScrollPane.setOpaque(false);
        billScrollPane.setVerifyInputWhenFocusTarget(false);

        //properties for bill panel (top text + table + bottom text)
        billPnl.setBorder(null);
        billPnl.setLayout(new javax.swing.BoxLayout(billPnl, javax.swing.BoxLayout.Y_AXIS));

        //properties for bill top editor pane
        billTopTxt.setBackground(Color.WHITE);
        billTopTxt.setEditable(false); // Make it non-editable
        billTopTxt.setContentType("text/html");
        billTopTxt.setText(htmlTopContent);
        billTopTxt.setBorder(null);
        billPnl.add(billTopTxt);

        // create bill table using bill table model + its properties
        billTable = new JTable(billTableModel);
        billTable.setTableHeader(null); // remove the table headers
        billTable.setShowGrid(false);   // remove grid lines
        billTable.setFont(customFont);  // set new font
        billTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                billItemRemoveMethod(evt,itemsDispTable,billTable,billTableModel,itemsTableModel);
                }
            }
        });

        //custom bill table column sizes
        TableColumnModel biillColumnModel = billTable.getColumnModel();
        TableColumn firstColumn = biillColumnModel.getColumn(0);
        firstColumn.setPreferredWidth(30);
        TableColumn secondColumn = biillColumnModel.getColumn(1);
        secondColumn.setPreferredWidth(80);
        TableColumn thirdColumn = biillColumnModel.getColumn(2);
        thirdColumn.setPreferredWidth(130);
        TableColumn fourColumn = biillColumnModel.getColumn(3);
        fourColumn.setPreferredWidth(50);
        TableColumn fiveColumn = biillColumnModel.getColumn(4);
        fiveColumn.setPreferredWidth(40);
        TableColumn sixColumn = biillColumnModel.getColumn(5);
        sixColumn.setPreferredWidth(70);

        //adding title rows to bill table
        billTableModel.addRow(new Object[]{"---","----------","------------------","------","----","--------"});
        billTableModel.addRow(new Object[]{"#","Code","Product","Price","Qty","Amount"});
        billTableModel.addRow(new Object[]{"---","----------","------------------","------","----","--------"});
        
        // custom cell renderer to change text alignment
        billRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        fourColumn.setCellRenderer(billRenderer);
        fiveColumn.setCellRenderer(billRenderer);
        sixColumn.setCellRenderer(billRenderer);
        billPnl.add(billTable);

        //properties for bill bottom editor pane
        billBottomTxt.setBorder(null);
        billBottomTxt.setBackground(Color.WHITE);
        billBottomTxt.setPreferredSize(new java.awt.Dimension(450, 520));
        billBottomTxt.setContentType("text/html");
        billBottomTxt.setEditable(false);
        billBottomTxt.setText(htmlBottomContent);
        billPnl.add(billBottomTxt);

        billScrollPane.setViewportView(billPnl);

        billPreviewpanel.add(billScrollPane);

        //properties for total text field
        totalTxt.setFont(new java.awt.Font("Noto Sans", 1, 18)); 
        totalTxt.setForeground(new java.awt.Color(255, 255, 255));
        totalTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalTxt.setText("Total : 0");
        totalTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        totalTxt.setMaximumSize(new java.awt.Dimension(149, 21));
        totalTxt.setMinimumSize(new java.awt.Dimension(149, 21));
        totalTxt.setPreferredSize(new java.awt.Dimension(150, 21));
        billPreviewpanel.add(totalTxt);

        //properties for remove button
        removeBtn.setBackground(new java.awt.Color(0, 102, 102));
        removeBtn.setForeground(new java.awt.Color(255, 255, 255));
        removeBtn.setText("Remove");
        removeBtn.setToolTipText("");
        removeBtn.setMaximumSize(new java.awt.Dimension(100, 27));
        removeBtn.setPreferredSize(new java.awt.Dimension(150, 34));
        removeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billItemRemoveMethod(evt,itemsDispTable,billTable,billTableModel,itemsTableModel);
            }
        });
        billPreviewpanel.add(removeBtn);
        rightPnl.add(billPreviewpanel);
        getContentPane().add(rightPnl);
        pack();
        //refreshing item table at sturtup
        refreshTable(itemsTableModel);
    }                       

//================================ METHODS =====================================   

    // table Refreshing method -------------------------------------------------
    public void refreshTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                String query = "SELECT stock_id, stock_name, stock_qntity,stock_price FROM stocks";
                try (PreparedStatement statement = connection.prepareStatement(query)){
                    try (ResultSet resultSet = statement.executeQuery(query)) {
                        while (resultSet.next()) {
                            String id = resultSet.getString("stock_id");
                            String name = resultSet.getString("stock_name");
                            String qntity = resultSet.getString("stock_qntity");
                            String price = resultSet.getString("stock_price");
                            int intQuan = Integer.parseInt(qntity);
                            double intPrice = Double.parseDouble(price);
                            tableModel.addRow(new Object[]{id, name, intQuan,intPrice});
                         }
                    }
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //--------------------------------------------------------------------------

    // payment calculation and invoice botom update method ---------------------
    public void paymentCal(double number){
        num = number;
        if("Card".equals(payMethod)){
            int digits = (int)number;
            htmlBottomContent = "<html><head></head><body  style='font-family:Liberation Mono; text-align:left;'>"
                    +"<p>-------------------------------------------------------------------------<p>"
                    + "<h3 style='color: black;font-weight: 500; float:left;'>Sub Total : "+totalBillAmount+"</h3>"
                    +"<p >-------------------------------------------------------------------------<p>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Payment Method : Card </h4>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Paid : "+totalBillAmount+"</h4>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Last 4 Digits of Card : "+digits+"</h4>";
        }
        else if("Voucher".equals(payMethod)){
            balance = number - totalBillAmount;
            htmlBottomContent = "<html><head></head><body  style='font-family:Liberation Mono; text-align:left;'>"
                    +"<p>-------------------------------------------------------------------------<p>"
                    + "<h3 style='color: black;font-weight: 500; float:left;'>Sub Total : "+totalBillAmount+"</h3>"
                    +"<p >-------------------------------------------------------------------------<p>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Payment Method : Voucher </h4>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Paid : "+totalBillAmount+"</h4>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Remaining balance of Voucher : "+balance+"</h4>";
        }
        else{
            balance = number - totalBillAmount;
            htmlBottomContent = "<html><head></head><body  style='font-family:Liberation Mono; text-align:left;'>"
                    +"<p>-------------------------------------------------------------------------<p>"
                    + "<h3 style='color: black;font-weight: 500; float:left;'>Sub Total : "+totalBillAmount+"</h3>"
                    +"<p>-------------------------------------------------------------------------<p>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Payment Method : Cash </h4>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Paid : "+number+"</h4>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Balance : "+balance+"</h4>";
        }

        if(cusBillPrint[2] == "-" || cusBillPrint[2] == " " || cusBillPrint[2] == ""){
            String htmlBottomaAppend1 ="<div style=' font-family:Liberation Mono;margin-left:120px;font-weight: 100;font-size:12;'>"
                    +"<p>----------------------<br>"
                    +"THANK YOU FOR SHOPPING<br>"
                    +"----------------------</p>"
                    +"</div>"
                    + "</body></html>";
            billBottomTxt.setText(htmlBottomContent + htmlBottomaAppend1);
        }

        else{
            String htmlBottomaAppend2 ="<p>-------------------------------------------------------------------------<p>"
                    +"<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-15px;'>Customer :"+cusBillPrint[0]+"</h4>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Newly Added Points :"+addedLoyal+"</h4>"
                    + "<h4 style='font-family:Liberation Mono;color: black;font-weight: 100;margin-top:-10px;'>Total Points :"+cusBillPrint[1]+"</h4>"
                    +"<div style=' font-family:Liberation Mono;margin-left:120px;font-weight: 120; font-size:12;'>"
                    +"<p>----------------------<br>"
                    +"THANK YOU FOR SHOPPING<br>"
                    +"----------------------</p>"
                    +"</div>"
                    + "</body></html>";
            billBottomTxt.setText(htmlBottomContent + htmlBottomaAppend2);
        }
    }
    //--------------------------------------------------------------------------

    // logout and make staff working record method -----------------------------
    private void logOutBtnMouseClicked(java.awt.event.MouseEvent evt) {
        int count =0;
        int nThUse =0;
        String logOutTime = timeFormat.format(new Date());
        String cashID = id;//System.out.println(id);
        String cashName =username;//System.out.println(cashName);

        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
                String query = "SELECT COUNT(*) FROM staff_work_hours";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                        count = resultSet.getInt(1);
                        }
                    }
                }

                nThUse = count +1; 

                query = "INSERT INTO staff_work_hours(nth_use,cashier_id,cashier_name,date,start_time,end_time,num_of_tra) VALUES (?,?,?,?,?,?,?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, nThUse);
                    statement.setString(2, cashID);
                    statement.setString(3, cashName);
                    statement.setString(4, loginDate);
                    statement.setString(5, loginTime);
                    statement.setString(6, logOutTime);
                    statement.setInt(7, numOfOrders);
                    statement.executeUpdate();
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Main.openLogin(); 
        dispose();
    }

    // Start new order method --------------------------------------------------
    private String newOrderBtnMouseClicked(java.awt.event.MouseEvent evt,String username) {
        paidBtnHasRun = false;
        billNum = BillNumberGenerator.generateBillNumber();
        date = dateFormat.format(new Date());
        time = timeFormat.format(new Date());
        totalQuantity =0;

        //table reseting loop
        while (billTableModel.getRowCount() > 3) {
            int lastRow = billTableModel.getRowCount() - 1;
            billTableModel.removeRow(lastRow);
        }
        htmlTopUpdateMethod();
        totalBillAmount = 0;
        cusBillPrint[0] = "Non-Reg Customer";
        cusBillPrint[1] = 0.0;
        cusBillPrint[2] = "";
        cusInfoLbl.setText("");
        totalTxt.setText("Total : 0");
        htmlBottomContent = "<html><head></head><body  style='font-family:Liberation Mono; text-align:left;'>"
                    +"<p>-------------------------------------------------------------------------<p>";
        billBottomTxt.setText(htmlBottomContent);
        return billNum;
    }

    // customer selecting or make customer non-reg method ----------------------
    private void cusSearchBtnMouseClicked(java.awt.event.MouseEvent evt) {
        String text = cusSearchTxt.getText();
        String id="-";
        String name="Non-Reg Customer";
        String phone ="-" ;
        double loyalty =0.0;    
        if (text.isEmpty()){}
        else{
            try {
                Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                    String query = "SELECT cus_id, cus_name, cus_phone, cus_loyal FROM customers WHERE cus_id LIKE ? OR cus_phone LIKE ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, text);
                        statement.setString(2, text);
                        try (ResultSet resultSet = statement.executeQuery()) {
                            while (resultSet.next()) {
                                 id = resultSet.getString("cus_id");
                                 name = resultSet.getString("cus_name");
                                 phone = resultSet.getString("cus_phone");
                                 loyalty = resultSet.getDouble("cus_loyal");
                            }
                        } 
                    }
                }
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if("-".equals(id)){
                cusInfoLbl.setText("");
                cusInfoLbl.setText("Customer Not Found");
                cusBillPrint[0] = "-" ;
                cusBillPrint[1] = 0.0 ;
                cusBillPrint[2] = "-" ;
                cusBillPrint[3] = "-" ;
            }
            else{
                cusInfoLbl.setText("");
                cusInfoLbl.setText(name +" ("+phone+")");
                cusBillPrint[0] = name ;
                cusBillPrint[1] = loyalty ;
                cusBillPrint[2] = id ;
                cusBillPrint[3] = phone;
            }
            cusSearchTxt.setText("");
        }
        //htmlBottomUpdateMethod();
    }

    // payment method change to cash method ------------------------------------
    private String cashBtnMouseClicked(java.awt.event.MouseEvent evt) {
        String payMethod = "Cash";
        return payMethod;
    }

    // payment method change to card method ------------------------------------
    private String cardBtnMouseClicked(java.awt.event.MouseEvent evt) {
        String payMethod = "Card";
        return payMethod;
    }

    // payment method chage to voucher method ----------------------------------
    private String voucherBtnMouseClicked(java.awt.event.MouseEvent evt) {
        String payMethod = "Voucher";
        return payMethod;
    }

    // paid amount input dialog boxand loyality point calculating method -------
    private void paidBtnMouseClicked(java.awt.event.MouseEvent evt) {
        afterPayLock = true;
        if (!paidBtnHasRun) { 
            paidBtnHasRun = true;    
            double oldLoyal = (double)cusBillPrint[1];
            addedLoyal = (totalBillAmount/500);
            newLoyal = addedLoyal + oldLoyal;
            String cusId = (String)cusBillPrint[2];
           

            if("-".equals(cusId)){}
            else{
                try {
                    Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                        String query = "UPDATE customers SET cus_loyal =? WHERE cus_id = ? ";
                        try (PreparedStatement statement = connection.prepareStatement(query)) {
                            statement.setDouble(1, newLoyal);
                            statement.setString(2, cusId);
                            statement.executeUpdate();
                        }
                    }
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            PopupFrame popupDlgFrame = new PopupFrame(this,payMethod);
            popupDlgFrame.setVisible(true);

            if("-".equals(cusId)){}
            else{
                cusBillPrint[1] = newLoyal ;
            }
        }
    } 

    // make the bill print but. yet do is add transaction record to Database----
    private void printBtnMouseClicked(java.awt.event.MouseEvent evt) {
        afterPayLock = false;
        numOfOrders += 1;
        int count =0;
        int traNum =0;
        String billN = billNum;
        String traDate =date;
        String traTime = time;
        String cashID = id;
        String cashName =username;
        int totalQty = totalQuantity;
        String cusId = (String)cusBillPrint[2];
        String cusName = (String)cusBillPrint[0];
        double subTotal =totalBillAmount;
        String payMeth =payMethod;
        double paidA =0.0;

        if ("Cash".equals(payMeth)) {
            paidA = num;
        }
        else{
            paidA = subTotal;
        }

        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
                String query = "SELECT COUNT(*) FROM transactions;";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = statement.executeQuery()){
                        while (resultSet.next()) {
                        count = resultSet.getInt(1);
                        }
                    }
                }

                traNum = count +1; 
        
                query = "INSERT INTO transactions(tra_number, bill_num, tra_date, tra_time, cashier_id, cashier_name, tra_total_qty, cus_id, cus_name, sub_total, pay_method, paid_amount,balance) VALUES (?, ?,?,?, ?, ?,?,?, ?, ?,?,?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, traNum);
                    statement.setString(2, billN);
                    statement.setString(3, traDate);
                    statement.setString(4, traTime);
                    statement.setString(5, cashID);
                    statement.setString(6, cashName);
                    statement.setInt(7, totalQty);
                    statement.setString(8, cusId);
                    statement.setString(9, cusName);
                    statement.setDouble(10, subTotal);
                    statement.setString(11, payMeth);
                    statement.setDouble(12, paidA);
                    statement.setDouble(13, balance);
                    statement.executeUpdate();
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        billNum = "";
        date = "";
        time = "";
        totalQuantity =0;

        //table reseting loop
        while (billTableModel.getRowCount() > 3) {
            int lastRow = billTableModel.getRowCount() - 1;
            billTableModel.removeRow(lastRow);
        }

        htmlTopUpdateMethod();
        totalBillAmount = 0;
        cusBillPrint[0] = "Non-Reg Customer";
        cusBillPrint[1] = 0.0;
        cusInfoLbl.setText("");
        totalTxt.setText("");
        htmlBottomUpdateMethod();
    }

    // item table and item search text resetting method ------------------------
    private void itemResetBtnMouseClicked(java.awt.event.MouseEvent evt, DefaultTableModel itemTableModel) {
        refreshTable(itemTableModel);
        itemSearchTxt.setText("");
    }
    
    // add to bill and reduce quantity from database method --------------------
    private void addToBillBtnMouseClicked(java.awt.event.MouseEvent evt, JTable itemTable,JTable billTable ,DefaultTableModel billTableModel,DefaultTableModel itemTableModel) {
        if(!afterPayLock){
            String quantity = quantityTxt.getText();
            int selectedRow = itemTable.getSelectedRow();
            totalBillAmount =0;
            boolean isItemInList = false;
            int billItemQty =0;
            int itemRow =0;
            int number =  billTable.getRowCount() - 2;
            int newBillItemQty = 0;

            if (selectedRow != -1) {
                int quant = 0;
                try {
                    // Parse the text into an integer
                    quant  = Integer.parseInt(quantity);
                }
                catch (NumberFormatException ex) {
                    quantityTxt.setText("Integer Only!");
                }

                String code = itemTable.getValueAt(selectedRow, 0).toString();
                String product = itemTable.getValueAt(selectedRow, 1).toString();
                int stockQty = (int)itemTable.getValueAt(selectedRow, 2);
                double itemPrice = (double) itemTable.getValueAt(selectedRow, 3);

                if(stockQty == 0){
                    JOptionPane.showMessageDialog(CashierWindow.this, "Out of Stocks");
                }
                else if(stockQty < quant){
                    JOptionPane.showMessageDialog(CashierWindow.this, "Insufficient Stocks");
                }
                else{
                    newBillItemQty = quant;
                    int j=0;
                    while(j < billTableModel.getRowCount()){ 
                        // check wether item already in the list
                        int row = j;
                        String billItemId = billTable.getValueAt(row, 1).toString();
                        if(billItemId.equals(code)){
                            isItemInList =true;
                            billItemQty = (int)billTable.getValueAt(row, 4);
                            newBillItemQty = quant + billItemQty;
                            itemRow = row;
                        }
                        j++;
                    }
                    double amount = itemPrice * newBillItemQty;

                    if(isItemInList){ //if item already in will update 
                        billTable.setValueAt(newBillItemQty,itemRow, 4);
                        billTable.setValueAt(amount,itemRow, 5);
                    }
                    else{ // if item not in will add a new
                    billTableModel.addRow(new Object[]{number,code, product,itemPrice, quant, amount});
                    }

                    int newStockQty = stockQty - quant;

                    try {
                        Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                            String query = "UPDATE stocks SET stock_qntity =? WHERE stock_id = ? ";
                            try (PreparedStatement statement = connection.prepareStatement(query)) {
                                statement.setInt(1, newStockQty);
                                statement.setString(2, code);
                                statement.executeUpdate();
                            }
                        }
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                
                int i = 4;
                int numc = billTableModel.getRowCount() + 1 ;
                while (i < numc) {
                    int row = i-1;
                    totalBillAmount += (double) billTableModel.getValueAt(row, 5);
                    i++;
                }

                totalTxt.setText("Total : "+totalBillAmount);
                totalQuantity += quant;
                htmlBottomUpdateMethod();
            }

            refreshTable(itemTableModel);
            quantityTxt.setText("1");
        }
    }

    // remove from bill and re add quantity to database method -----------------
    private void billItemRemoveMethod(java.awt.event.MouseEvent evt,JTable itemTable,JTable billTable ,DefaultTableModel billTableModel,DefaultTableModel itemTableModel) {
        if(!afterPayLock){
            int selectedRow = billTable.getSelectedRow();
            totalBillAmount =0;
            String stockQty ="";
            int intQuan = 0;

            if (selectedRow != -1) {
                if (selectedRow >= 3){
                    String code = billTable.getValueAt(selectedRow, 1).toString();
                    int quant = (int)billTable.getValueAt(selectedRow, 4);
                    try {
                        Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                            String query = "SELECT stock_qntity FROM stocks WHERE stock_id LIKE ? ";
                            try (PreparedStatement statement = connection.prepareStatement(query)) {
                                statement.setString(1, code);
                                try (ResultSet resultSet = statement.executeQuery()) {
                                    while (resultSet.next()) {
                                        stockQty= resultSet.getString("stock_qntity");
                                        intQuan = Integer.parseInt(stockQty);
                                    }
                                }
                            } 
                    
                            billTableModel.removeRow(selectedRow);// remove item from bill table
                            int newStockQty = intQuan + quant;// new quantity for database

                            query = "UPDATE stocks SET stock_qntity =? WHERE stock_id = ? ";
                            try (PreparedStatement statement = connection.prepareStatement(query)) {
                                statement.setInt(1, newStockQty);
                                statement.setString(2, code);
                                int rowsEff = statement.executeUpdate();
                            }
                        }
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    
                    int i = 4;
                    int numc = billTableModel.getRowCount() + 1 ;

                    while (i < numc) {
                        int row = i-1;
                        totalBillAmount += (double) billTableModel.getValueAt(row, 5);
                        i++;
                    }
                    totalTxt.setText("Total : "+ totalBillAmount);
                    totalQuantity -=quant;
                    htmlBottomUpdateMethod();
                }
            }
            refreshTable(itemTableModel);
        }
    }

    // customer search method --------------------------------------------------
    private void cusSearchTxtMouseClicked(java.awt.event.MouseEvent evt) {
        if (!cusSearchHasRun) {
            cusSearchTxt.setText("");
            cusSearchHasRun = true;
        } 
    }

    // item search text will empty for first time (method) ---------------------
    private void itemSearchTxtMouseClicked(java.awt.event.MouseEvent evt) {
        if (!itemSearchHasRun) {
            itemSearchTxt.setText("");
            itemSearchHasRun = true;
        } 
    }

    // search directly from database for each key press (method) ---------------
    private void itemSearchTxtKeyPressed(java.awt.event.KeyEvent evt,DefaultTableModel tableModel) {
        String text = itemSearchTxt.getText();
        if (text.isEmpty()){
            refreshTable(tableModel);
            itemSearchTxt.setText("");
        }
        else{
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

                                int intQuan = Integer.parseInt(qntity);
                                double intPrice = Double.parseDouble(price);
                                tableModel.addRow(new Object[]{id, name, intQuan,intPrice});
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

    // top editor pane HTML content update method ------------------------------
    private void htmlTopUpdateMethod(){
        htmlTopContent = "<html><head></head><body style='font-family:Liberation Mono; text-align:center;'>"
                        + "<h2 style='color: black; text-decoration: underline; font-weight: 700;'>THE GROCERY SHOP</h2>"
                        +"<h4 style='font-weight: 50;margin-top:-8px'>No. 123, Main Road, Matara.</h4>"
                        +"<h4 style='font-weight: 50;margin-top:-10px'>071-1234567.</h4>"
                        + "<h4 style='text-align:left;font-weight: 50;margin-top:-6px'>&nbsp&nbsp Cashier : "+username+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Date: " + date + "</h4>"
                        + "<h4 style='text-align:left;font-weight: 50;margin-top:-20px'>&nbsp&nbsp Bill No.: "+billNum+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Time: " + time + "<h4>"
                        +"</body></html>";
        billTopTxt.setText(htmlTopContent);
    }

    // bottom editor pane HTML content update method method --------------------
    private void htmlBottomUpdateMethod(){
        htmlBottomContent = "<html><head></head><body  style='font-family:Liberation Mono; text-align:left;'>"
                            +"<p>-------------------------------------------------------------------------</p>"
                            + "<h3 style='color: black;font-weight: 500; float:left;'>Sub Total : "+totalBillAmount+"</h3>"
                            +"<p>-------------------------------------------------------------------------</p>"
                            + "</body></html>";
        billBottomTxt.setText(htmlBottomContent);
    }

    //=========================== SUB CLASSES ==================================  
    // sub class for custom row render for item table. 
    //this will make row red if quantity is 0 
    static class CustomRowRenderer extends DefaultTableCellRenderer implements Serializable {
        private static final long serialVersionUID = 10;
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Check if the current row's Quantity column is 0
                if (table.getValueAt(row, 2) != null && (Integer) table.getValueAt(row, 2) == 0) {
                    component.setForeground(Color.RED);
                } else {
                    component.setForeground(table.getForeground());
                }
            return component;
        }
    } 
}