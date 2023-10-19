package com.csse.eticket.repository;

import com.csse.eticket.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT d FROM Transaction d")
    List<Transaction> getAll();
}
