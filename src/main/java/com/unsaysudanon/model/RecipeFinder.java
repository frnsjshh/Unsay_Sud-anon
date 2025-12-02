package com.unsaysudanon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeFinder {

    public List<Recipe> findMatches(List<String> userIngredients, List<Recipe> allRecipes) {
        List<Recipe> matches = new ArrayList<>();

        //Converting to stream to do opeartions without modifying the original list
        //Converting into a standard form
        List<String> normalizedUserIngredients = userIngredients.stream()
                .map(String::toLowerCase) // Convert each ingredient to lower case
                .map(String::trim)  // Remove empty strings
                .collect(Collectors.toList()); // Collect the results back into a List

        for (Recipe recipe : allRecipes) {
            //Converting to stream to do operations without modifying the original list
            List<String> recipeIngredients = recipe.getIngredients().stream()
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .collect(Collectors.toList());

            //counts how many userIngredients are in recipeIngredients
            //removes the ingredients that are not in the recipeIngredients
            long matchCount = normalizedUserIngredients.stream()
                    .filter(recipeIngredients::contains)
                    .count();



            if (normalizedUserIngredients.isEmpty())
                continue; //skips one iteration

            // Logic: If (Recipe.ingredients contains All or Most User.ingredients) then add to matches
            if (matchCount == normalizedUserIngredients.size() || matchCount > normalizedUserIngredients.size() / 2.0) {
                matches.add(recipe);
            }
        }
        return matches;
    }
}
