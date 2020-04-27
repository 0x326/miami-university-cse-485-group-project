#!/bin/gfsh

start locator --classpath=/pwd/build/classes/java/main
start server --classpath=/pwd/build/classes/java/main
create region --name=stocks --type=REPLICATE
create region --name=companies --type=REPLICATE
