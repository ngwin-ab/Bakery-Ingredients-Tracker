package com.anba.bakery.controller;

import com.anba.bakery.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.anba.bakery.repository.CategoryRepository;
import com.anba.bakery.repository.IngredientRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class IngredientController {
    private final CategoryRepository categoryRepository;

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientController(CategoryRepository categoryRepository, IngredientRepository ingredientRepository) {
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/categories/{categoryId}/ingredients")
    public ResponseEntity<List<Ingredient>> getAllIngredientsByCategoryId(@PathVariable(value = "categoryId") Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NoSuchElementException("Category with id = " + categoryId + " not found!");
        }

        List<Ingredient> ingredients = ingredientRepository.findByCategoryId(categoryId);
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable(value = "id") long id) {
        Optional<Ingredient> ingredientData = ingredientRepository.findById(id);

        if (ingredientData.isPresent()) {
            return new ResponseEntity<>(ingredientData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/categories/{categoryId}/ingredients")
    public ResponseEntity<Ingredient> createIngredient(@PathVariable(value = "categoryId") Long categoryId,
                                                       @RequestBody Ingredient ingredientRequest) {
        Ingredient ingredient = categoryRepository.findById(categoryId).map(category -> {
            ingredientRequest.setCategory(category);
            return ingredientRepository.save(ingredientRequest);
        }).orElseThrow(() -> new NoSuchElementException("Not found Category with id = " + categoryId));

        return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredientRequest) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ingredient " + id + "not found"));

        ingredient.setUnit(ingredientRequest.getUnit());
        ingredient.setName(ingredientRequest.getName());
        ingredient.setExpirationDate(ingredientRequest.getExpirationDate());

        return new ResponseEntity<>(ingredientRepository.save(ingredient), HttpStatus.OK);
    }

    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        ingredientRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/categories/{categoryId}/ingredients")
    public ResponseEntity<List<Ingredient>> deleteAllIngredientssOfCategory(@PathVariable(value = "categoryId") Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NoSuchElementException("Not found Category with id = " + categoryId);
        }

        ingredientRepository.deleteByCategoryId(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}