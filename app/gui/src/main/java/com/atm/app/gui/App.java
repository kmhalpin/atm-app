package com.atm.app.gui;

import com.atm.app.usecase.*;

import javax.swing.JPanel;

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
    TransactionUsecase tUsecase;
    UserUsecase uUsecase;
    String authAccount;

    App() {
        TransactionRepository tRepository = new DBTransactionRepository();
        UserRepository uRepository = new DBUserRepository();

        this.tUsecase = new TransactionUsecase(tRepository, uRepository);
        this.uUsecase = new UserUsecase(uRepository);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        movePage(Page.LOGIN);

        pack();
        setVisible(true);
    }

    private void changePanel(JPanel jPanel1) {
        getContentPane().removeAll();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
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
        update(getGraphics());
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
    public void setAuth(String account){
        this.authAccount = account;
    }

    @Override
    public String getAuth(){
        return this.authAccount;
    }

    @Override
    public void movePage(Page page) {
        switch (page) {
            case ACCOUNT:
                changePanel(new Account(this));
                return;
            case BALANCE:
                changePanel(new Balance(this, this.uUsecase));
                return;
            case DEPOSIT:
                changePanel(new Deposit(this, this.tUsecase));
                return;
            case HISTORY:
                changePanel(new History(this, this.tUsecase));
                return;
            case LOGIN:
                changePanel(new Login(this, this.uUsecase));
                return;
            case MENUTRANSACTION:
                changePanel(new MenuTransaction(this));
                return;
            case SIGNUP:
                changePanel(new Signup(this, this.uUsecase));
                return;
            case TRANSFER:
                changePanel(new Transfer(this, this.tUsecase));
                return;
            case WITHDRAW:
                changePanel(new Withdraw(this, this.tUsecase));
                return;
        }
    }
}
