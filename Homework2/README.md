## Ciulinca Andra Stefania - 324CA
## Tema 2 - PA

### Problema 1: Numarare
#### Implementare: 
Pentru a afla numarul de lanturi elementare din doua grafuri am folosit programare dinamica si DFS, astfel in vectorul dp vom retine numarul de cai comune in cele doua grafuri de la nodul i la nodul n. Consideram cazul de baza dp[n] = 1 deoarece noi vrem sa retrunam caile comune incepand cu nodul 1.Pentru parcurgere avem nevoie de o stiva si un vector visited care retine daca nodul a fost vizitat sau nu. Incepand cu nodul 1, acesta este adaugat in stiva si este marcat ca vizitat. Apoi cat timp stiva nu este goala verificam pentru fiecare vecin al nodului curent daca muchiile din primul graf se regasesc si in graful al doilea prin metoda found. Daca muchia se regaseste si vecinul curent nu a mai fost vizitat atunci acesta este adugat in stiva si marcat ca vizitat. Daca parametrul isLeaf este inca pe true inseamna ca toti vecinii au fost vizitati si am ajuns la acest nod in stiva sau ca nu are vecini/nu are vecini comunic in graful al doilea. Daca este cazul in care are toti vecinii vizitati atunci calculam dp-ul pentru nodul curent iterand iar prin toti vecinii acestuia si adaugand dp-urile acestora daca muchia se gaseste si in al doilea graf.

Complexitate temporala: O(n + m)

### Problema 2: Trenuri
#### Implementare: 
Am folosit un HashMap pentru a retine graph-ul. Cheia acestuia este reprezentata de un oras, iar valoarea este o lista de String-uri ce reprezinta orasele adiacente cheii.
Metoda routes este apelata initial cu orasul sursa si orasul destinatie pentru care vrem sa gasim numarul maxim de orase distincte care pot fi vizitate. Initial verifica daca source este egala cu destination. Daca aceasta conditie este indeplinita inseamna ca am ajuns la cazul de baza, adica am ajuns la destinatie deci returnam 1. Pentru fiecare oras retinem apoi intr-un HashMap numarul de orase de la el la destination si daca deja am adugat orasul curent in HashMap nu continuam cu cautarea si returnam valoarea stocata. Apoi incepem cautarea, iterand prin toti vecinii si retinanad pentru fiecare numarul de orase de la el la destinatie, pentru ca la final sa facem maximul dintre valorile fiecarui vecin retinute in HashMap-ul memo si a aduga nodul parinte la final. Odata ce am terminat cu toti vecinii nodului source returnam valoarea retinuta in memo la cheia lui.
Pentru exemplul:

bucuresti timisoara
4
bucuresti sibiu
sibiu timisoara
sibiu cluj
cluj timisoara 

procesul este urmator:

Se incepe cu nodul source Bucuresti si destionation Timisoara.

Pentru Bucuresti: 
1. Bucuresti nu este Timisoara si nici nu a fost adaugat in memo deci verificam vecinii
2. Vecinii: Sibiu
	Pentru Sibiu: 
	1. Sibiu nu este Timisoara si nici nu a fost adaugat in memo deci verificam vecinii
	2. Vecinii: Timisoara, Cluj
	Pentru Timisoara: Este destinatia deci returnam 1.
	3. maxPath-ul pentru Sibiu devine 1 si trecem la Cluj
	Pentru Cluj:
		1. Cluj nu este Timisoara si nici nu a fost adaugat in memo deci verificam vecinii
		2. Vecinii: Timisoara - Este destinatia deci returnam 1.
		3. Cluj nu mai are alti vecini deci maxPath-ul ramane 1 si la final adaugam 1 pentru a retine si orasul sursa(Cluj). Deci in memo pentru Cluj avem 2.
	
	4. maxPath-ul pentru Sibiu este maximul dintre 1, retinut inainte si cel returnat de la routes(Cluj, Timisoara), deci 2.
	5. Adugam in memo (Sibiu, 2 + 1 = 3)
3. Ne intoarcem la Bucuresti dupa ce am terminat vecinii si aduagam in memo (Bucuresti, 3 + 1 = 4).
4. Returnam valoarea retinuta in memo pentru Bucuresti.

Complexitate temporala: O(n + m)

### Problema 3: Drumuri obligatorii
#### Implementare: 
Pentru aceasta problema am utilizata algoritmul Dijkstra implementata asemanator ca in lab, dar care returneaza un vector de distante de la nodul sursa la toate celelalte noduri din graph.
Aplicam algoritmul pentru fiecare nod x, y, z. Pentru x, y aplicam algoritmul folosind lista de adiacente initiala, iar pentru z o folosim pe cea revesed, pentru a afla practic toate drumurile catre z. Dupa ce am aflat vectorii de distante, iteram prin toate nodurile pentru a afla daca exista drum de la x, y, respectiv z la nodul curent. Daca exista aceste drumuri atunci costul de a parcurge distantele x-z, y-z este egal cu costul x-currentNode + y-currentNode + z-currentNode, deaorece distanta z-currentNode va fi comuna pentru drumul x-z, dar si pentru y-z, deci mai trebuie sa aduga doar costul de a ajunge la x la currentNode si de la y la currentNode. Verificam daca acest cost este minim si trecem mai departe. Dupa ce am iteram prn toate nodurile returnam costul minim.

Complexitate temporala: O(m*logn) - complexitatea algoritmului Dijkstra
