# Patterns
Patterns implemented in Scala. Designed for getting up to speed quickly.

## How to compile and run example
cd to a pattern (e.g. cd singleton)

sbt compile

sbt run

## Quick glance of patterns
grep -r 'purpose' . will produce something like

./singleton/src/main/scala/Session.scala:// purpose (manages access to common resource (e.g. session, db connection pool))

./immutable/src/main/scala/Invoice.scala:// purpose (creates new object when change happens, does not change existing object)