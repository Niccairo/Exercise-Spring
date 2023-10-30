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
            ingredientOpt.get().setName(ingredient.getName());
            ingredientOpt.get().setGlutenFree(ingredient.isGlutenFree());
            ingredientOpt.get().setVegan(ingredient.isVegan());
            ingredientOpt.get().setVegetarian(ingredient.isVegetarian());
            ingredientOpt.get().setLactoseFree(ingredient.isLactoseFree());
            ingredientRepository.save(ingredient); //controlla se funziona
            return Optional.of(ingredient);
        }else {
            return Optional.empty();
        }
    }
}