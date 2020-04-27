#!/bin/gfsh

start locator --classpath=/pwd/out/production/classes
start server --classpath=/pwd/out/production/classes
create region --name=stocks --type=REPLICATE
create region --name=companies --type=REPLICATE
