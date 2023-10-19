package com.csse.eticket.serviceImpl;

import com.csse.eticket.dao.TransactionDao;
import com.csse.eticket.model.Transaction;
import com.csse.eticket.repository.TransactionRepository;
import com.csse.eticket.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public TransactionDao AddTransaction(float amount, String type) {

        LocalDateTime date = LocalDateTime.now();

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setType(type);
        transactionRepository.save(transaction);
        return modelMapper.map(transaction, TransactionDao.class);
    }

    @Override
    public List<TransactionDao> getAllTransactions() {
        List<Transaction> transactionList = transactionRepository.getAll();
        List<TransactionDao> transactionDaoList = new ArrayList<>();
        for(Transaction transaction : transactionList){
            transactionDaoList.add(modelMapper.map(transaction, TransactionDao.class));
        }
        return transactionDaoList;
    }

}
