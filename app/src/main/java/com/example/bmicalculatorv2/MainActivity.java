package com.example.bmicalculatorv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.formatter.ValueFormatter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.Locale;
import java.util.Map;
import java.util.LinkedHashMap;

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
    private LineChart lineChartBMI;

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
        lineChartBMI = findViewById(R.id.lineChartBMI);

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
                setupMockedBmiChart();
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
        textViewBMIResult.setText(String.format(Locale.getDefault(), "BMI: %.2f (%s)", bmi, status));

        double wzrostCm = wzrost * 100;
        double bmr = 66.47 + 13.75 * masa + 5.003 * wzrostCm - 6.755 * age;
        double factor = getActivityFactor(activityLevel);
        double dailyCalories = bmr * factor;
        textViewCalorieResult.setText(String.format(Locale.getDefault(), "Dzienne zapotrzebowanie kaloryczne: %.0f kcal", dailyCalories));

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

    private void setupMockedBmiChart() {
        // Zamockowane dane (LinkedHashMap zachowuje kolejność)
        Map<String, Float> bmiHistory = new LinkedHashMap<>();
        bmiHistory.put("01.05", 22.1f);
        bmiHistory.put("02.05", 22.4f);
        bmiHistory.put("03.05", 22.3f);
        bmiHistory.put("04.05", 22.6f);
        bmiHistory.put("05.05", 22.7f);
        bmiHistory.put("06.05", 22.5f);
        bmiHistory.put("07.05", 22.8f);

        List<Entry> entries = new ArrayList<>();
        final List<String> xLabels = new ArrayList<>();
        int index = 0;
        for (Map.Entry<String, Float> entry : bmiHistory.entrySet()) {
            entries.add(new Entry(index, entry.getValue()));
            xLabels.add(entry.getKey());
            index++;
        }

        LineDataSet dataSet = new LineDataSet(entries, "BMI w czasie");
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawValues(false);

        LineData lineData = new LineData(dataSet);
        lineChartBMI.setData(lineData);
        lineChartBMI.getDescription().setText("Zmiany BMI (mock)");
        lineChartBMI.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int idx = (int) value;
                if (idx >= 0 && idx < xLabels.size()) {
                    return xLabels.get(idx);
                } else {
                    return "";
                }
            }
        });
        lineChartBMI.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChartBMI.getXAxis().setGranularity(1f);
        lineChartBMI.getAxisRight().setEnabled(false);
        lineChartBMI.invalidate(); // odświeżenie
    }
}
