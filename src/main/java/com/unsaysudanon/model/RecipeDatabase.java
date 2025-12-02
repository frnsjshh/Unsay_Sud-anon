package com.unsaysudanon.model;

import java.util.List;

// OOP PRINCIPLE: ABSTRACTION
// This interface defines the contract for loading recipes without specifying the implementation details.
// It hides the complexity of where the data comes from (JSON, Database, API, etc.).
public interface RecipeDatabase {
    List<Recipe> loadRecipes();
}
