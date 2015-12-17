#!/bin/bash
pwd
cd voteria-client/
mvn clean install gwt:compile
cd ../voteria-server
mvn clean verify