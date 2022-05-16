package com.atm.app.usecase;

import com.atm.app.domain.repository.*;

import java.util.List;

import com.atm.app.domain.entity.*;

public class TransactionUsecase {
  private TransactionRepository tRepository;
  private UserRepository uRepository;

  private static final Object LOCK = new Object();

  public TransactionUsecase(TransactionRepository tRepository,
      UserRepository uRepository) {
    this.tRepository = tRepository;
    this.uRepository = uRepository;
  }

  public void Deposit(String accountFrom, long balance) throws Exception {
    Transaction t = new Transaction();
    t.setAccountFrom(accountFrom);
    t.setAccountTo(null);
    t.setBalance(balance);
    t.setType(TransactionType.DEPOSIT);

    synchronized (LOCK) {
      User user = this.uRepository.getByAccountNumber(accountFrom);
      this.tRepository.create(t);
      long newBalance = user.getBalance() + balance;
      this.uRepository.updateBalance(accountFrom, newBalance);
    }
  }

  public void Withdraw(String accountFrom, long balance) throws Exception {
    Transaction t = new Transaction();
    t.setAccountFrom(accountFrom);
    t.setAccountTo(null);
    t.setBalance(balance);
    t.setType(TransactionType.WITHDRAW);

    synchronized (LOCK) {
      User user = this.uRepository.getByAccountNumber(accountFrom);
      this.tRepository.create(t);
      long newBalance = user.getBalance() - balance;
      this.uRepository.updateBalance(accountFrom, newBalance);
    }
  }

  public void Transfer(String accountFrom, String accountTo, long balance) throws Exception {
    Transaction t = new Transaction();
    t.setAccountFrom(accountFrom);
    t.setAccountTo(accountTo);
    t.setBalance(balance);
    t.setType(TransactionType.TRANSFER);

    synchronized (LOCK) {
      User userTo = this.uRepository.getByAccountNumber(accountTo);
      User userFrom = this.uRepository.getByAccountNumber(accountFrom);

      this.tRepository.create(t);

      long newBalanceUserTo = userTo.getBalance() + balance;
      this.uRepository.updateBalance(accountTo, newBalanceUserTo);

      long newBalanceUserFrom = userFrom.getBalance() - balance;
      this.uRepository.updateBalance(accountFrom, newBalanceUserFrom);
    }
  }

  public List<Transaction> GetHistory(String account) throws Exception {
    this.uRepository.getByAccountNumber(account);
    List<Transaction> lTransactions = this.tRepository.findByAccount(account);
    return lTransactions;
  }
}
