## 3 Analisi dei requisiti

### 3.1 Requisiti di business

**1.1. Ambito**: Siamo in un negozio di pesci dove viene richiesta la
simulazione di un acquario reale personalizzabile, inserendo quindi più
o meno pesci di diverso tipo immersi in un ambiente più o meno ricco di
vegetazione, con lo scopo di osservare la salute dei pesci nel corso
della loro vita tramite interazioni tra loro e l'ambiente circostante.
Il prodotto finale sarà utile sia per mantenere un acquario digitale
all'interno del negozio, sia per essere venduto al cliente finale, il
quale potrà utilizzarlo per preparasi al meglio al mantenimento dei
pesci prima dell'effettivo acquisto.

**1.2. Esigenze aziendali**: 
1. Simulazione di un acquario contenente pesci e vegetazione
2. Interfaccia grafica d'intuitiva interazione.
3. Possibilità di visionare i pesci in tempo reale.
4. Facile installazione su diversi dispositivi fissi (il mobile non è richiesto).
5. Possibilità di visionare l'andamento dei parametri relativi ai pesci.

**1.3 Problemi**: Il negozio ha ricevuto diverse richieste di rimborso a seguito del decesso
di alcuni pesci venduti, non essendo in grado di dimostrare che questi fossero stati
in ottima salute e che il vero motivo della tragedia fosse un'erronea gestione dell'acquario, il negozio richiede un simulatore da fornire ai clienti per tutelarsi e tutelare i pesci.

### 3.2 Requisiti utente
Con lo scopo di mantenere la stesura dei requisiti d'utente più realistica possibile, abbiamo simulato una vera e propria intervista con il Product Owner:

>**Descriva in una frase ciò di cui ha bisogno.**  
Buongiorno, sono Filippo Benvenuti, proprietario e commesso del negozio di pesci "Fish Paradise", in poche parole ho bisogno di un programma che dia la possibilità ai miei clienti di provare, prima di comprarmi i pesci, a mantenere una sorta di acquario digitale che faccia da simulazione ad uno vero.  

>**Cosa intende lei per simulazione?**  
Non sono un esperto, mi riferisco alla possibilità di portarsi a casa un acquario in tutto e per tutto che abbia la possibilità di contenere più o meno pesci e magari anche la vegetazione, per dare al cliente la possibilità di allenarsi prima con un acquario in cui non può fare del male a nessuno e poi quando è più esperto tornare in negozio e acquistare un vero acquario con veri pesci. Spero di diminuire il numero di decessi dei pesci che vendo...

>**Quali sono le caratteristiche di un acquario?**  
Un acquario in primo luogo si differenzia dagli altri per il volume d'acqua che riesce a contenere e la forma, ma non penso che questi siano dati essenziali per la versione digitale, mi immagino un acquario piatto dove i pesci si muovono solo a destra a sinistra in alto e in basso. La dimensione dell'acquario potrebbe essere utile anche se influisce solo sul numero di pesci che riesce a contenere. Le caratteristiche principali, che poi sono quelle che influenzano i pesci che ci vivono all'interno, riguardano per la maggior parte l'acqua, parliamo ad esempio della temperatura dell'acqua, importante sia quella giusta per i tipi di pesci all'interno dell'acquario e la vegetazione, non è difficile che esistano sistemi che dopo averli impostati inizialmente la mantengono fissa scaldandola quando serve, raro che l'acqua sia troppo calda. Il pH dell'acqua è particolare, alcuni pesci sono molto sensibili ai cambiamenti del pH, bisogna stare attenti che questo valore non diventi né troppo alto nè troppo basso, la causa principale dei decessi dei pesci avviene per una scarsa o del tutto assente considerazione del pH, il quale tende ad alzarsi per effetto della presenza dei pesci, le piante possono aiutare ad abbassarlo. Le piante inoltre offrono l'importante ossigeno per i pesci, spesso succede che non è abbastanza e si usa una pompa che ne aumenta la concentrazione nell'acqua, sappiamo bene cosa succederebbe se venisse a mancare l'ossigeno. La luce nell'acquario è utile alle piante per crescere, i pesci non sono molto influenzati, lo sono invece dall'impurità dell'acqua, ecco perché è importante tenere sempre molto pulito l'acquario!

>**E invece le caratteristiche di un pesce?**  
Ogni pesce ha caratteristiche proprie che lo contraddistinguono, in realtà la complessità biologica dei pesci è tanta, per questo programma mi limiterei a distinguerli in modo semplificato, d'altronde l'obiettivo è quello di formare clienti inesperti, non di certo un biologo marino. Di prima battuta possiamo dividere i pesci in carnivori ed erbivori, entrambi se non nutriti inizieranno a cacciare per trovarsi il cibo in autonomia, i carnivori diventeranno violenti attaccando gli altri pesci, preferendo gli erbivori se presenti, gli erbivori oltre a scappare se malnutriti attaccheranno le piante nell'acquario, mangiandole. Le principali caratteristiche di un pesce possiamo semplificarle in dimensione, velocità, fame ed età, queste caratteristiche sono naturalmente legate tra di loro un pò come succede per gli umani, ad esempio un pesce di grandi dimensioni e età avanzata andrà sicuramente più piano di uno snello e giovincello, all'aumentare delle dimensioni aumenta anche la fame, per aumentare la veridicità dell'acquario sarebbe importante tenere in considerazione queste caratteristiche per vedere come influenzano la vita di un pesce durante la simulazione.

>**In che modo l'acquario influisce sui pesci e viceversa?**  
Brevemente, i pesci vengono influenzati dall'acquario secondo i parametri di cui abbiamo discusso prima: se la temperatura è troppo alta osserveremo i pesci muoversi velocemente con tutte le conseguenze del caso, se troppo bassa il contrario, in entrambi i casi un'esposizione prolungata a temperature estreme li conduce alla morte. Il pH è subdolo, se fuori dal range ottimale porterà alla morte dei pesci quasi casualmente. Se l'ossigeno dovesse scarseggiare vedremo i pesci avvicinarsi alle piante per tentare di assorbirne il più possibile, anche in questo caso troppo poco ossigeno porterà al decesso. La luce non influisce sui pesci, certamente più luce c'è meglio li vedremo, ma attenzione alle piante! L'impurità dell'acqua rende l'ambiente viscoso, i pesci faticheranno a muoversi: li vedremo quindi rallentare come se fosse freddo, questo fortunatamente non porta ad una morte improvvisa, ma un pesce troppo lento non riuscirà a nutrirsi. I pesci influenzano l'acquario, tramite gli escrementi contribuiscono all'impurità dell'acqua, più pesci ci sono più in fretta l'acquario si sporcherà a parità di dimensione, inoltre i pesci consumano ossigeno, anche in questo caso la popolazione fa la differenza, fortunatamente i pesci non sono abbastanza voluminosi da interferire con la temperatura dell'acqua anche se ne soffrono i cambiamenti. In generale lo sporco nell'acqua ne aumenta il pH.

>**Quali sono le caratteristiche della vegetazione? Come interagisce con il resto?**  
La vegetazione ha un ruolo fondamentale nell'acquario, si distingue per numero e per altezza, una pianta più grande sarà bersaglio dei pesci più affamati, importantissima in quanto produce ossigeno e diminuisce il livello del pH nell'acqua, ciò non ci giustifica a riempire l'acquario di piante, questo porterebbe entrambi i livelli fuori dal range ammissibile causando ancora una volta la morte dei pesci. Le piante sono influenzate solo dalla luminosità dell'acquario, più luce significa più velocità di crescita, piante più grandi producono più ossigeno e depurano meglio l'acqua.

>**Quali azioni può compiere il cliente su questo acquario digitale?**  
Ovviamente il cliente dovrà interagire con l'acquario, altrimenti non potrebbe imparare niente e sarebbe inutile, in particolare in base a come abbiamo detto che l'ecosistema funziona, il proprietario dell'acquario potrà decidere di: aggiungere o togliere pesci o piante; fornire cibo per erbivori o per carnivori; pulire l'acquario; regolare la luminosità, la temperatura e l'ossigenazione dell'acqua. Per semplificare il lavoro di un principiante, si può pensare alla possibilità di mettere in pausa e di riprendere successivamente la simulazione, in questo modo in momenti critici si può imparare cosa fare con calma.

>**Ha qualche nota da aggiungere?**  
Per la prima versione del prodotto finito quello che ci siamo detti è più che sufficiente, ma per il futuro potrebbe essere interessante aggiungere qualche funzionalità per renderlo più apprezzabile come ad esempio: la possibilità di controllare un pesce all'interno dell'acquario manualmente per rendere la simulazione non solo un lavoro ma anche ludica, ricostruire l'albero genealogico dei pesci, per osservare la dinastia e rendersi conto di quale famiglia sia stata la più forte nelle generazioni. Inoltre si potrebbe consentire di spegnere l'acquario e riprendere dal punto in cui si era rimasti e mettere la possibilità di velocizzare o rallentare la simulazione per evitare le parti noiose o per osservare nel dettaglio certi momenti.

Dall'intervista con Filippo Benvenuti, il Product Owner, sono stati dedotti i seguenti requisiti d'utente:  
* 2.1. osservare in tempo reale:
   * 2.1.1. ciclo di vita dei pesci
   * 2.1.2. interazioni fra pesci
   * 2.1.3. interazioni fra pesci e vegetazione
* 2.2. aggiungere e rimuovere pesci in tempo reale
* 2.3. memorizzazione dati dell'acquario all’interno di un database:
   * 2.3.1. possibilità di estrarre informazioni
   * 2.3.2. visionare grafici sull'andamento dei dati salvati
* 2.4. visualizzazione in tempo reale di:
   * 2.4.1. numero di esseri viventi divisi per specie
   * 2.4.2. temperatura dell’acqua
   * 2.4.3. luminosità
   * 2.4.4. pH dell’acqua
   * 2.4.5. impurità dell'acqua
   * 2.4.6. ossigenazione dell'acqua
* 2.5. interazione con la simulazione tramite:
   * 2.5.1. aggiunta cibo
   * 2.5.2. regolazione termostato
   * 2.5.3. pulizia acquario
   * 2.5.4. regolazione illuminazione
   * 2.5.5. play/stop della simulazione
* 2.6. Opzionali per il futuro:
   * 2.6.1. pesce controllabile manualmente dall’utente
   * 2.6.2. estrazione dell’albero genealogico di ogni pesce dal database
   * 2.6.3. salvataggio/caricamento della simulazione
   * 2.6.4. velocizzare/rallentare la simulazione

### 3.3 Requisiti funzionali
* 3.1. GUI
   * 3.1.1. controlli utente
     * 3.1.1.1. controllo intensità luminosa
     * 3.1.1.2. controllo temperatura
     * 3.1.1.3. controllo filtro dell'acqua (ossigenazione)
     * 3.1.1.4. aggiunta pesce o alga
     * 3.1.1.5. rimozione pesce o alga
     * 3.1.1.6. start/stop simulazione
     * 3.1.1.7. aggiunta cibo per pesci
     * 3.1.1.8. pulizia acquario
   * 3.1.2. simulation view
   * 3.1.3. grafici andamento parametri
   * 3.1.4. download dati simulazione (csv)
   * 3.1.5. visualizzazione parametri in tempo reale
   * 3.1.6. cronistoria eventi
* 3.2. simulation engine
    * 3.2.1. acquario
      * 3.2.1.1. dimensione
      * 3.2.1.2. parametri acqua
   * 3.2.2. pesce 
      * 3.2.2.1. posizione nell'acquario
      * 3.2.2.2. parametri del pesce
         * 3.2.2.2.1. età
         * 3.2.2.2.2. velocità
         * 3.2.2.2.3. fame
         * 3.2.2.2.4. dimensione
         * 3.2.2.2.5. nome
      * 3.2.2.3. movimento
   * 3.2.3. alga
      * 3.2.3.1. posizione nell'acquario
      * 3.2.3.2. dimensione
      * 3.2.3.3. velocità produzione ossigeno
   * 3.2.4. interazioni fra entità
      * 3.2.4.1. pesce ⇐⇒ pesce
      * 3.2.4.2. pesce ⇒ alga
      * 3.2.4.3. pesce ⇐⇒ acquario
      * 3.2.4.4. acquario ⇐⇒ alga
* 3.3. database
   * 3.3.1. memorizzazione dati ad ogni iterazione
     * 3.3.1.1. dati pesci
         * 3.3.1.1.1 csv
         * 3.3.1.1.2 immagini
     * 3.3.1.2. dati acquario
     * 3.3.1.3. dati alghe

### 3.4 Requisiti non funzionali
* 4.1. GUI responsiva
* 4.2. usabilità
* 4.3. semplice installazione cross-platform

### 3.5 Requisiti di implementazione
* 5.1. Scala
* 5.2. ScalaFX
* 5.3. ScalaTest
* 5.4. Prolog
* 5.6. sbt