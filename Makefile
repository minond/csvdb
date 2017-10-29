.PHONY: build test

build: prep
	javac -d classes src/main/csvdb/*.java
	java -cp ./classes csvdb/Main

test: prep
	javac -d classes src/main/csvdb/*.java src/test/csvdb/*.java
	java -ea -cp ./classes csvdb/Test

prep:
	@if [ ! -d classes ]; then mkdir classes; fi
