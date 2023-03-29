#!/bin/bash
git pull
mvn clean install -DskipTests
cp target/carrosluxo-*.jar target/carrosluxo.jar
sudo systemctl restart carrosluxo
sudo systemctl status carrosluxo
