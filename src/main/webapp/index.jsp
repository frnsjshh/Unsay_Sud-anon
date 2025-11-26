<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unsay Sud-anon</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Inter', sans-serif; }
    </style>
</head>
<body class="bg-gradient-to-br from-[#FAFAF8] to-[#F5F5F3] min-h-screen flex items-center justify-center p-4">

    <div class="w-full max-w-4xl bg-white rounded-3xl shadow-xl overflow-hidden">
        <!-- Header -->
        <div class="bg-gradient-to-r from-[#D17557] via-[#E8956F] to-[#D17557] p-8 text-center">
            <h1 class="text-4xl font-bold text-white mb-2">Unsay Sud-anon</h1>
            <p class="text-white/90">What's for dinner? Enter your ingredients below.</p>
        </div>

        <div class="p-8 md:p-12">
            <!-- Input Form -->
            <form action="recipes" method="post" class="flex gap-4 mb-8">
                <input type="hidden" name="action" value="add">
                <input type="text" name="ingredient" placeholder="e.g. Pork, Vinegar, Garlic" 
                       class="flex-1 px-6 py-4 text-lg border-2 border-gray-200 rounded-xl focus:outline-none focus:border-[#D17557] focus:ring-1 focus:ring-[#D17557] transition-all"
                       autofocus>
                <button type="submit" class="bg-[#141413] text-white px-8 py-4 rounded-xl font-semibold hover:bg-gray-800 transition-colors">
                    Add ingredient
                </button>
            </form>

            <!-- Ingredients List -->
            <div class="mb-12">
                <h2 class="text-xl font-semibold text-[#141413] mb-4">Your Ingredients:</h2>
                <div class="flex flex-wrap gap-3">
                    <c:choose>
                        <c:when test="${empty sessionScope.userIngredients}">
                            <p class="text-gray-400 italic">No ingredients added yet.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${sessionScope.userIngredients}" var="ing">
                                <div class="bg-white border border-gray-200 shadow-sm px-4 py-2 rounded-full flex items-center gap-2 text-[#475467]">
                                    <span>${ing}</span>
                                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-green-500" viewBox="0 0 20 20" fill="currentColor">
                                        <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                                    </svg>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <!-- Action Buttons -->
            <div class="flex flex-col gap-4">
                <c:set var="ingCount" value="${empty sessionScope.userIngredients ? 0 : sessionScope.userIngredients.size()}" />
                
                <form action="recipes" method="post">
                    <input type="hidden" name="action" value="search">
                    <button type="submit" 
                            class="w-full bg-gradient-to-r from-[#D17557] to-[#E8956F] text-white text-xl font-bold py-5 rounded-xl shadow-lg hover:shadow-xl transform hover:-translate-y-0.5 transition-all disabled:opacity-50 disabled:cursor-not-allowed"
                            ${ingCount < 3 ? 'disabled title="Add at least 3 ingredients"' : ''}>
                        Find Recipes
                    </button>
                </form>
                
                <c:if test="${ingCount > 0}">
                    <form action="recipes" method="post" class="text-center">
                        <input type="hidden" name="action" value="clear">
                        <button type="submit" class="text-[#475467] hover:text-[#D17557] underline text-sm">
                            Clear all ingredients
                        </button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>

</body>
</html>