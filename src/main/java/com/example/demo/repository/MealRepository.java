package com.example.demo.repository;

import com.example.demo.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal,Long> {
List<Meal> findByIsSummerMeal(boolean isSummerMeal);
}
