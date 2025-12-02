# Unsay Sud-anon 🍲

**Unsay Sud-anon** (Cebuano for "What's the dish?") is a dynamic web application that helps users find Filipino recipes based on the ingredients they have at home.

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
│   │   │   │   └── RecipeServlet.java      # Main Controller (Handles HTTP requests)
│   │   │   └── model/
│   │   │       ├── Recipe.java             # Data Model (POJO)
│   │   │       ├── RecipeDatabase.java     # Interface for data loading
│   │   │       ├── JsonRecipeDatabase.java # Implementation (Loads recipes.json)
│   │   │       └── RecipeFinder.java       # Logic for matching ingredients
│   │   ├── resources/
│   │   │   └── recipes.json                # Database of Filipino recipes
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml                 # Deployment Descriptor
│   │       ├── index.jsp                   # Input Page (Add ingredients)
│   │       ├── results.jsp                 # Results Page (View matches)
│   │       └── details.jsp                 # Recipe Details Page
└── pom.xml                                 # Maven Dependencies
```

## 🔄 Data Flow

The application follows the **Model-View-Controller (MVC)** pattern. For a detailed explanation of the architecture and logic, please refer to [data_flow.md](data_flow.md).

1.  **Initialization**: On startup, `RecipeServlet` initializes `JsonRecipeDatabase`, which reads and parses `recipes.json` into a list of `Recipe` objects.
2.  **Input (View -> Controller)**:
    *   User enters an ingredient on `index.jsp`.
    *   Form submits to `RecipeServlet` (`action=add`).
    *   Servlet adds the ingredient to the User's **Session**.
3.  **Processing (Controller -> Model)**:
    *   User clicks "Find Recipes".
    *   Servlet calls `RecipeFinder.findMatches()`.
    *   **Logic**: The system checks if a recipe contains **All or Most (>= 50%)** of the user's ingredients.
4.  **Output (Controller -> View)**:
    *   Matching recipes are stored in the Session.
    *   User is redirected to `results.jsp` to see the cards.
    *   Clicking a recipe forwards to `details.jsp` to show full instructions.

## 🚀 Setup & Installation

### Prerequisites
*   **Java Development Kit (JDK)**: Version 17 or higher.
*   **Apache Maven**: For building the project.
*   **Servlet Container**: Apache Tomcat 10+ (Must support Jakarta EE 6.0+).

### How to Run

1.  **Clone the repository** (or download the source).
2.  **Build the project**:
    Open a terminal in the project root and run:
    ```bash
    mvn clean package
    ```
    This will generate a `.war` file in the `target/` directory (e.g., `Unsay_Sud-anon-1.0-SNAPSHOT.war`).

3.  **Deploy to Tomcat**:
    *   Copy the generated `.war` file to your Tomcat's `webapps` folder.
    *   Start (or restart) your Tomcat server.

4.  **Access the App**:
    Open your web browser and navigate to:
    ```
    http://localhost:8080/Unsay_Sud-anon-1.0-SNAPSHOT/
    ```
    *(Note: The URL depends on the name of your WAR file and Tomcat configuration).*

## 📝 Usage
1.  **Add Ingredients**: Type an ingredient (e.g., "Pork", "Vinegar") and press Enter or click "Add".
2.  **Find Matches**: Once you have added at least 3 ingredients, click "Find Recipes".
3.  **Cook**: Click "View Recipe" to see how to make the dish!
