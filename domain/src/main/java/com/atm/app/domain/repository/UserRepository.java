package com.atm.app.domain.repository;

import java.util.List;

import com.atm.app.domain.entity.User;

public interface UserRepository {
  User create(User user);

  List<User> find();

  User getByAccountNumber(String aNumber) throws Exception;

  void updateBalance(String aNumber, long balance);
}
