package repository;

import jakarta.transaction.Transactional;
import model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

    List<Ingredient> findByCategoryId(Integer categoryId);

    @Transactional
    void deleteByCategoryId(Integer categoryId);
}
