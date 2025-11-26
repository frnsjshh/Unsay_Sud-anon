package com.unsaysudanon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeFinder {

    public List<Recipe> findMatches(List<String> userIngredients, List<Recipe> allRecipes) {
        List<Recipe> matches = new ArrayList<>();
        List<String> normalizedUserIngredients = userIngredients.stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());

        for (Recipe recipe : allRecipes) {
            List<String> recipeIngredients = recipe.getIngredients().stream()
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .collect(Collectors.toList());

            long matchCount = normalizedUserIngredients.stream()
                    .filter(recipeIngredients::contains)
                    .count();

            // Logic: If (Recipe.ingredients contains All or Most User.ingredients)
            // All: matchCount == normalizedUserIngredients.size()
            // Most: matchCount > normalizedUserIngredients.size() / 2.0

            if (normalizedUserIngredients.isEmpty())
                continue;

            if (matchCount == normalizedUserIngredients.size() || matchCount > normalizedUserIngredients.size() / 2.0) {
                matches.add(recipe);
            }
        }
        return matches;
    }
}
