# TIC-TAC-TOE

Here's my version of tic-tac-toe developed using Clojure.

Clojure Docs: https://clojure.org
## How to run a clojure project?

1. To build locally with Maven:

  - To build (output JARs in target/): `mvn package`
  - To build without testing: `mvn package -Dmaven.test.skip=true`
  - To build and install in local Maven repository: `mvn install`
  - To build a standalone jar with dependencies included: `mvn -Plocal -Dmaven.test.skip=true package`
  - To run with the standalone jar: `java -jar clojure.jar`

2. To run as lein project

- Clone the project: `https://github.com/vishwagosalia/Tic-tac-toe.git`
- Open terminal in the current project's repository `cd tic-tac-toe`
- Run `lein clean, compile, uberjar`
- Run `lein repl :headless`
- Connect the repl to the port.

