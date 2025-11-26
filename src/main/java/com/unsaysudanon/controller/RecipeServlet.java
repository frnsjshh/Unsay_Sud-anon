package com.unsaysudanon.controller;

import com.unsaysudanon.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@WebServlet(name = "RecipeServlet", urlPatterns = { "/recipes" })
public class RecipeServlet extends HttpServlet {

    private RecipeDatabase recipeDatabase;
    private RecipeFinder recipeFinder;
    private List<Recipe> allRecipes;

    @Override
    public void init() throws ServletException {
        super.init();
        recipeDatabase = new JsonRecipeDatabase();
        recipeFinder = new RecipeFinder();
        allRecipes = recipeDatabase.loadRecipes();

        // Load unique ingredients for autosuggest
        Set<String> allIngredients = recipeDatabase.getAllUniqueIngredients();
        getServletContext().setAttribute("allIngredients", allIngredients);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        List<String> userIngredients = (List<String>) session.getAttribute("userIngredients");
        if (userIngredients == null) {
            userIngredients = new ArrayList<>();
            session.setAttribute("userIngredients", userIngredients);
        }

        if ("add".equals(action)) {
            String ingredient = req.getParameter("ingredient");
            if (ingredient != null && !ingredient.trim().isEmpty()) {
                // Prevent duplicates
                String trimmed = ingredient.trim();
                boolean exists = userIngredients.stream().anyMatch(i -> i.equalsIgnoreCase(trimmed));
                if (!exists) {
                    userIngredients.add(trimmed);
                }
            }
            resp.sendRedirect("index.jsp");
        } else if ("search".equals(action)) {
            String category = req.getParameter("category");
            List<RecipeMatch> matches = recipeFinder.findMatches(userIngredients, allRecipes, category);
            session.setAttribute("matches", matches);
            session.setAttribute("selectedCategory", category);
            resp.sendRedirect("results.jsp");
        } else if ("clear".equals(action)) {
            session.removeAttribute("userIngredients");
            session.removeAttribute("matches");
            session.removeAttribute("selectedCategory");
            resp.sendRedirect("index.jsp");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("view".equals(action)) {
            String recipeName = req.getParameter("name");
            Optional<Recipe> recipe = allRecipes.stream()
                    .filter(r -> r.getName().equals(recipeName))
                    .findFirst();
            if (recipe.isPresent()) {
                req.setAttribute("recipe", recipe.get());
                req.getRequestDispatcher("details.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("results.jsp");
            }
        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}
