****************************************************************************************
++++++++++++++++++++++++++++++++++++++++ README ++++++++++++++++++++++++++++++++++++++++
****************************************************************************************

!WICHTIG! Zum Ausführen des Programms wird eine JAVA Version 20 benötigt !WICHTIG!

Ausführbare .jar Datei:

Im Ordner "Patientenakten" befindet sich eine ausführbare .jar Datei "patientenakten-ui.jar"
und ein Order "Data" in dem sich Beispiel-Datensätze zu Schauzwecken befinden.

Eine Änderung an diesen Datensätzen kann zum kompletten Funktionsverlust des Programms 
führen, darum bitten wir, keine Änderungen an den Daten vorzunehmen.

Falls ein durch ein unumgängliches Ereignis doch Daten corrupted werden sollen, befinden
sich im Order Patientenakten -> data -> ratings zwei Python Skripte:

	************************
	*** clearPatients.py ***
	************************

	Dieses Skript löscht alle Daten aus den vorhandenen .pat Dateien. Die Ausführung
	dieses Skripts sollte als erster Schritt ausgeführt werden, wenn sich die Inhalte der
	vorhandenen Dateien in einem nicht mehr bekannten Status befinden.

	************************
	**** fillPatients.py ***
	************************

	Dieses Skript erzeugt Bewertungsdaten in allen .pat Dateien welche sich im selben
	Ordner wie dieses Skript befinden. Die erzeugten Daten befinden sich im Zeitraum
	(now - 180d) bis now. Vor dem Ausführen dieses Skriptes UNBEDINGT! clearPatients.py
	ausführen, um eine komplette Spaghettifizierung der Daten zu verhindern!

