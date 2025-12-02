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

@WebServlet(name = "RecipeServlet", urlPatterns = { "/recipes" })
// OOP PRINCIPLE: INHERITANCE
// RecipeServlet inherits properties and methods from HttpServlet,
// allowing it to function as a web component.
public class RecipeServlet extends HttpServlet {

    private RecipeDatabase recipeDatabase;
    private RecipeFinder recipeFinder;
    private List<Recipe> allRecipes;

    // OOP PRINCIPLE: POLYMORPHISM (Method Overriding)
    // We override the init() method to provide custom initialization logic.
    @Override
    public void init() throws ServletException {
        super.init();
        recipeDatabase = new JsonRecipeDatabase();
        recipeFinder = new RecipeFinder();
        allRecipes = recipeDatabase.loadRecipes();
    }

    // OOP PRINCIPLE: POLYMORPHISM (Method Overriding)
    // We override doPost() to handle HTTP POST requests specifically for this
    // servlet.
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
                userIngredients.add(ingredient.trim());
            }
            resp.sendRedirect("index.jsp");
        } else if ("search".equals(action)) {
            List<Recipe> matches = recipeFinder.findMatches(userIngredients, allRecipes);
            session.setAttribute("matches", matches);
            resp.sendRedirect("results.jsp");
        } else if ("clear".equals(action)) {
            session.removeAttribute("userIngredients");
            session.removeAttribute("matches");
            resp.sendRedirect("index.jsp");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    // OOP PRINCIPLE: POLYMORPHISM (Method Overriding)
    // We override doGet() to handle HTTP GET requests specifically for this
    // servlet.
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
