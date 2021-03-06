JDBC driver for SQLite using JNA instead of JNI to make it easy to deploy
(if you already have SQLite installed).
There are two layers:
 - a small one matching the SQLite API (package org.sqlite)
 - a bloated one matching the JDBC API (package org.sqlite.driver)

[![Build Status][1]][2]

[1]: https://secure.travis-ci.org/gwenn/sqlite-jna.png
[2]: http://www.travis-ci.org/gwenn/sqlite-jna

INSTALL
-------
1. https://github.com/twall/jna/blob/master/www/GettingStarted.md
2. http://www.sqlite.org/download.html
3. Ensure JVM and SQLite match (x86 vs x86-64)

On windows, to build your own x86-64 version (with cygwin&mingw):
```sh
x86_64-w64-mingw32-gcc.exe -Wl,--kill-at -O -shared -o sqlite3.dll -DSQLITE_ENABLE_COLUMN_METADATA -DSQLITE_ENABLE_FTS4 -DSQLITE_ENABLE_STAT3 -DSQLITE_THREADSAFE=1 -DSQLITE_DEFAULT_FOREIGN_KEYS=1 sqlite3.c
+ Stripping...
```

TODO
----
1. Fix as many unimplemented methods as possible.
2. JDBC J2EE compliance test suite
3. Benchmark

LICENSE
-------
Public domain
