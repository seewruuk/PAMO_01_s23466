package com.example.bmicalculatorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * MainActivity - główna aktywność aplikacji.
 *
 * Funkcjonalności:
 * 1. Wyświetla ekran startowy z grafiką.
 * 2. Kalkulator BMI – oblicza BMI na podstawie wagi i wzrostu oraz wyświetla interpretację.
 * 3. Kalkulator dziennego zapotrzebowania kalorycznego – oblicza BMR wg wzoru Harris-Benedicta
 *    (dla uproszczenia przyjęto męską wersję) i mnoży przez współczynnik aktywności.
 * 4. Rekomendacje kulinarne – na podstawie dziennego zapotrzebowania losowo wybiera 2 przepisy
 *    z przygotowanych list (różne przepisy w zależności od wyniku kalorycznego).
 */
public class MainActivity extends AppCompatActivity {

    private ImageView imageViewSplash;
    private EditText editTextMasa, editTextWzrost, editTextAge;
    private Spinner spinnerActivity;
    private Button buttonOblicz;
    private TextView textViewBMIResult, textViewCalorieResult, textViewRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        imageViewSplash = findViewById(R.id.imageViewSplash);
        editTextMasa = findViewById(R.id.editTextMasa);
        editTextWzrost = findViewById(R.id.editTextWzrost);
        editTextAge = findViewById(R.id.editTextAge);
        spinnerActivity = findViewById(R.id.spinnerActivity);
        buttonOblicz = findViewById(R.id.buttonOblicz);
        textViewBMIResult = findViewById(R.id.textViewBMIResult);
        textViewCalorieResult = findViewById(R.id.textViewCalorieResult);
        textViewRecipes = findViewById(R.id.textViewRecipes);




        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndDisplayResults();
            }
        });
    }

    /**
     * Metoda calculateAndDisplayResults pobiera dane z pól, oblicza BMI oraz dzienne
     * zapotrzebowanie kaloryczne, a następnie na podstawie wyniku kalorycznego losowo wybiera
     * rekomendacje kulinarne.
     */
    private void calculateAndDisplayResults() {
        String masaStr = editTextMasa.getText().toString();
        String wzrostStr = editTextWzrost.getText().toString();
        String ageStr = editTextAge.getText().toString();
        String activityLevel = spinnerActivity.getSelectedItem().toString();

        if (masaStr.isEmpty() || wzrostStr.isEmpty() || ageStr.isEmpty()) {
            textViewBMIResult.setText("Proszę wpisać wagę, wzrost oraz wiek.");
            textViewCalorieResult.setText("");
            textViewRecipes.setText("");
            return;
        }

        double masa = Double.parseDouble(masaStr);
        double wzrost = Double.parseDouble(wzrostStr);
        int age = Integer.parseInt(ageStr);

        if (wzrost == 0) {
            textViewBMIResult.setText("Wzrost nie może być zerem.");
            return;
        }

        // Obliczenie BMI
        double bmi = masa / (wzrost * wzrost);
        String bmiStatus;
        if (bmi < 18.5) {
            bmiStatus = "niedowaga";
        } else if (bmi < 25) {
            bmiStatus = "optimum";
        } else if (bmi < 30) {
            bmiStatus = "nadwaga";
        } else {
            bmiStatus = "otyłość";
        }
        textViewBMIResult.setText(String.format("BMI: %.2f (%s)", bmi, bmiStatus));

        double wzrostCm = wzrost * 100;
        double bmr = 66.47 + (13.75 * masa) + (5.003 * wzrostCm) - (6.755 * age);
        double activityFactor = getActivityFactor(activityLevel);
        double dailyCalories = bmr * activityFactor;
        textViewCalorieResult.setText(String.format("Dzienne zapotrzebowanie kaloryczne: %.0f kcal", dailyCalories));

        String recommendations = getRecipeRecommendations(dailyCalories);
        textViewRecipes.setText(recommendations);
    }

    /**
     * Metoda getActivityFactor zwraca współczynnik aktywności na podstawie wybranej opcji.
     */
    private double getActivityFactor(String activityLevel) {
        switch (activityLevel) {
            case "Brak ruchu":
                return 1.2;
            case "1 raz w tygodniu":
                return 1.375;
            case "2-3 razy w tygodniu":
                return 1.55;
            case "4-5 razy w tygodniu":
                return 1.725;
            case "Codziennie":
                return 1.9;
            default:
                return 1.2;
        }
    }

    /**
     * Metoda getRecipeRecommendations wybiera losowo 2 przepisy z odpowiedniej listy w zależności
     * od dziennego zapotrzebowania kalorycznego.
     */
    private String getRecipeRecommendations(double dailyCalories) {
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
