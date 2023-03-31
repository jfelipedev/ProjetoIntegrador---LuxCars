#!/bin/bash
git pull
npm install
npm run build
sudo rm -R /var/www/html/*
cp build/. /var/www/html
