.PHONY: build
build:
	@if [ ! -d classes ]; then mkdir classes; fi
	javac -d classes src/main/csvdb/*.java
	java -cp ./classes csvdb/Main
