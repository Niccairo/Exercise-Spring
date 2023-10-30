package com.example.demo.service;
import com.example.demo.entity.Meal;
import com.example.demo.repository.MealRepository;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    private MealRepository mealRepository;
    private Double MAX_WINTER_TEMP = 15.0;
    @Autowired
    public MealService(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }
    public void createMeal(Meal meal){
        mealRepository.save(meal);
    }
    public boolean deleteMeal(Long id){
        if(mealRepository.existsById(id)){
            mealRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    public Optional<Meal> getMeal(Long id){
        return mealRepository.findById(id);
    }
    public Optional<Meal> updateMeal(Meal meal, Long id){
        Optional<Meal> mealOpt = mealRepository.findById(id);
        if(mealOpt.isPresent()){
            Meal existingMeal = mealOpt.get();
            existingMeal.setName(meal.getName());
            existingMeal.setDescription(meal.getDescription());
            existingMeal.setPrice(meal.getPrice());
            existingMeal.setIngredients(meal.getIngredients());
            mealRepository.save(existingMeal);
            return Optional.of(existingMeal);
        }
        return Optional.empty();
    }
    public List<Meal> getWinterMeals(){
       double currentTemp = getCurrentTemparatureInCentigrade();
       if(currentTemp > MAX_WINTER_TEMP){
           return new ArrayList<>();
       }
        return mealRepository.findByIsSummerMeal(false);
    }
    public Double getCurrentTemparatureInCentigrade(){
        try {
            JSONObject response = Unirest.get("https://api.open-meteo.com/v1/forecast?latitude=59.3294&longitude=18.0687&current_weather=true").asJson().getBody().getObject();
            return  response.getJSONObject("current_weather").getDouble("temperature");
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}