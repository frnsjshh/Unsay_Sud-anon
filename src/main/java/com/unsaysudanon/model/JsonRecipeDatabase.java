package com.unsaysudanon.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class JsonRecipeDatabase implements RecipeDatabase {

    @Override
    public List<Recipe> loadRecipes() {
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("recipes.json")))) {
            Gson gson = new Gson();
            Type recipeListType = new TypeToken<ArrayList<Recipe>>() {
            }.getType();
            return gson.fromJson(reader, recipeListType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Set<String> getAllUniqueIngredients() {
        Set<String> ingredients = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        List<Recipe> recipes = loadRecipes();
        if (recipes != null) {
            for (Recipe recipe : recipes) {
                if (recipe.getIngredients() != null) {
                    for (String ing : recipe.getIngredients()) {
                        ingredients.add(ing.trim());
                    }
                }
            }
        }
        return ingredients;
    }
}
