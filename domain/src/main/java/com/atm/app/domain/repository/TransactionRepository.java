package com.atm.app.domain.repository;

import java.util.List;

import com.atm.app.domain.entity.Transaction;

public interface TransactionRepository {
  Transaction create(Transaction transaction);

  List<Transaction> find();

  List<Transaction> findByAccount(String account);

  Transaction getByID(int id) throws Exception;
}
