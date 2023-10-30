package com.example.demo.controller;

import com.example.demo.entity.Meal;
import com.example.demo.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meal")
public class MealController {
    @Autowired
    private MealService mealService;
    @PostMapping("/add-meal")
    public ResponseEntity createMeal(@RequestBody Meal meal){
        mealService.createMeal(meal);
        return ResponseEntity.ok("Meal added!");
    }

    @DeleteMapping("delete-meal/{id}")
    public ResponseEntity deleteMeal(Long id){
        boolean mealDeleted = mealService.deleteMeal(id);
        if (mealDeleted){
            return ResponseEntity.ok("Meal deleted!");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/get-meal/{id}")
    public ResponseEntity getMeal(Long id){
        Optional<Meal> mealOpt = mealService.getMeal(id);
        if (mealOpt.isPresent()){
            return ResponseEntity.ok(mealOpt.get().toString()) ;
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update-meal/{id}")
    public ResponseEntity<Optional<Meal>> updateMeal(Long id,Meal meal){
        Optional<Meal> mealOpt = mealService.updateMeal(meal,id);
        if(mealOpt.isPresent()){
            return ResponseEntity.ok(mealOpt);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/winter-meals")
    public ResponseEntity<List<Meal>> getSummerMeals(){
        return ResponseEntity.ok(mealService.getWinterMeals());
    }
}
