# 3 Analisi dei requisiti

## 3.1 Requisiti di business

### 3.1.1. Ambito 
Siamo in un negozio di pesci: viene richiesta la
simulazione di un acquario personalizzabile, nel quale poter inserire più
o meno pesci di diverso tipo immersi in un ambiente più o meno ricco di
vegetazione allo scopo di osservare la salute dei pesci nel corso
della loro vita tramite interazioni tra loro e l'ambiente circostante.
Il prodotto finale sarà utile sia per mantenere un acquario digitale
all'interno del negozio, sia per essere venduto al cliente finale, il
quale potrà utilizzarlo per prepararsi al meglio al mantenimento dei
pesci prima dell'effettivo acquisto.

### 3.1.2. Esigenze aziendali 
1. Simulazione di un acquario contenente pesci e vegetazione
2. Interfaccia grafica intuitiva
3. Possibilità di visionare i pesci in tempo reale
4. Facile installazione su diversi dispositivi fissi (la versione mobile non è richiesta)
5. Possibilità di visionare l'andamento dei parametri relativi ai pesci

### 3.1.3 Problemi
Il negozio ha ricevuto diverse richieste di rimborso in seguito del decesso
di alcuni pesci venduti.  
Il negozio, non essendo stato in grado di dimostrare che questi fossero in ottima salute e che il vero motivo del decesso fosse un'erronea gestione dell'acquario, il negozio richiede un simulatore da fornire ai clienti per tutelarsi e tutelare i pesci.

## 3.2 Requisiti utente
Con lo scopo di mantenere la stesura dei requisiti utente più realistica possibile, abbiamo simulato una vera e propria intervista con il *Product Owner*:

>**Descriva in una frase ciò di cui ha bisogno.**  
Buongiorno, sono Filippo Benvenuti, proprietario del negozio di pesci "Fish Paradise". Avrei bisogno di un programma che dia la possibilità ai miei clienti di provare, prima di comprare i pesci, a mantenere una sorta di acquario digitale che simuli in tutto e per tutto uno vero.  

>**Cosa intende lei per simulazione?**  
Non sono un esperto, ma mi riferisco alla possibilità di avere un software che, esattamente come un acquario vero, permetta di confrontarsi con la gestione di pesci, della vegetazione e delle funzionalità fisiche di un acquario. Vorrei poter dare al cliente la possibilità di allenarsi prima di tornare in negozio e acquistarne uno vero. Spero di diminuire il numero di decessi dei pesci che vendo...

>**Quali sono le caratteristiche di un acquario?**  
Un acquario, in primo luogo, si differenzia dagli altri per il volume d'acqua che riesce a contenere e la forma, ma non penso che questi siano dati essenziali per la versione digitale.  
Mi immagino un acquario piatto dove i pesci si muovono solo a destra, a sinistra, in alto e in basso. La dimensione dell'acquario potrebbe essere utile, anche se influisce solo sul numero di pesci che riesce a contenere.  
Le caratteristiche principali, che poi sono quelle che influenzano i pesci che ci vivono all'interno, riguardano per la maggior parte l'acqua. Parliamo, ad esempio, della temperatura dell'acqua: è importante che sia quella giusta per i tipi di pesci all'interno dell'acquario e la vegetazione. Il sistema deve mantenere costante la temperatura dell'acqua una volta impostata, permettendo comunque di poterla cambiare se e quando necessario. Il pH dell'acqua, invece, è particolare: alcuni pesci sono molto sensibili al suo cambiamento, bisogna stare attenti che questo valore non diventi né troppo alto nè troppo basso. La causa principale dei decessi dei pesci avviene per una scarsa o del tutto assente considerazione del pH, il quale tende ad alzarsi per effetto della presenza dei pesci. Le piante possono aiutare ad abbassarlo e, inoltre, offrono ossigeno per i pesci: spesso, infatti, succede che non sia abbastanza e si deve ricorrere ad una pompa che ne aumenti la concentrazione. Sappiamo bene cosa succederebbe se venisse a mancare l'ossigeno. La luce nell'acquario è utile principalmente alle piante per crescere, mentre i pesci non ne sono molto influenzati: lo sono invece dall'impurità dell'acqua, ecco perché è importante tenere sempre molto pulito l'acquario!

>**E quali, invece, quelle di un pesce?**  
Ogni pesce ha caratteristiche proprie che lo contraddistinguono: in realtà la complessità biologica dei pesci è vasta ma per questo programma mi limiterei a distinguerli in modo semplificato. D'altronde l'obiettivo è quello di formare clienti inesperti, non di certo biologi marini.  
Di prima battuta possiamo dividere i pesci in carnivori ed erbivori: entrambi, se affamati, nel caso in cui si trovino vicino al loro tipo di cibo, lo mangiano. I carnivori potrebbero mangiare gli altri pesci erbivori, mentre quelli erbivori potrebbero mangiare le piante nell'acquario. Le principali caratteristiche di un pesce possono essere semplificate in dimensione, velocità, fame ed età.

>**In che modo l'acquario influisce sui pesci e viceversa?**  
I pesci vengono influenzati dall'acquario per via dei parametri di cui abbiamo discusso prima: se la temperatura è troppo alta osserveremo i pesci muoversi velocemente, se troppo bassa il contrario. In entrambi i casi un'esposizione prolungata a temperature estreme li conduce alla morte. Il pH è subdolo, perchè se si trova fuori dal range ottimale porterà alla morte dei pesci. Anche nel caso in cui l'ossigeno dovesse scarseggiare si potrebbe arrivare al decesso del pesce. La luce non influisce sui pesci, ma attenzione alle piante! Più luce c'è, più velocemente crescono, ma in caso non ci sia sufficente illuminazione, potrebbero morire. L'impurità dell'acqua rende l'ambiente viscoso e i pesci faticheranno a muoversi: li vedremo quindi rallentare come se fosse freddo, questo fortunatamente non porta ad una morte improvvisa, ma un pesce troppo lento non riuscirà a nutrirsi. I pesci influenzano l'acquario, tramite gli escrementi contribuiscono all'impurità dell'acqua: più pesci ci sono, più in fretta l'acquario si sporcherà. Inoltre i pesci consumano ossigeno, anche in questo caso la popolazione fa la differenza. Fortunatamente i pesci non sono abbastanza voluminosi da interferire con la temperatura dell'acqua, anche se ne soffrono i cambiamenti. 

>**Quali sono le caratteristiche della vegetazione? Come interagisce con il resto dell'ambiente?**  
La vegetazione ha un ruolo fondamentale nell'acquario. Le piante si distinguono per numero e per altezza e sono importantissime in quanto producono ossigeno e diminuiscono il livello del pH nell'acqua. Ciò, tuttavia, non ci giustifica a riempire l'acquario di piante poichè questo porterebbe entrambi i livelli fuori dal range ammissibile causando ancora una volta la morte dei pesci. Le piante sono influenzate solo dalla luminosità dell'acquario: più luce significa più velocità di crescita e piante più grandi producono più ossigeno.

>**Quali azioni può compiere il cliente su questo acquario digitale?**  
Ovviamente il cliente dovrà interagire con l'acquario, altrimenti non potrebbe imparare niente e sarebbe inutile. In particolare, in base a come abbiamo definito il funzionamento dell'ecosistema, il proprietario dell'acquario potrà decidere di: aggiungere o togliere pesci o piante; fornire cibo per erbivori o per carnivori; pulire l'acquario; regolare la luminosità, la temperatura e l'ossigenazione dell'acqua. Per semplificare il lavoro di un principiante, si può pensare alla possibilità di mettere in pausa e di riprendere successivamente la simulazione. Dovrà anche avere la possibilità di velocizzarla e rallentarla.

>**Come funziona la riproduzione tra pesci?**
Ottima domanda! In realtà è una questione abbastanza intricata. Possiamo limitarci a considerare per ogni pesce un fattore di riproduzione, equivalente, in un certo senso, alla quantità di ormoni: quando due pesci si incontrano, se entrambi hanno un fattore abbastanza elevato, allora si accoppieranno, producendo un altro esemplare.

>**Ha qualche nota da aggiungere?**  
Per la prima versione del prodotto quello che ci siamo detti è più che sufficiente, ma per il futuro potrebbe essere interessante aggiungere qualche altra funzionalità per renderlo più apprezzabile, come ad esempio la possibilità di controllare un pesce all'interno dell'acquario manualmente per rendere la simulazione più divertente, oppure ricostruire l'albero genealogico dei pesci. 

Dall'intervista con Filippo Benvenuti, il *Product Owner*, sono stati dedotti i seguenti requisiti d'utente:  
* osservare in tempo reale:
   * ciclo di vita dei pesci
   * interazioni fra pesci
   * interazioni fra pesci e vegetazione
   * interazioni fra pesci e cibo
   * interazioni fra pesci e stato dell'acquario
   * interazioni fra alghe e stato dell'acquario
* aggiungere e rimuovere pesci in tempo reale
* memorizzazione dati dell'acquario all’interno di un database:
   * possibilità di estrarre informazioni
   * visionare grafico sull'andamento della popolazione
* visualizzazione in tempo reale di:
   * numero di esseri viventi divisi per specie
   * temperatura dell’acqua
   * luminosità
   * pH dell’acqua
   * impurità dell'acqua
   * ossigenazione dell'acqua
* interazione dell'utente con la simulazione tramite:
   * aggiunta cibo
   * regolazione termostato
   * pulizia acquario
   * regolazione illuminazione
   * play/stop della simulazione
   * modifica della velocità della simulazione
* opzionali per il futuro:
   * pesce controllabile manualmente dall’utente
   * estrazione dell’albero genealogico di ogni pesce dal database

## 3.3 Requisiti funzionali
* GUI
   * controlli utente
     * controllo intensità luminosa
     * controllo temperatura
     * controllo filtro dell'acqua (ossigenazione)
     * aggiunta pesce o alga
     * rimozione pesce o alga
     * start/stop simulazione
     * aggiunta cibo per pesci
     * pulizia acquario
     * cambiamento velocità della simulazione
   * simulation view
   * grafici andamento popolazione
   * download dati simulazione (csv e Json)
   * visualizzazione parametri in tempo reale
   * cronistoria eventi
* simulation engine
    * acquario
      * dimensione
      * parametri acqua
        * luminosità
        * ossigenazione
        * ph
        * impurità
        * temperatura
   * pesce 
      * posizione nell'acquario
      * parametri del pesce
         * età
         * velocità
         * sazietà
         * dimensione
         * nome
         * tipo di alimentazione
         * fattore di riproduzione
      * movimento
      * interazione con i parametri dell'acquario
   * alga
      * posizione nell'acquario
      * dimensione
      * produzione ossigeno
   * interazioni fra entità
      * pesce ⇐⇒ pesce
      * pesce ⇒ alga
      * pesce ⇐⇒ acquario
      * acquario ⇐⇒ alga
* database
   * memorizzazione dati ad ogni iterazione
     * dati popolazione
   * download csv
   * download Json
   * grafici

## 3.4 Requisiti non funzionali
* GUI responsiva
* usabilità
* semplice installazione cross-platform

## 3.5 Requisiti di implementazione
* Scala
* ScalaFX
* ScalaTest
* Prolog
* sbt