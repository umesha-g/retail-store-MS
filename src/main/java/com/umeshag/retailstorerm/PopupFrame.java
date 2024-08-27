/*
 * This work is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/4.0/.
 */

// @author Umesha Madushan

package com.umeshag.retailstorerm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PopupFrame extends JFrame implements Serializable {
    private static final long serialVersionUID = 4;

    JLabel paymentPopupLbl = new JLabel();
    JPanel paymentPopupPnl = new JPanel();
    private CashierWindow cashierWindow;
    JButton paymentSubmitBtn = new JButton();
    JTextField paymentPopupTxt = new JTextField();
    int payment = 0;

    //============================= CONSTRUCTOR ====================================
    public PopupFrame(CashierWindow cashierWindow, String mode) {
        this.cashierWindow = cashierWindow;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 130);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setTitle("Paid");

        if(mode == "Cash"){
            paymentPopupLbl.setText("Enter Paid Amount");
        }
        else if(mode == "Card"){
            paymentPopupLbl.setText("Enter Last 4 Digits of Card");
        }
        else{
            paymentPopupLbl.setText("Enter Value of Gift Voucher");
        }

        paymentPopupPnl.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout paymentPopupPnlLayout = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 10);
        paymentPopupPnlLayout.setAlignOnBaseline(true);
        paymentPopupPnl.setLayout(paymentPopupPnlLayout);
        
        paymentPopupLbl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        paymentPopupPnl.add(paymentPopupLbl);
        
        paymentPopupTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 2));
        paymentPopupTxt.setPreferredSize(new java.awt.Dimension(160, 30));
        paymentPopupTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {}
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {// continue on enter
                    update();
                }
            }
            @Override
            public void keyReleased(KeyEvent evt) {}
        });
        paymentPopupPnl.add(paymentPopupTxt);
        
        paymentSubmitBtn.setBackground(new java.awt.Color(0, 102, 102));
        paymentSubmitBtn.setForeground(new java.awt.Color(255, 255, 255));
        paymentSubmitBtn.setText("Submit");
        paymentSubmitBtn.setPreferredSize(new java.awt.Dimension(100, 27));
        paymentSubmitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                update();
            }
        });
        paymentPopupPnl.add(paymentSubmitBtn);

        getContentPane().add(paymentPopupPnl);
    }

//================================ METHODS =====================================
    // method for update paid amount on cashier window -------------------------
    private void update() {
        try {
            double number = Double.valueOf(paymentPopupTxt.getText());
            cashierWindow.paymentCal(number);
            dispose(); // Close the popup window
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
}