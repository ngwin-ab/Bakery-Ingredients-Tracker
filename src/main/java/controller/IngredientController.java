package controller;

import model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.CategoryRepository;
import repository.IngredientRepository;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class IngredientController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/categories/{categoryId}/ingredients")
    public ResponseEntity<List<Ingredient>> getAllIngredientsByCategoryId(@PathVariable(value = "categoryId") Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NoSuchElementException("Not found Category with id = " + categoryId);
        }

        List<Ingredient> ingredients = ingredientRepository.findByCategoryId(categoryId);
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable(value = "id") Integer id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found Comment with id = " + id));

        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @PostMapping("/categories/{categoryId}/ingredients")
    public ResponseEntity<Ingredient> createIngredient(@PathVariable(value = "categoryId") Integer categoryId,
                                                 @RequestBody Ingredient ingredientRequest) {
        Ingredient ingredient = categoryRepository.findById(categoryId).map(category -> {
            ingredientRequest.setCategory(category.getName());
            return ingredientRepository.save(ingredientRequest);
        }).orElseThrow(() -> new NoSuchElementException("Not found Category with id = " + categoryId));

        return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") Integer id) {
        ingredientRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/categories/{categoryId}/ingredients")
    public ResponseEntity<List<Ingredient>> deleteAllIngredientssOfCategory(@PathVariable(value = "categoryId") Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NoSuchElementException("Not found Category with id = " + categoryId);
        }

        ingredientRepository.deleteByCategoryId(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}