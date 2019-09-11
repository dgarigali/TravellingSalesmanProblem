TEST := test_1

all:
	make -C src/
	java -jar exec.jar TESTS/$(TEST).xml
	
clean:
	rm exec.jar