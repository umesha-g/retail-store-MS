/*
 * This work is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/4.0/.
 */

// @author Umesha Madushan

package com.umeshag.retailstorerm;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        DataBaseInitializer.initialize();
         try{
             javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
         }
         catch(UnsupportedLookAndFeelException e){
             e.printStackTrace();
         }
        SwingUtilities.invokeLater(() -> {
             openLogin();
        });
    }

    //method for display cashier window on Main --------------------------------
    private static void openCashierWindow(String id,String username) {
            CashierWindow cashierFrame = new CashierWindow(id,username);
            cashierFrame.setVisible(true);
    }
    
    //method for display admin window on main ----------------------------------
    private static void openAdminWindow(String id,String username) {
        AdminWindow adminFrame = new AdminWindow(id,username);
        adminFrame.setVisible(true);
    }
    
    //method for create new login instance and inject selecting method ---------
    public static void openLogin() {
        LoginWindow loginFrame = new LoginWindow();
        loginFrame.setVisible(true);
        loginFrame.addLoginListener(new LoginListener() {
            @Override // new loginlistener with new selection method
            public void onLoginSuccess(String id,String job,String username) {
                loginFrame.dispose();
                String admin = "Admin";
                if (admin.equals(job)) {
                    openAdminWindow(id,username);
                }
                else{
                    openCashierWindow(id,username);     
                }
            }
        });
    }
}