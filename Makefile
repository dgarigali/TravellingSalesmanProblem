TEST := test_1

all:
	javac -Xlint src/event/*.java src/main/*.java src/sim/*.java src/species/*.java src/structure/*.java
	jar cfm exec.jar src/META-INF/MANIFEST.MF src/event/* src/main/* src/sim/* src/species/* src/structure/*
	java -jar exec.jar TESTS/$(TEST).xml
	
clean:
	rm src/event/*.class src/main/*.class src/sim/*.class src/species/*.class src/structure/*.class
	rm exec.jar