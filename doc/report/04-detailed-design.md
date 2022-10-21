# 4 Analisi dettagliata

### Obiettivi
// roba funzionale
// no eccezioni

## Filippo Benvenuti

### Design mvc
Per una leggibile e idiomatica implementazione del pattern architetturale MVC si è pensato di realizzarlo tramite Cake Pattern
// cake pattern
// gustosa separazione componenti
// dipendenze implicite

### Design simulation engine
// gestore motore simulazione (start stop velocità)
// macchina a stati finiti

### Design prolog engine

### Design *Entity*

## Elisa Albertini

### Design acquario

![UML_acquario<](img/UML_aquarium.png)

Parlando del design dell'**acquario** ho scelto di strutturarlo in modo da renderlo il più modulare possibile. Questo permette, in un secondo momento, di lavorare singolarmente con le sue varie componenti e aggiungerne di nuove in caso si voglia ampliare la simulazione.
Le principali componenti sono la **popolazione**, nel nostro caso specifico composta da pesci erbivori, carnivori e alghe, il suo **stato**, composto da temperatura, luminosità, ph, livello di impurità e livello di ossigenazione, e il **cibo disponibile**. Dato che popolazione e stato dell'acquario sono due componenti più complesse sono state realizzate come due classi separate poi incapsulate all'interno dell'acquario.
 
Le componenti risultanti sono state *Aquarium*, che ha come proprietà *Population*, *AquariumState* (due classi a se stanti) e che contiene anche il cibo disponibile.
 
Dato che ogni componente, compreso il cibo, richiede determinate funzionalità, ognuno di essi implementa delle interfacce che le forniscono. 
* *Population* implementa due differenti interfacce, una che modella la popolazione di pesci e fornisce i metodi che li restituiscono differenziati per tipo di alimentazione, e una che fornisce i metodi per aggiungere e togliere abitanti nell'acquario.
* *AquariumState* implementa un’interfaccia che fornisce i metodi per modificare i parametri dell'acquario.
* *Aquarium* implementa due interfacce che forniscono metodi per la gestione del cibo. Una modella il cibo disponibile e fornisce i metodi che lo restituiscono differenziato per tipo, mentre la seconda fornisce i metodi che permettono di andare ad aggiungerne e toglierne.
 
Tutti i metodi e le classi sono stati realizzati seguendo i principi della programmazione funzionale. Questo sarà approfondito più in dettaglio nel capitolo di implementazione.


### Design interazioni
![UML_interaction](img/UML_Interaction.png)
UML della factory di interazioni

Una parte fondamentale della simulazione sono le interazioni fra i componenti dell'acquario.
Dopo un'attenta analisi dei requisiti sono state definite le principali:
* Pesce che interagisce
    * con un pesce
    * con un'alga
* Entità (pesce/alga/cibo) che interagisce con lo stato dell'acquario
* Stato dell acquario che interagisce
  * con un pesce
  * con un'alga
 
 Il numero elevato di combinazioni, ovviamente, porta a dover realizzare un gran numero di differenti implementazioni. Queste, per quanto possano essere differenti fra loro, hanno una struttura comune che può essere modellata da un'unica interfaccia. Questo mi ha portato alla realizzazione di una **factory di interazioni**. 
 
Factory è un **design pattern creazionale** che permette di definire l'interfaccia per creare un oggetto ma delega la creazione dello stesso alle sottoclassi che la implementano.
Come benefici questo porta a ridurre al minimo le ripetizioni e la complessità del codice. Questo pattern permette anche, in un secondo momento, di aggiungere ulteriori implementazioni senza dover modificare il design del sistema.
 
Detto questo, l'interazione è stata modellata tramite l'interfaccia *Interaction* che espone un metodo che se chiamato ritorna il risultato dell'interazione fra due componenti. Dato che ogni specifica interazione ha un diverso tipo di ritorno, l'interfaccia è di tipo generico, in modo che sia poi la specifica implementazione a definirlo.

## Emanuele Lamagna

### Design *chronicle*

### Design view