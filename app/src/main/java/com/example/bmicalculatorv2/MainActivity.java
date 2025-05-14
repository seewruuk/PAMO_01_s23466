package com.example.bmicalculatorv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MainActivity - główna aktywność aplikacji.
 */
public class MainActivity extends AppCompatActivity {

    private ImageView imageViewSplash;
    private EditText editTextMasa, editTextWzrost, editTextAge;
    private Spinner spinnerActivity;
    private Button buttonOblicz;
    private TextView textViewBMIResult, textViewCalorieResult, textViewRecipes;
    private RecyclerView recyclerViewShopping;
    private ShoppingListAdapter shoppingListAdapter;
    private List<ShoppingItem> shoppingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init UI
        imageViewSplash = findViewById(R.id.imageViewSplash);
        editTextMasa = findViewById(R.id.editTextMasa);
        editTextWzrost = findViewById(R.id.editTextWzrost);
        editTextAge = findViewById(R.id.editTextAge);
        spinnerActivity = findViewById(R.id.spinnerActivity);
        buttonOblicz = findViewById(R.id.buttonOblicz);
        textViewBMIResult = findViewById(R.id.textViewBMIResult);
        textViewCalorieResult = findViewById(R.id.textViewCalorieResult);
        textViewRecipes = findViewById(R.id.textViewRecipes);
        recyclerViewShopping = findViewById(R.id.recyclerViewShopping);

        // setup RecyclerView
        recyclerViewShopping.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewShopping.setHasFixedSize(false);
        shoppingItems = new ArrayList<>();
        shoppingListAdapter = new ShoppingListAdapter(shoppingItems);
        recyclerViewShopping.setAdapter(shoppingListAdapter);

        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndDisplayResults();
            }
        });
    }

    private void calculateAndDisplayResults() {
        String masaStr = editTextMasa.getText().toString();
        String wzrostStr = editTextWzrost.getText().toString();
        String ageStr = editTextAge.getText().toString();
        String activityLevel = spinnerActivity.getSelectedItem().toString();

        if (masaStr.isEmpty() || wzrostStr.isEmpty() || ageStr.isEmpty()) {
            textViewBMIResult.setText("Proszę wpisać wagę, wzrost oraz wiek.");
            textViewCalorieResult.setText("");
            textViewRecipes.setText("");
            shoppingItems.clear();
            shoppingListAdapter.notifyDataSetChanged();
            return;
        }

        double masa = Double.parseDouble(masaStr);
        double wzrost = Double.parseDouble(wzrostStr);
        int age = Integer.parseInt(ageStr);

        if (wzrost == 0) {
            textViewBMIResult.setText("Wzrost nie może być zerem.");
            return;
        }

        double bmi = masa / (wzrost * wzrost);
        String status = bmi < 18.5 ? "niedowaga" :
                bmi < 25   ? "optimum" :
                        bmi < 30   ? "nadwaga" : "otyłość";
        textViewBMIResult.setText(String.format("BMI: %.2f (%s)", bmi, status));

        double wzrostCm = wzrost * 100;
        double bmr = 66.47 + 13.75 * masa + 5.003 * wzrostCm - 6.755 * age;
        double factor = getActivityFactor(activityLevel);
        double dailyCalories = bmr * factor;
        textViewCalorieResult.setText(String.format("Dzienne zapotrzebowanie kaloryczne: %.0f kcal", dailyCalories));

        List<String> recipes = RecipeHelper.getRecipes(dailyCalories);
        String recText = RecipeHelper.getRecommendationsText(recipes);
        textViewRecipes.setText(recText);

        String chosen = recipes.get(new Random().nextInt(recipes.size()));

        shoppingItems.clear();
        for (String ing : RecipeHelper.getIngredientsFor(chosen)) {
            shoppingItems.add(new ShoppingItem(ing));
        }
        shoppingListAdapter.notifyDataSetChanged();
    }

    private double getActivityFactor(String level) {
        switch (level) {
            case "Brak ruchu": return 1.2;
            case "1 raz w tygodniu": return 1.375;
            case "2 do 3 razy w tygodniu": return 1.55;
            case "4 do 5 razy w tygodniu": return 1.725;
            case "Codziennie": return 1.9;
            default: return 1.2;
        }
    }
}
