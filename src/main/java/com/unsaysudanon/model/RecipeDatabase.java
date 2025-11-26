package com.unsaysudanon.model;

import java.util.List;
import java.util.Set;

public interface RecipeDatabase {
    List<Recipe> loadRecipes();

    Set<String> getAllUniqueIngredients();
}
