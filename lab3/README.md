Problem ograniczonego bufora (producentów-konsumentów)
Dany jest bufor, do którego producent może wkładać dane, a konsument pobierać. Napisać program, który zapewni takie działanie producenta i konsumenta, w którym zapewniona będzie własność bezpieczeństwa i żywotności.
Zrealizować program:
przy pomocy metod wait()/notify()/signal()/await(). Kod szkieletu
dla przypadku 1 producent/1 konsument
dla przypadku n1 producentów/n2 konsumentów (n1>n2, n1=n2, n1 mniej od n2)
wprowadzić wywołanie metody sleep() i wykonać pomiary, obserwując zachowanie producentów/konsumentów
przy pomocy operacji P()/V() dla semafora:
n1=n2=1
n1>1, n2>1