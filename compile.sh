#!/bin/bash
mvn clean install
cd voteria-client/
mvn gwt:compile
cd ../voteria-server
mvn verify
