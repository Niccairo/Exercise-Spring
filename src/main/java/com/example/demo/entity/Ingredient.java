package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "vegetarian")
    private boolean isVegetarian;
    @Column(name = "vegan")
    private boolean isVegan;
    @Column(name = "gluten_free")
    private boolean isGlutenFree;
    @Column(name = "lactose_free")
    private boolean isLactoseFree;

    @JsonBackReference
    @ManyToOne
    private Meal meal;
    public Ingredient(){
    }

    public Ingredient(Long id, String name, boolean isVegetarian, boolean isVegan, boolean isGlutenFree, boolean isLactoseFree) {
        this.id = id;
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.isGlutenFree = isGlutenFree;
        this.isLactoseFree = isLactoseFree;
    }
    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }

    public boolean isLactoseFree() {
        return isLactoseFree;
    }

    public void setLactoseFree(boolean lactoseFree) {
        isLactoseFree = lactoseFree;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isVegetarian=" + isVegetarian +
                ", isVegan=" + isVegan +
                ", isGlutenFree=" + isGlutenFree +
                ", isLactoseFree=" + isLactoseFree +
                '}';
    }
}