package com.chumlung.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chumlung.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
