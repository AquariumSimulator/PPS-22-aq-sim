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

### Design *Entity*

## Elisa Albertini

### Design acquario

### Design interazioni

## Emanuele Lamagna

### Design *chronicle*

### Design view