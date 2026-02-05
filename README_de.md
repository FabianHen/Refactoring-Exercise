# Refactoring Aufgabe

## Inhaltsverzeichnis
1. [Golden Copy – Allgemein](#golden-copy--allgemein)
2. [Gilded Rose](#gilded-rose)
   - [Golden Copy](#golden-copy)
   - [Allgemeine Änderungen](#allgemeine-änderungen)
   - [Refactoring-Muster](#refactoring-muster)
      - [Strategy Pattern](#strategy-pattern)
      - [Factory Pattern](#factory-pattern)
   - [Umsetzung der gewünschten Änderungen](#umsetzung-der-gewünschten-änderungen)
3. [Tennis](#tennis)
   - [Golden Copy](#golden-copy-1)
   - [Allgemeine Änderungen](#allgemeine-änderungen-1)
   - [Refactoring-Muster](#refactoring-muster-1)
      - [State Pattern](#state-pattern)
   - [Umsetzung der gewünschten Änderungen](#umsetzung-der-gewünschten-änderungen-)
   - [Hinzufügen und Anpassung von Testfällen](#hinzufügen-und-anpassung-von-testfällen)
   - [Umgang mit den weiteren Dateien](#umgang-mit-den-weiteren-dateien)
4. [Trip Service](#trip-service)
   - [Golden Copy](#golden-copy-2)
   - [Allgemeine Änderungen](#allgemeine-änderungen-2)
   - [Umsetzung der gewünschten Änderungen](#umsetzung-der-gewünschten-änderungen-1)
5. [Expense Report](#expense-report)
   - [Golden Copy](#golden-copy-3)
   - [Allgemeine Änderungen](#allgemeine-änderungen-3)
   - [Refactoring-Muster](#refactoring-muster-2)
      - [Strategy Pattern](#strategy-pattern-1)
   - [Umsetzung der gewünschten Änderungen](#umsetzung-der-gewünschten-änderungen)

## Golden Copy – Allgemein
Um sicherzustellen, dass die Funktionalität der Anwendungen während des Refactorings erhalten bleibt, sollte in derTheorie für jede Anwendung eine "Golden Copy" erstellt werden. Diese Golden Copy dient als Referenzpunkt, um sicherzustellen, dass alle Änderungen korrekt implementiert wurden und keine unerwarteten Fehler eingeführt wurden. Um eine Golden Copy zu erstellen, müssen für jedes Verhalten der Anwendung Tests geschrieben werden, die das erwartete Verhalten überprüfen. Diese Tests sollten alle aktuellen Verhalten abdecken und sicherstellen, dass die Anwendung wie erwartet funktioniert. Für jedes Verhalten der Anwendung wird dabei folgendes gemacht:

1. Der Gewünschte Input wird an die Anwendung übergeben.
2. Das Ergebnis wird durch einen Testfall mit einem assert festgehalten.
3. Falls sich das Ergebnis ändert, schlägt der Test fehl und zeigt an, dass die Änderung das Verhalten der Anwendung beeinflusst hat. Wenn alle Tests erfolgreich sind, kann davon ausgegangen werden, dass die Änderungen keine unerwarteten Fehler eingeführt haben.

Erst nachdem die Golden Copy erstellt wurde, sollten Refactorings durchgeführt werden. Nach jedem Refactoring sollten die Tests der Golden Copy erneut ausgeführt werden, um sicherzustellen, dass die Funktionalität der Anwendung weiterhin erhalten bleibt. In den folgenden Abschnitten wird die theoretische Herangehensweise für die entsprechende Golden Copy der jeweiligen Anwendungen kurz beschrieben. Wie mit Ihnen besprochen, sind die Golden Copies in diesem Repository nicht implementiert.


## Gilded Rose
### Golden Copy
In diesem Projekt sollten für jedes Item und dessen Verhalten Tests geschrieben werden, um eine Golden Copy zu erstellen. Die folgenden Verhaltensweisen sollten neben Tests für Grenzwerte abgedeckt werden:

1. **Normale Artikel** – täglicher Qualitätsabfall; doppelt so schnell nach Ablauf des SellIn
2. **Aged Brie** – Qualität steigt; doppelt wenn SellIn <0 Cap bei 50
3. **Sulfuras** – unveränderliche Quality (80) und unverändertes SellIn
4. **Backstage passes** – +1 (>10d), +2 (<=10d), +3 (<=5d), 0 nach Event
5. **Invarianten** – Quality ∈ [0,50] (außer Sulfuras 80), SellIn dekrementiert (außer Sulfuras)

### Allgemeine Anpassungen
Um die Lesbarkeit des Codes zu verbessern, habe ich zu Beginn des Refactorings ein paar allgemeine Anpassungen vorgenommen. Dazu gehören folgende Änderungen:
- Umbau der for-Schleife in eine foreach-Schleife zur besseren Lesbarkeit.
- Inkrementierung und Dekrementierung von Variablen durch die Verwendung von `++` und `--` anstelle von `foo = foo + 1` und `foo = foo - 1`.
- Auslagerung der Strings in Konstanten zur besseren Lesbarkeit und geringerer Fehleranfälligkeit.
- Wie in der Projektbeschreibung erwähnt, habe ich sowohl die `updateQuality`-Methode als auch die `items`-Liste statisch gemacht, um die Notwendigkeit der Instanziierung der `GildedRose`-Klasse zu vermeiden.
- Da sich das `Sulfuras`-Item nicht ändert habe ich eine überprüfung darauf mit einer continue-Anweisung ergänzt, um ständige Überprüfungen zu vermeiden.
- Anstatt der überprüfung mit `!item.name.equals(FOO)` in Kombination mit einer Verschachtelung und der entsprechenden Behandlung im else-Block, habe ich die Logik umgedreht und mit `item.name.equals(FOO)` gearbeitet. Dies reduziert die Verschachtelung und verbessert die Lesbarkeit des Codes.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/20f08b14118dadafe76c27edfbb7880fca61d419)

### Refactoring-Muster
#### Strategy Pattern
Da die `updateQuality`-Methode sehr lang und komplex war, habe ich das Strategy Pattern verwendet, um die verschiedenen Verhaltensweisen der Items zu kapseln. Für jedes Item wurde eine eigene Klasse erstellt, die das Verhalten des jeweiligen Items implementiert. Dadurch konnte die `updateQuality`-Methode erheblich vereinfacht werden, da sie nun nur noch überprüfen muss, um welches Item es sich handelt, und dann das entsprechende Strategy-Objekt aufruft.

 Anstatt, wie üblich, ein Interface für die Strategien zu erstellen, habe ich mich hier für eine abstrakte Klasse entschieden. Dies habe ich getan, da für alle Items gilt, dass `quality` nicht größer als `50` und nicht kleiner als `0` sein darf. Diese Logik habe ich in der abstrakten Klasse implementiert, um Redundanzen in Form von Überprüfungen auf `item.quality < 50` oder `item.quality > 0` zu vermeiden.

Obwohl für das `Sulfuras`-Item keine Änderungen vorgenommen werden, habe ich dennoch eine eigene Strategie-Klasse dafür erstellt. Dies dient der Konsistenz und erleichtert zukünftige Erweiterungen oder Änderungen am Code.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/b50f308a45e0b32093b117b8626280d6f34ad77f)

#### Factory Pattern
Nach der Umsetzung des Strategy Patterns umfasste die `updateQuality`-Methode nur noch 9 Zeilen und ist somit bereits sehr übersichtlich. Da die der Name der `GildedRose`-Klasse jedoch eine weitreichende Funktionalität suggeriert, habe ich mich dazu entschieden, das Factory Pattern zu implementieren. Dadurch wird die Verantwortung für die Erstellung der Item-Strategien von der `GildedRose`-Klasse auf eine eigene Factory-Klasse übertragen. Dies verbessert die Single-Responsibility-Prinzip und macht den Code noch wartbarer.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/c91894af506dc89ec9134231f9b587889894e85f)

### Umsetzung der gewünschten Änderungen
Nachdem die Refactorings abgeschlossen waren, habe ich die gewünschten Änderungen implementiert. Die Erweiterung umfasste das Hinzufügen eines neuen Item-Typs namens `Conjured`, der sich doppelt so schnell wie normale Items verschlechtert. 

Bei der Umsetzung dieser Vorgabe war ich mir unsicher, da es so, wie es beschrieben ist, drei mögliche Interpretationen gibt:
1. `Conjured` ist ein Attribut der `Item`-Klasse und müsste in den bestehenden Strategy-Klassen berücksichtigen.
2. `Conjured` ist ein Teil der Item-Namen (z.B. `Conjured Cheddar`). Dabei müsste dann in der Factory-Klasse überprüft werden, ob der Name mit `Conjured` beginnt.
3. `Conjured` ist ein eigener Name und kann einfach als eigene Strategy-Klasse implementiert werde und mit einer Zeile in der Factory-Klasse berücksichtigt werden.

Da die dritte Interpretation im Zuge des durchgeführten Refactorings für mich am sinnvollsten erschien, habe ich mich für diese entschieden. Somit wurde eine eigene Strategy-Klasse namens `ConjuredItemStrategy` erstellt, die das Verhalten der `Conjured`-Items implementiert. In der Factory-Klasse wurde dann eine weitere Bedingung hinzugefügt, um diese neue Strategie zu berücksichtigen.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/3bda9de45b177db68338ed7b415f12ffa99fdf78)

## Tennis
### Golden Copy
In dem Projekt sind bereits die benötigten Test für eine Golden Copy vorhanden. Diese beschreiben durch ein Array von Punkten mit den entsprechenden Spielergebnissen das Verhalten des Tennis-Spiels.

### Allgemeine Anpassungen
Um auch hier die Lesbarkeit des Codes zu verbessern, habe ich zu Beginn des Refactorings ein paar allgemeine Anpassungen vorgenommen. Dazu gehören folgende Änderungen:
- String vergleiche mit `==` wurden durch `equals`-Methoden ersetzt.
- Auslagerung der "Magic Number" `4` in die Konstante `WIN_THRESHOLD` zur besseren Lesbarkeit und geringerer Fehleranfälligkeit.
- Erstellen von `getTieScore()`, `getWinScore()` und `getNormalScore()` durch Extract Method, in der `getScore()`-Methode, um die Lesbarkeit zu verbessern.
- Verwendung eines `StringBuilder` anstelle von `+=` in der `getNormalScore()` Methode.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/c3cad12a1a1035253869223f9872dca9276d178e)

### Refactoring-Muster
#### State Pattern
Da es sich in dem Projekt um eine Simulation von einem Spiel mit mehreren Zuständen handelt, habe ich mich für die Verwendung des State Patterns entschieden. Hierbei wird für jeden möglichen Spielzustand eine eigene Klasse erstellt, die das Verhalten in diesem Zustand implementiert. Dadurch wird die `getScore()`-Methode erheblich vereinfacht, da sie nun nur noch den aktuellen Zustand abfragen und die entsprechende Methode aufrufen muss. Der Übergang in die anderen states ist durch Methoden in den jeweiligen State-Klassen geregelt. Folgende States wurden implementiert:
- `NormalState` – regulärer Spielstand
- `DeuceState` – Gleichstand nach regulärem Spiel
- `AdvantageState` – Vorteil für einen Spieler nach regulärem Spiel
- `WinState` – Sieg für einen Spieler

Während der Implementierung habe ich auch noch ein paar zusätzliche allgemeine Anpassungen vorgenommen:
- Extrahieren der Namen der Punktestände in ein `String Array` für übersichtlicheren Code. Diese Logik ist nun in `NormalState` zu finden.
- Extract Class mit der `Player`-Klasse, um die Informationen über die Spieler zu kapseln und die für später vorgesehenen Änderungen zu erleichtern.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/c3cad12a1a1035253869223f9872dca9276d178e)

### Umsetzung der gewünschten Änderungen
In der Aufgabenstellung wurde bereits darauf hingewiesen, dass absichtlich dieser Fehler eingebaut wurde: 

*"Die Spielernamen sind fest auf „player1” und „player2” codiert. Nach der Refaktorierung sollten Sie dieses Problem beheben und geeignete Testfälle hinzufügen, um zu überprüfen, ob Ihre Korrektur funktioniert."*

Wie zuvor erwähnt habe ich bereits die `Player`-Klasse extrahiert und diesen Schritt somit erleichtert. Ich musste nun lediglich in der `TennisGame1`-Klasse auf den Namen der `Player`-Objekte zugreigen, anstatt die fest codierten Strings zu verwenden. 

Zudem habe ich in dieser Datei den `else`-Block zu einem `else if`-Block umgewandelt, was das grundsätzliche Verhalten des Spiels jedoch nicht unverändert. Nur bei eingabe eines ungültigen Spielernamens wird nun nicht mehr die Punktzahl des zweiten Spielers erhöht.

Eine weitere Änderung, die die gegebene Anforderung mit sich zog, war das Anpassen der `getScore()`-Methode in den Klassen `AdvantageState` und `WinState`. Hier wird nun ebenfalls auf die Namen der `Player`-Objekte zugegriffen.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/64357bb7d1031c81344e39fa1cd605a20b8de86b)

### Hinzufügen und Anpassung von Testfällen
Durch die Einführung der Player-Objekte hat sich die interne Punktelogik geändert: 
Die `wonPoint()`-Methode vergleicht nun die übergebenen Spielernamen mit den Namen 
der Player-Objekte. 

Die vorhandenen Testfälle mussten an sich nicht geändert werden, 
da sie weiterhin die korrekten Spielernamen übergeben.  
Wichtig ist jedoch, dass die Strings, die in den Tests verwendet werden, 
genau den Namen entsprechen, mit denen das TennisGame erstellt wurde.  

Um Konsistenz zu gewährleisten, könnten die Spielernamen in den Tests 
entweder als Konstanten definiert oder einmal global gesetzt werden. 
Dieses Verhalten lässt sich zudem durch einen zusätzlichen Testfall dokumentieren, 
der überprüft, dass die Punktelogik fehlschlägt, wenn Namen inkonsistent verwendet werden.

### Umgang mit den weiteren Dateien
Nachdem ich mich mit den restlichen TennisGame Klassen auseinandergesetzt habe, habe ich festgestellt, dass sie alle dieselbe fachliche Logik abbilden, jedoch in unterschiedlicher Struktur und Stil. Die Unterschiede lagen ausschließlich in der Art der Punktzählung, der Darstellung des Scores und der internen Variablenbenennung:
- **TennisGame1** verwendet eine lange getScore-Methode mit einer for schleife und switch-Anweisungen.
- **TennisGame2** verwendete eine riesige if/else-Struktur
- **TennisGame3** verwendet übermäßig komplexe Abfragen um möglichst wenige Zeilen zu benötigen
- **TennisGame4** nutzt viele verkettete `ResultProvider`-Objekte, was die Klassen schwer verständlich und unübersichtlich macht.
- **TennisGame5** hat alle möglichen Scores bis `4` in einer Map hinterlegt und greift darauf zu.
- **TennisGame6** ähnelt TennisGame1, verwendet jedoch 2 `switch` Anweisungen anstatt einer zweifachen for-Schleife.
- **TennisGame7** ist wie TennisGame6, aber formatiert den Score zusätzlich mit einer Nachricht wie „Current score: …, enjoy your game!“

Da alle diese Dateien die selbe Funktion haben, das Löschen der Dateien jedoch zu fehlschlagenden Tests führen würde, habe ich mich dazu entschieden, die übrigen Implementierungen (TennisGame2–7) nun über Delegationen auf `TennisGame1 `umzusetzen. Dadurch bleibt das beobachtbare Verhalten vollständig identisch (Tests bleiben grün), ohne jede Implementierung einzeln refaktorieren zu müssen.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/97f84f5ebf3d2f5c537096ea4509e519c67b53f4)

## Trip Service
### Golden Copy
Um das bestehende Verhalten des `TripService` vollständig abzudecken, müssen Tests für alle gegebenen Geschäftsregeln implementiert werden. Diese Regeln umfassen:
1. **Benutzer nicht angemeldet** – Löst eine `UserNotLoggedInException` aus.
2. **Benutzer ohne Freunde** – Gibt eine leere Liste zurück.
3. **Benutzer mit Freunden, aber ohne Reisen** – Gibt eine leere Liste zurück.
4. **Benutzer mit Freunden und Reisen** – Gibt eine Liste der Reisen der Freunde zurück.

Das Problem hierbei ist, dass die `TripService`-Klasse von zwei externen Diensten abhängt: `UserSession` und `TripDAO`. Diese werfen Fehler, wenn in einer Testinstanz auf ihre statischen Methoden zugegriffen wird. Daher müssen erst ein paar allgemeine Änderungen vorgenommen werden, um die Testbarkeit zu gewährleisten.
### Allgemeine Anpassungen
Um die Testbarkeit der `TripService`-Klasse zu verbessern, habe ich einige allgemeine Anpassungen vorgenommen:
- Ich habe den Aufruf der beiden statischen Methoden `UserSession.getLoggedInUser()` und `TripDAO.findTripsByUser(...)` in `protected` Methoden der `TripService`-Klasse gekapselt. Somit kann ich diese Methoden in einer Test-Subklasse überschreiben und Mock-Daten zurückgeben. Dadurch werden die werden die statischen Methoden nicht mehr direkt aufgerufen, wodurch der Code testbar wird.
- Ich habe die Methode `isFriend(...)` ausgelagert, um die Lesbarkeit der Hauptmethode zu verbessern.
- Ich habe in der Methode `getTripsByUser(...)` eine frühe Rückgabe hinzugefügt, wenn der Benutzer keine Freunde hat. Dies reduziert die Verschachtelung und verbessert die Lesbarkeit des Codes.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/79359ef97619a09a443945086b18f318cc90b5dd)
### Umsetzung der gewünschten Änderungen
Nachdem ich nun die Testbarkeit der `TripService` Klasse sichergestellt hatte, könnte ich nun anfangen Tests für die Golden Copy zu implementieren. Da für diese Abgabe keine Tests implementiert werden müssen, hab ich lediglich die Datei `TestableTripService` im Test-Ordner erstellt. Diese erbt von der `TripService` Klasse und überschreibt dabei die beiden Methoden `getLoggedUser()` und `findTripsByUser(...)`, um Mock-Daten zurückzugeben. Durch die zusätzliche Bereitstellung verschiedener Konstruktoren und Setter können die Mock-Daten flexibel angepasst werden, um verschiedene Testfälle abzudecken.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/733eeece730c5758b5b09bfe7c746a5dff9ff234)

## Expense Report
### Golden Copy
Da die Methode keinen Rückgabewert hat, sondern ausschließlich auf die Konsole schreibt, besteht das beobachtbare Verhalten ausschließlich aus der Textausgabe. Daher muss man in den Tests die Konsolenausgabe abfangen und mit den erwarteten Ausgaben vergleichen. Um eine vollständige Golden Copy zu erstellen, muss in den Tests folgendes abgesichert werden:
- der Bericht beginnt immer mit de Kopfzeile `Expenses <Datum>`
- jede Ausgabezeile ist korrekt :
   - richtiger Name in Abhängigkeit vom `ExpenseType`
   - richtiger Betrag
   - korrekt gesetzter Marker (`X` oder Leerzeichen)
- die Summen am Ende des Berichts sind Korrekt:
   - Summe der Ausgaben für Essen
   - Summe aller Ausgaben

Eine Sache, die bei der Erstellung des zu beachten ist, ist die Überprüfung der Kopfzeile. Da diese das aktuelle Datum enthält und somit nicht deterministisch bzw reproduzierbar ist, darf dort nur überprüft werden, ob "Expenses" vorkommt und danach noch etwas folgt. Alle anderen Zeilen können direkt mit den erwarteten Strings verglichen werden.

### Allgemeine Anpassungen
Um auch hier die Lesbarkeit des Codes zu verbessern, habe ich zu Beginn des Refactorings ein paar allgemeine Anpassungen vorgenommen. Dazu gehören folgende Änderungen:
- Ich habe die Erstellung des `mealOverExpensesMarker` zur Verbesserung der Lesbarkeit in eine eigene Methode `getMealOverExpensesMarker(...)` ausgelagert. Später habe ich diese Methode dann in die `Expense`-Klasse verschoben, da sie zu der responsibility dieser Klasse gehört.
- Da sowohl das Expense limit als auch der `expenseName` von dem Enum abhängig sind, habe ich diese direkt in das enum übernommen. Dadurch wird die Lesbarkeit verbessert und das Vorkommen von "Magic Numbers" und Strings reduziert.
- Ich habe dem Enum noch den booleschen Wert `isMealExpense` hinzugefügt, um immer länger werdende if-Abfragen zu vermeiden.
- Ich habe das Enum `ExpenseType` und die Klasse `Expense` in eigene Dateien ausgelagert, um die Übersicht zu verbessern.
- Ich habe die Datenfelder der Berichtzeilen in ein `DTO`(Data-Transfer-Object) der Klasse `ReportLine` ausgelagert, um die Informationen einer Zeile des Berichts zu kapseln. Dadurch wird die Lesbarkeit der `printReport(...)`-Methode verbessert. Zudem kann das erstellen des Strings dann auch in dieser Klasse gehandelt werden, was dem Single-Responsibility-Prinzip entspricht. 

Eine weitere Sache, die ich gerne umgesetzt hätte, ist die Umwandlung der `Expense` Klasse in ein `Record`, da diese nur Daten hält. Da dies jedoch die Verwendung der Klasse in Tests oder anderen möglichen Dateien geändert hätte, habe ich diese Änderung nicht vorgenommen.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/121ddd3178a42d6126549e484bb5415788163890)

### Refactoring-Muster
#### Strategy Pattern
Nach den allgemeinen Anpassungen in der gegebenen Datei war die `ExpenseReport`-Klasse bereits deutlich übersichtlicher. Dennoch gab es noch einen unsauberen Punkt in der `printReport(...)`-Methode: die direkten `System.out.println(...)` Aufrufe. Um diese noch auszulagern entschied ich mich für die Implementierung des Strategy Patterns in Form von `Printer` Klassen. 

Beim Schreiben des Interfaces ist mir dann jedoch aufgefallen, dass ich, wie bei der `ReportLine`, alle Zeilenarten in eigene Klassen auslagern könnte, um hier nur noch die Methode `printLine(Line line)` implementieren zu müssen. Eine alternative wäre gewesen, für jede Zeilenart eine eigene Methode in dem Interface zu erstellen. Dies hätte jedoch zu einer Aufblähung des Interfaces geführt und die Implementierung der Klassen erschwert. Daher entschied ich mich für die erste Variante. Natürlich könnte man hier die berechtigte Kritik anbringen, dass es sich hierbei um over-engineering handelt. Da es sich jedoch um eine Refactoring-Aufgabe handelt, wollte ich hier die Gelegenheit nutzen, um das Strategy Pattern in Verbindung mit Polymorphie zu demonstrieren.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/7bc70fac3c2ee71bb96844bd7be59ee2d900af63)

Nachdem ich dieses Änderung nun umgesetzt habe, habe ich die Umsetzung des Strategy Patterns fortgeführt. Dabei habe ich das `Printer`-Interface geschrieben und das aktuelle Standard-Verhalten der `System.out.println(...)` Aufrufe in die Klasse `ConsolePrinter` verschoben. Die `ExpenseReport`-Klasse hat nun ein `Printer`-Objekt als Attribut, welches in der `printReport(...)`-Methode verwendet wird. Dieses Objekt ist standardmäßig auf eine Instanz der `ConsolePrinter`-Klasse gesetzt, damit sich das Verhalten der Klasse nicht ändert. Es kann jedoch über einen Konstruktor oder Setter angepasst werden. Durch diese Funktionalität können die Tests für die `ExpenseReport`-Klasse deutlich vereinfacht werden, da man nun einfach einen Mock-Printer übergeben kann, um die Ausgabe zu überprüfen, anstatt die Konsolenausgabe abzufangen. Ein solchen beispielhaften Printer habe ich in der Klasse `TestPrinter` implementiert. Dort werden die theoretisch geschriebenen Zeilen einfach in einer Liste gespeichert.

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/82533ef648a787d48e95e66d529ff3546094a16a) und [Vergessene Änderung anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/a87a864f8e39d0e3a33ec09e30fb84757a827698)

### Umsetzung der gewünschten Änderungen
Nachdem das Refactoring nun abgeschlossen war, konnte ich mich nun der Implementierung der gewünschten Änderungen widmen. In der Aufgabenstellung wurde folgende Anforderung gegeben:

*"Fügen Sie „Lunch“ mit einem Ausgabenlimit von 2000 hinzu."*

Durch meine Änderungen war die Umsetzung dieser Anforderung relativ einfach. Es musste lediglich ein neuer Eintrag im `ExpenseType`-Enum erstellt werden, um den neuen Ausgaben-Typ zu repräsentieren. Da die Informationen über den Namen und das Limit bereits in diesem Enum enthalten sind, musste an keiner anderen Stelle im Code eine Änderung vorgenommen werden, um die neue Kategorie zu unterstützen. 

[Änderungen anzeigen](https://github.com/FabianHen/Refactoring-Exercise/commit/aabe4e53e415ce2567368c2ace266304f703841f)