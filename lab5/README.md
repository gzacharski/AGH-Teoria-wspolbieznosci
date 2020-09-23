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