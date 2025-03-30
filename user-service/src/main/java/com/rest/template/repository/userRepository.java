package com.rest.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.template.entity.User;

public interface userRepository extends JpaRepository<User , Integer>{

	
}
