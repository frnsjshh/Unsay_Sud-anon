<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Results - Unsay Sud-anon</title>
                <script src="https://cdn.tailwindcss.com"></script>
                <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
                    rel="stylesheet">
                <style>
                    body {
                        font-family: 'Inter', sans-serif;
                    }

                    .hidden-match {
                        display: none;
                    }
                </style>
                <script>
                    function toggleLowMatches() {
                        const hiddenMatches = document.querySelectorAll('.hidden-match');
                        const btn = document.getElementById('seeMoreBtn');
                        hiddenMatches.forEach(el => {
                            el.style.display = el.style.display === 'none' || el.style.display === '' ? 'block' : 'none';
                        });
                        btn.textContent = btn.textContent === 'Show Lower Matches' ? 'Hide Lower Matches' : 'Show Lower Matches';
                    }
                </script>
            </head>

            <body
                class="bg-gradient-to-br from-[#FAFAF8] to-[#F5F5F3] min-h-screen flex items-center justify-center p-4">

                <div class="w-full max-w-4xl bg-white rounded-3xl shadow-xl overflow-hidden flex flex-col max-h-[90vh]">
                    <!-- Header -->
                    <div class="bg-gradient-to-r from-[#D17557] via-[#E8956F] to-[#D17557] p-8 text-center shrink-0">
                        <h1 class="text-3xl font-bold text-white mb-1">Recipe Matches</h1>
                        <p class="text-white/90">
                            <c:if
                                test="${not empty sessionScope.selectedCategory and sessionScope.selectedCategory ne 'All'}">
                                Category: ${sessionScope.selectedCategory} |
                            </c:if>
                            Based on your ingredients
                        </p>
                    </div>

                    <div class="p-8 md:p-12 overflow-y-auto flex-1">
                        <c:choose>
                            <c:when test="${empty sessionScope.matches}">
                                <div class="text-center py-12">
                                    <div class="text-6xl mb-4">🍲</div>
                                    <h2 class="text-2xl font-bold text-[#141413] mb-2">No matches found</h2>
                                    <p class="text-[#475467] mb-8">Try adding more ingredients or changing the category.
                                    </p>
                                    <a href="index.jsp"
                                        class="inline-block bg-[#141413] text-white px-8 py-3 rounded-xl font-semibold hover:bg-gray-800 transition-colors">
                                        Go Back
                                    </a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                                    <c:set var="hasLowMatches" value="false" />
                                    <c:forEach items="${sessionScope.matches}" var="match" varStatus="status">
                                        <!-- Show top 6 matches by default, hide others -->
                                        <c:set var="isHidden" value="${status.count > 6}" />
                                        <c:if test="${isHidden}">
                                            <c:set var="hasLowMatches" value="true" />
                                        </c:if>

                                        <div
                                            class="bg-white border-2 border-gray-100 rounded-2xl p-6 hover:border-[#D17557] transition-colors shadow-sm hover:shadow-md group relative ${isHidden ? 'hidden-match' : ''}">
                                            <!-- Match Badge -->
                                            <div
                                                class="absolute top-4 right-4 px-3 py-1 rounded-full text-xs font-bold ${match.matchPercentage >= 80 ? 'bg-green-100 text-green-700' : (match.matchPercentage >= 50 ? 'bg-yellow-100 text-yellow-700' : 'bg-gray-100 text-gray-600')}">
                                                <fmt:formatNumber value="${match.matchPercentage}"
                                                    maxFractionDigits="0" />% Match
                                            </div>

                                            <h3
                                                class="text-xl font-bold text-[#141413] mb-2 group-hover:text-[#D17557] transition-colors pr-16">
                                                ${match.recipe.name}</h3>
                                            <p class="text-xs text-gray-500 uppercase tracking-wide mb-4">
                                                ${match.recipe.category}</p>

                                            <div class="flex justify-between items-center mt-4">
                                                <span class="text-sm text-gray-500">Missing
                                                    ${match.missingIngredientCount} ingredients</span>
                                                <a href="recipes?action=view&name=${match.recipe.name}"
                                                    class="bg-orange-100 text-[#D17557] px-4 py-2 rounded-lg font-medium hover:bg-[#D17557] hover:text-white transition-colors">
                                                    View Recipe
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>

                                <c:if test="${hasLowMatches}">
                                    <div class="mt-8 text-center">
                                        <button id="seeMoreBtn" onclick="toggleLowMatches()"
                                            class="bg-gray-100 text-gray-600 px-6 py-2 rounded-lg font-medium hover:bg-gray-200 transition-colors">
                                            Show Lower Matches
                                        </button>
                                    </div>
                                </c:if>

                                <div class="mt-12 text-center">
                                    <a href="index.jsp"
                                        class="text-[#475467] hover:text-[#D17557] font-medium flex items-center justify-center gap-2">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" viewBox="0 0 20 20"
                                            fill="currentColor">
                                            <path fill-rule="evenodd"
                                                d="M9.707 16.707a1 1 0 01-1.414 0l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 1.414L5.414 9H17a1 1 0 110 2H5.414l4.293 4.293a1 1 0 010 1.414z"
                                                clip-rule="evenodd" />
                                        </svg>
                                        Search Again
                                    </a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </body>

            </html>