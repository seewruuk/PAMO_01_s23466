# Kalkulator BMI

**Autor: Kacper Sewruk s23466**

## Opis projektu

Aplikacja **Kalkulator BMI** to prosta aplikacja mobilna stworzona w Android Studio przy użyciu języka Java. 
Jej głównym zadaniem jest obliczenie wskaźnika BMI (Body Mass Index) na podstawie danych wejściowych: masy ciała (w kg) oraz wzrostu (w metrach). 
Po obliczeniu BMI, aplikacja wyświetla wynik wraz z odpowiednią interpretacją:

- **niedowaga**
- **optimum**
- **nadwaga**
- **otyłość**

## Funkcjonalności

- **Wprowadzanie danych:** Użytkownik wpisuje swoją masę oraz wzrost.
- **Obliczanie BMI:** Aplikacja liczy wartość BMI używając wzoru: BMI = masa / (wzrost * wzrost)


- **Wyświetlanie wyniku:** Wynik oraz interpretacja (status) są prezentowane na ekranie.
- **Obsługa błędów:** Aplikacja sprawdza, czy pola zostały poprawnie wypełnione oraz czy wzrost nie jest równy zeru.

## Struktura projektu

- **AndroidManifest.xml:** Plik konfiguracyjny, definiujący główną aktywność.
- **activity_main.xml:** Layout interfejsu użytkownika zawierający EditText (dla masy i wzrostu), Button (do obliczeń) oraz TextView (do wyświetlania wyniku).
- **MainActivity.java:** Główna aktywność aplikacji, która zawiera logikę obliczania BMI oraz obsługę interfejsu.


![Zrzut ekranu 2025-03-20 210552](https://github.com/user-attachments/assets/3b856753-1b20-421c-a459-c0a59679e10e)

![Zrzut ekranu 2025-03-20 210607](https://github.com/user-attachments/assets/9a344324-3cfd-4a13-a63f-0b7454aeb383)

![Zrzut ekranu 2025-03-20 210620](https://github.com/user-attachments/assets/624b3912-87bd-429c-a49f-818734a54f75)

