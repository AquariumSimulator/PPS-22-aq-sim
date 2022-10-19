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


### Design prolog engine

### Design *Entity*

## Elisa Albertini

### Design acquario

### Design interazioni

## Emanuele Lamagna

### Design *chronicle*

### Design view