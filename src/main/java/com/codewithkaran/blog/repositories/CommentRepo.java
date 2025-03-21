package com.codewithkaran.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithkaran.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
