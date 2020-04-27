#!/bin/gfsh

start locator --classpath=/pwd
start server --classpath=/pwd
create region --name=stocks --type=REPLICATE
create region --name=companies --type=REPLICATE
