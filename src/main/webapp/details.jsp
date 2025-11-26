<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>${recipe.name} - Unsay Sud-anon</title>
            <script src="https://cdn.tailwindcss.com"></script>
            <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"
                rel="stylesheet">
            <style>
                body {
                    font-family: 'Inter', sans-serif;
                }
            </style>
        </head>

        <body class="bg-gradient-to-br from-[#FAFAF8] to-[#F5F5F3] min-h-screen flex items-center justify-center p-4">

            <div class="w-full max-w-4xl bg-white rounded-3xl shadow-xl overflow-hidden flex flex-col max-h-[90vh]">
                <!-- Hero -->
                <div class="bg-white p-8 border-b border-gray-100 flex items-center gap-4 shrink-0">
                    <div class="bg-orange-100 p-4 rounded-2xl text-4xl">🍳</div>
                    <div>
                        <h1 class="text-3xl font-bold text-[#141413]">${recipe.name}</h1>
                        <a href="results.jsp"
                            class="text-sm text-[#475467] hover:text-[#D17557] flex items-center gap-1 mt-1">
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-4 w-4" viewBox="0 0 20 20"
                                fill="currentColor">
                                <path fill-rule="evenodd"
                                    d="M9.707 16.707a1 1 0 01-1.414 0l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 1.414L5.414 9H17a1 1 0 110 2H5.414l4.293 4.293a1 1 0 010 1.414z"
                                    clip-rule="evenodd" />
                            </svg>
                            Back to results
                        </a>
                    </div>
                </div>

                <div class="flex-1 overflow-y-auto">
                    <div class="flex flex-col md:flex-row h-full">
                        <!-- Ingredients -->
                        <div class="md:w-1/3 bg-green-50 p-8 border-r border-green-100">
                            <h2 class="text-lg font-bold text-green-800 mb-6 uppercase tracking-wider text-sm">
                                Ingredients</h2>
                            <ul class="space-y-3">
                                <c:forEach items="${recipe.ingredients}" var="ing">
                                    <li class="flex items-start gap-3 text-green-900">
                                        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-green-600 shrink-0"
                                            fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                                d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                                        </svg>
                                        <span>${ing}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                        <!-- Instructions -->
                        <div class="md:w-2/3 bg-blue-50 p-8">
                            <h2 class="text-lg font-bold text-blue-800 mb-6 uppercase tracking-wider text-sm">
                                Instructions</h2>
                            <div class="space-y-6">
                                <c:forEach items="${recipe.instructions}" var="step" varStatus="status">
                                    <div class="flex gap-4">
                                        <span
                                            class="flex-shrink-0 w-8 h-8 bg-blue-200 text-blue-800 rounded-full flex items-center justify-center font-bold text-sm">
                                            ${status.count}
                                        </span>
                                        <p class="text-blue-900 leading-relaxed pt-1">${step}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Footer -->
                <div class="p-6 bg-white border-t border-gray-100 text-center shrink-0">
                    <form action="recipes" method="post">
                        <input type="hidden" name="action" value="clear">
                        <button type="submit"
                            class="bg-[#141413] text-white px-8 py-3 rounded-xl font-semibold hover:bg-gray-800 transition-colors">
                            Cook Another Dish
                        </button>
                    </form>
                </div>
            </div>

        </body>

        </html>