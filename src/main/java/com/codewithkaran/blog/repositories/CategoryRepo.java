package com.codewithkaran.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithkaran.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
