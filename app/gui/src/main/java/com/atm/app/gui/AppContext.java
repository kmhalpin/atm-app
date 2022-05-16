package com.atm.app.gui;

public interface AppContext {
  void movePage(Page page);
  void setAuth(String account);
  String getAuth();
}
