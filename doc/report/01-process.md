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
Abbiamo scelto di mantenere immutabile l'elenco dei *task* durante il corso dello sprint, lasciando eventuali modifiche dovute alla rimozione di task rivelatosi inutili o all'aggiunta di task non programmati al meeting di fine sprint.
Le uniche modifiche apportabili allo *sprint backlog* ammissibili durante lo sprint stesso riguardavano l'aggiornamento dello stato del task, ossia del numero indicativo del carico di lavoro rimanente.

//GitHub project

### 2.3 Revisione in itinere dei task
Per ogni task assegnato è stata considerata la seguente *definition of done*: un task o una funzionalità è da considerarsi terminato nel momento in cui è stato adeguatamente testato e documentato, ha passato una code review (automatica o manuale a seconda dell'importanza) e soddisfa le aspettative del committente.

### 2.4 Strumenti utilizzati per i test
Il team ha deciso di svolgere l'intero progetto seguendo il modello di sviluppo **test-driven development (TDD)**, che consiste nella stesura di test automatici, realizzati, in questo contesto, mediante il tool **ScalaTest**, prima che venga scritto il codice del software. In questo modo l'obiettivo dello sviluppo diventa quello di superare i test precedentemente implementati.

### 2.5 Strumenti utilizzati per la build
Come strumento per la build automation è stato utilizzato Scala Build Tool (`sbt`), che ha permesso una gestione efficiente delle dipendenze del progetto e di alcuni plugins utili per la il miglioramento della qualità del codice.

### 2.6 Strumenti utilizzati per la Continuous integration
Lo strumento scelto per effettuare Continuous Integration è **GitHub Actions**, una piattaforma che consente di automatizzare i flussi di lavoro distribuendoli insieme alla repository del progetto. L'obiettivo è quello di verificare continuamente l'integrità del codice mediante test automatici e assicurare la più alta qualità del codice possibile. L'applicativo sarà testato su Windows, Linux a MacOS al fine di verificarne il corretto funzionamento.