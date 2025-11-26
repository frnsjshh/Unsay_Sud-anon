package com.unsaysudanon.model;

/**
 * DTO to hold the result of a recipe match.
 * Contains the recipe, the match score (percentage), and missing ingredient
 * count.
 */
public class RecipeMatch implements Comparable<RecipeMatch> {
    private final Recipe recipe;
    private final double matchPercentage;
    private final int missingIngredientCount;

    public RecipeMatch(Recipe recipe, double matchPercentage, int missingIngredientCount) {
        this.recipe = recipe;
        this.matchPercentage = matchPercentage;
        this.missingIngredientCount = missingIngredientCount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public int getMissingIngredientCount() {
        return missingIngredientCount;
    }

    /**
     * Sorts by match percentage in descending order.
     */
    @Override
    public int compareTo(RecipeMatch other) {
        return Double.compare(other.matchPercentage, this.matchPercentage);
    }
}
