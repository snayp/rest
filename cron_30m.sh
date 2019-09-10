#!/bin/bash
cd /home/maven/rest
git pull origin master
mvn clean
mvn -Dtest=CacheTest test