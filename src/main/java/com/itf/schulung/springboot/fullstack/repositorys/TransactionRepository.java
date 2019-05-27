package com.itf.schulung.springboot.fullstack.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itf.schulung.springboot.fullstack.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
	
	List<Transaction> findAll();

}