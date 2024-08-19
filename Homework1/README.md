## Ciulinca Andra Stefania - 324CA
## Tema 1 - PA

### Problema 1: Alimentare Servere
#### Implementare:
Daca ne uitam la primul exemplu pentru puterile 6 9 7 5 si limitele de alimentare
2 4 1 8 observam ca obtinem graficul din figura de mai jos.
![img.png](img.png)
Pentru a calcula puterea de calcul maximă care poate ﬁ atinsă,
cea mai mică dintre puterile individuale ne uitam la intersectiile cu axa Ox din grafic.
Acestea pot fi calculate explicitand modulul din ecuatiile de forma: p - |x - c|, unde p este puterea serverului, 
c este limita de alimentare a serverului si x este puterea de calcul a serverului, 
iar c este limita de alimentare. Limita negativa se obtine prin diferenta c - p, iar limita pozitiva,
prin suma c + p. Se calculeaza aceste limite si sunt stocate in ArrayLists negativeLimits si positiveLimits.
Pentru a obtine x din ecuatia de mai sus ne trbuie cea mai mare limita negativa si cea mai mica
limita pozitiva, apoi se face media aritmetica a acestora. In final pentru a obtine puterea de calcul inlocuim x in 
ecuatia de mai sus cu media aritmetica a limitelor pentru fieacare
server si alegem minimul obtinut.

Complexitatea temporala: O(n)

### Problema 2: Colorare
#### Implementare:
Pentru a rezolva aceasta problema am folosit programare dinamica. Am stocat numarul de dreptunghiuri, respectiv orientarea
acestora (0 - orizontal, 1 - vertical) intr-o matrice de dimensiunea n x 2, unde n este numarul de dreptunghiuri. Cazurile 
de baza pentru dp sunt:
* daca primul dreptunghi este orizintal atunci dp[0] = 6, deaorece cele 2 dreptunghiuri puse orizontal se pot colorare,
  cu 3 culori in 6 moduri;
* daca primul dreptunghi este vertical atunci dp[0] = 3;
Apoi pentru numarul de dreptunghiuri ramase folosim ridicare la putere in timp logaritmic.
Iteram prin restul perechilor din matrice si verifica ce tip de dreptunghi vem si ce a fost anterior.
Combinatiile pot fi urmatoarele:
* HH - 3 moduri: daca dreptunghiul anterior orizontal este de ex roz-mov, atunci urmatorul poate avea urm combinatii: 
     mov-roz, mov-galben, galben-roz;
* VH - 2 moduri: daca dreptunghiul anterior vertical este de ex roz, atunci urmatorul poate avea urm combinatii: 
     mo-galben, galben-mov;
* HV - un mod: daca dreptunghiul anterior orizontal este de ex roz-mov, atunci urmatorul poate fi doar galben;
* VV - 2 moduri: daca dreptunghiul anterior vertical este de ex roz, atunci urmatorul poate avea urm combinatii: 
     galben, mov;

Daca avem mai multe dreptunghiuri consecutive la fel atunci acestea se grupeaza si folosim ridicarea la putere in timp
logaritmic pentru a fi mai eficient. Astfel, in functie de caz (HH sau VV) numarul de cazuri posibile este ridicat 
la numarul de dreptunghiuri adiacente.
In final dp[n - 1] reprezinta numarul de moduri in care putem colora dreptunghiurile.

Complexitatea temporala: parcurgerea vectorului in O(n) + ridicare la putere in timp logaritmic

### Problema 3: Compresie
#### Implementare:
Cei doi vectori pot fi comprimati doar daca suma celor doi este egala. Astfek inainte de a face modificari verificam
daca suma celor doi vectori este egala. Daca nu este, afisam -1.
Apoi parcurgem vectorii cu 2 indecsi si facem urmatoarele verificari:
* daca elementul din vectorul 1 este mai mare decat elementul din vectorul 2, atunci adunam la elementul urmator din 
  vectorul 2 elementul curent si mutam indexul vectorului 2 cu o pozitie in fata;
* daca elementul din vectorul 1 este mai mic decat elementul din vectorul 2, atunci adunam la elementul urmator din 
  vectorul 1 elementul curent si mutam indexul vectorului 1 cu o pozitie in fata;
* daca elementele sunt egale, atunci adaugam intr-un ArrayList elementul curent si mutam ambii indecsi cu o pozitie in 
  fata.

La finalul celor doi vectori returnma size-ul ArrayListului.

Complexitate temporala: O(n)

### Problema 4: Criptat
#### Implementare:
Structuri de date folosite:
* HsshSet - pentru stocarea caracterelor unice din cuvinte;
* HashMap - pentru fiecare cuvant exista un HashMap ce contine fiecare caracter din cuvant si nr de aparitii ale acestuia;
* Vector de Stringuri - pentru stocarea cuvintelor;
Pentru a afla paroloa cea mai lunga iteram prin toate caracterele din Hashset si calculam cea mai lunga frecventa posibila
al acestuia. Iteram prin toate cuvintele si verificam daca caracterul curent se afla in HashMap-ul cuvantului. Astfel, 
pentru fiecare lungime a parolei setam o litera, fara a recalcula litera dominanta, si construim combinatiile de lungimi
diferite ale parolei. In final facem ultima verificare conditia ca 2 * frecventa > lungimea parolei si salvam lungimea 
cea mai mare.

Complexitate temporala: O(n) - parcurgerea cuvintelor deoarece stim ca avem doar 8 litere unice

### Problema 5: Oferta
#### Implementare:
Cazurile de baza pentru totalPrice(dp din programare dinamica) sunt:
* totalPrice[0] = prices[0];
* totalPrice[1] - aplicam oferta cu discount de 50% pentru produsul cel mai ieftin;
Iteram prin restul produselor si avem 3 posibilitati de grupare:
* Adaugam produsul curent la pretul calculat la pasul anterior;
* Aplicam oferta de 50% si grupa produsul curent cu cel anterior si adaugam la pretul calculta la pasul i - 2;
* Daca avem 3 produse, aplicam oferta de 100% discount pentru produsul cel maiieftin si adaugam la pretul calculat la 
  pasul i - 3, sau, daca sunt primele 3 produse, adunam cu 0;

Facem pretul minim dintre cele 3 posibilitati si il salvam in totalPrice[i]. In final totalPrice[n - 1] reprezinta 
pretul minim.

Complexitate temporala: O(n)