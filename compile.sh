#!/bin/bash
cd voteria-common/
mvn clean install
cd ../voteria-client/
mvn gwt:compile
cd ../voteria-server
mvn clean verify
