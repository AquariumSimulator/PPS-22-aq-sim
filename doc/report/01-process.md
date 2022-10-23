# 2. Processo di sviluppo
Per il processo di sviluppo è stato utilizzato un approccio **Scrum-inspired** che permette una gestione agile, iterativa e incrementale dello sviluppo del progetto.

Il processo di sviluppo è dunque il seguente:
* Uno dei membri del gruppo funge da committente o esperto del dominio, oltre che da sviluppatore; cercherà di garantire l'usabilità/qualità del risultato. Nel nostro caso il *domain expert* è **Filippo Benvenuti**.
* Un membro del gruppo funge da *product owner*, oltre che da sviluppatore. Nel nostro caso è **Filippo Barbari**.
* Scrum prevederebbe anche la figura dello *Scrum master*: tuttavia, dato che nel *team* nessun componente si è ritenuto sufficientemente esperto, abbiamo preferito collaborare. 
* È necessario effettuare un meeting iniziale, durante il quale impostare i macro parametri di Scrum.
* Organizzare il lavoro suddividendolo in sprint medio/corti.
* Ad ogni sprint si cerca di ottenere risultati "tangibili", che possano già soddisfare anche gli stakeholder.
* Organizzazione dei meeting fondamentali ad un uso corretto di Scrum.

## 2.1 Meeting pianificati
Scrum basa la gestione dello sviluppo attraverso meeting specializzati in cui gli sviluppatori prendono decisioni sul progetto e/o si allineano rispetto all'andamento del lavoro, in particolare:
 - Meeting iniziale: oltre a eleggere *product owner*, *domain expert* e *scrum master*, bisogna organizzare gli altri meeting e redigere un **product backlog** nel quale sono definiti i macrotask del progetto ricavati da una fase di analisi dei requisiti svolta in precedenza. Grazie ad esso è possibile osservare un avanzamento globale dello sviluppo.
 - Sprint settimanali: abbiamo scelto una cadenza settimanale per gli sprint, cercando di suddividere il lavoro in maniera il più equilibrata possibile.
 - Meeting inizio sprint: all'inizio di ogni sprint viene effettuato un meeting nel quale vengono definiti gli **sprint goal** (obiettivi dello sprint, parte del progetto da realizzare) e lo **sprint backlog** (documento sintetico contente la suddivisione in task degli obiettivi settimanali, con tanto di assegnazione dei task agli sviluppatori).
 - Meeting fine sprint: a fine di ogni sprint viene effettuato un meeting durante il quale viene scritta la **sprint review**, in cui vengono espressi i commenti relativi allo sprint passato, si controlla di aver raggiunto i *goal* e si aggiorna il *product backlog* di conseguenza.
 - Standup meeting: incontro giornaliero di breve durata, durante il quale il team si sincronizza sul lavoro svolto e condivide aspetti critici, se emersi.

## 2.2 Divisione in itinere dei task
Durante il meeting di inizio *sprint* viene definito lo *sprint backlog*, che contiene una suddivisione in *task* del lavoro. Per ognuno viene valutata la complessità e si stima la difficoltà di sviluppo a priori. Nel nostro caso la scala di difficoltà scelta per i singoli *task* va da 0 a 5 mentre nel *product backlog* viene riportata la somma di questi valori per gruppo di appartenenza.  
Tali *task* sono successivamente vengono assegnati agli sviluppatori, tentando per quanto possibile di mantenere un carico di lavoro bilanciato.

Per il mantenimento dello stato di svolgimento dei task si è deciso di utilizzare *GitHub Projects*, uno strumento simile al più noto *Trello*, con il vantaggio di averlo integrato direttamente nel *repository* di GitHub.  
Tale strumento permette di assegnare i *task* tramite **drag & drop** a diverse liste che ne indicano lo stato, nel nostro caso abbiamo usato:
- Todo: per i *task* da svolgere, ma non ancora presi in carico.
- Doing: per i *task* che vengono presi in carico e sono in fase di svolgimento/completamento.
- Done: per tutti i *task* che sono definiti come conclusi.


## 2.3 Revisione in itinere dei task
Abbiamo scelto di mantenere immutabile l'elenco dei *task* durante il corso dello sprint, lasciando eventuali modifiche dovute alla rimozione di task rivelatosi inutili o all'aggiunta di task non programmati al meeting di fine sprint.
Le uniche modifiche apportabili allo *sprint backlog* ammissibili durante lo sprint riguardavano l'aggiornamento dello stato del task, ossia del numero che indica il carico di lavoro rimanente.
Per ogni task assegnato è stata considerata la seguente *definition of done*: un task o una funzionalità è da considerarsi terminata nel momento in cui è stata adeguatamente testata e documentat, ha passato una code review (automatica o manuale a seconda dell'importanza) e soddisfa le aspettative del committente.

## 2.4 Strumenti utilizzati per i test
Si è deciso di svolgere l'intero progetto seguendo il modello di sviluppo **Test-Driven Development (TDD)**, esso consiste nell'organizzare lo sviluppo del progetto secondo tre fasi:
- Red: implementare i test per ciò che si deve sviluppare ancor prima di averlo sviluppato (rosso perchè solitamente a questo punto il test non compila/non passa).
- Green: sviluppo del codice volto al superare i test, concentrandosi sull'efficacia piuttosto che sull'eleganza.
- Refactor: riscrittura del codice appena implementato ritoccando, se necessario, anche il codice relativo a test precedenti.

Queste tre fasi sono iterate per ogni singola implementazione necessaria al completamento del progetto, solitamente impiegando un periodo di tempo inferiore a una ventina minuti.
Tale metodologia di lavoro consente di scrivere codice di qualità, minimizzando la perdita di tempo relativa all'incorrettezza dell'implementazione. Seguendo il metodo tradizionale, un programmatore potrebbe sviluppare tutto il giorno e trovarsi alla fine con un codice che non fa quello per cui è stato scritto, potenzialmente perdendo un'intera giornata di lavoro: grazie al *TDD*, invece, questo rischio si riduce a una ventina di minuti (nel caso peggiore).

Per i test automatici abbiamo usato la libreria **ScalaTest**: tra i metodi di scrittura dei test da essa offerti, la nostra scelta è ricaduta su *Describe - it*, la quale permette di scrivere test ordinati e composti secondo una descrizione gerarchica del dominio testato.
I test scritti in questo modo risultano essere di facile comprensione ed esplicativi nel momento in cui uno di esso non dovesse passare.
Tale metodologia è estendibile tramite *Give - When - Then*, *keyword* grazie alle quali è possibile esplicare un ordinamento temporale dell'esecuzione dei test.

## 2.5 Strumenti utilizzati per la build automation
Come strumento per la build automation è stato utilizzato Scala Build Tool (**sbt**), che ha permesso una gestione efficiente delle dipendenze del progetto:
- *scalafx*: per la realizzazione della GUI
- *TuProlog*: per l'implementazione del database in prolog.
- *scalatest*: per i test.
- *scala-csv*: per la generazione di file *CSV*.
- *Gson*: per la generazione di file *JSON*.  

Oltre alle dipendenze è stato utilizzato un plugin per migliorare la qualità del codice: *scalafmt*, necessario per la formattazione automatica del codice.

## 2.6 Strumenti utilizzati per la Continuous integration
Il *repository* su *GitHub* ha due *branch* principali: **master**, contenente la versione finale del progetto, e **develop**, contenente l'ultima versione funzionante realizzata.
Per l'implementazione di ogni *task* si è deciso di creare un nuovo *branch* a partire da develop: una volta terminato il task viene fatta una **pull request** che, se confermata, permette di effettuare un **merge** delle modifiche su develop.
Per confermare una *pull request* è necessario che essa superi due tipologie di controllo:
- Manuale: un altro membro del team deve revisionare il codice scritto e approvare i cambiamenti fatti (**Code Review**).
- Automatico: grazie alle **GitHub Actions** ogni *pull request* fa partire la verifica dei *test* su tre **container** diversi (Ubuntu, Windows e Mac), grazie ai quali ci assicuriamo del corretto superamento dei test e del corretto funzionamento dell'applicazione su ogni piattaforma (*multi-platform*).
Una volta integrati i cambiamenti su *develop*, il *branch* usato per lo sviluppo puo' essere eliminato in sicurezza (senza perdite di codice).