package repository;

import model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
//    List<Ingredient> findByCategoryId(Integer categoryId);
//
//    @Transactional
//    void deleteByCategoryId(Integer categoryId);
}
