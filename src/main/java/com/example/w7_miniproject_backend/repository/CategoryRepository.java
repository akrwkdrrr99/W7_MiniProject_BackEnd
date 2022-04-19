package com.example.w7_miniproject_backend.repository;


import com.example.w7_miniproject_backend.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>  {

}
