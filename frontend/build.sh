#!/bin/bash
git pull
npm build
sudo rm -R /var/www/html/*
cp build/. /var/www/html
