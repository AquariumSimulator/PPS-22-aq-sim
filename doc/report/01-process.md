## 2. Processo di sviluppo
Per il processo di sviluppo è stato utilizzato un approccio **SCRUM-inspired** che permette una gestione agile, iterativa e incrementale dello sviluppo del progetto.

Il processo di sviluppo è dunque il seguente:
* Uno dei membri del gruppo funge da committente o esperto del dominio, oltre che da sviluppatore; cercherà di garantire l'usabilità/qualità del risultato. Nel nostro caso il *domain expert* è **Filippo Benvenuti**.
* Un membro del gruppo funge da *product owner*, oltre che da sviluppatore. Nel nostro caso è **Filippo Barbari**.
* Un membro del gruppo funge da *scrum master*, oltre che da sviluppatore, incaricato di garantire un corretto uso di Scrum. Nel nostro caso non è stato eletto un membro particolare.
* È necessario effettuare un meeting iniziale, durante il quale tarare i macro parametri di Scrum.
* Organizzare il lavoro suddividendolo in sprint medio/corti.
* Ad ogni sprint si cerca di ottenere risultati "tangibili", con già un valore che possano apprezzare anche gli stakeholder.
* Organizzazione dei meeting fondamentali ad un uso corretto di Scrum.

### 2.1 Meeting pianificati
Scrum basa la gestione dello sviluppo attraverso meeting specializzati in cui i programmatori prendono decisioni sul progetto e/o si allineano rispetto all'andamento del lavoro, in particolare:
 - Meeting iniziale: oltre a eleggere *product owner*, *domain expert* e *scrum master*, bisogna organizzare gli altri meeting e redigere un **product backlog** nel quale sono definiti i macrotask del progetto ricavati da una fase di analisi dei requisiti svolta in precedenza, grazie ad esso è possibile osservare un avanzamento globale dello sviluppo.
 - Sprint settimanali: abbiamo scelto una durata degli sprint di circa 15-20 ore, con cadenza settimanale per mantenere il carico di lavoro il più leggero possibile.
 - Meeting inizio sprint: ad inizio di ogni sprint un meeting nel quale vengono definiti **sprint goal** (obiettivi dello sprint, parte del progetto da realizzare) e **sprint backlog** (documento sintetico contente la suddivisione in task degli obiettivi settimanali, con tanto di assegnazione task agli sviluppatori).
 - Meeting fine sprint: a fine di ogni sprint un meeting durante il quale viene scritta la **sprint review** sottolineando commenti relativi allo sprint passato, si controlla di aver raggiunto i *goal* e si aggiorna il *product backlog* di conseguenza.
 - Standup meeting: incontro giornaliero di breve durata, durante il quale il team si sincronizza sul lavoro svolto e condivide aspetti critici se suscitati, nel nostro caso non è stata redetto alcun documento in merito in quanto considerato non necessario.

### 2.2 Divisione in itinere dei task
Durante il meeting di inizio *sprint* viene definito lo *sprint backlog*, esso contiene una suddivisione in *task* del lavoro dello sprint valutandone la complessità tentando di stimarne la difficoltà/tempo di sviluppo a priori, tali *task* sono successivamente assegnati ad uno ad uno agli sviluppatori, tentando per quanto possibile di mantenere un carico di lavoro bilanciato.

Per il mantenimento dello stato di svolgimento dei task si è deciso di utilizzare *GitHub Projects*, uno strumento del tutto simile al famoso Trello, con il vantaggio di averlo integrato direttamente nel *repository* di GitHub quindi accessibile in modo agile.
Tale strumento permette di assegnare i *task* tramite **drag & drop** a diverse liste che ne indicano lo stato, nel nostro caso abbiamo usato:
- Todo: per i *task* definiti da svolgersi, ma non ancora presi in carico.
- Doing: per i *task* che vengono presi in carico e sono in fase di svolgimento/completamento.
- Done: per tutti i *task* che sono definiti finiti.


### 2.3 Revisione in itinere dei task
Abbiamo scelto di mantenere immutabile l'elenco dei *task* durante il corso dello sprint, lasciando eventuali modifiche dovute alla rimozione di task rivelatosi inutili o all'aggiunta di task non programmati al meeting di fine sprint.
Le uniche modifiche apportabili allo *sprint backlog* ammissibili durante lo sprint stesso riguardavano l'aggiornamento dello stato del task, ossia del numero indicativo del carico di lavoro rimanente.
Per ogni task assegnato è stata considerata la seguente *definition of done*: un task o una funzionalità è da considerarsi terminato nel momento in cui è stato adeguatamente testato e documentato, ha passato una code review (automatica o manuale a seconda dell'importanza) e soddisfa le aspettative del committente.

### 2.4 Strumenti utilizzati per i test
Si è deciso di svolgere l'intero progetto seguendo il modello di sviluppo **Test-Driven Development (TDD)**, esso consiste nell'organizzare lo sviluppo del progetto secondo tre fasi:
- Red: implementare i test per ciò che si deve sviluppare ancor prima di averlo sviluppato (rosso perchè solitamente a questo punto il test non compila/non passa).
- Green: sviluppo del codice per passare i test, concentrandosi sulla minimalità per passare i test piuttosto che sulla bellezza.
- Refactor: concentrarsi sulla riscrittura del codice appena implementato, ritoccando se necessario anche il codice relativo a test precedenti.

Queste tre fasi sono iterate per ogni singola implementazione necessaria al completamento del progetto, solitamento seguendo un periodo di tempo sotto i 20 minuti.
Tale metodologia di lavoro consente di scrivere codice di qualità e testato, minimizzando la perdita di tempo relativa all'incorrettezza di un'implementazione: seguendo il metodo tradizionale, un programmatore potrebbe sviluppare tutto il giorno e trovarsi alla fine con un codice che non fa quello per cui è stato scritto, potenzialmente perdendo un'intera giornata di lavoro; grazie al *TDD*, invece, questo rischio si riduce al periodo del ciclo, portando ad una perdita di 20 minuti nel caso peggiore.

Per i test automatici abbiamo usato la libreria **ScalaTest**: tra i tre metodi di scrittura dei test da essa offerti, la nostra scelta è ricaduta su *Describe - it*, la quale permette di scrivere test ordinati e composti secondo una descrizione gerarchica del dominio testato.
I test scritti in questo modo risultano essere di facile comprensione ed esplicativi nel momento in cui uno di esso non dovesse passare.
Tale metodologia è decorabile tramite *Give - When - Then*, grazie a queste *keyword* è possibile esplicare un ordinamento temporale dell'esecuzione dei test.

### 2.5 Strumenti utilizzati per la build
Come strumento per la build automation è stato utilizzato Scala Build Tool (`sbt`), che ha permesso una gestione efficiente delle dipendenze del progetto:
- scalafx: per la realizzazione della GUI
- TuProlog: per l'implementazione in prolog del database.
- scalatest: per i test.
- scala-csv: per la generazione di file *CSV*.
- gson: per la generazione di file *JSON*.
e di alcuni plugins utili per il miglioramento della qualità del codice:
- scalafmt: per la formattazione automatica del codice.

### 2.6 Strumenti utilizzati per la Continuous integration
Il *repository* su GitHub ha due *branch* principali: **master**, contenente la versione finale del progetto, e **develop**, contenente l'ultima versione funzionante realizzata.
Per l'implementazione di ogni *task* si è deciso di creare un nuovo *branch* a partire da develop: una volta terminato il task viene creata una **pull request** che, se confermata, permette di effettuare un **merge** delle modifiche apportate su develop.
Per confermare una *pull request* è necessario che essa passi due tipologie di controllo:
- Manuale: un altro membro del team deve revisionare il codice scritto e approvare i cambiamenti fatti (**Code Review**).
- Automatico: grazie alle **GitHub Actions** ogni *pull request* scatena la verifica dei *test* su tre **container** diversi (Ubuntu, Windows e Mac), grazie ai quali ci assicuriamo che nessun test sia stato violato e che il progetto funzioni su ogni piattaforma (*multi-platform*).
Una volta integrati i cambiamenti su *develop*, il *branch* usato per lo sviluppo puo' essere eliminato in sicurezza (senza perdere codice).