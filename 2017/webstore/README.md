# Content

This directory contains an excercise task of building example web store api.

# Case

API sklepu internetowego, który obsługuje: produkty, ceny (ceny mogą się zmieniać w czasie), koszyk (listę produktów z cenami oraz ilością)

Dostępne funkcje:

* Pobierania listy produktów
* Przeszukiwanie listy produktów  po nazwie produktu
* Pobieranie danych produktu podając jego ID
* Tworzenie koszyka
* Dodawanie/Usuwanie produktów do koszyka
* Zmiana ilości produktów w koszyku
* Pobierania zawartości koszyka. 
 
Prosimy o zrobienie:

* Zaimplementowany model bazy danych w którejś z wbudowanych baz danych  (h2, hsql lub inna)
* Wprowadzone dane kilku produktów
* REST API z przykładami użycia w testach jednostkowych  
* Aplikacja powinna się uruchamiać z Mavena (można użyć Spring boot lub embedded  Jetty/Tomcat)
