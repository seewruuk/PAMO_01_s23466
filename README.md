# Kalkulator Kalorii i BMI

**Autor: Kacper Sewruk s23466**

##  Funkcjonalności aplikacji

- **Ekran startowy:** Wyświetla włąsną grafike.
- **Kalkulator BMI:** Użytkownik wprowadza wagę i wzrost, a aplikacja oblicza wskaźnik BMI i wyświetla interpretację (niedowaga, optimum, nadwaga, otyłość).
- **Kalkulator dziennego zapotrzebowania kalorycznego:** Na podstawie wzoru Harris-Benedicta (dla uproszczenia przyjęto męską wersję) oraz danych (wiek, waga, wzrost i poziom aktywności) aplikacja oblicza dzienne zapotrzebowanie kaloryczne.
- **Rekomendacje kulinarne:** W zależności od obliczonego zapotrzebowania kalorycznego, aplikacja losowo wybiera 2 przepisy z odpowiednio przygotowanych list (dla niższego i wyższego zapotrzebowania).
- **Lista zakupów (RecyclerView):** Po kliknięciu "Oblicz", użytkownik otrzymuje listę zakupów potrzebnych do przygotowania jednego z losowo wybranych przepisów. Lista pozwala na odznaczanie kupionych składników.
- **Wykres zmian BMI (MPAndroidChart):** Na dole aplikacji znajduje się liniowy wykres zmian BMI w czasie, z danymi przykładowymi (mock).

##  Testowanie aplikacji

- **Analiza statyczna (Lint)** Wykonano inspekcję kodu, zapisano raporty HTML (before i after). Poprawiono ostrzeżenia zgodnie z sugestiami Android Studio.
- **Test jednostkowy JUnit** Przetestowano metodę odpowiedzialną za klasyfikację BMI.
- **Test instrumentacyjny (Espresso):** Zrealizowano test UI, który symuluje wprowadzenie danych, kliknięcie przycisku i weryfikację wyświetlanych wyników.
- **Test Monkey (ADB):** Uruchomiono 500 losowych akcji za pomocą adb shell monkey. Aplikacja nie uległa awarii. W repozytorium znajduje się zrzut ekranu Monkey finished jako dowód.



![image](https://github.com/user-attachments/assets/b89246a2-93cb-4a32-8f7e-ee5e5763ab77)
![image](https://github.com/user-attachments/assets/c6b5b8f4-43b1-42bd-a0eb-2882da6d06c1)



