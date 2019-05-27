package com.itf.schulung.springboot.fullstack.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itf.schulung.springboot.fullstack.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{
	
	List<Account> findAll();

}
