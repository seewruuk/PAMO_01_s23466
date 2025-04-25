package com.example.bmicalculatorv2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RecipeHelperTest {
    @Test
    public void testLowCalorieRecommendations() {
        String result = RecipeHelper.getRecipeRecommendations(1800); // < 2000

        assertNotNull(result);
        assertTrue(result.contains("Sałatka") || result.contains("brokułów") || result.contains("Quinoa"));

        int lines = result.trim().split("\n").length;
        assertEquals(2, lines);
    }

    @Test
    public void testHighCalorieRecommendations() {
        String result = RecipeHelper.getRecipeRecommendations(2500); // >= 2000

        assertNotNull(result);
        assertTrue(result.contains("Makaron") || result.contains("Burger") || result.contains("Pizza"));

        int lines = result.trim().split("\n").length;
        assertEquals(2, lines);
    }
}
