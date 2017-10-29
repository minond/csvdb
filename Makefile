.PHONY: build
build:
	@if [ ! -d classes ]; then mkdir classes; fi
	javac src/main/csvdb/Main.java -d classes
	java -cp ./classes csvdb/Main
