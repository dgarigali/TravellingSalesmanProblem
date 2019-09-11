all:
	javac -Xlint -encoding ISO-8859-1 event/*.java main/*.java sim/*.java species/*.java structure/*.java
	jar cfm exec.jar META-INF/MANIFEST.MF main/*.class event/*.class sim/*.class species/*.class structure/*.class
	rm event/*.class main/*.class sim/*.class species/*.class structure/*.class
	mv exec.jar ../