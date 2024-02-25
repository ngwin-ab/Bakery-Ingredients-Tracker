package com.anba.bakery.repository;

import com.anba.bakery.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    List<Category> findByNameContaining(String str);
}
