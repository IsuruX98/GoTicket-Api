package com.csse.eticket.service;


import com.csse.eticket.dao.TransactionDao;
import com.csse.eticket.model.Transaction;

import java.util.List;

public interface TransactionService {

    TransactionDao AddTransaction(float amount, String type);

    List<TransactionDao> getAllTransactions();


}
