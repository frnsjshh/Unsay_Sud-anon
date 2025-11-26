package com.unsaysudanon.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Logic class for finding matching recipes based on user ingredients.
 * Implements scoring and filtering.
 */
public class RecipeFinder {

    /**
     * Finds recipes that match the user's ingredients.
     *
     * @param userIngredients List of ingredients the user has.
     * @param allRecipes      List of all available recipes.
     * @param categoryFilter  Optional category to filter by (can be null or empty).
     * @return List of RecipeMatch objects, sorted by match percentage descending.
     */
    public List<RecipeMatch> findMatches(List<String> userIngredients, List<Recipe> allRecipes, String categoryFilter) {
        List<RecipeMatch> matches = new ArrayList<>();

        // Normalize user ingredients (lowercase, trim)
        List<String> normalizedUserIngredients = userIngredients.stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());

        for (Recipe recipe : allRecipes) {
            // 1. Filter by Category (if provided)
            if (categoryFilter != null && !categoryFilter.isEmpty() && !categoryFilter.equals("All")) {
                if (recipe.getCategory() == null || !recipe.getCategory().equalsIgnoreCase(categoryFilter)) {
                    continue; // Skip if category doesn't match
                }
            }

            // Normalize recipe ingredients
            List<String> recipeIngredients = recipe.getIngredients().stream()
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .collect(Collectors.toList());

            // 2. Calculate Match Score
            // Count how many of the RECIPE's ingredients the USER has.
            // Note: The requirement is "Number of Matched Ingredients / Total Ingredients
            // in Recipe".
            long matchCount = recipeIngredients.stream()
                    .filter(normalizedUserIngredients::contains)
                    .count();

            double totalIngredients = recipeIngredients.size();
            double matchPercentage = 0.0;
            if (totalIngredients > 0) {
                matchPercentage = (matchCount / totalIngredients) * 100.0;
            }

            int missingCount = (int) (totalIngredients - matchCount);

            // 3. Threshold Check (> 20%)
            if (matchPercentage > 20.0) {
                matches.add(new RecipeMatch(recipe, matchPercentage, missingCount));
            }
        }

        // 4. Sort by Score (Descending)
        Collections.sort(matches);

        return matches;
    }
}
