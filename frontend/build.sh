#!/bin/bash
git pull
npm install
npm run build
sudo rm -R /var/www/html/*
sudo cp -R build/. /var/www/html
