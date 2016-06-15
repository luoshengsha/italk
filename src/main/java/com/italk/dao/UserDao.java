package com.italk.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.italk.bean.User;

@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

}
