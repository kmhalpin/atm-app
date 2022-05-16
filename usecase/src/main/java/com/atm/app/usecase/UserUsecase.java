package com.atm.app.usecase;

import com.atm.app.domain.entity.User;
import com.atm.app.domain.repository.UserRepository;

public class UserUsecase {
  private UserRepository uRepository;

  public UserUsecase(UserRepository uRepository) {
    this.uRepository = uRepository;
  }

  public User getAccount(String account) throws Exception {
    return this.uRepository.getByAccountNumber(account);
  }

  public User login(String account, int pin) throws Exception {
    User user = this.uRepository.getByAccountNumber(account);
    if (user.getSecurityPin() == pin) {
      return user;
    }
    throw new Exception("USER.UNAUTHORIZED");
  }

  public void register(User user) {
    this.uRepository.create(user);
  }
}
