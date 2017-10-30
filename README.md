## Module project proof of concept

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

Run `make` to build and execute and `make test` to run tests.
