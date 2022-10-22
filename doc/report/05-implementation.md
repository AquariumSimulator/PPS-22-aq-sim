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

## Elisa Albertini

### Acquario
Nella realizzazione delle varie componenti dell'acquario posso riportare le seguenti scelte implementative.
 
Le principali componenti dell'acquario, *Aquarium*, *AquariumState* e *Population*, sono state realizzate come **case classes**. Questa scelta è stata fatta per garantire che l'implementazione fosse il più aderente possibile ai principi della **Functional Programming**. Infatti la **case class** permette di avere la sicurezza che le sue proprietà non vengano modificate, il che favorisce un'implementazione priva di **side effects**.
 
In *AquariumState* ho fatto uso di **currying** e **higher order functions** in modo da rendere il codice più leggibile ma allo stesso tempo in modo da ridurre anche le ripetizioni di codice. In particolare la funzione che controlla che i valori dei parametri dell'acquario non escano dai limiti prende in input una **lambda expression** che fa il controllo sul nuovo valore.
 
```
def checkValueAndReturnAquarium(value: Double)(checkFunc: Double => Boolean)(
  newAquarium: AquariumState
): AquariumState = ...
```
In *UpdatePopulation* ho scelto di usare i **tipi generici** per le funzioni di aggiunta e rimozione di un abitante. Questa scelta implementativa mi ha permesso di avere un'unica funzione sia per gestire tutte le *entità*.  
```
def addInhabitant[A](newInhabitant: A): Population = ...
```
Inoltre, per diversificare la gestione dell'elemento di tipo generico, nell'implementazione del metodo, ho utilizzato un *match case* sul tipo.
```
newInhabitant match
  case f: Fish [...] =>
    ...
  case a: Algae [...]] =>
    ...
  case _ => ...
```
Sempre in *Population*, ho scelto di utilizzare la *ricorsione tail*, in particolare, per il metodo che inserisce le alghe in posizioni diverse quando una nuova istanza viene creata.
 
Per quanto riguarda *Events* e *Probabilities*, questi file presentano *objects* che contengono costanti e soprattutto **lambda expressions** che vengono utilizzate in varie parti del codice per calcolare, ad esempio, stringhe o probabilità.
 
### Interazioni
Parlando delle interazioni, per l'interfaccia *Interaction* ho scelto di utilizzare i generici, in quanto, come già spiegato precedentemente nel capitolo di design, ogni implementazione ha un tipo di ritorno diverso.    
Parlando invece delle implementazioni di interazioni da me realizzate, per alcune di esse ho utilizzato gli **opzionali**. Questa scelta è dovuta al fatto che, in alcune interazioni, uno dei due elementi che interagisce potrebbe morire. In questo caso viene appunto ritornato un opzionale vuoto.
 
### Model
Parlando dell'interfaccia *Model*, l'implementazione più rilevante è quella dello *step* della simulazione. Tale metodo, prende in input l'*Aquarium* corrente, ne restituisce uno nuovo aggiornato, rispettando i principi della **Functional Programming**.  
Il comportamento dello stesso è stato partizionato in modo da rispettare il più possibile i principi **DRY** e **KISS**. Per fare questo ho fatto dunque uso di **generici** e **higher order function**, affiancate dall'uso del **currying**, che mi hanno aiutato a ridurre le ripetizioni di codice e a rendere i miei metodi più comprensibili.  
Questo permette, inoltre, di poter utilizzare le stesse funzioni anche in caso di successive aggiunte al sistema della simulazione.  
Cito anche l'utilizzo di **for comprehension** e **ricorsione tail** in alcuni casi in cui mi è stato necessario compiere azioni particolari su insiemi o liste.
 
Un ultimo particolare implementativo da notare è la gestione delle interazioni asincrone dell'utente con la simulazione. Data la natura concorrente di queste operazioni è stata utilizzata una **ConcurrentLinkedQueue** di funzioni di tipo *Aquarium => Aquarium*. In questo modo, ogni volta che viene chiamato lo *step* della simulazione tutte le azioni che l'utente ha eseguito tramite la GUI vengono eseguite sull'acquario corrente.
 
### Codice prodotto
Per quanto riguarda le parti del sistema che ho implementato, mi sono occupata:
* Di tutto il modulo dell'*Acquario* contenuto all'interno del package **aquarium** all'interno del package **Model** e dei test associati
* Della realizzazione della struttura della factory delle *Interaction*, del file **Probabilities** che contiene varie costanti e lambda expression utilizzate dalle interazioni. Ho anche realizzato **InteractionEntityOnAquariumStateImpl**, **InteractionAquariumStateOnFishImpl** e **InteractionAquariumStateOnalgaeImpl**
* Di buona parte dei metodi contenuti e implementati in *Model* e *ModelComponent*, e dei relativi test, quali:
  * chronicle
  * addChronicleEvent
  * addUserInteraction
  * initializeAquarium
  * step (e tutte le funzioni utilizzate per la sua implementazione)
* Della realizzazione dell'object *Events* contenuto all'interno del package **chronicle** all'interno del package **Model** e dei test associati
*  Di alcuni metodi contenuti in *Controller* e implementati in *ControllerComponent*, e dei relativi test, quali:
   * updateTemperature
   * updateBrightness
   * clean
   * updateOxygenation
   * addInhabitant
   * removeInhabitant
   * addFood
   * deleteFood
   * getCurrentChronicle
* Di aggiungere l'evento per il click della *SimulationViewer* e, nello stesso object mi sono occupata anche della funzione findEntityClicked

## Emanuele Lamagna

### Entities
Per quanto riguarda le entità ho curato più che altro Fish, quella sicuramente più corposa, mentre le altre (Algae e Food) sono passive e più semplici. 

Ho scelto di utilizzare una case class, la quale permette di essere sicuri che le sue proprietà non vengano modificate: questo per evitare di introdurre side effects nel codice.
Oltre a ciò è presente il companion object della classe, che contiene tutte le informazioni statiche necessarie.

Tra i vari metodi il più importante è sicuramente *move*, che permette al pesce di muoversi nello spazio dell'acquario tenendo conto delle pareti, della velocità e della direzione.
Qui ho scelto di calcolare in primis la nuova posizione con il giusto moltiplicatore, e poi applicare un doppio *match case* per verificare se le nuove coordinate sono accettabili oppure se è necessario cambiare la posizione e la velocità.
Infatti se ad esempio il pesce raggiunge una coordinata negativa occorre riportarlo nel range giusto e modificare la velocità nel senso opposto.

In generale sono presenti molti metodi brevi e chiari, per mantenere un'elevata leggibilità del codice.

### Interazioni
Insieme ad Albertini mi sono occupato delle varie interazioni fra le entità: in particolare mi sono occupato delle interazioni fra:
- pesci e alghe
- pesci e cibo
- due pesci

Quest'ultimo caso è sicuramente il più degno di nota, siccome prevede più casistiche diverse. Infatti i pesci possono:
- mangiarsi a vicenda
- riprodursi
- ignorarsi

Nel metodo *update*, comune a tutte le interazioni, tramite un *match case* cerco di capire in quale di queste tre situazioni mi ritrovo.
Nell'ultimo caso ovviamente non succede nulla e i pesci rimangono identici, ma negli altri due casi utilizzo due funzioni rispettivamente: *checkReproduction* e *checkEatFish*. Questo per una migliore suddivisione e leggibilità del codice e per il rispetto del principio KISS.

### Download JSON
**DownloadJSON** sfrutta la libreria Gson per salvare la popolazione dell'acquario (alghe e pesci) in un file JSON.
In particolare vengono risaltati i principi:

- **KISS**: l'implementazione è molto semplice, poiché grazie a Gson basta scrivere in maniera lineare l'apertura/chiusura dei vari JSON Object o JSON Array. Qui ho reso tutto più semplice, leggibile e funzionale con due metodi che rispecchiano le due principali operazioni: *writeArray* e *writeObject*.
- **DRY**: proprio grazie ai due metodi sopra citati è stato possibile applicare questo principio. Infatti essi hanno evitato di riempire il codice di ripetizioni, siccome la scrittura di JSON Object o JSON Array accade più di una volta.
- **SRP**: la classe ha un unico scopo e quindi ha un'unica ragione per poter cambiare.

Oltre a ciò ho utilizzato **given** per evitare la ripetizione del passaggio di alcuni parametri, aiutato anche dall'utilizzo dell **currying**.



### Codice prodotto
In seguito le parti di codice di cui mi sono occupato:
- Model
  - cronistoria dell'acquario (case class **Chronicle**, trait **UpdateChronicle**)
  - gestione dei pesci (case class **Fish** e relativo companion object, trait **UpdateFish**)
  - interazioni, sempre nel model, fra:
    - due pesci (classe **InteractionFishOnFish**)
    - un pesce e un'alga (classe **InteractionFishOnAlgae**)
    - un pesce e un cibo (classe **InteractionFishOnFood**)
- View
  - realizzazione iniziale della **GUI** e dei suoi componenti (poi portata avanti soprattutto da altri membri del team)
  - realizzazione del pannello Chronicle
  - implementazione del download di file insieme a Benvenuti (io in particolare ho realizzato il download dei JSON tramite l'object DownloadJSON)
  - implementazione della visualizzazione dei nomi dei pesci sotto alla loro icona nella simulation view