package com.example.bmicalculatorv2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * MainActivity - główna aktywność aplikacji kalkulatora BMI.
 *
 * Aplikacja umożliwia obliczenie wskaźnika BMI (Body Mass Index) na podstawie
 * wprowadzonych danych: masy ciała (w kg) oraz wzrostu (w metrach). Wynik obliczeń
 * jest prezentowany wraz z interpretacją (niedowaga, optimum, nadwaga, otyłość).
 */
public class MainActivity extends AppCompatActivity {

    // Deklaracja zmiennych odpowiadających elementom UI
    private EditText editTextMasa;
    private EditText editTextWzrost;
    private Button buttonOblicz;
    private TextView textViewWynik;

    /**
     * Metoda onCreate wywoływana przy tworzeniu aktywności.
     * Inicjalizuje interfejs użytkownika i ustawia logikę działania przycisku.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ustawienie layoutu dla aktywności (activity_main.xml)
        setContentView(R.layout.activity_main);

        // Powiązanie zmiennych z elementami layoutu poprzez ich identyfikatory
        editTextMasa = findViewById(R.id.editTextMasa);
        editTextWzrost = findViewById(R.id.editTextWzrost);
        buttonOblicz = findViewById(R.id.buttonOblicz);
        textViewWynik = findViewById(R.id.textViewWynik);

        // Ustawienie listenera dla przycisku, aby obsłużyć kliknięcie
        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pobranie wartości wpisanych przez użytkownika
                String masaStr = editTextMasa.getText().toString();
                String wzrostStr = editTextWzrost.getText().toString();

                // Sprawdzenie, czy oba pola są wypełnione
                if (!masaStr.isEmpty() && !wzrostStr.isEmpty()) {
                    // Konwersja wartości tekstowych na liczby typu double
                    double masa = Double.parseDouble(masaStr);
                    double wzrost = Double.parseDouble(wzrostStr);

                    if (wzrost == 0) {
                        // Zapobieganie dzieleniu przez zero
                        textViewWynik.setText("Wzrost nie może być zerem.");
                        return;
                    }

                    // Obliczenie BMI wg wzoru: masa / (wzrost * wzrost)
                    double bmi = masa / (wzrost * wzrost);

                    // Ustalenie statusu BMI na podstawie obliczonej wartości
                    String status;
                    if (bmi < 18.5) {
                        status = "niedowaga";
                    } else if (bmi < 25) {
                        status = "optimum";
                    } else if (bmi < 30) {
                        status = "nadwaga";
                    } else {
                        status = "otyłość";
                    }

                    // Formatowanie i wyświetlenie wyniku obliczenia
                    String wynik = String.format("BMI: %.2f\nStatus: %s", bmi, status);
                    textViewWynik.setText(wynik);
                } else {
                    // Komunikat w przypadku braku wprowadzonych danych
                    textViewWynik.setText("Proszę wpisać zarówno masę, jak i wzrost.");
                }
            }
        });
    }
}
