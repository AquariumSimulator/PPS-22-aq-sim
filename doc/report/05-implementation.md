# 5 Implementazione

### Stile d'implementazione
Il team si è impegnato nel cercare di produrre codice, per quanto possibile, aderente allo stile di programmazione funzionale, in particolare utilizzando classi e oggetti immutabili, attraverso metodi che non contengono *side effects* al loro interno.
Durante la scrittura del codice si è cercato di rispettare il più possibile i principi DRY e KISS, assieme ai *SOLID principles*:
- SRP: Single responsibility principle
- OCP: Open/closed principle
- LSP: Liskov’s substitutability principle
- DIP: Dependency inversion principle

Dove abbiamo pensato potesse essere utile utilizzare un *pattern* di progettazione, ci siamo impegnati ad implementare rispettandolo, creando codice di qualità e facilmente comprensibile.
Ci siamo avvalsi il più possibile delle funzionalità di Scala, come:
- Match case
- Currying
- Given instances
- Higher order function
- Type class
- Case class
- Objects
- ...


## Filippo Benvenuti

### MVC
// gustosa separazione componenti
// abilitazione al testing
// pattern 

### Simulation engine
La parte principale del **Simualtion engine** risiede nella gestione della simulazione, in particolare possiamo concentrarci sull'implementazione del **loop**, essa è rinchiusa nell'uso di un iteratore infinito, al quale viene passato lo stato iniziale dell'*Aquarium*, ad ogni iterazione esso invoca il metodo *step* che dato un *Aquarium* ne restituisce la versione aggiornata al passo dopo della simulazione.
Per evitare di eseguire tutti i calcoli relativi alla simulazione all'interno del **Thread** dedicato all'interfaccia grafica, in modo da non bloccarla mai, ho scelto di usare un *Thread* a parte creato all'apertura dell'applicazione: in questo modo per decidere la velocità di esecuzione della simulazione è semplicemente bastato scegliere, in base allo stato corrente, il tempo di attesa tra uno step e l'altro, in questo caso implementato tramite una *sleep*, funzione messa a disposizione dai *Thread* di Java; da notare la scelta dell'uso di un **Delta time** che si va a sottrarre al tempo di attesa, per rendere la velocità di simulazione indipendente dalla velocità del computer su cui viene eseguita, rendendola di fatto deterministica (entro certi limiti).
Per come ho organizzato il *Simulation Engine*, per mettere in pausa la simulazione è bastato mettere in pausa il *Thread*, da questo punto di vista molto comodo, iniettando però l'uso esplicito delle **interrupt** per risvegliarlo da dormiente, questo va ad impattare leggermente l'implementazione causa uso del **try catch** per il corretto funzionamento, in quanto il risveglio non è indolore e genera un'eccezzione, di fatto portando lo sviluppo in un'idea di *Exception driven*, ma che in questo particolare caso non è sbagliato.

### Download CSV
**DownlaodCSV** evidenzia in particolare i classici principi **KISS** e **DRY**:
- *KISS*: per la parte di dichiarazione, un *object* contenente un *apply* che porta al completamento dell'operazione richiesta, di fatto rendendo banale l'invocazione da altri file:
  ```DownloadCSV("path/where/save/)```
- *DRY*: per l'organizzazione interna del codice, qui infatti ho usato diverse tecniche per eliminare completamente la ripetizione di codice, la funzione *saveToCSV* è generica nel tipo che stiamo salvando e accetta parametri in **currying**, in particolare il primo parametro è dichiarato con **using** e l'ultimo sfrutta il concetto di **higher order function**. Tali dettagli han permesso di usare la stessa funzione due volte evitando ripetizioni, specificando solo le differenze in termini di dati e **strategy** sul comportamento interno. Inoltre il **given** su *String* sfrutta implicitamente il *currying*, evitando di passare due volte lo stesso parametro ad invocazioni diverse.

### Algae

## Elisa Albertini

###

## Emanuele Lamagna

###