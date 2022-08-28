## 2. Processo di sviluppo
Per il processo di sviluppo è stato utilizzato un approccio **SCRUM-inspired** che permette una gestione agile, iterativa e incrementale dello sviluppo del progetto.

Il processo di sviluppo è dunque il seguente:
* Uno dei membri del gruppo funge da committente o esperto del dominio, oltre che da sviluppatore; cercherà di garantire l'usabilità/qualità del risultato. Nel nostro caso il *domain expert* è **Filippo Benvenuti**.
* Un membro del gruppo funge da *product owner*, oltre che da sviluppatore. Nel nostro caso è **Filippo Barbari**.
* È necessario effettuare un meeting iniziale, durante il quale redigere un *product backlog*, e definire un primo sprint organizzativo.
* Vengono effettuati sprint corti (da 15-20 ore di lavoro).
* Ad ogni sprint si cerca di ottenere risultati "tangibili", con già un valore per gli stakeholder.
* Viene redatto uno sprint backlog, e si fanno meeting frequenti, e meeting a inizio/fine sprint (con brevissimo report del risultato, anch'esso da tenere in versione).

### 2.1 Meeting pianificati
Dopo un primo meeting iniziale in cui sono stati assegnati i ruoli di *product owner* e *domain expert* sono cominciati gli print settimanali. Inoltre, all'inizio sono stati raccolti i requisiti del progetto e successivamente si è realizzato il **product backlog** che raccoglie tutti i macrotask del progetto.

Ogni settimana viene aperta e chiusa con un meeting. Ad ogni settimana viene prodotto uno **sprint goal** in cui vengono registrati i task che devono essere realizzati da ciascun componente del gruppo durante la settimana.

//Da definire meglio nel corso del progetto

### 2.2 Divisione in itinere dei task
Ogni settimana, quando viene effettuato il meeting di inizio sprint, vengono assegnati un congruo numero di task ad ogni membro che cercherà di portarli a termine entro il meeting di fine sprint. Nel caso alcuni task non siano stati completati essi verranno aggiunti a quelli dello sprint successivo. Per ogni task assegnato è stata considerata la seguente *definition of done*: un task o una funzionalità è da considerarsi terminato nel momento in cui è stato adeguatamente testato e documentato, ha passato una code review (automatica o manuale a seconda dell'importanza) e soddisfa le aspettative del committente.

//GitHub project

//Lista dei task di ogni componente del gruppo

### 2.3 Revisione in itinere dei task
//Da definire meglio nel corso del progetto

### 2.4 Strumenti utilizzati per i test
Il team ha deciso di svolgere l'intero progetto seguendo il modello di sviluppo **test-driven development (TDD)**, che consiste nella stesura di test automatici, realizzati, in questo contesto, mediante il tool **ScalaTest**, prima che venga scritto il codice del software. In questo modo l'obiettivo dello sviluppo diventa quello di superare i test precedentemente implementati.

### 2.5 Strumenti utilizzati per la build
Come strumento per la build automation è stato utilizzato Scala Build Tool (`sbt`), che ha permesso una gestione efficiente delle dipendenze del progetto e di alcuni plugins utili per la il miglioramento della qualità del codice.

### 2.6 Strumenti utilizzati per la Continuous integration
Lo strumento scelto per effettuare Continuous Integration è **GitHub Actions**, una piattaforma che consente di automatizzare i flussi di lavoro distribuendoli insieme alla repository del progetto. L'obiettivo è quello di verificare continuamente l'integrità del codice mediante test automatici e assicurare la più alta qualità del codice possibile. L'applicativo sarà testato su Windows, Linux a MacOS al fine di verificarne il corretto funzionamento.