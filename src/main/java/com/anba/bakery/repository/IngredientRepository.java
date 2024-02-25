package com.anba.bakery.repository;

import javax.transaction.Transactional;
import com.anba.bakery.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    List<Ingredient> findByNameContaining(String str);

    List<Ingredient> findByCategoryId(Long categoryId);

    @Transactional
    void deleteByCategoryId(Long categoryId);
}
