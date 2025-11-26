# Unsay Sud-anon 🍲

**Unsay Sud-anon** (Cebuano for "What's the dish?") is a dynamic web application that helps users find Filipino recipes based on the ingredients they have at home.

## 🌟 New Features
*   **Smart Scoring**: Recipes are ranked by match percentage. See exactly how close you are to a full dish!
*   **Autosuggest**: Start typing an ingredient, and the app will suggest available options from the database.
*   **Category Filtering**: Narrow down your search by Meat, Seafood, Vegetable, Poultry, or Dessert.
*   **"See More"**: High-scoring matches are shown first; lower scores are tucked away for a cleaner view.

## 🛠️ Tech Stack

*   **Backend**: Java (JDK 17+), Jakarta EE Servlets
*   **Frontend**: JavaServer Pages (JSP), JSTL
*   **Styling**: Tailwind CSS (via CDN)
*   **Data Format**: JSON (Parsed using Google Gson)
*   **Build Tool**: Apache Maven

## 📂 Project Structure

```
Unsay_Sud-anon/
├── src/
│   ├── main/
│   │   ├── java/com/unsaysudanon/
│   │   │   ├── controller/
│   │   │   │   └── RecipeServlet.java      # Main Controller
│   │   │   └── model/
│   │   │       ├── Recipe.java             # Data Model
│   │   │       ├── RecipeMatch.java        # DTO for Scored Matches
│   │   │       ├── RecipeDatabase.java     # Interface
│   │   │       ├── JsonRecipeDatabase.java # Data Loader
│   │   │       └── RecipeFinder.java       # Matching & Scoring Logic
│   │   ├── resources/
│   │   │   └── recipes.json                # Database of 40+ Filipino recipes
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml                 # Deployment Descriptor
│   │       ├── index.jsp                   # Input Page (Autosuggest + Filter)
│   │       ├── results.jsp                 # Results Page (Scored Cards)
│   │       └── details.jsp                 # Recipe Details Page
└── pom.xml                                 # Maven Dependencies
```

## 🚀 Setup & Installation

1.  **Clone & Build**:
    ```bash
    mvn clean package
    ```
2.  **Deploy**: Copy the `.war` file to your Tomcat `webapps` folder.
3.  **Run**: Start Tomcat and visit `http://localhost:8080/Unsay_Sud-anon-1.0-SNAPSHOT/`.

## 📝 Usage
1.  **Filter (Optional)**: Select a category (e.g., "Meat").
2.  **Add Ingredients**: Type to see suggestions (e.g., "Pork", "Soy Sauce").
3.  **Find Matches**: Click "Find Recipes" to see your scored results.
