package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService){
        this.ingredientService = ingredientService;
    }

    @PostMapping("/add-ingredient")
    public ResponseEntity   createIngredient(@RequestBody Ingredient ingredient){
        ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok("Ingredient added");
    }

    @DeleteMapping("/delete-ingredient/{id}")
    public ResponseEntity deleteIngredient(@PathVariable Long id){
        boolean deletedIngredient = ingredientService.deleteIngredient(id);
        if(deletedIngredient) {
            return ResponseEntity.ok("Ingredient deleted!");
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get-ingredient/{id}")
    public ResponseEntity getIngredient(@PathVariable Long id){
        Optional<Ingredient> opt = ingredientService.getIngredient(id);
        if(opt.isPresent()) {
            return ResponseEntity.ok(opt.get().toString());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update-ingredient/{id}")
    public ResponseEntity<Optional<Ingredient>> updateIngredient(@RequestBody Ingredient ingredient, @PathVariable Long id){
        Optional<Ingredient> ing = ingredientService.updateIngredient(ingredient,id);
        if(ing.isPresent()) {
            return ResponseEntity.ok(ing);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
