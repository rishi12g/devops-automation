package com.codewithkaran.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithkaran.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	

}
