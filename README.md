## Module project proof of concept

This is a proof of concept for what could be the first project in the UtahRETC
Java Programmer 1 Class (Module 6). Summary: build a generic CRM cli project
that uses CSVs as the data store.

We could provide `csvdb.Parser` and `csvdb.Source` (or something like them that
lets us read and write to a CSV) and let students write the logic found in
`csvdb.Main.main`. The requirements could be to write `search`, `update`,
`increment`, and `decrement` operations that are done upon the data found in
the csv files. I'm thinking the final api/use would be this:

```bash
# Usage: cmd <operation> <file> <id> <field> [updateValue]
# Prints out the value found in the `first_name` column of the row found in the
# `./data.csv` file that has an `id` (another column, the first one, always) of
# `00005`.
java -cp ./classes csvdb/Main read ./data.csv 00005 first_name

# Updates the `age` value of the row found in the `./data.csv` file that has an
# `id` (another column, the first one, always) of `00005`.
java -cp ./classes csvdb/Main update ./data.csv 00005 age 98
```

This would test them on loops (looping over rows until the right one is found),
conditionals (comparing values), storing state in variables (tracking the
column that is being requested or updates), and data types/casting (string to
int for increment/decrement).

"Done" for this project could pretty minimal, allowing students to extend it
for extra credit or future homework assignments. An example is adding error
handling as part of a future class. We could let students write a `main(...)
throws Exception` method in the beginning, which would crash on any and every
error, but add better and more specific error handling later as they learn
about Exceptions and `try`/`catch`.

This is a working example. To build and execute, run `make` and `make exec`.
Run `make test` to run tests. Idea credit goes to the awesome
[@MohamedDataStruct](https://github.com/MohamedDataStruct).
