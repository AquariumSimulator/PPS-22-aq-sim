# 4 Analisi dettagliata

### Obiettivi
// roba funzionale
// no eccezioni

## Filippo Benvenuti

### Design mvc
Per una leggibile e idiomatica implementazione del pattern architetturale **MVC** ho pensato di realizzarlo tramite **Cake Pattern**, tale prevede una suddivisione in componenti disgiunti e sostituibili del codice, permettendo di esplicitare dipendenze tra essi specificando di ognuno a quale altro componente può accedere, di fatto ottenendo un forte vantaggio in fase di sviluppo, per cui da un componente potremo accedere solo a quelli di cui ne abbiamo specificato la dipendenza prima, in quanto in caso contrario otterremmo un codice non compilabile.
Tale pattern permette in fase di sviluppo di avere dei limiti impliciti che annullano l'errore umano di bypassare il design dei componenti, portando di fatto un vantaggio in termini di correttezza del dominio del progetto.
Il *cake pattern* si sposa in modo ottimale con il pattern architetturale *MVC*, infatti secondo quest'ultimo bisogna suddividere il codice in tre macro componenti (Model, View e Controller) i quali non dipendono l'uno dall'altro se non tramite l'interfaccia esposta (difatto potendone sostituire l'implementazione uno ad uno senza intaccare quella degli altri componenti) e tra di loro non tutti devono poter comunicare con gli altri (e.g. il controller può invocare model e view, mentre la view può invocare solamente il controller).

### Design simulation engine
Il *simulation engine* è la parte che si occupa della gestione della simulazione, responsabile delle azioni di *start*, *pause* e *speed change*, il suo comportamento è naturalmente descrivibile come *macchina a stati finiti*, il suo diagramma degli **stati** risulta:

![simEngineState](img/sim_engine_state.png)

Possiamo osservare 5 stati della simulazione:
- Halt: la simulazione è in pausa.
- Running: la simulazione sta procedendo:
  - Slow: velocità lenta, per osservare meglio cosa succede.
  - Normal: velocità normale, per la normale operatività.
  - Fast: velocità veloce, per evitare attese noiose.
- Stop: la simulazione è terminata.

All'apertura dell'applicativo la simulazione parte nello stato di *Halt*, in cui non avanza, quando passiamo in uno dei 3 stati di *Running* essa eseguirà a diverse velocità, in questi stati è sempre possibile cambiare velocità oppure tornare nello stato di *Halt*, da quest'ultimo possiamo far avanzare la simulazione di uno *Step* alla volta ritornando sullo stesso stato, tale meccanismo potrebbe tornare utile per realizzare la visualizzazione della simulazione passo-passo.
Da ogni stato è sempre possibile allo stato *Stop*, nel quale non è più possibile uscire, esso indica che la simulazione è terminata e non può più essere avviata.

### Design prolog engine
**Prolog engine** è il componente che si occupa di interfacciarsi con il *database* scritto in *Prolog*, il suo obiettivo sarà quello di esporre tutte le operazioni effettuabili sul *db* tramite un'interfaccia comoda da usare, di fatto nascondendo tutta la logica di accesso ai dati ed elaborazione di essi per trasformarli in oggetti utilizzabili.
Il principale vantaggio di prevedere l'esistenza di questo componente la si trova nella leggibilità e ordine del codice prodotto, sommato ad un concetto di incapsulazione che rende l'accesso ai dati più sicuro e intuitivo.

## Elisa Albertini

### Design acquario

### Design interazioni

## Emanuele Lamagna

### Design chronicle
La **Chronicle** rappresenta la cronistoria dell'acquario. Qui sono inseriti tutti gli avvenimenti accaduti in esso, ordinati in ordine di data, partendo dall'avvenimento più recente. Tra i vari avvenimenti ci sono:
- nascita, morte o rimozione di pesci e alghe
- aggiunta di cibo
- pulizia dell'acquario
- cambio di velocità della simulazione
- variazione ossigeno/temperatura

La Chronicle è utile per controllare le azioni già svolte e concluse e agire di conseguenza nella scelta delle prossime azioni da eseguire.

### Design view
La **View** è lo strumento che l'utente utilizza per sperimentare l'esperienza di simulazione. Già dalla fase di realizzazione del mockup ho pensato a come poter rendere il sistema completo di ogni sua funzionalità rimanendo però pulito, efficiente e funzionale per l'utente che lo utilizza.

Partendo dalle caratteristiche che il sistema avrebbe dovuto avere, ho pensato a una serie di widget volti a soddisfare al meglio ogni singola funzionalità:

- **Pannello della simulazione:** per visualizzare i pesci, le alghe e il cibo nel loro ciclo di vita ho pensato a un semplice pannello centrale.
  I pesci sono liberi di muoversi, le alghe si posizionano in fondo e il cibo, una volta inserito, cade dall'alto verso il basso.
- **Barra inferiore:** ho pensato a una barra, nella parte bassa della view, che, oltre ad avere il bottone di pausa/ripresa della simulazione, contenesse tutti i comandi volti a eseguire azioni esenti da un qualche tipo di range (come sarebbe, ad esempio, il cambio di temperatura).
  Questi comandi sono:
  - aggiunta di pesci o alghe
  - cambio di velocità della simulazione
  - aggiunta di cibo
  - pulizia dell'acquario
- **Sliders:** contrariamente alla barra inferiore, gli sliders permettono modifiche di valori compresi in un certo range. Più nel dettaglio troviamo:
  - slider della temperatura
  - slider dell'ossigenazione
  - slider della luminosità
- **Pannello delle informazioni:** per visualizzare le informazioni in tempo reale ho ideato un pannello (nella parte in alto a sinistra della View) diviso in celle: ogni cella mostra una certa caratteristica dell'acquario. In ordine abbiamo:
  - Popolazione, che mostra il numero di entità totali ma, al click su di essa, apre un popup che elenca in dettaglio i tipi di entità presenti e la relativa quantità
  - Temperatura
  - Luminosità
  - PH
  - Impurità
  - Ossigenazione
- **Cronistoria:** ho inserito un pannello contenente l'elenco degli avvenimento dell'acquario. La cronistoria è descritta in modo dettagliato nel paragrafo precedente.