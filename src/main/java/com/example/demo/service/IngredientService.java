package com.example.demo.service;

import com.example.demo.repository.IngredientRepository;
import com.example.demo.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;
    public IngredientService(IngredientRepository ingredientDao){
        this.ingredientRepository = ingredientDao;
    }
    public void createIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }
    public boolean deleteIngredient(Long id){
        if(ingredientRepository.existsById(id)){
            ingredientRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    public Optional<Ingredient> getIngredient(Long id){
            return ingredientRepository.findById(id);
    }
    public Optional<Ingredient> updateIngredient(Ingredient ingredient, Long id){
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(id);
        if(ingredientOpt.isPresent()){
            Ingredient existingIngredient = ingredientOpt.get();
            existingIngredient.setName(ingredient.getName());
            existingIngredient.setGlutenFree(ingredient.isGlutenFree());
            existingIngredient.setVegan(ingredient.isVegan());
            existingIngredient.setVegetarian(ingredient.isVegetarian());
            existingIngredient.setLactoseFree(ingredient.isLactoseFree());
            ingredientRepository.save(existingIngredient); //controlla se funziona
            return Optional.of(existingIngredient);
        }else {
            return Optional.empty();
        }
    }
}