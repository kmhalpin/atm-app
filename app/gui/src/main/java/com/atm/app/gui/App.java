package com.atm.app.gui;

import com.atm.app.usecase.*;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;

import com.atm.app.db.*;
import com.atm.app.domain.repository.*;

enum Page {
    ACCOUNT,
    BALANCE,
    DEPOSIT,
    HISTORY,
    LOGIN,
    MENUTRANSACTION,
    SIGNUP,
    TRANSFER,
    WITHDRAW
}

public class App extends javax.swing.JFrame implements AppContext {
    JPanel withdraw,
            transfer,
            signup,
            menuTransaction,
            login,
            history,
            deposit,
            balance,
            account;
    javax.swing.GroupLayout layout;

    TransactionUsecase tUsecase;
    UserUsecase uUsecase;
    String authAccount;

    App() {
        TransactionRepository tRepository = new DBTransactionRepository();
        UserRepository uRepository = new DBUserRepository();

        this.tUsecase = new TransactionUsecase(tRepository, uRepository);
        this.uUsecase = new UserUsecase(uRepository);

        withdraw = new Withdraw(this, this.tUsecase, this.uUsecase);
        transfer = new Transfer(this, this.tUsecase);
        signup = new Signup(this, this.uUsecase);
        menuTransaction = new MenuTransaction(this);
        login = new Login(this, this.uUsecase);
        history = new History(this, this.tUsecase, this.uUsecase);
        deposit = new Deposit(this, this.tUsecase);
        balance = new Balance(this, this.uUsecase);
        account = new Account(this);
        layout = new javax.swing.GroupLayout(getContentPane());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        movePage(Page.LOGIN);

        pack();
        setVisible(true);
    }

    private void changePanel(JPanel jPanel1) {
        getContentPane().removeAll();
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        for (Component control : jPanel1.getComponents()) {
            if (control instanceof JTextField) {
                JTextField ctrl = (JTextField) control;
                ctrl.setText("");
            }
        }
        update(getGraphics());
        if (jPanel1 instanceof UpdateablePanel) {
            ((UpdateablePanel) jPanel1).updatePanel();
        }
    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }

    @Override
    public void setAuth(String account) {
        this.authAccount = account;
    }

    @Override
    public String getAuth() {
        return this.authAccount;
    }

    @Override
    public void movePage(Page page) {
        switch (page) {
            case ACCOUNT:
                changePanel(account);
                return;
            case BALANCE:
                changePanel(balance);
                return;
            case DEPOSIT:
                changePanel(deposit);
                return;
            case HISTORY:
                changePanel(history);
                return;
            case LOGIN:
                changePanel(login);
                return;
            case MENUTRANSACTION:
                changePanel(menuTransaction);
                return;
            case SIGNUP:
                changePanel(signup);
                return;
            case TRANSFER:
                changePanel(transfer);
                return;
            case WITHDRAW:
                changePanel(withdraw);
                return;
        }
    }
}
