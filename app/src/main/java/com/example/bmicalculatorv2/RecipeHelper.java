package com.example.bmicalculatorv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RecipeHelper {
    /**
     * Metoda getRecipeRecommendations wybiera losowo 2 przepisy z odpowiedniej listy w zależności
     * od dziennego zapotrzebowania kalorycznego.
     */
    public static String getRecipeRecommendations(double dailyCalories) {
        List<String> lowCalRecipes = new ArrayList<>();
        lowCalRecipes.add("Sałatka z grillowanym kurczakiem i warzywami");
        lowCalRecipes.add("Zupa krem z brokułów");
        lowCalRecipes.add("Quinoa z warzywami i tofu");

        List<String> highCalRecipes = new ArrayList<>();
        highCalRecipes.add("Makaron carbonara z boczkiem");
        highCalRecipes.add("Burger z wołowiną i frytkami");
        highCalRecipes.add("Pizza z podwójnym serem");

        List<String> selectedRecipes = dailyCalories < 2000 ? lowCalRecipes : highCalRecipes;

        Collections.shuffle(selectedRecipes, new Random());
        int numberOfRecipes = Math.min(2, selectedRecipes.size());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfRecipes; i++) {
            sb.append("- ").append(selectedRecipes.get(i)).append("\n");
        }
        return sb.toString();
    }
}
