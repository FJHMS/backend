package com.itf.schulung.springboot.fullstack.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itf.schulung.springboot.fullstack.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findAll();

}
