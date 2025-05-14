package com.example.bmicalculatorv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RecipeHelper {

    private static final Map<String, List<String>> INGREDIENTS_MAP = new HashMap<>();
    static {
        INGREDIENTS_MAP.put("Sałatka z grillowanym kurczakiem i warzywami", List.of(
                "Pierś z kurczaka (200 g)",
                "Sałata mieszana (100 g)",
                "Papryka czerwona (1 szt.)",
                "Pomidor (1 szt.)",
                "Ogórek (1 szt.)",
                "Oliwa z oliwek (2 łyżki)",
                "Sól i pieprz do smaku"
        ));
        INGREDIENTS_MAP.put("Zupa krem z brokułów", List.of(
                "Brokuły (300 g)",
                "Ziemniaki (2 szt.)",
                "Cebula (1 szt.)",
                "Bulion warzywny (500 ml)",
                "Śmietana 12% (2 łyżki)",
                "Sól i pieprz do smaku"
        ));
        INGREDIENTS_MAP.put("Quinoa z warzywami i tofu", List.of(
                "Quinoa (100 g)",
                "Tofu (150 g)",
                "Papryka żółta (1 szt.)",
                "Cukinia (1/2 szt.)",
                "Sos sojowy (2 łyżki)",
                "Oliwa z oliwek (1 łyżka)"
        ));
        INGREDIENTS_MAP.put("Makaron carbonara z boczkiem", List.of(
                "Makaron spaghetti (100 g)",
                "Boczek wędzony (100 g)",
                "Żółtka jaj (2 szt.)",
                "Parmezan (50 g)",
                "Śmietana 30% (50 ml)",
                "Sól i pieprz do smaku"
        ));
        INGREDIENTS_MAP.put("Burger z wołowiną i frytkami", List.of(
                "Bułka do burgera (1 szt.)",
                "Mięso wołowe mielone (150 g)",
                "Ser cheddar (1 plaster)",
                "Ziemniaki (2 szt.)",
                "Olej do smażenia",
                "Sałata, pomidor, ogórek do dekoracji"
        ));
        INGREDIENTS_MAP.put("Pizza z podwójnym serem", List.of(
                "Ciasto na pizzę (1 szt.)",
                "Sos pomidorowy (50 g)",
                "Ser mozzarella (100 g)",
                "Ser cheddar (50 g)",
                "Bazylia świeża"
        ));
    }

    /**
     * Zwraca listę nazw przepisów odpowiednią do dziennego zapotrzebowania.
     */
    public static List<String> getRecipes(double dailyCalories) {
        List<String> list = new ArrayList<>();
        if (dailyCalories < 2000) {
            list.add("Sałatka z grillowanym kurczakiem i warzywami");
            list.add("Zupa krem z brokułów");
            list.add("Quinoa z warzywami i tofu");
        } else {
            list.add("Makaron carbonara z boczkiem");
            list.add("Burger z wołowiną i frytkami");
            list.add("Pizza z podwójnym serem");
        }
        return list;
    }

    /**
     * Zwraca tekst rekomendacji (2 losowe przepisy) do wyświetlenia.
     */
    public static String getRecommendationsText(List<String> recipes) {
        Collections.shuffle(recipes, new Random());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(2, recipes.size()); i++) {
            sb.append("- ").append(recipes.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Zwraca listę składników dla zadanego przepisu.
     */
    public static List<String> getIngredientsFor(String recipeName) {
        return INGREDIENTS_MAP.getOrDefault(recipeName, List.of());
    }
}
