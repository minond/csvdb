# Module project proof of concept

This is a proof of concept for what we use as the project for Module 6 of the
UtahRETC Java Class. Summary of the project: build a generic CRM cli project
that uses CSVs as the data store.

We could provide `csvdb.Parser` and `csvdb.Source` (or something like them that
lets us read and write to a CSV) and let students write the logic found in
`csvdb.Main.main`. The requirements could be to write `search`, `update`,
`increment`, and `decrement` operations that are done upon the data found in
the csv files. I'm thinking something like this:

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

The final program could pretty minimal, allowing students to extend it for
extra credit or future homework assignments. An example of this is error
handling. We could let students write a `main(...) throws Exception` method in
the beginning, which would crash on any and every error, but add better and
more specific error handling in the future.

This is a working example. To build and execute, run `make`. Run `make test` to
run tests. Idea credit goes to the awesome
[@MohamedDataStruct](https://github.com/MohamedDataStruct).
