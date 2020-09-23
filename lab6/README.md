## Zadanie 1 
_Przetwarzanie potokowe z buforem_
1. Bufor o rozmiarze N - wspólny dla wszystkich procesów!
2. Proces A będący producentem.
3. Proces Z będący konsumentem.
4. Procesy B, C, ..., Y będące procesami przetwarzającymi. Każdy proces otrzymuje dana wejściową od procesu poprzedniego, jego wyjście jest konsumowane przez proces następny.
5. Procesy przetwarzają dane w miejscu, po czym przechodzą do kolejnej komórki bufora i znowu przetwarzają ja w miejscu. Szkielet

## Zadadnie 2 
_Producenci i konsumenci z losowa ilością pobieranych i wstawianych porcji_
1. Bufor o rozmiarze 2M
2. Jest m producentów i n konsumentów
3. Producent wstawia do bufora losowa liczbę elementów (nie więcej niż M)
4. Konsument pobiera losowa liczbę elementów (nie więcej niż M)

## Blokowanie drobnoziarniste
Zamek (lock) jest przydatny wtedy, gdy operacje zamykania/otwierania nie mogą być umieszczone w jednej metodzie lub bloku synchronized. Przykładem jest zakładanie blokady (lock) na elementy struktury danych, np. listy. Podczas przeglądania listy stosujemy następujący algorytm:
1. zamknij zamek na pierwszym elemencie listy
2. zamknij zamek na drugim elemencie
3. otwórz zamek na pierwszym elemencie
4. zamknij zamek na trzecim elemencie
5. otwórz zamek na drugim elemencie
6. powtarzaj dla kolejnych elementów

Dzięki temu unikamy konieczności blokowania całej listy i wiele wątków może równocześnie przeglądać i modyfikować różne jej fragmenty.

## Ćwiczenie
1. Proszę zaimplementować listę, w której każdy węzeł składa się z wartości typu Object, referencji do następnego węzła oraz zamka (lock). Lista nie może przechowywać wartości null.
2. Proszę zastosować metodę drobnoziarnistego blokowania do następujących metod listy:
* boolean contains(Object o); //czy lista zawiera element o
* boolean remove(Object o); //usuwa pierwsze wystąpienie elementu o
* boolean add(Object o); //dodaje element o na koncu listy 
<br/> 
Proszę porównać wydajność tego rozwiązania w stosunku do listy z jednym zamkiem blokującym dostęp do całości. Należy założyć, że koszt czasowy operacji na elemencie listy (porównanie, wstawianie obiektu) może być duży - proszę wykonać pomiary dla różnych wartości tego kosztu. Proszę sprawdzić jaka będzie wydajność tego rozwiązania dla różnej ilości procesów uzyskujących dostęp do zasobów. Ilość procesów od 10 do 100.