.PHONY: build test

build: prep
	javac -d classes src/main/csvdb/*.java

test: prep
	javac -d classes src/main/csvdb/*.java src/test/csvdb/*.java
	java -ea -cp ./classes csvdb/Test

prep:
	@if [ ! -d classes ]; then mkdir classes; fi

exec:
	@echo "Running read operation:"
	java -cp ./classes csvdb/Main read ./data.csv 00005 first_name
	@echo
	@echo "Running update operation:"
	java -cp ./classes csvdb/Main update ./data.csv 00005 age 98
	@echo
	@echo "Resetting data.csv file."
	@git checkout data.csv
	@echo "Done."
