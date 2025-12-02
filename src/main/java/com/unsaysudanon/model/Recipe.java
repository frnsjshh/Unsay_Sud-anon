package com.unsaysudanon.model;

import java.util.List;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private List<String> instructions;

    public Recipe() {
    }

    public Recipe(String name, List<String> ingredients, List<String> instructions) {
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    // OOP PRINCIPLE: ENCAPSULATION
    // Fields are private to hide internal state and restrict direct access.
    // Access is controlled via public getters and setters.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
